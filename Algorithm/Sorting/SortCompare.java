import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class SortCompare {
    public static  <Key extends Comparable<Key>>  double elapsedTime(String algorithm, Key[] comparables) {
        long start = System.currentTimeMillis();
        switch (algorithm) {
            case "Selection":
                Selection.sort(comparables);
                break;
            case "ShellSort":
                ShellSort.sort(comparables);
                break;
            case "InsertionWithSentinel":
                InsertionWithSentinel.sort(comparables);
                break;
            case "BinaryInsertion":
                BinaryInsertion.sort(comparables);
                break;
            case "Insertion":
                Insertion.sort(comparables);
                break;
            case "HeapSort":
                HeapSort.sort(comparables);
                break;
            default:
                break;
        }
        return (System.currentTimeMillis() - start) / 1000.0;
    }

    public static double timeRandomInput(String alg, int N, int T) {
        Random random = new Random();
        double total = 0.0;
        Double[] arrays = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                arrays[j] = random.nextDouble();
            }
            total += elapsedTime(alg, arrays);
        }
        return total;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        out.printf("For %d random Double\n %s is", N, alg1);
        out.printf(" %.1f times faster than %s\n", t2 / t1, alg2);
    }
}
