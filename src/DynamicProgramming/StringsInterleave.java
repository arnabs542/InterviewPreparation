package DynamicProgramming;

import java.io.*;

/*
Sample Input-1:
a = "123"
b = "456"
i = "123456"
Sample Output-1:
True


 */

public class StringsInterleave {
    /*
     * Complete the 'doStringsInterleave' function below.
     *
     * The function is expected to return a BOOLEAN.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     *  3. STRING i
     */

    public static boolean doStringsInterleave(String a, String b, String i) {
        // Write your code here
        if(i.length()!=a.length()+b.length()){
            return false;
        }

        boolean[] dp = new boolean[i.length()+1];
        createDptable(a, b, i, dp);
        return dp[0];

    }
    static void createDptable(String a, String b, String i, boolean[] dp){
        int a_index = a.length()-1;
        int b_index = b.length()-1;
        dp[i.length()] = true;
        for(int index = i.length()-1; index>=0 ; index--){

            if(a_index>=0 && i.charAt(index) == a.charAt(a_index)){
                --a_index;
                dp[index] = true && dp[index+1];
            }else if(b_index>=0 && i.charAt(index) == b.charAt(b_index)){
                --b_index;
                dp[index] = true && dp[index+1];
            }else{
                // System.out.println()
                dp[index] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        String i = bufferedReader.readLine();

        boolean result = doStringsInterleave(a, b, i);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

/*
First character in i should match first character in a or first character in b.

* If it matches first character in a, try matching second character in i with second character in a or first character in b

* If it matches first character in b, try matching second character in i with second character in b or first character in a

* If it matches none of them, terminate with a false



Hence, keep recursing for the rest of the strings. This is an exponential solution, O(2^(len(a)+len(b))) as you can either pick a character from a or a character from b.











Optimal solution (optimal_solution.cpp):



Convention: str[0 : x] = first x chars of str.



We can say that i[0 : (a_i + b_i)] is an interleaving of a[0 : a_i] and b[0 : b_i], if at least one of the below two is true:



1)

  - i[0 : (a_i + b_i - 1)] should be an interleaving of a[0 : (a_i - 1)] and b[0 : b_i]

  and

  - a[ai - 1] == i[ai + bi - 1].



or



2)

  - i[0 : (a_i + b_i - 1)] should be an interleaving of a[0 : a_i] and b[0 : (b_i - 1)]

  and

  - b[bi - 1] == i[ai + bi - 1].



We can use DP to keep track of the already calculated values. Have a look at the solution for more detials.



Space Complexity: O(len(a) * len(b))

Time Complexity: O(len(a) * len(b))


 */