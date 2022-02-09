jobject callback;
jclass TestCallBck = env->GetObjectClass(callback);
jmethod oncall = env->GetMethodID(TestCallBck,"functionName","()V");
env->CallVoidMethod(callback, oncall);
