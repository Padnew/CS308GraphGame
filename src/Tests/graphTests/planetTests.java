package Tests.graphTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Game.Planet;

import Game.Universe;

public class planetTests {



    Planet planet = new Planet(5);

    Universe universe = new Universe();


    // testing getNode
    @Test
    public void testNode() {
        assertEquals(5, planet.getNode());
    }

    @Test
    public void testNeighbours() {
    
        
    }
    
}
