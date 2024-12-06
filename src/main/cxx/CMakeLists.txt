cmake_minimum_required(VERSION 3.5)
set(CMAKE_MACOSX_RPATH 1)
set (CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fPIC")

project(dlib-jni LANGUAGES CXX)
find_package(Java 17 REQUIRED COMPONENTS Development)

if (CMAKE_HOST_WIN32)
    string(REGEX REPLACE "/bin/java.exe$" "" JAVA_HOME ${Java_JAVA_EXECUTABLE})
    set(JAVA_INCLUDE_PATH2 "${JAVA_HOME}/include/win32")
elseif(CMAKE_HOST_APPLE)
    string(REGEX REPLACE "/bin/java$" "" JAVA_HOME ${Java_JAVA_EXECUTABLE})
    set(JAVA_INCLUDE_PATH2 "${JAVA_HOME}/include/darwin")
elseif(CMAKE_HOST_UNIX)
    string(REGEX REPLACE "/bin/java$" "" JAVA_HOME ${Java_JAVA_EXECUTABLE})
    set(JAVA_INCLUDE_PATH2 "${JAVA_HOME}/include/linux")
endif()
set(JAVA_INCLUDE_PATH "${JAVA_HOME}/include")

set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${LIBRARY_PATH})
add_library(dlib-jni SHARED io_github_glorrian_DlibInterface.cpp)

set(DLib_LOCATION ${CMAKE_BINARY_DIR}/dlib/)
execute_process (
   COMMAND bash -c "git clone https://github.com/davisking/dlib.git"
)
add_subdirectory(${DLib_LOCATION} dlib_build)

target_link_libraries(dlib-jni dlib::dlib)
target_include_directories(dlib-jni PUBLIC ${JAVA_INCLUDE_PATH} ${JAVA_INCLUDE_PATH2})
