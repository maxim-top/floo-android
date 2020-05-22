//
// Created by lhr on 2019/6/30.
//
#include <functional>
#include "jni.h"
#include "xlog.h"
typedef std::function<void(int percent)> ProgressCallback;

extern "C" JNIEXPORT void JNICALL Java_im_floo_floolib_BMXHttpClient_FileOperationProgressCallback(JNIEnv *env, jobject thiz,jlong callbackAddr, jlong percent) {
  ProgressCallback *callback = (ProgressCallback*)callbackAddr;
  (*callback)(percent);
}
