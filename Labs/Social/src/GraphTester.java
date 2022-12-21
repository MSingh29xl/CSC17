public class GraphTester {
	public static void main(String[] args) {
		Graph g = new Graph();
		GraphLoader.loadGraph(g, "facebook_2000.txt");
		Ego e = new Ego(g);
		int i = 0;
		System.out.println("======== Top 5 ego networks ========");
		for (Ego.egonet element : e.top(5)) {
			System.out.printf("[%d] center: %4d strength: %d\n", i, element.getCenter(), element.getG().getE());
			i++;
		}
		Influence in = new Influence(g);
		i = 0;
		System.out.println("======== Top 5 Influencers ========");
		for (Influence.influencer element : in.top(5)) {
			System.out.printf("[%d] source: %4d influence: %.2f\n", i, element.getSource(), element.getPower());
			i++;
		}
		CC cc = new CC(g);
		i = 0;
		System.out.println("======== Top 5 Connected Component ========");
		System.out.println("Total number of CC: " + cc.count());
		for (CC.cc element : cc.top(5)) {
			System.out.printf("[%d] Id: %2d Size: %d\n", i, element.getId(), element.getSize());
			i++;
		}
	}
}