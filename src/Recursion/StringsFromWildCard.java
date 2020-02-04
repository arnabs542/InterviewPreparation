package Recursion;

import java.io.*;
import java.util.*;

/*

Sample Input 1:
s = “1?10”

Sample Output 1:
result = ["1010", "1110"] or ["1110", "1010"]

 */

public class StringsFromWildCard {

    /*
     * Complete the find_all_possibilities function below.
     */
    static String[] find_all_possibilities(String s) {
        /*
         * Write your code here.
         */

        ArrayList<String> results = new ArrayList<>();
        char[] currArray = s.toCharArray();
        generateAllPossiblities(s.toCharArray(), currArray,0,results);
        return results.toArray(new String[0]);
    }

    static void generateAllPossiblities(char[] charArray, char[] currArray,int index, ArrayList<String> results){
        if(index == charArray.length){
            results.add(String.valueOf(currArray));
            return;
        }else{
            if(charArray[index] == '?')
            {
                currArray[index] = '0';
                generateAllPossiblities(charArray,currArray, index+1, results);
                currArray[index] = '1';
                generateAllPossiblities(charArray, currArray, index+1, results);
            }else{
                currArray[index] = charArray[index];
                generateAllPossiblities(charArray,currArray, index+1, results);
            }
        }

    }



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = scanner.nextLine();

        String[] res = find_all_possibilities(s);

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
