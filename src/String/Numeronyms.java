package String;
import java.io.*;
import java.util.*;

/*
Input Format: Only one argument denoting input string word.
Output Format: Return strings array containing all possible numeronyms of given string word.
word = “nailed”
You need not to worry about order of strings in your output array. For words = "aaaaa", arrays ["a2aa", "aa2a", "a3a"], ["a3a", "aa2a", "a2aa"] etc will be considered as valid answer.
In case of no possible numeronym string, return empty list.
["n4d", "na3d", "n3ed", "n2led", "na2ed", "nai2d"]

 */
public class Numeronyms {

    /*
     * Complete the neuronyms function below.
     */
    static String[] neuronyms(String word) {
        /*
         * Write your code here.
         */
        int n = word.length();

        List<String> result = new ArrayList<>();
        for(int i = 1 ; i < n-2; i++){
            for(int j = i+2; j<n; j++){
                int len = j - i;
                result.add(word.substring(0,i)+len+word.substring(j,n));
            }
        }
        return result.toArray(new String[result.size()]);

    /*
    static String[] neuronyms(String word) {
        int n = word.length();

        List<String> neuronyms_strings = new ArrayList<>();

        String answer[];

        if(n<=3){
            answer = new String[0];
            return answer;
        }

        String temp;
        // Iterating over all possible length of valid substrings that can be omitted
        for (int len = 2; len <= n - 2; len++) {
            // Iterating over all possible starting point of valid substrings of length len that can be omitted
            for (int i = 1; i <= n-1-len; i++) {
                temp = word.substring(0, i) + len + word.substring(i + len, n);
                neuronyms_strings.add(temp);
            }
        }

        int size = neuronyms_strings.size();
        answer = new String[size];

        for(int i=0;i<size;i++){
            answer[i] = neuronyms_strings.get(i);
        }

        return answer;
    }

     */
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String word = scanner.nextLine();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        String[] res = neuronyms(word);

        for (int resItr = 0; resItr < res.length; resItr++) {
            bufferedWriter.write(res[resItr]);

            if (resItr != res.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
