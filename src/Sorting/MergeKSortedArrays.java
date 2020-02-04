package Sorting;
import java.io.*;
import java.util.*;
/*
*Input/Output Format For The Custom Input:



Input Format: The first line of input should contain an integer K. The second line should contain an integer N, denoting size of each input array.In next K lines, ith line should contain N space separated integers, denoting content of ith array of K input arrays, where jth element in this ith line is nothing but arr[i][j], i.e. value at index j of ith array, 0-based indexing.
If K = 3, N = 4 and arr = [
    [1, 3, 5, 7],
    [2, 4, 6, 8],
    [0, 9, 10, 11]
], then input should be:
3
4
1 3 5 7
2 4 6 8
0 9 10 11

Output Format: There will be (N*K) lines of output, where ith line contains an integer res[i], denoting value at index i of res. Here, res is the result array returned by solution function.
For input K = 3, N = 4 and arr = [
    [1, 3, 5, 7],
    [2, 4, 6, 8],
    [0, 9, 10, 11]
], output will be:
0
1
2
3
4
5
6
7
8
9
10
11
* */

public class MergeKSortedArrays {
    /*
     * Complete the mergeArrays function below.
     */
    static int[] mergeArrays(int[][] arr) {
        /*
         * Write your code here.
         */

        int totalKArrays = arr.length;
        int individualArray = arr[0].length;
        int totalCapacity = totalKArrays * individualArray;
        int[] outputMergedArray = new int[totalCapacity];
        PriorityQueue<Node> queue ;
        String SORT_ORDER = getSortOrder(arr, totalKArrays);

        if(SORT_ORDER.equals("asc")){
            queue = new PriorityQueue<>(totalKArrays);
        }else{
            queue = new PriorityQueue<>(totalKArrays, Collections.reverseOrder());
        }

        for(int i =0; i<totalKArrays; i++){
            Node firstElementOfEachArray = new Node(arr[i][0], i, 0);
            queue.add(firstElementOfEachArray);
        }

        for(int i = 0; i<totalCapacity; i++){
            Node curr = queue.poll();
            outputMergedArray[i] = curr.data;
            if(curr.nthColoumn+1  < individualArray){
                queue.add(new Node(arr[curr.kthRow][curr.nthColoumn+1],curr.kthRow, curr.nthColoumn+1));
            }

        }
        return outputMergedArray;

    }
    static class Node implements Comparable<Node>{
        int data;
        int kthRow;
        int nthColoumn;

        public Node(int value, int row, int column){
            this.data = value;
            this.kthRow = row;
            this.nthColoumn = column;
        }

        @Override
        public int compareTo(Node o){
            return Long.compare(this.data, o.data);
        }


    }
    static String getSortOrder(int[][] arr, int k){
        String sort = "asc";
        for(int i=0; i<k; i++){
            if(arr[i][0] > arr[i][arr[i].length -1]){
                sort = "desc";
            }
        }
        return sort;
    }
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrRows = Integer.parseInt(scan.nextLine().trim());
        int arrColumns = Integer.parseInt(scan.nextLine().trim());

        int[][] arr = new int[arrRows][arrColumns];

        for (int arrRowItr = 0; arrRowItr < arrRows; arrRowItr++) {
            String[] arrRowItems = scan.nextLine().split(" ");

            for (int arrColumnItr = 0; arrColumnItr < arrColumns; arrColumnItr++) {
                int arrItem = Integer.parseInt(arrRowItems[arrColumnItr].trim());
                arr[arrRowItr][arrColumnItr] = arrItem;
            }
        }

        int[] res = mergeArrays(arr);

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
