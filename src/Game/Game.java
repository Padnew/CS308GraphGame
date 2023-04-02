package Game;

import Graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.lang.Math;

/*
 * Game class is the controller and will create an instance of the view(GraphGUI) and model(Universe)
 */
public class Game implements MouseListener {
    Universe universe = new Universe();
    Graph graph = universe.buildGraph(universe.readFile());
    private HashMap<Integer, int[]> locations = new HashMap<>();
    Player player = new Player();

    GraphGUI graphGUI = new GraphGUI(graph, locations);
    JButton submitButton;
    JLabel srcLabel;
    JLabel destLabel;
    JTextField guessTextField;
    JButton randomiseButton;
    JButton clearLabelsButton;
    JLabel fuelLabel;
    JLabel scoreLabel;
    JPanel graphPanel;
    private final int PLANET_SIZE = 30; //Scales the size of the nodes/planets

    public Game() throws Exception {
//        Pull all the GUI elements from GraphGUI
        submitButton = graphGUI.getSubmitButton();
        srcLabel = graphGUI.getSrcLabel();
        destLabel = graphGUI.getDestLabel();
        guessTextField = graphGUI.getGuessTextField();
        randomiseButton = graphGUI.getRandomiseButton();
        clearLabelsButton = graphGUI.getClearLabelsButton();
        fuelLabel = graphGUI.getFuelLabel();
        scoreLabel = graphGUI.getScoreLabel();
        graphPanel = graphGUI.getGraphPanel();
        scoreLabel.setText(String.valueOf(player.getScore()));
        fuelLabel.setText(String.valueOf(player.getFuel()));
//        Adding an action listener for submitting their guess
        submitButton.addActionListener(e -> {
            // Super easy and not verbose code to display an icon on a message to a scaled size
            ImageIcon i = new ImageIcon("./data/rocketIcon.png");
            Image imageVersion = i.getImage();
            Image imageScaled = imageVersion.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            i = new ImageIcon(imageScaled);

//          get the positions which are selected and in the labels on the GUI
            if(srcLabel.getText() == "" || destLabel.getText() == ""){
                JOptionPane.showMessageDialog(null, "Please select BOTH a destination and a source node","Enter both nodes",JOptionPane.ERROR_MESSAGE, i);
            }
            else {
                int firstNode = Integer.parseInt(srcLabel.getText());
                int secondNode = Integer.parseInt(destLabel.getText());
                // If the guess is successful
                if (graph.dijkstraAlgo(firstNode, secondNode).get(0) == Integer.parseInt(guessTextField.getText())) {
                    player.incrementScore();
                    JOptionPane.showMessageDialog(null,
                            "Correct!\nTotal weight = " +
                                    graph.dijkstraAlgo(firstNode, secondNode).get(0)
                                    + "\nYour guess = " + Integer.parseInt(guessTextField.getText()), "Congrats!", JOptionPane.ERROR_MESSAGE, i);
                }
                // If the guess is unsuccessful
                else {
                    JOptionPane.showMessageDialog(null,
                            "Aw unlucky!\nTotal weight = " +
                                    graph.dijkstraAlgo(firstNode, secondNode).get(0)
                                    + "\nYour guess = " + Integer.parseInt(guessTextField.getText()), "Unlucky!", JOptionPane.ERROR_MESSAGE, i);
                    int diff = Math.abs(graph.dijkstraAlgo(firstNode, secondNode).get(0) - Integer.parseInt(guessTextField.getText())); //Absolute value of actual distance - guess
                    player.decrementFuel(diff);
                    player.incrementScore();
                    if (player.getFuel() <= 0) { //If player has ran out of fuel then restart game
                        JOptionPane.showMessageDialog(
                                null,
                                "Oh No! You have ran out of fuel!\nYou got " + (player.getScore() - 1) + " points!! \n...restarting game", "Game over", JOptionPane.ERROR_MESSAGE, i); //Display end game stats
                        player.resetPlayer();
                    }
                }
                // Reset the labels after a guess
                guessTextField.setText("");
                srcLabel.setText("");
                destLabel.setText("");
                scoreLabel.setText(String.valueOf(player.getScore()));
                fuelLabel.setText(String.valueOf(player.getFuel()));
                graphGUI.repaint();
            }
        });
//        Button for randomising each guess
        randomiseButton.addActionListener(e -> {
            Random rand = new Random();
            int num = rand.nextInt(graph.numOfVertices());
//            If a source has not been selected yet then the guess will be source
            if(srcLabel.getText().equals("")) {
                srcLabel.setText(String.valueOf(num));
                int[] coords = locations.get(num);
                graphGUI.drawSelection(coords[0], coords[1], Color.red);
            }
//            Otherwise if the destination node has not been selected then the guess will be a destination
            else if(destLabel.getText().equals("")){
                destLabel.setText(String.valueOf(num));
                int[] coords = locations.get(num);
                graphGUI.drawSelection(coords[0], coords[1], Color.green);
            }
        });
//        This action listener is for when the user clears all the labels and sections
        clearLabelsButton.addActionListener(e -> {
            srcLabel.setText("");
            destLabel.setText("");
            graphGUI.repaint();
        });
//        mouse listener is just for selecting planets
        graphPanel.addMouseListener(this);
    }
//    This function generates a set of coordinates for a planet
    public int[] getCoordinates() {
//        Panel dimensions are needed to ensure the planets arent mapped outside the GUI
        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        int[] coor;
//        Will generate the first coordinates of a planet
        if(locations.values().isEmpty()){
            int x = (int) (Math.random() * (panelWidth - PLANET_SIZE));
            int y = (int) (Math.random() * (panelHeight - PLANET_SIZE));
            coor = new int[]{x,y};
            return coor;
        }
        else {
//            If the first planet as been mapped then this code is called
            while (true) {
                int x = (int) (Math.random() * (panelWidth - PLANET_SIZE));
                int y = (int) (Math.random() * (panelHeight - PLANET_SIZE));
                coor = new int[]{x, y};
                boolean coordinatesOverlap = false;
//                Loop through all planets coordinates in the graph
                for (int[] oldCoordinates : locations.values()) {
//                    If the generated coordinates are within 3 planets diameter from it
//                    Then it will call set coordinatesOverlap to be true and will regenerate new coordinates
                    if (!(x > oldCoordinates[0] + PLANET_SIZE*3 || x + PLANET_SIZE*3 < oldCoordinates[0]
                            || y > oldCoordinates[1] + PLANET_SIZE*3 || y + PLANET_SIZE*3 < oldCoordinates[1])
                    ) {
                        coordinatesOverlap = true;
                        break;
                    }
                }
//                If the generated coordinates arent conflicting with other planets
                if (!coordinatesOverlap) {
                    return coor;
                }
            }
        }
    }
//    This function will now generate coordinates for every node/planet
    public void mapCoordinates(Graph graph){
//        For every planet in the graph...
        for(Planet planet : graph.adjacencyList.values()){
//            Get it some coordinates
            int[] coordinates = getCoordinates();
//            Slap those coordinates in the locations with the key being the planet and coordinates the value
            locations.put(planet.getNode(), coordinates);
        }
//        This handsome lil function cost me a few days of time
//        Now it will paint all those planets onto the GUI
        graphGUI.repaint();
    }
//    build game will just map all the planets in the graph
    public void buildGame(){
        mapCoordinates(graph);
    }
//    Set visible should be decoupled from buildGame so that the game can be generated
//    but maybe displayed in the different format
    public void displayGame(){
        graphGUI.setVisible(true);
    }
//    Mouse listener will find where the user clicks and if its a planet or not
    @Override
    public void mouseClicked(MouseEvent e) {
//        Get the coordinates
        int clickedX = e.getX();
        int clickedY = e.getY();
        int[] clickPosition = new int[]{clickedX,clickedY};
        for(Map.Entry<Integer, int[]> planet : locations.entrySet()){
            int[] planetCoordinates = planet.getValue();
//            +planet_size since the coordinates are placed at the top left and the square/hitbox is drawn out from that by its size
            if(((clickPosition[0] >= planetCoordinates[0]) && (clickPosition[0] <= planetCoordinates[0] + PLANET_SIZE)) && ((clickPosition[1] >= planetCoordinates[1]) && (clickPosition[1] <= planetCoordinates[1] + PLANET_SIZE))){
//              If the source has not been filled out
                if(srcLabel.getText().equals("")) {
                    srcLabel.setText(String.valueOf(planet.getKey()));
//                    Calls draw selection to highlight the source planet in red
                    graphGUI.drawSelection(planetCoordinates[0], planetCoordinates[1], Color.red);
                }
//                if the destination has not been selected
                else if(destLabel.getText().equals("")){
                    destLabel.setText(String.valueOf(planet.getKey()));
//                    Calls draw selection to highlight the desintation planet in green
                    graphGUI.drawSelection(planetCoordinates[0], planetCoordinates[1], Color.green);
                }

            }
        }
    }
    //    MouseListener generated methods -- Mostly ignore them
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
