package Game;

import Graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GraphGUI extends JFrame implements MouseListener {
    private JPanel mainPanel;
    private JPanel graphPanel;
    private JLabel backgroundLabel;
    private final int WIDTH = 1000;
    private final int HEIGHT = 800;
    private final int PLANET_SIZE = 25;
    private HashMap<Integer, int[]> locations = new HashMap<>();
    public GraphGUI(Graph graph) {
        setTitle("AstroTraveller");
        graphPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
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

        //        backgroundLabel = new JLabel(new ImageIcon(new javax.swing.ImageIcon("data/Background.png").getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
//        backgroundLabel.setLayout( new GridBagLayout() );
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(backgroundLabel);
        graphPanel.setBackground(Color.BLACK);
        graphPanel.addMouseListener(this);
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

        int x = (int) (Math.random() * (panelWidth - PLANET_SIZE));
        int y = (int) (Math.random() * (panelHeight - PLANET_SIZE));
        int[] coor = new int[]{x,y};
//        For the planet picture instead of just a lame circle/planet
        Toolkit tool=Toolkit.getDefaultToolkit();
        Image i=tool.getImage("data/planet.png");
        g.drawImage(i, x, y, PLANET_SIZE, PLANET_SIZE, this);
//        g.setColor(Color.MAGENTA);
//        g.fillOval(x, y, PLANET_SIZE, PLANET_SIZE);
        return coor;
    }

    public void drawEdge(Graphics2D g, int cpX, int cpY, int nX, int nY, int weight){
        g.setColor(Color.white);
        g.drawLine(cpX+(PLANET_SIZE/2), cpY+(PLANET_SIZE/2), nX+(PLANET_SIZE/2), nY+(PLANET_SIZE/2));
        g.drawString(Integer.toString(weight), (cpX+ nX) / 2, (cpY + nY)/ 2);
    }

    public void initGraph(Graph graph) {
        GraphGUI GUI = new GraphGUI(graph);
        GUI.setVisible(true);
    }

//Mouse clicky on the planet causes huge massive things to happen wow very cool
    @Override
    public void mouseClicked(MouseEvent e) {
        int clickedX = e.getX();
        int clickedY = e.getY();
        int[] clickPosition = new int[]{clickedX,clickedY};
        for(Map.Entry<Integer, int[]> planet : locations.entrySet()){
            int[] planetCoordinates = planet.getValue();
            if(((clickPosition[0] >= planetCoordinates[0]) && (clickPosition[0] <= planetCoordinates[0] + PLANET_SIZE)) && ((clickPosition[1] >= planetCoordinates[1]) && (clickPosition[1] <= planetCoordinates[1] + PLANET_SIZE))){
                System.out.println("You selected the planet " + planet.getKey());
            }
        }
    }
//    Ignore these for now
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}



