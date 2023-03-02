package Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* Graph will implement the GraphADT interface
*/
public class Graph implements GraphADT{
    public Map<Integer, List<Integer>> adjacencyList;
    public Graph(){
        this.adjacencyList = new HashMap<>();
    }
    @Override
    public void addNode(int node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
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
