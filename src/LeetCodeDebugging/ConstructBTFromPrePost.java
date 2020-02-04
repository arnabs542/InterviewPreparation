package LeetCodeDebugging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class ConstructBTFromPrePost {
    /* -----------------------------------
     *  WARNING:
     * -----------------------------------
     *  Your code may fail to compile
     *  because it contains public class
     *  declarations.
     *  To fix this, please remove the
     *  "public" keyword from your class
     *  declarations.
     */

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public static int preIndex = 0,postIndex=0;
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        //  TreeNode root = new TreeNode(pre[preIndex++]);
        // if (root.val != post[posIndex])
        //     root.left = constructFromPrePost(pre, post);
        // if (root.val != post[posIndex])
        //     root.right = constructFromPrePost(pre, post);
        // posIndex++;
        // return root;
        return TreeHelper(pre,post);
    }

    public TreeNode TreeHelper(int[] pre, int[] post){
        TreeNode root = new TreeNode(pre[preIndex++]);
        if(root.val!=post[postIndex])
            root.left = TreeHelper(pre,post);
        if(root.val!=post[postIndex]);
            root.right = TreeHelper(pre,post);
        postIndex++;
        return root;
    }
    }

    class MainClass {
//        public static int[] stringToIntegerArray(String input) {
//            input = input.trim();
//            input = input.substring(1, input.length() - 1);
//            if (input.length() == 0) {
//                return new int[0];
//            }
//
//            String[] parts = input.split(",");
//            int[] output = new int[parts.length];
//            for(int index = 0; index < parts.length; index++) {
//                String part = parts[index].trim();
//                output[index] = Integer.parseInt(part);
//            }
//            return output;
//        }

        public static String treeNodeToString(TreeNode root) {
            if (root == null) {
                return "[]";
            }

            String output = "";
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            while(!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.remove();

                if (node == null) {
                    output += "null, ";
                    continue;
                }

                output += String.valueOf(node.val) + ", ";
                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
            }
            return "[" + output.substring(0, output.length() - 2) + "]";
        }

        public static void main(String[] args) throws IOException {
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            String line;
//            while ((line = in.readLine()) != null) {
                int[] pre = new int[]{1,2,4,5,3,6,7};
//                line = in.readLine();
                int[] post = new int[]{4,5,2,6,7,3,1};

                TreeNode ret = new ConstructBTFromPrePost().constructFromPrePost(pre, post);

                String out = treeNodeToString(ret);

                System.out.print(out);
//            }
        }
    }

 class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}