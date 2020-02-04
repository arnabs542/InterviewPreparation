package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SubDomainVisitCountSolution {
    public List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> countMap = new HashMap<>();
        for(String domain : cpdomains){
            String[] domainCount = domain.split(" ");
            int countVal = Integer.parseInt(domainCount[0]);
            String domainStr = domainCount[1];
            String[] splitStr = domainStr.split(".");
            int n = splitStr.length;
            updateCountMap(countMap, splitStr[n-1], countVal);
            updateCountMap(countMap,splitStr[n-2]+"."+splitStr[n-1],countVal);
            if(n == 3){
                updateCountMap(countMap,domainStr,countVal);
            }
        }
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String, Integer> entry : countMap.entrySet()){
            sb.append(entry.getValue()).append(" ").append(entry.getKey());
            results.add(sb.toString());
            sb.setLength(0);
        }
        return results;

    }
    public void updateCountMap(HashMap<String, Integer> countMap , String key, int count){
        int countSoFar = countMap.getOrDefault(key,0);
        countSoFar = countSoFar+count;
        countMap.put(key, countSoFar);
    }
}
public class SubDomainVisitCount {
//    public static String[] stringToStringArray(String line) {
//        JsonArray jsonArray = JsonArray.readFrom(line);
//        String[] arr = new String[jsonArray.size()];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = jsonArray.get(i).asString();
//        }
//        return arr;
//    }

    public static String stringListToString(List<String> stringList) {
        StringBuilder sb = new StringBuilder("[");
        for (String item : stringList) {
            sb.append(item);
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
//            String[] cpdomains = stringToStringArray(line);
            String[] cpdomains = new String[]{"9001 discuss.leetcode.com"};

            List<String> ret = new SubDomainVisitCountSolution().subdomainVisits(cpdomains);

            String out = stringListToString(ret);

            System.out.print(out);
        }
    }
}
