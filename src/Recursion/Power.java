package Recursion;
import java.io.*;

/*
Input Format:

First line of the input contains one single integer a, denoting the value of the base and the second line also contains one single integer denoting the value of the exponent.
If a = 2 and b = 10, then custom input format will be:
2
10
Output Format:
Print one single line containing one integer denoting the calculated value of a^b % 1000000007.
For the above provided custom input, output would be:
1024

 */

public class Power {
    /*
     * Complete the 'calculate_power' function below.
     *
     * Complete the calculate_power function below.
     *
     * @param a : base
     *
     * @param b : exponent
     */

    private static long MOD = 1000000007;

    public static int calculate_power(long a, long b) {
        //This is using recursion

        //TC = O(logb)
        //SC = O(logb)

        //Base Case
        if (b == 0) {
            return 1;
        }

        a = a % MOD;

        long halfResult = calculate_power(a, b/2);

        //Even
        halfResult = halfResult * halfResult % MOD;

        //Odd
        if (b % 2 == 1) {
            halfResult = halfResult * a % MOD;
        }
        return (int)halfResult;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        long a = Long.parseLong(bufferedReader.readLine().trim());
        long b = Long.parseLong(bufferedReader.readLine().trim());

        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int result = calculate_power(a, b);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}



/*

Description: In this approach we will use the same power doubling principal as used in the other_solution approach. In the above solution we used top down approach by spiting into half at each step. Here, in this approach we will solve it using bottom up approach by doubling at each step. Below is the mathematical illustration of the approach using an example :
So, this will be our approach in solving this problem in bottom-up fashion. We will start with the least significant bit of the exponent when represented in binary and for each set bit we keep on multiplying that power of the base into our final result. Kindly, refer to the implementation for better understanding.
Time Complexity (assuming that input arguments are already given and excluding time used in declaration of output):
O(log(b)), where b is the exponent.
To evaluate the power we are iterating over all set-bits of the exponent which takes O(b) iterations.
Time Complexity:
O(log(b)), where b is the exponent.
To evaluate the power we are iterating over all set-bits of the exponent which takes O(b) iterations.
Auxiliary Space Used:
O(1)
This is a bottom up iterative approach. Hence, no stack memory is consumed in recursive function calls as compared to the other_solution.
Space Complexity:
O(1)
The total space complexity is equal to the auxiliary space plus the input and output space. Here the input and output space complexity is constant O(1) as we are only taking two integers as input and returning one integer as output. Hence, the total space complexity becomes O(1) + O(1) ~ O(1).

 */