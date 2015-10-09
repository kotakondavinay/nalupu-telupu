/**
package com.naltel.spoj;

import java.io.*;
import java.util.*;

class Node implements Comparator<Node> {

	public int node;
	public int cost;
	public Node(){
		
	}
	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
	public int compare(Node node1, Node node2) {
		if(node1.cost < node2.cost)
			return -1;
		if(node1.cost > node2.cost)
			return 1;
		return 0;
	}
}
public class Samera08A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n,m;
		int[][] edge = new int[500][500];
		int[][] shortestEdge = new int[500][500];
		int[] vertexD = new int[500];
		String input = in.nextLine();
		n = Integer.parseInt(input.split(" ")[0]);
		m = Integer.parseInt(input.split(" ")[1]);
		while(m !=0 && n != 0) {
			input = in.nextLine();
			int s = Integer.parseInt(input.split(" ")[0]);
			int d = Integer.parseInt(input.split(" ")[1]);
			Arrays.fill(edge, 1001);
			for(int i = 0; i < m ; i++) {
				input = in.nextLine();
				int v1 = Integer.parseInt(input.split(" ")[0]);
				int v2 = Integer.parseInt(input.split(" ")[1]);
				int val = Integer.parseInt(input.split(" ")[2]);
				edge[v1][v2] = val;
			}
			//Write Dijistra.
			//Deque<Integer> queue = new LinkedList<Integer>();
			PriorityQueue<Node> prQueue = new PriorityQueue<Node>();
			//prQueue.addLast(s);
			prQueue.add(new Node(s,0));
			Map<Integer, Set<Integer>> paths = new HashMap<Integer, Set<Integer>>();
			paths.put(s, new HashSet<Integer>());
			vertexD[s]=0;
			Set<Integer> vertexPaths = new HashSet<Integer>();
			Set<Integer> visited = new HashSet<Integer>();
			visited.add(s);
			while(!prQueue.isEmpty()) {
				Node nodeElement = prQueue.remove();
				int element = nodeElement.node;
				for(int i=0;i<n;i++) {
					//Check if edge exists
					if(edge[element][i] != 1001) {
						int alt = edge[element][i] + vertexD[element];
						if(edge[element][i] != 1001 && alt < vertexD[i]) {
							vertexD[i] = alt;
							Set<Integer> set = new HashSet<Integer>(Arrays.asList(element));
							paths.put(i,set);
						} else if(alt == vertexD[i]) {
							paths.get(i).add(new Integer(element));
						}
						queue.addLast(i);
					}
					
				}
			}
			
			//Check in queue.
			queue.addLast(d);
			while(!queue.isEmpty()) {
				int value = queue.pollFirst();
				vertexPaths.add(value);
				Set<Integer> prevPaths = paths.get(value);
				for(Integer prePath: prevPaths) {
					if(!vertexPaths.contains(prePath)) {
						shortestEdge[prePath][value] = 1;
						queue.addLast(prePath);
					}
				}
			}
			// Again Dijstiktra.
			queue.addLast(s);
			//Map<Integer, Set<Integer>> paths = new HashMap<Integer, Set<Integer>>();
			paths.put(s, new HashSet<Integer>());
			Arrays.fill(vertexD, 1001);
			vertexD[s]=0;
			//Set<Integer> vertexPaths = new HashSet<Integer>();
			while(!queue.isEmpty()) {
				Integer element = queue.pollFirst();
				for(int i=0;i<n;i++) {
					//Check if edge exists
					if(edge[element][i] != 0) {
						int alt = edge[element][i] + vertexD[element];
						if(edge[element][i] != 1003 && alt < vertexD[i]) {
							vertexD[i] = alt;
							Set<Integer> set = new HashSet<Integer>(Arrays.asList(element));
							//paths.put(i,set);
						}
						//else if(alt == vertexD[i]) {
						//	paths.get(i).add(new Integer(element));
						//}
						queue.addLast(i);
					}
					
				}
			}
			System.out.println(vertexD[d]);
			Arrays.fill(edge, 0);
			input = in.nextLine();
			n = Integer.parseInt(input.split(" ")[0]);
			m = Integer.parseInt(input.split(" ")[1]);
		}
		
	}
}
*/