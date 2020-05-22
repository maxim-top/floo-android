//-------------------------------------------------------------------------------------------------
// File    : bmx_voice_attachment.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx voice attachment define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_voice_attachment_h
#define bmx_voice_attachment_h

#include "bmx_file_attachment.h"

namespace floo {

template <typename T> class Encoder;
template <typename T> class Decoder;

/**
 * @brief 音频消息附件
 **/
class EXPORT_API BMXVoiceAttachment : public BMXFileAttachment
{
public:

  /**
   * @brief 构造函数，构建发送音频消息附件
   * @param path 文件的本地路径
   * @param duration 音频时长
   * @param displayName 文件展示名
   **/
  BMXVoiceAttachment(const std::string& path, int duration, const std::string& displayName = "");

  /**
   * @brief 构造函数，构建接收音频消息附件
   * @param url 文件服务器地址
   * @param duration 音频时长
   * @param displayName 文件展示名
   * @param fileLength 文件大小
   **/
  BMXVoiceAttachment(const std::string &url, int duration, const std::string& displayName, int64_t fileLength);

  /**
   * @brief 析构函数
   **/
  virtual ~BMXVoiceAttachment() {}

  /**
   * @brief 返回文件类型
   * @return Type
   **/
  virtual Type type() const { return Type::Voice; }

  /**
   * @brief 克隆函数
   * @return BMXMessageAttachmentPtr
   **/
  virtual BMXMessageAttachmentPtr clone() const;

  /**
   * @brief 语音时长
   * @return int32_t
   **/
  int32_t duration() const;

private:
  int32_t mDuration;
  friend class Encoder<BMXVoiceAttachment>;
  friend class Decoder<BMXVoiceAttachment>;
};
typedef std::shared_ptr<BMXVoiceAttachment> BMXVoiceAttachmentPtr;
}

#endif /* bmx_voice_attachment_h */
