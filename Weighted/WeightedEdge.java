
public class WeightedEdge {
    private Node src;
    private Node dst;
    private int weight;

    public WeightedEdge(Node src, Node dst, int weight){
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public Node getSrc() {
        return src;
    }

    public Node getDst() {
        return dst;
    }
    public int getWeight(){
    	return weight;
    }
}
