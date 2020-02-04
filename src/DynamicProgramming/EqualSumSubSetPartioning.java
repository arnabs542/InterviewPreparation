package DynamicProgramming;
import java.io.*;
import java.util.*;

/*
Input Format:
The first line of the text file contains one single integer n, denoting number of elements in array s.
Next n lines of the input, each line contains single integer denoting the ith element in the array s.
If n = 3 and s = [1, 0, -1], then custom input format will be:
3
1
0
-1
Output Format:
If a valid partition exists, then the first line contains an integer s1, denoting the size of the first subset and next S1 line contains
ith elements in the set s1 in the order they appear in the input array s. Next line contains an integer s2, denoting the size of the second subset. Next s2 lines will contain integers denoting the ith element in the set s2 in the order they appear in the input array s.
In case a valid partition is not possible the output contains only one line having integer -1.
For the above-provided custom input, one possible custom output could be:
2
1
-1
1
0

 */
public class EqualSumSubSetPartioning {

    /*
     * Complete the 'equalSubSetSumPartition' function below.
     *
     * @param s : input array as parameter.
     */

    public static List<Boolean> equalSubSetSumPartition(List<Integer> s) {
        // Write your code here
        int sum= 0;
        List<Boolean> result = new ArrayList<>();
        for(Integer i : s){
            sum = sum+i;
        }
        // if(sum%2 != 0){
        //     return result;
        // }
        int target = sum/2;
        HashSet<Integer> currentSet = new HashSet();
        HashMap<Integer, Boolean> inclusionMap = new HashMap<>();
        boolean found = isEqualSum(s, 0,  0, target, currentSet, inclusionMap);
        if(found){
            for(int i = 0; i < s.size();i++){
                if(currentSet.contains(i)){
                    result.add(i, true);
                }else{
                    result.add(i, false);
                }
            }
        }
        return result;
    }

    static boolean isEqualSum(List<Integer> s, int index , int sum, int target, Set<Integer> currentSet, Map<Integer, Boolean> inclusionMap){
        if(sum == target && currentSet.size() > 0 && currentSet.size() < s.size()){
            return true;
        }
        if(index == s.size()){
            return false;
        }
        if(inclusionMap.containsKey(sum)){
            return inclusionMap.get(sum);
        }
        currentSet.add(index);
        boolean isEqual = isEqualSum(s, index+1, sum+s.get(index), target, currentSet, inclusionMap);
        if(!isEqual){
            currentSet.remove(index);
            isEqual = isEqualSum(s, index+1, sum, target, currentSet, inclusionMap);
        }
        inclusionMap.put(sum, isEqual);
        return isEqual;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            Integer S_item = Integer.parseInt(bufferedReader.readLine().trim());
            s.add(S_item);
        }

        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Boolean> result = equalSubSetSumPartition(s);

        if (result.size() == 0) {
            bufferedWriter.write("-1\n");
            bufferedWriter.close();
            return;
        }

        int s1 = 0, s2 = 0;

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == true) {
                s1++;
            }
            else {
                s2++;
            }
        }

        bufferedWriter.write(String.valueOf(s1));
        bufferedWriter.newLine();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == true) {
                bufferedWriter.write(String.valueOf(s.get(i)));
                bufferedWriter.newLine();
            }
        }

        bufferedWriter.write(String.valueOf(s2));
        bufferedWriter.newLine();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) == false) {
                bufferedWriter.write(String.valueOf(s.get(i)));
                bufferedWriter.newLine();
            }
        }

        bufferedWriter.close();
    }

}
