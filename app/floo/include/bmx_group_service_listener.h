//-------------------------------------------------------------------------------------------------
// File    : bmx_group_service_listener.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx group service listener define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_group_service_listener_h
#define bmx_group_service_listener_h

#include "bmx_group.h"

namespace floo {

/**
 * @brief 群组变化监听者
 **/
class EXPORT_API BMXGroupServiceListener {
public:
  /**
   * @brief 析构函数
   **/
  virtual ~BMXGroupServiceListener() {}

  /**
   * @brief 多设备同步创建群组
   * @param group 新创建的群组
   **/
  virtual void onGroupCreate(BMXGroupPtr group) {}

  /**
   * @brief 群列表更新了
   * @param list 更新的群组列表
   **/
  virtual void onGroupListUpdate(const BMXGroupList& list)  {}

  /**
   * @brief 加入了某群
   * @param group 加入的群组
   **/
  virtual void onGroupJoined(BMXGroupPtr group)  {}

  /**
   * @brief 退出了某群
   * @param group 退出的群组
   * @param reason 退出原因
   **/
  virtual void onGroupLeft(BMXGroupPtr group, const std::string& reason)  {}

  /**
   * @brief 收到入群邀请
   * @param groupId 邀请进入的群组id
   * @param inviter 邀请者id
   * @param message 邀请原因
   **/
  virtual void onInvitated(int64_t groupId, int64_t inviter, const std::string& message)  {}

  /**
   * @brief 入群邀请被接受
   * @param group 邀请被同意的群组
   * @param inviteeId 被邀请者id
   **/
  virtual void onInvitationAccepted(BMXGroupPtr group, int64_t inviteeId)  {}

  /**
   * @brief 入群申请被拒绝
   * @param group 邀请被拒绝的群组
   * @param inviteeId 被邀请者id
   * @param reason 拒绝原因
   **/
  virtual void onInvitationDeclined(BMXGroupPtr group, int64_t inviteeId, const std::string& reason)  {}

  /**
   * @brief 收到入群申请
   * @param group 收到入群申请的群组
   * @param applicantId 申请者id
   * @param message 申请原因
   **/
  virtual void onApplied(BMXGroupPtr group, int64_t applicantId, const std::string& message)  {}

  /**
   * @brief 入群申请被接受
   * @param group 入群申请被接受的群组
   * @param approver 申请的批准者
   **/
  virtual void onApplicationAccepted(BMXGroupPtr group, int64_t approver)  {}

  /**
   * @brief 入群申请被拒绝
   * @param group 入群申请被拒绝的群组
   * @param approver 申请的批准者
   * @param reason 拒绝的原因
   **/
  virtual void onApplicationDeclined(BMXGroupPtr group, int64_t approver, const std::string& reason)  {}

  /**
   * @brief 群成员被禁言
   * @param group 群成员被禁言的群组
   * @param members 被禁言的群成员id列表
   * @param duration 禁言时长
   **/
  virtual void onMembersBanned(BMXGroupPtr group, const std::vector<int64_t>& members, int64_t duration)  {}

  /**
   * @brief 群成员被解除禁言
   * @param group 群成员被解除禁言的群组
   * @param members 被解除禁言的群成员id列表
   **/
  virtual void onMembersUnbanned(BMXGroupPtr group, const std::vector<int64_t>& members)  {}

  /**
   * @brief 加入新成员
   * @param group 有成员加入的群组
   * @param memberId 加入成员的id
   * @param inviter 邀请者id
   **/
  virtual void onMemberJoined(BMXGroupPtr group, int64_t memberId, int64_t inviter)  {}

  /**
   * @brief 群成员退出
   * @param group 有成员退出的群组
   * @param memberId 退出的群成员id
   * @param reason 退出的原因
   **/
  virtual void onMemberLeft(BMXGroupPtr group, int64_t memberId, const std::string& reason)  {}

  /**
   * @brief 添加了新管理员
   * @param group 发生添加新管理员的群组
   * @param members 被提升为管理员的成员列表
   **/
  virtual void onAdminsAdded(BMXGroupPtr group, const std::vector<int64_t>& members)  {}

  /**
   * @brief 移除了管理员
   * @param group 发生移除管理员的群组
   * @param members 被移除了管理员的成员列表
   * @param reason 被移除的原因
   **/
  virtual void onAdminsRemoved(BMXGroupPtr group, const std::vector<int64_t>& members, const std::string& reason)  {}

  /**
   * @brief 成为群主
   * @param group 被转让为群主的群组
   **/
  virtual void onOwnerAssigned(BMXGroupPtr group)  {}

  /**
   * @brief 群组信息变更
   * @param group 群信息发生变更的群组
   * @param type 发生变更的群信息类型
   **/
  virtual void onGroupInfoUpdate(BMXGroupPtr group, BMXGroup::UpdateInfoType type) {}

  /**
   * @brief 群成员更改群内昵称
   * @param group 发生群成员变更群昵称的群组
   * @param memberId 变更群昵称的群成员id
   * @param nickName 变更后的群昵称
   **/
  virtual void onMemberChangeNickName(BMXGroupPtr group, int64_t memberId, const std::string& nickName) {}

  /**
   * @brief 收到群公告
   * @param group 发生群公告更新的群组
   * @param announcement 变更后的最新的群更高
   **/
  virtual void onAnnouncementUpdate(BMXGroupPtr group, BMXGroup::AnnouncementPtr announcement) {}

  /**
   * @brief 收到共享文件
   * @param group 发生群共享文件上传的群组
   * @param sharedFile 新上传的群共享文件
   **/
  virtual void onSharedFileUploaded(BMXGroupPtr group, BMXGroup::SharedFilePtr sharedFile) {}

  /**
   * @brief 删除了共享文件
   * @param group 发生群共享文件删除的群组
   * @param sharedFile 被删除的群共享文件
   **/
  virtual void onSharedFileDeleted(BMXGroupPtr group, BMXGroup::SharedFilePtr sharedFile) {}

  /**
   * @brief 共享文件更新文件名
   * @param group 发生群共享文件更新的群组
   * @param sharedFile 更新的群共享文件
   **/
  virtual void onSharedFileUpdated(BMXGroupPtr group, BMXGroup::SharedFilePtr sharedFile) {}

  /**
   * @brief 添加黑名单
   * @param group 添加黑名单的群组
   * @param members 添加的黑名单成员列表
   **/
  virtual void onBlockListAdded(BMXGroupPtr group, const std::vector<int64_t>& members) {}

  /**
   * @brief 删除黑名单
   * @param group 删除黑名单的群组
   * @param members 删除的黑名单成员列表
   **/
  virtual void onBlockListRemoved(BMXGroupPtr group, const std::vector<int64_t>& members) {}

  /**
   * @brief 客户端从服务器拉取到新群组时触发，用于用户群组列表更新，从SDK调用本地获取群组即可取得全部成员信息
   **/
  virtual void onGroupListUpdate() {}
};

}

#endif /* bmx_group_service_listener_h */
