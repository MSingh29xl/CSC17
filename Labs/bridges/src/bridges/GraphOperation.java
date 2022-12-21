package bridges;
import java.util.*;

import CC.cc;


public class GraphOperation {

	public static int time = 0;

	private static class Graph {
		private int V;
		private Map<Integer, ArrayList<Integer>> adjListsMap;

		public Graph(int v) {
			V = 0;

			adjListsMap = new HashMap<Integer, ArrayList<Integer>>();
			for (int i = 0; i < v; i++) {
				addVertex();
			}
		}

		public int getNumVertices() {
			return V;
		}

		public List<Integer> getNeighbors(int v) {
			return new ArrayList<Integer>(adjListsMap.get(v));
		}

		public void addVertex() {
			int v = getNumVertices();
			ArrayList<Integer> neighbors = new ArrayList<Integer>();
			adjListsMap.put(v, neighbors);
			V++;
		}

		public void addEdge(int v, int w) {
			(adjListsMap.get(v)).add(w);
		}
	}


	public static List<List<Integer>> allBridges(Graph g) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		CC(g, list);
		return list;
	}
	
	private static void dfs(Graph g, int v) { // DFS Algorithm
	    marked[v] = true;
	    id(v) = count;
	    for (int w : g.getNeighbors(v)) {
	        if (!marked[w]) {
	            dfs(g, w);
	            count++;
	        }
	    }
	}

	public static boolean[] marked;
	public static List<Integer> id; 
	public static int count = 0;
	
	public static void CC(Graph g, List<List<Integer>> x) {
		
		// place your code here
		marked = new boolean[g.getNumVertices()];  
		id = new ArrayList<Integer>(g.getNumVertices());
		
		for (int v = 0; v < g.getNumVertices(); v++) {
			if (!marked[v]) {
				dfs(g, v); // Recursive
			}
		}
		for (int i = 1; i < g.getNumVertices(); i++) { 
			x.add(id);
		}
	}

	public static void main(String args[]) {
		Graph graph1 = new Graph(5);
		graph1.addEdge(0, 1);
		graph1.addEdge(0, 2);
		graph1.addEdge(0, 3);
		graph1.addEdge(1, 0);
		graph1.addEdge(1, 2);
		graph1.addEdge(2, 0);
		graph1.addEdge(2, 1);
		graph1.addEdge(3, 0);
		graph1.addEdge(3, 4);
		graph1.addEdge(4, 3);

		System.out.println(allBridges(graph1));
	}
}