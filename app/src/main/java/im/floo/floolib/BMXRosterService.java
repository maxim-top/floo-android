/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

/**
 *  好友Service
 **/
public class BMXRosterService {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected BMXRosterService(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BMXRosterService obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @Override
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        flooJNI.delete_BMXRosterService(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  /**
   *  好友邀请
   **/
  static public class Application {
    private transient long swigCPtr;
    protected transient boolean swigCMemOwn;
  
    protected Application(long cPtr, boolean cMemoryOwn) {
      swigCMemOwn = cMemoryOwn;
      swigCPtr = cPtr;
    }
  
    protected static long getCPtr(Application obj) {
      return (obj == null) ? 0 : obj.swigCPtr;
    }
  
    protected void finalize() {
      delete();
    }
  
    public synchronized void delete() {
      if (swigCPtr != 0) {
        if (swigCMemOwn) {
          swigCMemOwn = false;
          flooJNI.delete_BMXRosterService_Application(swigCPtr);
        }
        swigCPtr = 0;
      }
    }
  
    public Application() {
      this(flooJNI.new_BMXRosterService_Application(), true);
    }
  
    public void setMRosterId(long value) {
      flooJNI.BMXRosterService_Application_mRosterId_set(swigCPtr, this, value);
    }
  
    public long getMRosterId() {
      return flooJNI.BMXRosterService_Application_mRosterId_get(swigCPtr, this);
    }
  
    public void setMReason(String value) {
      flooJNI.BMXRosterService_Application_mReason_set(swigCPtr, this, value);
    }
  
    public String getMReason() {
      return flooJNI.BMXRosterService_Application_mReason_get(swigCPtr, this);
    }
  
    public void setMStatus(BMXRosterService.ApplicationStatus value) {
      flooJNI.BMXRosterService_Application_mStatus_set(swigCPtr, this, value.swigValue());
    }
  
    public BMXRosterService.ApplicationStatus getMStatus() {
      return BMXRosterService.ApplicationStatus.swigToEnum(flooJNI.BMXRosterService_Application_mStatus_get(swigCPtr, this));
    }
  
    public void setMExpire(long value) {
      flooJNI.BMXRosterService_Application_mExpire_set(swigCPtr, this, value);
    }
  
    public long getMExpire() {
      return flooJNI.BMXRosterService_Application_mExpire_get(swigCPtr, this);
    }
  
  }

  /**
   *  获取好友列表，如果forceRefresh == true，则强制从服务端拉取
   * @param list 好友id列表，传入空列表函数返回后从此处获取返回的好友id列表
   * @param forceRefresh 是否从服务器读取数据，true为强制从服务器获取，false情况下本地读取列表为空的情况下会自动从服务器读取
   * @return BMXErrorCode
   **/
  public BMXErrorCode get(ListOfLongLong list, boolean forceRefresh) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_get(swigCPtr, this, ListOfLongLong.getCPtr(list), list, forceRefresh));
  }

  /**
   *  搜索用户
   * @param rosterId 搜索的好友id
   * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
   * @param item 查询返回的用户的信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @return BMXErrorCode
   **/
  public BMXErrorCode fetchRosterById(long rosterId, boolean forceRefresh, BMXRosterItem item) {
    ListOfLongLong listOfLongLong = new ListOfLongLong();
    int iRet = flooJNI.BMXRosterService_fetchRosterById(swigCPtr, this, rosterId, forceRefresh, ListOfLongLong.getCPtr(listOfLongLong), listOfLongLong);
    item.swigCPtr = listOfLongLong.get(0);
    return BMXErrorCode.swigToEnum(iRet);
  }

  /**
   *  搜索用户
   * @param rosterId 搜索的好友id
   * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
   * @param item 查询返回的用户的信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @return BMXErrorCode
   **/
  public BMXErrorCode search(long rosterId, boolean forceRefresh, BMXRosterItem item) {
    ListOfLongLong listOfLongLong = new ListOfLongLong();
    int iRet = flooJNI.BMXRosterService_search__SWIG_0(swigCPtr, this, rosterId, forceRefresh, ListOfLongLong.getCPtr(listOfLongLong), listOfLongLong);
    item.swigCPtr = listOfLongLong.get(0);
    return BMXErrorCode.swigToEnum(iRet);
  }

