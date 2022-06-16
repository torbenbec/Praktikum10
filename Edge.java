
public class Edge {
    private Node src;
    private Node dst;

    public Edge(Node src, Node dst){
        this.src = src;
        this.dst = dst;
    }

    public Node getSrc() {
        return src;
    }

    public Node getDst() {
        return dst;
    }
}
