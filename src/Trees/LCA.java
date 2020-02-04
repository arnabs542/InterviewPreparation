package Trees;
import java.util.*;
import java.io.*;
/*
Input Format:
The first line contains three space separated integers n, the value of node a, and the value of node b.
Next n - 1 lines contain two space separated integers denoting an edge. The first value is the value of from node, and the second value is the value of the to node.
9 8 9
1 2
1 3
2 4
2 5
3 6
3 7
5 8
5 9
Output Format:
5


The integer value from the LCA of a and b will be printed.


 */

class Node
{
    Node left;
    Node right;
    int data;

    Node(int data)
    {
        this.data = data;
        left = null;
        right = null;
    }
}
public class LCA {
    /* you only have to complete the function given below.
    Node is defined as

    class Node {
      int data;
      Node left;
      Node right;
    }
    we need to find the lca for two nodes a, and b!!
    */

    int lca(Node head, Node a, Node b)
    {

        Stack<Integer> pathToA = new Stack();
        Stack<Integer> pathToB = new Stack();

        dfs(head, a, pathToA);
        dfs(head, b, pathToB);

        int diff = Math.abs(pathToA.size() - pathToB.size());
        if(pathToA.empty() || pathToB.empty()){
            return -1;
        }

        if(pathToA.size() > pathToB.size())
            popExtraElements(pathToA, pathToB.size());
        else if(pathToA.size() < pathToB.size())
            popExtraElements(pathToB, pathToA.size());


        while(pathToA.size()!=0){
            int nodeA = pathToA.pop();
            int nodeB = pathToB.pop();
            if(nodeA == nodeB)
                return nodeA;
        }

        return -1;

    }

    static void popExtraElements(Stack<Integer> stack, int size){
        while(stack.size() > size){
            stack.pop();
        }
    }

    static boolean dfs(Node root, Node target, Stack<Integer> stack){
        if(root == null)
            return false;

        if(root.data == target.data){
            stack.push(root.data);
            return true;
        }
        stack.push(root.data);
        boolean isPresentInLeftSubTree = dfs(root.left, target, stack);
        if(isPresentInLeftSubTree)
            return true;
        boolean isPresentInRightSubTree = dfs(root.right, target, stack);

        if(isPresentInRightSubTree)
            return true;
        else{
            stack.pop();
            return false;
        }

    }
    public static void preorder(Node root) {
        if (root == null) { return; }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }



    public static void parse_input(int siz , int from[] , int to[], Node[] arr_stub )
    {
        for(int i=1;i<siz;i++)
        {
            int x = from[i];
            int y = to[i] ;
            if( (arr_stub[x].left) == null )
                arr_stub[x].left = arr_stub[y] ;
            else
                arr_stub[x].right = arr_stub[y] ;
        }
    }
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        Node[] arr_stub = new Node[100020];
        int t = 1;
        Node root = null;
        for(int i = 1 ; i <= 100000;i++)
            arr_stub[i] = new Node(i) ;
        int[] from = new int[100020] ;
        int[] to = new int[100020] ;
        while(t-- > 0)
        {
            int n = scan.nextInt();
            int a = scan.nextInt();
            int b = scan.nextInt();
            for(int i=1;i<n;i++)
            {
                from[i] = scan.nextInt();
                to[i] = scan.nextInt() ;
            }
            parse_input( n ,from , to , arr_stub);
            LCA temporary = new LCA();
            int answer = temporary.lca( arr_stub[1] , arr_stub[a] , arr_stub[b] ) ;
            //preorder(arr_stub[1]);
            // we passed the head pointer basically which is arr_stub[1]
            System.out.println(answer) ;
        }
        scan.close();
    }


}
