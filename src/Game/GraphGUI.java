package Game;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GraphGUI extends JFrame{
    private JPanel mainPanel;
//    private final HashMap<Integer, Integer> graph = new HashMap<>();

//    public GraphGUI(HashMap<Integer, Integer> graph){
    public GraphGUI(){
        setTitle("AstroTraveller");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800,600));
        this.pack();
    }
    public void initGraph(){
        JFrame GUI = new GraphGUI();
        GUI.setVisible(true);
    }
}
