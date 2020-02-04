package Sorting;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/*

Input Format: First line contains integer n, the number of integers. The next n lines contains an integer each. If arr = [1,7,5,3] then input should be:
4
1
7
5
3
Output Format: Output the sorted array of integers, with each integer in a new line. For input arr = [1,7,5,3], output will be
1
3
5
7
*
* */

/*
 * Complete the 'merge_sort' function below.
 *
 * The function accepts an integer array as parameter.
 */

class ResultMergeSort {

    public static List<Integer> merge_sort(List<Integer> arr) {
        // Write your code here

        int[] array = new int[arr.size()];
        int i=0;
        for(Integer integer : arr){
            array[i++] = integer;
        }
        arr.clear();
        mergeSortWrapper(array);
        for (int j = 0; j < array.length; j++) {
            arr.add(array[j]);
        }
        return arr;
    }

    public static int[] mergeSortWrapper(int[] arr){
        mergeSortHelper(arr, 0, arr.length -1);
        return arr;
    }

    public static void mergeSortHelper(int[] arr, int start, int end){
        if(start >= end)
            return ;

        int mid = start + (end-start)/2;
        mergeSortHelper(arr, start, mid);
        mergeSortHelper(arr, mid+1, end);
        merge(arr, start, mid, end);

    }
    public static void merge(int[] arr, int start, int mid, int end){
        int n1 = mid - start + 1;
        int n2 = end - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for(int i = 0; i< n1; i++){
            L[i] = arr[start+i];
        }
        for(int i = 0; i< n2; i++){
            R[i] = arr[mid+1+i];
        }

        int i = 0; int j = 0; int k =start;
        while(i<n1  && j<n2){
            if(L[i] <= R[j])
                arr[k++] = L[i++];
            else
                arr[k++] = R[j++];
        }
        while(i<n1){
            arr[k++] = L[i++];
        }
        while(j<n2){
            arr[k++] = R[j++];
        }
    }


}

public class MergeSort {
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = ResultMergeSort.merge_sort(arr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedWriter.close();

        bufferedReader.close();
    }
}
