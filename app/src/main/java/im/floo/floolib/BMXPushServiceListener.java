/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public class BMXPushServiceListener {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected BMXPushServiceListener(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BMXPushServiceListener obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        flooJNI.delete_BMXPushServiceListener(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  protected void swigDirectorDisconnect() {
    swigCMemOwn = false;
    delete();
  }

  public void swigReleaseOwnership() {
    swigCMemOwn = false;
    flooJNI.BMXPushServiceListener_change_ownership(this, swigCPtr, false);
  }

  public void swigTakeOwnership() {
    swigCMemOwn = true;
    flooJNI.BMXPushServiceListener_change_ownership(this, swigCPtr, true);
  }

  /**
   * Push初始化完成通知。
   * @param bmxToken 当前push使用bmxToken
   **/
  public void onPushStart(String bmxToken) {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onPushStart(swigCPtr, this, bmxToken); else flooJNI.BMXPushServiceListener_onPushStartSwigExplicitBMXPushServiceListener(swigCPtr, this, bmxToken);
  }

  /**
   * Push功能停止通知。
   **/
  public void onPushStop() {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onPushStop(swigCPtr, this); else flooJNI.BMXPushServiceListener_onPushStopSwigExplicitBMXPushServiceListener(swigCPtr, this);
  }

  /**
   * Push初始化完成后获取推送证书。
   * @param cert 从服务器获取的推送证书
   **/
  public void onCertRetrieved(String cert) {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onCertRetrieved(swigCPtr, this, cert); else flooJNI.BMXPushServiceListener_onCertRetrievedSwigExplicitBMXPushServiceListener(swigCPtr, this, cert);
  }

  /**
   * 设置用户推送成功回调。
   * @param operationId 操作id
   **/
  public void onSetTags(String operationId) {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onSetTags(swigCPtr, this, operationId); else flooJNI.BMXPushServiceListener_onSetTagsSwigExplicitBMXPushServiceListener(swigCPtr, this, operationId);
  }

  /**
   * 获取用户推送成功回调。
   * @param operationId 操作id
   **/
  public void onGetTags(String operationId) {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onGetTags(swigCPtr, this, operationId); else flooJNI.BMXPushServiceListener_onGetTagsSwigExplicitBMXPushServiceListener(swigCPtr, this, operationId);
  }

  /**
   * 删除用户推送成功回调。
   * @param operationId 操作id
   **/
  public void onDeleteTags(String operationId) {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onDeleteTags(swigCPtr, this, operationId); else flooJNI.BMXPushServiceListener_onDeleteTagsSwigExplicitBMXPushServiceListener(swigCPtr, this, operationId);
  }

  /**
   * 清空用户推送成功回调。
   * @param operationId 操作id
   **/
  public void onClearTags(String operationId) {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onClearTags(swigCPtr, this, operationId); else flooJNI.BMXPushServiceListener_onClearTagsSwigExplicitBMXPushServiceListener(swigCPtr, this, operationId);
  }

  /**
   * 接收到新的Push通知。
   * @param list Push通知列表
   **/
  public void onReceivePush(BMXMessageList list) {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onReceivePush(swigCPtr, this, BMXMessageList.getCPtr(list), list); else flooJNI.BMXPushServiceListener_onReceivePushSwigExplicitBMXPushServiceListener(swigCPtr, this, BMXMessageList.getCPtr(list), list);
  }

  /**
   * 发送Push上行消息状态变化通知。
   * @param msg 发生状态变化的上行消息
   * @param error 状态错误码
   **/
  public void onStatusChanged(BMXMessage msg, BMXErrorCode error) {
    if (getClass() == BMXPushServiceListener.class) flooJNI.BMXPushServiceListener_onStatusChanged(swigCPtr, this, BMXMessage.getCPtr(msg), msg, error.swigValue()); else flooJNI.BMXPushServiceListener_onStatusChangedSwigExplicitBMXPushServiceListener(swigCPtr, this, BMXMessage.getCPtr(msg), msg, error.swigValue());
  }

  public BMXPushServiceListener() {
    this(flooJNI.new_BMXPushServiceListener(), true);
    flooJNI.BMXPushServiceListener_director_connect(this, swigCPtr, swigCMemOwn, true);
  }

  public void registerPushService(BMXPushService service) {
    flooJNI.BMXPushServiceListener_registerPushService(swigCPtr, this, BMXPushService.getCPtr(service), service);
  }

}
