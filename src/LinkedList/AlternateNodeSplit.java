package LinkedList;
import java.io.*;
import java.util.*;

/*
Input Format:
line number 1: n denoting the number of nodes of the linked list
line number 2: n space separated integers denoting the values of the nodes. Please make sure there are no leading or trailing spaces and between two integers there must be a single space.

 Raw input of sample:
5
1 2 3 4 5
Output Format:
Two lines will be printed.line 1: Space separated integer denoting the values of first linked list
line 2: Space separated integer denoting the values of second linked list
Raw output of sample:
1 3 5
2 4
 */

public class AlternateNodeSplit {
    /*
    Complete the function below
*/
    static LinkedListNode[] alternativeSplit(LinkedListNode head){
        LinkedListNode evenHead = null;
        LinkedListNode evenTail = null;
        LinkedListNode oddHead = null;
        LinkedListNode oddTail = null;
        int evenIndex = 1;
        while(head!=null){
            LinkedListNode curr = new LinkedListNode(head.val);
            if(evenIndex == 1){
                if(evenHead == null){
                    evenHead = curr;
                    evenTail = curr;
                }else{
                    evenTail.next = curr;
                    evenTail = evenTail.next;
                }
            }else{
                if(oddHead == null){
                    oddHead = curr;
                    oddTail = curr;
                }else{
                    oddTail.next = curr;
                    oddTail = oddTail.next;
                }
            }
            head = head.next;
            evenIndex = evenIndex^1;
        }

        LinkedListNode[] results = new LinkedListNode[]{evenHead, oddHead};
        return results;

    }


    static class LinkedListNode{
        public int val;
        public LinkedListNode next;
        public LinkedListNode(int val){
            this.val = val;
            this.next = null;
        }
    }

    static LinkedListNode createLinkedList(int[] inputArray){
        LinkedListNode head = null;
        LinkedListNode tail = null;

        for(int i = 0; i < inputArray.length; i++){
            LinkedListNode currentNode = new LinkedListNode(inputArray[i]);
            if(head == null){
                head = currentNode;
                tail = head;
            }
            else{
                tail.next = currentNode;
                tail = tail.next;
            }
        }
        return head;
    }

    static LinkedListNode readInput(){
        Scanner scan = new Scanner(System.in);
        int numberOfNodes = scan.nextInt();
        int[] nodeValues = new int[numberOfNodes];
        for(int i=0;i<numberOfNodes;i++){
            nodeValues[i] = scan.nextInt();
        }
        return createLinkedList(nodeValues);
    }

    static void printList(LinkedListNode head){
        int id = 0;
        while(head != null){
            if(id > 0){
                System.out.print(" ");
            }
            System.out.print(head.val);
            head = head.next;
            id++;
        }
        System.out.println();
    }

    static int getArraySize(LinkedListNode[] array){
        if(array == null){
            return 0;
        }
        return array.length;
    }

    public static void main(String[] args){
        try{
            LinkedListNode head = readInput();
            LinkedListNode[] result = alternativeSplit(head);
            int outputArraySize = getArraySize(result);
            if(outputArraySize != 2){
                throw new Exception("Output must be an array with 2 LinkedListNode");
            }
            printList(result[0]);
            printList(result[1]);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}


