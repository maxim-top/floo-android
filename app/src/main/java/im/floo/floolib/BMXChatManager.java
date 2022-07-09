package im.floo.floolib;

import im.floo.AsyncExecutor;
import im.floo.BMXCallBack;
import im.floo.BMXDataCallBack;

/**
 *  聊天管理器
 **/

public class BMXChatManager {

    private BMXChatService mService;

    public BMXChatManager(BMXChatService service) {
        mService = service;
    }

    /**
     发送消息，消息状态变化会通过listener通知
     @param msg 发送的消息
     **/
    public void sendMessage(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.sendMessage(msg);
            }
        });
    }

    /**
     重新发送消息，消息状态变化会通过listener通知
     @param msg 重新发送的消息
     **/
    public void resendMessage(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.resendMessage(msg);
            }
        });
    }

    /**
     撤回消息，消息状态变化会通过listener通知
     @param msg 撤回的消息
     **/
    public void recallMessage(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.recallMessage(msg);
            }
        });
    }

    /**
     合并转发消息
     @param list 转发的消息列表
     @param to 消息被转发到的会话
     @param newMsg 转发的消息列表合并后生成的新的单条转发消息
     @param callBack BMXErrorCode
     **/
    public void forwardMessage(final BMXMessageList list, final BMXConversation to, final BMXMessage newMsg, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.forwardMessage(list, to, newMsg);
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
     简单转发消息，用户应当通过BMXMessage::createForwardMessage()先创建转发消息
     @param msg 转发的消息
     **/
    public void forwardMessage(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.forwardMessage(msg);
            }
        });
    }

    /**
     *  标记此消息及之前全部消息为已读，该消息同步到当前用户的所有设备
     * @param msg 需要标记为此消息以前全部消息为已读的消息
     **/
    public void readAllMessage(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.readAllMessage(msg);
            }
        });
   }

    /**
     *  删除此消息，该消息同步到当前用户的其它设备
     * @param msg 需要删除的消息
     * @param synchronize 是否同步到其它设备，不同步的情况下只会删除本地存储的该条消息
     **/
    public void removeMessage(final BMXMessage msg, final boolean synchronize) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.removeMessage(msg, synchronize);
            }
        });
    }

    public void removeMessage(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.removeMessage(msg);
            }
        });
    }

    /**
     * 发送已读回执
     **/
    public void ackMessage(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.ackMessage(msg);
            }
        });
    }

    /**
     * 设置未读
     **/
    public void readCancel(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.readCancel(msg);
            }
        });
    }

    /**
     *  下载缩略图，下载状态变化和进度通过listener通知
     * 缩略图生成策略，1 - 第三方服务器生成， 2 - 本地服务器生成，默认值是 1。
     * @param msg 需要下载缩略图的消息
     **/
    public void downloadThumbnail(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.downloadThumbnail(msg);
            }
        });
    }

    /**
     *  下载附件，下载状态变化和进度通过listener通知
     * @param msg 需要下载附件的消息
     **/
    public void downloadAttachment(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.downloadAttachment(msg);
            }
        });
    }

    /**
     *  取消下载附件
     * @param msg 需要下载附件的消息
     **/
    public void cancelDownloadAttachment(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.cancelDownloadAttachment(msg);
            }
        });
    }

    /**
     *  取消上传附件
     * @param msg 需要上传附件的消息
     **/
    public void cancelUploadAttachment(final BMXMessage msg) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.cancelUploadAttachment(msg);
            }
        });
    }

    /**
     *  正在上传或下载中的文件数
     * @return 传输中的文件数
     **/
    public int transferingNum() {
        return mService.transferingNum();
    }

    /**
     *  插入消息
     * @param list 插入消息列表
     * @param callBack BMXErrorCode
     **/
    public void insertMessages(final BMXMessageList list, final BMXCallBack callBack) {
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.insertMessages(list);
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                callBack.onResult(code);
            }
        });
    }

    /**
     *  读取一条消息
     * @param msgId 需要获取消息的消息id
     * @param callBack BMXMessage
     **/
    public void getMessage(final long msgId, final BMXDataCallBack<BMXMessage> callBack) {
        final BMXMessageList messageList = new BMXMessageList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                messageList.add(mService.getMessage(msgId));
                return BMXErrorCode.NoError;
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, messageList.get(0));
            }
        });
    }

    /**
     *  删除会话
     * @param conversationId 需要删除会话的会话id
     * @param sync 是否同步删除其它设备该会话，默认为false，仅删除本设备会话
     **/
    public void deleteConversation(final long conversationId, final Boolean sync) {
        new AsyncExecutor().exec(new AsyncExecutor.SimpleTask() {
            @Override
            public void exec() {
                mService.deleteConversation(conversationId, sync);
            }
        });
    }

    /**
     *  打开一个会话
     * @param conversationId 需要打开的会话的会话id
     * @param type 会话的类型，单聊还是群聊。
     * @param createIfNotExist 会话不存在的情况下是否要创建本地会话，默认为创建
     * @param callBack  BMXConversation
     **/
    public void openConversation(final long conversationId, final BMXConversation.Type type,
                                            final boolean createIfNotExist, final BMXDataCallBack<BMXConversation> callBack) {
        final BMXConversationList conversationList = new BMXConversationList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                conversationList.add(mService.openConversation(conversationId, type, createIfNotExist));
                return BMXErrorCode.NoError;
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, conversationList.get(0));
            }
        });
    }

    /**
     *  获取所有会话
     * @param callBack BMXConversationList
     **/
    public void getAllConversations(final BMXDataCallBack<BMXConversationList> callBack) {
        final BMXConversationList conversationList = new BMXConversationList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                BMXConversationList bmxConversationList = mService.getAllConversations();
                for(int i=0; i < bmxConversationList.size(); i++){
                    conversationList.add(bmxConversationList.get(i));
                }
                return BMXErrorCode.NoError;
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, conversationList);
            }
        });
    }

    /**
     *  获取所有会话的全部未读数（标记为屏蔽的个人和群组的未读数不统计在内）
     * @param callBack  未读数
     **/
    public void getAllConversationsUnreadCount(final BMXDataCallBack<Integer> callBack) {
        final Integer[] cnt = new Integer[1];
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                cnt[0] = mService.getAllConversationsUnreadCount();
                return BMXErrorCode.NoError;
            }

            @Override
            public void onPostExecute(BMXErrorCode code) {
                if (callBack == null){
                    return;
                }
                callBack.onResult(code, cnt[0]);
            }
        });
    }

    /**
     *  拉取历史消息
     * @param conversation 需要拉取历史消息的会话
     * @param refMsgId 拉取会话消息的起始消息Id
     * @param size 拉取的最大消息条数
     * @param callBack  BMXErrorCode，拉取操作获取的消息列表
     **/
    public void retrieveHistoryMessages(final BMXConversation conversation, final long refMsgId,
                                        final long size, final BMXDataCallBack<BMXMessageList> callBack) {
        final BMXMessageList result = new BMXMessageList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.retrieveHistoryMessages(conversation, refMsgId, size, result);
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
     *  搜索消息
     * @param keywords 搜索的关键字
     * @param refTime 搜索消息的起始时间
     * @param size 搜索的最大消息条数
     * @param arg4 消息搜索方向，默认（Direction::Up）是从更早的消息中搜索
     * @param callBack  BMXErrorCode，搜索到的消息结果列表
     **/
    public void searchMessages(final String keywords, final long refTime, final long size,
                                       final BMXConversation.Direction arg4,
                                       final BMXDataCallBack<BMXMessageListList> callBack) {
        final BMXMessageListList result = new BMXMessageListList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.searchMessages(keywords, refTime, size, result, arg4);
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

    public void searchMessages(final String keywords, final long refTime, final long size,
                                       final BMXDataCallBack<BMXMessageListList> callBack) {
        final BMXMessageListList result = new BMXMessageListList();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.searchMessages(keywords, refTime, size, result);
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
     *  获取发送的群组消息已读用户id列表
     * @param msg 需要获取已读用户id列表的消息
     * @param callBack  BMXErrorCode,对该条消息已读的用户id列表
     **/
    public void getGroupAckMessageUserIdList(final BMXMessage msg,
                                                    final BMXDataCallBack<ListOfLongLong> callBack) {
        final ListOfLongLong result = new ListOfLongLong();
        new AsyncExecutor().exec(new AsyncExecutor.Task() {
            @Override
            public BMXErrorCode exec() {
                return mService.getGroupAckMessageUserIdList(msg, result);
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
     * 添加聊天监听者
     * @param listener 聊天监听者
     **/
    public void addChatListener(BMXChatServiceListener listener) {
        mService.addChatListener(listener);
    }

    /**
     * 移除聊天监听者
     * @param listener 聊天监听者
     **/
    public void removeChatListener(BMXChatServiceListener listener) {
        mService.removeChatListener(listener);
    }
}
