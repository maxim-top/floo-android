/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public class BMXJanusStreamInfo {
  private transient long swigCPtr;
  private transient boolean swigCMemOwn;

  protected BMXJanusStreamInfo(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BMXJanusStreamInfo obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        flooJNI.delete_BMXJanusStreamInfo(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public BMXJanusStreamInfo() {
    this(flooJNI.new_BMXJanusStreamInfo(), true);
  }

  public void setMFeedId(long value) {
    flooJNI.BMXJanusStreamInfo_mFeedId_set(swigCPtr, this, value);
  }

  public long getMFeedId() {
    return flooJNI.BMXJanusStreamInfo_mFeedId_get(swigCPtr, this);
  }

  public void setMFeedDisplay(String value) {
    flooJNI.BMXJanusStreamInfo_mFeedDisplay_set(swigCPtr, this, value);
  }

  public String getMFeedDisplay() {
    return flooJNI.BMXJanusStreamInfo_mFeedDisplay_get(swigCPtr, this);
  }

  public void setMFeedMid(String value) {
    flooJNI.BMXJanusStreamInfo_mFeedMid_set(swigCPtr, this, value);
  }

  public String getMFeedMid() {
    return flooJNI.BMXJanusStreamInfo_mFeedMid_get(swigCPtr, this);
  }

  public void setMType(String value) {
    flooJNI.BMXJanusStreamInfo_mType_set(swigCPtr, this, value);
  }

  public String getMType() {
    return flooJNI.BMXJanusStreamInfo_mType_get(swigCPtr, this);
  }

  public void setMMid(String value) {
    flooJNI.BMXJanusStreamInfo_mMid_set(swigCPtr, this, value);
  }

  public String getMMid() {
    return flooJNI.BMXJanusStreamInfo_mMid_get(swigCPtr, this);
  }

  public void setMCodec(String value) {
    flooJNI.BMXJanusStreamInfo_mCodec_set(swigCPtr, this, value);
  }

  public String getMCodec() {
    return flooJNI.BMXJanusStreamInfo_mCodec_get(swigCPtr, this);
  }

  public void setMMindex(int value) {
    flooJNI.BMXJanusStreamInfo_mMindex_set(swigCPtr, this, value);
  }

  public int getMMindex() {
    return flooJNI.BMXJanusStreamInfo_mMindex_get(swigCPtr, this);
  }

  public void setMTalking(boolean value) {
    flooJNI.BMXJanusStreamInfo_mTalking_set(swigCPtr, this, value);
  }

  public boolean getMTalking() {
    return flooJNI.BMXJanusStreamInfo_mTalking_get(swigCPtr, this);
  }

  public void setMActive(boolean value) {
    flooJNI.BMXJanusStreamInfo_mActive_set(swigCPtr, this, value);
  }

  public boolean getMActive() {
    return flooJNI.BMXJanusStreamInfo_mActive_get(swigCPtr, this);
  }

  public void setMReady(boolean value) {
    flooJNI.BMXJanusStreamInfo_mReady_set(swigCPtr, this, value);
  }

  public boolean getMReady() {
    return flooJNI.BMXJanusStreamInfo_mReady_get(swigCPtr, this);
  }

  public void setMSend(boolean value) {
    flooJNI.BMXJanusStreamInfo_mSend_set(swigCPtr, this, value);
  }

  public boolean getMSend() {
    return flooJNI.BMXJanusStreamInfo_mSend_get(swigCPtr, this);
  }

}
