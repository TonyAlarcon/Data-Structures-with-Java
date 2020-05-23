/******************************************************************************
 *                                  Assignment#9, Problem 2
 *                                  DUE: 05/02/20
 *                                  By Pedro Alarcon
 * 
 * PURPOSE:  Implements a HashMap that has functionality to return the sum
 * of all values stored in the Hmap such that their associated key starts with 
 * an inputted prefix. 
 * 
 * APPROACH :  This model utilized a brute force method. We initialize a HashMap
 * collection framework, each insertion operation is contant in time O(1). 
 * The sum method must parse each pair element N, and compare each key value
 * to an array of characters, or a string, S. Therefore, this operations 
 * is linear in time O(N * S)
 *******************************************************************************/

import java.util.*;


public class HomeworkAssignment9_2 {
   public static void main(String[] args) {
   Solution sol = new Solution();
   
   sol.put("app", 100); 
   System.out.println( sol.getSum("ap") );     // returns 100 as "ap" is a prefix  to "app"
   sol.put("ap", 1);
   System.out.println( sol.getSum("a") );      // returns 101 as "a" is a prefix to both keys in map
   System.out.println( sol.getSum("xyz") );    // returns 0 as "xyz" is not a prefix to any keys in map 
   sol.put("app", 8);    // replaces the value of the existing key "app" from 100 to 8
   System.out.println( sol.getSum("ap") );     // returns 9 as "ap" is a prefix to both keys in map 
    } 
}


class Solution {
   
   HashMap<String, Integer> Hmap;
   
    
   public Solution() { 
      Hmap = new HashMap<>();
   } 
   // Inserts a (key, value) pair into the map. If the input value already exists, update it. 
   // The input key is an English prefix and the value is a non-negative integer.
   public void put(String key, int value) { 
       
      Hmap.put(key, value); //simples insert functions
   } 
   // Returns a summation of all corresponding values of the keys in map that start with such prefix.
   public int getSum(String key) { 
      
       int sum = 0; //will hold our given sum of values
       
       for(String prefix: Hmap.keySet()){ //traverses to each entry within the map
           
           if(prefix.startsWith(key)){ //parses the key, if its a march, execute 
               
               sum = sum + Hmap.get(prefix); //sums the corresponding values
           }
   
       }
    return sum;
       
   } 
}