package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Game.Game;

public class gameTests {
    
    @Test
    public void test1() throws Exception {
        
        Game game = new Game();

        assertEquals(2, 1 + 1);
    }
}
