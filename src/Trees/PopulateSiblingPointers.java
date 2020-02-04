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
7
1 2 3 4 5 6 7
0
6
0 1 L
0 2 R
1 3 L
1 4 R
2 5 L
2 6 R
Output Format:
line number 1: <noOfNodes> denoting number of nodes of the tree.
line number 2: <noOfNdoes space seprated integers> denoting the values of the nodes. Please make sure there are not leading or trailing spaces and between two integers there must be a single space.
line number 3: <noOfNdoes space seprated string> denoting the values of the next right node. If there are no next right node then print the string "null" without quote. Please make sure there are not leading or trailing spaces and between two integers there must be a single space.
line number 4: <rootIndex> denoting the root of the tree. value of rootIndex must be between -1 to noOfNodes-1
line number 5: <noOfEdges> denoting the number of edges of the tree
next noOfEdges line: <parentNodeIndex><space><childNodeIndex><space><leftOrRightFlag>
Here <parentNodeIndex> and <childNodeIndex> are the node indexes ranging from 0 to noOfNodes-1. <leftOrRighFlag> is either "L" or "R" (without quotes) denoting the left or right child where "L" stands for left child and "R" stands for right child.
Raw output of sample:
7
1 2 3 4 5 6 7
null 3 null 5 6 7 null
0
6
0 1 L
0 2 R
1 3 L
1 4 R
2 5 L
2 6 R
 */

public class PopulateSiblingPointers {
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
    static TreeNode dummy = new TreeNode(0);
    static TreeNode populateSiblingPointers(TreeNode root){
        if(root == null)
            return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        siblingPointerHelper(queue);
        return root;
    }
    static void siblingPointerHelper(Queue<TreeNode> queue){
        while(queue.size()>0){
            int size = queue.size();
            for(int i= 0;i<size; i++){
                TreeNode qNode = queue.poll();
                dummy.nextRight = qNode;
                dummy = dummy.nextRight;
                if(qNode.left_ptr!=null)
                    queue.add(qNode.left_ptr);
                if(qNode.right_ptr!=null)
                    queue.add(qNode.right_ptr);
            }
            dummy.nextRight= null;
            dummy = new TreeNode(0);
        }
    }


    public static void main(String[] args){
        try{
            TreeNode root = readBinaryTree();
            TreeNode result = populateSiblingPointers(root);
            printBinaryTree(result);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

        private static class TreeNode{
            public int val;
            public TreeNode left_ptr;
            public TreeNode right_ptr;
            public TreeNode nextRight;

            public TreeNode(){
                this.left_ptr = null;
                this.right_ptr = null;
                this.nextRight = null;
            }

            public TreeNode(int val){
                this.val = val;
                this.left_ptr = null;
                this.right_ptr = null;
                this.nextRight = null;
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
            public ArrayList<TreeNode> nextRightNodes;
            public int rootIndex;
            public int noOfEdges;
            public ArrayList<Edge> edges;
            public TreeNode root;

            public BinaryTree(){
                noOfNodes = 0;
                rootIndex = -1;
                noOfEdges =0;
                nodeValues = new ArrayList();
                nextRightNodes = new ArrayList();
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

            private void considerEdge(String lOrR, TreeNode parent, TreeNode child,
                                      HashMap<TreeNode, Integer> nodeToId, Counter id, CycleIndicator cycleIndicator
            ){
                if(child != null){
                    if(!nodeToId.containsKey(child)){
                        nodeToId.put(child, id.counter++);
                        nodeValues.add(child.val);
                        nextRightNodes.add(child.nextRight);
                    }else{
                        cycleIndicator.edgeCreateCycle = true;
                        System.err.println("Cycle detected in the tree. Cycle contains the edge: "
                                +nodeToId.get(parent) + " " + nodeToId.get(child) +  " " + lOrR);
                    }
                    edges.add(new Edge(nodeToId.get(parent), nodeToId.get(child), lOrR));
                }
            }
            private void getNodeValuesAndEdges(TreeNode root, HashMap<TreeNode, Integer> nodeToId, Counter id){
                if(root == null){
                    return;
                }

                CycleIndicator leftEdgeCreateCycle = new CycleIndicator();
                CycleIndicator rightEdgeCreateCycle = new CycleIndicator();
                considerEdge("L", root, root.left_ptr, nodeToId, id, leftEdgeCreateCycle);
                considerEdge("R", root, root.right_ptr, nodeToId, id, rightEdgeCreateCycle);

                if(leftEdgeCreateCycle.edgeCreateCycle == false){
                    getNodeValuesAndEdges(root.left_ptr, nodeToId, id);
                }
                if(rightEdgeCreateCycle.edgeCreateCycle == false){
                    getNodeValuesAndEdges(root.right_ptr, nodeToId, id);
                }
            }

            public void populateRawValuesFromTree(){
                noOfNodes = 0;
                nodeValues.clear();
                rootIndex = -1;
                noOfEdges = 0;
                edges.clear();

                if(root != null){
                    Counter id  = new Counter();
                    HashMap<TreeNode, Integer> nodeToId = new HashMap();
                    rootIndex = 0;
                    nodeValues.add(root.val);
                    nextRightNodes.add(root.nextRight);
                    nodeToId.put(root, id.counter++);
                    getNodeValuesAndEdges(root, nodeToId, id);
                    noOfNodes = nodeValues.size();
                    noOfEdges = edges.size();
                }
            }

            public void writeRawValues(){
                System.out.println(noOfNodes);
                for(int i=0;i<noOfNodes;i++){
                    if(i>0){
                        System.out.print(" ");
                    }
                    System.out.print(nodeValues.get(i));
                }
                if(noOfNodes > 0){
                    System.out.println();
                }

                for(int i=0;i<noOfNodes;i++){
                    if(i>0){
                        System.out.print(" ");
                    }
                    if(nextRightNodes.get(i)==null){
                        System.out.print("null");
                    } else {
                        System.out.print(nextRightNodes.get(i).val);
                    }
                }
                if(noOfNodes > 0){
                    System.out.println();
                }

                System.out.println(rootIndex);

                System.out.println(noOfEdges);
                for(int i=0;i<noOfEdges;i++){
                    System.out.println(edges.get(i).parentNodeIndex+" " + edges.get(i).childNodeIndex
                            +" "+edges.get(i).leftRightFlag);
                }
            }
        }

        public static TreeNode readBinaryTree(){
            BinaryTree inputBinaryTree = new BinaryTree();
            inputBinaryTree.readRawValues();
            inputBinaryTree.buildFromRawValues();
            return inputBinaryTree.root;
        }

        public static void printBinaryTree(TreeNode root){
            BinaryTree outputBinaryTree = new BinaryTree();
            outputBinaryTree.root = root;
            outputBinaryTree.populateRawValuesFromTree();
            outputBinaryTree.writeRawValues();
        }


    }
