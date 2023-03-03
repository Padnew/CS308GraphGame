package Graph;

import java.util.ArrayList;
import java.util.HashMap;

public interface Node {
    int getNode();
    void addNeighbor(int neighbour, int weight);
    HashMap<Integer, Integer> getNeighbors();
}
