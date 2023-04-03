package Tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Test;

import Game.GraphGUI;
import Graph.Graph;


public class graphGuiTests {

// set up initial values for testing
Graph graph = new Graph();
// used in gui initialisation , same as gui class
HashMap<Integer, int[]> locations;
// implemented for using getter method in testing
GraphGUI gui = new GraphGUI(graph, locations);
JLabel label;
JPanel panel = new JPanel();
JButton button;
JTextField textField;
Graphics g;


// Testing gui button features are present and labels are correct, rather than their functionality
@Test
public void testSubmit() {
    button = gui.getSubmitButton();
    assertEquals("Submit", button.getText());
    assertNotNull(button);
}

@Test
public void testRandomise() {
    button = gui.getRandomiseButton();
    assertNotNull(button);
    assertEquals("Randomise", button.getText());
}

@Test
public void testClear() {
    button = gui.getClearLabelsButton();
    assertNotNull(button);
    assertEquals("Clear selections", button.getText());
}

// Testing gui labels are present and correct on the display
@Test
public void testSrcLabel() {
    label = gui.getSrcLabel();
    assertNotNull(label);
}

@Test
public void testDstLabel() {
    label = gui.getDestLabel();
    assertNotNull(label);
}

@Test
public void testGuessLabel() {
    label = gui.getGuessLabel();
    assertNotNull(label);

}

@Test
public void testScoreLabel() {
    label = gui.getScoreLabel();
    assertNotNull(label);

}

@Test
public void testFuelLabel() {
    label = gui.getFuelLabel();
    assertNotNull(label);
}

@Test
public void testGraphPanel() {
    panel = gui.getGraphPanel();
    assertNotNull(panel);
}

@Test
public void testTextField() {
    textField = gui.getGuessTextField();
    assertNotNull(textField);
}

}