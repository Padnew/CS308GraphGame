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

// Note -> Tests for the gui drawing colours around nodes are done on the document testing , rather than code 


// set up initial values for testing
Graph graph = new Graph();
// used in gui initialisation , same as gui class
HashMap<Integer, int[]> locations;
// implemented for using getter method in testing
GraphGUI gui = new GraphGUI(graph, locations);
// using in label testing as not to redifine repeatetly
JLabel label;
JPanel panel = new JPanel();
// used in button testing as not to redefine repeatetly
JButton button;
JTextField textField;
Graphics g;


// Testing gui button features are present and labels name on the gui are correct, rather than their functionality
@Test
public void testSubmit() {
    button = gui.getSubmitButton();
    assertEquals("Submit", button.getText()); // tests the text of the button is "Submit"
    assertNotNull(button); // check not null
}

@Test
public void testRandomise() {
    button = gui.getRandomiseButton();
    assertNotNull(button); // check not null
    assertEquals("Randomise", button.getText()); // tests the text of the button is "Randomise"
}

@Test
public void testClear() {
    button = gui.getClearLabelsButton();
    assertNotNull(button); // check not null
    assertEquals("Clear selections", button.getText()); // tests the text of the button is "Clear selections"
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