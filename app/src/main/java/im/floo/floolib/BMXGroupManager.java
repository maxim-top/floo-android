package im.floo.floolib;

import im.floo.AsyncExecutor;
import im.floo.BMXCallBack;
import im.floo.BMXDataCallBack;

/**
 *  群组管理器
 **/

public class BMXGroupManager {

    private BMXGroupService mService;

    public BMXGroupManager(BMXGroupService service) {
        mService = service;
    }

    /**
     *  获取群组列表，如果设置了forceRefresh则从服务器拉取
     * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack  BMXErrorCode,群组id列表
     **/
    public void getGroupList( final  boolean forceRefresh,final BMXDataCallBack<BMXGroupList> callBack) {
        final BMXGroupList list = new BMXGroupList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.search(list, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, list);
            }
        });
    }

    /**
     *  获取传入群组id的群组信息列表，如果设置了forceRefresh则从服务器拉取
     * @param groupIdList 群组id列表
     * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack  BMXErrorCode,群组详细信息列表
     **/
    public void getGroupList(final ListOfLongLong groupIdList,
                       final boolean forceRefresh,final BMXDataCallBack<BMXGroupList> callBack) {
        final  BMXGroupList list = new BMXGroupList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.search(groupIdList, list, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, list);
            }
        });
    }

    /**
     *  获取群信息，如果设置了forceRefresh则从服务器拉取
     * @param groupId 要搜索的群组id
     * @param forceUpdate 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack BMXErrorCode,搜索返回的群组信息
     **/
    public void getGroupInfo(final long groupId, final  boolean forceUpdate,final BMXDataCallBack<BMXGroup> callBack) {
        final BMXGroup group = new BMXGroup();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.search(groupId, group, forceUpdate);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, group);
            }
        });
    }

    /**
     *  分页获取群组邀请列表
     * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
     * @param pageSize 分页大小
     * @param callBack BMXErrorCode,分页获取的群组邀请列表
     **/
    public void getInvitationList(final  String cursor, final  int pageSize,final BMXDataCallBack<GroupInvitaionPage> callBack) {
        final GroupInvitaionPage result = new GroupInvitaionPage();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getInvitationList(result, cursor, pageSize);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, result);
            }
        });
    }

    /**
     *  分页获取群组申请列表
     * @param list 需要获取群组申请列表信息的群组id列表
     * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
     * @param pageSize 分页大小
     * @param callBack BMXErrorCode,分页获取的群组申请列表
     **/
    public void getApplicationList(final BMXGroupList list,
                                   final String cursor, final  int pageSize,final BMXDataCallBack<GroupApplicationPage> callBack) {
        final GroupApplicationPage result = new GroupApplicationPage();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getApplicationList(list, result, cursor, pageSize);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, result);
            }
        });
    }

    /**
     *  创建群
     * @param options 创建群组时传入的参数选项
     * @param callBack BMXErrorCode,创建好的群
     **/
    public void create(final BMXGroupService.CreateGroupOptions options, final BMXDataCallBack<BMXGroup> callBack) {
        final  BMXGroup group = new BMXGroup();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.create(options, group);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, group);
            }
        });
    }

    /**
     *  销毁群
     * @param callBack BMXErrorCode，要销毁的群组
     **/
    public void destroy(final BMXGroup group,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.destroy(group);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  加入一个群，根据群设置可能需要管理员批准
     * @param group 要加入的群组
     * @param message 申请入群的信息
     * @param callBack BMXErrorCode
     **/
    public void join(final BMXGroup group, final  String message,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.join(group, message);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  退出群
     * @param group 要退出的群组
     * @param callBack BMXErrorCode
     **/
    public void leave(final BMXGroup group,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.leave(group);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  获取群详情，从服务端拉取最新信息
     * @param callBack BMXErrorCode,要获取群组最新信息的群组
     **/
    public void getInfo(final BMXGroup group,final BMXDataCallBack<BMXGroup> callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getInfo(group);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, group);
            }
        });
    }

    /**
     *  获取群成员列表，如果设置了forceRefresh则从服务器拉取，最多拉取1000人
     * @param group 进行操作的群组
     * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
     * @param pageSize 分页大小
     * @param callBack BMXErrorCode,群成员列表
     **/
    public void getMembers(final BMXGroup group, final String cursor, final int pageSize, final BMXDataCallBack<BMXGroupMemberResultPage> callBack) {
        final  BMXGroupMemberResultPage page = new BMXGroupMemberResultPage();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getMembers(group, page, cursor, pageSize);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, page);
            }
        });
    }

    /**
     *  获取群成员列表，如果设置了forceRefresh则从服务器拉取，最多拉取1000人
     * @param group 进行操作的群组
     * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack BMXErrorCode,群成员列表
     **/
    public void getMembers(final BMXGroup group, final  boolean forceRefresh,final BMXDataCallBack<BMXGroupMemberList> callBack) {
        final  BMXGroupMemberList list = new BMXGroupMemberList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getMembers(group, list, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, list);
            }
        });
    }

    /**
     *  添加群成员
     * @param group 进行操作的群组
     * @param members 要添加进群的成员id列表
     * @param message 添加成员原因信息
     * @param callBack BMXErrorCode
     **/
    public void addMembers(final BMXGroup group, final  ListOfLongLong members,
                           final  String message,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.addMembers(group, members, message);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  删除群成员
     * @param group 进行操作的群组
     * @param members 要删除的群组成员id列表
     * @param reason 删除的原因
     * @param callBack BMXErrorCode
     **/
    public void removeMembers(final BMXGroup group, final  ListOfLongLong members,
                              final  String reason,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.removeMembers(group, members, reason);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  添加管理员
     * @param group 进行操作的群组
     * @param admins 要添加为管理员的成员id列表
     * @param message 添加为管理员的原因
     * @param callBack BMXErrorCode
     **/
    public void addAdmins(final BMXGroup group, final  ListOfLongLong admins, final  String message,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.addAdmins(group, admins, message);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  删除管理员
     * @param group 进行操作的群组
     * @param admins 要从管理员移除的成员id列表
     * @param reason 要移除管理员的原因
     * @param callBack BMXErrorCode
     **/
    public void removeAdmins(final BMXGroup group, final  ListOfLongLong admins, final  String reason,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.removeAdmins(group, admins, reason);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  获取Admins列表，如果设置了forceRefresh则从服务器拉取
     * @param group 进行操作的群组
     * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack BMXErrorCode,群管理员列表
     **/
    public void getAdmins(final BMXGroup group, final  boolean forceRefresh,final BMXDataCallBack<BMXGroupMemberList> callBack) {
        final  BMXGroupMemberList list = new BMXGroupMemberList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getAdmins(group, list, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, list);
            }
        });
    }

    /**
     *  添加黑名单
     * @param group 进行操作的群组
     * @param members 要加入黑名单的群成员id列表
     * @param callBack BMXErrorCode
     **/
    public void blockMembers(final BMXGroup group, final  ListOfLongLong members,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.blockMembers(group, members);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  从黑名单删除
     * @param group 进行操作的群组
     * @param members 从黑名单移除的用户id列表
     * @param callBack  BMXErrorCode
     **/
    public void unblockMembers(final BMXGroup group, final  ListOfLongLong members,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.unblockMembers(group, members);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  获取黑名单
     * @param group 进行操作的群组
     * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
     * @param pageSize 分页大小
     * @param callBack BMXErrorCode,群黑名单列表
     **/
    public void getBlockList(final BMXGroup group, final String cursor, final int pageSize, final BMXDataCallBack<BMXGroupMemberResultPage> callBack) {
        final  BMXGroupMemberResultPage page = new BMXGroupMemberResultPage();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getBlockList(group, page, cursor, pageSize);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, page);
            }
        });
    }

    /**
     *  获取黑名单
     * @param group 进行操作的群组
     * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack BMXErrorCode,群黑名单列表
     **/
    public void getBlockList(final BMXGroup group, final  boolean forceRefresh,final BMXDataCallBack<BMXGroupMemberList> callBack) {
        final  BMXGroupMemberList list = new BMXGroupMemberList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getBlockList(group, list, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, list);
            }
        });
    }

    /**
     *  禁言
     * @param group 进行操作的群组
     * @param members 被禁言的群成员id列表
     * @param duration 禁言时长
     * @param reason 禁言原因
     * @param callBack BMXErrorCode
     **/
    public void banMembers(final BMXGroup group, final  ListOfLongLong members,
                           final  long duration, final  String reason,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.banMembers(group, members, duration, reason);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  全员禁言
     * @param group 进行操作的群组
     * @param duration 禁言时长
     * @param callBack BMXErrorCode
     **/
    public void banGroup(final BMXGroup group, final  long duration, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.banGroup(group, duration);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  解除禁言
     * @param group 进行操作的群组
     * @param members 被解除禁言的群成员id列表
     * @param callBack BMXErrorCode
     **/
    public void unbanMembers(final BMXGroup group, final  ListOfLongLong members,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.unbanMembers(group, members);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  解除全员禁言
     * @param group 进行操作的群组
     * @param callBack BMXErrorCode
     **/
    public void unbanGroup(final BMXGroup group, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.unbanGroup(group);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  获取禁言列表
     * @param group 进行操作的群组
     * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
     * @param pageSize 分页大小
     * @param callBack BMXErrorCode 群禁言列表
     **/
    public void getBannedMembers(final BMXGroup group, final String cursor, final int pageSize, final BMXDataCallBack<BMXGroupBannedMemberResultPage> callBack) {
        final  BMXGroupBannedMemberResultPage page = new BMXGroupBannedMemberResultPage();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getBannedMembers(group, page, cursor, pageSize);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, page);
            }
        });
    }

    /**
     *  获取禁言列表
     * @param group 进行操作的群组
     * @param callBack BMXErrorCode 群禁言列表
     **/
    public void getBannedMembers(final BMXGroup group, final BMXDataCallBack<BMXGroupBannedMemberList> callBack) {
        final  BMXGroupBannedMemberList list = new BMXGroupBannedMemberList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getBannedMembers(group, list);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, list);
            }
        });
    }

    /**
     *  设置是否屏蔽群消息
     * @param group 进行操作的群组
     * @param mode 群屏蔽的模式
     * @param callBack BMXErrorCode
     **/
    public void muteMessage(final BMXGroup group, final  BMXGroup.MsgMuteMode mode,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.muteMessage(group, mode);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  接受入群申请
     * @param group 进行操作的群组
     * @param applicantId 申请进群的用户id
     * @param callBack BMXErrorCode
     **/
    public void acceptApplication(final BMXGroup group, final  long applicantId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.acceptApplication(group, applicantId);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  拒绝入群申请
     * @param group 进行操作的群组
     * @param applicantId 申请进群的用户id
     * @param reason 拒绝的原因
     * @param callBack BMXErrorCode
     **/
    public void declineApplication(final BMXGroup group, final  long applicantId, final  String reason,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.declineApplication(group, applicantId, reason);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  接受入群邀请
     * @param group 进行操作的群组
     * @param inviter 邀请者id
     * @param callBack BMXErrorCode
     **/
    public void acceptInvitation(final BMXGroup group, final  long inviter,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.acceptInvitation(group, inviter);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  拒绝入群邀请
     * @param group 进行操作的群组
     * @param inviter 邀请者id
     * @param callBack  BMXErrorCode
     **/
    public void declineInvitation(final BMXGroup group, final  long inviter,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.declineInvitation(group, inviter);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  转移群主
     * @param group 进行操作的群组
     * @param newOwnerId 转让为新群主的用户id
     * @param callBack BMXErrorCode
     **/
    public void transferOwner(final BMXGroup group, final  long newOwnerId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.transferOwner(group, newOwnerId);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  添加群共享文件
     * @param group 进行操作的群组
     * @param filePath 文件的本地路径
     * @param displayName 文件的展示名
     * @param extensionName 文件的扩展名
     * @param listener 上传回调函数
     * @param callBack  BMXErrorCode
     **/
    public void uploadSharedFile(final BMXGroup group, final  String filePath,
                                 final  String displayName, final  String extensionName,
                                 final  FileProgressListener listener,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.uploadSharedFile(group, filePath, displayName, extensionName, listener);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  移除群共享文件
     * @param group 进行操作的群组
     * @param sharedFile 删除的群共享文件
     * @param callBack BMXErrorCode
     **/
    public void removeSharedFile(final BMXGroup group, final  BMXGroup.SharedFile sharedFile,
                                 final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.removeSharedFile(group, sharedFile);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  下载群共享文件
     * @param group 进行操作的群组
     * @param sharedFile 下载的群共享文件
     * @param listener 下载回调函数
     * @param callBack BMXErrorCode
     **/
    public void downloadSharedFile(final BMXGroup group, final  BMXGroup.SharedFile sharedFile,
                                   final  FileProgressListener listener,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.downloadSharedFile(group, sharedFile, listener);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  获取群共享文件列表
     * @param group 进行操作的群组
     * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack BMXErrorCode 群共享文件列表
     **/
    public void getSharedFilesList(final BMXGroup group, final  boolean forceRefresh,
                                   final BMXDataCallBack<BMXGroupSharedFileList> callBack) {
        final  BMXGroupSharedFileList list = new BMXGroupSharedFileList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getSharedFilesList(group, list, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, list);
            }
        });
    }

    /**
     *  修改群共享文件名称
     * @param group 进行操作的群组
     * @param sharedFile 进行更改的群共享文件
     * @param name 修改的群共享文件名称
     * @param callBack BMXErrorCode
     **/
    public void changeSharedFileName(final BMXGroup group, final  BMXGroup.SharedFile sharedFile,
                                     final  String name,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.changeSharedFileName(group, sharedFile, name);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  获取最新的群公告
     * @param group 进行操作的群组
     * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack BMXErrorCode 最新的群组公告
     **/
    public void getLatestAnnouncement(final BMXGroup group, final  boolean forceRefresh,
                                      final BMXDataCallBack<BMXGroup.Announcement> callBack) {
        final  BMXGroup.Announcement announcement = new BMXGroup.Announcement();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getLatestAnnouncement(group, announcement, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, announcement);
            }
        });
    }

    /**
     *  获取群公告列表
     * @param group 进行操作的群组
     * @param forceRefresh 设置为true强制从服务器获取，本地获取失败的情况sdk会自动从服务器获取
     * @param callBack BMXErrorCode, 群公告列表
     **/
    public void getAnnouncementList(final BMXGroup group, final  boolean forceRefresh,
                                    final BMXDataCallBack<BMXGroupAnnouncementList> callBack) {
        final  BMXGroupAnnouncementList list = new BMXGroupAnnouncementList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getAnnouncementList(group, list, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, list);
            }
        });
    }

    /**
     *  设置群公告
     * @param group 进行操作的群组
     * @param title 群公告的标题
     * @param content 群公告的内容
     * @param callBack BMXErrorCode
     **/
    public void editAnnouncement(final BMXGroup group, final  String title, final  String content,
                                 final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.editAnnouncement(group, title, content);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  删除群公告
     * @param group 进行操作的群组
     * @param announcementId 删除的群公告id
     * @param callBack BMXErrorCode
     **/
    public void deleteAnnouncement(final BMXGroup group, final  long announcementId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.deleteAnnouncement(group, announcementId);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置群名称
     * @param group 进行操作的群组
     * @param name 群组名称
     * @param callBack BMXErrorCode
     **/
    public void setName(final BMXGroup group, final  String name,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setName(group, name);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置群描述信息
     * @param group 进行操作的群组
     * @param description 群组描述
     * @param callBack BMXErrorCode
     **/
    public void setDescription(final BMXGroup group, final  String description,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setDescription(group, description);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置群扩展信息
     * @param group 进行操作的群组
     * @param extension 群组的扩展信息
     * @param callBack BMXErrorCode
     **/
    public void setExtension(final BMXGroup group, final  String extension,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setExtension(group, extension);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置在群里的昵称
     * @param group 进行操作的群组
     * @param nickname 用户在群组内的昵称
     * @param callBack BMXErrorCode
     **/
    public void setMyNickname(final BMXGroup group, final  String nickname,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setMyNickname(group, nickname);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置群消息通知模式
     * @param group 进行操作的群组
     * @param mode 群消息通知模式
     * @param callBack BMXErrorCode
     **/
    public void setMsgPushMode(final BMXGroup group, final  BMXGroup.MsgPushMode mode,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setMsgPushMode(group, mode);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置入群审批模式
     * @param group 进行操作的群组
     * @param mode 入群审批模式
     * @param callBack BMXErrorCode
     **/
    public void setJoinAuthMode(final BMXGroup group, final  BMXGroup.JoinAuthMode mode,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setJoinAuthMode(group, mode);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置邀请模式
     * @param group 进行操作的群组
     * @param mode 群组的邀请模式
     * @param callBack BMXErrorCode
     **/
    public void setInviteMode(final BMXGroup group, final  BMXGroup.InviteMode mode,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setInviteMode(group, mode);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置群头像
     * @param group 进行操作的群组
     * @param avatarPath 群头像文件的本地路径
     * @param listener 上传回调函数
     * @param callBack BMXErrorCode
     **/
    public void setAvatar(final BMXGroup group, final  String avatarPath,
                          final  FileProgressListener listener,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setAvatar(group, avatarPath, listener);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  下载群头像
     * @param group 进行操作的群组
     * @param listener 下载回调函数
     * @param callBack BMXErrorCode
     **/
    public void downloadAvatar(final BMXGroup group, final  FileProgressListener listener,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.downloadAvatar(group, false, listener);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     * 添加群组变化监听者
     * @param listener 群组变化监听者
     **/
    public void addGroupListener(BMXGroupServiceListener listener) {
        mService.addGroupListener(listener);
    }

    /**
     * 移除群组变化监听者
     * @param listener 群组变化监听者
     **/
    public void removeGroupListener(BMXGroupServiceListener listener) {
        mService.removeGroupListener(listener);
    }

    /**
     *  设置是否开启群消息已读功能
     * @param group 进行操作的群组
     * @param enable 是否开启
     * @param callBack BMXErrorCode
     **/
    public void setEnableReadAck(final BMXGroup group, final  boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setEnableReadAck(group, enable);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }

    /**
     *  设置是否隐藏群成员信息
     * @param group 进行操作的群组
     * @param hide 是否隐藏
     * @param callBack BMXErrorCode
     **/
    public void setHideMemberInfo(final BMXGroup group, final  boolean hide,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setHideMemberInfo(group, hide);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code);
            }
        });
    }
}
