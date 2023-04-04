package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


import Graph.Graph;

import Game.Universe;

import Game.Game;

public class graphTests {


   // graph instance
   Graph graph = new Graph();

   // universe instance -> initially intended for dijkstra testing (now in universe tests)
   Universe universe = new Universe();
  
   
    

   // testing addNode method, create a new node and add it, checking if the adjacency list now contains this node
    @Test
    public void testAddNode() {
        int testNode = 5;
        graph.addNode(testNode);
        assertTrue(graph.adjacencyList.containsKey(testNode));
    }

    // we add new nodes to test of the number of vertices is increased to the correcr values, then add again and check it has incremented by 1
    @Test
    public void testNumVertices() {
        graph.addNode(8);
        graph.addNode(6);
        graph.addNode(7);

        assertEquals(3, graph.numOfVertices());

        graph.addNode(10);

        assertEquals(4, graph.numOfVertices());
    }


}