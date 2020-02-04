package Adhoc;
import java.io.*;
import java.util.*;
/*
Input Format:
The first line of the input should contain a single integer n, denoting the size of input array arr. In the next n lines, ith line should contain a number arr[i], denoting ith number of the input array arr, i=(1,2,...,n).
Next line should contain q, denoting no. of queries that need to be answered. In next 2*q lines, (2*i-1)th line should contain left value for ith query and (2*i)th line should contain right value for ith query, i=(1,2,...,q), i.e. 1st and 2nd line should contain left and right values for 1st query, 3rd and 4th line should contain left and right values for 2nd query, and so on...
If n = 5, arr = [2, 4, 6, 5, 8], q = 2, for 1st query: l = 0 and r = 4 and for 2nd query: l = 3 and r = 3, then input should be:
5
2
4
6
5
8
2
0
4
3
3

Output Format:
There will be q lines, where ith line contains a number maxArea[i], denoting result of ith query.
For input n = 5, arr = [2, 4, 6, 5, 8], q = 2, for 1st query: l = 0 and r = 4 and for 2nd query: l = 3 and r = 3, output will be:
16
5
 */

public class AreaUnderHistogram {

    // ============================ Start ============================
    static long findMaxPossibleArea(long[] arr, int l, int r) {
        // Create an empty stack. The stack holds indexes of arr[] array which can be from l to r.
        // The bars stored in stack are always in increasing order of their heights.
        Stack<Integer> stack = new Stack<>();

        long max_area = 0; // Initialize max area
        int tp;  // To store top of stack
        long area_with_top; // To store area with top bar as the smallest bar

        // Run through all bars of given histogram
        int i = l;
        while (i <= r)
        {
            // If this bar is higher than the bar on top stack, push it to stack
            if (stack.empty() || arr[stack.peek()] <= arr[i])
                stack.push(i++);

                // If this bar is lower than top of stack, then calculate area of rectangle
                // with stack top as the smallest (or minimum height) bar. 'i' is
                // 'right index' for the top and element before top in stack is 'left index'
            else
            {
                tp = stack.peek();  // store the top index
                stack.pop();  // pop the top

                // Calculate the area with arr[tp] stack as smallest bar
                area_with_top = arr[tp] * 1l * (stack.empty() ? i-l : i - stack.peek() - 1);

                // update max area, if needed
                if (max_area < area_with_top)
                    max_area = area_with_top;
            }
        }

        // Now pop the remaining bars from stack and calculate area with every
        // popped bar as the smallest bar
        while (stack.empty() == false)
        {
            tp = stack.peek();
            stack.pop();
            area_with_top = arr[tp] * 1l * (stack.empty() ? i-l : i - stack.peek() - 1);

            if (max_area < area_with_top)
                max_area = area_with_top;
        }

        return max_area;
    }
    // ============================= End ==============================


    public static void main(String args[]) {
        /*
        This function is used to increase the size of recursion stack. It makes the size of stack
        2^26 ~= 10^8
        */
        new Thread(null, new Runnable() {
            public void run() {
                try{
                    solve();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }
    public static void solve() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int heightsCount = Integer.parseInt(bufferedReader.readLine().trim());

        long[] heights = new long[heightsCount];

        for (int heightsItr = 0; heightsItr < heightsCount; heightsItr++) {
            heights[heightsItr] = Long.parseLong(bufferedReader.readLine().trim());
        }

        int q = Integer.parseInt(bufferedReader.readLine().trim());
        for (int i = 0; i < q; i++) {
            int l = Integer.parseInt(bufferedReader.readLine().trim());

            int r = Integer.parseInt(bufferedReader.readLine().trim());

            long res = findMaxPossibleArea(heights, l, r);
            bufferedWriter.write(res+"\n");
        }
        bufferedWriter.close();
    }
}
