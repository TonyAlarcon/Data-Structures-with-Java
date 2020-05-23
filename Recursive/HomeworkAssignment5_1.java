/******************************************************************************
 *                               Assignment#5, Problem 1
 *                                  DUE: 04/04/20
 *                                  By Pedro Alarcon
 * 
 * PURPOSE: Recursively reversed direction of linked list
 * 
 * APPROACH: 
 *  1. recursively traverses down list until last node is found, save this Node
 * in var tail. 
 *  2. curr will become second to last Node in first iteration. 
 *  3. Chain next of curr.next to curr (creates a reversal link)
 *  4. Unlink original chain by setting next of curr to null
 *  5. This continues until all links are reversed, reassign head to tail
 *******************************************************************************/


public class HomeworkAssignment5_1 {    
    public static void main(String[] args) {
       Solution sol = new Solution();
       
       Node node = new Node(1);
       node.next = new Node(2);
       node.next.next = new Node(3);
       node.next.next.next = new Node(4);
       node.next.next.next.next = null;
       

       sol.reverseList(node);



    }
}
class Node {
    int val;
    Node next;
    Node(int x) { val = x; }
}
class Solution {
    
    /******************************************************************************
    * PURPOSE: To invoke recursive function and print results
    * PARAMETER: Node Head -> head pointer
    *           
    * RETURN VALUES:  boolean, also prints values of nodes
    *             
    *******************************************************************************/
   public boolean reverseList(Node head) { 
       
       head = helper(head);//reassigns head pointer
       
       Node temp = head;
       while(temp != null){
           System.out.print(temp.val + "->");
           temp = temp.next;
       }
       System.out.print("NULL");
       return true;
             

   }
   /******************************************************************************
   * PURPOSE: Recursively traverses linked list and reverses node direction
   * PARAMETER: Node curr - pointer that is to be appended to end of reversed list
   * RETURN VALUES: 
   *             Node tail - Node at the beginning of reversed linked list
   *******************************************************************************/
   public Node helper(Node curr){
       
       //empty list scenario, BASE CASE 
        if(curr == null) return curr; 
       //activation record for curr [ 4 | 3 | 2 | 1 ]
        if (curr.next == null) return curr; //end of list scenario
        
        Node tail = helper(curr.next); //traverses down list until last node is found

        // 1 -> 2 -> 3 -> 4 -> 5 -> NULL, temp = 5 , head = 4, 
        curr.next.next = curr;   // create chain: 5 -> 4 thus 1 -> 2 -> 3 -> 4 <-> 5 
        curr.next = null; // breaks chain: 4 -> 5, so that 1 -> 2 -> 3 -> 4 <- 5
        
       //returns pointer to last node in original list, which is first node in reversed list
       return tail; 
   }
   
 
}