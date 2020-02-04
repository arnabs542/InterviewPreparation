package Trees;

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
5
1 2 3 4 5
0
4
0 1 L
0 2 R
1 3 L
1 4 R
Output Format:
Space separated integer denoting the values in postorder traversal.
Raw output of sample:
4 5 2 3 1
* */

public class PostOrderTraversalWithoutRecursion {

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
    static int[] postorderTraversal(TreeNode root){
        Stack<TreeNode> s = new Stack<>();
        Stack<TreeNode> result = new Stack();

        if(root == null)
            return new int[0];

        s.push(root);
        while(!s.empty()){
            TreeNode poppedRoot = s.pop();
            result.push(poppedRoot);
            if(poppedRoot.left_ptr!=null)
                s.push(poppedRoot.left_ptr);
            if(poppedRoot.right_ptr!=null)
                s.push(poppedRoot.right_ptr);
        }

        int[] output = new int[result.size()];
        for(int i = 0; i< output.length;i++){
            output[i] = result.pop().val;
        }

        return output;

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

        public static void printArray(int[] result){
            if(result == null){
                System.out.println();
                return;
            }
            for(int i = 0; i < result.length; i++){
                if(i>0){
                    System.out.print(" ");
                }
                System.out.print(result[i]);
            }
            System.out.println();
        }
    public static void main(String[] args){
        try{
            TreeNode root = readBinaryTree();
            int[] result = postorderTraversal(root);
            printArray(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    }
