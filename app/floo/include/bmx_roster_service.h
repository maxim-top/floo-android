//-------------------------------------------------------------------------------------------------
// File    : bmx_roster_service.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx roster service define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_roster_service_h
#define bmx_roster_service_h

#include <vector>
#include <functional>
#include "bmx_base_object.h"
#include "bmx_roster_item.h"
#include "bmx_result_page.h"

namespace floo {

class BMXRosterServiceListener;

/**
 * @brief 好友Service
 **/
class EXPORT_API BMXRosterService {
public:

  /**
   * @brief 请求状态
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
   * @brief 好友邀请
   **/
  struct Application {
    Application() {}
    virtual ~Application() {}

    int64_t mRosterId;
    std::string mReason;
    ApplicationStatus mStatus;
    int64_t mExpire;
  };
  typedef std::shared_ptr<Application> ApplicationPtr;
  typedef std::vector<ApplicationPtr> ApplicationList;
  typedef BMXResultPage<ApplicationPtr> BMXRosterApplicationResultPage;
  typedef std::shared_ptr<BMXRosterApplicationResultPage> BMXRosterApplicationResultPagePtr;

  virtual ~BMXRosterService() {}

  typedef std::function<void(int percent)> Callback;

  /**
   * @brief 获取好友列表，如果forceRefresh == true，则强制从服务端拉取
   * @param list 好友id列表，传入空列表函数返回后从此处获取返回的好友id列表
   * @param forceRefresh 是否从服务器读取数据，true为强制从服务器获取，false情况下本地读取列表为空的情况下会自动从服务器读取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode get(std::vector<int64_t>& list, bool forceRefresh) = 0;

  /**
   * @brief 通过联系人id搜索用户
   * @param rosterId 搜索的好友id
   * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
   * @param item 查询返回的用户的信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode fetchRosterById(int64_t rosterId, bool forceRefresh, BMXRosterItemPtr& item) = 0;

  /**
   * Deprecated. use fetchRosterById instead.
   * @brief 搜索用户
   * @param rosterId 搜索的好友id
   * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
   * @param item 查询返回的用户的信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode search(int64_t rosterId, bool forceRefresh, BMXRosterItemPtr& item) = 0;
  
  /**
   * @brief 通过用户名搜索用户
   * @param name 搜索的用户名
   * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
   * @param item 查询返回的用户的信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode fetchRosterByName(const std::string& name, bool forceRefresh, BMXRosterItemPtr& item) = 0;
  
  /**
   * Deprecated. use fetchRosterByName instead.
   * @brief 搜索用户
   * @param name 搜索的用户名
   * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
   * @param item 查询返回的用户的信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode search(const std::string& name, bool forceRefresh, BMXRosterItemPtr& item) = 0;

  /**
   * @brief 通过联系人id列表批量搜索用户
   * @param rosterIdList 需要搜索的用户id列表
   * @param list 返回的好友信息列表，传入空列表函数返回后从此处获取返回的好友信息列表
   * @param forceRefresh 是否强制从服务器获取，为true则强制从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode fetchRostersByIdList(const std::vector<int64_t>& rosterIdList, BMXRosterList& list, bool forceRefresh) = 0;

  /**
   * Deprecated. use fetchRostersByIdList instead.
   * @brief 批量搜索用户
   * @param rosterIdList 需要搜索的用户id列表
   * @param list 返回的好友信息列表，传入空列表函数返回后从此处获取返回的好友信息列表
   * @param forceRefresh 是否强制从服务器获取，为true则强制从服务器获取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode search(const std::vector<int64_t>& rosterIdList, BMXRosterList& list, bool forceRefresh) = 0;

  /**
   * @brief 更新好友本地扩展信息
   * @param item 用户信息
   * @param extension 本地扩展信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setItemLocalExtension(BMXRosterItemPtr item, const JSON& extension) = 0;

  /**
   * @brief 更新好友服务器扩展信息
   * @param item 用户信息
   * @param extension 服务器扩展信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setItemExtension(BMXRosterItemPtr item, const JSON& extension) = 0;

  /**
   * @brief 更新好友别名
   * @param item 用户信息
   * @param alias 好友别名
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setItemAlias(BMXRosterItemPtr item, const JSON& alias) = 0;

  /**
   * @brief 设置是否拒收用户消息
   * @param item 用户信息
   * @param status 是否拒收用户消息，true拒收，false不拒收
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setItemMuteNotification(BMXRosterItemPtr item, bool status) = 0;

  /**
   * @brief 获取申请添加好友列表
   * @param result 返回的申请好友列表信息，传入指向为空的shared_ptr对象函数执行后会自动赋值。
   * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
   * @param pageSize 分页大小
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getApplicationList(BMXRosterApplicationResultPagePtr& result, const std::string& cursor, int pageSize = 10) = 0;

  /**
   * @brief 申请添加好友
   * @param rosterId 申请添加的用户id
   * @param message 好友申请信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode apply(int64_t rosterId, const std::string& message, const std::string& authAnswer = "") = 0;

  /**
   * @brief 删除好友
   * @param rosterId 删除的好友id
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode remove(int64_t rosterId) = 0;

  /**
   * @brief 接受加好友申请
   * @param rosterId 申请加为好友的用户id
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode accept(int64_t rosterId) = 0;

  /**
   * @brief 拒绝加好友申请
   * @param rosterId 申请加为好友的用户id
   * @param reason 拒绝的原因
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode decline(int64_t rosterId, const std::string& reason) = 0;

  /**
   * @brief 加入黑名单
   * @param rosterId 加入黑名单的用户id
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode block(int64_t rosterId) = 0;

  /**
   * @brief 从黑名单移除
   * @param rosterId 从黑名单移除的用户id
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode unblock(int64_t rosterId) = 0;

  /**
   * @brief 获取黑名单，如果forceRefresh == true，则强制从服务端拉取
   * @param list 好友id列表，传入空列表函数返回后从此处获取返回的黑名单id列表
   * @param forceRefresh 是否从服务器读取数据，true为强制从服务器获取，false情况下本地读取列表为空的情况下会自动从服务器读取
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getBlockList(std::vector<int64_t>& list, bool forceRefresh) = 0;

  /**
   * @brief 下载头像
   * @param item 用户信息
   * @param thumbnail 是否下载缩略图，ture为缩略图，false为原图
   * @param callback 下载回调函数
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode downloadAvatar(BMXRosterItemPtr item, bool thumbnail = true, Callback callback = [](int){}) = 0;

  /**
   * @brief 添加好友变化监听者
   * @param listener 好友变化监听者
   **/
  virtual void addRosterListener(BMXRosterServiceListener* listener) = 0;

  /**
   * @brief 移除好友变化监听者
   * @param listener 好友变化监听者
   **/
  virtual void removeRosterListener(BMXRosterServiceListener* listener) = 0;

protected:
  BMXRosterService() {}
};

}

#endif /* bmx_roster_service_h */
