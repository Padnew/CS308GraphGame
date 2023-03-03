package Game;

import javax.swing.*;
import java.util.HashMap;

public class GraphGUI extends JFrame{
    private JPanel mainPanel;
//    private final HashMap<Integer, Integer> graph = new HashMap<>();
//    private final int width = 800;
//    private final int height = 600;
//    public GraphGUI(HashMap<Integer, Integer> graph){
    public GraphGUI(){
        setTitle("AstroTraveller");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }
    public void initGraph(){
        JFrame GUI = new GraphGUI();
        GUI.setVisible(true);
    }
}
