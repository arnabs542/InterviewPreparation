package Recursion;
import java.io.*;
import java.util.*;
/*

Input Format: The first and only line of input should contain a string s, denoting the input string.
If s = “abracadabra”, then input should be:
abracadabra
Output Format:
Let’s denote size of res array as m, where res is the resultant array of string returned by solution function.
Then, there will be m lines of output, where ith line contains a string res[i], denoting string at index i of res.
For input s = “abracadabra”, output will be:
a|b|r|a|c|ada|b|r|a
a|b|r|aca|d|a|b|r|a
a|b|r|a|c|a|d|a|b|r|a
 */

public class GenerateAllPalindromicDecompositions {

    /*
     * Complete the function below.
     */
    static String[] generate_palindromic_decompositions(String s) {
        ArrayList<String> results = new ArrayList<>();
        StringBuilder intermediateResults  = new StringBuilder();
        generateAllPalindrome(s, 0, intermediateResults,results);
        return results.toArray(new String[0]);
    }

    static void generateAllPalindrome(String s, int index, StringBuilder intermediate, ArrayList<String> results){
        if(index == s.length()){
            results.add(intermediate.toString());
            return;
        }else{
            for(int i = index ; i< s.length(); i ++){
                //   System.out.println("String : "+d s+" index : "+index+" i : "+i + " substring("+index+", "+i+1+") : " + s.substring(index, i+1));
                if(isPalindrome(s, index, i)){
                    String palindrome = s.substring(index, i+1);
                    int length = palindrome.length();
                    intermediate.append(palindrome);
                    if(i != s.length()-1){
                        length++;
                        intermediate.append("|");
                    }
                    generateAllPalindrome(s, i+1, intermediate, results);
                    intermediate.setLength(intermediate.length()-length);
                }
            }
        }
    }
    static boolean isPalindrome(String s, int start, int end){
        while(start<=end){
            if(s.charAt(start)!=s.charAt(end))
                return false;
            ++start;
            --end;
        }
        return true;
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

        res = generate_palindromic_decompositions(s);
        for(int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}
