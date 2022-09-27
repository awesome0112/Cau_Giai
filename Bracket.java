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
     * Complete the 'isBalanced' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    private static boolean isOpenBracket(char x) {
        return x == '[' || x == '{' || x == '(';
    }

    private static char Add(char x) {
        if(x == '[') return ']';
        else if(x == '{') return '}';
        else return ')';
    }

    public static String isBalanced(String s) {
        Stack<Character> x = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(isOpenBracket(s.charAt(i))) {
                x.add(Add(s.charAt(i)));
            } else {
                if(x.size() == 0) return "NO";
                else if(s.charAt(i) != x.pop()) return "NO";
            }
        }
        if(x.size() == 0) return "YES";
        else return "NO";
    }

}

public class Bracket {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
