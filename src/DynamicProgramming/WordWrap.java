package DynamicProgramming;
import java.io.*;
import java.util.*;
/*
Sample Test Case 2 Sample Input 2

4
omg
very
are
extreme
10

words = [omg, very, are, extreme]
limit = 10
Sample Output 2
result = 351
Explanation 2

Following arrangement of words in lines will have least cost:
Line1: “omg very  ”
Line2: “are       ”
Line3: “extreme”
Note that we need to ignore the extra white spaces at the end of last line. So, in the last line there will be 0 extra white spaces.
Cost for this configuration:
(10 - (3+1+4))^3 + (10 - 3)^3 + (0)^3 = 351
 */

public class WordWrap {
    /*
     * Complete the 'solveBalancedLineBreaks' function below.
     *
     * The function accepts STRING ARRAY and INTEGER as parameter.
     * Return LONG.
     */

    public static long solveBalancedLineBreaks(List<String> words, int limit) {
        // Write your code here
        long infinite = Long.MAX_VALUE;
        int n = words.size();
        long[] dp = new long[n + 1];
        long currentLineCost;
        for(int i = n-1; i>=0 ; i--){
            int noOfCharsInCurrentLine = 0;
            int noOfWordsInCurrentLine = 0;
            dp[i] = infinite;
            for(int j = i; j<n; j++){
                String word = words.get(j);
                noOfCharsInCurrentLine += word.length();
                noOfWordsInCurrentLine++;
                int totalNoOfChars = noOfCharsInCurrentLine + noOfWordsInCurrentLine-1;
                if(totalNoOfChars > limit){
                    break;
                }
                currentLineCost = j == n-1 ? 0 : (limit - totalNoOfChars);
                currentLineCost = currentLineCost * currentLineCost * currentLineCost;
                dp[i] = Math.min(currentLineCost + dp[j+1], dp[i]);
            }
        }
        return dp[0];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String word = bufferedReader.readLine().trim();
            words.add(word);
        }

        int limit = Integer.parseInt(bufferedReader.readLine().trim());

        bufferedReader.close();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        long result = solveBalancedLineBreaks(words, limit);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
