package Trees;
import java.io.*;
import java.util.*;
/*

Input Format:
line number 1: <noOfNodes> denoting number of nodes of the tree.
line number 2: <noOfNdoes space seprated integers> denoting the values of the nodes. Please make sure there are not leading or trailing spaces and between two integers there must be a single space.
line number 3: <rootIndex> denoting the root of the tree. value of rootIndex must be between -1 to noOfNodes-1
line number 4: <noOfEdges> denoting the number of edges of the tree
next noOfEdges line: <parentNodeIndex><space><childNodeIndex><space><leftOrRightFlag>
Here <parentNodeIndex> and <childNodeIndex> are the node indexes ranging from 0 to noOfNodes-1. <leftOrRighFlag> is either "L" or "R" (without quotes) denoting the left or right child where "L" stands for left child and "R" stands for right child.
Raw input of sample:
3
2 1 3
0
2
0 1 L
0 2 R
Output Format:
Space separated integer denoting the values in iterator order
Raw output of sample:
1 2 3


 */
public class TreeIterator {
    /*
        private static class TreeNode{
            public int val;
            public TreeNode left_ptr;
            public TreeNode right_ptr;
        }
    */

    /*
        Complete the function below
    */
    public static void pushLeft(TreeNode root, Stack<TreeNode> stack){
        while(root!=null){
            stack.push(root);
            root = root.left_ptr;
        }
    }
    public static class BSTIterator{
        Stack<TreeNode> stack = new Stack<>();
        public BSTIterator(TreeNode root){
            pushLeft(root, stack);
        }

        public boolean hasNext(){
            return stack.size()>0;
        }

        public int next(){
            TreeNode root = stack.pop();
            pushLeft(root.right_ptr, stack);
            return root.val;
        }
    }


    private static class TreeNode{
            public int val;
            public TreeNode left_ptr;
            public TreeNode right_ptr;

            public TreeNode(){
                this.left_ptr = null;
                this.right_ptr = null;
            }

            public TreeNode(int val){
                this.val = val;
                this.left_ptr = null;
                this.right_ptr = null;
            }
        }

        private static class BinaryTree{
            public class Edge{
                public int parentNodeIndex;
                public int childNodeIndex;
                public String leftRightFlag;

                public Edge(){}

                public Edge(int parentNodeIndex, int childNodeIndex, String leftRightFlag){
                    this.parentNodeIndex = parentNodeIndex;
                    this.childNodeIndex = childNodeIndex;
                    this.leftRightFlag = leftRightFlag;
                }
            }

            public class Counter{
                int counter;
            }

            public class CycleIndicator{
                boolean edgeCreateCycle;
            }

            public int noOfNodes;
            public ArrayList<Integer> nodeValues;
            public int rootIndex;
            public int noOfEdges;
            public ArrayList<Edge> edges;
            public TreeNode root;

            public BinaryTree(){
                noOfNodes = 0;
                rootIndex = -1;
                noOfEdges =0;
                nodeValues = new ArrayList();
                edges = new ArrayList();
                root  = null;
            }

            public void readRawValues(){
                Scanner scan = new Scanner(System.in);

                noOfNodes = scan.nextInt();
                for(int i=0;i<noOfNodes;i++){
                    int nodeVal = scan.nextInt();
                    nodeValues.add(nodeVal);
                }

                rootIndex = scan.nextInt();

                noOfEdges = scan.nextInt();
                for(int i=0;i<noOfEdges;i++){
                    int parentNodeIndex = scan.nextInt();
                    int childNodeIndex = scan.nextInt();
                    String leftRightFlag = scan.next();
                    edges.add(new Edge(parentNodeIndex, childNodeIndex, leftRightFlag));
                }
            }

            public void buildFromRawValues(){
                if(noOfNodes == 0){
                    root =  null;
                    return;
                }

                ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
                for(int i=0;i<noOfNodes;i++){
                    nodes.add(new TreeNode(nodeValues.get(i)));
                }

                for(int i=0;i<noOfEdges;i++){
                    if(edges.get(i).leftRightFlag.equals("L")){
                        nodes.get(edges.get(i).parentNodeIndex).left_ptr
                                = nodes.get(edges.get(i).childNodeIndex);
                    }else{
                        nodes.get(edges.get(i).parentNodeIndex).right_ptr = nodes.get(edges.get(i).childNodeIndex);
                    }
                }

                root = nodes.get(rootIndex);
                return;
            }
        }

        public static TreeNode readBinaryTree(){
            BinaryTree inputBinaryTree = new BinaryTree();
            inputBinaryTree.readRawValues();
            inputBinaryTree.buildFromRawValues();
            return inputBinaryTree.root;
        }


        public static void printIterator(TreeNode root){
        BSTIterator iterator = new BSTIterator(root);
        boolean isFirst = true;
        while(iterator.hasNext()){
            if(!isFirst){
                System.out.print(" ");
            }
            System.out.print(iterator.next());
            isFirst = false;
        }
        System.out.println();
    }
    public static void main(String[] args){
        try{
            TreeNode root = readBinaryTree();
            printIterator(root);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
