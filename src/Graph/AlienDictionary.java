package Graph;

import java.io.IOException;
import java.util.*;

public class AlienDictionary {

    private static String alienOrder(String[] words) {
            Map<Character, List<Character>> graph = new HashMap<>();
            for(int i = 0; i< words.length; i++){
                for(int j = i+1; j< words.length; j++)
                buildGraph(graph, words[i], words[j]);
            }
            Stack<Character> order = topologicalSort(graph);
            StringBuilder sb = new StringBuilder();
            while(!order.isEmpty()){
                sb.append(order.pop());
            }
            return sb.toString();
        }
        public static Stack<Character> topologicalSort(Map<Character, List<Character>> graph){
            Set<Character> visited = new HashSet<>();
            Stack<Character> order = new Stack<>();
            for(char vertex : graph.keySet()){
                if(!visited.contains(vertex))
                    topSort(visited, order, graph, vertex);
            }
            return order;

        }
        public static void topSort(Set<Character> visited, Stack<Character> order, Map<Character, List<Character>> graph, char vertex){
            if(visited.contains(vertex)) return;
            visited.add(vertex);
            for(char neighbour : graph.getOrDefault(vertex, new ArrayList<Character>())){
                topSort(visited, order, graph, neighbour);
            }
            order.push(vertex);
        }
        public static void buildGraph(Map<Character, List<Character>> graph, String word1, String word2){
            int length = Math.min(word1.length(), word2.length());
            for(int j = 0; j< length; j++){
                if(word1.charAt(j)!=word2.charAt(j)){
                    List neighbours = graph.getOrDefault(word1.charAt(j), new ArrayList<Character>());
                    if(!neighbours.contains(word2.charAt(j)))
                        neighbours.add(word2.charAt(j));
                    graph.put(word1.charAt(j), neighbours);
                    break;

                }
            }
        }
//    }
    public static void main(String[] args) {

//        public static void main(String[] args) throws IOException {
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            String line;
//            while ((line = in.readLine()) != null) {
                String[] words = {"wrt","wrf","er","ett","rftt"};

                String ret = alienOrder(words);

                String out = (ret);

                System.out.print(out);
            }
        }


//}

