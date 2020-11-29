package com.company;

class Vertex {
    private int id; // The id of the current vertex
    private double costToReach; // The total cost so far to reach this vertex
    private Vertex prev; // The previous vertex before reaching this

    Vertex(int id, Vertex prev, double costToReach) {
        this.id = id;
        this.prev = prev;
        this.costToReach = costToReach;
    }

    int getId() {
        return id;
    }

    double getCostToReach() {
        return costToReach;
    }

    Vertex getPrev() {
        return prev;
    }
}