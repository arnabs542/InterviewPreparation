package DynamicProgramming;
import java.io.*;
import java.util.*;
/*
Input Format:
The first line should contain an integer n, denoting no. of coins. In next n lines, ith line should contain an integer vi, denoting value of ith coin in input array v.
If n = 4 and v = [8, 15, 3, 7], then input should be:
4
8
15
3
7
Output Format:
There will be only one line, containing an integer max, denoting the maximum possible amount of sum that you can accumulate.
For input n = 4 and v = [8, 15, 3, 7], output will be:
22

 */
public class CoinPlay {

    // Complete the maxWin function below.
    static int maxWin(int[] v) {
        int[][] dptable = createDpTable(v);
        return dptable[0][v.length-1];
    }
    static int[][] createDpTable(int[] v){
        int rows = v.length;
        int cols = v.length;
        int[][] dptable = new int[rows][cols];
        for(int i = rows-1; i>=0; i--){
            for(int j = 0; j< cols;j++){
                if(j == i){
                    dptable[i][j] = v[i];
                }else if(j == i+1){
                    dptable[i][j] = Math.max(v[i],v[j]);
                }else if(i>j){
                    dptable[i][j] =0;
                }else{
                    int move1 = v[i]+Math.min(dptable[i+2][j], dptable[i+1][j-1]);
                    int move2 = v[j]+Math.min(dptable[i][j-2],dptable[i+1][j-1]);
                    dptable[i][j] = Math.max(move1, move2);
                }
            }
        }
        return dptable;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int vCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] v = new int[vCount];

        for (int i = 0; i < vCount; i++) {
            int vItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            v[i] = vItem;
        }

        int res = maxWin(v);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
/*
Recursive solution
There are two choices:
1. The user chooses the ith coin with value Vi: The opponent either chooses (i+1)th coin or jth coin. The opponent intends to choose the coin which leaves the user with minimum value.
i.e. The user can collect the value Vi + min(F(i+2, j), F(i+1, j-1) )
coinGame1
2. The user chooses the jth coin with value Vj: The opponent either chooses ith coin or (j-1)th coin. The opponent intends to choose the coin which leaves the user with minimum value.
i.e. The user can collect the value Vj + min(F(i+1, j-1), F(i, j-2) )
Recurrence relationship
F(i, j) ==> represents the maximum value the user can collect from i'th coin to j'th coin.
F(i, j) = Max(Vi + min(F(i+2, j), F(i+1, j-1)), Vj + min(F(i+1, j-1), F(i, j-2) ))
Base Cases
  F(i, j) = Vi      If j == i
  F(i, j) = max(Vi, Vj) If j == i+1
Optimal solution
We can memoize the recurrence relationship mentioned above or build an iterative version for the same problem.
Space Complexity: O(n^2)
Time Complexity: O(n^2)


Player 1 can start two different ways: either picking 8 or picking 7. Depending on the choice s/he makes, the end reward will be different. We want to find the maximum reward the first player can collect.



1. Player 1 start by picking coin of amount 8.

Remaining v = [15, 3, 7].

Opponent will have two choices, either pick coin of value 15 or 7. Opponent will always pick 15 (to maximize his/her own amount).

Remaining v = [3, 7].

Player 1 will have two choices, either pick coin of value 3 or 7.

Player 1 will always pick 7 (to maximize his/her own amount).

Hence in this case, player 1 can get maximum amount 8 + 7 = 15.



(This is greedy strategy i.e. pick the highest at every step.)



2. Player 1 start by picking coin of amount 7.

Remaining v = [8, 15, 3].

Opponent will have two choices, either pick coin of value 8 or 3.

Opponent will pick 8 (to maximize his/her own amount). (Even if he/she picks 3, then also answer will be same, because in next turn player 1 is looking for coin of value 15.)

Remaining v = [15, 3].

Player 1 will have two choices, either pick coin of value 15 or 3.

Player 1 will always pick 15 (to maximize his/her own amount).

Hence in this case, player 1 can get maximum amount 7 + 15 = 22.
 */

