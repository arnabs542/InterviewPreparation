package String;
import java.io.*;
import java.util.*;
/*
nput Format: The first line should contain a string t, denoting text string. Next line should contain an integer q, denoting no. of queries to be answered. In the next q lines, ith line contains a string pi, denoting pattern string for ith query, i=(1,2,...,q).
If
Ourbusinessisourbusinessnoneofyourbusiness
3
business
our
daisy
then input should be:
Ourbusinessisourbusinessnoneofyourbusiness
3
business
our
daisy
Output Format:
Output will be printed in the sequence of queries asked. So, output of 1st query will be printed first, followed by output of 2nd query and so on, upto output of qth query.
For ith query, let say length of resultant array posi[] is leni.
Then, for ith query, there will be leni lines, where jth line of these leni lines contains a number posi[j], denoting number at jth index of posi.
So, in total, no. of lines will be = (len1 + len2 + len3 + … + lenq)
For input:
t = "Ourbusinessisourbusinessnoneofyourbusiness",
q = 3,
p in 1st query = "business",
p in 2nd query = "our",
p in 3rd query = "daisy",
output will be:
3
16
34
13
31
-1

 */
public class KMP {
    // Complete the KMP function below.
    static int[] KMP(String t, String p) {

        if(t.length() < p.length()){
            return new int[]{-1};
        }
        List<Integer> result = new ArrayList<Integer>();
        int[] lps = getLPS(p);

        int ti=0;
        int pi=0;

        while(ti < t.length() && pi < p.length()){
            if(t.charAt(ti) == p.charAt(pi)){
                ti++;
                pi++;
                if(pi==p.length()){
                    result.add(ti-pi);
                    pi = lps[pi-1];
                }
            }else{

                if(pi>0){
                    pi = lps[pi-1];
                }else{
                    ti++;
                }

            }
        }
        if(result.size() == 0){
            return new int[]{-1};
        }

        int[] resultArray = new int[result.size()];
        int size = 0;
        for(Integer i : result){
            resultArray[size++] = i;
        }
        return resultArray;
    }

    static int[] getLPS(String p){
        int[] lps = new int[p.length()];

        int index = 0;
        lps[index++] = 0;
        int start=0;
        while(index < p.length()){
            if(p.charAt(start) == p.charAt(index)){
                start++;
                lps[index] = 1+lps[index-1];
                index++;
            }else{
                start = 0;
                lps[index++]=0;

            }
        }


//        int len = 0;
//        lps[0] = 0;
//
//        for(int i= 1; i<p.length();){
//            if(p.charAt(len) == p.charAt(i)){
//                len++;
//                lps[i] = len;
//                i++;
//            }else{
//                if(len > 0){
//                    len = lps[len-1];
//                }else{
//                    lps[i] = 0;
//                    i++;
//                }
//            }
//        }
        return lps;
    }


    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String t = br.readLine().trim();

        int q = Integer.parseInt(br.readLine().trim());
        while (q-- > 0) {
            String p = br.readLine().trim();

            int[] res = KMP(t, p);
            for (int i = 0; i < res.length; i++) {
                bufferedWriter.write(String.valueOf(res[i]));

                if (i != res.length - 1) {
                    bufferedWriter.write("\n");
                }
            }

            bufferedWriter.newLine();

        }
        bufferedWriter.close();
        bufferedWriter.close();
    }
}
