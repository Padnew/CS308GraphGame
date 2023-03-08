package Game;

import Graph.Graph;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.HashMap;

public class GraphGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel graphPanel;
    private JLabel backgroundLabel;
    private final int WIDTH = 1000;
    private final int HEIGHT = 800;
    private final int CIRCLE_SIZE = 20;
    public GraphGUI(Graph graph) {
        setTitle("AstroTraveller");
        // Create a new JLabel to hold the background image
//        backgroundLabel = new JLabel(new ImageIcon(new javax.swing.ImageIcon("data/Background.png").getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
//        backgroundLabel.setLayout( new GridBagLayout() );
        // Create a new JPanel to hold the circle and the background image
        graphPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                HashMap<Integer, int[]> locations = new HashMap<>();
//                Drawing planets
            for(Planet planet : graph.adjacencyList.values()){
                    int[] coordinates = drawPlanet((Graphics2D) g);
                    locations.put(planet.getNode(), coordinates);
                }
//            Drawing edges
                for (Planet planet : graph.adjacencyList.values()) {
                    HashMap<Integer, Integer> neighbours = planet.getNeighbours();
                    int[] currentPlanetCoordinate = locations.get(planet.getNode());
                    for(int id : neighbours.keySet()){
                        int[] neighbourCoordinates = locations.get(id);
                        int weight = neighbours.get(id);
                        drawEdge((Graphics2D) g, currentPlanetCoordinate[0],currentPlanetCoordinate[1], neighbourCoordinates[0], neighbourCoordinates[1], weight);
                    }
                }
            }
        };

        // Set the layout of the mainPanel and add the graphPanel and backgroundLabel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(backgroundLabel);
        graphPanel.setBackground(Color.BLACK);
//        backgroundLabel.add(graphPanel);
        mainPanel.add(graphPanel, BorderLayout.CENTER);
        mainPanel.add(backgroundLabel, BorderLayout.PAGE_START);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
    }

    public int[] drawPlanet(Graphics2D g) {
        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();

        int x = (int) (Math.random() * (panelWidth - CIRCLE_SIZE));
        int y = (int) (Math.random() * (panelHeight - CIRCLE_SIZE));
        int[] coor = new int[]{x,y};
        g.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
        g.setColor(Color.red);
        g.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
        return coor;
    }

    public void drawEdge(Graphics2D g, int cpX, int cpY, int nX, int nY, int weight){
        g.setColor(Color.white);
        g.drawLine(cpX+(CIRCLE_SIZE/2), cpY+(CIRCLE_SIZE/2), nX+(CIRCLE_SIZE/2), nY+(CIRCLE_SIZE/2));
        g.drawString(Integer.toString(weight), (cpX+ nX) / 2, (cpY + nY)/ 2);
    }

    public void initGraph(Graph graph) {
        GraphGUI GUI = new GraphGUI(graph);
        GUI.setVisible(true);
    }
}



