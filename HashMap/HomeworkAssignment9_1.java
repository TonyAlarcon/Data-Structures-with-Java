
/******************************************************************************
 *                                  Assignment#9, 
 *                                  DUE: 05/02/20
 *                                  By Pedro Alarcon
 * 
 * PURPOSE:  To develop a custom HashMap implementation with put(key, value),
 * get(key), remove(key) methods
 * 
 * APPROACH :  This custom HashMap implementations utilized a fixed-sized array
 * to stores the (key,value) pairs. A hash function using modular hashing techniques
 * determines the index of each entry based on input key. The size of array is
 * deliberately chosen to be a prime integer to ensure uniform distribution. 
 * A custom linked list is utilized to handle collisions (2 different key inputs
 * hashes to same index). 
 * 
 * Complexity Analyzes: Though we are given that a maximum entry input of 10,000,
 * the frequency and definite entry amount is unknown. Therefore, this model
 * sacrifices a bit of space  memory in order to maintain a constant O(1) for 
 * the referenced methods. 
 *******************************************************************************/

public class HomeworkAssignment9_1 {
   public static void main(String[] args) {
       
   //Testing Constructors 
   Solution sol = new Solution();
//   System.out.println( sol.get(0) );
//   for(int i = 0; i < 19; i++){
//       System.out.println(sol.get(i));
//   }
   
   sol.put(1, 1); 
   sol.put(2, 3); 
   System.out.println( sol.get(1) );       // returns 1 
   System.out.println( sol.get(30) );      // returns -1 (not found) 
   sol.put(1, 100);  // update the existing value 
   System.out.println( sol.get(1) );       // returns 100 
   sol.remove(2);    // removes the mapping for 2
   sol.remove(1);    // removes the mapping for 1 
   System.out.println( sol.get(2) );       // returns -1 (not found)
   System.out.println( sol.get(1) );       // returns -1 (not found) 

   } 
}
   
   
class Solution {
    
    //Using a prime number for bucket size ensures more uniform distribution of entries
    int DEFAULT_CAPACITY = 19; //bucket size
    double DEFAULT_LOAD = .75; //default load
    
    EntryNode[] buckets; //array of custom linked list
    int EntryNum;


   public Solution() { 
       
       this.buckets = new EntryNode[DEFAULT_CAPACITY]; //initialize
       
       //fill buckets with null
       for(int i = 0; i < DEFAULT_CAPACITY; i++){
           buckets[i] = null;
       }
    
   }
   
 /******************************************************************************
 * PURPOSE: Inserts a (Key, Value) pair into the hashed map. If entry with Key k
 * already exits, update the value.
 * PARAMETER:    Accepts Key and Non-Negative Value as parameters. 
 * RETURN VALUES: N/A
 *******************************************************************************/
   // Value is always non-negative
    public void put(int key, int value){
   
       
       EntryNode entry = new EntryNode(key, value); //initialize node with input values
       int index = getIndex(key); //Obtain Bucket Index
       //System.out.println(index); //uncomment for debugging purposes
       EntryNode head = buckets[index]; //reference node to head element in bucket
       
       //Case 1: If no element is stored in bucket
       if(head == null){
          buckets[index] = entry;
          entry.next = null;
          EntryNum++;
       }// Case 2: If only one element is stored in the bucket
       else if (head.next == null){
        // if any element within list matches key, replace value, exit method
            if(head.getKey() == key){
            head.setValue(value);
        }
       }//Case 3: If there are multiple (+1) elements stored in bucket
       else{ //this portion is a bit redundant but I had limited time //future project
           while(head.next != null){
            if(head.getKey() == key){
                head.setValue(value);
            }
               head = head.next;
           }
           head.next = entry; //insert at the end
           
       }
    

        
   }

       
   
 /******************************************************************************
 * PURPOSE: To retrieve the value mapped to the input key.read-only
 * PARAMETER:    Accepts Key k   
 * RETURN VALUES: Returns Value V associated with input Key k; if not found, return -1
 *******************************************************************************/
   public int get(int key) { 
       
       EntryNode head = buckets[ getIndex(key) ]; //initialize pointer to head of list
       int mappedValue = -1; //default value for empty bucket
       
       if (head == null){
           //do nothin, output will remain at -1
       }
       else{//traverse the linked list in the bucket
           while(head != null){
               if(head.getKey() == key){ //if match is found, store value
                   mappedValue = head.getValue(); 
               }
               head = head.next;
           }
       }
       return mappedValue;
  
   } 
   
  /******************************************************************************
 * PURPOSE: To remove from the hash map the associated value that is mapped to the input key. 
 * PARAMETER:    Key K   
 * RETURN VALUES: N/A
 *******************************************************************************/
   public void remove(int key) { 
      int index = getIndex(key); //computer bukcet index
      EntryNode head =  buckets[ index ]; //pointer to head
      EntryNode removedNode; //node to delete/remove
      
      //if bucket is empty
      if(head == null){
          return;
      }
      //if bucket only has one entry
      if(head.next == null){
          buckets[index] = null;
          return;
      }
      //if bucket has multiple entries
      while(head != null){
          //if removing a node not at the end
          if(head.getKey() == key && head.next != null){
              removedNode = head.next; //assigns to target node
              head.next = head.next.next; //reconnects pointers
              removedNode = null; //deletes
          }
      }
   } 
   
  /******************************************************************************
 * PURPOSE: Uses Modular Hashing to ascertain bucket index given a key input. 
 * PARAMETER:    int Key 
 * RETURN VALUES: Bucket Index
 *******************************************************************************/   
   public int getIndex(int key){
       int index = Math.abs(key % DEFAULT_CAPACITY ); // | key % Map Size |
       return index;
   }
   

}

  /******************************************************************************
 * PURPOSE: Custom Linked List Class to store pair entries whose keys map to the same nucket index

 *******************************************************************************/   
class EntryNode {
    
    int key;
    int value;
    EntryNode next;
    
    //initialize node
    EntryNode (int key, int value){   
        this.key = key;
        this.value = value;
        this.next = null;
    }
    
    //getters
    public int getKey(){ return key;  }
    public int getValue(){ return value; }
    public void setValue(int value) { this.value = value; } //setter
    
}

