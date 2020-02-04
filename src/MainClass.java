import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int[][] result = new int[K][];
        if(points == null || K > points.length || K==0){
            return new int[0][0];
        }

        int i = 0;
        Point[] pointsArray = new Point[points.length];
        for(int[] point : points){
            pointsArray[i] = new Point(point[0], point[1], Math.sqrt(point[0]*point[0]+point[1]*point[1]));
            i++;
        }

        qSelect(pointsArray, 0, pointsArray.length-1, K);
        for(i =0;i<K;i++){
            int[] ret = new int[]{pointsArray[i].x,pointsArray[i].y};
            result[i] = ret;
        }
        return result;
    }

    public void qSelect(Point[] pointsArray, int start, int end, int K){
        if(start >= end){
            return;
        }
        int pivotIndex = Partition(pointsArray, start, end);
        if (pivotIndex+1 == K)
            return;
        else if(pivotIndex+1 < K)
            qSelect(pointsArray, pivotIndex+1, end, K );
        else
            qSelect(pointsArray, start ,pivotIndex-1, K);
    }

    public int Partition(Point[] pointsArray, int start, int end){
        int mid = start + (end - start)/2;
        Point pivotElement = pointsArray[mid];
        swap(pointsArray, mid, end);
        int pivotIndex=0;
        for(int i = start; i<=end-1; i++){
            Point curr = pointsArray[i];
            if(curr.dist<=pivotElement.dist){
                swap(pointsArray, i, pivotIndex);
                pivotIndex++;
            }
        }
        swap(pointsArray, pivotIndex, end);
        return pivotIndex;
    }

    public void swap(Point[] pointsArray, int i, int j){
        Point temp = pointsArray[i];
        pointsArray[i] = pointsArray[j];
        pointsArray[j] = temp;
    }
}

class Point implements Comparable<Point>{
    int x;
    int y;
    double dist;

    @Override
    public int compareTo(Point other){
        return Double.compare(this.dist, other.dist);
    }

    Point(int x, int y, double dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

//    public static int[][] stringToInt2dArray(String input) {
//        JsonArray jsonArray = JsonArray.readFrom(input);
//        if (jsonArray.size() == 0) {
//            return new int[0][0];
//        }
//
//        int[][] arr = new int[jsonArray.size()][];
//        for (int i = 0; i < arr.length; i++) {
//            JsonArray cols = jsonArray.get(i).asArray();
//            arr[i] = stringToIntegerArray(cols.toString());
//        }
//        return arr;
//    }

//    public static String int2dArrayToString(int[][] array) {
//        if (array == null) {
//            return "null";
//        }
//        if (array.length == 0) {
//            return "[]";
//        }
//
//        StringBuilder sb = new StringBuilder("[");
//        for (int[] item : array) {
//            sb.append(Integer.toString(item));
//            sb.append(",");
//        }
//
//        sb.setCharAt(sb.length() - 1, ']');
//        return sb.toString();
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
//        while ((line = in.readLine()) != null) {
            //[[6,10],[-3,3],[-2,5],[0,2]]
            int[][] points = new int[][]{new int[]{6,10},new int[]{-3,3},new int[]{-2,5}, new int[]{0,2}};
//            line = in.readLine();
            int K = 3;

            int[][] ret = new Solution().kClosest(points, K);

//            String out = int2dArrayToString(ret);

//            System.out.print(out);
        }
    }
//}