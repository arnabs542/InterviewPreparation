package Recursion;

import java.io.*;
import java.util.*;

/*
Input Format: The first and only line of input should contain a string s, denoting the input string.
If s = “xy”, then input should be:
xy
Output Format:
Let’s denote the size of res as m, where res is the resultant array of strings returned by the solution function.
Then, there will be m lines of output, where ith line contains string at index i of res.
For input s = “xy”, output will be:
----------- START of output -----------
x
y
xy
----------- END of output ---------------
 */

public class GenerateAllSubsets {
    /*
     * Complete the function below.
     */
    static String[] generate_all_subsets(String s) {
        ArrayList<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.setLength(s.length());
        generateSubsets(s, 0, sb, 0, results);
        return results.toArray(new String[0]);
    }
    static void generateSubsets(String s, int i, StringBuilder sb, int j, ArrayList<String> results){
        if(i == s.length()){
            results.add(sb.substring(0,j));
            return;
        }else{
            generateSubsets(s, i+1, sb, j,results);
            sb.setCharAt(j,s.charAt(i));
            generateSubsets(s, i+1, sb, j+1,results);
        }

    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] res;
        String s;
        try {
            s = in.nextLine();
        } catch (Exception e) {
            s = "";
        }

        res = generate_all_subsets(s);
        for(int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }

}
