//-------------------------------------------------------------------------------------------------
// File    : bmx_result_page.h
// Author  : Eric Liang <eric@bmxlabs.com>
// Purpose : bmx result page define file.
// Created : 12 Nov 2018 by Eric Liang <eric@bmxlabs.com>
//-------------------------------------------------------------------------------------------------
//
//                    Copyright (C) 2018-2019   MaxIM.Top
//
// You may obtain a copy of the licence at http://www.maxim.top/LICENCE-MAXIM.md
//
//-------------------------------------------------------------------------------------------------

#ifndef bmx_result_page_h
#define bmx_result_page_h

#include <vector>
#include <string>

#include "bmx_base_object.h"
#include "bmx_group.h"

namespace floo
{

/**
 * @brief 分页结果
 **/
template<typename T>
class EXPORT_API BMXResultPage : public BMXBaseObject
{
public:
  /**
   * @brief 构造函数
   **/
  BMXResultPage() : mOffset(-1) {}

  /**
   * @brief 构造函数
   * @param result 列表数据
   * @param offset 偏移量
   **/
  BMXResultPage(const std::vector<T> &result, int64_t offset) :
    mResult(result), mOffset(offset) {}

  /**
   * @brief 构造函数
   * @param result 列表结果
   * @param cursor cursor偏移量
   **/
  BMXResultPage(const std::vector<T> &result, std::string cursor) :
    mResult(result), mCursor(cursor), mOffset(-1) {}

  /**
   * @brief 构造函数
   * @param from BMXResultPage对象
   **/
  BMXResultPage(const BMXResultPage& from) :
    mResult(from.mResult), mCursor(from.mCursor), mOffset(from.mOffset) {}

  /**
   * @brief 构造函数
   * @param from BMXResultPage对象
   **/
  BMXResultPage(BMXResultPage&& from) :
    mResult(std::move(from.mResult)), mCursor(from.mCursor), mOffset(from.mOffset) {}

  /**
   * @brief 赋值函数
   * @param from BMXResultPage对象
   * @return BMXResultPage
   **/
  BMXResultPage& operator=(const BMXResultPage& from) {
    if (this != &from) {
      mResult = from.mResult;
      mCursor = from.mCursor;
      mOffset = from.mOffset;
    }
    return *this;
  }

  /**
   * @brief 析构函数
   **/
  virtual ~BMXResultPage() {};

  /**
   * @brief vector对象数组大小
   * @return size_t
   **/
  size_t count() const { return mResult.size(); }

  /**
   * @brief 偏移量
   * @return int64_t
   **/
  int64_t offset() const { return mOffset; }

  /**
   * @brief cursor偏移量
   * @return std::string
   **/
  const std::string& cursor() const { return mCursor; }

  /**
   * @brief vector对象数组
   * @return std::vector<T>
   **/
  const std::vector<T>& result() const { return mResult; }

private:
  std::vector<T> mResult;
  std::string mCursor;
  int64_t mOffset;
};

typedef BMXResultPage<BMXGroup::MemberPtr> BMXGroupMemberResultPage;
typedef std::shared_ptr<BMXGroupMemberResultPage> BMXGroupMemberResultPagePtr;

typedef BMXResultPage<BMXGroup::BannedMemberPtr> BMXGroupBannedMemberResultPage;
typedef std::shared_ptr<BMXGroupBannedMemberResultPage> BMXGroupBannedMemberResultPagePtr;

typedef BMXResultPage<BMXGroup::InvitationPtr> BMXGroupInvitationPage;
typedef std::shared_ptr<BMXGroupInvitationPage> BMXGroupInvitationPagePtr;

typedef BMXResultPage<BMXGroup::ApplicationPtr> BMXGroupApplicationPage;
typedef std::shared_ptr<BMXGroupApplicationPage> BMXGroupApplicationPagePtr;

}

#endif /* bmx_result_page_h */
