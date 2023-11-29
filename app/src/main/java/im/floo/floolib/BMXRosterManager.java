package im.floo.floolib;

import im.floo.AsyncExecutor;
import im.floo.BMXCallBack;
import im.floo.BMXDataCallBack;

/**
 *  好友管理器
 **/

public class BMXRosterManager {

    private BMXRosterService mService;

    public BMXRosterManager(BMXRosterService service) {
        mService = service;
    }

    /**
     *  获取好友列表，如果forceRefresh == true，则强制从服务端拉取
     * @param forceRefresh 是否从服务器读取数据，true为强制从服务器获取，false情况下本地读取列表为空的情况下会自动从服务器读取
     * @param callBack BMXErrorCode 好友id列表
     **/
    public void get( final  boolean forceRefresh,final BMXDataCallBack<ListOfLongLong> callBack) {
        final ListOfLongLong listOfLongLong = new ListOfLongLong();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.get(listOfLongLong, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, listOfLongLong);
            }
        });
    }

    /**
     *  搜索用户
     * @param rosterId 搜索的好友id
     * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
     * @param callBack BMXErrorCode 查询返回的用户的信息
     **/
    public void search(final long rosterId, final  boolean forceRefresh, final BMXDataCallBack<BMXRosterItem> callBack) {
        final BMXRosterItem item = new BMXRosterItem();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.search(rosterId, forceRefresh, item);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, item);
            }
        });
    }

    /**
     *  搜索用户
     * @param name 搜索的用户名
     * @param forceRefresh 为true强制从服务器获取，为false情况下查询结果为空时自动从服务器获取。
     * @param callBack BMXErrorCode 查询返回的用户的信息
     **/
    public void search(final String name, final  boolean forceRefresh, final BMXDataCallBack<BMXRosterItem> callBack) {
        final  BMXRosterItem item = new BMXRosterItem();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.search(name, forceRefresh, item);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, item);
            }
        });
    }

    /**
     *  批量搜索用户
     * @param rosterIdList 需要搜索的用户id列表
     * @param forceRefresh 是否强制从服务器获取，为true则强制从服务器获取
     * @param callBack BMXErrorCode 返回的好友信息列表
     **/
    public void search(final ListOfLongLong rosterIdList,
                       final boolean forceRefresh,final BMXDataCallBack<BMXRosterItemList> callBack) {
        final  BMXRosterItemList list = new BMXRosterItemList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.search(rosterIdList, list, forceRefresh);
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
     *  更新好友本地扩展信息
     * @param item 用户信息
     * @param extension 本地扩展信息
     * @param callBack  BMXErrorCode
     **/
    public void setItemExtension(final BMXRosterItem item, final  String extension,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setItemExtension(item, extension);
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
     *  更新好友别名
     * @param item 用户信息
     * @param alias 好友别名
     * @param callBack BMXErrorCode
     **/
    public void setItemAlias(final BMXRosterItem item, final  String alias,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setItemAlias(item, alias);
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
     *  设置是否拒收用户消息
     * @param item 用户信息
     * @param status 是否拒收用户消息，true拒收，false不拒收
     * @param callBack  BMXErrorCode
     **/
    public void setItemMuteNotification(final BMXRosterItem item, final  boolean status,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setItemMuteNotification(item, status);
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
     *  申请添加好友
     * @param rosterId 申请添加的用户id
     * @param message 好友申请信息
     * @param callBack BMXErrorCode
     **/
    public void apply(final long rosterId, final  String message,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.apply(rosterId, message);
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
     *  申请添加好友
     * @param rosterId 申请添加的用户id
     * @param message 好友申请信息
     * @param authAnswer 好友验证问题
     * @param callBack BMXErrorCode
     **/
    public void apply(final long rosterId, final  String message, final  String authAnswer,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.apply(rosterId, message, authAnswer);
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
     *  删除好友
     * @param rosterId 删除的好友id
     * @param callBack BMXErrorCode
     **/
    public void remove(final long rosterId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.remove(rosterId);
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
     *  获取申请添加好友列表
     * @param cursor 分页获取的起始cursor，第一次传入为空，后续传入上次操作返回的result中的cursor
     * @param pageSize 分页大小
     * @param callBack BMXErrorCode 返回的申请好友列表信息
     **/
    public void getApplicationList(final  String cursor, final  int pageSize,final BMXDataCallBack<ApplicationPage> callBack) {
        final ApplicationPage result = new ApplicationPage();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getApplicationList(result, cursor, pageSize);
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
     *  接受加好友申请
     * @param rosterId 申请加为好友的用户id
     * @param callBack BMXErrorCode
     **/
    public void accept(final long rosterId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.accept(rosterId);
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
     *  拒绝加好友申请
     * @param rosterId 申请加为好友的用户id
     * @param reason 拒绝的原因
     * @param callBack BMXErrorCode
     **/
    public void decline(final long rosterId, final  String reason,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.decline(rosterId, reason);
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
     *  加入黑名单
     * @param rosterId 加入黑名单的用户id
     * @param callBack BMXErrorCode
     **/
    public void block(final long rosterId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.block(rosterId);
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
     *  从黑名单移除
     * @param rosterId 从黑名单移除的用户id
     * @param callBack BMXErrorCode
     **/
    public void unblock(final long rosterId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.unblock(rosterId);
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
     *  获取黑名单，如果forceRefresh == true，则强制从服务端拉取
     * @param forceRefresh 是否从服务器读取数据，true为强制从服务器获取，false情况下本地读取列表为空的情况下会自动从服务器读取
     * @param callBack BMXErrorCode 好友id列表
     **/
    public void getBlockList(final  boolean forceRefresh,final BMXDataCallBack<ListOfLongLong> callBack) {
        final ListOfLongLong listOfLongLong = new ListOfLongLong();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getBlockList(listOfLongLong, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, listOfLongLong);
            }
        });
    }

    /**
     *  下载头像
     * @param item 用户信息
     * @param listener 下载回调函数
     * @param callBack BMXErrorCode
     **/
    public void downloadAvatar(final BMXRosterItem item, final  FileProgressListener listener,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.downloadAvatar(item, false,listener);
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
     * 添加好友变化监听者
     * @param listener 好友变化监听者
     **/
    public void addRosterListener(BMXRosterServiceListener listener) {
        mService.addRosterListener(listener);
    }

    /**
     * 移除好友变化监听者
     * @param listener 好友变化监听者
     **/
    public void removeRosterListener(BMXRosterServiceListener listener) {
        mService.removeRosterListener(listener);
    }
}
