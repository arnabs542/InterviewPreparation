package String;
import java.io.*;

/*
Input Format:
The first line of input contains string s. The next line contains string t.
If s = “azisdflc” and t = “zsd” then input should be:
azisdflc
zsd
Output Format:
Output in a single line a string which is the minimum window that contains all the characters of string t.
For input s = “azisdflc” and t = “zsd”, output will be:
zisd


 */

public class MinimumWindowSubstring {


        /*
         * Complete the 'minimum_window' function below.
         *
         * The function accepts STRING s and STRING t as parameter.
         */

        public static String minimum_window(String s, String t) {
            // Write your code here
            if(s.length() < t.length()){
                return "-1";
            }
            String result = "";
            int n = s.length();
            int m = t.length();
            char[] freq1 = new char[128];
            char[] freq2 = new char[128];

            for(char c : t.toCharArray()){
                freq1[c]++;
            }

            int len = n+1;
            int cnt = 0;
            int l = 0;

            for(int i = 0 ; i< s.length(); i++){
                char temp = s.charAt(i);
                freq2[temp]++;
                if(freq1[temp]!=0 && freq2[temp] <= freq1[temp]){
                    cnt++;
                }

                if(cnt >= m){
                    while(freq2[s.charAt(l)] > freq1[s.charAt(l)] || freq1[s.charAt(l)] == 0){
                        if(freq2[s.charAt(l)] > freq1[s.charAt(l)]){
                            freq2[s.charAt(l)]--;
                        }
                        l++;
                    }
                    if(len > i - l + 1){
                        len = i-l+1;
                        result = s.substring(l,l+len);
                    }
                }


            }
            return result.length() == 0 ? "-1": result;
        }
    public static void main(String[] args) throws IOException {

        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String s = bufferedReader.readLine().trim();
            String t = bufferedReader.readLine().trim();
            String result = minimum_window(s,t);

            bufferedReader.close();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            bufferedWriter.write(result);
            bufferedWriter.newLine();
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    }


