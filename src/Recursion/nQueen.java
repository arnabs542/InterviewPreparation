package Recursion;

import java.io.*;
import java.util.*;

/*

Input Format: The first and only line of input should contain an integer n.
If n = 4, then input should be:
4
Output Format:
There will be (m*(n+1)) lines of output, describing m different possible arrangements. Order of printing of arrangements will be as per order in res array, i.e. res[0], res[1], …, res[m-1].
For ith arrangement (described by res[i]):There will be total n + 1 lines. In first n lines, jth line contains a string res[i][j], denoting string at index j of res[i]. Last line will be an empty line.
For input n = 4, output will be:
-q--
---q
q---
--q-


--q-
q---
---q
-q--

https://algorithms.tutorialhorizon.com/backtracking-n-queens-problem-better-solution/

 */

public class nQueen {

    /*
     * Complete the function below.
     */
    static String[][] find_all_arrangements(int n) {
        ArrayList<String[]> results = new ArrayList<>();
        String[][] nQueens = new String[n][n];
        generate_arrangements(0, nQueens, results,n);
        return results.toArray(new String[0][]);
    }
    static void generate_arrangements(int column, String[][] nQueens ,ArrayList<String[]> results, int n){
        if(column == n){
            String[] row = new String[n];
            for(int i = 0; i< n;i++){

                StringBuilder sb = new StringBuilder();
                for(int j = 0; j<n; j++){
                    if(nQueens[i][j] != null)
                        sb.append(nQueens[i][j]);
                    else
                        sb.append("-");
                }
                row[i] = sb.toString();
                sb.setLength(0);
            }
            results.add(row);
            return;
        }
        for(int row = 0; row < n; row++){

            if(isValidState(nQueens, row, column))
            {
                nQueens[row][column] = "q";
                generate_arrangements(column+1, nQueens, results, n);
                nQueens[row][column] = null;

            }

        }
    }
    static boolean isValidState(String[][] nQueens, int row, int column){
        int i,j;
        int length = nQueens[0].length;
        for(int c = 0; c < column; c++){
            if("q".equals(nQueens[row][c]))
                return false;
        }
        for(i=row, j=column; i>=0 && j>=0; i--,j--){
            if("q".equals(nQueens[i][j]))
                return false;
        }
        for(i=row, j=column; i<length && j>=0; i++,j--){
            if("q".equals(nQueens[i][j]))
                return false;
        }
        return true;
    }




    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[][] res;
        int n;
        n = Integer.parseInt(in.nextLine().trim());

        res = find_all_arrangements(n);
        int res_rowLength = res.length;
        for(int res_i = 0; res_i < res_rowLength; res_i++) {
            for(int res_j = 0; res_j < res[res_i].length; res_j++) {
                bw.write(String.valueOf(res[res_i][res_j]) + "\n");
            }
            if (res_i != res_rowLength - 1) {
                bw.write("\n");
            }
        }

        bw.close();
    }
}
