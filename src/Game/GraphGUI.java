package Game;
import javax.swing.*;
import java.awt.*;

public class GraphGUI extends JFrame{
    private JPanel mainFrame;
    private JTextField userInputTextField;
    ImageIcon icon = new ImageIcon("data/Background.png");
    private JButton submitButton;
    private JLabel selectedNodeLabel;
    private JPanel graphPanel;
    private JLabel backgroundLabel;

    public GraphGUI(){
        setTitle("AstroTraveller");
        backgroundLabel.setIcon(new ImageIcon(new javax.swing.ImageIcon("data/Background.png").getImage().getScaledInstance(700, 500, Image.SCALE_SMOOTH)));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainFrame);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(800,600));
        this.pack();
    }

    public void initGraph(){
        JFrame GUI = new GraphGUI();
        GUI.setVisible(true);
    }
}