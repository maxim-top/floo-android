//-------------------------------------------------------------------------------------------------
// File    : bmx_video_attachment.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx video attachment define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_video_attachment_h
#define bmx_video_attachment_h

#include "bmx_file_attachment.h"

namespace floo {

template <typename T> class Encoder;
template <typename T> class Decoder;

/**
 * @brief 视频消息附件
 **/
class EXPORT_API BMXVideoAttachment : public BMXFileAttachment
{
public:

  /**
   * @brief 构造函数，构建发送视频消息附件
   * @param path 文件的本地路径
   * @param duration 视频片段时长
   * @param size 视频大小，宽度和高度
   * @param displayName 文件展示名
   **/
  BMXVideoAttachment(const std::string& path, int duration, const Size& size, const std::string& displayName = "");

  /**
   * @brief 构造函数，构建发送视频消息附件
   * @param path 文件的本地路径
   * @param thumbnailPath 缩略图文件的本地路径
   * @param duration 视频片段时长
   * @param size 视频大小，宽度和高度
   * @param displayName 文件展示名
   **/
  BMXVideoAttachment(const std::string& path, const std::string &thumbnailPath, int duration, const Size& size, const std::string& displayName = "");

  /**
   * @brief 构造函数，构建接收视频消息附件
   * @param url 文件服务器地址
   * @param duration 视频片段时长
   * @param size 视频大小，宽度和高度
   * @param displayName 文件展示名
   * @param fileLength 文件大小
   **/
  BMXVideoAttachment(const std::string &url, int duration, const Size& size, const std::string& displayName, int64_t fileLength);

  /**
   * @brief 构造函数，构建接收视频消息附件
   * @param url 文件服务器地址
   * @param thumbnailUrl 缩略图文件服务器地址
   * @param duration 视频片段时长
   * @param size 视频大小，宽度和高度
   * @param displayName 文件展示名
   * @param fileLength 文件大小
   **/
  BMXVideoAttachment(const std::string &url, const std::string &thumbnailUrl, int duration, const Size& size, const std::string& displayName, int64_t fileLength);

  /**
   * @brief 析构函数
   */
  virtual ~BMXVideoAttachment() {}

  /**
   * @brief 返回文件类型
   * @return Type
   **/
  virtual Type type() const { return Type::Video; }

  /**
   * @brief 克隆函数
   * @return BMXMessageAttachmentPtr
   **/
  virtual BMXMessageAttachmentPtr clone() const;

  /**
   * @brief 视频大小，宽度和高度
   * @return Size
   **/
  const Size& size() const;

  /**
   * @brief 视频片段时长
   * @return int32_t
   **/
  int32_t duration() const;

  /**
   * @brief 设置发送视频片段消息缩略图
   * @param path 视频片段消息缩略图
   **/
  void setThumbnail(const std::string& path);

  /**
   * @brief 缩略图本地路径
   * @return std::string
   **/
  const std::string& thumbnailPath() const;

  /**
   * @brief 设置发送视频片段消息缩略图服务器路径
   * @param thumbnailUrl 视频片段消息缩略图服务器路径
   **/
  void setThumbnailUrl(const std::string& thumbnailUrl);

  /**
   * @brief 缩略图服务器路径
   * @return std::string
   **/
  const std::string& thumbnailUrl() const;

  /**
   * @brief 缩略图下载状态
   * @return DownloadStatus
   **/
  DownloadStatus thumbnailDownloadStatus() const;

private:
  BMXVideoAttachment(const BMXVideoAttachment&);
  BMXVideoAttachment& operator=(const BMXVideoAttachment&);

  //Video
  Size mSize;
  int mDuration;

  //Snapshot
  std::string mThumbnailPath;
  std::string mThumbnailUrl;
  DownloadStatus mThumbnailDownloadStatus;
  friend class Encoder<BMXVideoAttachment>;
  friend class Decoder<BMXVideoAttachment>;
};
typedef std::shared_ptr<BMXVideoAttachment> BMXVideoAttachmentPtr;
}

#endif /* bmx_video_attachment_h */
