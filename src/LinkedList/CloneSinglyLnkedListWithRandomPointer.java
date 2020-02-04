package LinkedList;
import java.io.*;
import java.util.*;


/*
Input Format:
First and only parameter of the function cloneLinkedList, that is to be implemented is the head node pointer of the given LinkedList list.
5
3
-1
4
-1
-1
Output Format:
Return the head node of the newly cloned LinkedList in the cloneLinkedList function.
Then code written by us will traverse the returned linked list (starting from head node) and for each node, it will print one line containing three space separated integers 1) Data of the current node 2) If next node exists, then data of the next node, else -1 3) If random pointed node exists, then data of the random pointed node else -1.
1 2 3
2 3 -1
3 4 4
4 5 -1
5 -1 -1
 */

class Result {

    /*
     * Complete the 'cloneLinkedList' function below.
     *
     * The function accepts SinglyLinkedList list as parameter. Returns the cloned
     * linked list object
     */

    /*
     * For your reference:
     *
     * SinglyLinkedListNode { int data; SinglyLinkedListNode next;
     * SinglyLinkedListNode randomPointer; }
     *
     */

    public static SinglyLinkedListNode cloneLinkedList(SinglyLinkedListNode list) {
        // Write your code here
        // return null;

        SinglyLinkedListNode temp = list;

        while(temp!=null){
            SinglyLinkedListNode currClonedNode = new SinglyLinkedListNode(temp.data);
            SinglyLinkedListNode next = temp.next;
            temp.next = currClonedNode;
            currClonedNode.next = next;
            temp = temp.next.next;
        }

        temp = list;

        while(temp!=null){
            SinglyLinkedListNode currClonedNode = temp.next;
            SinglyLinkedListNode currNodeRandomPointer = temp.randomPointer;
            if(currNodeRandomPointer !=null ){
                currNodeRandomPointer = currNodeRandomPointer.next;
            }

            currClonedNode.randomPointer = currNodeRandomPointer;
            temp = temp.next.next;

        }

        temp = list;
        SinglyLinkedListNode clonedNodeStart = null;
        SinglyLinkedListNode clonedNodeEnd = null;

        while(list!=null){
            SinglyLinkedListNode currClonedNode = list.next;
            list.next = currClonedNode.next;
            if(clonedNodeStart == null){
                clonedNodeStart = currClonedNode;
                clonedNodeEnd = currClonedNode;
            }else{
                clonedNodeEnd.next = currClonedNode;
                clonedNodeEnd = clonedNodeEnd.next;
            }
            list = list.next;
        }


        return clonedNodeStart;
    }

}


class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;
    public SinglyLinkedListNode randomPointer;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
        this.randomPointer = null;
    }

    public void setRandomPointer(SinglyLinkedListNode randomNode) {
        this.randomPointer = randomNode;
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

class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep) throws Exception {
        String delimiter = " ";
        HashSet<SinglyLinkedListNode> visited = new HashSet<SinglyLinkedListNode>();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
            BufferedWriter errWriter = new BufferedWriter(new OutputStreamWriter(System.err));

            while (node != null) {
                visited.add(node);
                if (node.next != null && visited.contains(node.next) == true) {
                    errWriter.write("The next pointer links in the cloned list " + "are forming a loop");
                    errWriter.close();
                    return;
                }
                bufferedWriter.write(node.data + delimiter);

                if (node.next != null) {
                    bufferedWriter.write(node.next.data + delimiter);
                } else {
                    bufferedWriter.write(-1 + delimiter);
                }

                if (node.randomPointer != null) {
                    bufferedWriter.write(node.randomPointer.data + "");
                } else {
                    bufferedWriter.write(-1 + "");

                }
                node = node.next;

                if (node != null)
                    bufferedWriter.write(sep);
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class CloneSinglyLnkedListWithRandomPointer {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        SinglyLinkedList List = new SinglyLinkedList();
        HashMap<Integer, SinglyLinkedListNode> mapper = new HashMap<Integer, SinglyLinkedListNode>();
        HashMap<SinglyLinkedListNode, Boolean> originalNodes = new HashMap<SinglyLinkedListNode, Boolean>();

        int N = Integer.parseInt(bufferedReader.readLine().trim());
        mapper.put(-1, null);
        for (int i = 0; i < N; i++) {
            int element = i + 1;
            List.insertNode(element);
            mapper.put(element, List.tail);
            originalNodes.put(List.tail, true);
        }
        for (int i = 0; i < N; i++) {
            int element = Integer.parseInt(bufferedReader.readLine().trim());
            mapper.get(i + 1).setRandomPointer(mapper.get(element));
        }

        SinglyLinkedListNode clonedListHeadNode = Result.cloneLinkedList(List.head);
        BufferedWriter errWriter = new BufferedWriter(new OutputStreamWriter(System.err));

        if (cloneCheck(clonedListHeadNode, originalNodes) == false) {
            // assert (false) : "LinkedList not cloned";
            errWriter.write("Instead of creating new node, you have used node "
                    + "from the input linked list. Any node from the input linked list "
                    + "must not be used in the cloned linked list");
            errWriter.close();
        } else {
            SinglyLinkedListPrintHelper.printList(clonedListHeadNode, "\n");
        }

        bufferedReader.close();
    }

    public static boolean cloneCheck(SinglyLinkedListNode head, HashMap<SinglyLinkedListNode, Boolean> originalNodes) {

        SinglyLinkedListNode tmpNode = head;
        while (tmpNode != null) {
            if (originalNodes.containsKey(tmpNode) == true)
                return false;
            tmpNode = tmpNode.next;
        }
        return true;
    }
}
