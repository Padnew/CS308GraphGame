package Graph;
import Game.Planet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
* Graph will implement the GraphADT interface
*/
public class Graph implements GraphADT{
    public Map<Integer, Planet> adjacencyList;
    public Graph(){
        this.adjacencyList = new HashMap<>();
    }
    public Graph(Map<Integer, Planet> objects){
        this.adjacencyList = objects;
    }
    @Override
    public void addNode(int node) {
        if (!adjacencyList.containsKey(node)) {
            adjacencyList.put(node, new Planet(node));
        }
    }
    @Override
    public int numOfVertices() {
        return adjacencyList.size();
    }
    @Override
    public ArrayList<Integer> djikstraAlgo(Map<Integer, List<Integer>> graph, int startNode){
        ArrayList<Integer> path = new ArrayList<>();
//        Yet to be filled out
        return path;
    };
}
