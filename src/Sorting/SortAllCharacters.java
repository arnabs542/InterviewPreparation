package Sorting;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;
import java.io.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
/*
*
*Input Format: The first line contains the size, n, of the character array. Next n lines each contain the characters present in the array, with each character of the array in a new line.
If arr = {a,z,i,#,&,l,c} then input should be:
7
a
z
i
#
&
l
c
Output Format: Output each character of the array on a new line, in sorted order of their ASCII values.
For above input - arr = {a,z,i,#,&,l,c}, output will be:
#
&
a
c
i
l
z
* */
class ResultSortAllCharacters {

    /*
     * Complete the 'sort_array' function below.
     *
     * The function accepts Character Array arr as parameter.
     */

    public static List<Character> sort_array(List<Character> arr) {
        // Write your code here
        int[] frequency = new int[128];

        for(char c : arr){
            frequency[c]++;
        }
        arr.clear();
        for(int i =0;i< frequency.length; i++){
            for(int j=0;j<frequency[i];j++)
                arr.add((char) i);
        }

        return arr;

    }


}
public class SortAllCharacters {
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Character> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(e -> e.charAt(0))
                .collect(toList());

        List<Character> result = ResultSortAllCharacters.sort_array(arr);

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
