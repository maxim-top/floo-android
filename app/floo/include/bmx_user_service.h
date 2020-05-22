//-------------------------------------------------------------------------------------------------
// File    : bmx_user_service.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx user service define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_user_service_h
#define bmx_user_service_h

#include <functional>
#include "bmx_user_profile.h"
#include "bmx_device.h"

namespace floo {

class BMXUserServiceListener;

/**
 * @brief 用户Service
 **/
class EXPORT_API BMXUserService {
public:
  virtual ~BMXUserService() {}

  typedef std::function<void(int percent)> Callback;

  /**
   * @brief 绑定设备推送token
   * @param token 设备token
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode bindDevice(const std::string& token) = 0;
  /**
  * @brief 获取设备列表
  * @param deviceList 设备列表，传入空列表函数返回后从此处获取返回的设备列表
  * @return BMXErrorCode
  */
  virtual BMXErrorCode getDeviceList(BMXDeviceList& deviceList) = 0;

  /**
   * @brief 删除设备
   * @param device_sn 设备序列号
   * @return BMXErrorCode
   */
  virtual BMXErrorCode deleteDevice(int32_t device_sn) = 0;

  /**
   * @brief 获取用户详情，如果forceRefresh == true，则强制从服务端拉取
   * @param profile 用户profile信息，初始传入指向为空的shared_ptr对象，函数返回后从此处获取用户profile信息。
   * @param forceRefresh 是否强制从服务器拉取，本地获取失败的情况下会自动从服务器拉取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getProfile(BMXUserProfilePtr& profile, bool forceRefresh) = 0;
  
  /**
   * @brief 设置昵称
   * @param nickname 用户昵称
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setNickname(const std::string& nickname) = 0;

  /**
   * @brief 上传头像
   * @param avatarPath 上传头像的本地地址
   * @param callback 上传回调函数
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode uploadAvatar(const std::string& avatarPath, Callback callback) = 0;

  /**
   * @brief 下载头像，默认下载缩略图
   * @param profile 用户profile
   * @param thumbnail 是否下载缩略图，true下载缩略图，false下载原图
   * @param callback 下载回调函数
   * @return BMXErrorCode
   **/
    virtual BMXErrorCode downloadAvatar(BMXUserProfilePtr profile, bool thumbnail = true, Callback callback = [](int){}) = 0;

  /**
   * @brief 设置公开扩展信息
   * @param publicInfo 公开扩展信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setPublicInfo(const std::string& publicInfo) = 0;

  /**
   * @brief 设置私有扩展信息
   * @param privateInfo 私有扩展信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setPrivateInfo(const std::string& privateInfo) = 0;

  /**
   * @brief 设置加好友验证方式
   * @param mode 加好友验证方式
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setAddFriendAuthMode(BMXUserProfile::AddFriendAuthMode mode) = 0;
  
  /**
   * @brief 设置加好友验证问题
   * @param authQuestion 加好友验证问题
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setAuthQuestion(const BMXUserProfile::AuthQuestion& authQuestion) = 0;

  /**
   * @brief 设置是否允许推送
   * @param enable 是否允许推送，true推送，false不推送
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setEnablePush(bool enable) = 0;

  /**
   * @brief 设置是否推送详情
   * @param enable 是否推送详情，true推送，false不推送
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setEnablePushDetaile(bool enable) = 0;

  /**
   * @brief 设置推送昵称
   * @param nickname 推送昵称
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setPushNickname(const std::string& nickname) = 0;

  /**
   * @brief 设置收到新消息是否声音提醒
   * @param enable 收到新消息是否声音提醒，true提醒，false不提醒
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setNotificationSound(bool enable) = 0;

  /**
   * @brief 设置收到新消息是否震动
   * @param enable 收到新消息是否震动，true震动，false不震动
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setNotificationVibrate(bool enable) = 0;

  /**
   * @brief 设置是否自动缩略图和语音附件
   * @param enable 是否自动缩略图和语音附件，true自动下载，false不会自动下载
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setAutoDownloadAttachment(bool enable) = 0;

  /**
   * @brief 设置是否自动同意入群邀请
   * @param enable 是否自动同意入群邀请，true同意，false不同意
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setAutoAcceptGroupInvite(bool enable) = 0;

  /**
   * @brief 添加用户状态监听者
   * @param listener 用户状态监听者
   **/
  virtual void addUserListener(BMXUserServiceListener* listener) = 0;

  /**
   * @brief 移除用户状态监听者
   * @param listener 用户状态监听者
   **/
  virtual void removeUserListener(BMXUserServiceListener* listener) = 0;

protected:
  BMXUserService() {}
};

}

#endif /* bmx_user_service_h */
