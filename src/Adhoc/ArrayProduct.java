package Adhoc;

import java.io.*;
import java.util.*;

/*
Input Format:The first line of the input should contain a single integer n denoting the size of input array.
In the next n lines, each line should contain a number Ai, denoting ith number of the input array A, (0<=i<n).
If n = 5 and nums = [1, 2, 3, 4, 5], then input should be:
5
1
2
3
4
5
Output Format:
There will be n lines, each line containing a number Pi, denoting ith number of the resultant product array P.
For input n = 5 and nums = [1, 2, 3, 4, 5], output will be:
120
60
40
30
24
 */
public class ArrayProduct {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int numsCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int[] nums = new int[numsCount];

        for (int numsItr = 0; numsItr < numsCount; numsItr++) {
            int numsItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
            nums[numsItr] = numsItem;
        }

        int[] res = getProductArray(nums);

        for (int resItr = 0; resItr < res.length; resItr++) {
            bufferedWriter.write(String.valueOf(res[resItr]));

            if (resItr != res.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private static int[] getProductArray(int[] nums) {
        int[] result = new int[nums.length];
        int left = 1;
        int right = 1;

        for(int i = 0; i< nums.length;i++){
            result[i] = 1;
        }

        for(int i=0; i < nums.length; i++){
            result[i]*=left;
            left*=nums[i];
        }
        for(int i=nums.length-1; i >=0; i--){
            result[i]*=right;
            right*=nums[i];
        }

        return result;
    }
}

