import java.util.*;

public class CC {
	// place your code here
	List<cc> ccList;
	
	// Comparator overload
	public class ccCompare implements Comparator<cc>{
		
		public int compare(cc o1, cc o2) {
			return Integer.compare(o2.getSize(),o1.getSize());
		}
	}
	
	// Initialize necessary arrays and integers for DFS tracking
	public boolean[] marked;
	public int[] id;  
	public int count = 1; // 1st id is 1

	// the nested class used to define a connected component
	public static class cc {
		int id; // the id of the component
		int size; // the size of the component

		cc(int i, int s) {
			id = i;
			size = s;
		}

		int getId() {
			return id;
		}

		int getSize() {
			return size;
		}
	}
	
	private void dfs(Graph g, int v) { // DFS Algorithm
	    marked[v] = true;
	    id[v] = count;
	    for (int w : g.adj(v)) {
	        if (!marked[w]) {
	            dfs(g, w);
	        }
	    }
	}

	public CC(Graph g) {
		// place your code here
		int temp = g.getV();
		List<Integer> vert = new ArrayList<Integer>();
		vert.addAll(g.getVertices());
		int infSize = vert.get(temp - 1);
		// Assign size for all tracking arrays
		ccList = new ArrayList<cc>();
		marked = new boolean[infSize+1];  
		id = new int[infSize+1];
		
		for (int v: g.getVertices()) {
			if (!marked[v]) {
				dfs(g, v); // Recursive
				count++;
			}
		}
		// Assign proper sizes for each id for ccList entry thru the id array
		for (int i = 1; i < count; i++) { 
			int size = 0;
			for (int j: id) { 
				if (i == j) {
					size++;
				}
			}
			cc tempCC = new cc(i, size);
			ccList.add(tempCC);
		}
		
	}

	public int count() {
		// place your code here
		return count;
	}

	public List<cc> top(int k) {
		// place your code here
		List<cc> topCC = new ArrayList<cc>(k);
		ccList.sort(new ccCompare());
		for (int i = 0; i < k; i++) {
			topCC.add(ccList.get(i));
		}
		return topCC;
	}
}




