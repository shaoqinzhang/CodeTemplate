// save jobject globally
jobject callback;
jobject globalCallBack;
setCallBack(jobject callback) {
    globalCallBack = env->NewGlobalRef(callback);
}
//  pay attention to
env->DeleteGlobalRef(globalCallBack);

//  call function
vm->GetEnv(&env);
vm->AttachCurrentThread(&env,Null);
jclass TestCallBck = env->GetObjectClass(callback);
jmethod oncall = env->GetMethodID(TestCallBck,"functionName","()V");
env->CallVoidMethod(callback, oncall);
vm->DetachCurrentThread();