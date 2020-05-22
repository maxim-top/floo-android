//-------------------------------------------------------------------------------------------------
// File    : bmx_chat_service.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx chat service define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_chat_service_h
#define bmx_chat_service_h

#include "bmx_message.h"
#include "bmx_conversation.h"
#include "bmx_error.h"
#include "bmx_result_page.h"

namespace floo {

class BMXChatServiceListener;

/**
 * @brief 聊天Service
 **/
class EXPORT_API BMXChatService {
public:

  /**
   * @brief 缩略图生成策略,
   **/
  enum class ThumbnailStrategy {
    /// 第三方服务器生成
    ThirdpartyServerCreate = 1,
    /// 本地服务器生成
    LocalServerCreate,
  };

  virtual ~BMXChatService() {}

  /**
   @brief 发送消息，消息状态变化会通过listener通知
   @param msg 发送的消息
   **/
  virtual void sendMessage(BMXMessagePtr msg) = 0;

  /**
   @brief 重新发送消息，消息状态变化会通过listener通知
   @param msg 重新发送的消息
   **/
  virtual void resendMessage(BMXMessagePtr msg) = 0;

  /**
   @brief 撤回消息，消息状态变化会通过listener通知
   @param msg 撤回的消息
   **/
  virtual void recallMessage(BMXMessagePtr msg) = 0;

  /**
   @brief 合并转发消息
   @param list 转发的消息列表
   @param to 消息被转发到的会话
   @param newMsg 转发的消息列表合并后生成的新的单条转发消息
   @return BMXErrorCode
   **/
  virtual BMXErrorCode forwardMessage(const BMXMessageList &list, BMXConversationPtr to, BMXMessagePtr &newMsg) = 0;

  /**
   @brief 简单转发消息，用户应当通过BMXMessage::createForwardMessage()先创建转发消息
   @param msg 转发的消息
   **/
  virtual void forwardMessage(BMXMessagePtr msg) = 0;

  /**
   * @brief 发送已读回执
   * @param msg 需要发送已读回执的消息
   **/
  virtual void ackMessage(BMXMessagePtr msg) = 0;

  /**
   * @brief 标记此消息为未读，该消息同步到当前用户的所有设备
   * @param msg 需要发送消息已读取消的消息
   **/
  virtual void readCancel(BMXMessagePtr msg) = 0;

  /**
   * @brief 标记此消息及之前全部消息为已读，该消息同步到当前用户的所有设备
   * @param msg 需要标记为此消息以前全部消息为已读的消息
   **/
  virtual void readAllMessage(BMXMessagePtr msg) = 0;

  /**
   * @brief 删除此消息，该消息同步到当前用户的其它设备
   * @param msg 需要删除的消息
   * @param synchronize 是否同步到其它设备，不同步的情况下只会删除本地存储的该条消息
   **/
  virtual void removeMessage(BMXMessagePtr msg, bool synchronize = true) = 0;

  /**
   * @brief 下载缩略图，下载状态变化和进度通过listener通知
   * 缩略图生成策略，1 - 第三方服务器生成， 2 - 本地服务器生成，默认值是 1。
   * @param msg 需要下载缩略图的消息
   * @param strategy 缩略图生成策略，1 - 第三方服务器生成， 2 - 本地服务器生成，默认值是 1。
   **/
  virtual void downloadThumbnail(BMXMessagePtr msg, ThumbnailStrategy strategy = ThumbnailStrategy::ThirdpartyServerCreate) = 0;

  /**
   * @brief 下载附件，下载状态变化和进度通过listener通知
   * @param msg 需要下载附件的消息
   **/
  virtual void downloadAttachment(BMXMessagePtr msg) = 0;

  /**
   * @brief 取消上传附件
   * @param msg 需要取消上传附件的消息
   **/
  virtual void cancelUploadAttachment(BMXMessagePtr msg) = 0;

  /**
   * @brief 取消下载附件
   * @param msg 需要取消下载附件的消息
   **/
  virtual void cancelDownloadAttachment(BMXMessagePtr msg) = 0;

  /**
   * @brief 插入消息
   * @param list 插入消息列表
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode insertMessages(const BMXMessageList& list) = 0;

  /**
   * @brief 读取一条消息
   * @param msgId 需要获取消息的消息id
   * @return BMXMessagePtr
   **/
  virtual BMXMessagePtr getMessage(int64_t msgId) = 0;

