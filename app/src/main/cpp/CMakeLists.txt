# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)
set(cmakedir ${CMAKE_SOURCE_DIR})
message("cmakedir= ${cmakedir}")
set(ffmpeg_so_dir ${CMAKE_SOURCE_DIR}/../jniLibs/${CMAKE_ANDROID_ARCH_ABI})
message("ffmpeg_so_dir= ${ffmpeg_so_dir}")
set(ffmpeg_head_dir ${CMAKE_SOURCE_DIR})
include_directories(include)

add_library(
        avcodec
        SHARED
        IMPORTED
)
message("libavcodecdoir = ${ffmpeg_so_dir}/libavcodec.so")
set_target_properties(
        avcodec
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_so_dir}/libavcodec.so
)
add_library(
        avfilter
        SHARED
        IMPORTED
)
set_target_properties(
        avfilter
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_so_dir}/libavfilter.so
)
add_library(
        avformat
        SHARED
        IMPORTED
)
set_target_properties(
        avformat
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_so_dir}/libavformat.so
)
add_library(
        avutil
        SHARED
        IMPORTED
)
set_target_properties(
        avutil
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_so_dir}/libavutil.so
)
add_library(
        postproc
        SHARED
        IMPORTED
)
set_target_properties(
        postproc
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_so_dir}/libpostproc.so
)
add_library(
        swresample
        SHARED
        IMPORTED
)
set_target_properties(
        swresample
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_so_dir}/libswresample.so
)
add_library(
        swscale
        SHARED
        IMPORTED
)
set_target_properties(
        swscale
        PROPERTIES IMPORTED_LOCATION
        ${ffmpeg_so_dir}/libswscale.so
)

#add_library( # Sets the name of the library.
#             native-lib
#
#             # Sets the library as a shared library.
#             SHARED
#
#             # Provides a relative path to your source file(s).
#             native-lib.cpp )

add_library( # Sets the name of the library.
        ffmpeg

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        com_json_ffmpegdemo_VideoManager.cpp )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib
              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )


#find_library(
#             android-lib
#             android
#)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

#target_link_libraries( # Specifies the target library.
#                       native-lib
#
#                       # Links the target library to the log library
#                       # included in the NDK.
#                       ${log-lib} )

#target_link_libraries(
#        ffmpeg
#        ${avcodec}
#        ${avfilter}
#        ${avformat}
#        ${avutil}
#        ${postproc}
#        ${swresample}
#        ${swscale}
#        ${log-lib}
#)

target_link_libraries(
        ffmpeg
        avcodec
        avfilter
        avformat
        swscale
        avutil
        postproc
        swresample
        android
        ${log-lib}
)