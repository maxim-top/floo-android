package im.floo.floolib;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import im.floo.floolib.okhttpwrapper.OkHttpClientHelper;
import im.floo.floolib.okhttpwrapper.ProgressRequestListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dns;
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
    private static HashSet<String> sPathSet = new HashSet<>();
    private static ConcurrentHashMap<Long, Call> sCallMap = new ConcurrentHashMap<>();

    private static OkHttpClient getOkHttpClient(final List<String> ipList){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS);
        if (ipList.isEmpty()){
            return builder.build();
        } else {
            return builder.dns(new Dns() {
                @Override
                public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                    List<InetAddress> addrList = new ArrayList<>();
                    try {
                        for(String ip: ipList){
                            String host = ip.replaceAll(":.*", "");
                            if (host.equals(hostname)){
                                ip = ip.replaceAll(".*:", "");
                                addrList.add(InetAddress.getByName(ip));
                            }
                        }
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    if (addrList.isEmpty()){
                        addrList.add(InetAddress.getByName(hostname));
                    }
                    return addrList;
                }
            }).build();
        }
    }

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

    private static void addCall (Long msgId, Call call){
        synchronized (sCallMap){
            sCallMap.putIfAbsent(msgId, call);
        }
    }

    private static void removeCall (final Long msgId){
        synchronized (sCallMap){
            sCallMap.remove(msgId);
        }
    }

    public static void cancelAttachmentRequest(final Long msgId) {
        Call call = null;
        synchronized (sCallMap) {
            call = sCallMap.get(msgId);
        }
        if (call != null){
            call.cancel();
            removeCall(msgId);
        }else if (msgId == -1){
            synchronized (sCallMap) {
                for (Call value : sCallMap.values()) {
                    if (value != null){
                        value.cancel();
                        sCallMap.clear();
                    }
                }
            }
        }
    }

    public static int transferingNum() {
        synchronized (sCallMap) {
            return sCallMap.size();
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
                                  String body, final StringBuilder strResponse, List<String> ipList) {
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

            OkHttpClient client = getOkHttpClient(ipList);

            final Call call = client.newCall(request);
            Response response = call.execute();
            retCode = response.code();
            strResponse.append( new String(response.body().bytes(), "utf-8") );
            Log.i(TAG, "sendRequest request:" + strUrl + " body:" + body + "response:"+strResponse.toString());
        } catch (MalformedURLException e) {
            Log.e(TAG, "sendRequest MalformedURLException:" + strUrl);
            try {
                strResponse.append( new String(e.getMessage().getBytes(), "utf-8") );
            } catch (UnsupportedEncodingException ex) {
            }
            retCode = 400;
        } catch (IOException e) {
            Log.e(TAG, "sendRequest IOException:" + strUrl + " exception:" + e.getMessage());
            try {
                strResponse.append( new String(e.getMessage().getBytes(), "utf-8") );
            } catch (UnsupportedEncodingException ex) {
            }
            retCode = 700;
        } catch (Exception e) {
            Log.e(TAG, "sendRequest Exception:" + strUrl + " exception:" + e.getMessage());
            try {
                strResponse.append( new String(e.getMessage().getBytes(), "utf-8") );
            } catch (UnsupportedEncodingException ex) {
            }
            retCode = 400;
        }

        return retCode;
    }

    public static int sendDownloadRequest(final String strUrl,
                                          String method, Map<String, String> headers, String body,
                                          final String filePath, final Long callbackAddr,
                                          final Long msgId, List<String> ipList)
    {
        if (isProcessing(filePath)){
            return 0;
        }
        addPath(filePath);
        try {
            return realSendDownloadRequest(strUrl, method, headers, body, filePath,
                    callbackAddr, msgId, ipList);
        } finally {
            removePath(filePath);
            removeCall(msgId);
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
                if(fos != null){
                    fos.close();
                }
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
                                               final String filePath, final Long callbackAddr,
                                               final Long msgId, List<String> ipList)
    {
        Log.d(TAG, "sendDownloadRequest begin:" + strUrl);
        if (isFileExist(filePath)){
            FileOperationProgressCallback(callbackAddr, 101);
            return 0;
        }
        final String tempPath = filePath + ".temp";
        final File fileTemp = new File(tempPath);
        long downloaded = 0;
        if (isFileExist(tempPath)){
            downloaded = fileTemp.length();
        }
        Log.d(TAG, "sendDownloadRequest downloaded:" + downloaded);

        Request.Builder builder = addHeaders(headers);
        builder.addHeader("RANGE","bytes="+downloaded+"-");
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

        OkHttpClient client = getOkHttpClient(ipList);

        final Call call = client.newCall(request);
        addCall(msgId, call);
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
                        long downloaded = fileTemp.length();
                        long total = response.body().contentLength();
                        long current = 0;
                        long prev_percent = 0;
                        is = response.body().byteStream();
                        while ((len = is.read(buf)) != -1) {
                            //the real file is downloaded by other thread
                            if (isFileExist(filePath)){
                                Log.e(TAG,  "sendDownloadRequest:" + strUrl + " file downloaded by others");
                                deleteFile(fos, tempPath);
                                break;
                            }
                            fos = new FileOutputStream(fileTemp, true);
                            fos.write(buf, 0, len);
                            fos.flush();
                            fos.close();
                            fos = null;

                            current += len;
                            long current_percent = (current + downloaded) * 100 / (total + downloaded);
                            if (current_percent - prev_percent > 0 || current_percent == 100){
                                changePercent(fileLock,percent,current_percent);
                                prev_percent = current_percent;
                                Log.d(TAG, "sendDownloadRequest cur percent:" + current_percent+" total:"+total+" len:"+len);
                            }
                        }
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

    public static int sendUploadRequest(final String strUrl, final String strUploadMethod,
                                        Map<String, Object> formdatas, Map<String, Object> headers,
                                        String body, String filePath, StringBuilder strResponse,
                                        Long callbackAddr, final Long msgId, List<String> ipList) {
        if (isProcessing(filePath)){
            return 0;
        }
        addPath(filePath);
        try {
            return realSendUploadRequest(strUrl, strUploadMethod, formdatas, headers, body, filePath,
                    strResponse, callbackAddr, msgId, ipList);
        } finally {
            removePath(filePath);
            removeCall(msgId);
        }
    }

    private static int realSendUploadRequest(final String strUrl, final String strUploadMethod,
                                             Map<String, Object> formdatas, Map<String, Object> headers,
                                             String body, String filePath, StringBuilder strResponse,
                                             Long callbackAddr, final Long msgId, final List<String> ipList) {
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
            }, ipList);
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            for (String key : formdatas.keySet()) {
                Object object = formdatas.get(key);
                if (!(object instanceof File)) {
                    String value = object.toString();
                    builder.addFormDataPart(key, value);
                }
            }

            RequestBody requestBody;
            File file = new File(filePath);
            String uploadMethod = strUploadMethod;
            if (strUploadMethod.isEmpty()){
                uploadMethod = "POST";
            }
            if (strUploadMethod.equals("PUT")){
                requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            }else{
                RequestBody requestFile =
                        RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("file", URLEncoder.encode(file.getName(), "UTF-8"), requestFile);
                requestBody = builder.build();
            }
            final Request request = new Request.Builder().url(strUrl).method(uploadMethod, requestBody).build();
            Log.d(TAG, "sendUploadRequest begin:" + strUrl);
            final Call call = client.newCall(request);
            addCall(msgId, call);
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
        } catch (IllegalStateException e){
            Log.e(TAG, "sendUploadRequest exception:" + strUrl + " excpt:" + e.toString());
            return ERROR_HTTP_FAILED;
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
