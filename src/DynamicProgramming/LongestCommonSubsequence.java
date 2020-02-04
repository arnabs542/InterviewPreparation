package DynamicProgramming;
import java.io.*;

/*
Input Format:The first line in the input contains string a and next line contains string b.

ABCDE
AECBD

Output Format:A string which is the longest common subsequence.
ABD
 */
public class LongestCommonSubsequence {
    /*
     * Complete the 'lcs' function below.
     *
     * The function accepts STRING a and STRING b as parameter.
     * The function is expected to return a STRING.
     */
    public static String lcs(String a, String b) {
        // Write your code here
        int len_a = a.length();
        int len_b = b.length();

        int[][] dptable = new int[len_a+1][len_b+1];
        for(int i = 0; i <= len_a; i++){
            for(int j = 0; j <= len_b; j++){
                if(i ==0 || j == 0){
                    dptable[i][j] = 0;
                }else if(a.charAt(i-1) == b.charAt(j-1)){
                    dptable[i][j] = 1 + dptable[i-1][j-1];
                }else {
                    dptable[i][j] = Math.max(dptable[i-1][j], dptable[i][j-1]);
                }
            }
        }
        int maxLength = dptable[len_a][len_b];
        char[] lcs = new char[maxLength + 1];
        lcs[maxLength] = ' ';
        int i = len_a;
        int j = len_b;
        while(i > 0 && j > 0){
            if(a.charAt(i-1) == b.charAt(j-1)){
                lcs[--maxLength] = a.charAt(i-1);
                i--;
                j--;
            }else{
                if(dptable[i-1][j] > dptable[i][j-1]){
                    i--;
                }else{
                    j--;
                }
            }

        }

        String answer = new String(lcs);
        answer = answer.trim();
        return answer.length() == 0 ? "-1" : answer;

    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        String result = lcs(a, b);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();
        bufferedReader.close();
    }
}

/*

3) optimal_solution.java



Description:



In this approach, we are trying to divide our problem of finding the longest common subsequence into two subproblems.

To calculate the length of the longest common subsequence: For this, we will also maintain states in 2d array L which will also help in solving the second subpart.
Create subsequence from maintained states while calculating the length of LCS.


Dp state in 2d array L where L[i][j] contains length of LCS of a[0..i-1] and b[0..j-1].



To calculate the length of the LCS:

We will fill our maintained 2d array L (where 0 <= i < length of a and 0 <= j < length of b) with

If i == 0 or j == 0 then L[i][j] = 0.
If a[i-1] == b[j-1] then L[i][j] = L[i-1][j-1] + 1.
Else L[i][j] = max(L[i-1][j], L[i][j-1]).


Now we know the length of LCS as L[length of a - 1][length of b - 1] and we have maintained dp state to create required subsequence out of it.

We create a character array lcs of length L[length of a - 1][length of b - 1].

We will start from right-most-bottom-most corner i.e. i = length of a - 1 and j = length of b - 1.

If the character at index i of string a and character at index j of string b are same then that character will be part of our LCS and we fill our character array lcs reduce values of both i and j.

Else we will compare L[i-1][j] and L[i][j-1].

If L[i-1][j] > L[i][j-1], reduce i.
Else reduce j.
We will do this till i>0 & j>0.



And by now we got our final longest common subsequence characters stored in character array lcs.



For better understanding please look at optimal_solution.java.



Time Complexity (assuming that input arguments are already given and excluding time used in the declaration of output):



O(len_a * len_b) where len_a and len_b are lengths of string A and string B respectively.



We are filling maintained 2d array L of size len_a * len_b, it will take O(len_a * len_b) also we are filling character array lcs of size min(len_a, len_b) in worst case, so it will take O(min(len_a, len_b)).

Hence total complexity will be O(len_a * len_b) + O(min(len_a, len_b)) → O(len_a * len_b).



Time Complexity:



O(len_a * len_b) where len_a and len_b are lengths of string A and string B respectively.



As time complexity assuming that input arguments are already given and excluding time used in the declaration of output is O(len_a * len_b) and to read input strings of length len_a and len_b, it takes O(len_a) + O(len_b) and output string can be O(min(len_a, len_b)). Hence total complexity will be

O(len_a * len_b) + O(len_a) + O(len_b) + O(min(len_a, len_b)) →  O(len_a * len_b).



Auxiliary Space Used:



O(len_a * len_b) where len_a and len_b are lengths of string A and string B respectively.



As we are maintaining 2d array L of size len_a * len_b, it will consume space of  O(len_a * len_b) and also we are maintaining character array of size min(len_a, len_b) in worst case, it will take O(min(len_a, len_b)). Hence, total auxiliary space used will be O(len_a * len_b)  + O(min(len_a, len_b)) → O(len_a * len_b).



Space Complexity:



O(len_a * len_b) where len_a and len_b are lengths of string A and string B respectively.



As to store two input strings of length len_a and len_b, it will take O(len_a) + O(len_b). Auxiliary space used is O(len_a * len_b) and for the output, string can take O(min(len_a, len_b)) of space. Hence total complexity will be

O(len_a * len_b) + O(len_a) + O(len_b) + O(min(len_a, len_b)) →  O(len_a * len_b).

 */
