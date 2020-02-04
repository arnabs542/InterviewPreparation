package LinkedList;
import java.io.*;
import java.util.*;
/*
Input Format: The first line of input should contain an integer n, denoting the length of input array arr. In next n lines, ith line should contain an integer arr[i], denoting value at index i of arr. In the next line, there should be an integer w, denoting size of window.
If n = 8, arr = [1, 3, -1, -3, 5, 3, 6, 7] and w = 3, then input should be:
8
1
3
-1
-3
5
3
6
7
3
Output Format:
There will be (n-w+1) lines, where ith line contains an integer res[i], denoting the value at index i of res. res is the result returned by the solution function.
For input n = 8, arr = [1, 3, -1, -3, 5, 3, 6, 7] and w = 3, output will be:
3
3
5
5
6
7


 */

public class MaximumInSlidingWindow {

    /*
     * Complete the function below.
     */
    static int[] max_in_sliding_window(int[] arr, int w) {
        int max = Integer.MIN_VALUE;
        int[] results = new int[arr.length -w +1];
        for(int i = 0 ; i < results.length;i++){
            for(int j = i;j < i+w ;j++)
            {
                max = Math.max(max, arr[j]);
            }
            results[i]= max;
            max = Integer.MIN_VALUE;
        }
        return results;
    }



    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] res;
        int arr_size = 0;
        arr_size = Integer.parseInt(in.nextLine().trim());

        int[] arr = new int[arr_size];
        for(int i = 0; i < arr_size; i++) {
            int arr_item;
            arr_item = Integer.parseInt(in.nextLine().trim());
            arr[i] = arr_item;
        }

        int w;
        w = Integer.parseInt(in.nextLine().trim());

        res = max_in_sliding_window(arr, w);
        for(int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}


