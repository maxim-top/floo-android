//-------------------------------------------------------------------------------------------------
// File    : bmx_network_listener.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx network listener define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_network_listener_h
#define bmx_network_listener_h

#include "bmx_defines.h"

namespace floo
{

class EXPORT_API BMXNetworkListener
{
public:
  virtual ~BMXNetworkListener() {}
  virtual void onNetworkChanged(BMXNetworkType type, bool reconnect) = 0;
};

}

#endif /* bmx_network_listener_h */
