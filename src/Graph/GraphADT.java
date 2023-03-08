package Graph;
//TODO - What other methods could be useful,

import java.util.ArrayList;

/**
 * A graph abstract data type that provides the structure for
 * an undirected weighted graph and provides some simple methods
 * for interacting with the graph
 */
public interface GraphADT {

    void addNode(int node);

    int numOfVertices();

    ArrayList<Integer> djikstraAlgo(int startNode, int endNode);
}