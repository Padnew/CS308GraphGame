package Game;

import javax.swing.*;
import java.awt.*;

/*
* Game class is the controller and will create an instance of the view(GraphGUI) and model(Universe)
*/
public class Game {
    GraphGUI graph = new GraphGUI();
    Universe universe = new Universe();
    public void startGame(){
        graph.initGraph();
    }
}
