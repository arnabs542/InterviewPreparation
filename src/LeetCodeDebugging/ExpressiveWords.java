package LeetCodeDebugging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExpressiveWords {
    static class Solution {
        public int expressiveWords(String S, String[] words) {
            int[] frequency = new int[128];
            for(int i =0; i<S.length(); i++){
                frequency[S.charAt(i) - 'a']++;
            }
            int result = 0;
            for(int w = 0; w < words.length; w++){
                String word = words[w];
                int j = 0, count = 0;
                int[] wordCharFreq = new int[128];
                if(word.length() >= S.length()) continue;
                if(!buildWordFrequency(word, wordCharFreq, frequency)) continue;
                if(!detectMismatch(word, wordCharFreq, frequency)) result++;
            }
            return result;
        }

        public boolean buildWordFrequency(String word, int[] wordCharFreq, int[] frequency){
            for(int i = 0; i < word.length(); i++){
                if(frequency[word.charAt(i)-'a'] == 0) return false;
                wordCharFreq[word.charAt(i)- 'a']++;
            }
            return true;
        }
        public boolean detectMismatch(String word, int[] wordCharFreq, int[] frequency){
            for(int i = 0; i < word.length(); i++){
                if(frequency[word.charAt(i)-'a'] - wordCharFreq[word.charAt(i)-'a'] == 1) return true;
            }
            return false;
        }
    }


//        public static String stringToString(String input) {
//            return JsonArray.readFrom("[" + input + "]").get(0).asString();
//        }
//
//        public static String[] stringToStringArray(String line) {
//            JsonArray jsonArray = JsonArray.readFrom(line);
//            String[] arr = new String[jsonArray.size()];
//            for (int i = 0; i < arr.length; i++) {
//                arr[i] = jsonArray.get(i).asString();
//            }
//            return arr;
//        }

        public static void main(String[] args) throws IOException {
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            String line;
//            while ((line = in.readLine()) != null) {
                String S = "dddiiiinnssssssoooo";
//                line = in.readLine();

//            "dddiiiinnssssssoooo"
//                    []
                String[] words = new String[]{"dinnssoo","ddinso","ddiinnso","ddiinnssoo","ddiinso","dinsoo","ddiinsso","dinssoo","dinso"};

                int ret = new Solution().expressiveWords(S, words);

                String out = String.valueOf(ret);

                System.out.println(out);
            }
        }


