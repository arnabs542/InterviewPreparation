package LinkedList;
import java.io.*;
import java.util.*;
/*
Input Format:
The first line of input should contain an integer n, denoting total number of nodes in L. In the next n lines, ith line should contain an integer Li, denoting a value in ith node of L.
If n = 4 and L: 3 -> 7 -> 2 -> 9 -> NULL, then input should be:
4
3
7
2
9
Output Format:
There will be one line, containing an integer middle, denoting the result returned by the solution function.
For input n = 4 and L: 3 -> 7 -> 2 -> 9 -> NULL, output will be:
2


 */

public class FindTheMiddleNodeInSinglyLinkedList {

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
    static LinkedListNode find_middle_node(LinkedListNode head) {
        LinkedListNode fastPtr = head;
        LinkedListNode slowPtr = head;

        while(fastPtr!=null && fastPtr.next!=null){
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        return slowPtr;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        LinkedListNode res;
        int head_size = 0;

        LinkedListNode head = null;
        LinkedListNode head_tail = null;

        head_size = Integer.parseInt(in.nextLine());

        for(int i = 0; i < head_size; i++) {
            int head_item;
            head_item = Integer.parseInt(in.nextLine().trim());
            head_tail = _insert_node_into_singlylinkedlist(head, head_tail, head_item);

            if(i == 0) {
                head = head_tail;
            }
        }


        res = find_middle_node(head);
        while (res != null) {
            bw.write(String.valueOf(res.val));
            bw.newLine();
            res = res.next;
        }

        bw.close();
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
        if(head == null) {
            head = new LinkedListNode(val);
            tail = head;
        }
        else {
            tail.next = new LinkedListNode(val);
            tail = tail.next;
        }
        return tail;
    }

}
