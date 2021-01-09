# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

LINK_LIBRARIES(z)

add_library(crypto
        STATIC
        IMPORTED)

set_target_properties(crypto
        PROPERTIES IMPORTED_LOCATION
        ${PROJECT_SOURCE_DIR}/floo/deps/openssl/libs/android/${ANDROID_ABI}/libcrypto.a )


add_library( # Sets the name of the library.
        floo
        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        src/main/cpp/floo_wrap.cxx
        src/main/cpp/my_jni.cpp
        floo/src/push_user_profile_impl.cc
        floo/src/bmx_push_service_impl.cc
        floo/src/cluster_service.cc
        floo/src/base64Encode.cc
        floo/src/base64Decode.cc
        floo/src/bmx_device_impl.cc
        floo/src/bmx_one_time_signal.cc
        floo/src/crypto_handler.cc
        floo/src/crypto_utils.cc
        floo/src/bmx_error.cc
        floo/src/alarm.cc
        floo/src/http.cc
        floo/src/conversation_service_impl.cc
        floo/src/bmx_chat_service_impl.cc
        floo/src/bmx_push_service_impl.cc
        floo/src/bmx_client.cc
        floo/src/bmx_client_impl.cc
        floo/src/conversation_impl.cc
        floo/src/bmx_file_attachment.cc
        floo/src/group_impl.cc
        floo/src/bmx_group_service_impl.cc
        floo/src/bmx_image_attachment.cc
        floo/src/bmx_location_attachment.cc
        floo/src/bmx_message.cc
        floo/src/roster_item_impl.cc
        floo/src/bmx_roster_service_impl.cc
        floo/src/bmx_sdk_config.cc
        floo/src/user_profile_impl.cc
        floo/src/bmx_user_service_impl.cc
        floo/src/bmx_video_attachment.cc
        floo/src/bmx_voice_attachment.cc
        floo/src/database.cc
        floo/src/thread_pool.cc
        floo/src/bmx_longlink_service.cc
        floo/src/settings.cc
        floo/src/bmx_signal.cc
        floo/src/sqlite.cc
        floo/deps/sqlite3/sqlite3.c
        floo/src/tcp.cc
        floo/src/utils.cc
        floo/src/xlog.cc
        floo/src/android/jni_global.cpp
        floo/src/protocol/bmx_base_node.cc
        floo/src/protocol/bmx_conversation_unread.cc
        floo/src/protocol/bmx_group_notice.cc
        floo/src/protocol/bmx_info.cc
        floo/src/protocol/bmx_message_body.cc
        floo/src/protocol/bmx_message_operation.cc
        floo/src/protocol/bmx_proto_conversation.cc
        floo/src/protocol/bmx_proto_conversation_operation.cc
        floo/src/bmx_message_config.cc
        floo/src/protocol/bmx_meta.cc
        floo/src/protocol/bmx_notice.cc
        floo/src/protocol/bmx_provision.cc
        floo/src/protocol/bmx_roster_notice.cc
        floo/src/protocol/bmx_status.cc
        floo/src/protocol/bmx_syncdl.cc
        floo/src/protocol/bmx_syncul.cc
        floo/src/protocol/bmx_unreaddl.cc
        floo/src/protocol/bmx_unreadul.cc
        floo/src/protocol/bmx_user_notice.cc
        floo/src/protocol/bmx_xid.cc
        floo/src/protocol/bmx_frame.cc
        floo/src/protocol/bmx_xsync_parser.cc
        floo/src/protocol/bmx_conversation_unread.cc
        floo/src/protocol/bmx_zlib_compression.cc
        floo/src/protocol/generated/groupnotice.pb.cc
        floo/src/protocol/generated/info.pb.cc
        floo/src/protocol/generated/messagebody.pb.cc
        floo/src/protocol/generated/rosternotice.pb.cc
        floo/src/protocol/generated/usernotice.pb.cc
        floo/src/protocol/generated/xid.pb.cc
        floo/src/protocol/generated/xsync.pb.cc
        floo/src/protocol/generated/conversation.pb.cc
        floo/deps/protobuf/google/protobuf/any.cc
        floo/deps/protobuf/google/protobuf/any.pb.cc
        floo/deps/protobuf/google/protobuf/api.pb.cc
        floo/deps/protobuf/google/protobuf/arena.cc
        floo/deps/protobuf/google/protobuf/arenastring.cc
        floo/deps/protobuf/google/protobuf/descriptor.cc
        floo/deps/protobuf/google/protobuf/descriptor.pb.cc
        floo/deps/protobuf/google/protobuf/descriptor_database.cc
        floo/deps/protobuf/google/protobuf/duration.pb.cc
        floo/deps/protobuf/google/protobuf/dynamic_message.cc
        floo/deps/protobuf/google/protobuf/empty.pb.cc
        floo/deps/protobuf/google/protobuf/extension_set.cc
        floo/deps/protobuf/google/protobuf/extension_set_heavy.cc
        floo/deps/protobuf/google/protobuf/field_mask.pb.cc
        floo/deps/protobuf/google/protobuf/generated_message_reflection.cc
        floo/deps/protobuf/google/protobuf/generated_message_table_driven.cc
        floo/deps/protobuf/google/protobuf/generated_message_table_driven_lite.cc
        floo/deps/protobuf/google/protobuf/generated_message_util.cc
        floo/deps/protobuf/google/protobuf/implicit_weak_message.cc
        floo/deps/protobuf/google/protobuf/map_field.cc
        floo/deps/protobuf/google/protobuf/message.cc
        floo/deps/protobuf/google/protobuf/message_lite.cc
        floo/deps/protobuf/google/protobuf/reflection_ops.cc
        floo/deps/protobuf/google/protobuf/repeated_field.cc
        floo/deps/protobuf/google/protobuf/service.cc
        floo/deps/protobuf/google/protobuf/source_context.pb.cc
        floo/deps/protobuf/google/protobuf/struct.pb.cc
        floo/deps/protobuf/google/protobuf/text_format.cc
        floo/deps/protobuf/google/protobuf/timestamp.pb.cc
        floo/deps/protobuf/google/protobuf/type.pb.cc
        floo/deps/protobuf/google/protobuf/unknown_field_set.cc
        floo/deps/protobuf/google/protobuf/wire_format.cc
        floo/deps/protobuf/google/protobuf/wire_format_lite.cc
        floo/deps/protobuf/google/protobuf/wrappers.pb.cc
        floo/deps/protobuf/google/protobuf/stubs/bytestream.cc
        floo/deps/protobuf/google/protobuf/stubs/common.cc
        floo/deps/protobuf/google/protobuf/stubs/int128.cc
        floo/deps/protobuf/google/protobuf/stubs/io_win32.cc
        floo/deps/protobuf/google/protobuf/stubs/mathlimits.cc
        floo/deps/protobuf/google/protobuf/stubs/pbtime.cc
        floo/deps/protobuf/google/protobuf/stubs/status.cc
        floo/deps/protobuf/google/protobuf/stubs/statusor.cc
        floo/deps/protobuf/google/protobuf/stubs/stringpiece.cc
        floo/deps/protobuf/google/protobuf/stubs/stringprintf.cc
        floo/deps/protobuf/google/protobuf/stubs/structurally_valid.cc
        floo/deps/protobuf/google/protobuf/stubs/strutil.cc
        floo/deps/protobuf/google/protobuf/stubs/substitute.cc
        floo/deps/protobuf/google/protobuf/io/coded_stream.cc
        floo/deps/protobuf/google/protobuf/io/gzip_stream.cc
        floo/deps/protobuf/google/protobuf/io/printer.cc
        floo/deps/protobuf/google/protobuf/io/strtod.cc
        floo/deps/protobuf/google/protobuf/io/tokenizer.cc
        floo/deps/protobuf/google/protobuf/io/zero_copy_stream.cc
        floo/deps/protobuf/google/protobuf/io/zero_copy_stream_impl.cc
        floo/deps/protobuf/google/protobuf/io/zero_copy_stream_impl_lite.cc
        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        floo

        # Links the target library to the log library
        # included in the NDK.
        ${z-lib}
        ${log-lib} crypto)
LINK_DIRECTORIES("/usr/local/lib" "/Users/lhr/maxim/floo-android/app/floo/deps/openssl/android")
include_directories(
        floo/include
        floo/android_include
        floo/deps/curlcpp/win
        floo/deps/curlcpp/include
        floo/deps/sqlite3/include
        floo/deps/rapidjson/include
        floo/deps/protobuf
        floo/deps/openssl/include
        floo/src/protocol
        floo/src
        floo/src/protocol/generated
        floo
        )