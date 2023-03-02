package Game;

import javax.swing.*;

public class GraphGUI extends JFrame{
    private JPanel mainPanel;
//    private final HashMap<Integer, Integer> graph = new HashMap<>();
//    private final int width = 800;
//    private final int height = 600;

    public GraphGUI(){
        setTitle("AstroTraveller");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }
    public static void main(String[] args) {
        JFrame GUI = new GraphGUI();
        GUI.setVisible(true);
    }
}
