//-------------------------------------------------------------------------------------------------
// File    : bmx_client.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx client define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef __bmx_client_h__
#define __bmx_client_h__

#include "bmx_sdk_config.h"
#include "bmx_network_listener.h"
#include "bmx_error.h"
#include "bmx_user_profile.h"
#include "bmx_message.h"

namespace floo {

class BMXClient;
typedef std::shared_ptr<BMXClient> BMXClientPtr;

}

namespace floo {

class BMXRosterService;
class BMXGroupService;
class BMXChatService;
class BMXUserService;

/**
 * @brief 客户端
 **/
class EXPORT_API BMXClient : public BMXNetworkListener {
public:
  /**
   * @brief 创建BMXClient
   * @param config 客户端本地已经创建好的BMXSDKConfig SDK配置对象
   * @return BMXClientPtr
   **/
  static BMXClientPtr create(BMXSDKConfigPtr config);

  /**
   * @brief 析构函数
   **/
  virtual ~BMXClient() {}

  /**
   * @brief 获取SDK设置
   * @return BMXSDKConfigPtr
   **/
  virtual BMXSDKConfigPtr getSDKConfig() = 0;

  /**
   * @brief 获取用户Service
   * @return BMXUserService
   **/
  virtual BMXUserService& getUserService() = 0;

  /**
   * @brief 获取聊天Service
   * @return BMXChatService
   **/
  virtual BMXChatService& getChatService() = 0;

  /**
   * @brief 获取群组Service
   * @return BMXGroupService
   **/
  virtual BMXGroupService& getGroupService() = 0;

  /**
   * @brief 获取好友Service
   * @return BMXRosterService
   **/
  virtual BMXRosterService& getRosterService() = 0;

  /**
   * @brief 注册新用户，username和password是必填参数
   * @param username 用户名
   * @param password 用户密码
   * @param bmxUserProfilePtr 注册成功后从该函数处获取新注册用户的Profile信息，初始传入指向为空的shared_ptr对象即可。
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode signUpNewUser(const std::string& username, const std::string& password, BMXUserProfilePtr& bmxUserProfilePtr) = 0;
  
  /**
   * @brief 通过用户名登录
   * @param name 用户名
   * @param password 用户密码
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode signInByName(const std::string& name, const std::string& password) = 0;
  
  /**
   * @brief 通过用户ID登录
   * @param int64_t 用户id
   * @param password 用户密码
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode signInById(int64_t, const std::string& password) = 0;

  /**
   * @brief 通过用户名快速登录（要求之前成功登录过，登录速度较快）
   * @param name 用户名
   * @param password 用户密码(用于sdk在内部token到期时自动更新用户token)
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode fastSignInByName(const std::string& name, const std::string& password) = 0;

  /**
   * @brief 通过用户ID快速登录（要求之前成功登录过，登录速度较快）
   * @param uid 用户id
   * @param password 用户密码(用于sdk在内部token到期时自动更新用户token)
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode fastSignInById(int64_t uid, const std::string& password) = 0;

  /**
   * @brief 退出登录
   * @param uid 退出用户的uid（默认输入0则退出当前登陆用户）
   * @param ignoreUnbindDevice 用户退出时是否忽略解绑定设备操作。对应某些服务器不可访问的情况下忽略服务器解绑定设备操作直接强制退出时设置为true
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode signOut(int64_t uid = 0, bool ignoreUnbindDevice = false) = 0;

  /**
   * @brief 获取当前和服务器的连接状态
   * @return BMXConnectStatus
   **/
  virtual BMXConnectStatus connectStatus() = 0;

  /**
   * @brief 获取当前的登录状态
   * @return BMXSignInStatus
   **/
  virtual BMXSignInStatus signInStatus() = 0;

  /**
   * @brief 强制重新连接
   **/
  virtual void reconnect() = 0;

  /**
   * @brief 处理网络状态发送变化
   * @param type 变化后的网络类型
   * @param reconnect 网络是否需要重连
   **/
  virtual void onNetworkChanged(BMXNetworkType type, bool reconnect) = 0;

  /**
   * @brief 断开网络连接
   **/
  virtual void disconnect() = 0;

  /**
   * @brief 更改SDK的appId，本操作会同时更新BMXConfig中的appId。
   * @param appId 新变更的appId
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode changeAppId(const std::string& appId) = 0;

  /**
   * @brief 获取app的服务器网络配置，在初始化SDK之后登陆之前调用，可以提前获取服务器配置加快登陆速度。
   * @param isLocal 为true则使用本地缓存的dns配置，为false则从服务器获取最新的配置。
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode initializeServerConfig(bool isLocal) = 0;
  
  /**
   * @brief 发送消息，消息状态变化会通过listener通知，在发送群组消息且指定的群组为开启群组已读回执的情况下，
   * 该接口会自动获取群成员列表id并且填充到message config中去，无需客户端自己进行群组成员列表的填充操作。
   * @param msg 发送的消息
   **/
  virtual void sendMessage(BMXMessagePtr msg) = 0;

protected:
  BMXClient();

private:
  BMXClient(const BMXClient&);
  BMXClient& operator=(const BMXClient&);
};

}

#endif /* __bmx_client_h__ */
