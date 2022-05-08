public class BinaryInsertion {
    public static <Key extends Comparable<Key>> void sort(Key[] arrays) {
        int n = arrays.length;
        for (int i = 1; i < n; i++) {

            // binary search to determine index j at which to insert arrays[i]
            Key v = arrays[i];
            int lo = 0, hi = i;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (less(v, arrays[mid])) hi = mid;
                else lo = mid + 1;
            }

            // insetion sort with "half exchanges"
            // (insert arrays[i] at index j and shift arrays[j], ..., arrays[i-1] to right)
            for (int j = i; j > lo; --j)
                arrays[j] = arrays[j - 1];
            arrays[lo] = v;
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

        private static <Key extends Comparable<Key>> boolean isSorted(Key[] arrays) {
            for (int i = 1; i < arrays.length; i++)
                if (less(arrays[i], arrays[i-1])) return false;
            return true;
        }

        private static  <Key extends Comparable<Key>> void show(Key[] arrays) {
            for (int i = 0; i < arrays.length; i++) {
                System.out.println(arrays[i]);
            }
        }
        public static void main(String[] args) {
            Integer[] a = new Integer[]{5, 4, 3, 2, 1};
            sort(a);
            show(a);
            assert isSorted(a);
        }
}
