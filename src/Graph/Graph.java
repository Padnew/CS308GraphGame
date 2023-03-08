package Graph;
import Game.Planet;

import java.util.*;

/*
 * Graph will implement the GraphADT interface
 */
public class Graph implements GraphADT {
    public Map<Integer, Planet> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public Graph(Map<Integer, Planet> objects) {
        this.adjacencyList = objects;
    }

    @Override
    public void addNode(int node) {
        if (!adjacencyList.containsKey(node)) {
            adjacencyList.put(node, new Planet(node));
        }
    }

    @Override
    public int numOfVertices() {
        return adjacencyList.size();
    }

    @Override
    public ArrayList<Integer> djikstraAlgo(int startNode, int endNode) {
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();
        List<Integer> unvisited = new ArrayList<>(adjacencyList.keySet()); // list of unvisited nodes

        for (int node : unvisited) {
            distance.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
        }
        distance.put(startNode, 0);

        while (!unvisited.isEmpty()) {
            int current = -1;
            int smallestDist = Integer.MAX_VALUE;
            for (int node : unvisited) {
                if (distance.get(node) < smallestDist) {
                    current = node;
                    smallestDist = distance.get(node);
                }
            }
            if (current == -1) {
                break;
            }
            for (int neighbor : adjacencyList.get(current).getNeighbors().keySet()) {
                int weight = adjacencyList.get(current).getNeighbors().get(neighbor);
                int alt = distance.get(current) + weight;
                if (alt < distance.get(neighbor)) {
                    distance.put(neighbor, alt);
                    previous.put(neighbor, current);
                }
            }
        }
        ArrayList<Integer> path = new ArrayList<>();
        int current = endNode;
        while (current != startNode && previous.get(current) != null) {
            path.add(current);
            current = previous.get(current);
        }
        if (current == startNode) {
            path.add(current);
            Collections.reverse(path);
        } else {
            path.clear(); // endNode is unreachable from startNode
        }
        return path;
    }
}
