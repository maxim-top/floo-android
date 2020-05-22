%module(directors="1") floo
%feature("director")  BMXChatServiceListener;
%feature("director")  BMXGroupServiceListener;
%feature("director")  BMXNetworkListener;
%feature("director")  BMXRosterServiceListener;
%feature("director")  BMXUserServiceListener;
%{
#include "bmx_error.h"
#include "bmx_defines.h"
#include "bmx_device.h"
#include "bmx_base_object.h"
#include "bmx_message_attachment.h"
#include "bmx_message_config.h"
#include "bmx_message.h"
#include "bmx_conversation.h"
#include "bmx_sdk_config.h"
#include "bmx_network_listener.h"
#include "bmx_chat_service.h"
#include "bmx_chat_service_listener.h"
#include "bmx_client.h"
#include "bmx_file_attachment.h"
#include "bmx_group.h"
#include "bmx_group_service.h"
#include "bmx_image_attachment.h"
#include "bmx_location_attachment.h"
#include "bmx_result_page.h"
#include "bmx_roster_item.h"
#include "bmx_roster_service.h"
#include "bmx_roster_service_listener.h"
#include "bmx_user_profile.h"
#include "user_profile_impl.h"
#include "bmx_user_service.h"
#include "bmx_user_service_listener.h"
#include "bmx_video_attachment.h"
#include "bmx_voice_attachment.h"
#include "bmx_group_service_listener.h"
#include <utility>
%}

%include "stdint.i"
%include "enums.swg"
%javaconst(1);


%typemap(out) int64_t * %{
  *(int64_t **)&$result = $1; /* cast C ptr into jlong */
%}

%include "std_shared_ptr.i"
%include "std_vector.i"
%include "std_string.i"
%shared_ptr(floo::BMXMessageConfig)
%shared_ptr(floo::BMXMessage)
%template(BMXMessageList) std::vector<std::shared_ptr<floo::BMXMessage>>;

typedef floo::BMXConversation::Type BMXConversationType;

%shared_ptr(floo::BMXConversation)
%template(BMXConversationList) std::vector<std::shared_ptr<floo::BMXConversation>>;

%shared_ptr(floo::BMXRosterItem)
%template(BMXRosterItemList) std::vector<std::shared_ptr<floo::BMXRosterItem>>;
%shared_ptr(floo::BMXDevice)
%template(BMXDeviceList) std::vector<std::shared_ptr<floo::BMXDevice>>;

%shared_ptr(floo::BMXImageAttachment)
%shared_ptr(floo::BMXLocationAttachment)
%shared_ptr(floo::BMXMessageAttachment)
%shared_ptr(floo::BMXNetworkListener)
%shared_ptr(floo::BMXClient)
%shared_ptr(floo::BMXBaseObject)
%shared_ptr(floo::BMXSDKConfig)
%shared_ptr(floo::BMXFileAttachment)
%shared_ptr(floo::BMXGroup)
%shared_ptr(floo::BMXGroup::Member)
%shared_ptr(floo::BMXGroup::BannedMember)
%shared_ptr(floo::BMXGroup::Announcement)
%shared_ptr(floo::BMXGroup::SharedFile)
%shared_ptr(floo::BMXRosterService::Application)
%shared_ptr(floo::BMXGroup::Application)
%shared_ptr(floo::BMXGroup::Invitation)
%shared_ptr(floo::BMXUserProfile)
%shared_ptr(floo::UserProfileImpl)
%shared_ptr(floo::BMXVoiceAttachment)
%shared_ptr(floo::BMXVideoAttachment)
%shared_ptr(floo::BMXResultPage<floo::BMXMessage>)

%template(BMXGroupList) std::vector<std::shared_ptr<floo::BMXGroup>>;
%template(BMXGroupMemberList) std::vector<std::shared_ptr<floo::BMXGroup::Member>>;
%template(BMXGroupBannedMemberList) std::vector<std::shared_ptr<floo::BMXGroup::BannedMember>>;
%template(BMXGroupSharedFileList) std::vector<std::shared_ptr<floo::BMXGroup::SharedFile>>;
%template(BMXGroupAnnouncementList) std::vector<std::shared_ptr<floo::BMXGroup::Announcement>>;
%template(BMXRosterServiceApplicationList) std::vector<std::shared_ptr<floo::BMXRosterService::Application>>;
%template(BMXGroupApplicationList) std::vector<std::shared_ptr<floo::BMXGroup::Application>>;
%template(BMXGroupInvitationList) std::vector<std::shared_ptr<floo::BMXGroup::Invitation>>;

%template(ListOfLongLong) std::vector<long long>;

