package Sorting;
import java.io.*;
import java.util.*;


/*
*  ip format
* The first line of input should contain an integer N, denoting no. of nuts. In next N lines, ith line should contain an integer NUTS[i], denoting size of ith nut.Next line should contain an integer N, denoting no. of bolts. In next N lines, ith line should contain an integer BOLTS[i], denoting size of ith bolt.
If N = 4, NUTS = [4, 32, 5, 7] and BOLTS = [32, 7, 5, 4], then input should be:
4
4
32
5
7
4
32
7
5
4
Output Format: There will be N lines of output, where ith line contains a string res[i], denoting value at index i of res.Here, res is the result array returned by solution function.
For input N = 4, NUTS = [4, 32, 5, 7] and BOLTS = [32, 7, 5, 4], output will be:
4 4
32 32
5 5
7 7
*
* */
public class NutsAndBolts {
    /*
     * Complete the solve function below.
     */
    static String[] solve(int[] nuts, int[] bolts){
        String[] results = new String[nuts.length];
        /*
         * Write your code here.
         */
        if(nuts.length == 0 || bolts.length == 0)
            return results;
        if(nuts.length != bolts.length)
            return results;
        quickSort(nuts, bolts, 0, bolts.length-1);
        for(int i =0;i<nuts.length;i++){

            StringBuilder match = new StringBuilder();
            match.append(nuts[i]).append(" ").append(bolts[i]);
            results[i] = match.toString();

        }
        return results;
    }
    static void quickSort(int[] nuts, int[] bolts, int start, int end){
        if(start >= end)
            return;

        int pivot_index  = choosePivot(bolts, start, end);
        pivot_index = partition(bolts, start, end, nuts[pivot_index]);
        partition(nuts, start, end, bolts[pivot_index]);

        quickSort(nuts, bolts, start, pivot_index-1);
        quickSort(nuts, bolts, pivot_index+1, end);

    }
    static int partition(int[] array, int start, int end, int pivot){

        int i = start;
        for(int cur = start; cur < end; cur++){
            if(array[cur] < pivot){
                swap(array, cur, i);
                i++;
            }else if (array[cur] == pivot){
                swap(array, cur, end);
                cur--;
            }
        }
        swap(array, i, end);

        return i;

    }
    static int choosePivot(int[] array, int start,int end){
        int mid = start + (end-start)/2;
        return mid;
    }
    static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return;
    }


    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nutsCount = Integer.parseInt(scan.nextLine().trim());

        int[] nuts = new int[nutsCount];

        for (int nutsItr = 0; nutsItr < nutsCount; nutsItr++) {
            int nutsItem = Integer.parseInt(scan.nextLine().trim());
            nuts[nutsItr] = nutsItem;
        }

        int boltsCount = Integer.parseInt(scan.nextLine().trim());

        int[] bolts = new int[boltsCount];

        for (int boltsItr = 0; boltsItr < boltsCount; boltsItr++) {
            int boltsItem = Integer.parseInt(scan.nextLine().trim());
            bolts[boltsItr] = boltsItem;
        }

        String[] res = solve(nuts, bolts);

        for (int resItr = 0; resItr < res.length; resItr++) {
            bw.write(res[resItr]);

            if (resItr != res.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}
