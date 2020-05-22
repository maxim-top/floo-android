package im.floo;

import im.floo.floolib.BMXErrorCode;

public interface BMXDataCallBack<T>{
    void onResult(BMXErrorCode code, T data);
}
