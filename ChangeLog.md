# floo-android Changelog

## 3.1.23 - 2022/01/05
新增：
1. 发送消息时携带用户名，方便在未获取到对方用户信息时显示用户名。
2. 根据图片和视频的原始尺寸生成缩略图（原来是固定大小）。
3. 支持aws S3作为文件存储服务器。
4. 获取当前正在上传或者下载中的文件数。
5. 新接口：发送送达回执。方便应用开发者自行发送送达回执。

优化：
1. 减小openssl库的尺寸。

解决bug：
1. 有文件上传或者下载任务时登出一直阻塞。

## 3.1.3 - 2021/08/04
新增：
1. 本地数据库加密功能

解决bug:
1. 各种服务监听器释放时自动从服务上移除，避免崩溃

## 3.1.1 - 2021/06/24
新增：
1. 通过SDK创建聊天室

解决bug:
1. ARM平台Base64解码失败

## 3.0.14 - 2020/09/18
新增：
1. 通过URL下载附件（之前已提供通过消息实体下载附件的功能）
2. 免鉴权下载消息附件及头像
3. 防DNS污染攻击，自研httpDNS服务
4. 语音视频类消息已播放回执


## 3.0.1 - 2020/09/18
新增：
1. 消息推送服务

## v2.3.1 - 2020/04/29

变更：

1. 迁移异步接口到Manager，与同步接口Service对应，分别为 BMXChatManager、BMXUserManager、BMXRosterManager、BMXGroupManager；

## v2.2.1 - 2020/04/07

变更：

1. 网络请求超时时间更改为20s  

## v2.2.0 - 2020/03/27

变更：

1. 将同步接口更换成异步方式，可以直接在回调做相关业务处理
2. 增加BMXDataCallBack<T> BMXCallBack两种回调低缓BMXErrorCode返回
    
    ```
    /**
     * 获取所有会话
     **/
    - public void getAllConversations(BMXDataCallBack<BMXConversationList> callBack) {
        mService.getAllConversations(callBack);
    }
	```
	
	```
	 /**
     * 设置昵称
     **/
    - public void setNickname(String nickname, BMXCallBack callBack) {
        mService.setNickname(nickname, callBack);
    }

	```


## v2.1.0 - 2020/03/17

新增:

1. 会话：新增更新会话中消息的扩展字段接口

	```
	/// 更新一条数据库存储消息的扩展字段信息
	/// @param message 需要更改扩展信息的消息此时msg部分已经更新扩展字椴信息
	- updateMessageExtension:(BMXMessage msg);
	```

2. 命令消息：新增创建命令消息的接口
	
	```
	/// 创建发送命令消息(命令消息通过content字段或者extension字段存放命令信息)
	/// @param type 单群类型
	/// @param from 消息发送者
	/// @param to 消息接收者
	/// @param mtype 消息类型
	/// @param content 消息内容
	- public void sendCommandMessage(BMXMessage.MessageType type, long from, long to,
            String content)
	```

3.  新增收到命令消息通知接口

	```
	/**
	 * 收到命令消息
	 **/
	- public void onReceiveCommandMessages(BMXMessageList list) {}
	```
	


