ver=`grep '^[ \t]*versionName' ../app/build.gradle|sed 's/[^"]*"\(.*\)"/\1/g'`
out_dir="floo-android-api-"$ver
javadoc -d $out_dir  -windowtitle "floo-android api" -encoding utf-8 -charset utf-8 \
../app/src/main/java/im/floo/floolib/BMXChatService.java \
../app/src/main/java/im/floo/floolib/BMXChatServiceListener.java \
../app/src/main/java/im/floo/floolib/BMXClient.java \
../app/src/main/java/im/floo/floolib/BMXClientType.java \
../app/src/main/java/im/floo/floolib/BMXConnectStatus.java \
../app/src/main/java/im/floo/floolib/BMXConversation.java \
../app/src/main/java/im/floo/floolib/BMXDevice.java \
../app/src/main/java/im/floo/floolib/BMXFileAttachment.java \
../app/src/main/java/im/floo/floolib/BMXGroup.java \
../app/src/main/java/im/floo/floolib/BMXGroupService.java \
../app/src/main/java/im/floo/floolib/BMXGroupServiceListener.java \
../app/src/main/java/im/floo/floolib/BMXImageAttachment.java \
../app/src/main/java/im/floo/floolib/BMXLocationAttachment.java \
../app/src/main/java/im/floo/floolib/BMXLogLevel.java \
../app/src/main/java/im/floo/floolib/BMXMessage.java \
../app/src/main/java/im/floo/floolib/BMXMessageAttachment.java \
../app/src/main/java/im/floo/floolib/BMXMessageConfig.java \
../app/src/main/java/im/floo/floolib/BMXNetworkListener.java \
../app/src/main/java/im/floo/floolib/BMXNetworkType.java \
../app/src/main/java/im/floo/floolib/BMXRosterItem.java \
../app/src/main/java/im/floo/floolib/BMXRosterService.java \
../app/src/main/java/im/floo/floolib/BMXRosterServiceListener.java \
../app/src/main/java/im/floo/floolib/BMXSDKConfig.java \
../app/src/main/java/im/floo/floolib/BMXSignInStatus.java \
../app/src/main/java/im/floo/floolib/BMXUserProfile.java \
../app/src/main/java/im/floo/floolib/BMXUserService.java \
../app/src/main/java/im/floo/floolib/BMXUserServiceListener.java \
../app/src/main/java/im/floo/floolib/BMXVideoAttachment.java \
../app/src/main/java/im/floo/floolib/BMXVoiceAttachment.java \
../app/src/main/java/im/floo/floolib/BMXUserManager.java \
../app/src/main/java/im/floo/floolib/BMXGroupManager.java \
../app/src/main/java/im/floo/floolib/BMXRosterManager.java \
../app/src/main/java/im/floo/floolib/BMXChatManager.java
cp -r resources $out_dir
