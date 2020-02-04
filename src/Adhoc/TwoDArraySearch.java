package Adhoc;
import java.io.*;
import java.util.*;
/*
Input Format:
The first line of the input should contain a single integer r, denoting the no. of rows of input array arr. Second line should contain a single integer c, denoting the no. of columns of arr.
In the next r lines, each line should contain c space separated numbers. jth number in ith line of these r lines is arr[i][j], denoting the number at ith row of the jth column of arr.
Next line should contain q, denoting no of queries to be answered. In next q lines, each line should contain xi, denoting x for ith query
If r = 3, c = 4, arr = [[1, 2, 3, 12], [4, 5, 6, 45], [7, 8, 9, 78]], q = 3, x for 1st query = 6, x for 2nd query = 7 and x for 3rd query = 23, then input should be:
3
4
1 2 3 12
4 5 6 45
7 8 9 78
3
6
7
23
Output Format:
There will be q lines, ith line of which contains the result for ith query as "present" or "not present"
For input r = 3, c = 4, arr = [[1, 2, 3, 12], [4, 5, 6, 45], [7, 8, 9, 78]], q = 3, x for 1st query = 6, x for 2nd query = 7 and x for 3rd query = 23, output will be:

present
present
not present
 */
public class TwoDArraySearch {
    /*
     * Complete the isPresent function below.
     */
    static String isPresent(int[][] arr, int x) {
        /*
         * Write your code here.
         */

        int rows = arr.length;
        int cols = arr[0].length;
        boolean found = false;
        for(int i = 0; i< rows; i++){
            //check boundary condition for row.length -1
            if( arr[i][0] <= x && x <= arr[i][cols-1]){
                found = isPresentInGrid(i, x, arr);
            }
            if(found){
                break;
            }

        }
        return found ? "present" : "not present";

    }

    static boolean isPresentInGrid(int row, int val, int[][] arr){
        boolean isPresentFlag = false;
        if( row < arr.length){
            for(int i = 0; i< arr[0].length; i++){
                if(arr[row][i] == val){
                    isPresentFlag = true;
                }
            }
        }
        return isPresentFlag;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrRows = Integer.parseInt(scanner.nextLine().trim());
        int arrColumns = Integer.parseInt(scanner.nextLine().trim());

        int[][] arr = new int[arrRows][arrColumns];

        for (int arrRowItr = 0; arrRowItr < arrRows; arrRowItr++) {
            String[] arrRowItems = scanner.nextLine().split(" ");

            for (int arrColumnItr = 0; arrColumnItr < arrColumns; arrColumnItr++) {
                int arrItem = Integer.parseInt(arrRowItems[arrColumnItr].trim());
                arr[arrRowItr][arrColumnItr] = arrItem;
            }
        }

        int x;
        int q = Integer.parseInt(scanner.nextLine().trim());
        String res;
        for (int i=0 ; i<q ; i++){
            x = Integer.parseInt(scanner.nextLine().trim());
            res = isPresent(arr, x);
            bufferedWriter.write(res);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }
}
