//-------------------------------------------------------------------------------------------------
// File    : bmx_conversation.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx conversation define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_conversation_h__
#define bmx_conversation_h__

#include "bmx_base_object.h"
#include "bmx_message.h"
#include "bmx_result_page.h"
#include "bmx_error.h"

namespace floo {

typedef BMXResultPage<std::shared_ptr<BMXMessage>> BMXMessagePage;

/**
 * @brief 会话
 **/
class BMXConversation : public BMXBaseObject {
public:
  /**
   * @brief 会话类型
   **/
  enum class Type {
    /// 单聊
    Single,
    /// 群聊
    Group,
    /// 系统通知
    System,
  };

  /**
   * @brief 消息搜索方向
   **/
  enum class Direction {
    /// 取更旧消息
    Up,
    /// 取更新消息
    Down
  };

  /**
   * @brief 析构函数
   **/
  virtual ~BMXConversation() {}

  /**
   * @brief 会话Id
   * @return int64_t
   **/
  virtual int64_t conversationId() = 0;

  /**
   * @brief 会话类型
   * @return Type
   **/
  virtual Type type() = 0;

  /**
   * @brief 最新消息
   * @return BMXMessagePtr
   **/
  virtual BMXMessagePtr lastMsg() = 0;

  /**
   * @brief 未读消息数
   * @return int32_t
   **/
  virtual int32_t unreadNumber() = 0;

  /**
   * @brief 会话中所有消息的数量
   * @return int32_t
   **/
  virtual int32_t messageCount() = 0;

  /**
   * @brief 是否提醒用户消息,不提醒的情况下会话总未读数不会统计该会话计数。
   * @return bool
   **/
  virtual bool isMuteNotification() = 0;

  /**
   * @brief 扩展信息
   * @return JSON(std::string)
   **/
  virtual const JSON& extension() = 0;

  /**
   * @brief 设置扩展信息
   * @param ext 会话扩展消息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setExtension(const std::string& ext) = 0;

  /**
   * @brief 编辑消息
   * @return std::string
   **/
  virtual const std::string& editMessage() = 0;

  /**
   * @brief 设置编辑消息
   * @param editMessage 会话正在编辑的文本消息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setEditMessage(const std::string& editMessage) = 0;

  /**
   * @brief 设置消息播放状态（只对语音/视频消息有效）
   * @param msg 需要设置播放状态的消息
   * @param status 消息是否已经播放
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setMessagePlayedStatus(BMXMessagePtr msg, bool status) = 0;

  /**
   * @brief 设置消息未读状态，更新未读消息数
   * @param msg 需要设置消息已读状态的消息
   * @param status 消息是否设置已读
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setMessageReadStatus(BMXMessagePtr msg, bool status) = 0;

  /**
   * @brief 把所有消息设置为已读，更新未读消息数
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode setAllMessagesRead() = 0;

  /**
   * @brief 更新一条数据库存储消息的扩展字段信息
   * @param msg 需要更改扩展信息的消息此时msg部分已经更新扩展字椴信息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode updateMessageExtension(BMXMessagePtr msg) = 0;

  /**
   * @brief 插入一条消息
   * @param msg 插入的消息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode insertMessage(BMXMessagePtr msg) = 0;

  /**
   * @brief 读取一条消息
   * @param msgId 需要读取的消息的消息id
   * @return BMXMessagePtr
   **/
  virtual BMXMessagePtr loadMessage(int64_t msgId) = 0;

  /**
   * @brief 删除会话中的所有消息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode removeAllMessages() = 0;

  /**
   * @brief 加载消息，如果不指定则从最新消息开始
   * @param refMsgId 加载消息的起始id
   * @param size 最大加载消息条数
   * @param result 数据库返回的加载消息列表
   * @param Direction 加载消息的方向，默认是加载更早的消息
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode loadMessages(int64_t refMsgId, size_t size, BMXMessageList& result, Direction = Direction::Up) = 0;

  /**
   * @brief 搜索消息，如果不指定则从最新消息开始
   * @param keywords 搜索消息的关键字
   * @param refTime 搜索消息的起始时间
   * @param size 最大加载消息条数
   * @param result 搜索到的消息结果列表
   * @param Direction 消息搜索方向，默认（Direction::Up）是从更早的消息中搜索
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode searchMessagesByKeyWords(const std::string& keywords, int64_t refTime, size_t size, BMXMessageList& result, Direction = Direction::Up) = 0;

  /**
   * Deprecated. use searchMessagesByKeyWords instead.
   * @brief 搜索消息，如果不指定则从最新消息开始
   * @param keywords 搜索消息的关键字
   * @param refTime 搜索消息的起始时间
   * @param size 最大加载消息条数
   * @param result 搜索到的消息结果列表
   * @param Direction 消息搜索方向，默认（Direction::Up）是从更早的消息中搜索
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode searchMessages(const std::string& keywords, int64_t refTime, size_t size, BMXMessageList& result, Direction = Direction::Up) = 0;

  /**
   * @brief 按照类型搜索消息，如果不指定则从最新消息开始
   * @param type 搜索消息的类型
   * @param refTime 搜索消息的起始时间
   * @param size 最大加载消息条数
   * @param result 搜索到的消息结果列表
   * @param Direction 消息搜索方向，默认（Direction::Up）是从更早的消息中搜索
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode searchMessagesByType(BMXMessage::ContentType type, int64_t refTime, size_t size, BMXMessageList& result, Direction = Direction::Up) = 0;

  /**
   * Deprecated. use searchMessagesByType instead.
   * @brief 按照类型搜索消息，如果不指定则从最新消息开始
   * @param type 搜索消息的类型
   * @param refTime 搜索消息的起始时间
   * @param size 最大加载消息条数
   * @param result 搜索到的消息结果列表
   * @param Direction 消息搜索方向，默认（Direction::Up）是从更早的消息中搜索
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode searchMessages(BMXMessage::ContentType type, int64_t refTime, size_t size, BMXMessageList& result, Direction = Direction::Up) = 0;

  /**
   * @brief 读取数据库当前会话所有消息数量，强制更新conversation的消息总数和未读消息数。
   * @return BMXErrorCode
   **/
  virtual BMXErrorCode refreshConversation() = 0;

protected:
  BMXConversation() {}

private:
  BMXConversation(const BMXConversation&);
  BMXConversation& operator=(const BMXConversation&);
};

typedef std::shared_ptr<BMXConversation> BMXConversationPtr;
typedef std::vector<BMXConversationPtr> BMXConversationList;
}

#endif /* bmx_conversation_h__ */
