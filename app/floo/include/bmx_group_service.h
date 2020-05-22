//-------------------------------------------------------------------------------------------------
// File    : bmx_group_service.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx group service define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_group_service_h
#define bmx_group_service_h

#include <functional>
#include "bmx_group.h"
#include "bmx_error.h"
#include "bmx_result_page.h"

namespace floo {

class BMXGroupServiceListener;

/**
 * @brief 群组Service
 **/
class EXPORT_API BMXGroupService {
public:

  typedef std::function<void(int percent)> Callback;

  /**
   * @brief 创建群组选项
   **/
  struct CreateGroupOptions {
    CreateGroupOptions() {}
    CreateGroupOptions(const std::string& name, const std::string& description, bool isPublic = false) :
      mName(name), mDescription(description), mIsPublic(isPublic) {
    }

    std::string mName;                          // 群组名称
    std::string mDescription;                   // 群组描述
    bool mIsPublic;                             // 是否公开群
    std::string mMessage;                       // 建群时成员收到的邀请信息
    std::vector<int64_t> mMembers;              // 建群时添加的成员
  };

  typedef std::shared_ptr<CreateGroupOptions> CreateGroupOptionsPtr;

  virtual ~BMXGroupService() {}

  /**
   * @brief 获取群组列表，如果设置了forceRefresh则从服务器拉取
   * @param list 群组id列表，传入空列表函数返回后从此处获取返回的群组id列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode get(BMXGroupList& list, bool forceRefresh) = 0;

  /**
   * Deprecated. use get instead.
   * @brief 获取群组列表，如果设置了forceRefresh则从服务器拉取
   * @param list 群组id列表，传入空列表函数返回后从此处获取返回的群组id列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode search(BMXGroupList& list, bool forceRefresh) = 0;

  /**
   * @brief 通过传入群组的id列表获取群组信息列表，如果设置了forceRefresh则从服务器拉取
   * @param groupIdList 群组id列表
   * @param list 群组详细信息列表，传入空列表函数返回后从此处获取返回的群组详细信息列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode fetchGroupsByIdList(const std::vector<int64_t>& groupIdList, BMXGroupList& list, bool forceRefresh) = 0;

  /**
   * Deprecated. use fetchGroupsByIdList instead.
   * @brief 获取传入群组id的群组信息列表，如果设置了forceRefresh则从服务器拉取
   * @param groupIdList 群组id列表
   * @param list 群组详细信息列表，传入空列表函数返回后从此处获取返回的群组详细信息列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode search(const std::vector<int64_t>& groupIdList, BMXGroupList& list, bool forceRefresh) = 0;

  /**
   * @brief 通过群组id获取群信息，如果设置了forceRefresh则从服务器拉取
   * @param groupId 要搜索的群组id
   * @param group 搜索返回的群组信息，传入指向为空的shared_ptr对象函数执行后从此获取返回结果
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode fetchGroupById(int64_t groupId, BMXGroupPtr& group, bool forceRefresh) = 0;

  /**
   * Deprecated. use fetchGroupById instead.
   * @brief 获取群信息，如果设置了forceRefresh则从服务器拉取
   * @param groupId 要搜索的群组id
   * @param group 搜索返回的群组信息，传入指向为空的shared_ptr对象函数执行后从此获取返回结果
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode search(int64_t groupId, BMXGroupPtr& group, bool forceRefresh) = 0;

  /**
   * @brief 通过群名称查询本地群信息，从本地数据库中通过群名称查询获取群组
   * @param list 搜索结果返回的群列表信息
   * @param name 查询的群名称关键字
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode fetchLocalGroupsByName(BMXGroupList& list, const std::string& name) = 0;

  /**
   * Deprecated. use fetchLocalGroupsByName instead.
   * @brief 通过群名称查询本地群信息，从本地数据库中通过群名称查询获取群组
   * @param list 搜索结果返回的群列表信息
   * @param name 查询的群名称关键字
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode search(BMXGroupList& list, const std::string& name) = 0;

  /**
   * @brief 创建群
   * @param options 创建群组时传入的参数选项
   * @param group 创建返回的结果，传入指向为空的shared_ptr对象函数执行后从此获取返回结果
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode create(const CreateGroupOptions& options, BMXGroupPtr& group) = 0;

  /**
   * @brief 销毁群
   * @param group 要销毁的群组
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode destroy(BMXGroupPtr group) = 0;

  /**
   * @brief 加入一个群，根据群设置可能需要管理员批准
   * @param group 要加入的群组
   * @param message 申请入群的信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode join(BMXGroupPtr group, const std::string& message) = 0;

  /**
   * @brief 退出群
   * @param group 要退出的群组
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode leave(BMXGroupPtr group) = 0;

  /**
   * @brief 获取群详情，从服务端拉取最新信息
   * @param group 要获取群组最新信息的群组
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getInfo(BMXGroupPtr group) = 0;

  /**
   * @brief 获取群组成员详细信息
   * @param group 进行操作的群组
   * @param members 要获取群组成员信息详情的群成员id
   * @param list 返回的群成员详细，传入空列表在函数操作后从此处获取群成员详细信息列表
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getMembersNickname(BMXGroupPtr group, const std::vector<int64_t>& members, BMXGroup::MemberList& list) = 0;

  /**
   * @brief 分页获取群组邀请列表
   * @param result 分页获取的群组邀请列表，传入指向为空的shared_ptr对象函数执行后从此处获取结果
   * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
   * @param pageSize 分页大小
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getInvitationList(BMXGroupInvitationPagePtr& result, const std::string& cursor = "", int pageSize = 10) = 0;

  /**
   * @brief 分页获取群组申请列表
   * @param list 需要获取群组申请列表信息的群组id列表
   * @param result 分页获取的群组申请列表，传入指向为空的shared_ptr对象函数执行后从此处获取结果
   * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
   * @param pageSize 分页大小
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getApplicationList(BMXGroupList list, BMXGroupApplicationPagePtr& result, const std::string& cursor = "", int pageSize = 10) = 0;

  /**
   * @brief 分页获取群成员列表，如果设置了forceRefresh则从服务器拉取，单页最大数量为500.
   * @param group 进行操作的群组
   * @param result 分页获取的群成员列表，传入指向为空的shared_ptr对象函数执行后从此处获取结果
   * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
   * @param pageSize 分页大小
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getMembers(BMXGroupPtr group, BMXGroupMemberResultPagePtr& result, const std::string& cursor = "", int pageSize = 200) = 0;

  /**
   * @brief 获取群成员列表，如果设置了forceRefresh则从服务器拉取，最多拉取1000人
   * @param group 进行操作的群组
   * @param list 群成员列表，传入空列表函数返回后从此处获取返回的群组详细信息列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getMembers(BMXGroupPtr group, BMXGroup::MemberList& list, bool forceRefresh) = 0;

  /**
   * @brief 添加群成员
   * @param group 进行操作的群组
   * @param members 要添加进群的成员id列表
   * @param message 添加成员原因信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode addMembers(BMXGroupPtr group, const std::vector<int64_t>& members, const std::string& message) = 0;

  /**
   * @brief 删除群成员
   * @param group 进行操作的群组
   * @param members 要删除的群组成员id列表
   * @param reason 删除的原因
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode removeMembers(BMXGroupPtr group, const std::vector<int64_t>& members, const std::string& reason) = 0;

  /**
   * @brief 添加管理员
   * @param group 进行操作的群组
   * @param admins 要添加为管理员的成员id列表
   * @param message 添加为管理员的原因
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode addAdmins(BMXGroupPtr group, const std::vector<int64_t>& admins, const std::string& message) = 0;

  /**
   * @brief 删除管理员
   * @param group 进行操作的群组
   * @param admins 要从管理员移除的成员id列表
   * @param reason 要移除管理员的原因
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode removeAdmins(BMXGroupPtr group, const std::vector<int64_t>& admins, const std::string& reason) = 0;

  /**
   * @brief 获取Admins列表，如果设置了forceRefresh则从服务器拉取
   * @param group 进行操作的群组
   * @param list 群管理员列表，传入空列表函数返回后从此处获取返回的群组详细信息列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getAdmins(BMXGroupPtr group, BMXGroup::MemberList& list, bool forceRefresh) = 0;

  /**
   * @brief 添加黑名单
   * @param group 进行操作的群组
   * @param members 要加入黑名单的群成员id列表
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode blockMembers(BMXGroupPtr group, const std::vector<int64_t>& members) = 0;

  /**
   * @brief 从黑名单删除
   * @param group 进行操作的群组
   * @param members 从黑名单移除的用户id列表
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode unblockMembers(BMXGroupPtr group, const std::vector<int64_t>& members) = 0;

  /**
   * @brief 分页获取黑名单
   * @param group 进行操作的群组
   * @param result 分页获取的黑名单列表，传入指向为空的shared_ptr对象函数执行后从此处获取结果
   * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
   * @param pageSize 分页大小
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getBlockList(BMXGroupPtr group, BMXGroupMemberResultPagePtr& result, const std::string& cursor = "", int pageSize = 200) = 0;

  /**
   * @brief 获取黑名单
   * @param group 进行操作的群组
   * @param list 群黑名单列表，传入空列表函数返回后从此处获取返回的群组详细信息列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getBlockList(BMXGroupPtr group, BMXGroup::MemberList& list, bool forceRefresh) = 0;

  /**
   * @brief 禁言
   * @param group 进行操作的群组
   * @param members 被禁言的群成员id列表
   * @param duration 禁言时长
   * @param reason 禁言原因
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode banMembers(BMXGroupPtr group, const std::vector<int64_t>& members, int64_t duration, const std::string& reason = "") = 0;

  /**
   * @brief 解除禁言
   * @param group 进行操作的群组
   * @param members 被解除禁言的群成员id列表
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode unbanMembers(BMXGroupPtr group, const std::vector<int64_t>& members) = 0;

  /**
   * @brief 分页获取禁言列表
   * @param group 进行操作的群组
   * @param result 分页获取的禁言列表，传入指向为空的shared_ptr对象函数执行后从此处获取结果
   * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
   * @param pageSize 分页大小
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getBannedMembers(BMXGroupPtr group, BMXGroupBannedMemberResultPagePtr& result, const std::string& cursor = "", int pageSize = 200) = 0;

  /**
   * @brief 获取禁言列表
   * @param group 进行操作的群组
   * @param list 群禁言列表，传入空列表函数返回后从此处获取返回的群组详细信息列表
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getBannedMembers(BMXGroupPtr group, BMXGroup::BannedMemberList& list) = 0;

  /**
   * @brief 设置是否屏蔽群消息
   * @param group 进行操作的群组
   * @param mode 群屏蔽的模式
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode muteMessage(BMXGroupPtr group, BMXGroup::MsgMuteMode mode) = 0;

  /**
   * @brief 接受入群申请
   * @param group 进行操作的群组
   * @param applicantId 申请进群的用户id
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode acceptApplication(BMXGroupPtr group, int64_t applicantId) = 0;

  /**
   * @brief 拒绝入群申请
   * @param group 进行操作的群组
   * @param applicantId 申请进群的用户id
   * @param reason 拒绝的原因
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode declineApplication(BMXGroupPtr group, int64_t applicantId, const std::string& reason = "") = 0;

  /**
   * @brief 接受入群邀请
   * @param group 进行操作的群组
   * @param inviter 邀请者id
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode acceptInvitation(BMXGroupPtr group, int64_t inviter) = 0;

  /**
   * @brief 拒绝入群邀请
   * @param group 进行操作的群组
   * @param inviter 邀请者id
   * @param reason 拒绝的原因
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode declineInvitation(BMXGroupPtr group, int64_t inviter, const std::string& reason = "") = 0;

  /**
   * @brief 转移群主
   * @param group 进行操作的群组
   * @param newOwnerId 转让为新群主的用户id
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode transferOwner(BMXGroupPtr group, int64_t newOwnerId) = 0;

  /**
   * @brief 添加群共享文件
   * @param group 进行操作的群组
   * @param filePath 文件的本地路径
   * @param displayName 文件的展示名
   * @param extensionName 文件的扩展名
   * @param Callback 上传回调函数
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode uploadSharedFile(BMXGroupPtr group, const std::string& filePath, const std::string& displayName, const std::string& extensionName, Callback) = 0;

  /**
   * @brief 取消上传群共享文件
   * @param group 进行操作的群组
   * @param filePath 文件的本地路径
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode cancelUploadSharedFile(BMXGroupPtr group, const std::string& filePath) = 0;

  /**
   * @brief 移除群共享文件
   * @param group 进行操作的群组
   * @param sharedFile 删除的群共享文件
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode removeSharedFile(BMXGroupPtr group, BMXGroup::SharedFilePtr sharedFile) = 0;

  /**
   * @brief 下载群共享文件
   * @param group 进行操作的群组
   * @param sharedFile 下载的群共享文件
   * @param Callback 下载回调函数
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode downloadSharedFile(BMXGroupPtr group, BMXGroup::SharedFilePtr sharedFile, Callback) = 0;

    /**
   * @brief 取消下载群共享文件
   * @param group 进行操作的群组
   * @param sharedFile 下载的群共享文件
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode cancelDownloadSharedFile(BMXGroupPtr group, BMXGroup::SharedFilePtr sharedFile) = 0;

  /**
   * @brief 获取群共享文件列表
   * @param group 进行操作的群组
   * @param list 群共享文件列表，传入空列表函数返回后从此处获取返回的群组详细信息列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getSharedFilesList(BMXGroupPtr group, BMXGroup::SharedFileList& list, bool forceRefresh) = 0;

  /**
   * @brief 修改群共享文件名称
   * @param group 进行操作的群组
   * @param sharedFile 进行更改的群共享文件
   * @param name 修改的群共享文件名称
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode changeSharedFileName(BMXGroupPtr group, BMXGroup::SharedFilePtr sharedFile, const std::string& name) = 0;

  /**
   * @brief 获取最新的群公告
   * @param group 进行操作的群组
   * @param announcement 最新的群组公告，传入指向为空的shared_ptr对象函数返回后从此处获取最新的群组公告
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getLatestAnnouncement(BMXGroupPtr group, BMXGroup::AnnouncementPtr& announcement, bool forceRefresh) = 0;

  /**
   * @brief 获取群公告列表
   * @param group 进行操作的群组
   * @param list 群公告列表，传入空列表函数返回后从此处获取返回的群组详细信息列表
   * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getAnnouncementList(BMXGroupPtr group, BMXGroup::AnnouncementList& list, bool forceRefresh) = 0;

  /**
   * @brief 设置群公告
   * @param group 进行操作的群组
   * @param title 群公告的标题
   * @param content 群公告的内容
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode editAnnouncement(BMXGroupPtr group, const std::string& title, const std::string& content) = 0;

  /**
   * @brief 删除群公告
   * @param group 进行操作的群组
   * @param announcementId 删除的群公告id
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode deleteAnnouncement(BMXGroupPtr group, int64_t announcementId) = 0;

  /**
   * @brief 设置群名称
   * @param group 进行操作的群组
   * @param name 群组名称
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setName(BMXGroupPtr group, const std::string& name) = 0;

  /**
   * @brief 设置群描述信息
   * @param group 进行操作的群组
   * @param description 群组描述
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setDescription(BMXGroupPtr group, const std::string& description) = 0;

  /**
   * @brief 设置群扩展信息
   * @param group 进行操作的群组
   * @param extension 群组的扩展信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setExtension(BMXGroupPtr group, const std::string& extension) = 0;

  /**
   * @brief 设置在群里的昵称
   * @param group 进行操作的群组
   * @param nickname 用户在群组内的昵称
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setMyNickname(BMXGroupPtr group, const std::string& nickname) = 0;

  /**
   * @brief 设置群消息通知模式
   * @param group 进行操作的群组
   * @param mode 群消息通知模式
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setMsgPushMode(BMXGroupPtr group, BMXGroup::MsgPushMode mode) = 0;

  /**
   * @brief 设置入群审批模式
   * @param group 进行操作的群组
   * @param mode 入群审批模式
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setJoinAuthMode(BMXGroupPtr group, BMXGroup::JoinAuthMode mode) = 0;

  /**
   * @brief 设置邀请模式
   * @param group 进行操作的群组
   * @param mode 群组的邀请模式
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setInviteMode(BMXGroupPtr group, BMXGroup::InviteMode mode) = 0;

  /**
   * @brief 设置是否允许群成员设置群信息
   * @param group 进行操作的群组
   * @param enable 是否允许操作
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setAllowMemberModify(BMXGroupPtr group, bool enable) = 0;

  /**
   * @brief 设置是否开启群消息已读功能
   * @param group 进行操作的群组
   * @param enable 是否开启
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setEnableReadAck(BMXGroupPtr group, bool enable) = 0;

  /**
   * @brief 设置群成员是否开可见群历史聊天记录
   * @param group 进行操作的群组
   * @param enable 是否开启
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setHistoryVisible(BMXGroupPtr group, bool enable) = 0;

  /**
   * @brief 设置群头像
   * @param group 进行操作的群组
   * @param avatarPath 群头像文件的本地路径
   * @param Callback 上传回调函数
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setAvatar(BMXGroupPtr group, const std::string& avatarPath, Callback) = 0;

  /**
   * @brief 下载群头像
   * @param group 进行操作的群组
   * @param thumbnail 设置为true下载缩略图，false下载原图
   * @param callback 下载回调函数
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode downloadAvatar(BMXGroupPtr group,  bool thumbnail = true, Callback callback = [](int){}) = 0;

  /**
   * @brief 添加群组变化监听者
   * @param listener 群组变化监听者
   **/
  virtual void addGroupListener(BMXGroupServiceListener* listener) = 0;

  /**
   * @brief 移除群组变化监听者
   * @param listener 群组变化监听者
   **/
  virtual void removeGroupListener(BMXGroupServiceListener* listener) = 0;

protected:
  BMXGroupService() {}
};

}

#endif /* bmx_group_service_h */
