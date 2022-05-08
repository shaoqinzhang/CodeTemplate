public class HeapSort {
    public static void sort(Comparable[] comparables) {
        int N = comparables.length;
        for (int k = N / 2; k >= 1; k--) {
            sink(comparables, k, N);
        }
        while (N > 1) {
            exchange(comparables, 0, --N);
            sink(comparables, 1, N);
        }
        assert isSorted(comparables);
    }

    public static void sink(Comparable[] comparables, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(comparables[j-1], comparables[j])) {
                j++;
            }
            if (!less(comparables[k-1], comparables[j-1])) {
               break;
            }
            exchange(comparables, k-1 , j-1 );
            k=j;
        }
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exchange(Comparable[] arrays, int i, int j) {
        Comparable swap = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = swap;
    }
    private static  boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{5, 3, 2, 4, 1};
        sort(a);
        show(a);

    }
}
