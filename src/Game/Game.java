package Game;

import Graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * Game class is the controller and will create an instance of the view(GraphGUI) and model(Universe)
 */
public class Game implements MouseListener {
    Universe universe = new Universe();
    Graph graph = universe.buildGraph(universe.readFile());
    private HashMap<Integer, int[]> locations = new HashMap<>();

    GraphGUI graphGUI = new GraphGUI(graph, locations);
    JButton submitButton;
    JLabel srcLabel;
    JLabel destLabel;
    JTextField guessTextField;
    JButton randomiseButton;
    JButton clearLabelsButton;
    JPanel graphPanel;
    private final int PLANET_SIZE = 30; //Scales the size of the nodes/planets

    public Game() throws Exception {
        submitButton = graphGUI.getSubmitButton();
        srcLabel = graphGUI.getSrcLabel();
        destLabel = graphGUI.getDestLabel();
        guessTextField = graphGUI.getGuessTextField();
        randomiseButton = graphGUI.getRandomiseButton();
        clearLabelsButton = graphGUI.getClearLabelsButton();
        graphPanel = graphGUI.getGraphPanel();
//        g = graphGUI.getGraphGraphics();
        submitButton.addActionListener(e -> {
            // Super easy and not verbose code to display an icon on a message to a scaled size
            ImageIcon i = new ImageIcon("data/rocketIcon.png");
            Image imageVersion = i.getImage();
            Image imageScaled = imageVersion.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            i = new ImageIcon(imageScaled);

            int firstNode = Integer.parseInt(srcLabel.getText());
            int secondNode = Integer.parseInt(destLabel.getText());
            // If the guess is successful
            if(graph.dijkstraAlgo(firstNode,secondNode).get(0) == Integer.parseInt(guessTextField.getText())){
                JOptionPane.showMessageDialog(null,
                        "Correct!\nTotal weight = " +
                                graph.dijkstraAlgo(firstNode,secondNode).get(0)
                                +"\nYour guess = " + Integer.parseInt(guessTextField.getText()), "Congrats!", JOptionPane.ERROR_MESSAGE, i);
            }
            // If the guess is unsuccessful
            else{
                JOptionPane.showMessageDialog(null,
                        "Aw unlucky!\nTotal weight = " +
                                graph.dijkstraAlgo(firstNode,secondNode).get(0)
                                +"\nYour guess = " + Integer.parseInt(guessTextField.getText()),"Unlucky!", JOptionPane.ERROR_MESSAGE, i);

            }
            // Reset the labels after a guess
            // TODO: Consider option pane to reset values or not
            guessTextField.setText("");
            srcLabel.setText("");
            destLabel.setText("");
        });
        randomiseButton.addActionListener(e -> {
            Random rand = new Random();
            int num = rand.nextInt(graph.numOfVertices());
            if(srcLabel.getText().equals("")) {
                srcLabel.setText(String.valueOf(num));
            }
            else if(destLabel.getText().equals("")){
                destLabel.setText(String.valueOf(num));
            }
        });
//        This action listener is for when the press submit
        clearLabelsButton.addActionListener(e -> {
            srcLabel.setText("");
            destLabel.setText("");
        });
//        mouse listener is just for selecting planets
        graphPanel.addMouseListener(this);
    }
    public int[] getCoordinates() {
        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        int[] coor;
        if(locations.values().isEmpty()){
            int x = (int) (Math.random() * (panelWidth - PLANET_SIZE));
            int y = (int) (Math.random() * (panelHeight - PLANET_SIZE));
            coor = new int[]{x,y};
            return coor;
        }
        else {
//            Should generate coordinates which dont overlap with another planet
            while (true) {
                int x = (int) (Math.random() * (panelWidth - PLANET_SIZE));
                int y = (int) (Math.random() * (panelHeight - PLANET_SIZE));
                coor = new int[]{x, y};
                boolean coordinatesOverlap = false;
                for (int[] oldCoordinates : locations.values()) {
                    if (!(x > oldCoordinates[0] + PLANET_SIZE*3 || x + PLANET_SIZE*3 < oldCoordinates[0]
                            || y > oldCoordinates[1] + PLANET_SIZE*3 || y + PLANET_SIZE*3 < oldCoordinates[1])
                    ) {
                        coordinatesOverlap = true;
                        break;
                    }
                }
                if (!coordinatesOverlap) {
                    return coor;
                }
            }
        }
    }
    public void mapCoordinates(Graph graph){
        //                Drawing planets
        for(Planet planet : graph.adjacencyList.values()){
            int[] coordinates = getCoordinates();
            locations.put(planet.getNode(), coordinates);
        }
//        This handsome lil function cost me a few days of time
        graphGUI.repaint();
    }

    public void displayGame(){
        mapCoordinates(graph);
        graphGUI.setVisible(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int clickedX = e.getX();
        int clickedY = e.getY();
        int[] clickPosition = new int[]{clickedX,clickedY};
        for(Map.Entry<Integer, int[]> planet : locations.entrySet()){
            int[] planetCoordinates = planet.getValue();
//            +planet_size since the coordinates are placed at the top left and the square/hitbox is drawn out from that by its size
            if(((clickPosition[0] >= planetCoordinates[0]) && (clickPosition[0] <= planetCoordinates[0] + PLANET_SIZE)) && ((clickPosition[1] >= planetCoordinates[1]) && (clickPosition[1] <= planetCoordinates[1] + PLANET_SIZE))){
//                TODO: Add selected planet to the algorithm and srcLabel label
                if(srcLabel.getText().equals("")) {
                    srcLabel.setText(String.valueOf(planet.getKey()));
                    graphGUI.drawSelection(planetCoordinates[0], planetCoordinates[1], Color.red);
                }
                else if(destLabel.getText().equals("")){
                    destLabel.setText(String.valueOf(planet.getKey()));
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
