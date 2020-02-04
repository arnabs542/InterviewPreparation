package Recursion;
import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/*
Input Format: There should be one line for input, containing a single integer n, denoting the number of disks in the first peg.
If n = 4 then input should be:
4
Output Format:
There will be a 2d array of integers, where ith row of result 2d array will denote ith step taken to reach the objective. Each row will have two integers denoting from peg and to peg, for example, if the ith row is "2 3" then it means, in this step, we moved top disk on peg 2 to peg 3.
For input n = 4, the output will be as follows:
1 2
1 3
2 3
1 2
3 1
3 2
1 2
1 3
2 3
2 1
3 1
2 3
1 2
1 3
2 3


 */
public class TowerOfHanoi {

    /*
     * Complete the 'tower_of_hanoi' function below.
     *
     * The function accepts INTEGER as parameter.
     * Return 2D INTEGER ARRAY.
     */
    static List<List<Integer>> res = new ArrayList<>();

    static void helper(int rem, int src, int dst, int aux) {
        if (rem == 1){
            res.add(Arrays.asList(src, dst));
            return;
        }

        helper(rem-1, src, aux, dst);
        res.add(Arrays.asList(src, dst));
        helper(rem-1, aux, dst, src);
        return;
    }
    public static List<List<Integer>> tower_of_hanoi(int n) {
        // Write your code here
        helper (n, 1, 3, 2);
        return res;

    }

    public static void main(String args[]) {
        /*
        This function is used to increase the size of recursion stack. It makes the size of stack
        2^26 ~= 10^8
        */
        new Thread(null, new Runnable() {
            public void run() {
                try{
                    solve();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }
    public static void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        List<List<Integer>> result = tower_of_hanoi(n);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedWriter.close();
        bufferedReader.close();
    }


}
/*

static int[][] steps_in_tower_of_hanoi(int no_of_disks) {

    move_tower_of_hanoi(no_of_disks, "S", "D", "T");
    return new int[1][1];
}

    static void move_tower_of_hanoi(int n , String source, String destination, String temp){
        if(n==1){
            System.out.println("Moved 1 from source : "+ source+ " to : "+destination);
            return ;
        }else{
            move_tower_of_hanoi(n-1, source, temp, destination);
            System.out.println("Moved "+n+" from source : "+ source+ " to : "+destination);
            move_tower_of_hanoi(n-1, temp, destination, source);
        }
    }



 */