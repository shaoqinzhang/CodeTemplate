/* 
jclass
jmethodID
jfieldID
FindClass to get calss object referrence
GetFiledID to get the filed
GetIntFiled to get the contents of the filed
jcalss reference must be protected with a call to NewGlobalRef
 */

jclass localClass = env->FindClass("MyClass");
jclass globalClass = reinterpret_cast<jclass>(env-NewGlobalRef(localClass));

/* 
mode can be 0, JNI_COMMIT, JNI_ABORT
 */
jbyte* data = env->GetByteArrayElements(array, NULL);
if (data != NULL) {
    memcpy(buffer, data, len);
    env->ReleaseByteArrayElements(array, data, JNI_ABORT);
}
//more simply way
env->GetByteArrayRegion(array, 0, len, buffer);