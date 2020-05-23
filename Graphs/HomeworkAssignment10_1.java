/******************************************************************************
 *                                  Assignment#10, Problem 1
 *                                  DUE: 05/30/20
 *                                  By Pedro Alarcon
 * 
 * PURPOSE:  Given a linked list implementing a directed graph, we wish to know
 * whether the graph has a cycle ()
 * 
 * APPROACH :  Traverse the linked list, and keep an accounting of visited Nodes
 * using a HashSet. At each visit, we check whether node pointer address is contained
 * within the HashSet. If so, this means we are revisiting the same node, cycle exists.
 * If Null is reached, traversal is complete and no cycles were found. 
 *******************************************************************************/
import java.util.*;

public class HomeworkAssignment10_1 {
   public static void main(String[] args) {
   Solution sol = new Solution();
   Node a = new Node("a");
   Node b = new Node("b");
   Node c = new Node("c"); 
   // let's create a cycle with c -> b
   a.next = b;
   a.next.next = c;
   c.next = b;
   System.out.println(sol.hasCycle(a)); // true
} 
}
//our standard Node class
class Node {
   String label;
   Node next = null;
   Node(String label) {
      this.label = label;
   }
  
}


/******************************************************************************
 * PURPOSE: Given a linked list, we wish to know if graph contains cycles
 * PARAMETER:  Node pointer to arbitrary node in linked list
 * RETURN VALUES: true if cycle is found, false if otherwise
 *******************************************************************************/
 class Solution {
    
   public boolean hasCycle(Node head) {
       //collections framework to keep accounting of visits
      HashSet<Node> map = new HashSet<Node>();
      
      while(head != null){ //keep traversing until null
          if (map.contains(head)){ //do we recognize the current node pointer address?
              return true; //if so, cycle is found
          }
          map.add(head); //otherwise, add to our hashset and keep traversing
          head = head.next;
      }
      return false; //will reach this point only when traversal is done
   }
   
}
