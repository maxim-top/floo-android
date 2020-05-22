//-------------------------------------------------------------------------------------------------
// File    : bmx_roster_item.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx roster item define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_roster_item_h
#define bmx_roster_item_h

#include <vector>
#include "bmx_base_object.h"

namespace floo {

/**
 * @brief 联系人
 **/
class EXPORT_API BMXRosterItem : public BMXBaseObject {
public:

  /**
   * @brief 好友关系
   **/
  enum class RosterRelation {
    /// 好友
    Friend,
    /// 被删除
    Deleted,
    /// 陌生人
    Stranger,
    /// 被加入黑名单
    Blocked,
  };

  /**
   * @brief roster 被申请加好友时的验证方式
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
   * @brief 析构函数
   **/
  virtual ~BMXRosterItem() {}

  /**
   * @brief 好友Id
   * @return int64_t
   **/
  virtual int64_t rosterId() = 0;

  /**
   * @brief 好友名
   * @return std::string
   **/
  virtual const std::string& username() = 0;

  /**
   * @brief 好友昵称
   * @return std::string
   **/
  virtual const std::string& nickname() = 0;

  /**
   * @brief 好友头像服务器地址
   * @return std::string
   **/
  virtual std::string avatarUrl() = 0;

  /**
   * @brief 好友头像本地存储路径
   * @return std::string
   **/
  virtual std::string avatarPath() = 0;

  /**
   * @brief 好友头像缩略图本地存储路径
   * @return std::string
   **/
  virtual std::string avatarThumbnailPath() = 0;

  /**
   * @brief 扩展信息，用户设置的好友可以看到的信息，比如地址，个性签名等
   * @return JSON(std::string)
   **/
  virtual const JSON& publicInfo() = 0;

  /**
   * @brief 用户对好友添加的备注等信息
   * @return JSON(std::string)
   **/
  virtual const JSON& alias() = 0;

  /**
   * @brief 用户的服务器扩展信息
   * @return JSON(std::string)
   **/
  virtual const JSON& ext() = 0;

  /**
   * @brief 用户的本地扩展信息
   * @return JSON(std::string)
   **/
  virtual const JSON& localExt() = 0;

  /**
   * @brief 联系人关系
   * @return RosterRelation
   **/
  virtual RosterRelation relation() = 0;

  /**
   * @brief 是否提醒用户消息
   * @return bool
   **/
  virtual bool isMuteNotification() = 0;
  
  /**
   * @brief roster的好友添加验证方式。
   * @return AddFriendAuthMode
   **/
  virtual AddFriendAuthMode addFriendAuthMode() = 0;

  /**
   * @brief roster的好友验证问题。
   * @return std::string
   **/
  virtual const std::string& authQuestion() = 0;

protected:
  BMXRosterItem() {}

private:
  BMXRosterItem(const BMXRosterItem&);
  BMXRosterItem& operator=(const BMXRosterItem&);
};

typedef std::shared_ptr<BMXRosterItem> BMXRosterItemPtr;
typedef std::vector<BMXRosterItemPtr> BMXRosterList;
}

#endif /* bmx_roster_item_h */
