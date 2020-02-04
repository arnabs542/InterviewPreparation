package Adhoc;
import java.io.*;
import java.util.*;

/*
Input Format:
There is only one argument in input, denoting integer array named array.

9
2
3
-4
-9
-1
-7
1
-5
-6

Output Format:



Return an integer array with alternate positive and negative numbers with order maintained.


 */

public class AlternatingPositivesAndNegatives {

    /*
     * Complete the function below.
     */
    static int[] alternating_positives_and_negatives(int[] arr) {
        int[] ret = new int[arr.length];
        int p = -1;
        int n = -1;
        int k = 0;
        while(k<ret.length){
            p = nextPos(arr,p+1);
            n = nextNeg(arr,n+1);
            if(p<arr.length && p>=0) ret[k++] = arr[p];
            if(n<arr.length && n>=0 && k<ret.length)ret[k++] = arr[n];
        }
        return ret;

    }

    static int nextPos(int[] arr, int index){
        while(index < arr.length && arr[index] <0){
            index++;
        }
        return index;

    }
    static int nextNeg(int[] arr, int index){
        while(index < arr.length && arr[index] >= 0){
            index++;
        }
        return index;

    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int arraySize = Integer.parseInt(scan.nextLine().trim());

        int[] array = new int[arraySize];


        for (int arrayItr = 0; arrayItr < arraySize; arrayItr++) {
            int arrayItem = Integer.parseInt(scan.nextLine().trim());
            array[arrayItr] = arrayItem;

        }

        int[] res = alternating_positives_and_negatives(array);

        for (int resItr = 0; resItr < res.length; resItr++) {
            bw.write(String.valueOf(res[resItr]));

            if (resItr != res.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();
        bw.close();
    }
}
