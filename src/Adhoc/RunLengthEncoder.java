package Adhoc;

import java.util.Scanner;

/*
Input Format:
A line consisting of alphabetic characters only.
ABaaaBCC
Output Format:
AB3aB2C
A string consisting of alphanumeric characters will be printed.
 */

public class RunLengthEncoder {
    /*
       Complete the function below
   */
    public static String RLE(String strInput){
        StringBuilder sb = new StringBuilder();
        char[] charArray = strInput.toCharArray();
        int count = 1;
        for(int i=0;i<charArray.length-1;i++){

            if(charArray[i] == charArray[i+1]){
                count++;
                continue;
            }

            if(count >1){
                sb.append(count);
            }
            sb.append(charArray[i]);
            count = 1;
        }
        if(count>1){
            sb.append(count);
        }
        sb.append(charArray[strInput.length()-1]);
        return sb.toString();
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        String result = RLE(s);
        System.out.println(result);
    }
}
