
import java.util.LinkedList;

public class Node {
    private int id;
    private LinkedList<WeightedEdge> edges;
    public Node(int id){
        this.id = id;
        edges = new LinkedList<WeightedEdge>();
    }

    public int getId() {
        return id;
    }

    public boolean equals(Node other){
        return other.getId() == id;
    }

    public WeightedEdge addEdge(Node dst, int weight){
        WeightedEdge edge = new WeightedEdge(this, dst, weight);
        edges.add(edge);
        return edge;
    }

    public LinkedList<WeightedEdge> getEdges(){
        return edges;
    }
}
