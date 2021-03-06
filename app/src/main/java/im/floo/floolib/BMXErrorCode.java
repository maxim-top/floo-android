/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public enum BMXErrorCode {
  NoError,
  GeneralError,
  InvalidParam,
  NotFound,
  UserNotLogin,
  UserAlreadyLogin,
  UserAuthFailed,
  UserPermissionDenied,
  UserNotExist,
  UserAlreadyExist,
  UserFrozen,
  UserBanned,
  UserRemoved,
  UserTooManyDevice,
  UserPasswordChanged,
  UserKickedBySameDevice,
  UserKickedByOtherDevices,
  UserAbnormal,
  UserCancel,
  UserOldPasswordNotMatch,
  PushTokenInvalid,
  PushAliasBindByOtherUser,
  PushAliasTokenNotMatch,
  InvalidVerificationCode,
  InvalidRequestParameter,
  InvalidUserNameParameter,
  MissingAccessToken,
  CurrentUserIsInRoster,
  CurrentUserIsInBlocklist,
  AnswerFailed,
  InvalidToken,
  InvalidFileSign,
  InvalidFileObjectType,
  InvalidFileUploadToType,
  InvalidFileDownloadUrl,
  RosterNotFriend,
  RosterBlockListExist,
  RosterRejectApplication,
  GroupServerDbError,
  GroupNotExist,
  GroupNotMemberFound,
  GroupMsgNotifyTypeUnknown,
  GroupOwnerCannotLeave,
  GroupTransferNotAllowed,
  GroupRecoveryMode,
  GroupExceedLimitGlobal,
  GroupExceedLimitUserCreate,
  GroupExceedLimitUserJoin,
  GroupCapacityExceedLimit,
  GroupMemberPermissionRequired,
  GroupAdminPermissionRequired,
  GroupOwnerPermissionRequired,
  GroupApplicationExpiredOrHandled,
  GroupInvitationExpiredOrHandled,
  GroupKickTooManyTimes,
  GroupMemberExist,
  GroupBlockListExist,
  GroupAnnouncementNotFound,
  GroupAnnouncementForbidden,
  GroupSharedFileNotFound,
  GroupSharedFileOperateNotAllowed,
  GroupMemberBanned,
  SignInCancelled,
  SignInTimeout,
  SignInFailed,
  DbOperationFailed,
  MessageInvalid,
  MessageOutRecallTime,
  MessageRecallDisabled,
  MessageCensored,
  MessageInvalidType,
  ServerNotReachable,
  ServerUnknownError,
  ServerInvalid,
  ServerDecryptionFailed,
  ServerEncryptMethodUnsupported,
  ServerBusy,
  ServerNeedRetry,
  ServerTimeOut,
  ServerConnectFailed,
  ServerDNSFailed,
  ServerNeedReconnected,
  ServerFileUploadUnknownError,
  ServerFileDownloadUnknownError,
  ServerInvalidLicense,
  ServerLicenseLimit,
  ServerAppFrozen,
  ServerTooManyRequest,
  ServerNotAllowOpenRegister,
  ServerFireplaceUnknownError,
  ServerResponseInvalid,
  ServerInvalidUploadUrl,
  ServerAppLicenseInvalid,
  ServerAppLicenseExpired,
  ServerAppLicenseExceedLimit,
  ServerAppIdMissing,
  ServerAppIdInvalid,
  ServerAppSignInvalid,
  ServerAppNotifierNotExist,
  ServerNoClusterInfoForClusterId,
  ServerFileDownloadFailure,
  ServerAppStatusNotNormal;

  public final int swigValue() {
    return swigValue;
  }

  public static BMXErrorCode swigToEnum(int swigValue) {
    BMXErrorCode[] swigValues = BMXErrorCode.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (BMXErrorCode swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + BMXErrorCode.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private BMXErrorCode() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private BMXErrorCode(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private BMXErrorCode(BMXErrorCode swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}

