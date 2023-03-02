package Graph;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private final int id;
    private HashMap<Integer, Integer> neighbours;
    public Node(int id, HashMap<Integer, Integer> neighbours) {
        this.id = id;
        this.neighbours = neighbours;
    }
    public int getNode(){
        return this.id;
    }
    public void addNeighbor(int neighbour, int weight){
        this.neighbours.put(neighbour, weight);
    }
    public HashMap<Integer, Integer> getNeighbors(){
        return this.neighbours;
    }
}
