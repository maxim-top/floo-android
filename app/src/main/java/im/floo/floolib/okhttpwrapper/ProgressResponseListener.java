package im.floo.floolib.okhttpwrapper;

public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead, long contentLength, boolean done);
}