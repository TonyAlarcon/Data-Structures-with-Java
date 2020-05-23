

/******************************************************************************
 *                                  Assignment#7, 
 *                                  DUE: 05/02/20
 *                                  By Pedro Alarcon
 * 
 * PURPOSE: Custom built iterator interface for ArrayList collections. ArrayList are
 * dynamic arrays that implements List interfaces and whose elements are objects. 
 * APPROACH :  
 * 
 *          
 *******************************************************************************/

import java.util.*;
public class HomeworkAssignment7 {    
    public static void main(String[] args) {
      
       List<Integer> ints = new ArrayList<>(Arrays.asList(1, 2, 3));
       MyIterator iter = new MyIterator(ints.iterator());
       System.out.println(iter.next());         // 1
       System.out.println(iter.lookAhead());    // 2
       System.out.println(iter.next());         // 2
       System.out.println(iter.next());         // 3
       System.out.println(iter.hasNext());      // false
       System.out.println(iter.next());         // null
       System.out.println(iter.lookAhead());    // null
       
    }
}



 /******************************************************************************
 * PURPOSE: 
 * PARAMETER:         
 * RETURN VALUES: 
 *******************************************************************************/
class MyIterator implements Iterator<Integer> {

List<Integer> list = new ArrayList<>();
Iterator<Integer> itr;
int index = -1;

    
    
    
 /******************************************************************************
 * PURPOSE: 
 * PARAMETER:         
 * RETURN VALUES: 
 *******************************************************************************/
   public MyIterator(Iterator<Integer> iterator) {
      this.itr = iterator;
      Integer x;
      while(iterator.hasNext()){
          x = iterator.next();
          list.add(x);
      }
      
  
   }
   
   
 /******************************************************************************
 * PURPOSE: Returns the next element in the iteration WITHOUT advancing the iterator.
 * PARAMETER:       N/A  
 * RETURN VALUES:  
 *                Integer - the next element in the iterator
 *                Null - if at the end of list
 *******************************************************************************/
   public Integer lookAhead() {
       
       if( index < list.size() - 1 ){

            return list.get(index + 1);
       }
       return null;
       
   }
   
   
 /******************************************************************************
 * PURPOSE: Advances iterator to the next element and returns it. 
 * PARAMETER:        N/A 
 * RETURN VALUES: 
 *******************************************************************************/
   @Override
   public Integer next() {
       if( index < list.size() - 1 ){
            index++;
            return list.get(index);
       }
       return null;

   }
   
   
 /******************************************************************************
 * PURPOSE: 
 * PARAMETER:         
 * RETURN VALUES: 
 *******************************************************************************/
   @Override
   public boolean hasNext() {
       
       return (index > list.size() - 1);

 
   }
}