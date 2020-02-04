package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class WiggleSortSolution {
    public void wiggleSort(int[] nums) {
        int i =1;
        int n = nums.length-1;
        while(i<=n-1){
            if(i%2==1){
                if(nums[i-1] > nums[i]){
                    findNumberSmallerThanTargetFromEnd(nums, nums[i], n, i-1);
                }else if(nums[i+1] > nums[i]){
                    findNumberSmallerThanTargetFromEnd(nums, nums[i], n, i+1);
                }
            }else{
                if(nums[i-1] < nums[i]){
                    findNumberGreaterThanTargetFromEnd(nums, nums[i], n, i-1);
                }else if(nums[i+1] < nums[i]){
                    findNumberGreaterThanTargetFromEnd(nums, nums[i], n, i+1);
                }
            }
            i++;
        }
    }
    public void findNumberSmallerThanTargetFromEnd(int[] nums, int target, int end, int indexToSwap){
        while(end >= 0 && nums[end] > target){
            end--;
        }
        int temp = nums[end];
        nums[end] = nums[indexToSwap];
        nums[indexToSwap] = temp;
    }
    public void findNumberGreaterThanTargetFromEnd(int[] nums, int target, int end, int indexToSwap){
        while(end >= 0 && nums[end] < target){
            end--;
        }
        int temp = nums[end];
        nums[end] = nums[indexToSwap];
        nums[indexToSwap] = temp;
    }
}

public class WiggleSort {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            new WiggleSortSolution().wiggleSort(nums);
            String out = integerArrayToString(nums);

            System.out.print(out);
        }
    }
}
