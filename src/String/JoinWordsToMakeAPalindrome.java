package String;
import java.io.*;
import java.util.*;

/*
Input Format:
Only argument for function, list of strings words.

3
indeed
asdfg
eedni

Output Format:Return a pair of words (i.e. list of string result of size 2 such that result[0] + result[1] is a palindrome).
In case of multiple answer return any one of them.
In case of no answer return list [“NOTFOUND”, “DNUOFTON”].
indeed
eedni

 */
public class JoinWordsToMakeAPalindrome {
    /*
     * Complete the join_words_to_make_a_palindrome function below.
     */
    static String[] join_words_to_make_a_palindrome(String[] words) {
        /*
         * Write your code here.
         */
        if(words == null || words.length < 2){
            return new String[]{"NOTFOUND", "DNUOFTON"};
        }
        HashMap<String, Integer> map = new HashMap();
        int i = 0;
        for(String word : words){
            map.put(new StringBuilder(word).reverse().toString(), i++);
        }
        i=0;
        for (String word : words){

            for(int j=0;j<word.length();j++){
                String prefix = word.substring(0,j);
                String suffix = word.substring(j,word.length());

                Integer position = null;
                if(isPalindrome(prefix) && map.containsKey(suffix)){
                    position = map.get(suffix);
                }else if(isPalindrome(suffix) && map.containsKey(prefix)){
                    position = map.get(prefix);
                }else{
                    position = null;
                }

                if(position != null && position != i){
                    return i < position ? new String[]{words[i], words[position]}
                            :new String[]{ words[position], words[i]};
                }
            }
            i++;
        }
        return new String[]{"NOTFOUND", "DNUOFTON"};

    }
    static boolean isPalindrome(String s){
        if(s == null) return false;
        if(s.length() ==1 ) return true;
        int left = 0;
        int right = s.length()-1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }



    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int wordsCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        String[] words = new String[wordsCount];

        for (int wordsItr = 0; wordsItr < wordsCount; wordsItr++) {
            String wordsItem = scanner.nextLine();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");
            words[wordsItr] = wordsItem;
        }

        String[] res = join_words_to_make_a_palindrome(words);

        for (int resItr = 0; resItr < res.length; resItr++) {
            bufferedWriter.write(res[resItr]);

            if (resItr != res.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}


/*
Description:



A better approach would be as follows from some observations:



Let say there exists a pair of words (words[x], words[y]), such that result = (words[x] + words[y]) is a palindrome.



Two cases are possible here:



CASE 1: words[x].length() >= words[y].length()



Iterating over words, considering the word in the current iteration as xth word in words. Task is to find out if there exists some yth word, such that words[x] + words[y] is a palindrome. Now, if such y exists, it must be of the form stringReverse(words[x].substring(0, k)), for some 0 <= k < words[x].length().

So, now we only need to find such k, such that (words[y] == stringReverse(words[x].substring(0, k))) and (words[x].substring(k+1, words[x].length())) is a palindrome, if (k+1< words[x].length())



CASE 2: words[x].length() < words[y].length()

Iterating over words, considering the word in the current iteration as xth word in words. Task is to find out if there exists some yth word, such that words[y] + words[x] is a palindrome. Now, if such y exists, it must be of the form stringReverse(words[x].substring(k, words[x].length())), for some 0 <= k < words[x].length().

So, now we only need to find such k, such that (words[y] == stringReverse(words[x].substring(k, words[x].length()))) and (words[x].substring(0, k)) is a palindrome, if (k>0)



Both cases requires a quick lookup of words in list words. So, we can use hashset or hashMap here for constant time (amortized time) lookup of words. Also, in some cases, for eg. "aaaaa", we need to know the frequency of words so that

same word (same indexed word in list of words) doesn't get picked up as other word to make a palindrome. So, hashmap having word as key and frequency of that word as value will work here.



See the implementation for better understanding.





Time Complexity:



O(n*(l^2)) where n is size of list words and l is the maximum length of words in list words.



As while iterating over list of words, considering the word in current iteration as left_word, we have to do two lookups and two palindrome check for each k, 0 <= k < length(left_word), time complexity will be O(l^2) for each word left_word.

So, total time complexity will be O(n*(l^2)).



Auxiliary Space Used:



O(n*l) where n is size of list words and l is the maximum length of words in list words.



As we are maintaining a hashmap of frequencies of words for n words of list words, space complexity to maintain this will be O(n*l) and we are storing result list of two words of maximum length l.

O(n*l) + O(l) → O(n*l)



Space Complexity:



O(n*l) where n is size of list words and l is the maximum length of words in list words.



Input will take space O(n*l) because we are storing n words of list words where maximum possible length of word can be l and auxiliary space used is O(n*l). So, O(n*l) + O(n*l) -> O(n*l).


 */