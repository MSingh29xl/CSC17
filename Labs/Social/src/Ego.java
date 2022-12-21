import java.util.*;

public class Ego {
	// place your code here
	List<egonet> egoList;
	
	// Comparator overload
	public class egoCompare implements Comparator<egonet>{
	
		public int compare(egonet o1, egonet o2) {
			return Integer.compare(o2.getG().getE(),o1.getG().getE());
		}
	}	

	// the nested class used to define a egonet
	public static class egonet {
		int center; // center of the egonet
		Graph G; // subgraph that represents the egonet

		egonet(int c, Graph g) {
			center = c;
			G = g;
		}

		int getCenter() {
			return center;
		}

		Graph getG() {
			return G;
		}
	}

	public Ego(Graph g) {
		// place your code here
		egoList = new ArrayList<egonet>();
		for(int i: g.getVertices()) {
			Graph temp = new Graph(); //Initialize graphs for egonets
			Set<Integer> adjacent = new HashSet<Integer>();
			temp.addVertex(i);
			adjacent.addAll(g.adj(i));
			for(int j: adjacent) {
				// Add vertices and edges in Ego for adjacent connections
				temp.addVertex(j);
				temp.addEdge(i, j);
				temp.addEdge(j, i);
				for(int k: g.adj(j)) {
					// Add edges for vertices if they are connected to center AND each other
					if (adjacent.contains(k) && !temp.adj(j).contains(k)){
						temp.addEdge(j, k);
						temp.addEdge(k, j);
					}
				}
			}
			egonet tempNet = new egonet(i, temp); 
			egoList.add(tempNet);
		}
	}

	public List<egonet> top(int k) {
		// place your code here
		List<egonet> topEgo = new ArrayList<egonet>(k);
		egoList.sort(new egoCompare());
		for (int i = 0; i < k; i++) {
			topEgo.add(egoList.get(i));
		}
		return topEgo;
	}
}



