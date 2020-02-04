package LinkedList;

import java.io.*;
import java.util.stream.*;

/*
Input Format: First line contains integer n, the number of integers. The next n lines contains an integer each.
If linked list is 1->2->4->3 then input should be:
4
1
2
4
3
Output Format:
The function should return the head of the sorted linked list. The inbuilt print function will print each integer of the linked list starting from head in a new line.
For above input, output will be
1
2
3
4
 */

public class MergeASortedLinkedList {


    public static SinglyLinkedListNode merge_sort(SinglyLinkedListNode head) {
        // Write your code here

        if(head == null || head.next == null){
            return head;
        }

        SinglyLinkedListNode middle = split(head);
        SinglyLinkedListNode afterMiddle = middle.next;

        middle.next = null;

        SinglyLinkedListNode left =  merge_sort(head);
        SinglyLinkedListNode right = merge_sort(afterMiddle);

        SinglyLinkedListNode sortedList = merge(left, right);

        return sortedList;
    }

    public static SinglyLinkedListNode merge(SinglyLinkedListNode left, SinglyLinkedListNode right){
        SinglyLinkedListNode result = null;
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }

        if(left.data <= right.data){
            result = left;
            result.next = merge(left.next, right);
        }else{
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }

    public static SinglyLinkedListNode split(SinglyLinkedListNode node){
        if(node == null || node.next == null){
            return node;
        }
        SinglyLinkedListNode fast = node.next;
        SinglyLinkedListNode slow = node;

        while(fast.next!=null){
            fast = fast.next;
//            if(fast!=null){
                slow = slow.next;
                fast = fast.next;
//            }
        }

        return slow;
    }

    class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    static class SinglyLinkedListPrintHelper {
        public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
            while (node != null) {
                bufferedWriter.write(String.valueOf(node.data));

                node = node.next;

                if (node != null) {
                    bufferedWriter.write(sep);
                }
            }
        }
    }

    public static void main(String args[]) {
        /*
        This function is used to increase the size of recursion stack. It makes the size of stack
        2^26 ~= 10^8
        */
        MergeASortedLinkedList mergeASortedLinkedList = new MergeASortedLinkedList();
        new Thread(null, new Runnable() {
            public void run() {
                try{
                    mergeASortedLinkedList.solve();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, "1", 1 << 26).start();
    }

    public void solve() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        SinglyLinkedList head = new SinglyLinkedList();

        int headCount = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, headCount).forEach(i -> {
            try {
                int headItem = Integer.parseInt(bufferedReader.readLine().trim());

                head.insertNode(headItem);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        SinglyLinkedListNode result = merge_sort(head.head);

        SinglyLinkedListPrintHelper.printList(result, "\n", bufferedWriter);
        bufferedWriter.newLine();

        bufferedWriter.close();

        bufferedReader.close();
    }


}
