package DynamicProgramming;
import java.io.*;
import java.util.*;
/*
The minimum no of steps required to convert word1 to word2 with the given set of allowed operations is called edit distance.
e.g. Minimum edit distance between the words 'kitten' and 'sitting', is 3.
kitten → sitten (substitution of "s" for "k")
sitten → sittin (substitution of "i" for "e")
sittin → sitting (insertion of "g" at the end)

Input Format:The first line should contain a string denoting word1.The second line should contain a string denoting word2. If word1 = “cat” and word2 = “bat”, then input should be:
cat
bat


Output Format: There will be one line, containing an integer editDist, denoting the result returned by solution function.
For input word1 = “cat” and word2 = “bat”, output will be:
1
 */
public class LevenshteinDistance {
    /*
     * Complete the levenshteinDistance function below.
     */
    static int levenshteinDistance(String strWord1, String strWord2) {
        /*
         * Write your code here.
         */

        if(strWord1 == null || strWord2 == null){
            return -1;
        }
        int[][] dpTable = createDpTable(strWord1, strWord2);

        return dpTable[0][0];
    }
    static int[][] createDpTable(String word1, String word2){
        int rows = word1.length()+1;
        int cols = word2.length()+1;
        int dptable[][] = new int[rows][cols];
        for(int i = rows-1; i>=0; i--){
            for(int j = cols-1; j>=0; j--){
                if(i == word1.length()){
                    dptable[i][j] = word2.length() - j;
                }
                else if(j == word2.length()){
                    dptable[i][j] = word1.length() - i;
                }
                else if(word1.charAt(i) == word2.charAt(j)){
                    dptable[i][j] = dptable[i+1][j+1];
                }
                else {
                    int min = Math.min(dptable[i][j+1], dptable[i+1][j]);
                    int globalMin = Math.min(min, dptable[i+1][j+1]);
                    dptable[i][j] = globalMin + 1;
                }
            }
        }
        return dptable;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String strWord1 = scanner.nextLine();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        String strWord2 = scanner.nextLine();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int res = levenshteinDistance(strWord1, strWord2);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
