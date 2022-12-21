import java.util.*;

class TreeNode {
	public int key;
	public TreeNode left, right;

	public TreeNode(int item) {
		key = item;
		left = right = null;
	}
}

public class BinaryTree {

	public static List<String> binaryTreePaths(TreeNode node) {
		// TODO: place your code here
		List<String> paths = new LinkedList<>();
		if (node == null)
			return paths;
		if (node.left == null && node.right == null) {
			paths.add(node.key + "");
			return paths;
		}
		for (String path : binaryTreePaths(node.left)) {
			paths.add(node.key + "->" + path);
		}
		for (String path : binaryTreePaths(node.right)) {
			paths.add(node.key + "->" + path);
		}
		return paths;
		// return null;

	}

	public static List<Double> averageOfLevels(TreeNode node) {
		// TODO: place your code here
		List<Double> res = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(node);
		while (!queue.isEmpty()) {
			long sum = 0, count = 0;
			Queue<TreeNode> temp = new LinkedList<>();
			while (!queue.isEmpty()) {
				TreeNode n = queue.remove();
				sum += n.key;
				count++;
				if (n.left != null)
					temp.add(n.left);
				if (n.right != null)
					temp.add(n.right);
			}
			queue = temp;
			res.add(sum * 1.0 / count);
		}
		return res;
		// return null;
	}

	public static void main(String args[]) {
		/*
		 *      4   <-- root
		 *     / \
		 *    3   1
		 *       / \
		 *      2   2
		 *     / \ / \
		 *    3  4 4  3
		 */

		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(3);
		root.right = new TreeNode(1);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(2);
		root.right.left.left = new TreeNode(3);
		root.right.left.right = new TreeNode(4);
		root.right.right.left = new TreeNode(4);
		root.right.right.right = new TreeNode(3);

		System.out.println(binaryTreePaths(root));
		System.out.println(averageOfLevels(root));

	}
}
