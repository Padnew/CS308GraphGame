package Tests;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import Game.Player;

public class playerTests {
    @Test
    // test the player class constructor and getters
    public void testPlayer() {
        Player player = new Player();
        assertEquals(0, player.getScore());
        assertEquals(200, player.getFuel());
    }

    @Test
    // test the incrementScore method
    public void testIncrementScore() {
        Player player = new Player();
        player.incrementScore();
        assertEquals(1, player.getScore());
    }

    @Test
    // test the decrementFuel method
    public void testDecrementFuel() {
        Player player = new Player();
        player.decrementFuel(10);
        assertEquals(190, player.getFuel());
    }

    @Test
    // test the resetPlayer method
    public void testResetPlayer() {
        Player player = new Player();
        player.incrementScore();
        player.decrementFuel(50);
        player.resetPlayer();
        assertEquals(0, player.getScore());
        assertEquals(200, player.getFuel());
    }

 
    
}
