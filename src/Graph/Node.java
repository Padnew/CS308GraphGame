package Graph;

import java.util.HashMap;

public interface Node {
    int getNode();
    void addNeighbour(int neighbour, int weight);
    HashMap<Integer, Integer> getNeighbours();
}
