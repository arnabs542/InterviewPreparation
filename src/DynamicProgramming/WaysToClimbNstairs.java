package DynamicProgramming;
import java.io.*;
import java.util.*;

/*
Input Format:
The first line should contain m(let say), denoting size of steps array. In next m lines, ith line should contain steps[i], denoting ith value in array steps. In the next line, there should be an integer, denoting n.
If n = 7 and steps = [2, 3], then input should be:
2
2
3
7
Output Format:
There will be one line, containing an integer ways, denoting the number of ways to reach the last staircase.
For input n = 7 and steps = [2, 3], output will be:
Three ways to reach = [{2, 2, 3}, {2, 3, 2}, {3, 2, 2}]
 */

public class WaysToClimbNstairs {
    static long countWaysToClimb(int[] steps, int n) {
        long[] countTill = new long[n+1];

        countTill[0] = 1;
        for(int i=1;i<=n;i++){
            for(int step : steps){
                int prev = i - step;
                if(prev>=0){
                    countTill[i] += countTill[prev];
                }
            }

        }
        return countTill[n];
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int stepsCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] steps = new int[stepsCount];

        for (int i = 0; i < stepsCount; i++) {
            int stepsItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            steps[i] = stepsItem;
        }

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long res = countWaysToClimb(steps, n);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
