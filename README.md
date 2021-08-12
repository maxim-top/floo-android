# floo-android
Floo Android native codes 美信拓扑IM SDK，安卓本地库

美信拓扑IM SDK（代号 floo ）底层使用 C++ 实现，各平台（Android、iOS、Linux等）在此基础上再行封装，
完成本地库的开发，以达到多平台复用的目的，并保持跨平台协议实现的一致性。

本工程 floo-android 为供安卓使用的本地应用库，主体由 [SWIG](http://www.swig.org/index.php) 框架自动生成。

[![Scc Count Badge](https://sloc.xyz/github/maxim-top/floo-android/?category=total&avg-wage=1)](https://github.com/maxim-top/floo-android/) [![Scc Count Badge](https://sloc.xyz/github/maxim-top/floo-android/?category=code&avg-wage=1)](https://github.com/maxim-top/floo-android/)

## 设计思想

用 SWIG 生成的 Java 代码，通过 JNI 方式调用底层 C++ 类库，因此大部分接口均为同步，这便是 floo-android 低级 API 的主体。
代码生成和转换的过程中，相关数据结构得以直接映射到底层类库，减少了内存拷贝，因此其性能接近于直接调用底层库。

同时，又考虑到开发者集成方便，我们也基于此类重新封装了高级 API，使用了更为友好的数据结构，并完成了异步机制封装。

## 介绍

整个类库库分为三部分：

1. 低级 API（Low-Level / 同步调用 / 以Service结尾）

主要有 BMXUserService、BMXChatService、BMXRosterService、BMXGroupService。

2. 高级 API（High-Level / 异步调用 / 以Manager结尾）

主要有 BMXUserManager、BMXChatManager、BMXRosterManager、BMXGroupManager。

3. 工具类 Utility

包括 BMXClient、BMXSDKConfig、BMXMessage、BMXConversation、BMXUserProfile、BMXGroup、BMXDevice等。

快速集成文档参考[美信拓扑快速集成指南Android版](https://www.maximtop.com/docs/quick/android/)，
详细文档可参考[floo-android reference](https://www.maximtop.com/docs/android/)

## 开发

1. 检查 ndk 已安装并配置

下载[Android NDK](https://developer.android.com/ndk/downloads)，解压后配置 local.properties 中的 ndk.dir。

2. 构建

使用 Android Studio 打开工程，执行 app -> build -> assembleRelease 任务，可生成可用 AAR 库。

## 常见错误

1. 不支持armeabi

错误信息：
```
ABIs [armeabi] are not supported for platform. Supported ABIs are [arm64-v8a, armeabi-v7a, x86, x86_64].
```
原因以及解决：
armeabi 从 ndk17 开始已被移除，
可以使用 [ndk-r16b](https://dl.google.com/android/repository/android-ndk-r16b-darwin-x86_64.zip?hl=zh_cn)
（以 mac 为例）

## 生成文档

进入 javadoc 文件夹，执行命令
```
./gendoc.sh
```

祝玩得开心😊
