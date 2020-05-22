//-------------------------------------------------------------------------------------------------
// File    : bmx_image_attachment.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx message image attachment define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_image_attachment_h
#define bmx_image_attachment_h

#include "bmx_file_attachment.h"

namespace floo {

template <typename T> class Encoder;
template <typename T> class Decoder;

/**
 * @brief 图片消息附件
 **/
class EXPORT_API BMXImageAttachment : public BMXFileAttachment
{
public:

  /**
   * @brief 构造函数，构建发送图片消息附件
   * @param path 本地路径
   * @param size 图片的大小，宽度和高度
   * @param displayName 展示名
   **/
  BMXImageAttachment(const std::string& path, const Size& size, const std::string& displayName = "");

  /**
   * @brief 构造函数，构建接收图片消息附件
   * @param url 图片服务器地址
   * @param size 图片的大小，宽度和高度
   * @param displayName 展示名
   * @param fileLength 文件大小
   **/
  BMXImageAttachment(const std::string& url, const Size& size, const std::string& displayName, int64_t fileLength);

  /**
   * @brief 析构函数
   **/
  virtual ~BMXImageAttachment() {}

  /**
   * @brief 返回图片附件类型
   * @return Type
   **/
  virtual Type type() const { return Type::Image; }

  /**
   * @brief 克隆函数
   * @return BMXMessageAttachmentPtr
   **/
  virtual BMXMessageAttachmentPtr clone() const;

  /**
   * @brief 图片大小
   * @return Size
   **/
  const Size& size() const;

  /**
   * @brief 设置发送图片消息缩略图
   * @param path 本地路径
   **/
  void setThumbnail(const std::string& path);

  /**
   * @brief 缩略图本地路径
   * @return std::string
   **/
  const std::string& thumbnailPath() const;

  /**
   * @brief 缩略图下载状态
   * @return DownloadStatus
   **/
  DownloadStatus thumbnailDownloadStatus() const;

private:
  BMXImageAttachment(const BMXImageAttachment&);
  BMXImageAttachment& operator=(const BMXImageAttachment&);
  //Image size
  Size mSize;

  //Thumbnail
  std::string mThumbnailPath;
  DownloadStatus mThumbnailDownloadStatus;
  friend class Encoder<BMXImageAttachment>;
  friend class Decoder<BMXImageAttachment>;
};
typedef std::shared_ptr<BMXImageAttachment> BMXImageAttachmentPtr;
}

#endif /* bmx_image_attachment_h */
