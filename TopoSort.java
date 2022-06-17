import java.util.LinkedList;
import java.util.Arrays;
public class TopoSort {
    public static void main(String[] args) {
        String path = args[0];

        Graph graph = Graph.fromFile(path);
        LinkedList<Node> solution =topoSort(graph);
        int a=graph.getNumNodes();
        //sind s und t im Graphen
        if(t>=0&&s>=0&&s<a&&t<a){
        	//Zyklus gefunden?
      		if(solution==null){
        		System.out.println("Der Graph ist keine DAG.");
       	 }else{
       	 	//solution ausgeben
        		int[] lösung = new int[graph.getNumNodes()];
        		for(int i=0; i< graph.getNumNodes(); i++){
        			lösung[i]=solution.removeLast().getId();
        		}	
        		System.out.println(" Topologische Sortierug: "+Arrays.toString(lösung));
      	 	 }
      	 }
      	 System.out.println("Eingaben nicht im Graph.");
        
    }
    enum Mark {
    	UNMARKED,
    	TEMPORARY,
    	PERMANENT
  }
  
  static boolean dfs(LinkedList<Node> solution, Node currentNode, Mark[] marked){
  	//Mark current=marked[currentNode.getId()];
  	switch(marked[currentNode.getId()]) {
      		case TEMPORARY:
      		// Zyklus gefunden
        		return false;
      		case UNMARKED:
         		marked[currentNode.getId()]=Mark.TEMPORARY;
  			LinkedList<Edge> edges = currentNode.getEdges();
  			//für jeden knoten der mit currentNode verbunden ist dfs wieder aufrufen
  			for(Edge edge: edges){
  				if(!dfs(solution,edge.getDst(), marked)){
  					return false;
  					
  				}
  			}
  			
  			solution.add(currentNode);
  			marked[currentNode.getId()]=Mark.PERMANENT;
  			return true;
      		case PERMANENT:
        		return true;
    	}
    	return true;
  }
   static LinkedList<Node> topoSort(Graph g){
   	Mark[] marked=new Mark[g.getNumNodes()];
   	//alles auf UNMARKED setzen
   	for(int i=0; i<g.getNumNodes(); i++){
   		marked[i]=Mark.UNMARKED;
   	}
   	LinkedList<Node> solution = new LinkedList<>();
   	//für jeden knoten dfs aufrufen wenn er unmarked ist
   	for(int i=0; i<g.getNumNodes();i++){
   		Node v=g.getNode(i);
   		switch(marked[i]) {
      			case UNMARKED:
   				if(!dfs(solution, v, marked)){
   					return null;
   				}
   				break;
   			case PERMANENT:
        			break;
        		case TEMPORARY:
        			break;
   		}
   	}
   		return solution;
   			
   }
}