%include "bmx_error.h"
%include "bmx_defines.h"
%include "bmx_device.h"
%include "bmx_base_object.h"
%include "bmx_message_attachment.h"
%include "bmx_message_config.h"
%include "bmx_message.h"
%include "bmx_conversation.h"
%include "bmx_sdk_config.h"
%include "bmx_network_listener.h"
%include "bmx_chat_service.h"
%include "bmx_chat_service_listener.h"
%include "bmx_client.h"
%include "bmx_file_attachment.h"
%exception floo::BMXFileAttachment::dynamic_cast(floo::BMXMessageAttachment *attachment) {
  $action
    if (!result) {
      jclass excep = jenv->FindClass("java/lang/ClassCastException");
      if (excep) {
        jenv->ThrowNew(excep, "dynamic_cast exception");
      }
    }
}
%extend floo::BMXFileAttachment {
  static floo::BMXFileAttachment *dynamic_cast(floo::BMXMessageAttachment *attachment) {
    return dynamic_cast<floo::BMXFileAttachment *>(attachment);
  }
};

%include "bmx_group.h"
%include "bmx_group_service.h"
%include "bmx_image_attachment.h"
%exception floo::BMXImageAttachment::dynamic_cast(floo::BMXMessageAttachment *attachment) {
  $action
    if (!result) {
      jclass excep = jenv->FindClass("java/lang/ClassCastException");
      if (excep) {
        jenv->ThrowNew(excep, "dynamic_cast exception");
      }
    }
}
%extend floo::BMXImageAttachment {
  static floo::BMXImageAttachment *dynamic_cast(floo::BMXMessageAttachment *attachment) {
    return dynamic_cast<floo::BMXImageAttachment *>(attachment);
  }
};

%include "bmx_location_attachment.h"
%exception floo::BMXLocationAttachment::dynamic_cast(floo::BMXMessageAttachment *attachment) {
  $action
    if (!result) {
      jclass excep = jenv->FindClass("java/lang/ClassCastException");
      if (excep) {
        jenv->ThrowNew(excep, "dynamic_cast exception");
      }
    }
}
%extend floo::BMXLocationAttachment {
  static floo::BMXLocationAttachment *dynamic_cast(floo::BMXMessageAttachment *attachment) {
    return dynamic_cast<floo::BMXLocationAttachment *>(attachment);
  }
};

%include "bmx_result_page.h"
%template(BMXMessagePage) floo::BMXResultPage<std::shared_ptr<floo::BMXMessage>>;
%template(BMXGroupMemberResultPage)  floo::BMXResultPage<BMXGroup::MemberPtr>;
%template(BMXGroupBannedMemberResultPage)  floo::BMXResultPage<BMXGroup::BannedMemberPtr>;
%shared_ptr(floo::BMXResultPage<BMXGroup::MemberPtr>)
%shared_ptr(floo::BMXResultPage<BMXGroup::BannedMemberPtr>)
%template(ApplicationPage) floo::BMXResultPage<std::shared_ptr<floo::BMXRosterService::Application>>;
%template(GroupApplicationPage) floo::BMXResultPage<std::shared_ptr<floo::BMXGroup::Application>>;
%template(GroupInvitaionPage) floo::BMXResultPage<std::shared_ptr<floo::BMXGroup::Invitation>>;
%shared_ptr(floo::BMXResultPage<std::shared_ptr<floo::BMXRosterService::Application>>)
%shared_ptr(floo::BMXResultPage<std::shared_ptr<floo::BMXGroup::Application>>)
%shared_ptr(floo::BMXResultPage<std::shared_ptr<floo::BMXGroup::Invitation>>)

%template(BMXMessageListList) std::vector<std::vector<std::shared_ptr<floo::BMXMessage>>>;
%include "bmx_roster_item.h"
%include "bmx_roster_service.h"
%include "bmx_roster_service_listener.h"
%include "bmx_user_profile.h"
%include "user_profile_impl.h"
%include "bmx_user_service.h"
%include "bmx_user_service_listener.h"
%include "bmx_video_attachment.h"
%exception floo::BMXVideoAttachment::dynamic_cast(floo::BMXMessageAttachment *attachment) {
  $action
    if (!result) {
      jclass excep = jenv->FindClass("java/lang/ClassCastException");
      if (excep) {
        jenv->ThrowNew(excep, "dynamic_cast exception");
      }
    }
}
%extend floo::BMXVideoAttachment {
  static floo::BMXVideoAttachment *dynamic_cast(floo::BMXMessageAttachment *attachment) {
    return dynamic_cast<floo::BMXVideoAttachment *>(attachment);
  }
};


%include "bmx_voice_attachment.h"
%exception floo::BMXVoiceAttachment::dynamic_cast(floo::BMXMessageAttachment *attachment) {
  $action
    if (!result) {
      jclass excep = jenv->FindClass("java/lang/ClassCastException");
      if (excep) {
        jenv->ThrowNew(excep, "dynamic_cast exception");
      }
    }
}
%extend floo::BMXVoiceAttachment {
  static floo::BMXVoiceAttachment *dynamic_cast(floo::BMXMessageAttachment *attachment) {
    return dynamic_cast<floo::BMXVoiceAttachment *>(attachment);
  }
};


%include "bmx_group_service_listener.h"
