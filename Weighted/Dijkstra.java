import java.util.*;
public class Dijkstra {
    public static void main(String[] args) {
        String path = args[0];
	int s = 0;
        int t = 0;
        try {
            s = Integer.parseInt(args[1]);
            t = Integer.parseInt(args[2]);
        } catch (NumberFormatException e){
            System.out.println("Eingabe war nicht eine Zahl");
        }
        WeightedGraph graph = WeightedGraph.fromFile(path);
	int ergebniss=dijkstra( graph,  s,  t);
	if(ergebniss==Integer.MAX_VALUE){
		System.out.println("Es gibt keinen Pfad von Knoten "+s+" zu Knoten " +t+".");
	}else{ 
		System.out.println("Kuerzester Pfad der Laenge " +ergebniss+" gefunden.");
	}
    }
       public  static int dijkstra(WeightedGraph g, int s, int t){
        	int[] dist =new int[g.getNumNodes()+1];
        	for(int i=1; i<=g.getNumNodes();i++){
        		dist[i]=Integer.MAX_VALUE;
        	}
        	PriorityQueue<HeapElement> queue= new PriorityQueue<HeapElement>();
        	HeapElement sheap= new HeapElement(0, g.getNode(s));
        	
        	queue.add(sheap);
        	dist[s]=0;
        	while(!queue.isEmpty()){
        		HeapElement u1=queue.remove();
        		PriorityQueue<HeapElement> hilfsqueue= new PriorityQueue<HeapElement>();
        		while (!queue.isEmpty()) {
        			HeapElement u2=queue.remove();
        			if(dist[u2.getNode().getId()] == Integer.MAX_VALUE) {
        				if(u1.compareTo(u2)==1){
        					hilfsqueue.add(u1);
        					u1=u2;
        				}else{
        					hilfsqueue.add(u2);
        				}
        				
            			}
            		}
            		queue=hilfsqueue;
            		dist[u1.getNode().getId()]=u1.getDistance();
            		if(u1.getNode().getId()==t){
            			return dist[t];
            		}
            		LinkedList<WeightedEdge> edges = u1.getNode().getEdges();
            		for(WeightedEdge edge: edges){
               		if(dist[edge.getDst().getId()] == Integer.MAX_VALUE) {
            				HeapElement wheap= new HeapElement(u1.getDistance()+edge.getWeight(),edge.getDst());
        				queue.add(wheap);
        			}
        		}
        	}
        	return dist[t];
        }
}