  /**
   * @brief 删除会话
   * @param conversationId 需要删除会话的会话id
   * @param synchronize 是否同步删除其它设备该会话，默认为false，仅删除本设备会话
   **/
  virtual void deleteConversation(int64_t conversationId, bool synchronize = false) = 0;

  /**
   * @brief 打开一个会话
   * @param conversationId 需要打开的会话的会话id
   * @param type 会话的类型，单聊还是群聊。
   * @param createIfNotExist 会话不存在的情况下是否要创建本地会话，默认为创建
   * @return BMXConversationPtr
   **/
  virtual BMXConversationPtr openConversation(int64_t conversationId, BMXConversation::Type type, bool createIfNotExist = true) = 0;

  /**
   * @brief 获取附件保存路径
   * @return std::string
   **/
  virtual std::string attachmentDir() = 0;

  /**
   * @brief 获取会话的附件保存路径
   * @param conversationId 需要获取会话附件路径的会话id
   * @return std::string
   **/
  virtual std::string attachmentDirForConversation(int64_t conversationId) = 0;

  /**
   * @brief 获取所有会话
   * @return BMXConversationList
   **/
  virtual BMXConversationList getAllConversations() = 0;

  /**
   * @brief 获取所有会话的全部未读数（标记为屏蔽的个人和群组的未读数不统计在内）
   * @return int
   **/
  virtual int getAllConversationsUnreadCount() = 0;

  /**
   * @brief 拉取历史消息
   * @param conversation 需要拉取历史消息的会话
   * @param refMsgId 拉取会话消息的起始消息Id
   * @param size 拉取的最大消息条数
   * @param result 拉取操作获取的消息列表，外部初始化传入空列表。
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode retrieveHistoryMessages(BMXConversationPtr conversation, int64_t refMsgId, size_t size, BMXMessageList& result) = 0;

  /**
   * @brief 使用关键字搜索消息
   * @param keywords 搜索的关键字
   * @param refTime 搜索消息的起始时间
   * @param size 搜索的最大消息条数
   * @param result 搜索到的消息结果列表，外部初始化传入空列表。
   * @param Direction 消息搜索方向，默认（Direction::Up）是从更早的消息中搜索
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode searchMessagesByKeyWords(const std::string& keywords, int64_t refTime, size_t size, std::vector<BMXMessageList>& result, BMXConversation::Direction = BMXConversation::Direction::Up) = 0;

  /**
   * Deprecated. use searchMessagesByKeyWords instead.
   * @brief 搜索消息
   * @param keywords 搜索的关键字
   * @param refTime 搜索消息的起始时间
   * @param size 搜索的最大消息条数
   * @param result 搜索到的消息结果列表，外部初始化传入空列表。
   * @param Direction 消息搜索方向，默认（Direction::Up）是从更早的消息中搜索
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode searchMessages(const std::string& keywords, int64_t refTime, size_t size, std::vector<BMXMessageList>& result, BMXConversation::Direction = BMXConversation::Direction::Up) = 0;

  /**
   * @brief 获取发送的群组消息已读用户id列表
   * @param msg 需要获取已读用户id列表的消息
   * @param groupMemberIdList 对该条消息已读的用户id列表，初始传入为空列表
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getGroupAckMessageUserIdList(BMXMessagePtr msg, std::vector<int64_t>& groupMemberIdList) = 0;

  /**
   * @brief 获取发送的群组消息未读用户id列表
   * @param msg 需要获取未读用户id列表的消息
   * @param groupMemberIdList 对该条消息未读的用户id列表，初始传入为空列表
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode getGroupAckMessageUnreadUserIdList(BMXMessagePtr msg, std::vector<int64_t>& groupMemberIdList) = 0;

  /**
   * @brief 添加聊天监听者
   * @param listener 聊天监听者
   **/
  virtual void addChatListener(BMXChatServiceListener* listener) = 0;

  /**
   * @brief 移除聊天监听者
   * @param listener 聊天监听者
   **/
  virtual void removeChatListener(BMXChatServiceListener* listener) = 0;

protected:
  BMXChatService() {}
  void updateMessageId(BMXMessagePtr msg, int64_t newId);
};

}

#endif /* bmx_chat_service_h */
