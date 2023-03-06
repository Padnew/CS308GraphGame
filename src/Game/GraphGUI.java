package Game;
import javax.swing.*;
import java.awt.*;

public class GraphGUI extends JFrame{
    private JPanel mainFrame;
    private JTextField userInputTextField;
    private JButton submitButton;
    private JLabel selectedNodeLabel;
    private JPanel graphPanel;

    public GraphGUI(){
        setTitle("AstroTraveller");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainFrame);
        this.setPreferredSize(new Dimension(800,600));
        this.pack();
    }

    public void initGraph(){
        JFrame GUI = new GraphGUI();
        GUI.setVisible(true);
    }
}