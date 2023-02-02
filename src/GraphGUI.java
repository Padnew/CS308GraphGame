import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphGUI extends JFrame{
    private JPanel mainPanel;

    public GraphGUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }
    public static void main(String[] args) {
        JFrame frame = new GraphGUI("Graph Game");
        frame.setVisible(true);
    }
}
