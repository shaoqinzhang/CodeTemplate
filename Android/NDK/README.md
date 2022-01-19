google guides url https://developer.android.google.cn/ndk/guides

compile tools include CMake and ndk-build, and add-on tools [CCache](https://ccache.dev/)

LLDB:the debugger Android Studio uses to debug native code

intsall component url:[https://developer.android.google.cn/studio/projects/install-ndk#kts](https://developer.android.google.cn/studio/projects/install-ndk#kts)

when to use ndk
+ port app
+ reuse library
+ computationally intensive ones

[Java Native Interface Specification](https://docs.oracle.com/javase/7/docs/technotes/guides/jni/spec/jniTOC.html)

shared libraries end with .so 
static libraries end with .a

Application Binary Interface(ABI), such as arm32, aarch64, x86, x86-64

natve-only app: use [native_activity.h](https://developer.android.google.cn/ndk/guides/concepts#na)

[Android.mk](https://developer.android.google.cn/ndk/guides/android_mk)

[Android ndk samples](https://github.com/android/ndk-samples)

JNI heap view tool [Memory Profiler](https://developer.android.google.cn/studio/profile/memory-profiler#jni-references)

Tips for jni 
+ Minimize marshalling of resources across the JNI layer
+ Avoid asynchronous communication between java and c++
+ Minimize the numbers of threads, or keep JNI communication between the thread pool owner rather than individual worker threads.
+ Interfaces code in a low number of code source locations

JavaVM: a function table of "invocation interface" functions. Android only allows one JavaVM per process. 
JNIEnv: most of the JNI functions, usually, as the first argument of native functions. JNIEnv is used for thread-local storage. 

tips for java class is unloaded or reloaded
+ move a native function to java class static zone. the code pieces will be executed once.
  
local preference means that it's valid for the duration of current native method and current thread. The only way to get non-local references is via the funtions NewFGlobalRef and NewWeakGlobalRef. The reference is valid until you call DeleteGlobalRef.

To see if two reference refer to the same object via IsSameObject

AttachCurrentThread will never automatically free local reference until thread detaches.

+ Don't forget to Release the strings you 
+ Data passed to NewStringUTF must be in Modified UTF-8 format

Primitive Array? howto get and release

checkJNI: 
adb shell stop 
    adb shell setprop dalvik.vm.checkjni true 
    adb shell start 
or 
adb shell setprop debug.checkjni 1 
or  
set android::debuggable in manifest to use CheckJNI 

