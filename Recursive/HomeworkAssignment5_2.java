/******************************************************************************
 *                               Assignment#5, Problem 2
 *                                  DUE: 04/05/20
 *                                By: Pedro Alarcon
 * 
 * PURPOSE: Given Array A[] with N positive integers, we wish to partition the vector space into
 *  K subsets whose sum of elements are equal for all subsets. 
 * 
 * APPROACH:
 * 
 * Mathematical derivations (shown below) shows that subset sum, Y = SUM(A)/k. Thus consider
 * an initially empty array whose kth value represent subset-sum. Consider each element in resource
 * and recursively add each element to the kth subset-sum such that resulting sum does not exceed targetSum.
 * If each subset-sum equals targetSum, we have sucessfully allocated each element in k partition.
 * If we allocated incorrectly, we backtrack and repartition the values into a different subset. 
 * 
 * This greedy recursive approach cycles through each possible subset combination which
 * could become unfeasible for very large arrays. Nevertheless, by sorting the array
 * element and verifying against constrains would increment performance in various scenarios.
 * 
 *       Constrains:  
 *                 SUM(A) must be divisible by K and Y, where K !- 0, Y != 0 
 *                 K < N
 *                 Sum of elements in each subset partition is SUM(A)/K
 *******************************************************************************/



public class HomeworkAssignment5_2 {    
   public static void main(String[] args) {
       Solution sol = new Solution();
      
      
       int A[] = {1,2, 2, 3, 3, 4, 5};
       int K = 4;
       System.out.println( sol.canDistribute(A, K) );
       
             
       int A2[] = {1, 3, 2, 3, 4, 1, 3, 5, 2, 1};
       int K2 = 5;
       System.out.println( sol.canDistribute(A2, K2) );
       
       int A3[] = {3,4,5,6};
       int K3 = 2;
       System.out.println( sol.canDistribute(A3, K3) );
       
       
       int A4[] = {1};
       int K4 = 4;
       System.out.println( sol.canDistribute(A4, K4) );
   }
}

 /******************************************************************************
 *  Mathematical Analysis:
 * Let A denote a subset spanning an n-dimensional vector space with elements : [X1, X2, X3, ... , Xn], 
 * we are tasked to find K-subsets A1, A2, ... ,A_K , such that the sum of elements within each
 * subset equals Y. Or Equivalently:
 * , 
 *             SUM(A1) + SUM(A2) + ... + SUM(A_K) = Y + Y + ... + Y = Y*K
 *          => SUM(A) = Y*K 
 *          => Y = SUM(A) / K
 * 
 * Analyzing the above, we conclude the following:
 *  - Given K and Array A[], we can find sum of each subset partition, Y. 
 *  - Sum of integers must be an integer, thus Y must also be an int
 *  - Note that the number of partitions, K, has an upper limit of N, otherwise subset
 *  division N/K would produce a decimal, thus N > K
 *  - If K = 1, our subset is the vector space itself, SUM(A) 
 *******************************************************************************/
class Solution {
    
    /******************************************************************************
    * PURPOSE: setSummation) -- sums each element of the array. 
    * PARAMETER: 
    *              Array of integers
    * RETURN VALUES: 
    *              the sum of all elements in array parameter
    *******************************************************************************/
    public int setSummation(int[] resources){
        int sum = 0;
        //utility function to return sum of elements within an array
        for(int i = 0; i < resources.length; i++){
            sum = sum + resources[i];
        }
        
        return sum;
    }
   
    /******************************************************************************
    * PURPOSE: Verifies if problem contrains are met
    * PARAMETER: 
    *              Array A[] - set we wish to partition
    *              integer groups - number of partitions
    * RETURN VALUES: 
    *              True - if both constrains are met
    *              False - if one constrain is not met
    *******************************************************************************/
    public boolean areConstrainsSatisfied(int[] resources, int groups){
        
        int setElements = resources.length;
        int sum = setSummation(resources);
        

        boolean isSumDivisible = false;
        //is N > K, or is number of elements in resources greater than partition value?
        boolean isPartitionValid = (setElements > groups);
        
        //if targetSum divisible by partition value?
        if(sum % groups == 0){
            isSumDivisible = true;
        }
        
        //return true only if both conditions are met
        return (isPartitionValid && isSumDivisible);
        
    }
    
