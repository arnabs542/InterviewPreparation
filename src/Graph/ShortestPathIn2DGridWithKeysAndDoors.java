package Graph;
import java.io.*;
import java.util.*;

/*
*
Input Format: There is only one argument: String array rowStr. rowStr describes the grid.
Cells in the grid can be described as:
'#' = Water.
'.' = Land.
'a' = Key of type 'a'. All lowercase letters are keys.
'A' = Door that opens with key 'a'. All uppercase letters are doors.
'@' = Starting cell.
'+' = Ending cell (goal).
Output Format:
Return a 2D integer array pathArr, containing the path from start cell to goal cell.
Suppose your path contains p cells, then output array should be of size p * 2, where (pathArr[i][0], pathArr[i][1]) describes a cell position.
Positioning of cells:
0-indexed.
Columns: Increasing from left to right.
Rows: Increasing from top to bottom.
There can be multiple shortest paths, so you are free to return any of them.
*
*
*
* Input Format:
The first line of input should contain an integer n, denoting no. of rows in input grid, which is also a size of array rowStr. In next n lines, ith line should contain a string rowStr[i], denoting value at index i of rowStr.
If n = 3, m = 4 and rowStr = [“...B”, “.b#.”, “@#+.”], then input should be:
3
...B
.b#.
@#+.

Output Format:
Let’s denote p * 2 as dimensions of pathArr, where pathArr is the 2D array returned by solution function.The first line of output contains a string (without quotes)   “<len> cells will be visited in shortest path.”, where “<len>”would have been replaced with value of p.In next line, there will be a string (without quotes) “Actual path is:”. In next p lines, ith line contains two space separated integers pathArr[i][0] and pathArr[i][1], denoting value at index (i,0) and (i,1) of pathArr respectively.
For input n = 3, m = 4 and rowStr = [“...B”, “.b#.”, “@#+.”], output will be:
9 cells will be visited in shortest path.

Actual path is:
2 0
1 0
1 1
0 1
0 2
0 3
1 3
2 3
*
* */

public class ShortestPathIn2DGridWithKeysAndDoors {
    /*
     * Complete the function below.
     */

    static List<int[]> paths = new ArrayList();
    static int validMoves = 4;
    static int[] addRows = {-1,0,1,0};
    static int[] addCols = {0,1,0,-1};
    private static class Cell{
        int row;
        int col;
        int key;
        int dist;
        Cell parent;

        Cell(int r, int c, int k, int d){
            this.row = r;
            this.col = c;
            this.key = k;
            this.dist = d;
        }
    }
    static int[][] find_shortest_path(String[] grid) {
        Cell startCell = null;
        int rows = grid.length;
        int cols = grid[0].length();
        for(int i = 0; i< rows;i++){
            for(int j = 0; j< cols; j++){
                if(grid[i].charAt(j) == '@')
                    startCell = new Cell(i,j,0,0);
            }
        }

        bfs(startCell, grid);
        if (paths.size() == 0) {
            return new int[0][0];
        }

        Collections.reverse(paths);
        return paths.toArray(new int[paths.size()][]);


    }

    static void bfs(Cell startCell, String[] grid){
        Queue<Cell> q = new LinkedList();
        boolean[][][] visited = new boolean[grid.length][grid[0].length()][1024];
        visited[startCell.row][startCell.col][0] = true;
        q.add(startCell);

        while(!q.isEmpty()){
            Cell curr = q.poll();

            if(isDest(curr.row, curr.col, grid)){
                while(curr!=null){
                    paths.add(new int[]{curr.row, curr.col});
                    curr = curr.parent;
                }
                break;
            }

            for(int i=0;i<validMoves; i++){
                int nextRow = curr.row + addRows[i];
                int nextCol = curr.col + addCols[i];

                if(!isValid(nextRow, nextCol, grid)){
                    continue;
                }
                if(isWater(nextRow, nextCol, grid)){
                    continue;
                }
                if(isDoor(nextRow, nextCol, grid)){
                    if(!hasKey(curr.key, nextRow, nextCol, grid)){
                        continue;
                    }
                }
                Cell next = new Cell(nextRow, nextCol, curr.key, curr.dist+1);
                if(isKey(nextRow,nextCol,grid)){
                    next.key = addKey(next, grid);
                }

                next.parent = curr;
                if(!visited[next.row][next.col][next.key]){
                    q.add(next);
                    visited[next.row][next.col][next.key] = true;
                }

            }

        }
    }

