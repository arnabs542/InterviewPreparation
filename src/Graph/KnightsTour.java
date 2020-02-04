package Graph;
import java.io.*;
import java.util.*;
/*
*
*
* Input Format: There are six arguments. First is an integer denoting rows, second is an integer denoting cols, third is an integer denoting start_row, fourth is an integer denoting start_col, fifth is an integer denoting end_row and sixth is an integer denoting end_col.

* Output Format: Return an integer.If it is possible to reach from starting position to ending position then return minimum number of moves that the knight needs to take to get from starting position to ending position.
If it is not possible to reach from starting position to ending position then return -1.

  Sample input :
  * 5
  * 5
  * 0
  * 0
  * 4
  * 1
  Sample output
  * 3
* */
public class KnightsTour {
    private static class Position{
        int row;
        int col;
        int depth;

        public Position(int r, int c, int d){
            this.row = r;
            this.col = c;
            this.depth = d;
        }
    }
    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {
        // Write your code here.
        int[] add_rows = {-2, -2, -1, 1, 2, 2, 1, -1};
        int[] add_cols = {-1, 1, 2, 2, 1, -1, -2, -2};
        boolean[][] visited = new boolean[rows][cols];
        int next_available_moves = 8;

        if(start_row == end_row && start_col == end_col){
            return 0;
        }
        if(!isValidConfiguration(rows, cols, start_row, start_col) || !isValidConfiguration(rows, cols, end_row, end_col)){
            return -1;
        }
        Queue<Position> queue = new LinkedList<>();
        Position startPos = new Position(start_row, start_col,0);
        visited[start_row][start_col] = true;
        queue.add(startPos);

        while(!queue.isEmpty()){
            Position currPos = queue.poll();

            for(int i=0;i<next_available_moves; i++){
                int next_row = currPos.row + add_rows[i];
                int next_col = currPos.col + add_cols[i];
                if(isValidConfiguration(rows, cols, next_row, next_col)){
                    Position newPos = new Position(next_row, next_col, currPos.depth+1);

                    if(!visited[next_row][next_col]){
                        if(next_row == end_row && next_col==end_col){
                            return currPos.depth+1;
                        }
                        visited[next_row][next_col] = true;
                        queue.add(newPos);
                    }


                }
            }
        }

        return -1;
    }

    public static boolean isValidConfiguration(int rows,int cols, int start_row, int start_col){
        return (0<=start_row) && (start_row < rows) && (0<=start_col) && (start_col < cols);
    }
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int rows = Integer.parseInt(scan.nextLine().trim());

        int cols = Integer.parseInt(scan.nextLine().trim());

        int start_row = Integer.parseInt(scan.nextLine().trim());

        int start_col = Integer.parseInt(scan.nextLine().trim());

        int end_row = Integer.parseInt(scan.nextLine().trim());

        int end_col = Integer.parseInt(scan.nextLine().trim());

        int res = find_minimum_number_of_moves(rows, cols, start_row, start_col, end_row, end_col);

        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}
