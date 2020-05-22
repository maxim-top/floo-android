//-------------------------------------------------------------------------------------------------
// File    : bmx_message.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx message define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef __bmx_message_h__
#define __bmx_message_h__

#include <vector>
#include <memory>
#include <mutex>
#include "bmx_base_object.h"
#include "bmx_message_attachment.h"
#include "bmx_message_config.h"

namespace floo {
class BMXMessage;
typedef std::shared_ptr<BMXMessage> BMXMessagePtr;
typedef std::vector<BMXMessagePtr> BMXMessageList;
class BMXChatService;

/**
 * @brief 消息
 **/
class EXPORT_API BMXMessage : public BMXBaseObject
{
public:
  /**
   *  @brief 消息投递状态
   **/
  enum class DeliveryStatus {
    /// 新创建消息
    New,
    /// 消息投递中
    Delivering,
    /// 消息已投递
    Deliveried,
    /// 消息投递失败
    Failed,
    /// 消息已撤回
    Recalled
  };

  /**
   *  @brief 消息类型
   **/
  enum class MessageType {
    /// 单聊消息
    Single,
    /// 群聊消息
    Group,
    /// 系统消息
    System,
  };

  /**
   *  @brief 消息内容类型
   **/
  enum class ContentType {
    /// 文本消息
    Text,
    /// 图片消息
    Image,
    /// 语音消息
    Voice,
    /// 视频片段消息
    Video,
    /// 文件消息
    File,
    /// 位置消息
    Location,
    /// 命令消息
    Command,
    /// 转发消息
    Forward,
  };

  /**
   *  @brief 消息投递质量
   **/
  enum class DeliveryQos {
    /// 最少投递一次
    AtLastOnce,
    /// 最多投递一次
    AtMostOnce,
    /// 仅投递一次
    ExactlyOnce
  };

  /**
   * @brief 析构函数
   **/
  virtual ~BMXMessage();

  /**
   * @brief 消息唯一ID
   * @return int64_t
   **/
  int64_t msgId();

  /**
   * @brief 消息客户端ID,仅在消息发送端存在
   * @return int64_t
   **/
  int64_t clientMsgId();

  /**
   * @brief 消息发送方ID
   * @return int64_t
   **/
  int64_t fromId();

  /**
   * @brief 消息接收方ID
   * @return int64_t
   */
  int64_t toId();

  /**
   * @brief 消息类型
   * @return MessageType
   */
  MessageType type();

  /**
   * @brief 消息所属会话ID
   * @return int64_t
   */
  int64_t conversationId();

  /**
   * @brief 消息投递状态
   * @return DeliveryStatus
   */
  DeliveryStatus deliveryStatus();

  /**
   * @brief 设置消息投递状态
   */
  void setDeliveryStatus(DeliveryStatus);

  /**
   * @brief 消息时间戳（服务端收到时的时间）
   * @return int64_t
   */
  int64_t serverTimestamp();

  /**
   * @brief 设置时间戳（服务端收到时的时间）
   */
  void setServerTimestamp(int64_t);

  /**
   * @brief 本地时间戳（消息创建或者收到时的本地时间）
   * @return int64_t
   */
  int64_t clientTimestamp();

  /**
   * @brief 设置消息本地时间戳
   */
  void setClientTimestamp(int64_t);

  /**
   * @brief 语音或者视频消息是否播放过，仅对收到的音视频消息有效
   * @return bool
   */
  bool isPlayed();

  /**
   * @brief 设置语音或者视频消息是否播放过，仅对收到的音视频消息有效
   */
  void setIsPlayed(bool);

  /**
   * @brief 是否接收的消息
   * @return bool
   */
  bool isReceiveMsg();

  /**
   * @brief 设置是否接收的消息
   */
  void setIsReceiveMsg(bool);

  /**
   * @brief 消息是否已读标志
   * @return bool
   */
  bool isRead();

  /**
   * @brief 消息是否已读标志
   */
  void setIsRead(bool);

  /**
   * @brief 对于发送方表示是否收到了已读回执，对于接收方表示是否发送了已读回执
   * @return bool
   */
  bool isReadAcked();

  /**
   * @brief 设置已读回执
   */
  void setIsReadAcked(bool);

  /**
   * @brief 对于发送方表示消息是否已投递到对方，对于接收方表示是否发送了消息已到达回执
   * @return bool
   */
  bool isDeliveryAcked();

  /**
   * @brief 设置投递回执
   */
  void setIsDeliveryAcked(bool);

  /**
   * @brief 消息文本内容
   * @return std::string
   */
  const std::string& content();

  /**
   * @brief 消息文本内容
   * @param content 消息文本内容
   */
  void setContent(const std::string& content);

  /**
   * @brief 消息内容类型，如果带附件就表示附件类型，不带附件就是文本类型
   * @return ContentType
   */
  ContentType contentType();

  /**
   * @brief 消息附件，BMXMessage拥有附件的所有权，负责释放
   * @return BMXMessageAttachmentPtr
   */
  BMXMessageAttachmentPtr attachment();

  /**
   * @brief 消息的配置信息
   * @return JSON(std::string)
   */
  BMXMessageConfigPtr config();

  /**
   * @brief 设置消息配置信息
   */
  void setConfig(BMXMessageConfigPtr);

  /**
   * @brief 消息扩展信息
   * @return JSON(std::string)
   */
  const JSON& extension();

  /**
   * @brief 设置消息扩展信息
   */
  void setExtension(const JSON&);

  /**
   * @brief 消息投递QOS
   * @return DeliveryQos
   */
  DeliveryQos deliveryQos();

  /**
   * @brief 设置消息投递QOS
   * @param qos 消息投递QOS
   */
  void setDeliveryQos(DeliveryQos qos);