  /**
   *  搜索用户
   * @param name 搜索的用户名
   * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
   * @param item 查询返回的用户的信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @return BMXErrorCode
   **/
  public BMXErrorCode fetchRosterByName(String name, boolean forceRefresh, BMXRosterItem item) {
    ListOfLongLong listOfLongLong = new ListOfLongLong();
    int iRet = flooJNI.BMXRosterService_fetchRosterByName(swigCPtr, this, name, forceRefresh, ListOfLongLong.getCPtr(listOfLongLong), listOfLongLong);
    item.swigCPtr = listOfLongLong.get(0);
    return BMXErrorCode.swigToEnum(iRet);
  }

  /**
   *  搜索用户
   * @param name 搜索的用户名
   * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
   * @param item 查询返回的用户的信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @return BMXErrorCode
   **/
  public BMXErrorCode search(String name, boolean forceRefresh, BMXRosterItem item) {
    ListOfLongLong listOfLongLong = new ListOfLongLong();
    int iRet = flooJNI.BMXRosterService_search__SWIG_1(swigCPtr, this, name, forceRefresh, ListOfLongLong.getCPtr(listOfLongLong), listOfLongLong);
    item.swigCPtr = listOfLongLong.get(0);
    return BMXErrorCode.swigToEnum(iRet);
  }


