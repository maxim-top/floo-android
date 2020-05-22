package im.floo.floolib;

import im.floo.AsyncExecutor;
import im.floo.BMXCallBack;
import im.floo.BMXDataCallBack;

/**
 *  用户管理器
 **/
public class BMXUserManager {

    private BMXUserService mService;
    private BMXClient bmxClient;

    public BMXUserManager(BMXUserService service, BMXClient bmxClient) {
        mService = service;
        this.bmxClient = bmxClient;
    }

    /**
     * 注册
     *
     * @param password 密码
     * @param username 用户名
     * @param callBack BMXUserProfile
     */
    public void signUpNewUser(final String username, final String password, final BMXDataCallBack<BMXUserProfile> callBack) {
        final BMXUserProfile profile = new BMXUserProfile();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return bmxClient.signUpNewUser(username, password, profile);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, profile);
            }
        });
    }

    /**
     * 用户名登陆
     *
     * @param name
     * @param password
     * @param callBack BMXErrorCode
     */
    public void signInByName(final String name, final String password, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return bmxClient.signInByName(name, password);
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
     * id 登陆
     *
     * @param id
     * @param password
     * @param callBack BMXErrorCode
     */
    public void signInById(final long id, final String password,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return bmxClient.signInById(id, password);
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
     * 自动登陆 根据用户名
     *
     * @param name
     * @param password
     * @param callBack BMXErrorCode
     */
    public void autoSignInByName(final String name, final String password, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return bmxClient.fastSignInByName(name, password);
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
     * 自动登陆 根据id
     *
     * @param uid
     * @param password
     * @param callBack BMXErrorCode
     */
    public void autoSignInById(final long uid, final String password, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return bmxClient.fastSignInById(uid, password);
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
     * 退出登录
     *
     * @param callBack BMXErrorCode
     */
    public void signOut(final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return bmxClient.signOut();
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
     * 退出登录
     *
     * @param callBack BMXErrorCode
     */
    public void signOut(final long userId, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return bmxClient.signOut(userId);
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
     * 获取当前和服务器的连接状态
     **/
    public BMXConnectStatus connectStatus() {
        return bmxClient.connectStatus();
    }

    /**
     * 获取当前的登录状态
     **/
    public BMXSignInStatus signInStatus() {
        return bmxClient.signInStatus();
    }

    /**
     * 绑定设备推送token
     * @param token device token
     * @param callBack BMXErrorCode
     **/
    public void bindDevice(final String token, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.bindDevice(token);
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
     * 获取登录的设备列表
     * @param callBack BMXErrorCode 登录的设备列表
     */
    public void getDeviceList(final BMXDataCallBack<BMXDeviceList> callBack) {
        final BMXDeviceList bmxDeviceList = new BMXDeviceList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getDeviceList(bmxDeviceList);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, bmxDeviceList);
            }
        });
    }

    /**
     * 删除设备
     * @param callBack BMXErrorCode
     */
    public void deleteDevice(final int device_sn, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.deleteDevice(device_sn);
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
     * 获取用户详情
     * @param forceRefresh 强制从服务器拉取最新结果
     * @param callBack BMXErrorCode,用户详情
     **/
    public void getProfile(final boolean forceRefresh, final BMXDataCallBack<BMXUserProfile> callBack) {
        final BMXUserProfile profile = new BMXUserProfile();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getProfile(profile, forceRefresh);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, profile);
            }
        });
    }

    /**
     * 设置昵称
     * @param nickname 昵称
     * @param callBack BMXErrorCode
     **/
    public void setNickname(final String nickname,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setNickname(nickname);
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
     * 上传头像
     * @param avatarPath 头像本地文件路径
     * @param listener 上传进度监听器
     * @param callBack BMXErrorCode
     **/
    public void uploadAvatar(final String avatarPath, final  FileProgressListener listener,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.uploadAvatar(avatarPath, listener);
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
     * 下载头像
     * @param profile 用户详情
     * @param listener 下载进度监听器
     * @param callBack BMXErrorCode
     */
    public void downloadAvatar(final BMXUserProfile profile, final  FileProgressListener listener,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.downloadAvatar(profile, false, listener);
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
     * 设置公开扩展信息
     * @param publicInfo 用户公开信息
     * @param callBack BMXErrorCode
     **/
    public void setPublicInfo(final String publicInfo,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setPublicInfo(publicInfo);
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
     * 设置私有扩展信息
     * @param privateInfo 用户私有信息（只对自己可见）
     * @param callBack BMXErrorCode
     **/
    public void setPrivateInfo(final String privateInfo,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setPrivateInfo(privateInfo);
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
     * 设置加好友验证方式
     * @param mode 添加好友时的验证方式
     * @param callBack BMXErrorCode
     **/
    public void setAddFriendAuthMode(final BMXUserProfile.AddFriendAuthMode mode,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setAddFriendAuthMode(mode);
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
     * 设置加好友验证问题
     * @param authQuestion 验证问题
     * @param callBack BMXErrorCode
     **/
    public void setAuthQuestion(final BMXUserProfile.AuthQuestion authQuestion,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setAuthQuestion(authQuestion);
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
     * 设置是否允许推送
     * @param enable 是否允许推送，true推送，false不推送
     * @param callBack BMXErrorCode
     **/
    public void setEnablePush(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setEnablePush(enable);
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
     *  设置是否推送详情
     * @param enable 是否推送详情，true推送，false不推送
     * @param callBack BMXErrorCode
     **/
    public void setEnablePushDetaile(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setEnablePushDetaile(enable);
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
     *  设置推送昵称
     * @param nickname 推送昵称
     * @param callBack  BMXErrorCode
     **/
    public void setPushNickname(final String nickname,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setPushNickname(nickname);
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
     *  设置收到新消息是否声音提醒
     * @param enable 收到新消息是否声音提醒，true提醒，false不提醒
     * @param callBack BMXErrorCode
     **/
    public void setNotificationSound(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setNotificationSound(enable);
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
     *  设置收到新消息是否震动
     * @param enable 收到新消息是否震动，true震动，false不震动
     * @param callBack BMXErrorCode
     **/
    public void setNotificationVibrate(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setNotificationVibrate(enable);
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
     *  设置是否自动缩略图和语音附件
     * @param enable 是否自动缩略图和语音附件，true自动下载，false不会自动下载
     * @param callBack  BMXErrorCode
     **/
    public void setAutoDownloadAttachment(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setAutoDownloadAttachment(enable);
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
     *  设置是否自动同意入群邀请
     * @param enable 是否自动同意入群邀请，true同意，false不同意
     * @param callBack BMXErrorCode
     **/
    public void setAutoAcceptGroupInvite(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setAutoAcceptGroupInvite(enable);
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
     * 添加用户状态监听者
     * @param listener 用户状态监听者
     **/
    public void addUserListener(BMXUserServiceListener listener) {
        mService.addUserListener(listener);
    }

    /**
     * 移除用户状态监听者
     * @param listener 用户状态监听者
     **/
    public void removeUserListener(BMXUserServiceListener listener) {
        mService.removeUserListener(listener);
    }

    /**
     * 切换appId
     * @param appId appId
     */
    public void changeAppId(final String appId, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return bmxClient.changeAppId(appId);
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
