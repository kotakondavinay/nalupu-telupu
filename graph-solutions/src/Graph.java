import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");;
	private  int V;
	private int E;
	private List<ArrayList<Integer>> adj;
	
	public Graph(int V){
		this.V = V;
		this.E = 0;
		adj = new ArrayList<>();
		for(int v = 0; v < V; v++){
			ArrayList<Integer> list = new ArrayList<>();
		    adj.add(list);
		}
	}
	
	public Graph(Scanner in){
		this(in.nextInt());
		int numberOfEdges = in.nextInt();
		
		for(int i=0; i < numberOfEdges; i++){
			int v = in.nextInt();
			int w = in.nextInt();
			addEdge(v,w);
		}
		
	}
	public Graph(Graph G){
		this(G.V());
		this.E = G.E;
		
		for(int i=0; i< G.V(); i++){
			ArrayList<Integer> list = new ArrayList<>();
			ArrayList<Integer> graphList = G.adj.get(i);
			for(int j=0; j < graphList.size();j++){
				list.add(graphList.get(i));
			}
			this.adj.add(list);
			
		}
	}
	public void validateVertex(int v){
		if (v < 0 || v >= V)
		throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));	
	}
	
	public int V(){
		return this.V;
	}
	
	public int E(){
		return this.E;
	}
	
	public void addEdge(int v,int w){
		validateVertex(v);
		validateVertex(w);
		E++;
		ArrayList<Integer> listV = adj.get(v);
		listV.add(w);
		ArrayList<Integer> listW = adj.get(w);
		listW.add(v);
	}
	
	public ArrayList<Integer> adjList(int v){
		validateVertex(v);
		return adj.get(v);
	}
	
	public int degree(int v){
		return adj.get(v).size();
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		
		s.append(V + " vertices ,"+ E+" edges" + NEWLINE);
		for(int v=0; v < V; v++){
			s.append(v+ " : ");
			ArrayList<Integer> list = adj.get(v);
			for(int i=0;i < list.size();i++){
				s.append(list.get(i) + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		System.out.println("Initializes a graph from an input stream.");
		System.out.println("The format is the number of vertices V");
		System.out.println("followed by the number of edges E");
		System.out.println("followed by E pairs of vertices, with each entry separated by whitespace.");
        Scanner in = new Scanner(System.in);
        Graph G = new Graph(in);
        System.out.println(G);
    }
	
	
}
