package Game;

import Graph.Node;

import java.util.HashMap;

public class Planet implements Node {
    private final int id;
    private final HashMap<Integer, Integer> neighbours;
//    private int x;
//    private int y;

    public Planet(int id) {
        this.id = id;
        this.neighbours = new HashMap<>(); // initialize the neighbours HashMap
    }

//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }

    public int getNode(){
        return this.id;
    }

    public void addNeighbour(int neighbour, int weight){
        this.neighbours.put(neighbour, weight);
    }

    public HashMap<Integer, Integer> getNeighbours(){
        return this.neighbours;
    }
}