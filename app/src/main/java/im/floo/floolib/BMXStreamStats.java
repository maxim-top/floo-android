/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public class BMXStreamStats {
  private transient long swigCPtr;
  private transient boolean swigCMemOwn;

  protected BMXStreamStats(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BMXStreamStats obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        flooJNI.delete_BMXStreamStats(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setMUserId(int value) {
    flooJNI.BMXStreamStats_mUserId_set(swigCPtr, this, value);
  }

  public int getMUserId() {
    return flooJNI.BMXStreamStats_mUserId_get(swigCPtr, this);
  }

  public void setMStreamId(String value) {
    flooJNI.BMXStreamStats_mStreamId_set(swigCPtr, this, value);
  }

  public String getMStreamId() {
    return flooJNI.BMXStreamStats_mStreamId_get(swigCPtr, this);
  }

  public void setMMediaType(BMXVideoMediaType value) {
    flooJNI.BMXStreamStats_mMediaType_set(swigCPtr, this, value.swigValue());
  }

  public BMXVideoMediaType getMMediaType() {
    return BMXVideoMediaType.swigToEnum(flooJNI.BMXStreamStats_mMediaType_get(swigCPtr, this));
  }

  public void setMTrackType(BMXTrackType value) {
    flooJNI.BMXStreamStats_mTrackType_set(swigCPtr, this, value.swigValue());
  }

  public BMXTrackType getMTrackType() {
    return BMXTrackType.swigToEnum(flooJNI.BMXStreamStats_mTrackType_get(swigCPtr, this));
  }

  public void setMAudioBitrate(int value) {
    flooJNI.BMXStreamStats_mAudioBitrate_set(swigCPtr, this, value);
  }

  public int getMAudioBitrate() {
    return flooJNI.BMXStreamStats_mAudioBitrate_get(swigCPtr, this);
  }

  public void setMVideoBitrate(int value) {
    flooJNI.BMXStreamStats_mVideoBitrate_set(swigCPtr, this, value);
  }

  public int getMVideoBitrate() {
    return flooJNI.BMXStreamStats_mVideoBitrate_get(swigCPtr, this);
  }

  public void setMWidth(int value) {
    flooJNI.BMXStreamStats_mWidth_set(swigCPtr, this, value);
  }

  public int getMWidth() {
    return flooJNI.BMXStreamStats_mWidth_get(swigCPtr, this);
  }

  public void setMHeight(int value) {
    flooJNI.BMXStreamStats_mHeight_set(swigCPtr, this, value);
  }

  public int getMHeight() {
    return flooJNI.BMXStreamStats_mHeight_get(swigCPtr, this);
  }

  public void setMFrameRate(int value) {
    flooJNI.BMXStreamStats_mFrameRate_set(swigCPtr, this, value);
  }

  public int getMFrameRate() {
    return flooJNI.BMXStreamStats_mFrameRate_get(swigCPtr, this);
  }

  public void setMPacketLostRate(float value) {
    flooJNI.BMXStreamStats_mPacketLostRate_set(swigCPtr, this, value);
  }

  public float getMPacketLostRate() {
    return flooJNI.BMXStreamStats_mPacketLostRate_get(swigCPtr, this);
  }

  public BMXStreamStats() {
    this(flooJNI.new_BMXStreamStats(), true);
  }

}