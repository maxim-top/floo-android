/usr/local/bin/swig -debug-classes -debug-module 4 -debug-typemap -c++ -java -package im.floo.floolib -outdir src/main/java/im/floo/floolib/ -o src/main/cpp/floo_wrap.cxx -Ifloo/include -Ifloo/src swig/floo.i
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__\(.*\)_t/\1/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__\(.*\)_t/\1/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/BMXGroupMemberResultPagePtr/floo::BMXGroupMemberResultPagePtr/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/BMXPushUserProfilePtr/floo::BMXPushUserProfilePtr/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/BMXGroupBannedMemberResultPagePtr/floo::BMXGroupBannedMemberResultPagePtr/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/< BMXGroup::MemberPtr >/< floo::BMXGroup::MemberPtr >/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/< BMXGroup::BannedMemberPtr >/< floo::BMXGroup::BannedMemberPtr >/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/BMXGroupInvitationPagePtr/floo::BMXGroupInvitationPagePtr/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/BMXGroupApplicationPagePtr/floo::BMXGroupApplicationPagePtr/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/SWIGTYPE_p_BMXGroupMemberResultPagePtr/BMXGroupMemberResultPage/g' src/main/java/im/floo/floolib/BMXGroupService.java
sed -i.bak 's/SWIGTYPE_p_BMXPushUserProfilePtr/BMXPushUserProfile/g' src/main/java/im/floo/floolib/BMXPushService.java
sed -i.bak 's/SWIGTYPE_p_BMXGroupBannedMemberResultPagePtr/BMXGroupBannedMemberResultPage/g' src/main/java/im/floo/floolib/BMXGroupService.java
sed -i.bak 's/SWIGTYPE_p_BMXGroupApplicationPagePtr/GroupApplicationPage/g' src/main/java/im/floo/floolib/BMXGroupService.java
sed -i.bak 's/SWIGTYPE_p_BMXGroupInvitaionPagePtr/GroupInvitaionPage/g' src/main/java/im/floo/floolib/BMXGroupService.java
sed -i.bak 's/BMXGroup_t/BMXGroup/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/BMXRTCRoom_t/BMXRTCRoom/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/BMXRTCSession_t/BMXRTCSession/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/BMXConversation_t/BMXConversation/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXGroup__Announcement/BMXGroup$Announcement/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXGroup__SharedFile/BMXGroup$SharedFile/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXRTCRoom/BMXRTCRoom/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXMessage/BMXMessage/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXRoomSDPInfo/BMXRoomSDPInfo/g' src/main/cpp/floo_wrap.cxx
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXGroup__Announcement/BMXGroup.Announcement/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXGroup__SharedFile/BMXGroup.SharedFile/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXRTCRoom/BMXRTCRoom/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXMessage/BMXMessage/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/SWIGTYPE_p_std__shared_ptrT_floo__BMXRoomSDPInfo/BMXRoomSDPInfo/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/BMXGroup_t/BMXGroup/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/BMXRTCRoom_t/BMXRTCRoom/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/BMXRTCSession_t/BMXRTCSession/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/BMXConversation_t/BMXConversation/g' src/main/java/im/floo/floolib/flooJNI.java
sed -i.bak 's/private transient boolean swigCMemOwn/protected transient boolean swigCMemOwn/g' src/main/java/im/floo/floolib/BMXBaseObject.java
awk 'BEGIN{i=0}{if(/Java_im_floo_floolib_flooJNI_delete_1BMXMessagePage/){i++;}if(i>0){i++;}if(i==4){print "  std::shared_ptr< floo::BMXMessage > *smartarg1 = 0 ;"}if(i==7){print "  smartarg1 = *(std::shared_ptr<  floo::BMXMessage > **)&jarg1;"} print $0;}'  src/main/cpp/floo_wrap.cxx  > ttt
mv ttt src/main/cpp/floo_wrap.cxx
python resultpage_rep.py src/main/cpp/floo_wrap.cxx > ttt
mv ttt src/main/cpp/floo_wrap.cxx
find . -name "*.bak" |xargs rm -f
