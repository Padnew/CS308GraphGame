package Game;

import Graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/*
 * Game class is the controller and will create an instance of the view(GraphGUI) and model(Universe)
 */
public class Game {
    Universe universe = new Universe();
    Graph graph = universe.buildGraph(universe.readFile());
    JButton submitButton;
    JLabel srcLabel;
    JLabel destLabel;
    JTextField guessTextField;
    JButton randomiseButton;
    JButton clearLabelsButton;
    JPanel graphPanel;

//    Throws an exception, because... like it has to?
//    public Game() throws Exception {
//    }
GraphGUI graphGUI = new GraphGUI(graph);

    public Game() throws Exception {
        submitButton = graphGUI.getSubmitButton();
        srcLabel = graphGUI.getSrcLabel();
        destLabel = graphGUI.getDestLabel();
        guessTextField = graphGUI.getGuessTextField();
        randomiseButton = graphGUI.getRandomiseButton();
        clearLabelsButton = graphGUI.getClearLabelsButton();
        graphPanel = graphGUI.getGraphPanel();
        submitButton.addActionListener(e -> {
            // Super easy and not verbose code to display an icon on a message to a scaled size
            ImageIcon i = new ImageIcon("data/rocketIcon.png");
            Image imageVersion = i.getImage();
            Image imageScaled = imageVersion.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            i = new ImageIcon(imageScaled);

            int firstNode = Integer.parseInt(srcLabel.getText());
            int secondNode = Integer.parseInt(destLabel.getText());
            // If the guess is successful
            if(graph.dijkstraAlgo(firstNode,secondNode).get(0) == Integer.parseInt(guessTextField.getText())){
                JOptionPane.showMessageDialog(null,
                        "Correct!\nTotal weight = " +
                                graph.dijkstraAlgo(firstNode,secondNode).get(0)
                                +"\nYour guess = " + Integer.parseInt(guessTextField.getText()), "Congrats!", JOptionPane.ERROR_MESSAGE, i);
            }
            // If the guess is unsuccessful
            else{
                JOptionPane.showMessageDialog(null,
                        "Aw unlucky!\nTotal weight = " +
                                graph.dijkstraAlgo(firstNode,secondNode).get(0)
                                +"\nYour guess = " + Integer.parseInt(guessTextField.getText()),"Unlucky!", JOptionPane.ERROR_MESSAGE, i);

            }
            // Reset the labels after a guess
            // TODO: Consider option pane to reset values or not
            guessTextField.setText("");
            srcLabel.setText("");
            destLabel.setText("");
        });
        randomiseButton.addActionListener(e -> {
            Random rand = new Random();
            int num = rand.nextInt(graph.numOfVertices());
            if(srcLabel.getText().equals("")) {
                srcLabel.setText(String.valueOf(num));
            }
            else if(destLabel.getText().equals("")){
                destLabel.setText(String.valueOf(num));
            }
        });
//        This action listener is for when the press submit
        clearLabelsButton.addActionListener(e -> {
            srcLabel.setText("");
            destLabel.setText("");
        });
//        mouse listener is just for selecting planets
        graphPanel.addMouseListener(graphGUI);
    }
    public void displayGame(){
        graphGUI.setVisible(true);
    }
}
