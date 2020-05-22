//-------------------------------------------------------------------------------------------------
// File    : bmx_message_attachment.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx message attachment define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_message_attachment_h
#define bmx_message_attachment_h

#include <string>
#include <memory>
#include "bmx_base_object.h"

namespace floo {

/**
 * @brief 消息附件
 **/
class EXPORT_API BMXMessageAttachment : public BMXBaseObject
{
public:

  /**
   * @brief 附件类型
   **/
  enum class Type
  {
    /// 图片
    Image = 1,
    /// 语音
    Voice,
    /// 视频片段
    Video,
    /// 文件
    File,
    /// 位置
    Location,
    /// 命令消息
    Command,
    /// 转发消息
    Forward,
  };

  /**
   * @brief 附件下载状态
   **/
  enum class DownloadStatus
  {
    /// 下载中
    Downloaing,
    /// 下载成功
    Successed,
    /// 下载失败
    Failed,
    /// 下载尚未开始
    NotStart,
    /// 下载被取消
    Canceled,
  };

  /**
   * @brief 图片/视频大小
   **/
  struct Size
  {
    Size(double width = 0.0, double height = 0.0) : mWidth(width), mHeight(height) {}
    double mWidth;
    double mHeight;
  };

  /**
   * @brief 构造函数
   **/
  BMXMessageAttachment() {}

  /**
   * @brief 析构函数
   **/
  virtual ~BMXMessageAttachment() {}

  /**
   * @brief 附件类型
   * @return Type
   **/
  virtual Type type() const = 0;

  /**
   * @brief 复制附件
   * @return BMXMessageAttachmentPtr
   **/
  virtual std::shared_ptr<BMXMessageAttachment> clone() const = 0;

private:
  BMXMessageAttachment& operator=(const BMXMessageAttachment&);
};

typedef std::shared_ptr<BMXMessageAttachment> BMXMessageAttachmentPtr;

}

#endif /* bmx_message_attachment_h */