    /******************************************************************************
    * PURPOSE: Checks whether the elements in array are equal to targetSum 
    * PARAMETER: 
    *              None.
    * RETURN VALUES: 
    *              Boolean - True if they are all equal, false otherwise
    *******************************************************************************/   
    public boolean areSubsetsSumEqual(int targetSum, int subsetsSum[]){
        
        boolean isEqual = true;
        //loop over each subset and verify if sum is equal to targetSum
        for(int i = 0; i < subsetsSum.length; i++){ 
            
            if(subsetsSum[i] != targetSum){ 
                isEqual = false; //if one instance fails, exit program report failure
                break;
            }
           
        }
        return isEqual;
    }
    
    /******************************************************************************
    * PURPOSE: recursive function that testes various subsets and looks for targetSum
    * PARAMETER:    int[] resources, -> array of resources we wish to partition
    *               int[] memoryBuffer, -> array of k elements
    *               int groups, -> partition number
    *               int currElementIndex,-> current element index
    *               int targetSum -> sum that all k groups should have
    *              
    * RETURN VALUES: 
    *              True if partition is possible, false otherwise
    *******************************************************************************/    
    public boolean helper(int[] resources, int[] memoryBuffer, int groups, int currElementIndex,int targetSum){

        //If we've considered all elements within resource[] 
        //AND each subset sums == target, sucess!
        if (currElementIndex < 0 && areSubsetsSumEqual(targetSum, memoryBuffer)){    
            return true; 
        }
        
        // for each element, j,  in resources[], loop over each subset k and add element
        // i to valud in the firt available subset k so long as the resulting sum
        // does not exceed targetSum.
        for(int i = 0; i < memoryBuffer.length; i++){
            
            //if resulting sum exceeds target value immediately jumps to next subset and try again
            if( ( memoryBuffer[i] + resources[currElementIndex] ) > targetSum ){
                continue;   
            }
            //otherwise, add values
            memoryBuffer[i] += resources[currElementIndex];
            
            //Recursively proceed to the next element, j, in resources[] and distribute accordingly
            if( helper(resources, memoryBuffer, groups, currElementIndex - 1, targetSum) ){
                return true;
            }
            //If solution fails to partition correctly, backtrack by subtracting current element
            //from corresponding partition. Tries to allocate same element into the next subset 
            memoryBuffer[i] -= resources[currElementIndex]; //This allows us to try different combinations
        }


        
        return false;
    }

  
    /******************************************************************************
    * PURPOSE: Checks constrains and invokes recursive function to test whether
    * a given set of resources could be subdivided into k groups with equal sum.
    * PARAMETER: 
    *           int[] resources -> vector space we wish to partition
    *           int groups -> int K, iow, the amount of subsets we wish to partition
    *              
    * RETURN VALUES: Boolean true or false. True only if given resources could be 
    * subdivided into k groups with equal sum.
    *              
    *******************************************************************************/   
    public boolean canDistribute(int[] resources, int groups) { 
       
       //check constrains, exit program if not met
       if(!areConstrainsSatisfied(resources, groups)){ 
           return false;  
       }
       //Initialize empty array of K elements which are intended to hold sum of subset
       int memoryBuffer[] = new int[groups];
       // figure out target sum that each memoryBuffer[i] should have
       int targetSum = setSummation(resources)/groups; 
       
 
       // we start with last element in resource[], which we designed it to have the largest value
       return helper(resources, memoryBuffer, groups, resources.length - 1, targetSum);
      
   }
}