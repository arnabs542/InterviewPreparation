package Graph;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;


/*
Custom Input Format: First line of input contains n (size of the board). Second line also contains n (size of array moves). Next n lines contain values from moves[0] to moves[n-1].
For n = 20 and moves = [-1, 18, -1, -1, -1, -1, -1, -1, 2, -1, -1, 15, -1, -1, -1, -1, -1, -1, -1, -1] input would be as follows:
20
20
-1
18
-1
-1
-1
-1
-1
-1
2
-1
-1
15
-1
-1
-1
-1
-1
-1
-1
-1
Custom Output Format:
The only line of output contains the return integer value, e.g.


 */
public class SnakeAndLadder {
    // Complete the minNumberOfRolls function below.
    static int minNumberOfRolls(int n, List<Integer> moves) {
        int[] move = new int[n];
        for(int i =0;i<n;i++){
            move[i] = moves.get(i);
            if(move[i]!=-1){
                move[i]--;
            }
        }
        return bfs(n, move);
    }


    public static class Node{
        int index;
        int dist;
        Node(int i, int d){
            this.index = i;
            this.dist = d;
        }
    }
    static int bfs(int n,int[] move){
        Queue<Node> queue = new LinkedList();
        boolean[] visited = new boolean[n];
        Node start = new Node(0,0);
        queue.add(start);

        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if(!visited[curr.index]){
                visited[curr.index] = true;
                if(curr.index == n-1){
                    return curr.dist;
                }

                for(int dice = 1; dice <= 6; dice++){
                    int nextMove = curr.index + dice;
                    if(nextMove < n && !visited[nextMove]){
                        Node newNode = null;
                        if(move[nextMove] == -1){
                            newNode = new Node(nextMove, curr.dist+1);
                        }else{
                            newNode = new Node(move[nextMove], curr.dist+1);
                        }
                        queue.add(newNode);

                    }
                }
            }
        }
        return -1;

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int movesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> movesTemp = new ArrayList<>();

        IntStream.range(0, movesCount).forEach(i -> {
            try {
                movesTemp.add(bufferedReader.readLine().replaceAll("\\s+$", ""));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> moves = movesTemp.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int res = minNumberOfRolls(n, moves);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
