# Memory Layout

//to do

# Practice

* set minimal heap size using -Xms. eg. -Xms20m.

* set maximal heap size using -Xmx. eg. -Xmx20m.

* dump current heap snapshot when out of memory using -XX:+HeapDumpOnOutOfMemoryError.

* set native method size using -Xoss( may not take effect in HotSpot VM because VM do not distinguish java vm stack and native method stacks ).

* set stack size using -Xss. eg. -Xss180k.

* -XX:PermSize and -XX:MaxPermSize are was removed in 8.0. now using -XX:MetaspaceSize and -XX:MaxMetaspaceSize. eg. -XX:MetaspaceSize=1M

* -XX:MaxDirectMemorySize=10M

# Test Case

// to do add code

  ## test 1

OOM using -Xms and -Xmx

## test 2

stackoverflowerror use -Xss

## test 3

stackoverflowerror using expanding local variable list

## test 4

OOM using create multihread and make every thread using a large method stack size. 





# Hprof File Analysis

// to do

