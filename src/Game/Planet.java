package Game;
import Graph.Node;
import java.util.HashMap;

/*
* Planet will implement the node interface
*/
public class Planet implements Node {
    private final int id;
    private final HashMap<Integer, Integer> neighbours;
    public Planet(int id) {
        this.id = id;
        this.neighbours = new HashMap<>(); // initialize the neighbours HashMap
    }

//    public Planet(int id, HashMap<Integer, Integer> neighbours) {
//        this.id = id;
//        this.neighbours = neighbours;
//    }
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
