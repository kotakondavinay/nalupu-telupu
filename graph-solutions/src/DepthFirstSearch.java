import java.util.ArrayList;
import java.util.Scanner;


public class DepthFirstSearch {
	private boolean[] marked;
	private int count;
 
	public DepthFirstSearch(Graph G,int s){
		marked = new boolean[G.V()];
		dfs(G,s);
	}
	public void dfs(Graph G,int v){
		count++;
		marked[v] = true;
		int w;
		ArrayList<Integer> list = G.adjList(v);
		for(int i = 0; i < list.size(); i++){
			w = list.get(i);
			if(!marked[w])
				dfs(G,w);
		}
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	public int count(){
		return count;
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Graph G = new Graph(in);
		System.out.println(G);
		System.out.println("Give source vertex: ");
		
		int s = in.nextInt();
		while(s != -1){
			DepthFirstSearch dfs = new DepthFirstSearch(G,s);
			for(int v = 0; v < G.V(); v++){
				if(dfs.marked(v))
					System.out.print(v+" ");
			}
			s = in.nextInt();
		}
	}
	
 
	
}
