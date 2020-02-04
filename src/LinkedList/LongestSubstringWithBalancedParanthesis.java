package LinkedList;
import java.io.*;
import java.util.*;

/*
Input Format:
There should be only one line of input, containing a string brackets, denoting input string of parentheses.
If brackets = “((((())(((()”, then input should be:
((((())(((()
Output Format:
There will be one line, containing an integer res, denoting the result returned by the solution function.
For input brackets = “((((())(((()”, output will be:
4
 */

public class LongestSubstringWithBalancedParanthesis {

    /*
     * Complete the function below.
     */
    static int find_max_length_of_matching_parentheses(String brackets) {
        int n = brackets.length();
        Stack<Integer> stack = new Stack();
        int maxLength = 0;
        int validFrom = 0;

        for(int i = 0; i<brackets.length(); i++){
            if( brackets.charAt(i) == '('){
                stack.push(i);
            }else{
                if(stack.empty()){
                    validFrom = i+1;
                }else{
                    int index = stack.pop();
                    if(stack.empty()){
                        maxLength = Math.max(i- validFrom+1, maxLength);
                    }
                    else{
                        maxLength = Math.max(i-stack.peek(), maxLength);
                    }
                }

            }
        }
        return maxLength;

    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int res;
        String brackets;
        try {
            brackets = in.nextLine();
        } catch (Exception e) {
            brackets = null;
        }

        res = find_max_length_of_matching_parentheses(brackets);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }


}
