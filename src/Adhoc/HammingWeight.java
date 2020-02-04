package Adhoc;
import java.io.*;
import java.util.*;

/*
Input Format:
First line of the input contains one single integer n, denoting number of elements in array s.
Next n lines of the input, each line contains single integer denoting the ith element in the array s.
If n = 3 and s = [ 1, 2, 3 ], then custom input format will be:
3
1
2
3
Output Format:
Print one single line containing one integer denoting the total hamming weight of the input array s.
For the above provided custom input, output would be:
4
 */
public class HammingWeight {

    /*
     * Complete the 'calculateHammingWeight' function below.
     *
     * Complete the calculateHammingWeight function below.
     *
     * @param s : input array as parameter.
     */

    public static int calculateHammingWeight(List<Long> s) {
        // Write your code here
        int count = 0;

        // iterate thru "column" or bit position
        // Note: you could stop at 10^9 as stated in the problem if you want to optimize
        for (int i = 0; i < 32; i++)
        {
            long mask = 1 << i;
            int countOnes = 0;
            for(long x : s)
            {
                if ((x & mask) != 0) countOnes++;
            }

            count += countOnes;
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> s = new ArrayList<Long>();

        for (int i = 0; i < n; i++) {
            Long s_item = Long.parseLong(bufferedReader.readLine().trim());
            s.add(s_item);
        }

        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int result = calculateHammingWeight(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
