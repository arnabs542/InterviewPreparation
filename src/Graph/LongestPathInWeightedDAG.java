package Graph;
import java.io.*;
import java.util.*;


/*
Input Format: The first line of input should contain two space separated integers dag_nodes and dag_edges, denoting no. of nodes and no. of edges in input DAG respectively. In next dag_edges lines, ith line should contain three space separated integers dag_from[i], dag_to[i], dag_weight[i], denoting values from node, to node and weight(length) for ith edge.
In next two lines, first line should contain an integer from_node and second line should contain to_node.
If dag_nodes = 4, dag_edges = 4, dag_from = [1, 1, 1, 3], dag_to = [2, 3, 4, 4], dag_weight =  [2, 2, 4, 3], from_node = 1 and to_node = 4, then input should be:
4 4
1 2 2
1 3 2
1 4 4
3 4 3
1
4

Output Format: Let’s denote m as length of pathArr, where pathArr is the array returned by solution function.
The first line of output contains a string (without quotes)   “<len> is the length of longest path in weighted DAG.”, where “<len>”would have been replaced with length of longest path described by pathArr.
In next line, there will be a string (without quotes) “Actual path is:”.
In next m lines, ith line contains an integer pathArr[i], denoting value at index i of pathArr.
For input dag_nodes = 4, dag_edges = 4, dag_from = [1, 1, 1, 3], dag_to = [2, 3, 4, 4], dag_weight =  [2, 2, 4, 3], from_node = 1 and to_node = 4, output will be:
5 is the length of longest path in weighted DAG.
Actual path is:
1
3
4
 */
public class LongestPathInWeightedDAG {

    /*
     * Complete the function below.
     */
    /*
    	For the weighted graph:
    	1. The number of nodes is <name>_nodes.
    	2. The number of edges is <name>_edges.
    	3. An edge exists between <name>_from[i] to <name>_to[i] and the weight of the edge is <name>_weight[i].
    */
    // static List<Integer> max_path = new ArrayList<>();
    // static long maxCount = Long.MIN_VALUE;
    static int[] find_longest_path(int dag_nodes, int[] dag_from, int[] dag_to, int[] dag_weight, int from_node, int to_node) {
        if(from_node == to_node)
            return new int[]{to_node};
        List<List<int[]>> graph = new ArrayList<>();
        for(int i = 0 ;i<= dag_nodes; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i < dag_from.length; i++){
            graph.get(dag_from[i]).add(new int[]{dag_to[i], dag_weight[i]});
        }
        Stack<Integer> stack = new Stack<>();
        boolean [] visited = new boolean[dag_nodes+1];

        for(int i =1; i<=dag_nodes;i++){
            if(!visited[i]){
                topSort(graph, i , visited, stack);
            }
        }
        long[] dist = new long[dag_nodes+1];
        int[] parent = new int[dag_nodes+1];
        for(int i=0;i<=dag_nodes;i++)
            dist[i]=Long.MIN_VALUE;
        dist[from_node]=0;
        while(!stack.isEmpty()){
            int popped = stack.pop();
            if(graph.get(popped)!=null && graph.get(popped).size() > 0){
                for(int[] adj: graph.get(popped)){
                    if(dist[adj[0]] < dist[popped]+adj[1]){
                        dist[adj[0]] = dist[popped]+adj[1];
                        parent[adj[0]] = popped;
                    }
                }
            }
        }

        List<Integer> max_path = new ArrayList<>();

        while(to_node!=0){
            max_path.add(to_node);
            to_node = parent[to_node];
        }

        // long sumCount = 0;
        // dfs(graph, from_node, to_node, path, sumCount);
        int[] res = new int[max_path.size()];
        int index =0;
        for (int i = max_path.size()-1; i >=0; i--){
            res[index++] = max_path.get(i);
        }
        return res;
    }

    static void topSort(List<List<int[]>> graph, int i ,boolean[] visited, Stack<Integer> stack){
        if(visited[i])
            return;
        visited[i] = true;
        if(graph.get(i)!=null && graph.get(i).size() > 0){
            for(int[] adj: graph.get(i)){
                topSort(graph, adj[0], visited, stack);
            }
        }
        stack.push(i);
    }

    // static void dfs(List<List<int[]>> graph, int from_node, int to_node, List<Integer> path, long sumCount ){
    //     path.add(from_node);
    //     if(from_node == to_node){
    //         if(maxCount< sumCount){
    //             maxCount = sumCount;
    //             max_path = new ArrayList<>(path);
    //         }
    //         return;
    //     }


    //     for(int[] next : graph.get(from_node)){
    //         dfs(graph, next[0], to_node, path, sumCount+next[1]);
    //         // System.out.println("next node "+next[0]);
    //         path.remove(path.size()-1);
    //     }
    // }



    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] res;
        String[] dag_nodesm = in.nextLine().split(" ");
        int dag_nodes = Integer.parseInt(dag_nodesm[0]);
        int dag_edges = Integer.parseInt(dag_nodesm[1]);

        int[] dag_from = new int[dag_edges];
        int[] dag_to = new int[dag_edges];
        int[] dag_weight = new int[dag_edges];

        for (int dag_i = 0; dag_i < dag_edges; dag_i++) {
            String[] dag_fromvw = in.nextLine().split(" ");
            dag_from[dag_i] = Integer.parseInt(dag_fromvw[0]);
            dag_to[dag_i] = Integer.parseInt(dag_fromvw[1]);
            dag_weight[dag_i] = Integer.parseInt(dag_fromvw[2]);
        }

        int from_node;
        from_node = Integer.parseInt(in.nextLine().trim());

        int to_node;
        to_node = Integer.parseInt(in.nextLine().trim());

        res = find_longest_path(dag_nodes, dag_from, dag_to, dag_weight, from_node, to_node);
        for(int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}
