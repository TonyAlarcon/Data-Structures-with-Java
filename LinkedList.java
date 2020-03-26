/******************************************************************************
 *                               Assignment#3, Problem 1
 *                                  DUE: 03/28/20
 * By: Pedro Antonio Alarcon
 * 
 * PURPOSE: Consider a linked list L, with N Nodes representing subatomic particles in
 * distinct states of matter. Namely, for a given mixture of N homogeneous particles, there
 * are k gas particles and (N - k) liquid particles ordered sequentially via a singly linked list.
 *              L = L1 -> L2 -> L3 -> .... -> Ln-1 -> Ln 
 * We wish to transform the above pattern such that the particle mixture becomes homogeneous. Or
 * equivalently: 
 *              L = L1 -> Ln -> L2 -> Ln-1 -> L3 -> Ln-2 -> L4 -> .....
 * APPROACH: 
 *  Step 1: We begin by splitting the given list, L into two segments L1, L2 such that
 * 
 *            L1 = L1 -> L2 -> ... -> Lk-1 -> Lk  , L2 = Lk+1 -> Lk+2 -> ... -> Ln-1 -> Ln
 * 
 * where | k - (N - k) | = 1 if N is odd and 0 if  N is even. In other words, 
 * the node count difference of the lists is 0 or 1. Therefore, k >= N - k
 * 
 *  Step 2: Reverse Node order in list L2, such that L2 = Ln -> Ln-1 -> ... -> Lk+1
 * 
 *  Step 3: Merge L1 and L2
 *******************************************************************************/

public class HomeworkAssignment3_1 {    
    public static void main(String[] args) {
       // just like any problems, whatever you need here, etc. For example:
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(4);
        node.next.next.next.next = null;
        Solution sol = new Solution();
        
        

        sol.mixList(node); // list be homogeneous after this call

        
        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(3);
        node2.next.next.next = new Node(4);
        node2.next.next.next.next = new Node(5);
        node2.next.next.next.next.next = null;
        

        sol.mixList(node2); // list be homogeneous after this call

        
                
        Node node3 = new Node(1);
        node3.next = null;
        sol.mixList(node3); // list be homogeneous after this call
        
        Node node4 = new Node(1);
        node4.next = new Node(2);
        node4.next.next = null;
        sol.mixList(node4); // list be homogeneous after this call
    }
}
class Node {
    int val;
    Node next;
    Node(int x) { val = x; }
}

class Solution {
    
   /******************************************************************************
   * PURPOSE: printList(Node head) -- print all node values 
   * PARAMETER: 
   *             head - Inputs a Node Pointer to the head of a linked list
   *            
   * RETURN VALUES: 
   *              No Returns. Sequentially prints node values to console.  
   *******************************************************************************/ 
   public void printList(Node head) { 
       
       Node temp = head; //auxilliary node
       
       do{
           if(temp == null) {break;} //checks if linked list is empty, if so break
           
           System.out.print( temp.val + "->"); //print current node val
           temp = temp.next; //traverse to next node
           
       }while(temp != null); //terminates when next node is null
       
       System.out.println( "NULL");
   }
   
   /******************************************************************************
   * PURPOSE: getNodeCount(Node head) -- Counts number of nodes in linked list 
   * PARAMETER: 
   *             head - Inputs a Node Pointer to the head of a linked list
   *            
   * RETURN VALUES: 
   *              integer whose value is the number of nodes in a linked list  
   *******************************************************************************/ 
   public int getNodeCount(Node head){
       
       Node temp = head;
       int count = 0;
       
       while(temp != null){
           count++;
           temp = temp.next;   
       }
       
       return count;
   }
   
   /******************************************************************************
   * PURPOSE: isEven(Node head) -- Checks if given list has an even or odd number of nodes
   * PARAMETER: 
   *             head - Inputs a Node Pointer to the head of a linked list
   *            
   * RETURN VALUES: 
   *              Boolean - True if even, False if Odd 
   *******************************************************************************/ 
      public boolean isEven(Node head){
       
       int count = getNodeCount(head);
       boolean even = (count % 2 == 0); 
       
       return even;
   }
   

   /******************************************************************************
   * PURPOSE: getMiddleNode(Node head) -- Obtains Middle Node of a Linked List which 
   * defines the point where to split the list.
   * PARAMETER: 
   *             Node head: head pointer of the non-homogeneous list
   *            
   * RETURN VALUES: 
   *              Node l1Tail - last node of L1
   *******************************************************************************/ 
   public Node getMiddleNode(Node head){
       
       //if N is even, nodes are split evenly, otherwise L1 has 1 more node than L2
       int nodeCount = getNodeCount(head);
       //index to traverse until last node in L1
       int tailIndex = (isEven(head)) ? (nodeCount/2) : (nodeCount/2 + 1); 
 
       Node l1_Tail = head;
       //Traverse
       for(int i = 1; i < tailIndex; i++){
           l1_Tail  = l1_Tail.next;
       }
       
    return l1_Tail ;
   }
   
   
   /******************************************************************************
   * PURPOSE: reverseList(Node head) -- reverses the node order of a singly linked list
   * PARAMETER: 
   *             Node head - Inputs a Node Pointer to the head of a linked list
   *            
   * RETURN VALUES: 
   *              Head Node of the reversed list
   *******************************************************************************/ 
   public Node reverseList(Node head){
       
    
       Node curr = head;
       Node prev = null;
       Node next = null;
       
       while(curr != null){
           
           next = curr.next; //important to store next node where flip occurs
           curr.next = prev; //This reverses pointer order 
           prev = curr; //proceeds to next pointer  
           curr = next;
       }
       
       head = prev;//tail becomes new head
       
       return head;
       
   }
   /******************************************************************************
   * PURPOSE: mergeList(Node head1, Node head2) -- merges two singly linked list
   * PARAMETER: 
   *             Node head1 - Node Pointer to the head of the 1st linked list
   *             Node head2 - Node Pointer to the head of the 2nd linked list
   * RETURN VALUES: 
   *              No returned values
   *******************************************************************************/   
   public void mergeList(Node head1, Node head2){ //note head1 is head node of largest (or equal) list
       
       while(head2 != null){
           Node next = head1.next; //next = L2
           head1.next = head2; // L2 = Ln
           head1 = head2;  // L1 = Ln
           head2 = next; // Ln = L2
       }
  
   } 
   

   /******************************************************************************
   * PURPOSE: mixList(Node head) - utilizes all user-defined methods to rearrange 
   * nodes of a list in a homogeneous matter
   * PARAMETER: 
   *           Node head - Inputs a Node Pointer to the head of a linked list we wish to transform
   *            
   * RETURN VALUES: 
   *             No values are returned. 
   *******************************************************************************/   
    public void mixList(Node head) { 
        
        System.out.print("Original Non-Homogeneous List: ");
        printList(head);
        
        Node l1_Tail = getMiddleNode(head);
        Node l2_Head = l1_Tail.next;

        
        //Split list, reverse order of latter list
        l1_Tail.next = null;
        l2_Head = reverseList(l2_Head);
        
        System.out.println("Split List Into Two: ");
        System.out.print("L1: ");
        printList(l2_Head);
        System.out.print("L2: ");
        printList(head);
        
        
        mergeList(head, l2_Head);
        System.out.print("Homogeneous List: ");
        printList(head);
        System.out.print("\n");
        
  
        
    }
}