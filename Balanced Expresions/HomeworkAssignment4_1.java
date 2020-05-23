
/******************************************************************************
 *                               Assignment#4, Problem 1
 *                                  DUE: 03/08/20
 *                                By Pedro Antonio Alarcon
 * 
 * PURPOSE: To write a program that evaluates postfix expression, i.e. mathematical
 * notation where an operator (+, -, /, %, ...) appear after their operands.
 * 
 * APPROACH: Given a String array that holds our postfix expression, first parse over
 * the expression and tokenize sets of characters using spaces as a delimiter. Loop
 * over each token and do the following:
 *          1. if operand, simply push into stack
 *          2. if operator, pop two operands, ascertain the operation via
 *          test cases and apply to operands
 * Push result into stack and iterate over the next token and repeat above steps.
 * 
 *******************************************************************************/



import java.util.*;

public class HomeworkAssignment4_1 { 
   public static void main(String[] args) {
   
      Solution sol = new Solution();
      System.out.println(sol.postfix("5 2 4 * + 7 -"));           //6
      System.out.println(sol.postfix("5 7 + 6 2 - *"));             //48
      System.out.println(sol.postfix("-1 2 +"));                      //1
      System.out.println(sol.postfix("-1         2       +"));       //1
      System.out.println(sol.postfix("1 2 3 * + 4 +"));             //11
      System.out.println(sol.postfix("8 5 * 7 4 2 + * +"));           //82
      System.out.println(sol.postfix("6 8 2 / 1 - *"));              //18
      System.out.println(sol.postfix("4 55 + 62 23 - *"));             //2301
 
   }
}

class Solution {
    /******************************************************************************
    * PURPOSE: postfix(String equation) 
    * PARAMETER: 
    *              String equation - holds the postfix expression for evaluation
    * RETURN VALUES: 
    *              Int value resulting from the mathematical operations
    *******************************************************************************/   
   public int postfix(String equation) { 
     
       Stack<Integer> stack = new Stack<>();
       //tokenizes our 
       Scanner item = new Scanner(equation); 
       
       //Iterate over each token
       while(item.hasNext()){ 
     
          //if operand aka int, we push onto Stack
          if( item.hasNextInt()){ //true only if next set of characters can be read as Int 
              stack.push( item.nextInt() ); //converts into int
          } 
          else{ //else, if operator, we must have previously pushed operands to evaluate
                //lets pop them and save them in a vars
              int operand2 = stack.pop();
              int operand1 = stack.pop();
              
              String operator = item.next(); //saves operator in String variable
              
              //Test different cases and evaluate expression accordingly
              // pushing the result into our stack
              if( operator.equals("+") ){
                 stack.push(operand1 + operand2);
              }
              else if(operator.equals("-")){
                  stack.push(operand1 - operand2);
              }
              else if(operator.equals("*")){
                   stack.push(operand1 * operand2);
              }
              else if(operator.equals("/")){
                  stack.push(operand1/operand2);
              }
              else if(operator.equals("%")){
                   stack.push(operand1%operand2);
              }
  
          }
              
           
       }
       
        return stack.pop(); //pop the result
    }
}