package Game;

import Graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GraphGUI extends JFrame implements MouseListener {
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
    //    TODO: Agree on a size of the game window
    private final int WIDTH = 1000;
    private final int HEIGHT = 800;
    private final int PLANET_SIZE = 30; //Scales the size of the nodes/planets
    public Graphics graphics;
    HashMap<Integer, int[]> locations = new HashMap<>();
    public GraphGUI(Graph graph) {
        setTitle("AstroTraveller");
        mainPanel = new JPanel();
        graphPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                Toolkit tool=Toolkit.getDefaultToolkit();
                Image i=tool.getImage("data/planet.png");
                super.paintComponent(g);
                for(int[] coordinates : locations.values()){
                    g.drawImage(i, coordinates[0], coordinates[1], PLANET_SIZE, PLANET_SIZE, this);
                }
//            Drawing edges
                for (Planet planet : graph.adjacencyList.values()) {
                    HashMap<Integer, Integer> neighbours = planet.getNeighbours();
                    int[] currentPlanetCoordinate = locations.get(planet.getNode());
                    int cpX = currentPlanetCoordinate[0] + (PLANET_SIZE/2);
                    int cpY = currentPlanetCoordinate[1] + (PLANET_SIZE/2);
                    for(int id : neighbours.keySet()){
                        int[] neighbourCoordinates = locations.get(id);
                        int nX = neighbourCoordinates[0] + (PLANET_SIZE/2);
                        int nY = neighbourCoordinates[1] + (PLANET_SIZE/2);
                        int weight = neighbours.get(id);
                        g.setColor(Color.white);
                        g.drawLine(cpX,cpY, nX, nY);
                        g.drawString(Integer.toString(weight), (cpX+ nX) / 2, (cpY + nY)/ 2);
                    }
                }
            }
        };
//        Bottom panel is necessary to hold all the none graph related components, graph panel wasnt enough apprently
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

    public JButton getSubmitButton(){
        return submitButton;
    }
    public JLabel getSrcLabel(){
        return srcLabel;
    }
    public JLabel getDestLabel(){
        return destLabel;
    }
    public JTextField getGuessTextField(){
        return guessTextField;
    }
    public JButton getRandomiseButton(){
        return randomiseButton;
    }
    public JButton getClearLabelsButton(){
        return clearLabelsButton;
    }
    public JPanel getGraphPanel(){
        return graphPanel;
    }
    public Graphics getGraphGraphics(){
        return graphics;
    }
    public int getPlanetSize(){
        return PLANET_SIZE;
    }
//TODO: Custom edge, dashed line? DrawString should say cost followed by weight?

    public void drawSelection(int x, int y, Color color){
        Graphics g = getGraphics();
        g.setColor(color);
        g.drawOval(x, y + PLANET_SIZE, PLANET_SIZE, PLANET_SIZE);
    }
    public int[] getCoordinates() {
        int panelWidth = graphPanel.getWidth();
        int panelHeight = graphPanel.getHeight();
        int[] coor;
        if(locations.values().isEmpty()){
            int x = (int) (Math.random() * (panelWidth - PLANET_SIZE));
            int y = (int) (Math.random() * (panelHeight - PLANET_SIZE));
            coor = new int[]{x,y};
//        For the planet picture instead of just a lame circle
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
        repaint();
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
//                TODO: Add selected planet to the algorithm and srcLabel label
                if(srcLabel.getText().equals("")) {
                    srcLabel.setText(String.valueOf(planet.getKey()));
                    drawSelection(planetCoordinates[0], planetCoordinates[1], Color.red);
                }
                else if(destLabel.getText().equals("")){
                    destLabel.setText(String.valueOf(planet.getKey()));
                    drawSelection(planetCoordinates[0], planetCoordinates[1], Color.green);
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



