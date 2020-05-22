//-------------------------------------------------------------------------------------------------
// File    : bmx_file_attachment.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx message file attachment define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_file_attachment_h
#define bmx_file_attachment_h

#include "bmx_message_attachment.h"

namespace floo {

template <typename T> class Encoder;
template <typename T> class Decoder;

/**
 * @brief 消息文件附件
 **/
class EXPORT_API BMXFileAttachment : public BMXMessageAttachment
{
public:

  /**
   * @brief 构造函数，构建发送文件消息附件
   * @param path 文件的本地路径
   * @param displayName 文件展示名
   **/
  BMXFileAttachment(const std::string& path, const std::string& displayName = "");

  /**
   * @brief 构造函数，构建接收文件消息附件
   * @param url 文件服务器地址
   * @param displayName 文件展示名
   * @param fileLength 文件大小
   **/
  BMXFileAttachment(const std::string &url, const std::string& displayName, int64_t fileLength);

  /**
   * @brief 析构函数
   **/
  virtual ~BMXFileAttachment() {}

  /**
   * @brief 返回文件类型
   * @return Type
   **/
  virtual Type type() const { return Type::File; }

  /**
   * @brief 克隆函数
   * @return BMXMessageAttachmentPtr
   **/
  virtual BMXMessageAttachmentPtr clone() const;

  /**
   * @brief 本地路径
   * @return std::string
   **/
  const std::string& path() const;

  /**
   * @brief 显示名
   * @return std::string
   **/
  const std::string& displayName() const;

  /**
   * @brief 远程URL
   * @return std::string
   **/
  const std::string& url() const;

  /**
   * @brief 文件长度
   * @return std::string
   **/
  int64_t fileLength() const;

  /**
   * @brief 附件下载状态
   * @return DownloadStatus
   **/
  DownloadStatus downloadStatus() const;

protected:
  std::string mPath;
  std::string mDisplayName;
  std::string mUrl;
  int64_t mFileLength;
  DownloadStatus mDownloadStatus;

private:
  friend class Encoder<BMXFileAttachment>;
  friend class Decoder<BMXFileAttachment>;
};

typedef std::shared_ptr<BMXFileAttachment> BMXFileAttachmentPtr;
}

#endif /* bmx_file_attachment_h */
