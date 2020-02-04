package LinkedList;

import java.io.*;
import java.util.*;

/*

Input Format:
The first line of input should contain an integer n, denoting length of operations array. In next n lines, ith line should contain an integer operations[i], denoting value at index i of operations array. (i=0,1,...,n-1)
If n = 7 and operations = [10, 5, 0, -1, 0, -1, 0], then input should be:
7
10
5
0
-1
0
-1
0
Output Format:
Let’s denote the total number of min query operations(i.e. operations[j]=0) in input array operations as x. So, length of returned array res must be x.
There will be x lines, where ith line contains an integer res[i], denoting value at index i of res.
For input n = 7 and operations = [10, 5, 0, -1, 0, -1, 0], output will be:
5
10
-1
 */

public class MinStack {

    /*
     * Complete the function below.
     */
    static int[] min_stack(int[] operations) {
        Stack<Integer> stack = new Stack();
        Stack<Integer> min = new Stack();
        ArrayList<Integer> results = new ArrayList();

        for(int i =0; i< operations.length;i++){
            if(operations[i] > 0){
                if(stack.isEmpty() && min.isEmpty()){
                    stack.push(operations[i]);
                    min.push(operations[i]);
                }else{
                    stack.push(operations[i]);
                    if(min.peek() >= operations[i]){
                        min.push(operations[i]);
                    }
                }
            }else if(operations[i] == 0){
                if(min.isEmpty()){
                    results.add(-1);
                }else{
                    results.add(min.peek());
                }
            }else{
                if(!stack.isEmpty()){
                    int popVal = stack.pop();
                    if(popVal == min.peek()){
                        min.pop();
                    }
                }
            }
        }
        int[] ret = new int[results.size()];
        for(int i = 0; i < results.size();i++){
            ret[i] = results.get(i);
        }
        return ret;

    }


    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int operationsSize = Integer.parseInt(scan.nextLine().trim());

        int[] operations = new int[operationsSize];


        for (int operationsItr = 0; operationsItr < operationsSize; operationsItr++) {
            int operationsItem = Integer.parseInt(scan.nextLine().trim());
            operations[operationsItr] = operationsItem;

        }

        int[] res = min_stack(operations);

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
