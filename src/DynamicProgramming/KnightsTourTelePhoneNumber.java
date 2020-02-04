package DynamicProgramming;
import java.io.*;
import java.util.*;

/*
1 2 3
4 5 6
7 8 9
- 0 -
Input/Output Format For The Custom Input:
Input Format:
The first line should contain an integer startdigit, denoting the digit from which the phone number should start. The second line should contain phonenumberlength, denoting the length of phone number to be formed.
If startdigit = 1 and phonenumberlength = 3, then input should be:
1
3
Output Format:
There will be one line, containing an integer count, denoting result returned by the solution function.
For input startdigit = 1 and phonenumberlength = 3, output will be:
5

Possible numbers of length 3: 160, 161, 167, 181, 183

 */
public class KnightsTourTelePhoneNumber {
    /*
     * Complete the numPhoneNumbers function below.
     */
    static long numPhoneNumbers(int startdigit, int phonenumberlength) {
        /*
         * Write your code here.
         */
        long[][] dptable = createTable(phonenumberlength);
        return dptable[startdigit][phonenumberlength];
    }
    static long[][] createTable(int length){
        long[][] dptable = new long[10][length+1];
        for(int j = 0; j < length+1; j++){
            for(int i = 0 ; i< 10; i++){
                if(j == 0){
                    dptable[i][j] = 0;
                }else if(j ==1 ){
                    dptable[i][j] = 1;
                }
                else if(i == 5){
                    dptable[i][j] = 0;
                }else{
                    int[] next = getNextDigit(i);
                    long sum = 0;
                    for(int neighbour : next){
                        sum+=dptable[neighbour][j-1];
                    }
                    dptable[i][j] = sum;
                }
            }
        }
        return dptable;
    }
    static int[] getNextDigit(int i){
        int[] next = null;
        if(i==0){
            next = new int[] {4,6};
        }else if(i==1){
            next = new int[] {8,6};
        }else if(i==2){
            next = new int[] {7,9};
        }else if(i==3){
            next = new int[] {4,8};
        }else if(i==4){
            next = new int[] {0,3,9};
        }else if(i==5){
            next = new int[0];
        }else if(i==6){
            next = new int[] {1,7,0};
        }else if(i==7){
            next = new int[] {2,6};
        }else if(i==8){
            next = new int[] {1,3};
        }else if(i==9){
            next = new int[] {2,4};
        }
        return next;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int startdigit = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int phonenumberlength = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        long res = numPhoneNumbers(startdigit, phonenumberlength);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

/*
Recursive solution



Think of it recursively like this: How many numbers can I construct using 10 digits starting from 1?



Answer is



[number of 9-digit numbers starting from 8] + [number of 9-digit numbers starting from 6]



So how many "9-digit numbers starting from 8" are there? Well,



[number of 8-digit numbers starting from 1] + [number of 8-digit numbers starting from 3]



Hence, we can recursively build this as



f(len, i) = sum(f(len-1, knight neighbours of i))



Base case would be f(0, num), where

f(0, num) = 1, if num = starting digit

f(0, num) = 0, otherwise



Optimal solution



We can memoize the recurrence relationship mentioned above or build an iterative version for the same problem.



Space Complexity: O(phonenumberlength)

Time Complexity: O(phonenumberlength)


 */
