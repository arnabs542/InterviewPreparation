package String;
import java.io.*;
import java.util.*;

/*
Input Format:
The first line of the input should contain an integer t, denoting no. of test cases.
In the next t lines, ith line should contain a string si, denoting an input string s for ith test case.
If t = 3, s for 1st test case = “ababababa”, s for 2nd test case = “e” and s for 3rd test case = “baabcbab”, then input should be:
3
ababababa
e
baabcbab
Output Format:
There will be t lines for output, where ith line contains an integer leni, denoting resultant value len for ith test case.
For input t = 3, s for 1st test case = “ababababa”, s for 2nd test case = “e” and s for 3rd test case = “baabcbab”, output will be:
9
0
4
 */

public class LongestSubstringLengthExactlyTwoDistinctChars {
    // Complete the getLongestSubstringLengthExactly2DistinctChars function below.
    static int getLongestSubstringLengthExactly2DistinctChars(String s) {
        int left = 0;
        int right = 0;
        int maxLength = 0;
        HashMap<Character, Integer> freq = new HashMap();

        while(right < s.length()){
            if(freq.containsKey(s.charAt(right))){
                freq.put(s.charAt(right), freq.get(s.charAt(right))+1);
            }else{
                freq.put(s.charAt(right), 1);
            }

            if(freq.size() > 2){
                freq.put(s.charAt(left), freq.get(s.charAt(left))-1);
                if(freq.get(s.charAt(left)) == 0){
                    freq.remove(s.charAt(left));
                }
                left++;
            }
            if(freq.size() == 2){
                maxLength = Math.max(maxLength, right -left +1);
            }
            right++;
        }
        return maxLength;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int q = Integer.parseInt(bufferedReader.readLine().trim());
        while (q-- > 0) {
            String s = bufferedReader.readLine();

            int res = getLongestSubstringLengthExactly2DistinctChars(s);

            bufferedWriter.write(String.valueOf(res));
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