  /**
   *  批量搜索用户
   * @param rosterIdList 需要搜索的用户id列表
   * @param list 返回的好友信息列表，传入空列表函数返回后从此处获取返回的好友信息列表
   * @param forceRefresh 是否强制从服务器获取，为true则强制从服务器获取
   * @return BMXErrorCode
   **/
  public BMXErrorCode fetchRostersByIdList(ListOfLongLong rosterIdList, BMXRosterItemList list, boolean forceRefresh) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_fetchRostersByIdList(swigCPtr, this, ListOfLongLong.getCPtr(rosterIdList), rosterIdList, BMXRosterItemList.getCPtr(list), list, forceRefresh));
  }

  /**
   *  批量搜索用户
   * @param rosterIdList 需要搜索的用户id列表
   * @param list 返回的好友信息列表，传入空列表函数返回后从此处获取返回的好友信息列表
   * @param forceRefresh 是否强制从服务器获取，为true则强制从服务器获取
   * @return BMXErrorCode
   **/
  public BMXErrorCode search(ListOfLongLong rosterIdList, BMXRosterItemList list, boolean forceRefresh) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_search__SWIG_2(swigCPtr, this, ListOfLongLong.getCPtr(rosterIdList), rosterIdList, BMXRosterItemList.getCPtr(list), list, forceRefresh));
  }

  /**
   *  更新好友本地扩展信息
   * @param item 用户信息
   * @param extension 本地扩展信息
   * @return BMXErrorCode
   **/
  public BMXErrorCode setItemLocalExtension(BMXRosterItem item, String extension) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_setItemLocalExtension(swigCPtr, this, BMXRosterItem.getCPtr(item), item, extension));
  }

  /**
   *  更新好友服务器扩展信息
   * @param item 用户信息
   * @param extension 服务器扩展信息
   * @return BMXErrorCode
   **/
  public BMXErrorCode setItemExtension(BMXRosterItem item, String extension) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_setItemExtension(swigCPtr, this, BMXRosterItem.getCPtr(item), item, extension));
  }

  /**
   *  更新好友别名
   * @param item 用户信息
   * @param alias 好友别名
   * @return BMXErrorCode
   **/
  public BMXErrorCode setItemAlias(BMXRosterItem item, String alias) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_setItemAlias(swigCPtr, this, BMXRosterItem.getCPtr(item), item, alias));
  }

  /**
   *  设置是否拒收用户消息
   * @param item 用户信息
   * @param status 是否拒收用户消息，true拒收，false不拒收
   * @return BMXErrorCode
   **/
  public BMXErrorCode setItemMuteNotification(BMXRosterItem item, boolean status) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_setItemMuteNotification(swigCPtr, this, BMXRosterItem.getCPtr(item), item, status));
  }

  /**
   *  获取申请添加好友列表
   * @param result 返回的申请好友列表信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
   * @param pageSize 分页大小
   * @return BMXErrorCode
   **/
  public BMXErrorCode getApplicationList(ApplicationPage result, String cursor, int pageSize) {
    ListOfLongLong listOfLongLong = new ListOfLongLong();
    int iRet = flooJNI.BMXRosterService_getApplicationList__SWIG_0(swigCPtr, this, ListOfLongLong.getCPtr(listOfLongLong), listOfLongLong, cursor, pageSize);
    result.swigCPtr = listOfLongLong.get(0);
    return BMXErrorCode.swigToEnum(iRet);
  }

  public BMXErrorCode apply(long rosterId, String message, String authAnswer) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_apply__SWIG_0(swigCPtr, this, rosterId, message, authAnswer));
  }

  /**
   *  申请添加好友
   * @param rosterId 申请添加的用户id
   * @param message 好友申请信息
   * @return BMXErrorCode
   **/
  public BMXErrorCode apply(long rosterId, String message) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_apply__SWIG_1(swigCPtr, this, rosterId, message));
  }

  /**
   *  删除好友
   * @param rosterId 删除的好友id
   * @return BMXErrorCode
   **/
  public BMXErrorCode remove(long rosterId) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_remove(swigCPtr, this, rosterId));
  }

  /**
   *  接受加好友申请
   * @param rosterId 申请加为好友的用户id
   * @return BMXErrorCode
   **/
  public BMXErrorCode accept(long rosterId) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_accept(swigCPtr, this, rosterId));
  }

  /**
   *  拒绝加好友申请
   * @param rosterId 申请加为好友的用户id
   * @param reason 拒绝的原因
   * @return BMXErrorCode
   **/
  public BMXErrorCode decline(long rosterId, String reason) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_decline(swigCPtr, this, rosterId, reason));
  }

  /**
   *  加入黑名单
   * @param rosterId 加入黑名单的用户id
   * @return BMXErrorCode
   **/
  public BMXErrorCode block(long rosterId) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_block(swigCPtr, this, rosterId));
  }

  /**
   *  从黑名单移除
   * @param rosterId 从黑名单移除的用户id
   * @return BMXErrorCode
   **/
  public BMXErrorCode unblock(long rosterId) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_unblock(swigCPtr, this, rosterId));
  }

  /**
   *  获取黑名单，如果forceRefresh == true，则强制从服务端拉取
   * @param list 好友id列表，传入空列表函数返回后从此处获取返回的黑名单id列表
   * @param forceRefresh 是否从服务器读取数据，true为强制从服务器获取，false情况下本地读取列表为空的情况下会自动从服务器读取
   * @return BMXErrorCode
   **/
  public BMXErrorCode getBlockList(ListOfLongLong list, boolean forceRefresh) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_getBlockList(swigCPtr, this, ListOfLongLong.getCPtr(list), list, forceRefresh));
  }

  /**
   *  下载头像
   * @param item 用户信息
   * @param thumbnail 是否下载缩略图，ture为缩略图，false为原图
   * @param listener 下载回调函数
   * @return BMXErrorCode
   **/
  public BMXErrorCode downloadAvatar(BMXRosterItem item, boolean thumbnail, FileProgressListener listener) {
    return BMXErrorCode.swigToEnum(flooJNI.BMXRosterService_downloadAvatar(swigCPtr, this, BMXRosterItem.getCPtr(item), item, thumbnail, listener));
  }

  /**
   *  添加好友变化监听者
   * @param listener 好友变化监听者
   **/
  public void addRosterListener(BMXRosterServiceListener listener) {
    flooJNI.BMXRosterService_addRosterListener(swigCPtr, this, BMXRosterServiceListener.getCPtr(listener), listener);
  }

  /**
   *  移除好友变化监听者
   * @param listener 好友变化监听者
   **/
  public void removeRosterListener(BMXRosterServiceListener listener) {
    flooJNI.BMXRosterService_removeRosterListener(swigCPtr, this, BMXRosterServiceListener.getCPtr(listener), listener);
  }

  /**
   *  请求状态
   **/
  public enum ApplicationStatus {
    Pending,
    Accepted,
    Declined;

    public final int swigValue() {
      return swigValue;
    }

    public static ApplicationStatus swigToEnum(int swigValue) {
      ApplicationStatus[] swigValues = ApplicationStatus.class.getEnumConstants();
      if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
        return swigValues[swigValue];
      for (ApplicationStatus swigEnum : swigValues)
        if (swigEnum.swigValue == swigValue)
          return swigEnum;
      throw new IllegalArgumentException("No enum " + ApplicationStatus.class + " with value " + swigValue);
    }

    @SuppressWarnings("unused")
    private ApplicationStatus() {
      this.swigValue = SwigNext.next++;
    }

    @SuppressWarnings("unused")
    private ApplicationStatus(int swigValue) {
      this.swigValue = swigValue;
      SwigNext.next = swigValue+1;
    }

    @SuppressWarnings("unused")
    private ApplicationStatus(ApplicationStatus swigEnum) {
      this.swigValue = swigEnum.swigValue;
      SwigNext.next = this.swigValue+1;
    }

    private final int swigValue;

    private static class SwigNext {
      private static int next = 0;
    }
  }

}
