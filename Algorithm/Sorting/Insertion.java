public class Insertion {
    public static <Key extends Comparable<Key>> void sort(Key[] arrays) {
        int n = arrays.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(arrays[j], arrays[j - 1]); j--)
                exchange(arrays, j, j - 1);
        }
    }

    public static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    public static void exchange(Object[] arrays, int i, int j) {
        Object swap = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = swap;
    }

    private static <Key extends Comparable<Key>> boolean isSorted(Key[] a) {
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
