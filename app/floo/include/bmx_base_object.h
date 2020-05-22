//-------------------------------------------------------------------------------------------------
// File    : bmx_base_object.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx base object define.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_base_object_h_
#define bmx_base_object_h_

#include <memory>
#include <string>
#include "bmx_defines.h"
#include "bmx_error.h"
namespace floo
{

typedef std::string JSON;

class EXPORT_API BMXBaseObject
{
public:
  virtual ~BMXBaseObject() {}

  template <typename T>
  T* cast() { return dynamic_cast<T*>(this); }
};

typedef std::shared_ptr<BMXBaseObject> BMXBaseObjectPtr;

}

#endif /* bmx_base_object_h_ */
