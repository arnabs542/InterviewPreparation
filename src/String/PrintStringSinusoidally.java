package String;

import java.util.*;
/*
This is a string puzzle problem disguised as a programming problem. Also called "SnakeString". For example, the phrase "Google Worked" should be printed as follows (where ~ is the word separator):

    o     ~         k

  o  g  e  W   r   e

G     l        o       d

The length of each row should be the same, i.e. there should be two spaces at the end of the first line, one space at the end of the second line, and zero spaces at the end of the third line.
Input format:
There is only one argument named s denoting the input string.
Google Worked

Output format:

  o   ~   k
 o g e W r e
G   l   o   d



 */

public class PrintStringSinusoidally {
    /*
    Complete the function below
*/
    public static void printStringSinusoidally(String s){
        boolean directionUp = true;
        int n = s.length();
        int rows = 2;
        int cols = 0;
        char[][] matrix = new char[rows+1][n];

        for (int i = 0; i < n ;i++ ){
            if(directionUp){
                matrix[rows][i] = s.charAt(i) == ' '? '~' : s.charAt(i);
                cols++;
                rows--;
                directionUp = rows == 0 ? !directionUp : directionUp;
            }else {

                matrix[rows][i] =  s.charAt(i) == ' '? '~' : s.charAt(i);
                cols++;
                rows++;
                directionUp = rows == 2 ? !directionUp : directionUp;
            }
        }

        for(int i = 0; i< 3; i++){
            for(int j = 0; j < n ;j++){
                if(matrix[i][j] == 0)
                    System.out.print(" ");
                else
                    System.out.print(matrix[i][j]);

                //   System.out.print(" ");
            }
            System.out.println();
        }



    }


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        printStringSinusoidally(s);
    }
}



