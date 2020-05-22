//-------------------------------------------------------------------------------------------------
// File    : bmx_group.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx group define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_group_h__
#define bmx_group_h__

#include <vector>
#include "bmx_base_object.h"

namespace floo {

/**
 * @brief 群组
 **/
class EXPORT_API BMXGroup: public BMXBaseObject {
public:

  /**
   * @brief 群成员
   **/
  struct Member {
    Member(int64_t uid, const std::string& nickname, int64_t createTime) :
      mUid(uid), mGroupNickname(nickname), mCreateTime(createTime) {}
    virtual ~Member() {}

    int64_t mUid;
    std::string mGroupNickname;
    int64_t mCreateTime;
  };
  typedef std::shared_ptr<Member> MemberPtr;
  typedef std::vector<MemberPtr> MemberList;

  /**
   * @brief 群禁言成员
   **/
  struct BannedMember {
    BannedMember() {}
    virtual ~BannedMember() {}

    int64_t mUid;
    std::string mGroupNickname;
    int64_t mCreateTime;
    int64_t mExpired;
  };
  typedef std::shared_ptr<BannedMember> BannedMemberPtr;
  typedef std::vector<BannedMemberPtr> BannedMemberList;

  /**
   * @brief 群共享文件
   **/
  struct SharedFile {
    SharedFile() {}
    virtual ~SharedFile() {}

    int64_t mFileId;
    int64_t mGroupId;
    int64_t mUploader;
    int mSize;
    int64_t mCreateTime;
    int64_t mUpdateTime;
    std::string mUrl;
    std::string mPath;
    std::string mDisplayName;
    std::string mType;
  };
  typedef std::shared_ptr<SharedFile> SharedFilePtr;
  typedef std::vector<SharedFilePtr> SharedFileList;

  /**
   * @brief 群公告
   **/
  struct Announcement {
    Announcement() {}
    virtual ~Announcement() {}

    std::string mTitle;
    std::string mContent;
    int64_t mAuthor;
    int64_t mCreateTime;
    int64_t mId;
  };
  typedef std::shared_ptr<Announcement> AnnouncementPtr;
  typedef std::vector<AnnouncementPtr> AnnouncementList;

  /**
   * @brief 群邀请状态
   **/
  enum class InvitationStatus {
    /// 请求待处理
    Pending,
    /// 请求已接受
    Accepted,
    /// 请求已拒绝
    Declined
  };

  /**
   * @brief 群邀请
   **/
  struct Invitation {
    Invitation() {}
    ~Invitation() {}

    int64_t mGroupId;
    int64_t mInviterId;
    std::string mReason;
    InvitationStatus mStatus;
    int64_t mExpired;
  };
  typedef std::shared_ptr<Invitation> InvitationPtr;
  typedef std::vector<InvitationPtr> InvitationList;

  /**
   * @brief 群申请状态
   **/
  enum class ApplicationStatus {
    /// 请求待处理
    Pending,
    /// 请求已接受
    Accepted,
    /// 请求已拒绝
    Declined
  };

  /**
   * @brief 群申请
   **/
  struct Application {
    Application() {}
    ~Application() {}

    int64_t mGroupId;
    int64_t mApplicationId;
    std::string mReason;
    ApplicationStatus mStatus;
    int64_t mExpired;
  };
  typedef std::shared_ptr<Application> ApplicationPtr;
  typedef std::vector<ApplicationPtr> ApplicationList;

  /**
   * @brief 消息通知类型
   **/
  enum class MsgPushMode {
    /// 通知所有群消息
    All,
    /// 所有消息都不通知
    None,
    /// 只通知管理员或者被@消息
    AdminOrAt,
    /// 只通知知管理员消息
    Admin,
    /// 只通知被@消息
    At
  };

  /**
   * @brief 群信息修改模式
   **/
  enum class ModifyMode {
    /// 只有管理员可以
    AdminOnly,
    /// 所有群成员都可以修改
    Open,
  };

  /**
   * @brief 进群验证方式
   **/
  enum class JoinAuthMode {
    /// 无需验证
    Open,
    /// 需要管理员批准
    NeedApproval,
    /// 拒绝所有申请
    RejectAll
  };

  /**
   * @brief 邀请入群模式
   **/
  enum class InviteMode {
    /// 只有管理员可以邀请他人进群
    AdminOnly,
    /// 所有人都可以邀请他人进群
    Open
  };

  /**
   * @brief 群组信息更新类型
   **/
  enum class UpdateInfoType {
    /// 默认初始化值
    UnKnown,
    /// 修改群名称
    Name,
    /// 修改群描述
    Description,
    /// 修改群头像
    Avatar,
    /// 修改群主
    Owner,
    /// 修改群扩展
    Ext,
    /// 群成员修改昵称
    NickName,
    /// 修改群信息模式
    ModifyMode,
    /// 修改进群验证方式
    JoinAuthMode,
    /// 修改邀请入群模式
    InviteMode,
    /// 修改群消息推送类型
    MsgPushMode,
    /// 修改是否提醒消息
    MsgMuteMode,
    /// 是否开启群消息已读功能
    ReadAckMode,
    /// 新群成员是否可见群历史聊天记录
    HistoryVisibleMode
  };

