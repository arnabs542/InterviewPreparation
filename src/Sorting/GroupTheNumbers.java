package Sorting;

import java.io.*;
import java.util.*;
/*
*Input Format:



The first line of input should contain an integer n, denoting size of input array arr. In next n lines, ith line should contain an integer arr[i], denoting a value at index i of arr.
If n = 4 and arr = [1, 2, 3, 4], then input should be:
4
1
2
3
4
Output Format:There will be n lines of output, where ith line contains an integer res[i], denoting value at index i of res.Here, res is the result array returned by solution function.
For input n = 4 and arr = [1, 2, 3, 4], output will be:
4
2
1
3
*/
public class GroupTheNumbers {

    /*
     * Complete the function below.
     */
    static int[] solve(int[] arr) {

        partition(arr, 0, arr.length-1);
        return arr;
    }

    static void partition(int[] arr, int start, int end){
        int mid = start + (end-start)/2;
        int i = start;
        for(int curr= start; curr < arr.length; curr++){
            if(arr[curr]%2 == 0){
                swap(arr, i, curr);
                i++;
            }
        }
    }
    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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

        res = solve(arr);
        for(int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}
