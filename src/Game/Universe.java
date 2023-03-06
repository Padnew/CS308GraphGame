package Game;
import Graph.Graph;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

/*
* Universe is the model in the MVC format, it will implement the graph class as well as the planet class
*/
public class Universe {
    ArrayList<Planet> planets = new ArrayList<>();
    Graph graph = new Graph();
    public ArrayList<Planet> readFile() throws Exception{
        File file = new File("data/graphData.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        int source;
        int destination;
        int weight;
        while ((st = br.readLine()) != null){
//            0 1 {'weight': 7}
//            (0 47 {'weight': 3})
            source = Integer.parseInt(st.substring(0,st.indexOf(' ')));
            destination = Integer.parseInt(st.substring(st.indexOf(' ')+1,st.indexOf(' ', st.indexOf(" ")+1)));
            weight = Integer.parseInt(st.substring(st.indexOf("'weight': ") + 10, st.indexOf("}", st.indexOf("'weight': "))));
            Planet planet = null;
            for (Planet p : planets) {
                if (p.getNode() == source) {
                    planet = p;
                    break;
                }
            }
            if (planet == null) {
                planet = new Planet(source);
                planets.add(planet);
            }
            planet.addNeighbor(destination, weight);
            }
//        for(Planet p : planets){
//            System.out.println(p.getNode());
//            System.out.println(p.getNeighbors());
//        }
        return planets;
    }

    public Graph buildGraph(ArrayList<Planet> planets){
        Graph graph = new Graph();

        for (Planet planet : planets) {
            graph.addNode(planet.getNode());
            graph.adjacencyList.put(planet.getNode(), planet);
        }

        for (Planet planet : planets) {
            for (Map.Entry<Integer, Integer> neighbor : planet.getNeighbors().entrySet()) {
                graph.adjacencyList.get(planet.getNode()).addNeighbor(neighbor.getKey(), neighbor.getValue());
            }
        }

        return graph;
    }

}