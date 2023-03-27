package Game;

import Graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GraphGUI extends JFrame {
    private final JPanel mainPanel;
    private JPanel graphPanel;
    private JLabel backgroundLabel;
    private JButton clearLabelsButton;
    private JTextField guessTextField;
    private JButton submitButton;
    private JButton randomiseButton;
    private JLabel guessLabel;
    private JLabel selectedLabel;
    private JLabel srcLabel;
    private JLabel destLabel;
    private final int WIDTH = 1000;
    private final int HEIGHT = 800;
    private final int PLANET_SIZE = 30; //Scales the size of the nodes/planets

    public GraphGUI(Graph graph, HashMap<Integer, int[]> locations) {
        setTitle("AstroTraveller");
//        Generate the main body of the GUI
        mainPanel = new JPanel();
        graphPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
//                All drawing is fully dependant on the locations Hashmap from the Game class
//                So repaint will be called after the locations hashmap is generated
//                Repaint will essentially just draw everything again onto the graph panel
                Toolkit tool = Toolkit.getDefaultToolkit();
                Image i = tool.getImage("data/planet.png");
                super.paintComponent(g);
//            Drawing edges first
                for (Planet planet : graph.adjacencyList.values()) {
                    HashMap<Integer, Integer> neighbours = planet.getNeighbours();
                    int[] currentPlanetCoordinate = locations.get(planet.getNode());
//                    Adjusting the coordinates since java swing is silly and maps images/ovals
//                    to the top left in a square, so the lines will meet in the middle of the image
                    int cpX = currentPlanetCoordinate[0] + (PLANET_SIZE / 2);
                    int cpY = currentPlanetCoordinate[1] + (PLANET_SIZE / 2);
//                    For every neighbour of this current planet
                    for (int id : neighbours.keySet()) {
                        int[] neighbourCoordinates = locations.get(id);
//                        + planet_size/2 is to get the exact centre of each node
                        int nX = neighbourCoordinates[0] + (PLANET_SIZE / 2);
                        int nY = neighbourCoordinates[1] + (PLANET_SIZE / 2);
//                        get the value of the key; the weight of the edge between the source and neighbour
                        int weight = neighbours.get(id);
                        g.setColor(Color.white);
                        g.drawLine(cpX, cpY, nX, nY);
//                        This will place the weight of the line directly in the middle of the two planets on the edge
                        g.drawString(Integer.toString(weight), (cpX + nX) / 2, (cpY + nY) / 2);
                    }
                }
//                Draw planets on top of the lines
                for (int[] coordinates : locations.values()) {
                    g.drawImage(i, coordinates[0], coordinates[1], PLANET_SIZE, PLANET_SIZE, this);
                }
            }
        };
//        Bottom panel is necessary to hold all the none graph related components, will get attached to graph panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        guessLabel = new JLabel("Guess:");
        selectedLabel = new JLabel("Selected node: ");
        srcLabel = new JLabel("");
        destLabel = new JLabel("");
        randomiseButton = new JButton("Randomise");
        guessTextField = new JTextField(3);
        submitButton = new JButton("Submit");
        mainPanel.setLayout(new BorderLayout());
        graphPanel.setBackground(Color.BLACK);
        clearLabelsButton = new JButton("Clear selections");
//        Adding components to the bottom panel
//        TODO: Split bottom panel into left and right panel for controls
        mainPanel.add(graphPanel);
        bottomPanel.add(guessLabel);
        bottomPanel.add(guessTextField);
        bottomPanel.add(submitButton);
        bottomPanel.add(selectedLabel);
        bottomPanel.add(srcLabel);
        bottomPanel.add(destLabel);
        bottomPanel.add(randomiseButton);
        bottomPanel.add(clearLabelsButton);
//        Adds the bottom panl to the main panel
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
//        Boilerplate Java gui stuff
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
    }

//getters for each component the Game class needs to add action listeners or interract with
    public JButton getSubmitButton() {
        return submitButton;
    }

    public JLabel getSrcLabel() {
        return srcLabel;
    }

    public JLabel getDestLabel() {
        return destLabel;
    }

    public JTextField getGuessTextField() {
        return guessTextField;
    }

    public JButton getRandomiseButton() {
        return randomiseButton;
    }

    public JButton getClearLabelsButton() {
        return clearLabelsButton;
    }

    public JPanel getGraphPanel() {
        return graphPanel;
    }
//This will get passed coordinates from the mouseClicked function from Game.Java and draw a circle round it
    public void drawSelection(int x, int y, Color color) {
        Graphics g = getGraphics();
        g.setColor(color);
        g.drawOval(x, y + PLANET_SIZE, PLANET_SIZE, PLANET_SIZE);
    }

}



