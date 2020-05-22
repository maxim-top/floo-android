//-------------------------------------------------------------------------------------------------
// File    : bmx_user_profile.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx user profile define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_user_profile_h
#define bmx_user_profile_h

#include "bmx_base_object.h"

namespace floo {

/**
 * @brief 用户Profile
 **/
class EXPORT_API BMXUserProfile: public BMXBaseObject {

public:
  /**
   * @brief 对方申请加好友时的验证方式
   **/
  enum class AddFriendAuthMode {
    /// 无需验证，任何人可以加为好友
    Open,
    /// 需要同意方可加为好友
    NeedApproval,
    /// 需要回答问题正确方可加为好友
    AnswerQuestion,
    /// 拒绝所有加好友申请
    RejectAll
  };

  /**
   * @brief 添加好友时的校验问题
   **/
  struct AuthQuestion {
    AuthQuestion() {}

    /// 好友验证问题
    std::string mQuestion;

    /// 好友验证问题答案
    std::string mAnswer;
  };

  /**
   * @brief 用户消息设置
   **/
  struct MessageSetting {
    MessageSetting() {
      mPushEnabled = true;
      mPushDetail = true;
      mNotificationSound = true;
      mNotificationVibrate = true;
      mAutoDownloadAttachment = true;
    }

    // 推送设定
    /// 当APP未打开时是否允许推送
    bool mPushEnabled;

    /// 是否推送消息详情
    bool mPushDetail;

    /// 对方收到推送消息时显示的名称
    std::string mPushNickname;

    // 在线消息设定
    /// 收到消息时是否通过声音提醒
    bool mNotificationSound;

    /// 收到消息时是否通过震动提醒
    bool mNotificationVibrate;

    /// 收到消息时是否自动下载缩略图或者语音
    bool mAutoDownloadAttachment;
  };

  /**
   * @brief 用户类型
   **/
  enum class UserCategory {
    /// 普通用户
    Normal,
    /// 高级用户
    Advanced
  };

  /**
   * @brief 析构函数
   **/
  virtual ~BMXUserProfile() {}

  /**
   * @brief 用户ID（唯一）
   * @return int64_t
   **/
  virtual int64_t userId() = 0;

  /**
   * @brief 用户策略
   * @return UserCategory
   **/
  virtual UserCategory category() = 0;

  /**
   * @brief 用户名（唯一）
   * @return std::string
   **/
  virtual const std::string& username() = 0;

  /**
   * @brief 用户昵称
   * @return std::string
   **/
  virtual const std::string& nickname() = 0;

  /**
   * @brief 用户头像
   * @return std::string
   **/
  virtual std::string avatarUrl() = 0;

  /**
   * @brief 用户头像本地存储路径
   * @return std::string
   **/
  virtual std::string avatarPath() = 0;

  /**
   * @brief 用户头像缩略图本地存储路径
   * @return std::string
   **/
  virtual std::string avatarThumbnailPath() = 0;

  /**
   * @brief 用户手机
   * @return std::string
   **/
  virtual const std::string& mobilePhone() = 0;

  /**
   * @brief 用户邮箱
   * @return std::string
   **/
  virtual const std::string& email() = 0;

  /**
   * @brief 用户公开扩展信息，好友可见
   * @return JSON(std::string)
   **/
  virtual const JSON& publicInfo() = 0;

  /**
   * @brief 用户私有扩展信息，好友不可见
   * @return JSON(std::string)
   **/
  virtual const JSON& privateInfo() = 0;

  /**
   * @brief 加好友校验方式
   * @return AddFriendAuthMode
   **/
  virtual AddFriendAuthMode addFriendAuthMode() = 0;

  /**
   * @brief 添加好友时的验证问题
   * @return AuthQuestion
   **/
  virtual const AuthQuestion& authQuestion() = 0;

  /**
   * @brief 用户消息设定
   * @return MessageSetting
   **/
  virtual const MessageSetting& messageSetting() = 0;

  /**
   * @brief 收到群组邀请进群时是否自动同意进群
   * @return bool
   **/
  virtual bool isAutoAcceptGroupInvite() = 0;

protected:
  BMXUserProfile() {}

private:
  BMXUserProfile(const BMXUserProfile&);
  BMXUserProfile& operator=(const BMXUserProfile&);
};

typedef std::shared_ptr<BMXUserProfile> BMXUserProfilePtr;
}

#endif /* bmx_user_profile_h */
