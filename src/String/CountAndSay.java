package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class CountAndSaySolution {
    public String countAndSay(int n) {
        if(n==1)
            return "1";
        String output = countAndSay(n-1);
        StringBuilder sb = new StringBuilder();
//        System.out.println(n+" "+output);
        int count = 0;
        int len = output.length();
        for(int i=0; i<=len-1;i++){
            if(i==0 && output.length()==1){
                count++;
                sb.append(count).append(output);
                return sb.toString();
            }
            if(i!=0 && output.charAt(i-1) != output.charAt(i)){
                sb.append(count).append(output.charAt(i-1));
                count = 1;
            }else{
                count++;
            }
            if(i==len-1){
                sb.append(count).append(output.charAt(i));
            }
        }

        return sb.toString();
    }
}
public class CountAndSay {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            String ret = new CountAndSaySolution().countAndSay(n);

            String out = (ret);

            System.out.print(out);
        }
    }
}
