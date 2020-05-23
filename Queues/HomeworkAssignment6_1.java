//
/******************************************************************************
*                               Assignment#6, Problem 1
*                                  DUE: 04/19/20
*                                  By Pedro Alarcon
* 
* PURPOSE: 
* To implement a Queue from scratch using two local stacks. A queue is a 
* data structure modeling a FIFO (First In, First Out) policy where elements are
* added from the rear and removed from the head
* 
* APPROACH :      popStack           pushStack
*               [ 1 | 2 | 3 ]           [ ]         //Starting Phase
*                   [ ]             [ 3 | 2 | 1 ]   1. push everything to pushStack
*                   [ 4 ]           [ 3 | 2 | 1 ]   2. push x into popStack
*             [ 1 | 2 | 3 | 4 ]          [ ]        3. push everything from pushStack 
*                                                   // back onto popStack             
*******************************************************************************/

import java.util.Stack;



public class HomeworkAssignment6_1 {    
   public static void main(String[] args) {
      
           // TEST CASE #1
           System.out.println("TEST CASE #1");
           Solution sol = new Solution();
           sol.add(8);
           sol.add(1);  
           System.out.println( sol.peek() );     // 8 (if you use System.out.println(), for example)
           sol.remove();   // 8
           System.out.println( sol.isEmpty() );  // false
           sol.remove();   // 1
           System.out.println( sol.isEmpty() );  // true
           sol.add(2);     
           sol.add(3); 
           System.out.println( sol.peek() );     // 2
           
           
           //TEST CASE #2
           System.out.println("\n\nTEST CASE #2");
           Solution test2 = new Solution();
           for (int i = 0; i < 4; i++){
               test2.add(i); // [ 0 | 1 | 2 | 3 ]
           }
           System.out.println("Is Queue Empty? :" + test2.isEmpty() );  // false
           for (int i = 0; i < 4; i++){
               System.out.print( test2.remove() + " " );  // [ 0 | 1 | 2 | 3 ]
           }
           System.out.println( "\nIs Queue Empty? :" + test2.isEmpty() );  // true
       
       
   }
}



class Solution {

  // YOUR STACK TO USE FOR THIS PROBLEM
  Stack<Integer> pushStack = new Stack<Integer>();
  Stack<Integer> popStack = new Stack<Integer>();

  /* ===================================== */
  /* !!! DO NOT MODIFY ABOVE THIS LINE!!! */
  /* ==================================== */

   /******************************************************************************
   * PURPOSE: Enqueue Operation - adds int x to the rear of a queue
   * PARAMETER: int x - integer to be added         
   * RETURN VALUES:  N/A   
   *******************************************************************************/
  public void add(int x) { 
      

      
      while( !popStack.empty() ){
          pushStack.push( popStack.pop() );   
      }
      
      popStack.push(x);

      while( !pushStack.empty() ){
          popStack.push( pushStack.pop() );
      }
      
      
      
  }
  
   /******************************************************************************
   * PURPOSE: Dequeue Operation - removes and returns int x, the front element in a queue. 
   * PARAMETER: N/A
   * RETURN VALUES:  int x, the front element in a queue            
   *******************************************************************************/
  public int remove() { 

      return popStack.pop();
       
      
  }

   /******************************************************************************
   * PURPOSE: read-only method that returns the element in front of a queue
   * PARAMETER: N/A
   * RETURN VALUES: integer at the front of the queue     
   *******************************************************************************/
  public int peek() { 
      
      return popStack.peek();

  }

  /******************************************************************************
  * PURPOSE: Checks whether a queue has at least one element
  * PARAMETER: N/A     
  * RETURN VALUES: Boolean - True if empty, false otherwise            
  *******************************************************************************/
  public boolean isEmpty() { 
      
      return popStack.empty();

  }
}