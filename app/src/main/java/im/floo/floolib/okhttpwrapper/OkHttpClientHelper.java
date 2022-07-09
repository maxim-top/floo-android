package im.floo.floolib.okhttpwrapper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Cmad on 2016/4/28.
 */
public class OkHttpClientHelper {

    /**
     * 包装OkHttpClient，用于下载文件的回调
     * @param progressListener 进度回调接口
     * @return 包装后的OkHttpClient
     */
    public static OkHttpClient addProgressResponseListener(final ProgressResponseListener progressListener){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(1, TimeUnit.HOURS).writeTimeout(1, TimeUnit.HOURS);
        //增加拦截器
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //拦截
                Response originalResponse = chain.proceed(chain.request());

                //包装响应体并返回
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                        .build();
            }
        });
        return client.build();
    }


    /**
     * 包装OkHttpClient，用于上传文件的回调
     * @param progressListener 进度回调接口
     * @return 包装后的OkHttpClient
     */
    public static OkHttpClient addProgressRequestListener(final ProgressRequestListener progressListener, final List<String> ipList){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(1, TimeUnit.HOURS)
                .writeTimeout(1, TimeUnit.HOURS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .method(original.method(), new ProgressRequestBody(original.body(),progressListener))
                                .build();
                        return chain.proceed(request);
                    }
                });
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
}