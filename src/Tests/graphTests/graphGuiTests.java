package Tests.graphTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Test;

import Game.GraphGUI;
import Graph.*;

/// Note -> I would like to write assertEqual tests for checking the actual values of labels, this will be done in Game ///


public class graphGuiTests {

    // initialise a new graph
    Graph graph = new Graph();
    // using locations hashmap, same as graphGUI
    HashMap<Integer, int[]> locations = new HashMap<>();
    GraphGUI gui = new GraphGUI(graph, locations);
    JPanel panel = gui.getGraphPanel();
    // Intended for use in draw section tests
    Graphics graphic;

    @Test 
    // test a graph is generated
    public void testGraphGenerated() {
        assertNotNull(gui);
    }

    // test that the game panel (graph has nodes, planets) is not null
    @Test
    public void testPanelNotNull() {
        assertNotNull(gui.getGraphPanel());
    }

    @Test
    // test that the graph panel is not null and is displayed
    public void testGraphPanels() {
        assertNotNull(panel);
        assertTrue(panel.isVisible());
    }

    @Test
    public void testDrawSelection() {
        graphic = gui.getGraphics();
        assertNotNull(graphic);

        // i need do some colour tests in here
    }

    // tesing guess label is not null when value entered
    // I believe this is a useless test from the way the text is configured
    @Test
    public void testingGuessLabel() {
        JLabel labelTest = gui.getGuessLabel();
        assertNotNull(labelTest);
    }

    // Testting the source label is not null and that is visible in the gui frame
    @Test
    public void testingSourceLabel() {
        JLabel src = gui.getSrcLabel();
        assertNotNull(src);
        assertTrue(src.isVisible());
    }

    // Testting the destination label is not null and visible in the gui frame
    @Test
    public void testingDestinationLabel() {
        JLabel dest = gui.getDestLabel();
        assertNotNull(dest);
        assertTrue(dest.isVisible());
    }

    // Testing the text label is not null and visiible in gui frame
    @Test
    public void testingTextLabel() {
        JTextField textField = gui.getGuessTextField();
        assertNotNull(textField);
        assertTrue(textField.isVisible());
        assertTrue(textField.isEditable());
    }

    // Testing the randomise button is not null , is in use with enabled and that the JButton text is  Randomise
    @Test
    public void testingRandomise() {
        JButton randomButton = gui.getRandomiseButton();
        assertEquals("Randomise", randomButton.getText());
        assertNotNull(randomButton);
        assertTrue(randomButton.isEnabled());
    }

    @Test
    // Testing the clear button is not null, the text label is Clear Sections and that it is use with enabled
    public void testingClearButtons() {
        JButton clearLabelButton = gui.getClearLabelsButton();
        assertEquals("Clear selections", clearLabelButton.getText());
        assertNotNull(clearLabelButton);
        assertTrue(clearLabelButton.isEnabled());
    }




    
}
