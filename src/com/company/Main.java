package com.company;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

public class Main {

    private static double[][] matrix;
    private static int N;

    public static void main(String[] args) throws IOException {

        //performanceTestingBruteForceTSP();
        //performanceTestingDynamicProgrammingTSP();
        //performanceTestingGreedyTSP();

        SQR();

        //readGraphFromFile("./src/matrix1.txt");

        N = 60;
        int r = 100;

        //for( N = 4; N<= 15; N++) {

            //Graph graph = new Graph(N);

            //double[][] matrix = graph.generateRandomCircularGraphCostMatrix(r);

            //writeGraphToFile(matrix);

        /*double[][] matrix = {{0, 10, 15, 20},
                            {10, 0, 35, 25},
                            {15, 35, 0, 30},
                            {20, 25, 30, 0}};*/

            //System.out.println("----------------------------------------");
            /*BruteForceTSP bruteForceTSP = new BruteForceTSP(matrix);
            System.out.println("Brute Force Approach: ");
            double p = bruteForceTSP.getShortestPathCost();
            System.out.println("Path Cost: " + p);
            Stack<Integer> bestPath;
            bestPath = bruteForceTSP.getShortestPath();

            System.out.print("Path Taken: " + bestPath.pop());
            while (!bestPath.isEmpty())
                System.out.print("-> " + bestPath.pop());*/

            /*System.out.println("\n----------------------------------------");

            DynamicProgrammingTSP dynamicProgrammingTSP = new DynamicProgrammingTSP(matrix);
            System.out.println("\nDynamic Programming Approach: ");
            double pathCost = dynamicProgrammingTSP.getShortestPathCost();
            System.out.println("Path Cost: " + pathCost);
            List<Integer> bp = dynamicProgrammingTSP.getShortestPath();

            String list = Arrays.toString(bp.toArray()).replace("[", "").replace("]", "").replace(",", "->");
            //System.out.print(bp);
            System.out.print("Path Taken: " + list);

            System.out.println("\n----------------------------------------");*/
        //}
        /*GreedyTSP greedyTSP = new GreedyTSP(matrix);
        System.out.println("Greedy Programming Approach: ");
        double shortestPath = greedyTSP.getShortestPathCost();
        System.out.println("Path Cost: "+shortestPath);
        List<Integer> greedyp = greedyTSP.getShortestPath();

        String greedyList = Arrays.toString(greedyp.toArray()).replace("[", "").replace("]", "").replace(",", "->");
        System.out.print("Path Taken: " + greedyList);*/
    }

