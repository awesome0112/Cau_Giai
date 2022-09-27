import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Stack<String> s = new Stack<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String res = "";
        for(int i = 0; i < n; i++) {
            int cmd = sc.nextInt();
            switch (cmd) {
                case 1:
                    s.push(res);
                    String w = sc.next();
                    res += w;
                    break;

                case 2:
                    int k = sc.nextInt();
                    if(k >= res.length()){
                        s.push(res);
                        res = "";
                    } 
                    else {
                        s.push(res);
                        res = res.substring(0, res.length() - k);
                    }
                    break;

                case 3:
                    int a = sc.nextInt();
                    System.out.println(res.charAt(a - 1));
                    break;

                case 4:
                    res = s.pop();
                    break;
            }
        }
    }
}
