package Graph;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/*
*
Input Format: The first line contains integer n, the number of Strings. The next n lines contain a String each.
If arr = {“110”,”110”,”001”} then the input should be:
3
110
110
001

Output Format: Output the number of zombie clusters in a single line. For the above input, the output would be
2*

* */

class ZombieClusterResult{
    public static int zombieCluster(List<String> zombies) {
        // Write your code here

        boolean[] visited = new boolean[zombies.size()];
        int connectedZombies = 0;
        for(int i =0; i< zombies.size(); i++){
            if(!visited[i]){
                connectedZombies++;
                explore(i, zombies, visited);

            }
        }
        return connectedZombies;

    }

    public static void explore(int zombieIndex, List<String> zombies, boolean[] visited){
        visited[zombieIndex] = true;
        String neighbours = zombies.get(zombieIndex);
        for(int i =0; i< neighbours.length(); i++){
            if(neighbours.charAt(i) == '1' && !visited[i]){
                explore(i, zombies, visited);
            }
        }
    }

}
public class ZombieCluster {
    /*
     * Complete the 'zombieCluster' function below.
     *
     * The function accepts STRING ARRAY as parameter.
     */


    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> zombies = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        int result = ZombieClusterResult.zombieCluster(zombies);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        bufferedReader.close();
    }
}
