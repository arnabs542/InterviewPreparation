package LinkedList;

import java.io.*;
import java.util.*;

/*

Input Format:
The first line of input should contain an integer n, denoting size of input linked list L. In next n lines, ith line should contain an integer Li, denoting value in ith node of L. In the next line, there should be an integer k, denoting the size of group as explained in problem statement section.
If n = 6, L: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> NULL and k = 3, then input should be:
6
1
2
3
4
5
6
3
Output Format:
There will be n lines, where ith line contains value of ith node of resL. Here, resL is the resultant linked list returned by the solution function.
For input n = 6, L: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> NULL and k = 3, output will be:
3
2
1
6
5
4


 */
public class ReverseALinkedListinGroupsOfK {

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
    static LinkedListNode reverse_linked_list_in_groups_of_k(LinkedListNode head, int k) {

        LinkedListNode startK, endK, prevOfStart = null;
        startK = head;
        endK = head;
        LinkedListNode startOfNextK = null;

        while(endK!=null){
            int count = 1;
            while(count < k && endK.next != null){
                endK = endK.next;
                count++;
            }

            if(endK!=null){
                startOfNextK = endK.next;
                endK.next = null;
            }

            reverseLinkedList(startK);
            if(prevOfStart == null){
                head = endK;
            }else{
                prevOfStart.next = endK;
            }
            prevOfStart = startK;
            startK = startOfNextK;
            endK = startOfNextK;
        }

        return head;
    }

    public static void reverseLinkedList(LinkedListNode curr){
        if(curr == null || curr.next == null) return;
        LinkedListNode prev = null, next = null;
        while(curr!= null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
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

        LinkedListNode res = reverse_linked_list_in_groups_of_k(head, k);

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


