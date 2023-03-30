package Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import Game.Planet;
import Game.Universe;
import Graph.Graph;
import java.util.ArrayList;

public class universeTests {
    
    @Test
    public void testReadFile() throws Exception {
        Universe universe = new Universe();
        ArrayList<Planet> planets = universe.readFile();
        assertEquals(129, planets.size());
    }
    
    @Test
    public void testBuildGraph() throws Exception {
        Universe universe = new Universe();
        ArrayList<Planet> planets = universe.readFile();
        Graph graph = universe.buildGraph(planets);
        assertEquals(50, graph.adjacencyList.size());
    }
    
}