  /**
   * @brief 消息发送者的显示名称
   * @return std::string
   */
  const std::string& senderName();

  /**
   * @brief 设置消息的发送者显示名称
   * @param senderName 消息文本内容
   */
  void setSenderName(const std::string& senderName);

  /**
   * @brief 群消息已读AckCount数目
   * @return int
   */
  int groupAckCount();

  /**
   * @brief 设置消息已读groupAckCount数目(SDK 内部调用接口，上层不应该调用)
   * @param count 设置群消息已读数目
   */
  void setGroupAckCount(int count);

  /**
   * @brief 群消息未读AckCount数目
   * @return int
   */
  int groupAckUnreadCount();

  /**
   * @brief 设置消息未读groupAckCount数目(SDK 内部调用接口，上层不应该调用)
   * @param count 设置群消息未读数目
   */
  void setGroupAckUnreadCount(int count);

  /**
   * @brief 群消息是否全部已读
   * @return bool
   */
  bool groupAckReadAll();

  /**
   * @brief 设置消息的扩散优先级，默认为0。0表示扩散，数字越小扩散的越多。
   * @brief 取值范围0-10。普通人在聊天室发送的消息级别默认为5，可以丢弃。管理员默认为0不会丢弃。其它值可以根据业务自行设置。
   * @param priority 设置群消息未读数目
   */
  void setPriority(int priority);

  /**
   * @brief 消息的扩散优先级
   * @return int
   */
  int priority();

public:
  /**
   * @brief 创建发送文本消息
   * @param from 消息发送者
   * @param to 消息接收者
   * @param type 消息类型
   * @param conversationId 会话id
   * @param content 消息内容
   **/
  static BMXMessagePtr createMessage(int64_t from, int64_t to, MessageType type, int64_t conversationId, const std::string& content);

  /**
   * @brief 创建发送附件消息
   * @param from 消息发送者
   * @param to 消息接收者
   * @param type 消息类型
   * @param conversationId 会话id
   * @param attachment 附件
   **/
  static BMXMessagePtr createMessage(int64_t from, int64_t to, MessageType type, int64_t conversationId, BMXMessageAttachmentPtr attachment);

  /**
   * @brief 创建发送命令消息(命令消息通过content字段或者extension字段存放命令信息)
   * @param from 消息发送者
   * @param to 消息接收者
   * @param type 消息类型
   * @param conversationId 会话id
   * @param content 消息内容
   **/
  static BMXMessagePtr createCommandMessage(int64_t from, int64_t to, MessageType type, int64_t conversationId, const std::string& content);

  /**
   * @brief 创建收到的消息
   * @param msgId 消息id
   * @param from 消息发送者
   * @param to 消息接收者
   * @param type 消息类型
   * @param conversationId 会话id
   * @param content 消息内容
   * @param serverTimestamp 服务器时间戳
   **/
  static BMXMessagePtr createMessage(int64_t msgId, int64_t from, int64_t to, MessageType type, int64_t conversationId, const std::string& content, int64_t serverTimestamp);

  /**
   * @brief 创建收到的消息
   * @param msgId 消息id
   * @param from 消息发送者
   * @param to 消息接收者
   * @param type 消息类型
   * @param conversationId 会话id
   * @param attachment 附件
   * @param serverTimestamp 服务器时间戳
   **/
  static BMXMessagePtr createMessage(int64_t msgId, int64_t from, int64_t to, MessageType type, int64_t conversationId, BMXMessageAttachmentPtr attachment, int64_t serverTimestamp);

  /**
   * @brief 创建收到的命令消息(命令消息通过content字段或者extension字段存放命令信息)
   * @param msgId 消息id
   * @param from 消息发送者
   * @param to 消息接收者
   * @param type 消息类型
   * @param conversationId 会话id
   * @param content 消息内容
   * @param serverTimestamp 服务器时间戳
   **/
  static BMXMessagePtr createCommandMessage(int64_t msgId, int64_t from, int64_t to, MessageType type, int64_t conversationId, const std::string& content, int64_t serverTimestamp);

  /**
   * @brief 创建转发消息
   * @param msg 要转发的消息
   * @param from 消息发送者
   * @param to 消息接收者
   * @param type 消息类型
   * @param conversationId 会话id
   **/
  static BMXMessagePtr createForwardMessage(BMXMessagePtr msg, int64_t from, int64_t to, MessageType type, int64_t conversationId);

private:
  BMXMessage(const BMXMessage&);
  BMXMessage(const BMXMessage&&);
  BMXMessage& operator=(const BMXMessage&);
  BMXMessage(int64_t msgId, int64_t from, int64_t to, MessageType type, int64_t conversationId, ContentType contentType);
  friend BMXChatService;

  int64_t mMsgId;
  int64_t mClientMsgId;
  int64_t mFromId;
  int64_t mToId;
  MessageType mType;
  int64_t mConversationId;
  DeliveryStatus mDeliveryStatus;
  int64_t mClientTimestamp;
  int64_t mServerTimestamp;
  bool mIsPlayed;
  bool mIsReceiveMsg;
  bool mIsRead;
  bool mIsReadAcked;
  bool mIsDeliveryAcked;
  std::string mContent;
  ContentType mContentType;
  BMXMessageAttachmentPtr mAttachment;
  BMXMessageConfigPtr mConfig;
  JSON mExtension;
  DeliveryQos mDeliveryQos;
  std::string mSenderName;
  bool mIsGroupAck;
  int mGroupAckCount;
  int mGroupAckUnreadCount;
  int mPriority;
  std::recursive_mutex mMutex;
};

}
#endif /* __bmx_message_h__ */
