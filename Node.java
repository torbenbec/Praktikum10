
import java.util.LinkedList;

public class Node {
    private int id;
    private LinkedList<Edge> edges;
    public Node(int id){
        this.id = id;
        edges = new LinkedList<Edge>();
    }

    public int getId() {
        return id;
    }

    public boolean equals(Node other){
        return other.getId() == id;
    }

    public Edge addEdge(Node dst){
        Edge edge = new Edge(this, dst);
        edges.add(edge);
        return edge;
    }

    public LinkedList<Edge> getEdges(){
        return edges;
    }
}
