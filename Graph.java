import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    private Node[] nodes;
    private int numNodes;
    private int numEdges;


    public Graph(){
        this.nodes = new Node[100];
        this.numNodes = 0;
        this.numEdges = 0;
    }

    public static Graph fromFile(String filepath){
        Graph graph = new Graph();
        BufferedReader bufferedReader = null;
        try {
            FileReader fileReader = new FileReader(filepath);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        try {
            //file reader liest ein linie ein
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // geht durch alle linien denn file
        while (line != null) {
            int numNodes = 0 ;
            int numEdges =0;
            int nodeOne;
            int nodeTwo;
            // scanner wird genutzt um durch die linie zu lesen
            Scanner scan = new Scanner(line);
                if (line.charAt(0) == 'p') {
                    while (scan.hasNext()) {
                        if (scan.hasNextInt()) {
                            numNodes = scan.nextInt();
                            numEdges = scan.nextInt();
                            for(int i = 0; i < numNodes; i++){
                                graph.addNode(i);
                            }
                        } else {
                            scan.next();
                        }
                    }
                } else if(line.charAt(0) == 'e') {
                    while (scan.hasNext()) {
                        if (scan.hasNextInt()) {
                            nodeOne = scan.nextInt();
                            nodeTwo = scan.nextInt();
                            graph.addEdge(nodeOne, nodeTwo);
                        } else {
                            scan.next();
                        }
                    }
                }
                try {
                    line = bufferedReader.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return graph;

    }

    public String toString(){
        //String builder nutzen um string zu bauen
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < numNodes; i++){
            stringBuilder.append("Knoten" + i + " verbunden zu: ");
            LinkedList<Edge> edges = nodes[i].getEdges();
            for(Edge edge: edges){
                stringBuilder.append(edge.getDst().getId() +" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void addNode(int id){
        // checken ob id gultig ist
        if(id > nodes.length){
            Node[] larger = new Node[nodes.length*2];
            for(int i = 0; i < nodes.length; i++){
                larger[i] = nodes[i];
            }
            nodes = larger;
        }
        if(id == numNodes){
            nodes[id] = new Node(id);
        }
        numNodes++;
    }

    public boolean addEdge(int src, int dst){
        Node srcNode = getNode(src);
        Node dstNode = getNode(dst);
        // schauen ob node da ist
        if(srcNode==null || dstNode == null){
            return false;
        }
        srcNode.addEdge(dstNode);
        return true;
    }

    public Node getNode(int id){
        // ist node id gultig
        if(id >= numNodes || numNodes == 0){
            return null;
        }
        for(int i = 0; i < numNodes; i++){
            if(nodes[i].getId() == id){
                return nodes[i];
            }
        }
        return null;
    }

    public boolean contains(int id){
        if(id >= numNodes || numNodes == 0){
            return false;
        }
        return true;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public int getNumEdges() {
        return numEdges;
    }

    public int breitenSuche(int s, int t){
        int[] distances = new int[numNodes];
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[s] = 0;
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(getNode(s));
        Node current;
        while (!queue.isEmpty()){
           current = queue.removeFirst();
           // wenn ziel ist gib distance von ziel zuruck
           if(current.getId() == t){
               return distances[t];
           }
           LinkedList<Edge> edges = current.getEdges();
           //fuge die knoten mit denn current verbunden ins queue ein
           for(Edge edge: edges){
               if(distances[edge.getDst().getId()] == Integer.MAX_VALUE) {
                   queue.addLast(edge.getDst());
                   //pass distance von die knoten ein
                   distances[edge.getDst().getId()] = distances[current.getId()]+1;}
           }
        }
        return distances[t];
    }
    
    
   	
  
  			
  
}
