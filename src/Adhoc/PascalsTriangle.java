package Adhoc;
import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/*
Input Format: There should be one line for input, containing a single integer n, denoting the number of lines of Pascal's triangle to be considered.
If n = 6, then input should be:
6
Output Format:
There will be 2d array of integers, where each row of result 2d array will denotes row of pascal’s triangle in same order.
For input n = 6, output will be:
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
1 5 10 10 5 1


 */

public class PascalsTriangle {
    // -------------------- START ----------------------
    /*
     * Complete the 'findPascalTriangle' function below.
     *
     * The function accepts INTEGER as parameter.
     * Return 2D INTEGER ARRAY.
     */
    static List<List<Integer>> findPascalTriangle(int n) {
        int mod = 1000000007;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++){
            // Every ith row has number of integers
            // equal to row number
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++){
                // First and last values in every row are 1
                if (i == j || j == 0){
                    row.add(1);
                }
                // Other values are sum of values just
                // above and left of above
                else{
                    row.add((result.get(i-1).get(j-1) + result.get(i-1).get(j))%mod);
                }
            }
            result.add(row);
        }
        return result;
    }
    // -------------------- END ----------------------
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
        List<List<Integer>> result = findPascalTriangle(n);

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
