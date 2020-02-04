package DynamicProgramming;

import java.io.*;
import java.util.*;
/*
nput Format: The first line of input should contain a number, denoting the size of mtxSizes, which is (n+1).
In next (n+1) lines, ith line should contain ith entry of mtxSizes, i=(1,...,n+1).
If n = 4 and mtxSizes = [10, 30, 5, 60], then input should be:
4
10
30
5
60
Output Format:
There will be one line, containing an integer minOps, denoting the result returned by solution function.
For input n = 4 and mtxSizes = [10, 30, 5, 60], output will be:
4500

However, the order in which we parenthesize the product affects the number of simple arithmetic operations needed to compute the product, or the efficiency. For example, suppose A is a 10 × 30 matrix, B is a 30 × 5 matrix, and C is a 5 × 60 matrix. Then,
(AB)C = (10×30×5) + (10×5×60) = 1500 + 3000 = 4500 operations
A(BC) = (30×5×60) + (10×30×60) = 9000 + 18000 = 27000 operations.
 */
public class MatrixChainMultiplication {


    static int minMultiplicationCost(int[] mtxSizes) {
        int len = mtxSizes.length;
        int[][] dp = new int[len][len];
        for(int i = 0;i < len; i++){
            for(int j=0; j<len; j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        return minCost(0, len-1, mtxSizes, dp);
    }

    static int minCost(int start, int end, int[] mtxSizes, int[][] dp){
        if(dp[start][end]!=Integer.MAX_VALUE){
            return dp[start][end];
        }
        if(start+1 == end){
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for(int index = start+1; index<=end-1; index++){
            int cost = mtxSizes[start] * mtxSizes[index] * mtxSizes[end] + minCost(start, index, mtxSizes, dp)
                    + minCost(index, end, mtxSizes, dp);
            min = Math.min(min, cost);
        }
        dp[start][end] = min;
        return dp[start][end];
    }


    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int _mtxSizes_size = 0;
        _mtxSizes_size = Integer.parseInt(in.nextLine().trim());
        int[] _mtxSizes = new int[_mtxSizes_size];
        int _mtxSizes_item;
        for(int _mtxSizes_i = 0; _mtxSizes_i < _mtxSizes_size; _mtxSizes_i++) {
            _mtxSizes_item = Integer.parseInt(in.nextLine().trim());
            _mtxSizes[_mtxSizes_i] = _mtxSizes_item;
        }

        int res = minMultiplicationCost(_mtxSizes);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}

/*
Recursive solution



A function f(start, end) is defined to denote the minimum operations needed for solving the mtxSizes in the range start…end



Thus the base case would be

f(i, i+1) = 0

This is because it is denoting a single matrix.



Lets put a parenthesis at index. We can recur for the cost for start…index and index…end.



cost[index] = mtxSizes[start]*mtxSizes[index]*mtxSizes[end] + f(start, index) + f(index, end)



Recurrence relationship for this problem would be

f(start, end) = min(cost[index])

where start<index<end



Optimal solution



We can memoize the recurrence relationship mentioned above or build an iterative version for the same problem. We can see there are overlapping subproblems.

We can maintain a 2D table of size nxn. Thus every table cell denotes f(i,j).



Space Complexity: O(length(mtxSizes)^2)

Time Complexity: O(length(mtxSizes)^3)


 */