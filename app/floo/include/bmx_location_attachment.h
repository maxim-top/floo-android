//-------------------------------------------------------------------------------------------------
// File    : bmx_location_attachment.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx message location attachment define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_location_attachment_h
#define bmx_location_attachment_h

#include "bmx_message_attachment.h"

namespace floo {

template <typename T> class Encoder;
template <typename T> class Decoder;

/**
 * @brief 位置消息附件
 **/
class EXPORT_API BMXLocationAttachment : public BMXMessageAttachment
{
public:
  /**
   * @brief 构造函数
   * @param latitude 纬度
   * @param longitude 经度
   * @param address 地址名称
   **/
  BMXLocationAttachment(double latitude, double longitude, const std::string& address);

  /**
   * @brief 析构函数
   **/
  virtual ~BMXLocationAttachment() {}

  /**
   * @brief 返回位置附件类型
   * @return Type
   **/
  virtual Type type() const { return Type::Location; }

  /**
   * @brief 克隆函数
   * @return BMXMessageAttachmentPtr
   **/
  virtual BMXMessageAttachmentPtr clone() const;

  /**
   * @brief 纬度
   * @return double
   **/
  double latitude() const;

  /**
   * @brief 经度
   * @return double
   **/
  double longitude() const;

  /**
   * @brief 地址
   * @return std::string
   **/
  const std::string& address() const;

private:
  double mLatitude;
  double mLongitude;
  std::string mAddress;
  friend class Encoder<BMXLocationAttachment>;
  friend class Decoder<BMXLocationAttachment>;
};

typedef std::shared_ptr<BMXLocationAttachment> BMXLocationAttachmentPtr;
}

#endif /* bmx_location_attachment_h */
