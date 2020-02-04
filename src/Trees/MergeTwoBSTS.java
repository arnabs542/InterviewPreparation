package Trees;

import java.io.*;
import java.util.*;

/*
Input Format: The first line of the input contains N1, the size of the first BST.
The next N1 lines contain the parent index array of BST-1, where the ith element is the parent index (0-based) of the ith node (parent of the root is -1).
Example for N1 = 3:
1
-1
1
parent of the 0th node is 1st node, 1st node is root and parent of the 2nd node is also 1st node.
The next N1 lines contain the child array of BST-1, where the ith element is 0 if it is the left child, 1 if it is the right child or -1 if it is the root.
Example of N1 = 3:
0
-1
1
0th node is left child of 1st node, 1st node is root and 2nd node is right child of 1st node.
The next N1 lines contain the key array of BST-1, where the ith element is the key of the ith node.
Example of N1 = 3:
1
2
3
The following lines describe the BST-2 in the similar way.

3
1
-1
1
0
-1
1
1
2
3
3
1
-1
1
0
-1
1
6
7
8
Output Format:

If the returned tree is height balanced then:
Inorder traversal of the returned tree will be printed where each value is on a new line.
else:

1
2
3
6
7
8
Note "Returned tree is not height balanced" will be printed.
 */

class TreeNode{

    TreeNode left;
    TreeNode right;
    int key;

    TreeNode(int key) {
        this.left = null;
        this.right = null;
        this.key = key;
    }

}

class MergeTwoBStResult {

    /*
     * Complete the mergeTwoBSTs function below.
     *
     * The function is expected to return an root node of BST.
     */
    public static TreeNode mergeTwoBSTs(TreeNode root1, TreeNode root2) {
        // Write your code here
        if(root2 == null)
            return root1;
        if(root1 == null)
            return root2;

        ArrayList<Integer> list1 = getSortedArray(root1);
        ArrayList<Integer> list2 = getSortedArray(root2);
        ArrayList<Integer> merged = mergeTwoLists(list1, list2);
        TreeNode root = buildBST(merged, 0, merged.size()-1);
        return root;
    }
    static TreeNode buildBST(ArrayList<Integer> merged, int start, int end){
        if(start > end)
            return null;
        if(start == end)
            return new TreeNode(merged.get(start));
        int mid = start + (end - start)/2;
        TreeNode node = new TreeNode(merged.get(mid));
        node.left = buildBST(merged, start, mid-1);
        node.right = buildBST(merged, mid+1, end);
        return node;
    }
    static ArrayList<Integer> mergeTwoLists(ArrayList<Integer> list1, ArrayList<Integer> list2){
        int i = 0; int j = 0;
        ArrayList<Integer> merged = new ArrayList<>();
        while( i<list1.size() && j<list2.size() ){
            if(list1.get(i)<=list2.get(j)){
                merged.add(list1.get(i));
                i++;
            }else{
                merged.add(list2.get(j));
                j++;
            }
        }

        while(i<list1.size()){
            merged.add(list1.get(i));
            i++;
        }
        while(j<list2.size()){
            merged.add(list2.get(j));
            j++;
        }

        return merged;
    }
    static ArrayList<Integer> getSortedArray(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        inOrderHelper(root, list);
        return list;
    }

    static void inOrderHelper(TreeNode root, ArrayList<Integer> list){
        if(root == null)
            return;

        inOrderHelper(root.left, list);
        list.add(root.key);
        inOrderHelper(root.right, list);
    }

}


public class MergeTwoBSTS {
    static void solve() throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int N1 = Integer.parseInt(bufferedReader.readLine().trim());
        int[] parent1 = new int[N1];
        int[] child1 = new int[N1];
        int[] key1 = new int[N1];

        for(int i = 0; i < N1; i++) {
            parent1[i] = Integer.parseInt(bufferedReader.readLine().trim());
        }
        for(int i = 0; i < N1; i++) {
            child1[i] = Integer.parseInt(bufferedReader.readLine().trim());
        }
        for(int i = 0; i < N1; i++) {
            key1[i] = Integer.parseInt(bufferedReader.readLine().trim());
        }

        int N2 = Integer.parseInt(bufferedReader.readLine().trim());
        int[] parent2 = new int[N2];
        int[] child2 = new int[N2];
        int[] key2 = new int[N2];

        for(int i = 0; i < N2; i++) {
            parent2[i] = Integer.parseInt(bufferedReader.readLine().trim());
        }
        for(int i = 0; i < N2; i++) {
            child2[i] = Integer.parseInt(bufferedReader.readLine().trim());
        }
        for(int i = 0; i < N2; i++) {
            key2[i] = Integer.parseInt(bufferedReader.readLine().trim());
        }


        int[][] tree1 = new int[N1][2];
        int[][] tree2 = new int[N2][2];

        for(int i = 0; i < N1; i++)
            tree1[i][0] = tree1[i][1] = -1;

        for(int i = 0; i < N2; i++)
            tree2[i][0] = tree2[i][1] = -1;

        int r1 = -1;
        for(int i = 0; i < N1; i++) {
            if(parent1[i] == -1)
                r1 = i;
            else{
                if(child1[i] == 0) {
                    tree1[parent1[i]][0] = i;
                }
                else if(child1[i] == 1) {
                    tree1[parent1[i]][1] = i;
                }
            }
        }

        int r2 = -1;
        for(int i = 0; i < N2; i++) {
            if(parent2[i] == -1)
                r2 = i;
            else{
                if(child2[i] == 0) {
                    tree2[parent2[i]][0] = i;
                }
                else if(child2[i] == 1) {
                    tree2[parent2[i]][1] = i;
                }
            }
        }

        TreeNode root1 = buildTree(r1, key1, tree1);
        TreeNode root2 = buildTree(r2, key2, tree2);

        TreeNode root = MergeTwoBStResult.mergeTwoBSTs(root1, root2);

        if(isBalanced(root, new Height())) {
            ArrayList<Integer> list = new ArrayList<>();
            inOrderTraversal(root, list);
            for(int ele: list) {
                bufferedWriter.write(String.valueOf(ele));
                bufferedWriter.write("\n");
            }
        }
        else {
            bufferedWriter.write("Returned tree is not height balanced\n");
        }

        bufferedWriter.close();
    }

    public static void main(String[] args) {


        new Thread(null ,new Runnable(){
            public void run()
            {
                try{
                    solve();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        },"1",1<<26).start();


    }

    static class Height {
        int height;
    }

    static boolean isBalanced(TreeNode temp, Height height) {
        if(temp == null)
            return true;

        Height lh = new Height();
        Height rh = new Height();

        boolean l = isBalanced(temp.left, lh);
        boolean r = isBalanced(temp.right, rh);

        height.height = Math.max(lh.height, rh.height) + 1;

        if(Math.abs(lh.height - rh.height) <= 1 && l && r)
            return true;
        return false;
    }

    static void inOrderTraversal(TreeNode temp, ArrayList<Integer> list) {
        if(temp == null)
            return;
        inOrderTraversal(temp.left, list);
        list.add(temp.key);
        inOrderTraversal(temp.right, list);
    }

    static TreeNode buildTree(int idx, int[] key, int[][] tree) {
        TreeNode root = new TreeNode(key[idx]);
        if(tree[idx][0] != -1) {
            root.left = buildTree(tree[idx][0], key, tree);
        }
        if(tree[idx][1] != -1) {
            root.right = buildTree(tree[idx][1], key, tree);
        }
        return root;
    }
}
