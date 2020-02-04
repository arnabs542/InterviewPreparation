package Trees;

import java.util.*;


//Input Format:
//The first line of the input contains an integer noOfNodes, denoting the number of nodes of the tree.
//In the second line of the input, it contains noOfNodes integers, denoting the values of the nodes. Please make sure there are no leading or trailing spaces and between two integers there must be a single space.
//In the third line of the input, it contains a single integer rootIndex, denoting the root of the tree. value of rootIndex must be between 0 to noOfNodes-1.
//In the fourth line of the input, it contains a single integer noOfEdges, denoting the number of edges of the tree.
//Next noOfNodes-1 lines, each line contains parentNodeIndex, childNodeIndex, leftOrRightFlag separated by space. Here parentNodeIndex and childNodeIndex are the node indexes ranging from 0 to noOfNodes-1. leftOrRighFlag is either "L" or "R" (without quotes) denoting the relationship between parent and child, the left or right child where "L" stands for left child and "R" stands for the right child.
//If noOfNodes= 6, values = [5, 5, 5, 5, 5, 5], root_index = 0, noOfEdges=5, tree = [ {Node: 0, LeftChild: 1, RightChild: 2}, {Node: 1, LeftChild: 3, RightChild: 4}, {Node: 2, RightChild: 5}]:
//6
//5 5 5 5 5 5
//0
//5
//0 1 L
//0 2 R
//1 3 L
//1 4 R
//2 5 R
//Output Format:
//A single integer denoting answer of the problem i.e. the number of unival subtrees in a given tree.
//For input, noOfNodes= 6, values = [5, 5, 5, 5, 5, 5], root_index = 0, noOfEdges=5, tree = [ {Node: 0, LeftChild: 1, RightChild: 2}, {Node: 1, LeftChild: 3, RightChild: 4}, {Node: 2, RightChild: 5}], output will be:
//6



public class SingleValueTree {
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
    static int unival = 0;
    static int findSingleValueTrees(TreeNode root){

        findSVT(root);
        return unival;
    }

    static boolean findSVT(TreeNode root){
        if(root == null){
            return true;
        }
        boolean left = findSVT(root.left_ptr);
        boolean right = findSVT(root.right_ptr);

        if(left && right){
            if(root.left_ptr != null && root.left_ptr.val == root.val && root.right_ptr!=null && root.right_ptr.val == root.val){
                unival = unival+1;
                return true;
            }
            if(root.left_ptr != null && root.left_ptr.val == root.val && root.right_ptr==null){
                unival = unival+1;
                return true;
            }
            if(root.right_ptr != null && root.right_ptr.val == root.val && root.left_ptr==null){
                unival = unival+1;
                return true;
            }
            if(root.left_ptr==null && root.right_ptr==null ){
                unival = unival+1;
                return true;
            }

        }
        return false;
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

    public static void main(String args[]) {
        /*
        This function is used to increase the size of recursion stack. It makes the size of stack
        2^26 ~= 10^8
        */
        new Thread(null, new Runnable() {
            public void run() {
                try{
                    solve();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public static void solve(){
        try{
            TreeNode root = readBinaryTree();
            int result = findSingleValueTrees(root);
            System.out.println(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