    static boolean isDest(int nextRow, int nextCol, String[] grid){
        return grid[nextRow].charAt(nextCol) == '+';
    }
    static boolean isValid(int nextRow, int nextCol, String[] grid){
        return (0<=nextRow) && (nextRow < grid.length) && (0<=nextCol) && (nextCol<grid[0].length());
    }
    static boolean isWater(int nextRow, int nextCol, String[] grid){
        return grid[nextRow].charAt(nextCol) == '#';
    }
    static boolean isDoor(int nextRow, int nextCol, String[] grid){
        return grid[nextRow].charAt(nextCol) >= 'A' && grid[nextRow].charAt(nextCol) >= 'J';
    }
    static boolean isKey(int nextRow, int nextCol, String[] grid){
        return grid[nextRow].charAt(nextCol) >= 'a' && grid[nextRow].charAt(nextCol) >= 'j';
    }
    static boolean hasKey(int key, int nextRow, int nextCol, String[] grid){
        return (key & (1<<grid[nextRow].charAt(nextCol)-'A')) == 1;
    }
    static int addKey(Cell cell, String[] grid){
        return cell.key | (1<<grid[cell.row].charAt(cell.col)-'a');
    }


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[][] res;
        int grid_size = 0;
        grid_size = Integer.parseInt(in.nextLine().trim());

        String[] grid = new String[grid_size];
        for(int i = 0; i < grid_size; i++) {
            String grid_item;
            try {
                grid_item = in.nextLine();
            } catch (Exception e) {
                grid_item = null;
            }
            grid[i] = grid_item;
        }

        res = find_shortest_path(grid);
        int res_rowLength = res.length;
        for(int res_i = 0; res_i < res_rowLength; res_i++) {
            for(int res_j = 0; res_j < res[res_i].length; res_j++) {
                bw.write(String.valueOf(res[res_i][res_j]) + " ");
            }
            bw.newLine();
        }

        bw.close();
    }
}

/*
* If there are no keys and doors then solution would be a simple BFS.



Now lets think about the original problem.



To solve the original problem also, we can use BFS, but with some modifications!



There can be only 10 different keys ('a' to 'j'). So we can use bitmasking to store the keys. Specifically we can use int to store the keys that we already have (call that int as ring_of_keys). If we have only key of type 'a' then ring_ok_keys would be 000..000001 in binary representation, if we have two keys 'a' and 'd' then ring_of_keys would be 000..001001 in binary representation. Here we will use 10 least significant bits to store the keys.



Now lets think about BFS when neighbour cell is:

1) Water: Simply return because we can not use water cell.

2) Land (not key and not door): Continue BFS. Visit(update/add/consider) its neighbours.

3) Start: Consider it as land cell.

4) Stop: Return and update ans.

5) Door: Check if corresponding key is present in the ring_of_keys or not, if yes then treat it as land cell else treat it as water cell. We can check it using

if ((ring_of_keys >> (grid[r][c] - 'A')) & 1)

treat as land

else

treat as water

6) Key: This is the part where we need to pay attention. If we already have collected the same type of key then consider it as a land cell because it does not change anything, else we need to do something more. If we have found a new key then it might be possible that in past during BFS, somewhere we were not able to go thru door but now we can because that can be opended by this key. So we need to reconsider the visited cells "and" continue visiting unvisited cells also.

if ((ring_of_keys >> (grid[r][c] - 'a')) & 1)

treat as land

else

revisit visited cells and continue BFS



Now first have a look at the "exponential solution" provided by us (brute_force.java). That uses DFS but idea is almost same. This will help you understand the basic idea.



This solution will only work for smaller constraints. Problem with this solution is that it does lots of recomputation.



Now let's think about optimized solution.



Let's take dp[r][c][ring_of_keys] as shortest path from starting point to current point denoted by (r, c), where we have already collected keys present in ring_of_keys.



Base case is dp[start_r][start_c][0] = 0. Set others as INFINITY, now do BFS!



During BFS when neighbour cell is:

1) Water: Simply return because we can not use water cell.

2) Land: Add that cell and update it. We can go to that neighbour cell by taking one more step! And we will also have all the keys. So that can be done as,

dp[neighbout_r][neighbour_c][ring_of_keys] = dp[cur_r][cur_c][ring_of_keys] + 1.

3) Start: Consider it as land cell.

4) Stop: Return and update ans.

5) Door: Check if corresponding key is present in the ring_of_keys or not, if yes then treat it as land cell else treat it as water cell.

6) Key: If we already have collected the same type of key then consider it as a land cell because it does not change anything, else update differently. Add the key to our ring_of_keys (let's say new_ring_of_keys). So that can be done as,

dp[neighbout_r][neighbour_c][new_ring_of_keys] = dp[cur_r][cur_c][ring_of_keys] + 1.



In normal BFS we do not visit previously visited cell again, here we will not visit previously visited "state" again, which means we will not visit dp[r][c][ring_of_keys] if it is already visited (but here it is possible that same node is visited again!).



Lets take very small example.

Grid = "a@A+" now initially we have,

dp[0][1][0] = 0, (we are at the starting position and we don't have any key)

now from '@' 'a' will be updated hence,

dp[0][0][1] = 1, (we are at 'a' and we have collected one key)

now this will update '@',

dp[0][1][1] = 2, (again we are at the starting position and we have collected one key)

so '@' is visited again.



Now have a look at the optimal_solution.cpp. It will give more clear idea about the solution.



Time complexity, auxiliary space used and space complexity of the solution is O(number of rows * number of cols * 2^(number of different keys possible that is 10 in our case)).

 * */
