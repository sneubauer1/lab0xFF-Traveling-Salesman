package com.company;

import java.util.ArrayList;

public class Graph {

    public static int[] v;
    int V;
    double[][] matrix;
    //public int[] v;
    final static int infinity = -1;

    public Graph(int V) {
        this.V = V;
        matrix = new double[V][V];
        //v = randomizeVertices();
        v = new int[V];
    }

    public void addEdge(int source, int destination, double weight) {
        //add edge
        matrix[source][destination]= weight;

        //add bak edge for undirected graph
        matrix[destination][source] = weight;
    }

    public void removeEdge(int source, int destination ) {
        //remove edge
        matrix[source][destination] = infinity;

        //remove back edge for undirected graph
        matrix[destination][source] = infinity;
    }

    public double[][] generateRandomCostMatrix( int maxE) {

        //System.out.println("N: "+this.V);

        for(int i = 0; i < this.V; i++) {
            for(int j = i; j < this.V; j++) {
                if(i == j) {
                    matrix[i][j] = 0;
                }
                else {
                    double temp = Math.random()*maxE;
                    temp =  Math.round(temp*100)/100.0d;
                    addEdge(i,j,temp);
                    //matrix[i][j] = temp;
                    //matrix[j][i] = temp;
                }
            }
        }
        //printGraph();
        return matrix;
    }

    public double[][] generateRandomEuclideanCostMatrix( int maxCoord){

        //System.out.println("N: "+this.V);

        for(int i = 0; i < this.V; i++) {
            for(int j = i; j < this.V; j++) {
                if(i == j) {
                    matrix[i][j] = 0;
                }
                else {
                    double xVal1 = Math.random() * maxCoord;
                    double xVal2 = Math.random() * maxCoord;
                    double yVal1 = Math.random() * maxCoord;
                    double yVal2 = Math.random() * maxCoord;
                    double d =  dist(xVal1,xVal2,yVal1,yVal2);
                    addEdge(i,j,d);
                    //matrix[i][j] =  d;
                    //matrix[j][i] =  d;
                }
            }
        }
        //printGraph();
        return matrix;
    }

    public double[][] generateRandomCircularGraphCostMatrix( int r) {

        System.out.println("N: "+this.V);

        System.out.print("Expected Path around circle: ");
        for ( int i = 0; i < this.V; i++){
            System.out.print(v[i]+"-> ");
        }
        System.out.print(v[0]);

        System.out.println();
        double sparsity = 0.5;
        double stepAngle = (2 * Math.PI) / this.V;
        double[] x = new double[this.V];
        double[] y = new double[this.V];
        double[] d = new double[this.V];


        for (int s = 0; s < this.V; s++) {
            double xTemp = r * Math.sin(s * stepAngle);
            x[s] = Math.round(xTemp * 1000) / 1000.0d;
            double yTemp = r * Math.cos(s * stepAngle);
            y[s] = Math.round(yTemp * 1000) / 1000.0d;
        }
        System.out.print("V: ");
        for (int i = 0; i < this.V; i++) {
            System.out.print(v[i]+" "+"("+x[i]+","+y[i]+") ");
        }
        System.out.println();


        for (int i = 0; i < this.V; i++) {
            for (int j = i; j < this.V; j++) {
                if (i == j) {
                    matrix[v[i]][v[j]] = 0;
                } else {
                    double xVal1 = x[v[i]];
                    double xVal2 = x[v[j]];
                    double yVal1 = y[v[i]];
                    double yVal2 = y[v[j]];
                    d[i] = dist(xVal1,xVal2,yVal1,yVal2);
                    //System.out.println("Distance: "+d[i]);
                    addEdge(v[i],v[j],d[i]);
                    //matrix[i][j] = d;
                    //matrix[j][i] = d;
                }
            }
        }
        double distanceadj = getAdjDistance(d);

        System.out.println("Expected Cost: "+this.V*distanceadj);
        printCircleGraph();

        return matrix;
    }

    public int[] randomizeVertices( ){
        int[] vertices = new int[this.V];

        for ( int i = 0; i < this.V; i++){
            if( i == 0){
                vertices[0] = 0;
            } else {
                vertices[i] = (int) (Math.random() * this.V);
            }
            for( int j = 0; j < i; j++){
                if ( vertices[i] == vertices[j]){
                    i--;
                    break;
                }
            }
        }
        return vertices;
    }

    public double dist( double x1, double x2, double y1, double y2){
        double d =  Math.sqrt( (y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1) );
        return  (Math.round(d*100)/100.0d);
    }

    public double getAdjDistance(double[] num){
        double min = num[0];
        for( int i = 1; i < num.length; i++){
            if( num[i] < min && num[i] != 0){
                min = num[i];
            }
        }
        return min;
    }


    public void printCircleGraph(){
        System.out.println("Graph: (Circular Cost Matrix)");
        for (int i = 0; i < this.V; i++) {
            for (int j = 0; j < this.V ; j++) {
                System.out.print(matrix[v[i]][v[j]]+ " ");
            }
            System.out.println();
        }
        /*for (int i = 0; i < this.V; i++) {
            System.out.print("Vertex " + v[i] + " is connected to: ");
            for (int j = 0; j < this.V ; j++) {
                if(matrix[v[i]][v[j]]> 0.000){
                    System.out.print(v[j] + " ");
                }
            }
            System.out.println();
        }*/
    }

    public void printGraph() {
        System.out.println("Graph: (Adjacency Matrix)");
        for (int i = 0; i < this.V; i++) {
            for (int j = 0; j < this.V ; j++) {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }
        for (int i = 0; i < this.V; i++) {
            System.out.print("Vertex " + i + " is connected to: ");
            for (int j = 0; j < this.V ; j++) {
                if(matrix[i][j]> 0.000){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }



}
