package Tests.graphTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


import Graph.Graph;

import Game.Universe;

import Game.Game;

public class graphTest {


   Graph graph = new Graph();

   Universe universe = new Universe();
  
   
    

    @Test
    public void testAddNode() {
        int testNode = 5;
        graph.addNode(testNode);
        assertTrue(graph.adjacencyList.containsKey(testNode));
    }

    @Test
    public void testNumVertices() {
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);

        assertEquals(3, graph.numOfVertices());

        graph.addNode(8);

        assertEquals(4, graph.numOfVertices());
    }

    // initialise for testing

    

    @Test
    public void testDijkstras() {
        // testing universe and planet instead
    }









}
