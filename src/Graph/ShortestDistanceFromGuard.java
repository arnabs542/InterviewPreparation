package Graph;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


/*
Input Format: The first line of input should contain an integer n, denoting no. of rows in input grid, which is also a size of array rowStr.
The second line of input should contain an integer m, denoting no. of columns in input grid.
In next n lines, the ith line should contain a string rowStr[i], denoting value at index i of rowStr.
If n = 5, m = 5 and rowStr = [“OOOOG”, “OWWOO”, “OOOWO”, “GWWWO”, “OOOOG”], then the input should be:
5
5
OOOOG
OWWOO
OOOWO
GWWWO
OOOOG

Output Format:
Output 2D array of integers resArr will be of same size of given 2D array i.e. n * m.
There will be n lines of output, where the ith line contains m space separated integers. So, jth integer in ith line is value resArr[i][j], denoting value at index (i, j) of resArr.
For input n = 5, m = 5 and rowStr = [“OOOOG”, “OWWOO”, “OOOWO”, “GWWWO”, “OOOOG”], output will be:
3 3 2 1 0
2 -1 -1 2 1
1 2 3 -1 2
0 -1 -1 -1 1
1 2 2 1 0
 */
public class ShortestDistanceFromGuard {
    /*
     * Complete the 'find_shortest_distance_from_a_guard' function below.
     *
     * The function accepts the 2D CHARACTER ARRAY.
     * Returns 2D INTEGER ARRAY.
     */
    public static class Node{
        int row;
        int col;
        int distance;

        Node(int r, int c, int d){
            this.row = r;
            this.col = c;
            this.distance = d;
        }
    }
    public static List<List<Integer>> find_shortest_distance_from_a_guard(List<List<Character>> grid) {
        // Write your code here

        int rows = grid.size();
        int cols = grid.get(0).size();
        List<List<Integer>> distance = new ArrayList<>();
        for(int i = 0; i< rows; i++){
            distance.add(new ArrayList<Integer>());
            for(int j = 0; j<cols;j++){
                distance.get(i).add(-1);
            }
        }
        Queue<Node> queue = new LinkedList<>();
        initializeQueue(queue, grid, rows, cols);
        boolean[][] seen = new boolean[rows][cols];
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            if(seen[curr.row][curr.col]){
                continue;
            }
            seen[curr.row][curr.col] = true;
            distance.get(curr.row).set(curr.col, curr.distance);
            for(Node next : getNeighbours(curr, rows, cols, grid)){
                next.distance = curr.distance+1;
                queue.add(next);
            }

        }

        return distance;
    }
    public static List<Node> getNeighbours(Node curr, int rows, int cols, List<List<Character>> grid){
        List<Node> neighbours = new ArrayList<>();
        int row = curr.row;
        int col = curr.col;
        //left
        if(col-1 >=0 && grid.get(row).get(col-1)=='O'){
            Node newNode = new Node(row, col-1,0);
            neighbours.add(newNode);
        }
        //right
        if(col+1 < cols && grid.get(row).get(col+1)=='O'){
            Node newNode = new Node(row, col+1,0);
            neighbours.add(newNode);
        }
        //up
        if(row-1 >=0 && grid.get(row-1).get(col)=='O'){
            Node newNode = new Node(row-1,col,0);
            neighbours.add(newNode);
        }
        //down
        if(row+1 < rows && grid.get(row+1).get(col)=='O'){
            Node newNode = new Node(row+1, col,0);
            neighbours.add(newNode);
        }
        return neighbours;

    }
    public static void initializeQueue(Queue<Node> queue, List<List<Character>> grid, int rows, int cols){
        for(int i = 0; i < rows;i++){
            for(int j = 0 ;j < cols; j++){
                if(grid.get(i).get(j) == 'G'){
                    queue.add(new Node(i,j,0));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Character>> grid = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                grid.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(""))
                                .map(e -> e.charAt(0))
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> result = find_shortest_distance_from_a_guard(grid);

        result.stream()
                .map(
                        r -> r.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                )
                .map(r -> r + "\n")
                .collect(toList())
                .forEach(e -> {
                    try {
                        bufferedWriter.write(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });

        bufferedWriter.close();

        bufferedReader.close();
    }
}
