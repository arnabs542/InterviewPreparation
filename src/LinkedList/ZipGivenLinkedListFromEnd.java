package LinkedList;

import java.io.*;
import java.util.*;

/*
Input Format:
The first line of input should contain an integer n, denoting size of input linked list L. In next n lines, ith line should contain an integer Li, denoting value in ith node of L.
If n = 6 and L: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> NULL, then input should be:
6
1
2
3
4
5
6
Output Format:
There will be n lines, where ith line contains value of ith node of resL. Here, resL is the resultant linked list returned by the solution function.
For input n = 6 and L: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> NULL, output will be:
1
6
2
5
3
4


*  */
public class ZipGivenLinkedListFromEnd {


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
    static LinkedListNode zip_given_linked_list(LinkedListNode head) {
        if(head == null)
            return null;
        LinkedListNode fast = head.next, slow = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }

        LinkedListNode first  = head;
        LinkedListNode second = slow.next;
        LinkedListNode next = null;
        slow.next = null;

        second = reverseLinkedList(second);

        LinkedListNode next1;
        LinkedListNode next2;

        while (second!=null)
        {
            next1 = first.next;
            next2 = second.next;
            first.next = second;
            second.next = next1;
            first = next1;
            second = next2;
        }
        // if(first!=null)
        return head;

    }

    public static LinkedListNode reverseLinkedList(LinkedListNode curr){
        LinkedListNode prev = null;
        LinkedListNode next = null;
        while(curr!=null){
            next = curr.next;

            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev ;
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

        LinkedListNode res = zip_given_linked_list(head);

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

