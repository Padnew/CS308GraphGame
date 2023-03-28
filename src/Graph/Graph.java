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

//    public Graph(Map<Integer, Planet> objects) {
//        this.adjacencyList = objects;
//    }

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

    //    TODO: Run tests on algo
    //Dijkstra's Algorithm for finding the shortest path between two nodes
    @Override
    public ArrayList<Integer> dijkstraAlgo(int startNode, int endNode) {
        Map<Integer, Integer> distance = new HashMap<>(); //Map where key is a node and value is distance from source
        Map<Integer, Integer> previous = new HashMap<>();
        Map<Integer, Integer> totalWeight = new HashMap<>();
        List<Integer> unvisited = new ArrayList<>(adjacencyList.keySet()); //List of unvisited nodes, initially every node

        //Initialise all nodes distances and total weights
        for (int node : unvisited) {
            distance.put(node, Integer.MAX_VALUE);
            previous.put(node, null);
            totalWeight.put(node, Integer.MAX_VALUE);
        }
        //Distance and total weight of start node is 0
        distance.put(startNode, 0);
        totalWeight.put(startNode, 0);

        // While there are unvisited nodes...
        while (!unvisited.isEmpty()) {
            int current = -1; // Initialise current node
            int smallestDist = Integer.MAX_VALUE; //Initialise shortest distance as infinity
            for (int node : unvisited) {
                if (distance.get(node) < smallestDist) { //Checks if current node has shortest distance
                    current = node;
                    smallestDist = distance.get(node);
                }
            }
            if (current == -1 || smallestDist == Integer.MAX_VALUE) {
                // No more reachable nodes or endNode is not reachable from startNode
                break;
            }
            unvisited.remove(Integer.valueOf(current));
            for (int neighbor : adjacencyList.get(current).getNeighbours().keySet()) { //For every neighbor of current node
                int weight = adjacencyList.get(current).getNeighbours().get(neighbor); // Get weight of edge between curr and neighbor
                int alt = distance.get(current) + weight; // Calculate olternate distance
                if (alt < distance.get(neighbor)) { // If alternate distance is shorter than curr distance
                    distance.put(neighbor, alt); // Update distance in keymap
                    previous.put(neighbor, current); //Update previous node of neighbor
                    totalWeight.put(neighbor, totalWeight.get(current) + weight); // Update total path weight
                }
            }
        }
        //Reconstruct path by working backwards
        ArrayList<Integer> path = new ArrayList<>();
        int current = endNode;
        while (current != startNode && previous.get(current) != null) {
            path.add(current);
            current = previous.get(current);
        }
        if (current == startNode) {
            path.add(current);
            Collections.reverse(path); // Reverse path so it's in the right order
//            System.out.println("Total weight of the path: " + totalWeight.get(endNode));
            path.add(0, totalWeight.get(endNode)); // Adding the weight to the start
        } else {
            path.clear(); // endNode is unreachable from startNode
        }
        return path;
    }
}

