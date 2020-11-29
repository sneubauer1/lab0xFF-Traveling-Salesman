package com.company;

import java.io.*;
import java.util.*;

public class Main {

    private static double[][] matrixFile;
    private static int N;

    public static void main(String[] args) throws IOException {
	// write your code here

        //readGraphFromFile("./src/matrix.txt");

        int N = 6;
        int r = 100;

        Graph graph = new Graph(N);

        double[][] matrix = graph.generateRandomCircularGraphCostMatrix(r);

        //writeGraphToFile(matrix);

        /*double[][] matrix = {{0, 10, 15, 20},
                            {10, 0, 35, 25},
                            {15, 35, 0, 30},
                            {20, 25, 30, 0}};*/

        System.out.println("----------------------------------------");
        BruteForceTSP bruteForceTSP = new BruteForceTSP(matrix);
        System.out.println("Brute Force Approach: ");
        double p = bruteForceTSP.getShortestPathCost();
        System.out.println("Path Cost: "+p);
        Stack<Integer> bestPath;
        bestPath = bruteForceTSP.getShortestPath();

        System.out.print("Path Taken: " + bestPath.pop());
        while(!bestPath.isEmpty())
            System.out.print("-> " + bestPath.pop() );

        System.out.println("\n----------------------------------------");

        DynamicProgrammingTSP dynamicProgrammingTSP = new DynamicProgrammingTSP(matrix);
        System.out.println("Dynamic Programming Approach: ");
        double pathCost = dynamicProgrammingTSP.getShortestPathCost();
        System.out.println("Path Cost: "+pathCost);
        List<Integer> bp = dynamicProgrammingTSP.getShortestPath();

        String list = Arrays.toString(bp.toArray()).replace("[", "").replace("]", "").replace(",", "->");
        //System.out.print(bp);
        System.out.print("Path Taken: " + list);

        System.out.println("\n----------------------------------------");

        /*GreedyTSP greedyTSP = new GreedyTSP(matrix);
        System.out.println("Greedy Programming Approach: ");
        double shortestPath = greedyTSP.getShortestPathCost();
        System.out.println("Path Cost: "+shortestPath);
        List<Integer> greedyp = greedyTSP.getShortestPath();

        String greedyList = Arrays.toString(greedyp.toArray()).replace("[", "").replace("]", "").replace(",", "->");
        System.out.print("Path Taken: " + greedyList);*/
    }



    private static void readGraphFromFile(String fileName) throws FileNotFoundException {
        // Used to get the size of the array
        Scanner scanner = new Scanner(new File(fileName));
        String firstLine = scanner.nextLine();
        String[] data = firstLine.split(" "); // *First number on first line of the file must not have a "space"*
        N = data.length;
        scanner.close();

        // Copy the graph to the graph array
        scanner = new Scanner(new File(fileName));
        matrixFile = new double[N][N];
        for(int r=0; r < N; r++)
        {
            for(int c=0; c < N; c++)
                matrixFile[r][c] = scanner.nextInt();
        }
    }

    private static void writeGraphToFile(double[][] graph) throws IOException {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph.length; j++){
                builder.append(graph[i][j]);
                if(j < graph.length - 1)
                    builder.append(" ");
            }
            builder.append("\n");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("./src/matrix1.txt"));
        writer.write(builder.toString());
        writer.close();
    }
}
