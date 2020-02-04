package Trees;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

/*
Sample Input 1: inorder = [2, 1, 3] and preorder = [1, 2, 3]
Sample Output 1:
  1

/ \

2 3

3
2
1
3
3
1
2
3

Explanation 1:
In this case, Binary tree will be look like as given above.
Return the pointer of root node of constructed binary tree. In this case root treenode has value '1'.
12##3##


 */

public class ConstructABinaryTree {
    /*
     * Complete the 'constructBinaryTree' function below.
     *
     * The function accepts INTEGER_ARRAY inorder and preorder as parameter, and returns Root pointer of constructed binary tree.
     *
     * Definition for binary tree.
     *   class TreeNode {
     *        int value;
     *        TreeNode left;
     *        TreeNode right;
     *
     *        TreeNode(int x) {
     *             value = x;
     *             left=null;
     *             right=null;
     *        }
     *   }
     */
    static int curr = 0;
    public static TreeNode constructBinaryTree(List<Integer> inorder, List<Integer> preorder) {
        // Write your code here

        if(inorder.size() == 0 || inorder.size() != preorder.size()){
            return null;
        }
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.size(); i++){
            inorderMap.put(inorder.get(i), i);
        }

        return binaryTreeUtil(preorder, 0, inorder.size()-1, inorderMap);
    }

    public static TreeNode binaryTreeUtil(List<Integer> preorder, int start, int end, HashMap<Integer, Integer> inorderMap){
        if(start>end){
            return null;
        }
        TreeNode root = new TreeNode(preorder.get(curr++));
        if(start == end){
            return root;
        }
        int index = inorderMap.get(root.value);
        root.left = binaryTreeUtil(preorder, start, index-1, inorderMap);
        root.right = binaryTreeUtil(preorder, index+1, end, inorderMap);
        return root;
    }
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            value = x;
            left=null;
            right=null;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int arr_len = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> inorder = IntStream.range(0, arr_len).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        arr_len = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> preorder = IntStream.range(0, arr_len).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());


        TreeNode root = constructBinaryTree(inorder, preorder);

        printPreOrder(root);
        System.out.println();

        bufferedReader.close();
    }

    private static void printPreOrder(TreeNode root) {

        if (root == null) {
            System.out.print("#");
            return;
        }


        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(root);

        while (!st.empty()) {

            TreeNode node = st.peek();
            if(node == null) {
                System.out.print("#");
                st.pop();
                continue;
            }
            System.out.print(node.value);
            st.pop();


            if (node.right != null) {
                st.push(node.right);
            } else {
                st.push(null);
            }
            if (node.left != null) {
                st.push(node.left);
            } else {
                st.push(null);
            }
        }
    }



}
