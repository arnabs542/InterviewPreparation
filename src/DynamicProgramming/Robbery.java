package DynamicProgramming;
import java.io.*;
import java.util.*;

/*

Input Format: The first line of input should contain a number n, denoting the number of houses. In next n lines, ith line should contain a number values[i], denoting ith index entry in values, i=(0,1,...,n-1).
If n = 4 and values = [6, 1, 2, 7], then input should be:
4
6
1
2
7
Output Format:
There will be one line, containing a number max, denoting the result returned by solution function.
For input n = 4 and values = [6, 1, 2, 7], output will be:
13


 */

public class Robbery {
    // Complete the maxStolenValue function below.
    static int maxStolenValue(int[] values) {

        int[] dp = createDp(values);
        return dp[values.length-1];
    }

    static int[] createDp(int[] values){
        int[] dp = new int[values.length];

        for(int i=0;i<=dp.length-1;i++){
            if(i==0){
                dp[i] = values[i];
            }else if(i==1){
                dp[i] = Math.max(values[i],dp[i-1]);
            }else{
                dp[i] = Math.max(values[i]+dp[i-2],dp[i-1]);
            }


        }
        return dp;

    }



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int valuesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] values = new int[valuesCount];

        for (int i = 0; i < valuesCount; i++) {
            int valuesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            values[i] = valuesItem;
        }

        int res = maxStolenValue(values);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
