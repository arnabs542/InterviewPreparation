package Adhoc;

import java.io.*;
import java.util.*;

/*
Input Format: Only argument for function, integer array named arr.
8
4
5
6
7
8
1
2
3
Output Format: Return integer which is minimum element in given array.
1

 */
public class MinimumElementInASortedAndRotatedSubArray {

    /*
     * Complete the 'find_minimum' function below.
     *
     * The function accepts INTEGER ARRAY as parameter.
     * Return INTEGER.
     */
    public static int find_minimum(List<Integer> arr) {
        // Write your code here
        int n = arr.size();
        if(n==1){
            return arr.get(0);
        }
        if(n==2){
            return Math.min(arr.get(0), arr.get(1));
        }
        if(n==3){
            return Math.min(arr.get(0), Math.min(arr.get(1), arr.get(2)));
        }

        if( arr.get(0)<=arr.get(n-1) && arr.get(0)<=arr.get(1) && arr.get(n-2)<=arr.get(n-1)){
            return arr.get(0);
        }


        if( arr.get(0)>=arr.get(n-1) && arr.get(0)>=arr.get(1) && arr.get(n-2)>=arr.get(n-1)){
            return arr.get(n-1);
        }

        if(arr.get(0) > arr.get(n-1)){
            return find_minimum_in_increasing(arr);
        }else{
            return find_minimum_in_decreasing(arr);
        }
    }



    static int find_minimum_in_increasing(List<Integer> arr){
        int left = 0;
        int right = arr.size()-1;

        while(left <= right){
            if( arr.get(left) <= arr.get(right)){
                return arr.get(left);
            }
            int mid = (left+right)/2;
            if(arr.get(left) <= arr.get(mid)){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return -1;

    }
    static int find_minimum_in_decreasing(List<Integer> arr){
        int left = 0;
        int right = arr.size()-1;
        while(left <= right){
            if(arr.get(left) >= arr.get(right)){
                return arr.get(right);
            }
            int mid = (left+right)/2;
            if(arr.get(mid)<arr.get(left)){
                left  = mid;
            }else{
                right = mid;
            }
        }
        return -1;
    }


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

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        List<Integer> arr = new ArrayList<>();

        for(int i=0;i<n;i++){
            int num = Integer.parseInt(bufferedReader.readLine().trim());
            arr.add(num);
        }
        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int result = find_minimum(arr);
        bufferedWriter.write(result+"\n");
        bufferedWriter.close();
    }
}
