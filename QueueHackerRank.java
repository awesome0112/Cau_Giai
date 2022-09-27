import java.io.*;
import java.util.*;

public class QueueHackerRank {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Queue<Integer> q = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] CMD = new int[N];
        for(int i = 0; i < N; i++) {
            int cmd = sc.nextInt();
            if(cmd == 1) {
                int x = sc.nextInt();
                q.add(x);
            } else if(cmd == 2) {
                q.remove();
            } else {
                System.out.println(q.peek());
            }
        }
    }
}
