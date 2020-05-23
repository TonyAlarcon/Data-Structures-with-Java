/******************************************************************************
 *                                  Assignment#10, Problem 2
 *                                  DUE: 05/30/20
 *                                  By Pedro Alarcon
 * 
 * PURPOSE:  Problem Set Up: 
 * Suppose a hospital has N employees each with a unique sequential ID ranging from [0, N - 1]
 * The hospital is tasked to evenly distribute PPE such that equipment distribution 
 * originates from the CEO to the lower levels of command. 
 * 
 * 
 * APPROACH :  We model this problem by constructing an undirected graph implemented 
 * using adjacency matrices where Nodes represent managers and edges correspond 
 * to a mapped direct. Thereafter, we traverse the graph
 * using breath first search, each time aggregating the distribution time/costs when
 * each manager is visited.
 * 
 * V(Graph 1) = {Node1, Node2, Node3,....}, where each Node i represents a manager
 * 
 * Each healthcare worker i, has only one direct manager whose unique ID is manager[i],
 * where manager[] is an array with manager's IDs. This, the edges are represented by:
 * 
 * E(Graph 1) = {(i, manager[i]), (j, manager[j]), (k, manager[k]), ...}, where manager
 * 
 *******************************************************************************/


import java.util.LinkedList;
import java.util.Queue;


public class HomeworkAssignment10_2 {
   public static void main(String[] args) {
      Solution sol = new Solution();
      
      System.out.println("\nTest #1");
      int[] managers = new int[]{2,2,-1,2,2};
      int[] distributionTimes = new int[]{0,0,1,0,0};
      int time = sol.getMinutes(5, 2, managers, distributionTimes);
      System.out.println();
      System.out.println(time);
      
      System.out.println("\nTest #2");
      int[] managers1 = new int[]{1, 2, -1};
      int[] distributionTimes1 = new int[]{5, 2, 1};
      int time1 = sol.getMinutes(3, 2, managers1, distributionTimes1);
      System.out.println();
      System.out.println(time1);

   }
}


/******************************************************************************
 * PURPOSE: To calculate the total distribution time required to evenly distribute PPEs
 * PARAMETER:  
 * number of Nodes (employees) n, 
 * ID of CEO, ceoID, 
 * array of integers whose elements are IDs of managers, int[] managers
 * array of ints whose elements correspond to distribution overhead per manager, 
 * 
 * RETURN VALUES: returns the total time, in minutes, required to evenly distribute PPEs
 *******************************************************************************/
class Solution {
   // OR, YOUR DOCUMENTATION HERE
   public int getMinutes(int n, int ceoID, int[] managers, int[] distributionTimes) { 
      
       Graph graph = new Graph(n);
       for(int i = 0; i < n; i++){
    
           graph.addEdge(Math.abs(managers[i]), i); //absolute value due to manager[ceoID] = -1;
           
       }
       graph.printMatrix(); //get matrix
       System.out.println();
       graph.printEdges(); //print node connections, for clarity and debugging
       System.out.println();
       graph.BFS(graph.getMatrix(), ceoID, distributionTimes); //traverse using BFS
       
       
       return graph.getTime();//target
   } 
}


/* Undirected Graph Implementation using Adjacency Matrices */
class Graph{
    private int vertexCount; //Number of Nodes
    private int[][] adjMatrix; //Our matrix
    private int totalTime = 0; //target variable
    
    // constructor
    public Graph(int vertexCount){
        this.vertexCount = vertexCount;
        adjMatrix = new int[vertexCount][vertexCount]; // N x N 2D Array
    }
    //getters
    public int[][] getMatrix(){ return this.adjMatrix;}
    
    public int getTime(){ return this.totalTime;}
    
    //Utility function to denote a connection between two vertices
     //In Undirected Graphs, Adjacency Matrix is symmetrical
    public void addEdge(int from, int to){
        adjMatrix[from][to] = 1; //This edges are ordered pairs
        adjMatrix[to][from] = 1;// as such, we define both edges (x,y) and (y,x)
    }
    
    //Breath First Search algorithm that traverses the graph level by level
    public void BFS(int[][] adjMatrix, int source, int[] distributionTimes){
        
        boolean[] visited = new boolean[adjMatrix.length]; //array to keep track of visited nodes
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source); //Enqueues and marks current node
        visited[source] = true;
        

        
        System.out.print("BST: ");
        while(!queue.isEmpty()){
            System.out.print(queue.peek() + " -> ");
            this.totalTime += distributionTimes[queue.peek()]; //calculate time distribution 
            int x = queue.poll(); //dequeues
            
            for(int i = 0; i < adjMatrix.length; i++){
                if(adjMatrix[x][i] == 1 && !visited[i]){
                    queue.add(i);
                    visited[i] = true; //mark as discovered
                    
                }
            
        }
        }

        
    }
    //Utility function to print  Adjacency Matrix along with their index
    public void printMatrix(){
        System.out.print("      ");
    
        for(int i = 0; i < vertexCount; i++){
            System.out.print(i + " ");
        }
        
        System.out.println();
        System.out.println("_ _ _ _ _ _ __");
        for(int i = 0; i < vertexCount; i++){ //rows
            System.out.print(i + "   | ");
            for(int j = 0; j < vertexCount; j++){ //columns
                
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
            
        }
    }
    
    //Utility function to print each node and their corresponding connections
    public void printEdges(){
        for(int i = 0; i < vertexCount; i++){
            System.out.print("Node " + i + " is connected to: ");
            for(int j = 0; j < vertexCount; j++){ //traverses through all nodes
                if(adjMatrix[i][j] == 1){ // 1 denotes a connection
                    System.out.print(j + " ");
                }
            }
            System.out.println();
            
        }       
        
    }
            
}

