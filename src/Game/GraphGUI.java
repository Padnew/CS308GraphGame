package Game;

import Graph.Graph;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GraphGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel graphPanel;
    private JLabel backgroundLabel;

    public GraphGUI(Graph graph) {
        setTitle("AstroTraveller");

        // Create a new JLabel to hold the background image
//        backgroundLabel = new JLabel(new ImageIcon(new javax.swing.ImageIcon("data/Background.png").getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));

        // Create a new JPanel to hold the circle and the background image
        graphPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
            for(Planet planet : graph.adjacencyList.values()){
                    drawPlanet((Graphics2D) g);
                }
//                for (Planet planet : graph.adjacencyList.values()) {
//                    HashMap<Integer, Integer> neighbours = planet.getNeighbours();
//                    if (neighbours != null) {
//                        for (Planet neighbour : ) {
//                            if (neighbour != null) {
//                                g.drawLine(x1, y1, x2, y2);
//                            }
//                        }
//                    }
//                }
            };
        };

        // Set the layout of the mainPanel and add the graphPanel and backgroundLabel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(graphPanel, BorderLayout.CENTER);
        mainPanel.add(backgroundLabel, BorderLayout.PAGE_START);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(800, 600));
        pack();
    }

    public void drawPlanet(Graphics2D g) {
        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        int circleSize = 20;

        int x = (int) (Math.random() * (panelWidth - circleSize));
        int y = (int) (Math.random() * (panelHeight - circleSize));

        g.drawOval(x, y, circleSize, circleSize);
        g.setColor(Color.BLUE);
        g.fillOval(x, y, circleSize, circleSize);
    }

    public void initGraph(Graph graph) {
        GraphGUI GUI = new GraphGUI(graph);
        GUI.setVisible(true);
    }
}



