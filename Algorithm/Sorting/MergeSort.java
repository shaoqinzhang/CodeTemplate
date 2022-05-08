public class MergeSort {
    public static void sort(Comparable[] arrays) {
        int n = arrays.length;
        Comparable[] aux = new Comparable[arrays.length];
        sort(arrays, aux, 0, arrays.length-1);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }
    public static void sort(Comparable[] arrays, Comparable[] aux , int lo, int hi) {
        int n = arrays.length;
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arrays, aux, lo, mid);
        sort(arrays, aux, mid + 1, hi);
        merge(arrays, aux, lo, mid, hi);
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
        assert isSorted(a);
    }
}
