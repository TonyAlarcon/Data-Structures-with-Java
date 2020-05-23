/******************************************************************************
 *                               Assignment#2, Problem 1
 *                                  DUE: 03/14/20
 * By: Pedro Antonio Alarcon
 * PURPOSE: Check whether a given sequence has a valid order of operations
 * APPROACH: Use Auxillary Stack to simulate events, pushing element and checking
 * if that current element is on top of pop_events stack. Count number of pop at the end
 * if they are the same as length of events_pushed, return true
 *******************************************************************************/


import java.util.Stack;



public class HomeworkAssignment2_1 {    
    public static void main(String[] args) {
        
       Solution test = new Solution();
        
      // Example #1 : False
       int[] a = {2,1,3};
       int[] b = {3,2,1};
       System.out.println( test.isSameEventSequence(a,b) ? "True" : "False" );

       // Example #2: True
       int[] c = {1,2,3};
       int[] d = {1,2,3};
       System.out.println( test.isSameEventSequence(c,d) ? "True" : "False" );
    
       
       // Example #3: True
       int[] e = {1};
       int[] f = {1};
       System.out.println( test.isSameEventSequence(e,f) ? "True" : "False" );
    }
}
    
    
    
/******************************************************************************
* The Solution class
* 
*              Push the first element from pushed events to auxilliary stack, called events
*              Checks The Following Criteria:
*                       Does Event[] Have an Element?
*                       Is value of popped items less than number of pushed events?
*                       Is Event[0] == Popped[0]?
*              While all criterias are met, pop Event[0], increment poppedIndex
*              Repeat for all elements in pushed stack
*       
 *******************************************************************************/
class Solution {

       
   /******************************************************************************
   * PURPOSE: isSameEventSequence() -- Verifies whether events sequences are valid or not
   * PARAMETER: 
   *             events_pushed - Array of integers representing pushed events
   *             events_popped - Array of integers representing pop events
   * RETURN VALUES: 
   *              Boolean: True/False
   *******************************************************************************/ 
   public boolean isSameEventSequence(int[] events_pushed, int[] events_popped) { 
       //Check Criterias
       boolean isSameLength = (events_pushed.length == events_popped.length);
       boolean isNotWithinBounds = (events_pushed.length > 100 || events_popped.length > 100);

       
        if (!isSameLength || isNotWithinBounds ){
           return false;
       }
       
       for(int a: events_pushed){
           if(a <= 0 || a > 100){ return false; }
       }
       for(int b: events_popped){
           if(b <= 0 || b > 100){ return false; }
       }
       
 
       
       
       Stack<Integer> events = new Stack();
       
       int poppedIndex = 0;
       
       for(int x: events_pushed){
           
           events.push(x);
           
           while(!events.isEmpty() && poppedIndex < events_pushed.length && events.peek() == events_popped[poppedIndex]){
               
               events.pop();
               poppedIndex++;
               
           }
        }
       
       return (events_pushed.length == poppedIndex);
       
   }
}



