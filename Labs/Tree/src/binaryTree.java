class TreeNode {
	public int key;
	public TreeNode left, right;

	public TreeNode(int item) {
		key = item;
		left = right = null;
	}
}

public class binaryTree {
	static int maxDepth = 0;
	static int maxDepthleft = 1;
	static int maxDepthRight = 1;

	public static int maxDepth(TreeNode node) {
		if(node == null) {
			return 0;
		}
		maxDepth++;
		return depth(node.left, node.right);
	}
	
	public static int depth(TreeNode left, TreeNode right) {
		if(left == null && right == null) {
			return compare(maxDepthleft, maxDepthRight);
		}
		if(left != null) {
			maxDepthleft++;
			return(depth(left.left, left.right));
		}
		if(right != null) {
			maxDepthRight++;
			return(depth(right.left, right.right));
		}
		return compare(maxDepthleft, maxDepthRight);
	}
	
	public static int compare(int l, int r) {
		if(l==r) {
			return l;
		}
		else if(l>r) {
			return l;
		}
		else {
			return r;
		}
	}
	
	public static boolean isMirror(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		}
		else if (left == null || right == null) {
			return false;
		}
		if(left.key != right.key) {
			return false;
		}
		else {
			return isMirror(left.left, right.right);
		}
	}

	public static boolean isSymmetric(TreeNode node) {
		if(node == null) {
			return true;
		}
		return isMirror(node.left, node.right);
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
