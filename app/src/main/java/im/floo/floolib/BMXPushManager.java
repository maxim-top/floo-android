package im.floo.floolib;

import im.floo.AsyncExecutor;
import im.floo.BMXCallBack;
import im.floo.BMXDataCallBack;

/**
 *  推送管理器
 **/

public class BMXPushManager {

    private BMXPushService mService;

    public BMXPushManager(BMXPushService service) {
        mService = service;
    }


  /**
   初始化推送sdk。在仅使用推送的情况下使用该接口初始化推送sdk。在同时使用IM功能的时候直接在BMXClient调用登陆功能即可。config对象初始化的时候需要传入平台类型和设备id。
   @param alias 推送初始化使用的当前用户别名
   @param bmxToken 推送初始化的时候App传入的使用的用户的token，无用户的状态下不传入即可。
   @param callBack BMXErrorCode
   **/
    public void start(final String alias, final  String bmxToken,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.start(alias, bmxToken);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    public void start(final String alias,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.start(alias);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    public void start(final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.start();
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

  /**
   停止推送功能接口。
   @param callBack BMXErrorCode
   **/
    public void stop(final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.stop();
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

  /**
   恢复推送功能接口。
   @param callBack BMXErrorCode
   **/
    public void resume(final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.resume();
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

  /**
   解除用户别名绑定。
   @param alias 需要解除绑定的用户别名。
   @param callBack BMXErrorCode
   **/
    public void unbindAlias(final String alias,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.unbindAlias(alias);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

  /**
   获取登陆后使用的用户token。
   **/
    public String getToken() {
        return mService.getToken();
    }

  /**
   获取登陆后服务器返回的推送证书。
   **/
    public String getCert() {
        return mService.getCert();
    }

  /**
   推送sdk当前的状态。
   @return PushSdkStatus
   **/
    public BMXPushService.PushSdkStatus status() {
        return mService.status();
    }

  /**
   推送绑定设备token。
   @param token 设备的推送token
   @param callBack BMXErrorCode
   **/
    public void bindDeviceToken(final String token,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.bindDeviceToken(token);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

  /**
   绑定推送设备的voiptoken。
   @param token 设备的voip推送token
   @param callBack BMXErrorCode
   **/
    public void bindVoipToken(final String token,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.bindVoipToken(token);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

   /**
    获取推送用户详情，如果forceRefresh == true，则强制从服务端拉取
    @param forceRefresh 是否强制从服务器拉取，本地获取失败的情况下会自动从服务器拉取
    @param callBack 推送用户profile信息，初始传入指向为空的shared_ptr对象，函数返回后从此处获取用户profile信息
    **/
    public void getPushProfile(final boolean forceRefresh, final BMXDataCallBack<BMXPushUserProfile> callBack) {
        final BMXPushUserProfile profile = new BMXPushUserProfile();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getPushProfile(profile, forceRefresh);
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
     设置推送用户的标签。
     @param tags 用户标签
     @param operationId 操作id。在回调通知中对应通知提醒。
     @param callBack BMXErrorCode
     **/
    public void setTags(final TagList tags, final  String operationId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setTags(tags, operationId);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     获取推送用户的标签。
     @param tags 用户标签
     @param operationId 操作id。在回调通知中对应通知提醒。
     @param callBack BMXErrorCode
     **/
    public void getTags(final TagList tags, final  String operationId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getTags(tags, operationId);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     删除推送用户的标签。
     @param tags 要删除用户标签
     @param operationId 操作id。在回调通知中对应通知提醒。
     @param callBack BMXErrorCode
     **/
    public void deleteTags(final TagList tags, final  String operationId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.deleteTags(tags, operationId);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     清空推送用户的标签。
     @param operationId 操作id。在回调通知中对应通知提醒。
     @param callBack BMXErrorCode
     **/
    public void clearTags(final String operationId,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.clearTags(operationId);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     设置推送用户的未读角标。
     @param count 用户未读角标数
     @param callBack BMXErrorCode
     **/
    public void setBadge(final int count,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setBadge(count);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     设置推送启用状态。默认为使用推送。
     @param enable 推送的启用状态
     @param callBack BMXErrorCode
     **/
    public void setPushMode(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setPushMode(enable);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    public void setPushMode(final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setPushMode();
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     设置允许推送时间。
     @param startHour 静默允许推送的起始时间小时
     @param endHour 静默允许推送的结束时间小时
     @param callBack BMXErrorCode
     **/
    public void setPushTime(final int startHour, final  int endHour,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setPushTime(startHour, endHour);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     设置推送静默的起始结束时间。
     @param startHour 静默推送的起始时间小时
     @param endHour 静默推送的结束时间小时
     @param callBack BMXErrorCode
     **/
    public void setSilenceTime(final int startHour, final  int endHour,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setSilenceTime(startHour, endHour);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     设置推送是否可以后台运行。默认是false。
     @param enable 推送后台运行状态。
     @param callBack BMXErrorCode
     **/
    public void setRunBackgroundMode(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setRunBackgroundMode(enable);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    public void setRunBackgroundMode(final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setRunBackgroundMode();
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     设置推送的地理围栏功能是否运行。
     @param enable 地理围栏功能是否运行。
     @param isAllow 用户是否主动弹出用户定位请求。
     @param callBack BMXErrorCode
     **/
    public void setGeoFenceMode(final boolean enable, final  boolean isAllow,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setGeoFenceMode(enable, isAllow);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    public void setGeoFenceMode(final boolean enable,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setGeoFenceMode(enable);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    public void setGeoFenceMode(final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.setGeoFenceMode();
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     清除指定id的通知。
     @param notificationId 通知id
     **/
    public void clearNotification(final long notificationId) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.clearNotification(notificationId);
            }
        });
    }

    public void clearAllNotifications() {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.clearAllNotifications();
            }
        });
    }

    /**
     发送推送上行消息，消息状态变化会通过listener通知
     @param content 发送的上行推送消息内容
     **/
    public void sendMessage(final String content) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.sendMessage(content);
            }
        });
    }

    /**
     加载数据库本地存储的推送消息。如果不指定则从最新消息开始
     @param refMsgId 加载推送消息的起始id
     @param size 最大加载消息条数
     @param result 数据库返回的加载本地推送消息列表
     @param arg3 加载推送消息的方向，默认是加载更早的消息
     **/
    public void loadLocalPushMessages(final long refMsgId, final  long size, final  BMXMessageList result, final  BMXPushService.PushDirection arg3,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.loadLocalPushMessages(refMsgId, size, result, arg3);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    public void loadLocalPushMessages(final long refMsgId, final  long size, final  BMXMessageList result,final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.loadLocalPushMessages(refMsgId, size, result);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     * 添加推送监听者
     * @param listener 推送监听者
     **/
    public void addPushListener(BMXPushServiceListener listener) {
        mService.addPushListener(listener);
    }

    /**
     * 移除推送监听者
     * @param listener 推送监听者
     **/
    public void removePushListener(BMXPushServiceListener listener) {
        mService.removePushListener(listener);
    }

}
