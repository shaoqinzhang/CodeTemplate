# sort

sort function in #include<algorithm>

sort(start,end)
sort(start,end,compare), compare is a bool function

# memory

+ static local var
+ static global var
+ dynamic local var ->out of memory
+ dynamic global var

# [include file order](https://stackoverflow.com/questions/2762568/c-c-include-header-file-order)

> + The prototype/interface header for this implementation (ie, the .h/.hh file that corresponds to this .cpp/.cc file).
> + Other headers from the same project, as needed.
> + Headers from other non-standard, non-system libraries (for example, Qt, Eigen, etc).
> + Headers from other "almost-standard" libraries (for example, Boost)
> + Standard C++ headers (for example, iostream, functional, etc.)
> + Standard C headers (for example, cstdint, dirent.h, etc.)

# static global var vs static local var

global var in single c file -> static global

global var in single function -> static local

# reentrant

pay attention to dynamic global, static local and static global

# the benefit of inline function

type check, no function call

# [off by one error](https://stackoverflow.com/questions/2939869/what-is-an-off-by-one-error-and-how-do-i-fix-it)

# [Difference between private, public, and protected inheritance](https://stackoverflow.com/questions/860339/difference-between-private-public-and-protected-inheritance/1372858#1372858)

# override and overload

 override between class with the same signature method.

 overload in a single class with different parameters

 # final and override

 C++11

 # inline

 The same function definition decorated with the inline keyword can appear in multiple translation units at the same time
 
# int* p[] and int (*p)[]

# float epsilon

e-5


