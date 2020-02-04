package Recursion;
import java.io.*;
import java.util.*;

/*
Input/Output Format For The Custom Input: Input Format:
The first and only line of input should contain an integer n.
If n = 3, then input should be:
3
Output Format:
There will be one line, containing an integer res, denoting the result returned by solution function.
For input n = 3, output will be:
5

Explanation 3:
1) root (node val = 3), root->left (node val = 2), root->left->left (node val = 1)
2) root (node val = 3), root->left (node val = 1), root->left->right (node val = 2)
3) root (node val = 1), root->right (node val = 2), root->right->right (node val = 3)
4) root (node val = 1), root->right (node val = 3), root->right->left (node val = 2)
5) root (node val = 2), root->left (node val = 1), root->right (node val = 3)


 */
public class HowManyBinarySearchTreesWithNNodes {
    /*
     * Complete the function below.
     */
    static long how_many_BSTs(int n) {
        return countBST(n);
    }
    static long countBST(int n){
        if(n==0){
            return 1L;
        }else{
            long totalCount = 0;
            for(int numberOfNodesInLeftSubTree = 0; numberOfNodesInLeftSubTree < n; numberOfNodesInLeftSubTree++){
                int numberOfNodesInRightSubTree = n-1-numberOfNodesInLeftSubTree;
                long countLeftSubTree = countBST(numberOfNodesInLeftSubTree);
                long countRightSubTree = countBST(numberOfNodesInRightSubTree);
                totalCount += (countLeftSubTree * countRightSubTree);
            }
            return totalCount;
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long res;
        int n;
        n = Integer.parseInt(in.nextLine().trim());

        res = how_many_BSTs(n);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}
