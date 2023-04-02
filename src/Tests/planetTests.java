package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import Game.Planet;

import Game.Universe;

public class planetTests {

    Planet planet = new Planet(5);

    Planet planetTest = new Planet(4);

    Universe universe = new Universe();


    // testing getNode
    @Test
    public void testNode() {
        assertEquals(5, planet.getNode());
    }

    // testing addNeighbour 
    @Test
    public void testAddNeighbour() {
        planet.addNeighbour(7, 4);
        assertTrue(planet.getNeighbours().containsKey(7));
    }

    // testing getNeighbours function
    @Test 
    public void testGetNeighbours() {
        planetTest.addNeighbour(3,5);
        planetTest.addNeighbour(6, 8);
        HashMap<Integer, Integer> testMap = new HashMap<Integer, Integer>();
        testMap.put(3, 5);
        testMap.put(6, 8);
        assertEquals(testMap, planetTest.getNeighbours());
    }


    
    
}
