import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'countingSort' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
     
    private static void merge(List<Integer> a, int[] aux, int left, int mid, int right) {
        for(int k = left; k <= right; k++) {
            aux[k] = a.get(k);
        }
        int i = left, j = mid + 1;
        for(int k = left; k <= right; k++) {
            if(i > mid) a.set(k, aux[j++]);
            else if(j > right) a.set(k, aux[i++]);
            else if(aux[j] < aux[i]) a.set(k, aux[j++]);
            else a.set(k, aux[i++]);
        }
    }
     
    private static void mergeSort(List<Integer> a, int[] aux, int left, int right) {
        if(right <= left) return;
        int mid = left + (right - left) / 2;
        mergeSort(a, aux, left, mid);
        mergeSort(a, aux, mid + 1, right);
        merge(a, aux, left, mid, right);
    }

    public static List<Integer> countingSort(List<Integer> a) {
    // Write your code here
        int[] aux = new int[a.size()];
        mergeSort(a, aux, 0, a.size() - 1);
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i <= 99; i++){
            result.add(0);
        }
        for(Integer i : a) {
            result.set(i, result.get(i) + 1);
        }
        return result;
    }

}

public class CountingSortUsingMergeSort {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.countingSort(arr);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
