package Sorting;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/*
*Input Format: The first line of input contains x coordinate of point p, p_x. The next line contains y coordinate of point p, p_y. The next line contains integer k. The next line contains the number of rows n in array points and the following line contains c, the number of columns in array points. It is guaranteed that c = 2 always. The following n lines contain 2 integers each, the x and y coordinates of a point.
Example:
0
0
2
3
2
1 1
0 1
1 0
Output Format:
Output k lines, each line contains two integers each representing x and y coordinates. For the above input, the output will be:
0 1
1 0
* */
public class KNearestNeighbour {
    /*
     * Complete the 'nearest_neighbours' function below.
     *
     * The function accepts integer p_x, p_y, k and a 2D integer array n_points as parameter.
     */


    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int p_x = Integer.parseInt(bufferedReader.readLine().trim());

        int p_y = Integer.parseInt(bufferedReader.readLine().trim());

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int n_pointsRows = Integer.parseInt(bufferedReader.readLine().trim());
        int n_pointsColumns = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> n_points = new ArrayList<>();

        IntStream.range(0, n_pointsRows).forEach(i -> {
            try {
                n_points.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> result = Result.nearest_neighbours(p_x, p_y, k, n_points);

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
class Result {
    static class Point{
        List<Integer> coords;
        double distance;
        Point(List<Integer> coords, int px, int py){
            this.coords = coords;
            long dx = (coords.get(0)-px);
            long dy = (coords.get(1)-py);
            long sumSq = dx*dx + dy*dy;
            this.distance =Math.sqrt((double)sumSq);
        }

        public List<Integer> getCoords(){
            return this.coords;
        }
        public double getDistance(){
            return this.distance;
        }
    }

    public static List<List<Integer>> nearest_neighbours(int p_x, int p_y, int k, List<List<Integer>> n_points) {
        // Write your code here
        // Write your code here
        Point[] points = new Point[n_points.size()];
        for(int i=0; i<n_points.size(); i++){
            points[i] = new Point(n_points.get(i), p_x, p_y);
        }

        q_select(points, k, 0, n_points.size()-1);
        List<List<Integer>> output = new ArrayList<>();
        for(int i=0; i < k;i++){
            output.add(points[i].getCoords());
        }
        return output;
    }

    static void q_select(Point[] points, int k, int start, int end){
        if(start >= end)
            return;

        int p_index = partition(points, start, end);
        int p = p_index+1;
        if(k==p){
            return;
        }

        if(k<p){
            q_select(points, k, start, p_index-1);
        }else {
            q_select(points, k, p_index+1, end);
        }

    }

    static int partition(Point[] points, int s, int e){
        Random random = new Random();
        //int random_pivot = s + (e-s)/2;
        int random_pivot = random.nextInt(e-s)+s;
        swap(points, random_pivot, e);
        double pivot_dist = points[e].getDistance();
        int i = s;
        for(int curr=s; curr<=e-1;curr++){
            if(points[curr].getDistance() <= pivot_dist){
                swap(points, curr, i);
                i++;
            }
        }
        swap(points, i, e);
        return i;


    }

    static void swap(Point[] points, int i, int j){
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

}