    /** Get CPU time in nanoseconds since the program(thread) started. */
    /**
     * from: http://nadeausoftware.com/articles/2008/03/java_tip_how_get_cpu_and_user_time_benchmarking#TimingasinglethreadedtaskusingCPUsystemandusertime
     **/
    public static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ?
                bean.getCurrentThreadCpuTime() : 0L;
    }

    public static void performanceTestingBruteForceTSP(){

    int N_Min = 4;
    int N_Max = 15;
    int maxE = 100;
    long trialCount;
    long totalTime;
    long timeStampBefore;
    long timeStampAfter;
    long timeMeasured;
    long maxTrials = 1000;
    long maxTime = 150000;
    long averageTimeMeasured = 1;
    long timeResults[] = new long[N_Max+1];
    double doubleRatio = 1;
    double expectedDoubleRatio = 1;

    /**Print Column Headings**/
    System.out.printf("%20s %25s \n", "N","Brute Force Time");
    System.out.printf("%50s\n", "--------------------------------------");

    for ( N = N_Min; N <= N_Max; N++){

            Graph graph = new Graph(N);
            double[][] matrix = graph.generateRandomCostMatrix(maxE);

            totalTime = 0;
            trialCount = 0;

            while ( totalTime < maxTime && trialCount < maxTrials ) {
                timeStampBefore = getCpuTime();
                BruteForceTSP bruteForceTSP = new BruteForceTSP(matrix);
                bruteForceTSP.getShortestPath();
                bruteForceTSP.getShortestPathCost();
                timeStampAfter = getCpuTime();
                timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            averageTimeMeasured = totalTime / trialCount;
            //timeResults[N] = averageTimeMeasured;


            System.out.printf("%20s %25s \n", N, averageTimeMeasured);
        }

    }

    public static void performanceTestingDynamicProgrammingTSP(){

        int N_Min = 4;
        int N_Max = 30;
        int maxE = 100;
        long trialCount;
        long totalTime;
        long timeStampBefore;
        long timeStampAfter;
        long timeMeasured;
        long maxTrials = 1000;
        long maxTime = 150000;
        long averageTimeMeasured = 1;
        long timeResults[] = new long[N_Max+1];
        double doubleRatio = 1;
        double expectedDoubleRatio = 1;

        /**Print Column Headings**/
        System.out.printf("%20s %25s \n", "N","Dynamic Time");
        System.out.printf("%50s\n", "--------------------------------------");

        for ( N = N_Min; N <= N_Max; N++){

            Graph graph = new Graph(N);
            double[][] matrix = graph.generateRandomCostMatrix(maxE);

            totalTime = 0;
            trialCount = 0;

            while ( totalTime < maxTime && trialCount < maxTrials ) {
                timeStampBefore = getCpuTime();
                DynamicProgrammingTSP dynamicProgrammingTSP = new DynamicProgrammingTSP(matrix);
                dynamicProgrammingTSP.getShortestPath();
                dynamicProgrammingTSP.getShortestPathCost();
                timeStampAfter = getCpuTime();
                timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            averageTimeMeasured = totalTime / trialCount;
            //timeResults[N] = averageTimeMeasured;


            System.out.printf("%20s %25s \n", N, averageTimeMeasured);
        }

    }

    public static void performanceTestingGreedyTSP(){

        int N_Min = 50;
        int N_Max = 100000;
        int maxE = 100;
        long trialCount;
        long totalTime;
        long timeStampBefore;
        long timeStampAfter;
        long timeMeasured;
        long maxTrials = 1000;
        long maxTime = 150000;
        long averageTimeMeasured = 1;
        long timeResults[] = new long[N_Max+1];
        double doubleRatio = 1;
        double expectedDoubleRatio = 1;

        /**Print Column Headings**/
        System.out.printf("%20s %25s \n", "N","Greedy Time");
        System.out.printf("%50s\n", "--------------------------------------");

        for ( N = N_Min; N <= N_Max; N=N*2){

            Graph graph = new Graph(N);
            double[][] matrix = graph.generateRandomCostMatrix(maxE);

            totalTime = 0;
            trialCount = 0;

            while ( totalTime < maxTime && trialCount < maxTrials ) {
                timeStampBefore = getCpuTime();
                GreedyTSP greedyTSP = new GreedyTSP(matrix);
                greedyTSP.getShortestPath();
                greedyTSP.getShortestPathCost();
                timeStampAfter = getCpuTime();
                timeMeasured = timeStampAfter - timeStampBefore;
                totalTime = totalTime + timeMeasured;
                trialCount++;
            }
            averageTimeMeasured = totalTime / trialCount;
            //timeResults[N] = averageTimeMeasured;


            System.out.printf("%20s %25s \n", N, averageTimeMeasured);
        }

    }

    public static  void SQR(){

        int maxCoord = 100;
        int N_Min = 4;
        int N_Max = 23;
        double dynamicPathCost;
        double greedyPathCost;

        double[] averageSQR = new double[N_Max+1];
        int trialCount;
        int maxTrials = 10;
        double[] SQR = new double[(N_Max+1)*maxTrials];
        double total = 0;

        for ( N = N_Min; N <=N_Max; N++){

            //Graph graph = new Graph(N);
            //double[][] matrix = graph.generateRandomEuclideanCostMatrix(maxCoord);
            System.out.println("N: "+N);
            trialCount = 0;
            total = 0;

            while ( trialCount < maxTrials ) {
                Graph graph = new Graph(N);
                double[][] matrix = graph.generateRandomEuclideanCostMatrix(maxCoord);
                DynamicProgrammingTSP dynamicProgrammingTSP = new DynamicProgrammingTSP(matrix);
                dynamicPathCost = dynamicProgrammingTSP.getShortestPathCost();

                GreedyTSP greedyTSP = new GreedyTSP(matrix);
                greedyPathCost = greedyTSP.getShortestPathCost();
                SQR[trialCount] = greedyPathCost/dynamicPathCost;
                System.out.println(SQR[trialCount]);
                trialCount++;
            }
            for(int i = 0; i <= maxTrials; i++){
                total = total + SQR[i];
            }
            double average = total / maxTrials;
            System.out.println("total "+total+ " average "+average);
            averageSQR[N] = average;
        }
        System.out.println("----------------------------------------");
        System.out.println("Average SQR over all trials for each N");
        for( int i = N_Min; i <= N_Max; i++){
            System.out.println("N: "+i+" "+averageSQR[i]);
        }

    }

    public static long fact( int N){
        long fact = 1;
        for( int i = 2; i <= N; i++){
            fact = fact * i;
        }
        return fact;
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
        matrix = new double[N][N];
        for(int r=0; r < N; r++)
        {
            for(int c=0; c < N; c++)
                matrix[r][c] = scanner.nextInt();
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
