package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Game.Planet;
import Game.Universe;
import Graph.Graph;
import java.util.ArrayList;

public class universeTests {

    //Tests that every planet is read from file
    @Test
    public void testReadFile() throws Exception {
        Universe universe = new Universe();
        ArrayList<Planet> planets = universe.readFile();
        assertEquals(129, planets.size());
    }

    //Tests that every connection in the data file gets added to the graph
    @Test
    public void testBuildGraph() throws Exception {
        Universe universe = new Universe();
        ArrayList<Planet> planets = universe.readFile();
        Graph graph = universe.buildGraph(planets);
        assertEquals(50, graph.adjacencyList.size());
    }

    //Tests the Dijkstra's algorithm returns the correct shortest path
    //with the correct total weight in index 0
    @Test
    public void testDijkstra() throws Exception{
        Universe universe = new Universe();
        ArrayList<Planet> planets = universe.readFile();
        Graph graph = universe.buildGraph(planets);
        ArrayList<Integer> testPath = new ArrayList<>();
        testPath.add(5);
        testPath.add(0);
        testPath.add(47);
        testPath.add(5);
        testPath.add(1);
        assertEquals(testPath,graph.dijkstraAlgo(0,1));
        testPath.clear();
        testPath.add(3);
        testPath.add(0);
        testPath.add(47);
        testPath.add(10);
        assertEquals(testPath,graph.dijkstraAlgo(0,10));
        testPath.clear();
        assertEquals(testPath,graph.dijkstraAlgo(0,2345));
        testPath.add(12);
        testPath.add(20);
        testPath.add(11);
        testPath.add(10);
        testPath.add(45);
        assertEquals(testPath,graph.dijkstraAlgo(20,45));
    }
}

