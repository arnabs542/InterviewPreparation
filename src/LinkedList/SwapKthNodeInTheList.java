package LinkedList;
import java.io.*;
import java.util.*;


/*

Input Format:
The first line of input should contain an integer n, denoting size of input linked list L. In next n lines, ith line should contain an integer Li, denoting value in ith node of L. In the next line, there should be an integer k, denoting the size of group as explained in problem statement section.
If n = 6, L: 1 -> 2 -> 3 -> 4 -> 7 -> 0 -> NULL and k = 2, then input should be:
6
1
2
3
4
7
0
2
Output Format:
There will be n lines, where ith line contains value of ith node of resL. Here, resL is the resultant linked list returned by the solution function.
For input n = 6, L: 1 -> 2 -> 3 -> 4 -> 7 -> 0 -> NULL and k = 2, output will be:
1
7
3
4
2
0
 */

public class SwapKthNodeInTheList {

    /*
     * Complete the function below.
     */
    /*
    For your reference:

    LinkedListNode {
        int val;
        LinkedListNode next;
    };
    */
    static LinkedListNode swap_nodes(LinkedListNode head, int k) {
        int n = 0;
        LinkedListNode temp = head;

        while(temp!=null){
            n++;
            temp = temp.next;
        }

        int kth_index = k-1;
        int mth_index = n-k;
        temp = head;
        int val_kth = 0;
        int val_mth = 0;
        int i = 0;
        while(temp!=null){
            if(i == kth_index)
                val_kth = temp.val;
            if(i == mth_index)
                val_mth = temp.val;

            temp = temp.next;
            i++;
        }

        temp = head;
        i =0;

        while(head!=null){
            if(i == kth_index)
                head.val = val_mth;
            if(i == mth_index)
                head.val = val_kth;

            head = head.next;
            i++;
        }
        head = temp;
        return head;


    }


    public static class LinkedListNode {
        int val;
        LinkedListNode next;

        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    };

    public static LinkedListNode _insert_node_into_singlylinkedlist(LinkedListNode head, LinkedListNode tail, int val) {
        if (head == null) {
            head = new LinkedListNode(val);
            tail = head;
        } else {
            tail.next = new LinkedListNode(val);
            tail = tail.next;
        }

        return tail;
    }
    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int headSize = Integer.parseInt(scan.nextLine().trim());

        LinkedListNode head = null;
        LinkedListNode headTail = null;

        for (int headItr = 0; headItr < headSize; headItr++) {
            int headItem = Integer.parseInt(scan.nextLine().trim());
            headTail = _insert_node_into_singlylinkedlist(head, headTail, headItem);

            if (headItr == 0) {
                head = headTail;
            }
        }

        int k = Integer.parseInt(scan.nextLine().trim());

        LinkedListNode res = swap_nodes(head, k);

        while (res != null) {
            bw.write(String.valueOf(res.val));

            if (res.next != null) {
                bw.write("\n");
            }

            res = res.next;
        }

        bw.newLine();
        bw.close();
    }
}
