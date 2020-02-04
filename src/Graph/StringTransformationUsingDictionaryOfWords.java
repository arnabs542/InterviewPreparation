package Graph;
import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

/*
Input Format:



The first line of input should contain an integer n, denoting size of input array words. In next n lines, ith line should contain a string words[i], denoting a value at index i of words. In next line, there should be a string start, denoting the start string. In next line, there should be a string stop, denoting the stop string.
If n = 4, words = ["cat", "hat", "bad", "had"], start = “bat” and stop = “had”, then input should be:
4
cat
hat
bad
had
bat
had
Output Format:
Let’s denote the size of ans array as m, where ans is the output string array returned by solution function.
There will be m lines of output, where ith line contains a string ans[i], denoting a value at index i of ans.
For input n = 4, words = ["cat", "hat", "bad", "had"], start = “bat” and stop = “had”, output will be:
bat
hat
had
*
* */

public class StringTransformationUsingDictionaryOfWords{
    /*
     * Complete the function below.
     */
    static String[] string_transformation(String[] words, String start, String stop) {

        Set<String> dict = findUniqueWords(words);

        boolean mustGetNeighbours = (dict.size() * dict.size()) < (start.length() * 26) ? true : false;
        HashMap<String, String> back_refs = new HashMap();
        Queue<String> queue = new LinkedList();
        back_refs.put(start, null);
        queue.add(start);

        while(!queue.isEmpty()){
            String currStr = queue.poll();
            if(isDiffByOneChar(currStr, stop)){
                back_refs.put(stop, currStr);
                break;
            }

            List<String> neighbours = new ArrayList();
            if(mustGetNeighbours){
                neighbours = getNeighbours(dict, currStr);
            }else{
                neighbours = generateNeighbours(dict, currStr);
            }

            for(String neighbour : neighbours){
                if(!back_refs.containsKey(neighbour)){
                    back_refs.put(neighbour, currStr);
                    queue.add(neighbour);
                }
            }

        }
        List<String> path = buildPath(back_refs, stop, start);
        if (path.size() != 0){
            String[] ret = new String[path.size()];
            ret = path.toArray(ret);
            return ret;
        }
        return new String[]{"-1"};

    }
    static Set<String> findUniqueWords(String[]  words){
        Set<String> uniqueWords = new HashSet();
        for(String word : words){
            uniqueWords.add(word);
        }
        return uniqueWords;
    }
    static boolean isDiffByOneChar(String word1, String word2){
        int diff = 0;
        int i=0;
        while (i<word1.length()){
            if(word1.charAt(i) != word2.charAt(i)){
                ++diff;
            }
            i++;
        }
        return diff == 1;
    }
    static List<String> getNeighbours(Set<String> dict, String str){
        List<String> neighbours = new ArrayList();
        for(String s : dict){
            if(isDiffByOneChar(s, str)){
                neighbours.add(s);
            }
        }

        return neighbours;
    }

    static List<String> generateNeighbours(Set<String> dict, String str){
        List<String> neighbours = new ArrayList<>();
        for(int i = 0;i<str.length();i++){
            String prefix = str.substring(0,i);
            String suffix = str.substring(i+1, str.length());
            for (char c = 'a'; c <= 'z'; c++) {
                if (str.charAt(i) != c) {
                    if(dict.contains(prefix + String.valueOf(c) + suffix)){
                        neighbours.add(prefix + String.valueOf(c) + suffix);
                    }
                }
            }
        }
        return neighbours;
    }

    static List<String> buildPath(HashMap<String, String> back_refs, String target, String start){
        List<String> path = new ArrayList<String>();
        List<String> empty = new ArrayList<String>();

        String str = target;
        while (str != null) {
            path.add(str);
            if (str == start) {
                Collections.reverse(path);
                return path;
            }
            String next = back_refs.get(str);
            str = next;
        }
        return empty;
    }


    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int wordsSize = Integer.parseInt(scan.nextLine().trim());

        String[] words = new String[wordsSize];


        for (int wordsItr = 0; wordsItr < wordsSize; wordsItr++) {
            String wordsItem = scan.nextLine();
            words[wordsItr] = wordsItem;

        }

        String start = scan.nextLine();

        String stop = scan.nextLine();

        String[] res = string_transformation(words, start, stop);

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
