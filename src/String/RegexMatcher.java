package String;
import java.io.*;
/*

Input Format:There are two arguments, first one is string denoting text and second one is string denoting pattern.
Output Format:Return boolean, true if text and pattern matches exactly, otherwise return false.
Sample Input 2:
text = "abcdefg" and pattern = "a.c.*.*gg*"
Sample Output 2:
true
Explanation 2:
".*" in pattern can match  "", ".", "..", "...", "....", ...
"g*" in pattern can match "", "g", "gg", "ggg", "gggg", ...
Now, consider:
   '.' at position 2 as 'b',
   '.*'  at position {4,5} as "...",
   '.*' at position {6,7} as "" and
   [g*] at position {8,9} as "".
So, "a.c.*gg*" = "abc...g" where we can write "..." as "def"

 */
public class RegexMatcher {
    /*
     * Complete the 'pattern_matcher' function below.
     *
     * The function accepts STRING text and pattern as parameter and returns BOOLEAN.
     */

    static boolean pattern_matcher(String text, String pattern) {
        // Write your code here
        int[][] m = new int[pattern.length()+1][text.length()+1];
        return matcher(text, pattern, 0,0, m);
    }

    static boolean match(char c, String text, int j){
        return j < text.length() &&  (c == '.' || c == text.charAt(j));
    }

    static boolean isStarred(int j, String pattern){
        return j < pattern.length()-1 && pattern.charAt(j+1) == '*';
    }
    static boolean matcher(String s, String p, int i, int j, int[][] m ){
        if(i == p.length()){
            return j == s.length();
        }
        if(m[i][j] != 0){
            return m[i][j] == 1;
        }
        boolean b = false;
        if(isStarred(i, p)){
            // skip and move on and J matches preceding character //consume and stay
            b = matcher(s, p, i+2, j,m) || (match(p.charAt(i), s, j) && matcher(s,p,i, j+1, m));
        }else{
            b  = match(p.charAt(i), s, j) && matcher(s,p,i+1,j+1,m);
        }
        m[i][j] = b ? 1:2;
        return b;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = bufferedReader.readLine();
        String pattern = bufferedReader.readLine();

        boolean result = pattern_matcher(text, pattern);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedWriter.close();

        bufferedReader.close();
    }
}
