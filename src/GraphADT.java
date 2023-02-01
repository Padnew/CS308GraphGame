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

    /**
     * A definition of an edge, to be used within the adjacency list
     * representing the graph
     */
    class Edge{
        int destination;
        int weight;

        Edge(int destination, int weight){
            this.destination = destination;
            this.weight = weight;
        }
    }
}