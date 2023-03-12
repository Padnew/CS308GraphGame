package Game;

import Graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class GraphGUI extends JFrame implements MouseListener {
    private final JPanel mainPanel;
    private JPanel graphPanel;
    private JLabel backgroundLabel;
    private JTextField guessTextField;
    private JButton submitButton;
    private JLabel guessLabel;
    private JLabel selectedLabel;
    private JLabel selectedNode;
    //    TODO: Agree on a size of the game window
    private final int WIDTH = 1000;
    private final int HEIGHT = 800;
    private final int PLANET_SIZE = 30; //Scales the size of the nodes/planets
    private HashMap<Integer, int[]> locations = new HashMap<>();
    public GraphGUI(Graph graph) {
//        TODO: Come up with a better name for the game lol
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

        mainPanel = new JPanel();
        //        Bottom panel is necessary to hold all the none graph related components, graph panel wasnt enough apprently
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        guessLabel = new JLabel("Guess:");
        selectedLabel = new JLabel("Selected node: ");
        selectedNode = new JLabel("");
        guessTextField = new JTextField(3);
        submitButton = new JButton("Submit");
        mainPanel.setLayout(new BorderLayout());
        graphPanel.setBackground(Color.BLACK);
//        Adding components to their respective places
        mainPanel.add(graphPanel);
//        The order these are added are left to right (Order is important)
        bottomPanel.add(guessLabel);
        bottomPanel.add(guessTextField);
        bottomPanel.add(submitButton);
        bottomPanel.add(selectedLabel);
        bottomPanel.add(selectedNode);
//        Adds the bottom panl to the main panel
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

//        This action listener is for when the press submit
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = Integer.parseInt(selectedNode.getText());
                System.out.println(selected);
//                System.out.println(graph.djikstraAlgo(0, output));
            }
        });
//        mouse listener is just for selecting planets
        graphPanel.addMouseListener(this);
//        Boilerplate Java gui stuff
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        pack();
    }
    public int[] drawPlanet(Graphics2D g) {
        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        Toolkit tool=Toolkit.getDefaultToolkit();
        Image i=tool.getImage("data/planet.png");
        int x = 0;
        int y = 0;
        int[] coor;
        if(locations.values().isEmpty()){
            x = (int) (Math.random() * (panelWidth - PLANET_SIZE));
            y = (int) (Math.random() * (panelHeight - PLANET_SIZE));
            coor = new int[]{x,y};
//        For the planet picture instead of just a lame circle
            g.drawImage(i, x, y, PLANET_SIZE, PLANET_SIZE, this);
            return coor;
        }
        else {
//            Should generate coordinates which dont overlap with another planet
            while (true) {
                x = (int) (Math.random() * (panelWidth - PLANET_SIZE));
                y = (int) (Math.random() * (panelHeight - PLANET_SIZE));
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
                    g.drawImage(i, x, y, PLANET_SIZE, PLANET_SIZE, this);
                    return coor;
                }
            }
        }
    }
//TODO: Custom edge, dashed line? DrawString should say cost followed by weight?
    public void drawEdge(Graphics2D g, int cpX, int cpY, int nX, int nY, int weight){
        g.setColor(Color.white);
        g.drawLine(cpX+(PLANET_SIZE/2), cpY+(PLANET_SIZE/2), nX+(PLANET_SIZE/2), nY+(PLANET_SIZE/2));
//        Places the weight in the middle of the line from the source to dest using a lil pythagorus equation
//        g.drawString(Integer.toString(weight), (cpX+ nX) / 2, (cpY + nY)/ 2);
    }

    public void initGraph(Graph graph) {
        GraphGUI GUI = new GraphGUI(graph);
        GUI.setVisible(true);
    }

//Mouse clicky on the planet causes huge massive things to happen
    @Override
    public void mouseClicked(MouseEvent e) {
        int clickedX = e.getX();
        int clickedY = e.getY();
        int[] clickPosition = new int[]{clickedX,clickedY};
        for(Map.Entry<Integer, int[]> planet : locations.entrySet()){
            int[] planetCoordinates = planet.getValue();
//            +planet_size since the coordinates are placed at the top left and the square/hitbox is drawn out from that by its size
            if(((clickPosition[0] >= planetCoordinates[0]) && (clickPosition[0] <= planetCoordinates[0] + PLANET_SIZE)) && ((clickPosition[1] >= planetCoordinates[1]) && (clickPosition[1] <= planetCoordinates[1] + PLANET_SIZE))){
//                TODO: Add selected planet to the algorithm and selectedNode label
                selectedNode.setText(String.valueOf(planet.getKey()));
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



