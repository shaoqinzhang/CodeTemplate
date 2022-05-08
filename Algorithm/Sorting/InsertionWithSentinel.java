public class InsertionWithSentinel {
    public static <Key extends Comparable<Key>> void sort(Key[] arrays) {
        int n = arrays.length;

        int exchanges = 0;
        for (int i = n-1; i > 0; i--) {
            if (less(arrays[i], arrays[i-1])) {
                exchange(arrays, i, i-1);
                exchanges++;
            }
        }
        if (exchanges == 0) return;
        for (int i = 2; i < n; i++) {
            Key v = arrays[i];
            int j = i;
            while (less(v, arrays[j-1])) {
                arrays[j] = arrays[j-1];
                j--;
            }
            arrays[j] = v;
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
