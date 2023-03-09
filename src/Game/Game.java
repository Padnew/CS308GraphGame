package Game;
import Graph.Graph;


/*
* Game class is the controller and will create an instance of the view(GraphGUI) and model(Universe)
*/
public class Game {
    Graph graph = new Graph();
    GraphGUI graphGUI = new GraphGUI(graph);
    Universe universe = new Universe();
    public void startGame() throws Exception {
        graph = universe.buildGraph(universe.readFile());
        graphGUI.initGraph(graph);
    }
}
