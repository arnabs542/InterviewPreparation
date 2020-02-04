package DynamicProgramming;
import java.io.*;
import java.util.*;

/*
Input Format:
The first line of input should contain a single number n, denoting the number of rows of input matrix mat. Second line should contain a single integer m, denoting the number of columns of input matrix mat. In the next n lines, each line should contain m space separated numbers. jth number in ith line of these n lines is mat[i][j], denoting the number at ith row and jth column of matrix.
If n=3, m=3 and mat=[ [1,0,0], [0,1,1], [0,1,1]], then input should be:

3
3
1 0 0
0 1 1
0 1 1

Output Format:
There should be a single number representing result.
If n=3, m=3 and mat=[ [1,0,0], [0,1,1], [0,1,1]], then output should be:
2
 */
public class LargestSubSquareMatrixWithAll1s {
    /*
     * Complete the 'largest_sub_square_matrix' function below.
     *
     * @parama n: number of rows in mat
     *
     * @param m : number of columns in mat
     *
     * @param mat: 2D matrix of zeros and ones
     *
     */
    static int max = 0;
    public static int largest_sub_square_matrix(int n, int m, List<List<Integer>> mat) {
        // Write your code here
        int rows = mat.size();
        int cols = mat.get(0).size();

        int[][] dp = new int[rows+1][cols+1];

        for(int r = rows-1; r >=0 ; r--){
            for(int c = cols-1; c >=0; c--){
                if(mat.get(r).get(c) == 1){
                    dp[r][c] = 1 + getMin(dp[r+1][c], dp[r+1][c+1], dp[r][c+1]);
                    max = Math.max(max, dp[r][c]);
                }else{
                    dp[r][c] = 0;
                }
            }
        }
        return max;
    }

    static int getMin(int a, int b, int c){
        int min;
        min = a > b ? b : a;
        min = min > c ? c : min;
        return min;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> mat = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] matRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> matRowItems = new ArrayList<>();

            for (int j = 0; j < m; j++) {
                int matItem = Integer.parseInt(matRowTempItems[j]);
                matRowItems.add(matItem);
            }

            mat.add(matRowItems);
        }

        PrintStream writer = System.out;

        int result = largest_sub_square_matrix(n, m, mat);
        writer.println(result);

        bufferedReader.close();
        writer.close();
    }
}
