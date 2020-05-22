package im.floo.floolib;

//回调到各个线程
public interface FileProgressListener {
    public int onProgressChange(String percent);
};
