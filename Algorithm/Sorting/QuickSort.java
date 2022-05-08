import java.util.Random;

public class QuickSort {
    public static void shuffle(Comparable[] a) {
        int n = a.length;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n-i);     // between i and n-1
            Comparable temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }
    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i],v)){
                if (i == hi) {
                    break;
                }
            }
            while (less(v,a[--j])){
                if (j == lo) {
                    break;
                }
            }
            if (i > j) {
                break;
            }
            exchange(a,i,j);
        }
        exchange(a, lo, j);
        return j;
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
        Integer[] a = new Integer[]{5, 4, 3, 2, 1};
        sort(a);
        show(a);
        assert isSorted(a);
    }
}
