package Adhoc;
import java.io.*;
import java.util.*;

/*
Input Format: First line should contain a number n, denoting number of intervals in inputArray. Next line should contain 2, unconditionally, as inputArray is 2D array of n*2. In next n lines, ith line should contain two space separated numbers starti and endi, denoting start point and end point of ith interval respectively.
If n = 4, inputArray = [[1, 3], [5, 7], [2, 4], [6, 8]], then input should be:
4
2
1 3
5 7
2 4
6 8

Output Format:
Let say len*2 is the size of resultant 2D array outputArray. Then, there will be len lines, where ith line contains two space separated integers starti and endi, denoting start point and end point of ith interval in outputArray respectively.
For input n = 4, inputArray = [[1, 3], [5, 7], [2, 4], [6, 8]], output will be:
1 4
5 8

 */

public class MergeIntervals {
    public static int[][] getMergedIntervals(int[][] intervals) {

        if(intervals.length <= 1){
            return intervals;
        }
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        List<int[]> result = new ArrayList();
        int[] newInterval = intervals[0];
        result.add(newInterval);

        for(int[] interval : intervals){
            if(interval[0] <= newInterval[1]){
                newInterval[1] = Math.max(interval[1], newInterval[1]);
            }else{
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);

    }
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inputArrayRows = Integer.parseInt(scan.nextLine().trim());
        int inputArrayColumns = Integer.parseInt(scan.nextLine().trim());

        int[][] inputArray = new int[inputArrayRows][inputArrayColumns];

        for (int inputArrayRowItr = 0; inputArrayRowItr < inputArrayRows; inputArrayRowItr++) {
            String[] inputArrayRowItems = scan.nextLine().split(" ");

            for (int inputArrayColumnItr = 0; inputArrayColumnItr < inputArrayColumns; inputArrayColumnItr++) {
                int inputArrayItem = Integer.parseInt(inputArrayRowItems[inputArrayColumnItr].trim());
                inputArray[inputArrayRowItr][inputArrayColumnItr] = inputArrayItem;
            }
        }

        int[][] res = getMergedIntervals(inputArray);

        for (int resRowItr = 0; resRowItr < res.length; resRowItr++) {
            for (int resColumnItr = 0; resColumnItr < res[resRowItr].length; resColumnItr++) {
                bw.write(String.valueOf(res[resRowItr][resColumnItr]));

                if (resColumnItr != res[resRowItr].length - 1) {
                    bw.write(" ");
                }
            }

            if (resRowItr != res.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}


