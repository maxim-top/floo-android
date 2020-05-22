//-------------------------------------------------------------------------------------------------
// File    : bmx_device.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : device implementation defines file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_device_h__
#define bmx_device_h__

#include <vector>
#include "bmx_base_object.h"

namespace floo {

/**
 * @brief 设备信息
 **/
class EXPORT_API BMXDevice : public BMXBaseObject {
public:

  /**
   * @brief 析构函数
   **/
  virtual ~BMXDevice() {}

  /**
   * @brief 设备序列号
   * @return int
   **/
  virtual int deviceSN() = 0;

  /**
   * @brief 用户id
   * @return int64_t
   **/
  virtual int64_t userId() = 0;

  /**
   * @brief 软件平台
   * @return int
   **/
  virtual int platform() = 0;

  /**
   * @brief 用户代理信息
   * @return std::string
   **/
  virtual std::string userAgent() = 0;

  /**
   * @brief 设置用户代理信息
   * @param userAgent 用户代理信息
   **/
  virtual void setUserAgent(const std::string& userAgent) = 0;

  /**
   * @brief 是否是当前设备
   * @return bool
   **/
  virtual bool isCurrentDevice() = 0;

protected:
  BMXDevice() {}

private:
  BMXDevice(const BMXDevice&);
  BMXDevice& operator=(const BMXDevice&);  
};

typedef std::shared_ptr<BMXDevice> BMXDevicePtr;
typedef std::vector<BMXDevicePtr> BMXDeviceList;

}

#endif //__bmx_device_h__
