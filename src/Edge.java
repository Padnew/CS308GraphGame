/**
 * A definition of an edge, to be used within the adjacency list
 * representing the graph
 */
public class Edge{
    int destination;
    int weight;

    Edge(int destination, int weight){
        this.destination = destination;
        this.weight = weight;
    }

    // getter methods used in the graph adt class

    public int getEdge() {
        return this.destination;
    }

    public int getWeight() {
        return this.weight;
    }

}
