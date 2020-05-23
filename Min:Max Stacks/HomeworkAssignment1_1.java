/******************************************************************************
 *                               Assignment#1, Problem 1
 *                                  DUE: 03/08/20
 * PURPOSE: Stack class designed to retrieve the max element in the stack 
 * in constant time (i.e., O(1).Supports push, pop, peek methods.
 * APPROACH: Max value assigned to global variable and updated upon extraction/insertion
 * of a new max value. Supplemental stack tracks maximum values pushed onto main stack at a 
 * given instance and enables push/pop in all cases. 
 *******************************************************************************/
 

import java.util.Stack;

//Test MaxStack Class and its methods
public class HomeworkAssignment1_1 {    
    public static void main(String[] args) {
       
       MaxStack obj = new MaxStack();
       
       
       obj.push(-2);
       obj.push(0);
       obj.push(-3);
       
       System.out.println(obj.getMax()); // return 0
       
       obj.pop();
       System.out.println(obj.top());// return 0
       System.out.println(obj.getMax()); // return 0

   }
}

/******************************************************************************
* The MaxStack program implements a Stack class with the following features:
*              push(x) -- push element x onto stack
*              pop() -- remove the element on top of the stack
*              top() -- get the top element.
*              getMax() -- retrieve the max element in the stack in constant time 
 *******************************************************************************/

class MaxStack {
    
    int maxInt; //Global max value in maxStack
    Stack<Integer> maxTracker; //History of max values
    Stack<Integer> maxStack;
  

   public MaxStack() {
       
        maxStack = new Stack<>(); 
        maxTracker = new Stack<>(); 
         
   }
/******************************************************************************
 * PURPOSE: push() method adds input parameter x to referenced stack
 * PARAMETER:
 *           x, integer with value in between Integer.MIN_VALUE and Integer.MAX_VALUE
 * RETURN VALUES:
 *           None. void method. 
 * SCENARIOS:
 *          (1) Push onto Empty Stack
 *          (2) Push out of bounds integer x
 *          (3) Push onto Non-Empty Stack
 *                  (a)  x => maxInt
 *                  (b)  x < maxInt
 *******************************************************************************/
   public void push(int x) {  
       
       // if stack is empty, assign global max value
       // to first pushed item
       if ( maxStack.empty() ){
           
           maxInt = x;
           maxTracker.push(x);
           maxStack.push(x);
       }
       else if(x > Integer.MAX_VALUE || x < Integer.MIN_VALUE){
           
           System.out.println("Stack Out of Bounds");
           
       }
       // otherwise, compare and maxInt with pushed item
       // assign accordingly
       else
       {    
           maxStack.push(x);
           
           if (x >= maxInt){
               maxTracker.push(x);
               maxInt = x;
           }        
           
       }

   }
   
/******************************************************************************
 * PURPOSE: pop() method extracts top element of the referenced stack
 * PARAMETER: 
 *              None.
 * RETURN VALUES: 
 *              None.
 * SCENARIOS:
 *          (1) Pop Empty Stack
 *          (2) Pop Non-Empty Stack
 *                  (a)  pop current maxInt
 *                  (b)  pop non-current maxInt
 *******************************************************************************/
    public void pop() {  
       
       // if stack is empty, assign global max value
       // to first pushed item
       if ( maxStack.empty() ){
           
           System.out.println("Empty Stack");

       }
       else
       {   
           if ( this.top() == maxInt ){
             
               maxTracker.pop();
               maxInt = maxTracker.peek();
               
           } 
           
           maxStack.pop();
       }  
      
   }
   
 /******************************************************************************
 * PURPOSE: top() -- retrieve top element in main stack.
 * PARAMETER: 
 *              None.
 * RETURN VALUES: 
 *              Top element in main stack. 
 *******************************************************************************/  
   
    public int top() { 
       
        int topInt = maxStack.peek();
        return topInt;
   }
   
 /******************************************************************************
 * PURPOSE: getMax() -- retrieve the max element in the stack in constant time (i.e., O(1))
 * PARAMETER: 
 *              None.
 * RETURN VALUES: 
 *              Current Max value in Stack 
 *******************************************************************************/  
    public int getMax() { 
       
        return maxInt;
    }
   


}
