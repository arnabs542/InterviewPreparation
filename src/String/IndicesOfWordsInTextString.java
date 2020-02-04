package String;

import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.*;

/*
Input Format:
Function has two arguments: string text and list of strings words.
you are very very smart
4
you
are
very
handsome
Output Format:
Function must return a list of lists of integers. One list for each one of the words. i-th list must contain all indices of characters in text where i-th word starts, in the ascending order. If i-th word isn’t found in the text at all, then i-th list must be [-1].
0
4
8 13
-1

 */

public class IndicesOfWordsInTextString {

    /*
     * Complete the 'find_words' function below.
     *
     * The function accepts STRING and STRING ARRAY as parameter.
     * Return 2D INTEGER ARRAY.
     */
    public static ArrayList<ArrayList<Integer>> find_words(String text, List<String> words) {
        // Write your code here
        String[] wordsInText = text.split(" ");
        HashMap<String, TreeSet<Integer>> ordermap = new HashMap();
        int index = wordsInText[0].length() + 1;

        ordermap.put(wordsInText[0], new TreeSet<Integer>() {{ add(0); }});
        for(int i = 1 ; i <= wordsInText.length-1; i++){
            TreeSet<Integer> value;
            if(ordermap.containsKey(wordsInText[i])){
                ordermap.get(wordsInText[i]).add(index);
            }else{
                value = new TreeSet<>(); value.add(index);
                ordermap.put(wordsInText[i], value);
            }
            index = index + wordsInText[i].length() + 1;
        }

        ArrayList<ArrayList<Integer>> results = new ArrayList();
        for(String word : words){
            if(ordermap.containsKey(word)){
                results.add(new ArrayList<Integer>(ordermap.get(word)));
            }else{
                ArrayList<Integer> empty = new ArrayList();
                empty.add(-1);
                results.add(empty);
            }
        }
        return results;
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

            String text = bufferedReader.readLine().trim();
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<String> words = new ArrayList<String>();

            for(int i=0;i<q;i++){
                words.add(bufferedReader.readLine().trim());
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            ArrayList<ArrayList<Integer>> result = find_words(text, words);
            result.stream()
                    .map(
                            r -> r.stream()
                                    .map(Object::toString)
                                    .collect(joining(" "))
                    )
                    .map(r -> r + "\n")
                    .collect(toList())
                    .forEach(e -> {
                        try {
                            bufferedWriter.write(e);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
            bufferedWriter.close();
        }
    }

