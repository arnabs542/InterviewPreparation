package Graph;
import java.util.*;
import java.io.*;

/*
Input Format:
The first line of the input contains a number row_count, denoting the number of rows of the matrix. The second line of the input contains a number col_count, denoting the number of columns of the matrix. Next row_count lines, contains col_count numbers denoting matrix[i][j] where 0<=i<row_count and 0<=j<col_count.
For row_count=3, col_count=3 and matrix = [ [1, 5, 2], [2, 4, 7], [3, 6, 9]]
3
3
1 5 2
2 4 7
3 6 9
Output Format:
The output contains an array of numbers basin_sizes. Values of the basin_sizes array will be represented as one on a line.
For row_count=3, col_count=3 and matrix = [ [1, 5, 2], [2, 4, 7], [3, 6, 9]], output will be:
2
7


 */

public class CountBasins {

    static int sinkId = 1;
    public static List<Integer> find_basins(List<List<Integer>> matrix) {
        //Write your code here
        int[][] graph = convert(matrix);
        int rows = graph.length;
        int cols = graph[0].length;
        int[][] basinIds = new int[rows][cols];

        for(int i =0;i<rows; i++){
            for(int j=0;j<cols; j++){
                if(basinIds[i][j] == 0){
                    dfs(graph, i, j, rows, cols, basinIds);
                }
            }
        }

        return countBasins(basinIds, rows, cols);

    }

    static List<Integer> countBasins(int[][] graph, int rows, int cols){
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i=0; i<rows; i++){
            for(int j=0;j<cols; j++){
                Integer count = countMap.getOrDefault(graph[i][j], 0);
                count++;
                countMap.put(graph[i][j], count);
            }
        }
        List<Integer> results = new ArrayList();
        for(Integer val : countMap.values()){
            results.add(val);
        }
        Collections.sort(results, new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2){
                return i2- i1;
            }
        });
        return results;
    }
    static int dfs(int[][] graph, int i, int j, int rows, int cols, int[][] basinIds){
        int[] neighbour = findSink(graph, i, j, rows, cols);
        if(neighbour == null){
            basinIds[i][j] = sinkId;
            sinkId++;
            return basinIds[i][j];
        }
        int ni = neighbour[0];
        int nj = neighbour[1];

        if(basinIds[ni][nj]!=0){
            basinIds[i][j] = basinIds[ni][nj];
            return basinIds[i][j];
        }

        basinIds[i][j] = dfs(graph, ni, nj, rows, cols, basinIds);
        return basinIds[i][j];
    }

    static int[] findSink(int[][] graph, int i, int j, int rows, int cols){
        int minVal = graph[i][j];
        int direction  = 0;
        if(j-1 >=0 && minVal > graph[i][j-1]){
            minVal = graph[i][j-1];
            direction = 1;
        }
        if(j+1 < cols && minVal > graph[i][j+1]){
            minVal = graph[i][j+1];
            direction = 2;
        }
        if(i-1 >=0 && minVal > graph[i-1][j]){
            minVal = graph[i-1][j];
            direction = 3;
        }
        if(i+1 < rows && minVal > graph[i+1][j]){
            minVal = graph[i+1][j];
            direction = 4;
        }
        switch(direction){
            case 1:
                return new int[]{i,j-1};
            case 2:
                return new int[]{i, j+1};
            case 3:
                return new int[]{i-1, j};
            case 4:
                return new int[]{i+1, j};
            default:
                return null;
        }
    }
    static int[][] convert(List<List<Integer>> matrix){
        int rows = matrix.size();
        int cols = matrix.get(0).size();
        int[][] graph = new int[rows][cols];

        for(int i =0 ; i< rows; i++){
            for(int j = 0; j< cols; j++){
                graph[i][j] = matrix.get(i).get(j);
            }
        }
        return graph;
    }



    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int row_count = Integer.parseInt(bufferedReader.readLine().trim());
        int col_count = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < row_count; i++) {
            String[] row_values = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> row = new ArrayList<>();

            for (int j = 0; j < col_count; j++) {
                row.add(Integer.parseInt(row_values[j]));
            }

            matrix.add(row);
        }

        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        // printMatrix(row_count, col_count, matrix);

        List<Integer> basin_sizes = find_basins(matrix);

        for (int i = 0; i < basin_sizes.size(); i++){
            bufferedWriter.write(basin_sizes.get(i)+"\n");
        }
        bufferedWriter.close();
    }

    public static void printMatrix(int row_count, int col_count, List<List<Integer>> matrix) {
        for (int i = 0; i < row_count; i++) {
            for (int j = 0; j < col_count; j++) {
                System.out.printf("%d ", matrix.get(i).get(j));
            }
            System.out.println("");
        }
    }
}


/*
Sample Test Cases:
Sample Input:
3
3
1 5 2
2 4 7
3 6 9
Sample Output:

2
7
Explanation
The basins, labeled with A’s and B’s, are:
A A B
A A B
A A A
Every cell labeled with A will sink at (0, 0), whereas B will sink at (0, 2).
 */