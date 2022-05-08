# Code from https://algs4.cs.princeton.edu/home/

# Selection sort

+ Running time is insensitive to input. Time of randomly-ordered array is equal to ordered array.

+ Data movement is minimal, linear  function of the array size. Most sorting algorithms involve linearithmic or quadratic growth.

+ use ~$N^2/2$ compares and N exchanges to sort array of length $N$.

+ O(1) extra memory.

+ Selection sort is not stable.


# Insertion sort

+ O(1) extra memory.
+ Insertion sort algorithm is stable.
+ Insertion sort is faster on inputs that are partially-sorted.

# Shellsort

+ The sorting algorithm is not stable.
+ O(1) extra memory.
+ every *h*th entry yields a sorted subsequence.
+ an h-sorted array is h independent sorted subsequence interleaved together.
+ time is less then $N^2$. with [Knuth's increment sequence](https://oeis.org/A003462) (1, 4, 13, 40, ...),  In the worst case, this implementation makes $ N^{3/2}$compares and exchanges to sort an array of length *n*.

# MergeSort

+ Divide-and-conquer paradigm
+ Top-down mergesort uses  between $1/2NlgN$ and $NlgN$ compares to sort.
+ Top-down mergesort uses at most $6NlgN$ array accesses.
+ Bottom-up mergesort is the method of choice for sorting data organized in a linked list.
+ it uses extra space proportional to N.
+ This sorting algorithm is stable
+ No compare-based sorting algorithm can guarantee to sort N items with fewer than lg(N!) ~ N lg N compares.
+ Mergesort is an asymptotically optimal compare-based sorting algorithm. That is, both the number of compares used by mergesort in the worst case and the minimum number of compares that any compare-based sorting algorithm can guarantee are ~N lg N.

# QuickSort

+ It is in-place (uses only a small auxiliary stack), requires time proportional to N log N on the average to sort N items, and has an extremely short inner loop.
+ Quicksort is a divide-and-conquer method for sorting.
+ When two sub-arrays are sorted, the whole array is ordered.
+ rearranges the array to make the following three conditions hold:

  - The entry a[j] is in its final place in the array, for some j .

  - No entry in a[lo] through  a[j-1] is greater than a[j] .
  - No entry in a[j+1] through a[hi] is less than a[j].
+ Qicksort uses ~$2 N ln N$ compares (and one-sixth that many exchanges) on the average to sort an array of length N with distinct keys
+ Quicksort uses ~$N^2/2$ compares in the worst case, but random shuffling protects against this case.
+ *Cutoff to insertion sort.* As with mergesort, it pays to switch to insertion sort for tiny arrays. The optimum value of the cutoff is system-dependent, but any value between 5 and 15 is likely to work well in most situations.
+ *Median-of-three partitioning.*  It turns out that most of the available improvement comes from choosing a sample of size 3 (and then partitioning on the middle item).
+ Quicksort with 3-way partitioning is entropy-optimal:
	- a[i] less than v: exchange a[lt] with a[i] and increment both lt and i
	- a[i] greater than v: exchange a[i] with a[gt] and decrement gt
	- a[i] equal to v: increment i
# Priority Queues

+ remove the maximum and insert.
+  binary tree is *heap-ordered* if the key in each node is larger than (or equal to) the keys in that nodes two children (if any).
+  The heap operations that we consider work by first making a simple modification that could violate the heap condition, then traveling through the heap, modifying the heap as required to ensure that the heap condition is satisfied everywhere. We refer to this process as *reheapifying*, or *restoring heap order*.
