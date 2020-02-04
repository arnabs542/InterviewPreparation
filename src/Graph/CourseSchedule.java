package Graph;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/*
*
*
* Input Format: The first line of input should contain a single number n, denoting the number of courses. Second line should contain a single number e, denoting the length of 2d array prerequisites. In the next e lines, each line contain 2 space separated numbers denoting two courses dependency that first course is dependent on second course.
If n=4, e=4 and prerequisites=[ [1, 0], [2, 0], [3, 1], [3, 2] ], then input should be:
4
4
1 0
2 0
3 1
3 2

* Output Format: There should be a array of numbers representing resultant ordering of courses.
If n=4, e=4 and prerequisites=[ [1, 0], [2, 0], [3, 1], [3, 2] ], then output should be:
0
1
2
3
*
* */

class Result{
    /*
     * Complete the function below.
     */
    static List<Integer> course_schedule(int n, List<List<Integer>> prerequisites) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        for(List<Integer> prereq : prerequisites){
            graph[prereq.get(0)].add(prereq.get(1));
        }
        List<Integer> results = new ArrayList<Integer>();
        int[] visited = new int[n];
        boolean hasCycle = false;

        for(int i=0;i<n;i++){
            if(visited[i] == 0){
                hasCycle = dfs(graph,visited, results,i);
                if(hasCycle){
                    results = new ArrayList<>();
                    results.add(-1);
                    return results;
                }
            }
        }

        return results;
    }

    static boolean dfs(List<Integer>[] graph, int[] visited, List<Integer> results, int i){
        if(visited[i] == 2)
            return false;
        if(visited[i] == 1)
            return true;
        visited[i] = 1;

        for(Integer neighbour : graph[i]){
            boolean hasCycle = dfs(graph, visited, results, neighbour);
            if(hasCycle)
                return true;
        }

        results.add(i);
        visited[i] = 2;
        return false;
    }
}

public class CourseSchedule {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        int e = Integer.parseInt(bufferedReader.readLine().trim());
        int prerequisitesColumns = 2;

        List<List<Integer>> prerequisites = new ArrayList<>();

        IntStream.range(0, e).forEach(i -> {
            try {
                prerequisites.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.course_schedule(n, prerequisites);
        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
