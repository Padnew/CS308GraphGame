package Graph;

import java.util.ArrayList;
public class Node {
    private int vertex;
    private ArrayList<Integer> neighbors;
    public Node(int vertex, ArrayList<Integer> neighbors) {
        this.vertex = vertex;
        this.neighbors = neighbors;
    }
    public int getNode(){
        return this.vertex;
    }
    public void addNeighbor(int neighbor){
        this.neighbors.add(neighbor);
    }
    public ArrayList<Integer> getNeighbors(){
        return this.neighbors;
    }
}
