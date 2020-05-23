
/******************************************************************************
 *                               Assignment#6, Problem 1
 *                                  DUE: 04/19/20
 *                                  By Pedro Alarcon
 * 
 * PURPOSE: Implement a floating front designed queue utilizing a fixed-size array. 
 * Supports the following methods: add(), remove(), peek(), isEmpty(). This design allows
 * the index front and rear of the queue to move around within an array of fixed size such that
 * elements reallocation is no longer needed. 
 * 
 * APPROACH :  We  keep track of the indexes of the front and rear of the queue through
 * variable assignment. Enqueue and Dequeue operations increment the indexes in a circular
 * manner. We assume all operations are valid such that error handling is not necessary 
 * ex. remove(), peek() are not invoked on an empty queue.
 * 
 * Cases of interest:
 *          1. Queue is initially empty
 *          2. Queue is stripped of all its elements until its empty
 *          3. Indexes reaches the end of an array
 *          4. Queue is non empty (general case)
 *  
          
 *******************************************************************************/

public class HomeworkAssignment6_2 {    
    public static void main(String[] args) {
    
        // TEST CASE #1: Create a queue with capacity = 1
        Solution sol = new Solution(1);
        System.out.println( sol.getFront() ); // -1 [ ]
        System.out.println(sol.getRear());  // -1 
        sol.add(8);  // [ 8 ]
        System.out.println( sol.getFront() ); // 0
        System.out.println(sol.getRear());  // 0 
        System.out.println( sol.peek() );     // 8 
        System.out.println( sol.remove() );   // 8 [ ]
        System.out.println( sol.isEmpty() );  // true

// TEST CASE #2: Create a queue with capacity = 3
        System.out.println("\n");
        Solution sol1 = new Solution(3);
        sol1.add(1);
        sol1.add(2);
        sol1.add(3); // [ 1 | 2 | 3 ]
        System.out.println( sol1.getFront() ); //index =  0
        System.out.println(sol1.getRear());  // index = 2 
        System.out.println( sol1.remove() );   //index =  1
        System.out.println( sol1.getFront() ); // index = 1
        System.out.println(sol1.getRear());  // index = 2 
        sol1.add(4); // [ 4 | 2 | 3 ]
        System.out.println( sol1.getFront() ); // index = 1
        System.out.println(sol1.getRear());  // index = 0 
        
        
        // TEST CASE #3: Create a queue with capacity = 3
        System.out.println("\n"); // we have // [ 4 | 2 | 3 ] //frontIndex = 1 RearIndex = 0
        System.out.println( sol1.peek() ); // 2
        sol1.remove();
        System.out.println( sol1.peek() ); //3
        System.out.println( sol1.getFront() ); //frontIndex = 2 RearIndex = 0
        System.out.println(sol1.getRear());  
        sol1.remove();
        System.out.println( sol1.peek() );//4
        System.out.println( sol1.getFront() ); //frontIndex = 0 RearIndex = 0
        System.out.println(sol1.getRear());  
        sol1.remove();  
        System.out.println( sol1.isEmpty() );
        System.out.println( sol1.getFront() );  //frontIndex = -1 RearIndex = -1
        System.out.println(sol1.getRear());  
    }
}


class Solution {

   // Dynamic array size
   private int capacity;
   // Queue
   private int[] elements;
   // Dynamic queue size
   private int numElements = 0;
   // Dynamic index for the front of queue, defaulting to -1
   private int front  = -1;
   // Dynamic index for the rear of queue, defaulting to -1
   private int rear = -1;

   // Constructor
   public Solution(int capacity) {
      this.capacity = capacity;
      this.elements = new int[this.capacity];
   }
   // Get the front index
   public int getFront() {
      return this.front;
   }
   // Get the rear index
   public int getRear() {
      return this.rear;
   }

   /* ===================================== */
   /* !!! DO NOT MODIFY ABOVE THIS LINE!!! */
   /* ==================================== */

   /******************************************************************************
   * PURPOSE: Enqueue Operation - adds int x to the rear of a queue
   * PARAMETER: int x - integer to be added         
   * RETURN VALUES:  N/A   
   *******************************************************************************/
   public void add(int x) { 
       
       if (getRear() == this.capacity - 1){ //if arrays's rear is fully occupied
           this.rear = 0; //we circle around to the beginning
       }
       else{ //otherwise, add element x to next available slot
           this.rear = getRear() + 1;  
           if( isEmpty() ){  //default index for empty queue is -1
                this.front++; 
           }
       }
      
       elements[getRear()] = x; //add element to the rear slot
       
       this.numElements++; 
       
   }
   
    /******************************************************************************
    * PURPOSE: Dequeue Operation - removes and returns int x, the front element in a queue. 
    * PARAMETER: N/A
    * RETURN VALUES:  int x, the front element in a queue            
    *******************************************************************************/
   public int remove() { 
       
       int popped = elements[ getFront() ]; //assign popped element to variable
       
       this.numElements--; //decrease element count

       if (getFront() == this.capacity - 1 && !isEmpty()){ 
  
               this.front = 0;

       }
       else if(isEmpty()){ //if we are removing the only element in queue
           this.front = -1; //assign default indexes
           this.rear = -1;
       }
       else{ //next sequential element in array becomes the head
           this.front = getFront() + 1; 
       }
       
       
       
       return popped; //returned removed item

   }

       /******************************************************************************
    * PURPOSE: read-only method that returns the element in front of a queue
    * PARAMETER: N/A
    * RETURN VALUES: integer at the front of the queue     
    *******************************************************************************/
   public int peek() { 
      return elements[ getFront() ];
   }
   
   
      /******************************************************************************
   * PURPOSE: Checks whether a queue has at least one element
   * PARAMETER: N/A     
   * RETURN VALUES: Boolean - True if empty, false otherwise            
   *******************************************************************************/
   public boolean isEmpty() { 
     boolean empty = (this.numElements == 0); //true if empty
     return empty;
   }
}