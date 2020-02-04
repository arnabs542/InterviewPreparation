package Sorting;
import java.io.*;
import java.util.*;

/**
 * Input Format: The first line of input should contain an integer n, denoting size of input array arr. In next n lines, ith line should contain an integer arr[i], denoting a value at index i of arr. In the next line, there should be an integer, denoting value of K.
 If n = 5, arr = [1, 5, 4, 4, 2] and K = 2, then input should be:
 5
 1
 5
 4
 4
 2
 2
 * Output Format: Let’s denote size of res as m, where res is the array of integers returned by solution function. Then, there will be m lines of output, where ith line contains an integer res[i], denoting value at index i of res.
   For input n = 5, arr = [1, 5, 4, 4, 2] and K = 2, output will be:
 4
 5
 */
public class TopK {
    /*
     * Complete the function below.
     */
    static int[] topK(int[] arr, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue(k, Collections.reverseOrder());
        for(int i=0 ; i< arr.length ; i++){
            if(!queue.contains(arr[i]))
                queue.add(arr[i]);
        }
        int outputSize = k < queue.size() ? k : queue.size();
        int[] output = new int[outputSize];
        for(int i = 0; i< output.length; i++){
            output[i] = queue.poll();
        }
        return output;
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

        int k;
        k = Integer.parseInt(in.nextLine().trim());

        res = topK(arr, k);
        for(int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}
