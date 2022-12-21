class TreeNode {
	public int key;
	public TreeNode left, right;

	public TreeNode(int item) {
		key = item;
		left = right = null;
	}
}

public class BinaryTree {

	public static int maxDepth(TreeNode node) {
		// TODO: place your code here
		return 0;
	}

	public static boolean isSymmetric(TreeNode node) {
		// TODO: place your code here
		return true;
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

		System.out.println(isSymmetric(root));
		System.out.println(isSymmetric(root.right));

		System.out.println(maxDepth(root));
		System.out.println(maxDepth(root.right));
	}
}