package Graph;
import java.io.*;
import java.util.*;
import java.util.stream.*;
/*
Input Format: Function has three arguments: N, number of vertices. M, number of directed edges.
edges, a two-dimensional array where each one of M rows represents an edge; integer values in the first and second columns of a row are (zero-based) indices of the starting and ending vertices, respectively, of that directed edge.
6
7
0 1
1 2
2 3
3 4
4 5
5 0
4 1

Output Format:
Function must return boolean true if at least one cycle exist in the given graph, otherwise it must return false.
1
 */
public class DetectCycleInGraph {

    /*
     * Complete the 'hasCycle' function below.
     *
     * The function accepts INTEGER N, INTEGER M and 2D_INTEGER_ARRAY edges as parameter.
     * The function is expected to return a BOOLEAN.
     */
    public static boolean hasCycle(int N, int M, List<List<Integer>> edges) {
        // Write your code here
        List<Integer>[] graph = new ArrayList[N];
        for(int i=0;i<N;i++){
            graph[i] = new ArrayList<Integer>();
        }

        for(List<Integer> edge : edges){
            graph[edge.get(0)].add(edge.get(1));
        }
        boolean hasCycle = false;
        int[] visited = new int[N];
        for(int i=0;i<N;i++){
            if(visited[i] == 0){
                hasCycle = dfs(graph, visited, i);
                if(hasCycle){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean dfs(List<Integer>[] graph, int[] visited,int i){
        if(visited[i] == 2)
            return false;
        if(visited[i] == 1)
            return true;

        visited[i] = 1;
        for(Integer neighbour : graph[i]){
            boolean hasCycle = dfs(graph, visited, neighbour);
            if(hasCycle){
                return true;
            }
        }
        visited[i]=2;
        return false;
    }



    public static void main(String args[]) {
        /*
        This function is used to increase the size of recurison stack. It makes the size of stack
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
        public static void solve() throws IOException{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            int N = Integer.parseInt(bufferedReader.readLine().trim());
            int M = Integer.parseInt(bufferedReader.readLine().trim());

            List<List<Integer>> edges = new ArrayList<>();

            IntStream.range(0, M).forEach(i -> {
                try {
                    edges.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

            boolean result = hasCycle(N, M, edges);

            bufferedWriter.write(String.valueOf(result ? "1" : "0"));
            bufferedWriter.newLine();

            bufferedWriter.close();
        }
    }


