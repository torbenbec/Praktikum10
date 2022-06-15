import java.util.LinkedList;
import java.util.Arrays;
public class TopoSort {
    public static void main(String[] args) {
        String path = args[0];

        Graph graph = Graph.fromFile(path);
        LinkedList<Node> solution =topoSort(graph);
        if(solution==null){
        	System.out.println("Der Graph ist keine DAG.");
        }else{
        	int[] lösung = new int[graph.getNumNodes()];
        	for(int i=0; i< graph.getNumNodes(); i++){
        		lösung[i]=solution.removeFirst().getId();
        	}	
        	System.out.println(" Topologische Sortierug: "+Arrays.toString(lösung));
        }
        
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
        		return false;
      		case UNMARKED:
         		marked[currentNode.getId()]=Mark.TEMPORARY;
  			LinkedList<Edge> edges = currentNode.getEdges();
  			for(Edge edge: edges){
  				if(!dfs(solution,edge.getDst(), marked)){
  					return false;
  				}
  			}
  			solution.add(currentNode);
  			return true;
      		case PERMANENT:
        		return true;
    	}
    	return true;
  }
   static LinkedList<Node> topoSort(Graph g){
   	Mark[] marked=new Mark[g.getNumNodes()+1];
   	for(int i=0; i<=g.getNumNodes(); i++){
   		marked[i]=Mark.UNMARKED;
   	}
   	LinkedList<Node> solution = new LinkedList<>();
   	for(int i=1; i<=g.getNumNodes();i++){
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
