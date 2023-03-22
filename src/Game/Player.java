package Game;

/**
* Player class is used to store the current players score and fuel state
* in order to have an end state for the game
**/
public class Player {
    int score;
    int fuel;
    private final int START_FUEL = 200; // Initial Fuel

    public Player(){
        this.score = 0;
        this.fuel = START_FUEL;
    }

    public int getScore() {
        return score;
    }

    public int getFuel(){
        return fuel;
    }

    public void incrementScore(){
        this.score ++;
    }

    /**
     * Used to change the fuel when a guess is wrong
     */
    public void decrementFuel(int difference){
        this.fuel -= difference;
    }

    /**
     * Resets player to default state
     */
    public void resetPlayer(){
        this.fuel = START_FUEL;
        this.score = 0;
    }
}
