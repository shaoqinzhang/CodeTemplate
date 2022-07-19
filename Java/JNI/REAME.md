one process has only one JavaVM

env is associated with thread, so env can not be saved for using by other thread.

thread created by native must use AttachCurrentThread to associated with JVM, and use DetachCurrentThread finnaly.



