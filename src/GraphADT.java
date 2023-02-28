//TODO - What other methods could be useful,
// better way to represent edges?

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

             // Nodes   , List of edges
            // calculate number of edges by doing length of list weights in loop
    private Map<Integer, List<Edge>> adjacencyList;

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
        int numOfNodes = 0;
        numOfNodes =  adjacencyList.size();
        return numOfNodes;
    }

    int numEdges() {
        int numOfEdges = 0;
        for (List<Edge> value: adjacencyList.values()) { // loop over and find the length of each list , adding it to the total of numOfeEdges
            numOfEdges += value.size();
        }
        return numOfEdges;
    }

    /**
     * Adds an edge between two nodes, as the graph is undirected there must
     * be a node created in both directions
     * @param source Where the edge starts
     * @param destination Where the edge ends
     * @param weight The weight of the edge
     */
    void addEdge(int source, int destination, int weight){
        adjacencyList.get(source).add(new Edge(destination, weight));
        adjacencyList.get(destination).add(new Edge(source, weight));
    }

    /** gets list of edges
        * want to check if there is an edge between nodes A -> B
        * would need to check if in the list , for key A, there exists an edge to B
        * get list of edges from source , check edge in the list to see if it equals destination node
     **/
    Edge getEdge(int source, int destination) {


        Edge finalEdge = null;
        List<Edge> edges = adjacencyList.get(source); // get list of edges
        for (Edge value: edges) {
            if (value.getEdge() == destination) {
                finalEdge = value;
            }
        }
        return finalEdge;
    }

    /**
     * Removes an edge by deleting the edge in both directions
     * @param source Where the edge starts
     * @param destination Where the edge ends
     */
    void removeEdge(int source, int destination){
        List<Edge> edges = adjacencyList.get(source);
        edges.removeIf(edge -> edge.destination == destination);
        edges = adjacencyList.get(destination);
        edges.removeIf(edge -> edge.destination == source);
    }

    /**
     * Removes a node and all of its associated edges
     * @param node The node to be removed
     */
    void removeNode(int node){
        adjacencyList.values().forEach(edges -> edges.removeIf(edge -> edge.destination == node));
        adjacencyList.remove(node);
    }
}