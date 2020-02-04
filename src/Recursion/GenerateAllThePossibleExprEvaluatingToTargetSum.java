package Recursion;

import java.io.*;
import java.util.*;
/*

Input Format:
The first line of input should contain a string s, denoting input string. The second line should contain an integer target, denoting the target value as explained in problem statement section.
If s = “222” and target = 24, then input should be:
222
24
Output Format:
Let’s denote the size of res as m, where res is the resultant array of strings returned by solution function.
Then, there will be m lines of output, where ith line contains a string res[i], denoting value at index i of res.
For input s = “222” and target = 24, output will be:
2+22
22+2
 */
public class GenerateAllThePossibleExprEvaluatingToTargetSum {

    /*
     * Complete the function below.
     */
    static String[] generate_all_expressions(String s, long target) {
        ArrayList<String> result = new ArrayList<>();
        generate(s, result,"",0, 0, target, 0);
        String[] array = result.toArray(new String[0]);
        return array;
    }

    static void generate(String s, List<String> result, String path, int index, long evaluatedSoFar, long target, long prev){

        if(index==s.length()){
            if(target == evaluatedSoFar)
                result.add(path);
            return;
        }
        else{
            for(int i = index; i<s.length(); i++){
                String prefix = s.substring(index, i+1);
//                System.out.println(prefix);
                long curr = Long.parseLong(s.substring(index, i+1));
                if(index == 0) {
                    generate(s, result, path + prefix, i+1, curr, target, curr );
                }else{
                    generate(s, result, path + "*" + prefix, i+1, evaluatedSoFar - prev + prev * curr, target, prev * curr);
                    generate(s, result, path + "+" + prefix, i+1, evaluatedSoFar + curr, target, curr);
                }
            }
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
            s = null;
        }

        long target;
        target = Long.parseLong(in.nextLine().trim());

        res = generate_all_expressions(s, target);
        for(int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}
