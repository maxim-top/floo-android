package im.floo.floolib;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import im.floo.floolib.okhttpwrapper.OkHttpClientHelper;
import im.floo.floolib.okhttpwrapper.ProgressRequestListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;

public class BMXHttpClient {
    private static final Integer ERROR_HTTP_FAILED = 3;
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static int mTimeOut = 200000000;
    private static final String TAG = BMXHttpClient.class.getSimpleName();

    private static final int TIME_OUT = 10*10000000; //超时时间
    private static final String CHARSET = "utf-8"; //设置编码

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(40, TimeUnit.SECONDS)
                    .build();

    private static HashSet<String> sPathSet = new HashSet<>();

    private static boolean addPath (String path){
        synchronized (sPathSet){
            return sPathSet.add(path);
        }
    }

    private static boolean removePath (String path){
        synchronized (sPathSet){
            return sPathSet.remove(path);
        }
    }

    private static boolean isProcessing(String path){
        synchronized (sPathSet){
            return sPathSet.contains(path);
        }
    }

    private static class ResponseCodeInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            if (response.code() == HTTP_UNAUTHORIZED){
                //todo: BmxSdkMgr.getInstance().refreshToken();
            }
            return response;
        }
    }

    public final static native void FileOperationProgressCallback(long callbackAddr, long percent);

    private static Request.Builder addHeaders(Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String, String> kv : headers.entrySet()) {
            builder.addHeader(kv.getKey(), kv.getValue());
        }
        return builder;
    }

    public static int sendRequest(String strUrl, String method, Map<String, String> headers,
                                  String body, final StringBuilder strResponse) {
        int retCode = 0;
        try {
            Request.Builder builder = addHeaders(headers);
            Request request = null;
            if (method.equals(GET)){
                request =  builder
                        .url(strUrl)
                        .get()
                        .build();
            }else {
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
                RequestBody requestBody = RequestBody.create(mediaType, body);
                request =  builder
                        .url(strUrl)
                        .method(method,requestBody)
                        .build();
            }

            final Call call = okHttpClient.newCall(request);
            Response response = call.execute();
            retCode = response.code();
            strResponse.append( new String(response.body().bytes(), "utf-8") );
            Log.i(TAG, "sendRequest request:" + strUrl + " body:" + body + "response:"+strResponse.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "sendRequest MalformedURLException:" + strUrl);
            retCode = 400;
        } catch (IOException e) {
            Log.e(TAG, "sendRequest IOException:" + strUrl + " exception:" + e.getMessage());
            retCode = 500;
        } catch (Exception e) {
            Log.e(TAG, "sendRequest Exception:" + strUrl + " exception:" + e.getMessage());
            retCode = 400;
        }

        return retCode;
    }

    public static int sendDownloadRequest(final String strUrl,
                                          String method, Map<String, String> headers, String body,
                                          final String filePath, final Long callbackAddr)
    {
        if (isProcessing(filePath)){
            return 0;
        }
        addPath(filePath);
        try {
            return realSendDownloadRequest(strUrl, method, headers, body, filePath,
                    callbackAddr);
        } finally {
            removePath(filePath);
        }
    }

    private static boolean isFileExist(String path){
        final File fileReal = new File(path);
        if (fileReal.exists()) {
            Log.d(TAG, "sendDownloadRequest file exist:" + path);
            return true;
        }
        return false;
    }

    private static class MyCallback implements Callback {
        public void deleteFile(FileOutputStream fos, String path){
            try {
                fos.close();
                fos = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            final File file = new File(path);
            if (file.isFile() && file.exists()) {
                file.delete();
                Log.e(TAG,  "sendDownloadRequest:" + path + " deleted");
            }
        }
        public void changePercent(final Object fileLock, final StringBuffer percent, long newVal){
            synchronized (fileLock) {
                int sb_length = percent.length();
                percent.delete(0, sb_length);
                percent.append(newVal);
                fileLock.notifyAll();
            }
        }

        @Override
        public void onFailure(Call call, IOException e) {

        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {

        }
    }
    private static int realSendDownloadRequest(final String strUrl,
                                               String method, Map<String, String> headers, String body,
                                               final String filePath, final Long callbackAddr)
    {
        Log.d(TAG, "sendDownloadRequest begin:" + strUrl);
        if (isFileExist(filePath)){
            return 0;
        }
        final String tempPath = filePath + ".temp";
        if (isFileExist(tempPath)){
            return 0;
        }
        final File fileTemp = new File(tempPath);

        Request.Builder builder = addHeaders(headers);
        Request request = null;
        if (method.equals(GET)){
            request =  builder
                    .url(strUrl)
                    .get()
                    .build();
        }else {
            MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(mediaType, body);
            request =  builder
                    .url(strUrl)
                    .method(method,requestBody)
                    .build();
        }

        final Call call = okHttpClient.newCall(request);
        final Object fileLock = new Object();
        final StringBuffer percent = new StringBuffer("0");
        call.enqueue(new MyCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "sendDownloadRequest failure:" + strUrl + " error:" + e.toString());
                changePercent(fileLock,percent, 400);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                int httpStatusCode = response.code();
                if (httpStatusCode / 100 ==2){
                    try {
                        long total = response.body().contentLength();
                        long current = 0;
                        long prev_percent = 0;
                        is = response.body().byteStream();
                        fos = new FileOutputStream(fileTemp);
                        while ((len = is.read(buf)) != -1) {
                            //the real file is downloaded by other thread
                            if (isFileExist(filePath)){
                                Log.e(TAG,  "sendDownloadRequest:" + strUrl + " file downloaded by others");
                                deleteFile(fos, tempPath);
                                break;
                            }

                            current += len;
                            long current_percent = current*100/total;
                            fos.write(buf, 0, len);
                            if (current_percent - prev_percent > 0 || current_percent == 100){
                                changePercent(fileLock,percent,current_percent);
                                prev_percent = current_percent;
                            }
                        }
                        fos.flush();
                    } catch (IOException e) {
                        Log.e(TAG,  "sendDownloadRequest:" + strUrl + " exception:" + e.getMessage());
                        httpStatusCode = 400;
                        deleteFile(fos, tempPath);
                    } finally {
                        try {
                            if (is != null) {
                                is.close();
                            }
                            if (fos != null) {
                                fos.close();
                            }
                        } catch (IOException e) {
                            Log.e(TAG,  "sendDownloadRequest:" + strUrl + " exception2:" + e.getMessage());
                            httpStatusCode = 400;
                        } finally {
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            changePercent(fileLock,percent,httpStatusCode);
                        }
                    }
                }else{
                    Log.e(TAG, "sendDownloadRequest failure:" + strUrl + " http code:" + httpStatusCode);
                    changePercent(fileLock,percent,httpStatusCode);
                }
            }
        });
        synchronized (fileLock){
            try {
                while (Integer.parseInt(percent.toString()) <= 100){
                    fileLock.wait();
                    int code = Integer.parseInt(percent.toString());
                    if (code > 100){
                        if (code / 100 == 2){
                            code = 0;
                        }
                        if (code == 0){
                            final File file = new File(tempPath);
                            if (file.isFile() && file.exists()) {
                                file.renameTo(new File(filePath));
                                Log.d(TAG,  "sendDownloadRequest: rename filename");
                            }
                        }
                        Log.d(TAG, "sendDownloadRequest url:" + strUrl + " result code" + percent);
                        FileOperationProgressCallback(callbackAddr, code == 0 ? 101 : 102);
                        return code;
                    } else {
                        FileOperationProgressCallback(callbackAddr, Integer.valueOf(percent.toString()));
                        Log.v(TAG, "sendDownloadRequest progress:" + strUrl + " :" + percent);
                    }
                }
            } catch (InterruptedException e) {
                Log.e(TAG,  "sendDownloadRequest:" + strUrl + " exception3:" + e.getMessage());
            }
        }
        return ERROR_HTTP_FAILED;
    }

    public static int sendUploadRequest(final String strUrl,
                                        Map<String, Object> formdatas, Map<String, Object> headers,
                                        String body, String filePath, StringBuilder strResponse,
                                        Long callbackAddr) {
        if (isProcessing(filePath)){
            return 0;
        }
        addPath(filePath);
        try {
            return realSendUploadRequest(strUrl, formdatas, headers, body, filePath,
                    strResponse, callbackAddr);
        } finally {
            removePath(filePath);
        }
    }

    private static int realSendUploadRequest(final String strUrl,
                                             Map<String, Object> formdatas, Map<String, Object> headers,
                                             String body, String filePath, StringBuilder strResponse,
                                             Long callbackAddr) {
        final Object fileLock = new Object();
        final StringBuffer percent = new StringBuffer("0");
        try {
            OkHttpClient client = OkHttpClientHelper.addProgressRequestListener(new ProgressRequestListener() {
                private void changePercent(long newVal){
                    synchronized (fileLock) {
                        int sb_length = percent.length();
                        percent.delete(0, sb_length);
                        percent.append(newVal);
                        fileLock.notifyAll();
                    }
                }

                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    long current_percent = bytesWritten*100/contentLength;
                    long prev_percent = Long.parseLong(percent.toString());
                    if (current_percent - prev_percent > 0 || current_percent == 100) {
                        changePercent(current_percent);
                    }
                }
            });
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            for (String key : formdatas.keySet()) {
                Object object = formdatas.get(key);
                if (!(object instanceof File)) {
                    String value = object.toString();
                    builder.addFormDataPart(key, value);
                }
            }

            File file = new File(filePath);
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("file", URLEncoder.encode(file.getName(), "UTF-8"), requestFile);
            RequestBody requestBody = builder.build();
            final Request request = new Request.Builder().url(strUrl).post(requestBody).build();
            Log.d(TAG, "sendUploadRequest begin:" + strUrl);
            final Call call = client.newCall(request);
            call.enqueue(new MyCallback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "sendUploadRequest failure:" + strUrl + " error:" + e.toString());
                    changePercent(fileLock, percent, 400);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    int httpStatusCode = response.code();
                    changePercent(fileLock, percent, httpStatusCode);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "sendUploadRequest exception:" + strUrl + " excpt:" + e.toString());
            return ERROR_HTTP_FAILED;
        }
        synchronized (fileLock){
            try {
                while (Integer.parseInt(percent.toString()) <= 100){
                    fileLock.wait();
                    int code = Integer.parseInt(percent.toString());
                    if (code > 100){
                        if (code / 100 == 2){
                            code = 0;
                        }
                        Log.d(TAG, "sendUploadRequest url:" + strUrl + " result code" + percent);
                        FileOperationProgressCallback(callbackAddr, code == 0 ? 101 : 102);
                        return code;
                    } else {
                        FileOperationProgressCallback(callbackAddr, Integer.valueOf(percent.toString()));
                        Log.v(TAG, "sendUploadRequest progress:" + strUrl + " :" + percent);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ERROR_HTTP_FAILED;
    }
}
