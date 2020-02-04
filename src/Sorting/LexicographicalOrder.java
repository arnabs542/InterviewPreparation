package Sorting;
import java.io.*;
import java.util.*;

/*
* Input Format:The first line of input should contain an integer N, denoting size of input array arr. In next N lines, ith line should contain a string arr[i], denoting a value at index i of arr.
If N = 5 and arr = [“key1 abcd”, “key2 zzz”, “key1 hello”,
“key3 world”, "key1 hello"], then input should be:
5
key1 abcd
key2 zzz
key1 hello
key3 world
key1 hello

* Output Format:There will be N lines of output, where ith line contains a string res[i], denoting value at index i of res.Here, res is the result array returned by solution function.
For input N = 5 and arr = [“key1 abcd”, “key2 zzz”, “key1 hello”,
“key3 world”, "key1 hello"], output will be:

key3:1,world
key2:1,zzz
key1:3,hello
* */
public class LexicographicalOrder {
    /*
     * Complete the solve function below.
     */
    static String[] solve(String[] arr) {
        /*
         * Write your code here.
         */

        HashMap<String, String[]> map = new HashMap<>();
        for(int i =0;i<arr.length; i++){
            String str = arr[i];
            String key = str.split(" ")[0];
            String val = str.split(" ")[1];
            if(map.containsKey(key)){
                String[] existing = map.get(key);
                int counter = Integer.parseInt(existing[0]);
                String existingVal = existing[1];
                String lexicographicalOrdering = existingVal.compareTo(val) > 0? existingVal: val;
                existing[0] = String.valueOf(++counter);
                existing[1] = lexicographicalOrdering;
                map.put(key, existing);
            }else{

                String[] newVal = {"1", val};
                map.put(key, newVal);
            }
        }
        String[] output = new String[map.size()];
        int i = 0;
        for(Map.Entry<String, String[]> entry : map.entrySet()){
            StringBuilder ans = new StringBuilder();
            String key = entry.getKey();
            String[] valueArray = entry.getValue();

            ans.append(key).append(":").append(valueArray[0]).append(",").append(valueArray[1]);
            output[i++] = ans.toString();
        }
        return output;

    }
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int arrCount = Integer.parseInt(scan.nextLine().trim());

        String[] arr = new String[arrCount];

        for (int arrItr = 0; arrItr < arrCount; arrItr++) {
            String arrItem = scan.nextLine();
            arr[arrItr] = arrItem;
        }

        String[] res = solve(arr);

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
