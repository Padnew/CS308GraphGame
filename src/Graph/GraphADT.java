package Graph;
//TODO - What other methods could be useful,

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A graph abstract data type that provides the structure for
 * an undirected weighted graph and provides some simple methods
 * for interacting with the graph
 */
public abstract class GraphADT {

    private Map<Integer, List<Integer>> adjacencyList;

    GraphADT() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * This method adds a node to the adjacency list if it
     * is not present
     * @param node The value of the node to be added
     */
    void addNode(int node){
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    int numOfVertices() {
        return adjacencyList.size();
    }
}