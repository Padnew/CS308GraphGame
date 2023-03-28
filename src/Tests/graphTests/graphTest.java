package Tests.graphTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;


import Graph.Graph;

public class graphTest {


   Graph graph = new Graph();



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

        // test distances
        // test distance from source
        // test nodes are added to visited list, check initialised to all
        // test neighbours
        // test weights


    }









}
