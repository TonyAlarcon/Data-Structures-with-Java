/******************************************************************************
 *                              Assignment#1, Problem 2
 *                                  DUE: 03/08/20
 * PURPOSE: Stack class designed to retrieve the min element in the stack 
 * in constant time (i.e., O(1).Supports push, pop, peek methods.
 * APPROACH: Min value assigned to global variable and updated upon extraction/insertion
 * of a new max value. Supplemental stack tracks minimum values pushed onto main stack at a 
 * given instance and enables push/pop in all cases. 
 *******************************************************************************/
 
import java.util.Stack;

// Test MinStack and it's methods
public class HomeworkAssignment1_2 {    
    public static void main(String[] args) {
       
       MinStack obj = new MinStack();
       

       obj.push(-2);
       obj.push(0);
       obj.push(-3);
       
       System.out.println(obj.getMin()); // return 0
       
       obj.pop();
       System.out.println(obj.top());// return 0
       System.out.println(obj.getMin()); // return 0

   }
}

/******************************************************************************
* The MinStack program implements a Stack class with the following features:
*              push(x) -- push element x onto stack
*              pop() -- remove the element on top of the stack
*              top() -- get the top element.
*              getMin() -- retrieve the min element in the stack in constant time 
 *******************************************************************************/
class MinStack {
    
    int minInt; //Global min value in maxStack
    Stack<Integer> minTracker; //History of min values
    Stack<Integer> minStack;
  

   public MinStack() {
       
        minStack = new Stack<>(); 
        minTracker = new Stack<>(); 
         
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
 *                  (a)  x => minInt
 *                  (b)  x < minInt
 *******************************************************************************/
   public void push(int x) {  
       
       // if stack is empty, assign global max value
       // to first pushed item
       if ( minStack.empty() ){
           
           minInt = x;
           minTracker.push(x);
           minStack.push(x);
       }       
       else if(x > Integer.MAX_VALUE || x < Integer.MIN_VALUE){
           
           System.out.println("Stack Out of Bounds");
           
       }
       
       // otherwise, compare and maxInt with pushed item
       // assign accordingly
       else
       {    
           minStack.push(x);
           
           if (x <= minInt){
               minTracker.push(x);
               minInt = x;
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
 *                  (a)  pop current minInt
 *                  (b)  pop non-current minInt
 *******************************************************************************/
    public void pop() {  
       
       // if stack is empty, assign global min value
       // to first pushed element
       if ( minStack.empty() ){
           
           System.out.println("Empty Stack");

       }
       else
       {    
           
           if ( this.top() == minInt ){
             
               minTracker.pop();
               minInt = minTracker.peek();
               
           }  
           
           minStack.pop();
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
       
        int topInt = minStack.peek();
        return topInt;
   }
   
 /******************************************************************************
 * PURPOSE: getMin() -- retrieve the min element in the stack in constant time (i.e., O(1))
 * PARAMETER: 
 *              None.
 * RETURN VALUES: 
 *              Current Min value in Stack 
 *******************************************************************************/  
    public int getMin() { 
       
        return minInt;
    }



}
