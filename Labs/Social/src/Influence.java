import java.util.*;

public class Influence {
	// place your code here
	List<influencer> infList;
	
	// Comparator overload
	public class infCompare implements Comparator<influencer>{
		
		public int compare(influencer o1, influencer o2) {
			return Double.compare(o2.getPower(),o1.getPower());
		}
	}

	// the nested class used to define a influencer
	public static class influencer {
		int source; // the influencer
		double power; // the impact of this influencer

		influencer(int i, double p) {
			source = i;
			power = p;
		}

		int getSource() {
			return source;
		}

		double getPower() {
			return power;
		}
	}
	
	public double getInfScore(int[] sc) { // Calculate power scores for each BFS layer
		double fin = 0;
		for(int i: sc) {
			if(i > 0) {
				fin += 1/Math.pow(2, i-1);
			}
		}
		return fin;
	}

	public Influence(Graph g) {
		// place your code here
		infList = new ArrayList<influencer>();
		double power = 0;
		int temp = g.getV();
		List<Integer> vert = new ArrayList<Integer>();
		vert.addAll(g.getVertices());
		int infSize = vert.get(temp - 1); // Proper sizing of lists
		for(int i: g.getVertices()) {
			BFS.bfs(g, i, infSize + 1);
			power = getInfScore(BFS.getDistTo()); // Assigns power based on distance from initial
			influencer inf = new influencer(i, power);
			infList.add(inf);
		}
	}
	
	public List<influencer> top(int k) {
		// place your code here
		List<influencer> topInf = new ArrayList<influencer>(k);
		infList.sort(new infCompare());
		for (int i = 0; i < k; i++) {
			topInf.add(infList.get(i));
		}
		return topInf;
	}
	
	public class BFS { // BFS for each layer (1/2 power draw for each distance)
		// Initialize arrays for tracking
		public static boolean[] marked;
		public static int[] edgeTo;
		public static int[] distTo;
		public static void bfs(Graph G, int s, int len) { // (Graph, vertex, size to initialize tracking arrays)
			Queue<Integer> q = new LinkedList<>();
			q.add(s);
			marked = new boolean[len];
			edgeTo = new int[len];
			distTo = new int[len];
			marked[s] = true;  
			distTo[s]= 0;
			while(!q.isEmpty())  {  
				int v = q.remove();
				for (int w: G.adj(v))    {
					if (!marked[w]){  
						q.add(w); 
						marked[w] = true; 
						edgeTo[w] = v; 
						distTo[w] = distTo[v] + 1;
					}
				}
			}
		}
		public static int[] getDistTo() { // Get distance from initial to use for power calculation
			return distTo;
		}
	}	
}