  /**
   * @brief 群组状态
   **/
  enum class GroupStatus {
    /// 群组状态正常
    Normal,
    /// 群组已销毁
    Destroyed,
  };

  /**
   * @brief 群组消息屏蔽模式
   **/
  enum class MsgMuteMode {
    /// 不屏蔽
    None,
    /// 屏蔽本地消息通知
    MuteNotification,
    /// 屏蔽消息，不接收消息
    MuteChat
  };

  enum class MemberRoleType {
    /// 群成员
    GroupMember,
    /// 群管理员
    GroupAdmin,
    /// 群主
    GroupOwner,
    /// 非群成员
    NotGroupMember
  };

  enum class GroupType {
    /// 私有群组
    Private,
    /// 公开群组(现在暂时没有开放次类型群组)
    Public,
    /// 聊天室
    Chatroom
  };

  /**
   * @brief 析构函数
   **/
  virtual ~BMXGroup() {}

  /**
   * @brief 群Id
   * @return int64_t
   **/
  virtual int64_t groupId() = 0;

  /**
   * @brief 当前群组的群组类型（Private 私有群组，Public 公开群组，Chatroom 聊天室）
   * @return GroupType
   **/
  virtual GroupType groupType() = 0;

  /**
   * @brief 在群里的昵称
   * @return std::string
   **/
  virtual const std::string& myNickname() = 0;

  /**
   * @brief 群名称
   * @return std::string
   **/
  virtual const std::string& name() = 0;

  /**
   * @brief 群描述
   * @return std::string
   **/
  virtual const std::string& description() = 0;

  /**
   * @brief 群头像
   * @return std::string
   **/
  virtual std::string avatarUrl() = 0;

  /**
   * @brief 群头像下载后的本地路径
   * @return std::string
   **/
  virtual std::string avatarPath() = 0;

  /**
   * @brief 群头像缩略图下载后的本地路径
   * @return std::string
   **/
  virtual std::string avatarThumbnailPath() = 0;

  /**
   * @brief 群创建时间
   * @return int64_t
   **/
  virtual int64_t createTime() = 0;

  /**
   * @brief 群扩展信息
   * @return JSON(std::string)
   **/
  virtual const JSON& extension() = 0;

  /**
   * @brief 群Owner
   * @return int64_t
   **/
  virtual int64_t ownerId() = 0;

  /**
   * @brief 最大人数
   * @return int
   **/
  virtual int capacity() = 0;

  /**
   * @brief 群成员数量，包含Owner，admins 和members
   * @return int
   **/
  virtual int membersCount() = 0;

  /**
   * @brief 群管理员数量
   * @return int
   **/
  virtual int adminsCount() = 0;

  /**
   * @brief 黑名单数量
   * @return int
   **/
  virtual int blockListSize() = 0;

  /**
   * @brief 禁言数量
   * @return int
   **/
  virtual int bannedListSize() = 0;

  /**
   * @brief 群共享文件数量
   * @return int
   **/
  virtual int sharedFilesCount() = 0;

  /**
   * @brief 最新群公告id
   * @return int64_t
   **/
  virtual int64_t latestAnnouncementId() = 0;

  /**
   * @brief 群消息通知类型
   * @return MsgPushMode
   **/
  virtual MsgPushMode msgPushMode() = 0;

  /**
   * @brief 群信息修改模式
   * @return ModifyMode
   **/
  virtual ModifyMode modifyMode() = 0;

  /**
   * @brief 入群审批模式
   * @return JoinAuthMode
   **/
  virtual JoinAuthMode joinAuthMode() = 0;

  /**
   * @brief 入群邀请模式
   * @return InviteMode
   **/
  virtual InviteMode inviteMode() = 0;

  /**
   * @brief 群消息屏蔽模式
   * @return MsgMuteMode
   **/
  virtual MsgMuteMode msgMuteMode() = 0;

  /**
   * @brief 当前群组的状态。（Normal 正常， Destroyed 以销毁）
   * @return GroupStatus
   **/
  virtual GroupStatus groupStatus() = 0;

  /**
   * Deprecated use roleType instead.
   * @brief 当前用户是否是群成员
   * @return bool
   **/
  virtual bool isMember() = 0;

  /**
   * @brief 是否开启群消息已读功能
   * @return bool
   **/
  virtual bool enableReadAck() = 0;

  /**
   * @brief 是否可以加载显示历史聊天记录
   * @return bool
   **/
  virtual bool historyVisible() = 0;

  /**
   * @brief 成员在群组内的角色类型
   * @return MemberRoleType
   **/
  virtual MemberRoleType roleType() = 0;

protected:
  BMXGroup() {}

private:
  BMXGroup(const BMXGroup&);
  BMXGroup& operator=(const BMXGroup&);
};

typedef std::shared_ptr<BMXGroup> BMXGroupPtr;
typedef std::vector<BMXGroupPtr> BMXGroupList;

}

#endif /* bmx_group_h__ */
