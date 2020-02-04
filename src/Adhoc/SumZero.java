package Adhoc;
import java.io.*;
import java.util.*;

/*
Input Format:
The first line of the input should contain a single integer n, denoting the size of input array arr.
In the next n lines, ith line should contain an integer denoting arr[i].
If n = 6 and arr = [5, 1, 2, -3, 7, -4], then input should be:
6
5
1
2
-3
7
-4
If n = 5 and arr = [1, 2, 3, 5, -9], then input should be:
5
1
2
3
5
-9
Output Format:
There are two cases here:
1. If a valid sum zero subarray exists in arr, then there will be two lines for output. First line will have an integer res[0] and second line will have an integer res[1], denoting starting index and ending index of required subarray (0 based indexing, both inclusive).
2. Otherwise if there is no valid sum zero subarray, there will be only one line for output, having an integer -1.
For input n = 6 and arr = [5, 1, 2, -3, 7, -4], output will be:
1
3
For input n = 5 and arr = [1, 2, 3, 5, -9], output will be:
-1

arr[] = {1, 4, -2, -2, 5, -4, 3}

If we consider all prefix sums, we can
notice that there is a subarray with 0
sum when :
1) Either a prefix sum repeats or
2) Or prefix sum becomes 0.

Prefix sums for above array are:
1, 5, 3, 1, 6, 2, 5

Since prefix sum 1 repeats, we have a subarray
with 0 sum.

 */
public class SumZero {
    // -------------------- START ----------------------
    static int[] sumZero(int[] arr) {
        // To store interval (start, end) for which sum is zero
        int[] res = new int[2];
        // To store prefix sum i.e. sum of subarray starting at index 0 and ending at index i
        // Key of hashmap will be sum and value will be index i for prefix sum
        HashMap<Long, Integer> map = new HashMap<>();
        // To check whether prefix sum it self is equal to zero
        map.put(0l, -1);

        // To store current sum
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            // To check if we encountered with value which itself is zero
            if(arr[i]==0){
                res[0]=i;
                res[1]=i;
                return res;
            }
            // Adding current value in current sum
            sum += arr[i];
            // If we found value sum in our hashmap means we have encountered with this sum before
            // means arr[0, map.get(sum)] = sum and
            // arr[0, map.get(sum)] + arr[map.get(sum)+1, i] = sum
            // which implies arr[map.get(sum)+1, i] = 0 and hence interval we are looking for is
            // start = map.get(sum)+1 and end = i
            if (map.containsKey(sum)) {
                res[0] = map.get(sum) + 1;
                res[1] = i;
                return res;
            } else {
                map.put(sum, i);
            }
        }
        // If no subarray having sum = 0 found then we will return [-1]
        return new int[]{-1};
    }
    // -------------------- END ----------------------
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

        int n = Integer.parseInt(bufferedReader.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(bufferedReader.readLine());
        }

        int[] resSubArray = sumZero(arr);

        for (int i = 0; i < resSubArray.length; i++) {
            bufferedWriter.write(String.valueOf(resSubArray[i])+"\n");
        }
        bufferedWriter.newLine();

        bufferedWriter.close();

        bufferedReader.close();
    }
}
