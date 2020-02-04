package DynamicProgramming;

import java.io.*;
import java.util.*;

/*

Input Format:
The first line of the input contains an integer n, denoting no. of coins denominations. In the next n lines, the ith line contains an integer coin denoting coins[i]. The n+2 th line of the input contains an integer value, denoting value for which coins denominations to found.
If coins=[1, 3, 5], value=9, then input should be:
3
1
3
5
9
Output Format:
The one and only line of output will contain a single integer representing the minimum number of coins required to make value.
For input coins=[1, 3, 5], value=9, result will be:
3

[1, 1, 1, 1, 1, 1, 1, 1, 1]
[1, 1, 1, 1, 1, 1, 3]
[1, 1, 1, 1, 5]
[1, 1, 1, 3, 3]
[1, 3, 5]
[3, 3, 3]
 */

public class MinimumCoins {

    /*
     * Complete the 'minimum_coins' function below.
     *
     * The function accepts INTEGER ARRAY and INTEGER as parameter.
     * Return INTEGER.
     */
    public static int minimum_coins(List<Integer> coins, int value) {
        // Write your code here
        int[] dp = new int[value+1];
        Arrays.fill(dp, value+1);
        dp[0] = 0;
        for(int i =1;i<= value; i++){
            for(int coin : coins){
                if(i >= coin){
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }
        return dp[value];
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

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        List<Integer> coins = new ArrayList<Integer>();

        for(int i=0;i<n;i++){
            coins.add(Integer.parseInt(bufferedReader.readLine().trim()));
        }
        int value = Integer.parseInt(bufferedReader.readLine().trim());

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int result = minimum_coins(coins, value);
        bufferedWriter.write(result+"\n");
        bufferedWriter.close();
    }
}
