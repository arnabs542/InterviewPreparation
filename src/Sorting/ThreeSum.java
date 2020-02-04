package Sorting;
import java.io.*;
import java.util.*;
/*
*Input Format: The first line of input should contain an integer N, denoting size of input array arr. In next N lines, ith line should contain an integer arr[i], denoting a value at index i of arr.
If N = 6 and arr = [10, 3, -4, 1, -6, 9], then input should be:
6
10
3
-4
1
-6
9

Output Format: Let’s denote size of res as m, where res is the array of strings returned by solution function. Then, there will be m lines of output, where ith line contains res[i], denoting value at index i of res.
For input N = 6 and arr = [10, 3, -4, 1, -6, 9], output will be:
10,-4,-6
3,-4,1
*/
public class ThreeSum {
    /*
     * Complete the function below.
     */
    static String[] findZeroSum(int[] arr) {
        // Write your code here.
        HashSet<String> results = new HashSet<String>();
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++){
            if(i==0 || arr[i]!=arr[i-1]){
                int target = -arr[i];
                twoSum(arr, target, i,i+1, arr.length-1, results);
            }
        }
        return results.toArray(new String[results.size()]);
    }

    static void twoSum(int[] arr,int target, int curr,int start, int end, Set<String> results){
        int i = start;
        int j = end;
        while(i<j){
            int twoSum = arr[i] + arr[j];
            if(twoSum == target){
                String result = arr[curr] + "," +arr[i]+ "," +arr[j];
                results.add(result);
                i++;j--;
                while(i<j && arr[i] == arr[i-1])
                    i++;
                while(i<j && arr[j] == arr[j+1])
                    j--;
            }else if(twoSum < target)
                i++;
            else
                j--;
        }
    }

    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrSize = Integer.parseInt(scan.nextLine().trim());

        int[] arr = new int[arrSize];

        for (int arrItr = 0; arrItr < arrSize; arrItr++) {
            int arrItem = Integer.parseInt(scan.nextLine().trim());
            arr[arrItr] = arrItem;

        }

        String[] res = findZeroSum(arr);

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

