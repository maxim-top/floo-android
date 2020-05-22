package im.floo.floolib;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import im.floo.floolib.okhttpwrapper.OkHttpClientHelper;
import im.floo.floolib.okhttpwrapper.ProgressRequestListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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

    public final static native void FileOperationProgressCallback(long callbackAddr, long percent);

    public static void ProgressCallback(long callbackAddr, long percent){
        FileOperationProgressCallback(callbackAddr, percent);
    }

    private static Request.Builder addHeaders(Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String, String> kv : headers.entrySet()) {
            builder.addHeader(kv.getKey(), kv.getValue());
        }
        return builder;
    }

    public static int sendRequest(String strUrl, String method, Map<String, String> headers, String body, final StringBuilder strResponse) {
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
            Log.i(TAG, "sendRequest response:"+strResponse.toString());
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

    public static int sendDownloadRequest(final String strUrl, String method, Map<String, String> headers, String body, final String filePath, final Long callbackAddr)
    {
        final File file = new File(filePath);
        if (file.exists()) {
            return 0;
        }

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
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "sendDownloadRequest failure:" + strUrl + " error:" + e.toString());
                synchronized (fileLock) {
                    int sb_length = percent.length();
                    percent.delete(0, sb_length);
                    percent.append(400);
                    fileLock.notifyAll();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                int httpStatusCode = response.code();
                try {
                    long total = response.body().contentLength();
                    long current = 0;
                    long prev_percent = 0;
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        current += len;
                        long current_percent = current*100/total;
                        fos.write(buf, 0, len);
                        if (current_percent - prev_percent > 5 || current_percent == 100){
                            synchronized (fileLock) {
                                int sb_length = percent.length();
                                percent.delete(0, sb_length);
                                percent.append(current_percent);
                                fileLock.notifyAll();
                                prev_percent = current_percent;
                            }
                        }
                    }
                    fos.flush();
                } catch (IOException e) {
                    Log.e(TAG,  "sendDownloadRequest:" + strUrl + " exception:" + e.getMessage());
                    httpStatusCode = 400;
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
                        synchronized (fileLock) {
                            int sb_length = percent.length();
                            percent.delete(0, sb_length);
                            percent.append(httpStatusCode);
                            fileLock.notifyAll();
                        }
                    }
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
                        Log.d(TAG, "sendDownloadRequest url:" + strUrl + " result code" + percent);
                        return code;
                    } else {
                        ProgressCallback(callbackAddr, Integer.valueOf(percent.toString()));
                        Log.v(TAG, "sendDownloadRequest progress:" + strUrl + " :" + percent);
                    }
                }
            } catch (InterruptedException e) {
                Log.e(TAG,  "sendDownloadRequest:" + strUrl + " exception3:" + e.getMessage());
            }
        }
        return ERROR_HTTP_FAILED;

    }


    public static int sendUploadRequest(final String strUrl, Map<String, Object> formdatas, Map<String, Object> headers, String body, String filePath, StringBuilder strResponse, Long callbackAddr) {
        final Object fileLock = new Object();
        final StringBuffer percent = new StringBuffer("0");

        try {
            OkHttpClient client = OkHttpClientHelper.addProgressRequestListener(new ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    synchronized (fileLock) {
                        int sb_length = percent.length();
                        percent.delete(0, sb_length);
                        percent.append(bytesWritten*100/contentLength);
                        fileLock.notifyAll();
                    }
                }
            });
            //补全请求地址
            MultipartBody.Builder builder = new MultipartBody.Builder();
            //设置类型
            builder.setType(MultipartBody.FORM);
            //追加参数
            for (String key : formdatas.keySet()) {
                Object object = formdatas.get(key);
                if (!(object instanceof File)) {
                    builder.addFormDataPart(key, object.toString());
                }
            }
            File file = new File(filePath);
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("file", URLEncoder.encode(file.getName(), "UTF-8"), requestFile);
            //创建RequestBody
            RequestBody requestBody = builder.build();
            //创建Request
            final Request request = new Request.Builder().url(strUrl).post(requestBody).build();
            Log.d(TAG, "sendUploadRequest begin:" + strUrl);
            final Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "sendUploadRequest failure:" + strUrl + " error:" + e.toString());
                    synchronized (fileLock) {
                        int sb_length = percent.length();
                        percent.delete(0, sb_length);
                        percent.append(400);
                        fileLock.notifyAll();
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    int httpStatusCode = response.code();
                    synchronized (fileLock) {
                        int sb_length = percent.length();
                        percent.delete(0, sb_length);
                        percent.append(httpStatusCode);
                        fileLock.notifyAll();
                    }
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
                        return code;
                    } else {
                        ProgressCallback(callbackAddr,Integer.valueOf(percent.toString()));
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
