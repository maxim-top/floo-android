//-------------------------------------------------------------------------------------------------
// File    : bmx_chat_service_listener.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx chat service listener define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_chat_service_listener_h
#define bmx_chat_service_listener_h

#include "bmx_message.h"
#include "bmx_conversation.h"
#include "bmx_error.h"

namespace floo {

/**
 * @brief 聊天监听者
 **/
class EXPORT_API BMXChatServiceListener {
public:
  /**
   * @brief 析构函数
   **/
  virtual ~BMXChatServiceListener() {}

  /**
   * @brief 消息发送状态发生变化
   * @param msg 发生状态变化的消息
   * @param error 状态错误码
   **/
  virtual void onStatusChanged(BMXMessagePtr msg, BMXErrorCode error)  {}

  /**
   * @brief 附件上传进度发送变化
   * @param msg 上传附件的消息
   * @param percent 附件上传的进度
   **/
  virtual void onAttachmentUploadProgressChanged(BMXMessagePtr msg, int percent)  {}

  /**
   * @brief 消息撤回状态发送变化
   * @param msg 撤回状态发生变化的消息
   * @param error 状态错误码
   **/
  virtual void onRecallStatusChanged(BMXMessagePtr msg, BMXErrorCode error)  {}

  /**
   * @brief 收到消息
   * @param list 接收到的消息列表
   **/
  virtual void onReceive(const BMXMessageList& list)  {}

  /**
   * @brief 收到命令消息
   * @param list 接收到的消息列表
   **/
  virtual void onReceiveCommandMessages(const BMXMessageList& list) {}

  /**
   * @brief 收到系统通知消息
   * @param list 接收到的系统消息列表
   **/
  virtual void onReceiveSystemMessages(const BMXMessageList& list)  {}

  /**
   * @brief 收到消息已读回执
   * @param list 接收到的已读回执消息列表
   **/
  virtual void onReceiveReadAcks(const BMXMessageList& list)  {}

  /**
   * @brief 收到消息已送达回执
   * @param list 接收到的已送达回执消息列表
   **/
  virtual void onReceiveDeliverAcks(const BMXMessageList& list)  {}

  /**
   * @brief 收到撤回消息
   * @param list 接收到的撤回消息列表
   **/
  virtual void onReceiveRecallMessages(const BMXMessageList& list)  {}

  /**
   * @brief 收到消息已读取消（多设备其他设备同步消息已读状态变为未读）
   * @param list 接收到的消息已读取消消息列表
   **/
  virtual void onReceiveReadCancels(const BMXMessageList& list) {}

  /**
   * @brief 收到消息全部已读（多设备同步某消息之前消息全部设置为已读）
   * @param list 接收到的消息全部已读消息列表
   **/
  virtual void onReceiveReadAllMessages(const BMXMessageList& list) {}

  /**
   * @brief 收到删除消息 （多设备同步删除消息）
   * @param list 接收到的删除消息列表
   **/
  virtual void onReceiveDeleteMessages(const BMXMessageList& list) {}

  /**
   * @brief 附件下载状态发生变化
   * @param msg 发生下载状态变化的消息
   * @param error 状态错误码
   * @param percent 附件下载的进度
   **/
  virtual void onAttachmentStatusChanged(BMXMessagePtr msg, BMXErrorCode error, int percent)  {}

  /**
   * @brief 拉取历史消息
   * @param conversation 发生了拉取指历史消息的会话
   **/
  virtual void onRetrieveHistoryMessages(BMXConversationPtr conversation) {}

  /**
   * @brief 已经加载完未读会话列表
   **/
  virtual void onLoadAllConversation() {}

  /**
   * @brief 本地创建新会话
   * @param conversation 新创建的本地会话
   * @param msg 会话的最新消息，存在返回不存在返回为空
   **/
  virtual void onConversationCreate(BMXConversationPtr conversation, BMXMessagePtr msg) {}

  /**
   * @brief 删除会话
   * @param conversationId 删除的本地会话id
   * @param error 状态错误码
   **/
  virtual void onConversationDelete(int64_t conversationId, BMXErrorCode error) {}

  /**
   * @brief 更新总未读数
   * @param unreadCount 本地全部会话未读总数
   **/
  virtual void onTotalUnreadCountChanged(int unreadCount) {}
};

}

#endif /* bmx_chat_service_listener_h */
