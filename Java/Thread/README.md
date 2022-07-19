# Exception

# wait, sleep, yield, join, notify, isAlive, setPriority, notifyAll

After waiting, if there is some exception, thread will give up resource

# difference between sleep and wait

```sleep()``` don't release block except fot interrupting. ```sleep()``` is thread method.

```wait()``` release lock. Wait is object method.



# shutdown and shutdownnow

# setUncaughtException

# callable and runnable

# preparestatement and callablestatement

# Five state

New, Runnable( a thread might actually be running or be ready to run), Blocked, Waiting, Timed Waiting, Terminated.

When calling thread.start(), it is in ready state.
