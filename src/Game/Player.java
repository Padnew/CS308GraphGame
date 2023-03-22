package Game;

/*
* Player class is used to store the current players score and fuel state
* in order to have an end state for the game
*/
public class Player {
    int score;
    int fuel;

    public Player(){
        this.score = 0;
        this.fuel = 200;
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

    public void decrementFuel(int difference){
        this.fuel -= difference;
    }

    public void resetPlayer(){
        this.fuel = 200;
        this.score = 0;
    }
}
