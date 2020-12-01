package com.company;

import java.util.Arrays;
import java.util.Stack;

public class BruteForceTSP {
    private int n; // Representing the total vertices of the graph
    private double [][] matrix; // Representing the adjacency matrix of the graph
    private boolean[] visitedVertices; // Representing which vertices have been currently visited
    private double shortestPathCost; // Representing the total cost of the path taken
    private Vertex lastVertex; // The last visited vertex before reaching the root again (Contains pointers with the path taken)
    int[] v = Graph.v;

    BruteForceTSP(double[][] matrix)
    {
        this.n = matrix.length;
        this.matrix = matrix;
        this.visitedVertices = new boolean[n];
        this.shortestPathCost = Integer.MAX_VALUE;
        this.lastVertex = null;

        // Mark first vertex as visited since we use it as the root
        visitedVertices[0] = true;
    }

    // Returns a double representing the shortest's path cost
    double getShortestPathCost() {
        backTracking(new Vertex(0,null,0),  1);
        return Math.round(shortestPathCost*100) / 100.0d;
    }

    // Method to find the shortest path
    private void backTracking(Vertex currentVertex, int verticesVisited)
    {
        // If last vertex is reached and it has a link to the root vertex then
        // keep the minimum value out of the total cost
        // of traversal and "ans"
        // Returning to check for more possible values
        if (verticesVisited == n && matrix[currentVertex.getId()][0] > 0)
        {
            if (shortestPathCost <= currentVertex.getCostToReach() + matrix[currentVertex.getId()][0])
                return;

                // Better path found
            else
            {
                shortestPathCost = currentVertex.getCostToReach() + matrix[currentVertex.getId()][0];
                lastVertex = currentVertex;
                return;
            }
        }

        // Loop to traverse the adjacency list of the current vertex and increasing the visited vertices
        // by 1, moving to the next vertex and increasing the new vertex cost by graph[currentVertex,i] value
        for (int i = 0; i < n; i++)
        {
            if (!visitedVertices[i] && matrix[currentVertex.getId()][i] > 0)
            {
                // Mark as visited
                visitedVertices[i] = true;

                Vertex nextVertex =  new Vertex(i, currentVertex, currentVertex.getCostToReach() + matrix[currentVertex.getId()][i]);
                backTracking(nextVertex, verticesVisited + 1);

                // Mark ith node as unvisited after the recursion return
                visitedVertices[i] = false;
            }
        }
    }

    // Returns a Stack of integers representing the path of the best shortest route to take
    Stack<Integer> getShortestPath() {

        Stack<Integer> shortestPath = new Stack<>();
        //Stack<Integer> shortestPath = new Stack<>();
        shortestPath.push(0);

        Vertex currentVertex  = lastVertex;

        while(currentVertex != null)
        {
            shortestPath.push(currentVertex.getId());
            currentVertex = currentVertex.getPrev();
        }

        return shortestPath;
    }

}
