package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import org.w3c.dom.Node;

public class test21_30 {
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static void main(String args[]) {
		test21_30 a = new test21_30();
		TreeNode root = new TreeNode(1);
		TreeNode left1 = new TreeNode(2);
		TreeNode right1 = new TreeNode(3);
		TreeNode left2 = new TreeNode(4);
		TreeNode right2 = new TreeNode(5);
		root.left = left1;
		left1.left = right1;
		right1.left = left2;
		left1.right = right2;

		int[] b = { 1, 2, 3 };
		int[] c = { 3, 2, 1 };
		a.buildTree(b, c);
		// c = a.buildArray(b, 5, 5);
		// System.out.print(preorderTraversal(root));

	}

	// Binary Tree Preorder Traversal
	// Recursive
	static List<Integer> list = new LinkedList<Integer>();

	/*
	 * public List<Integer> preorderTraversal(TreeNode root) { if(root == null)
	 * {return list;} list.add(root.val); if(root.left!=null) {
	 * this.preorderTraversal(root.left); } if(root.right!=null) {
	 * this.preorderTraversal(root.right); }
	 * 
	 * return list; }
	 */
	// iteratively
	public static List<Integer> preorderTraversal(TreeNode root) {
		if (root == null)
			return list;

		Queue<TreeNode> leftnode = new LinkedList<TreeNode>();
		Stack<TreeNode> rightnode = new Stack<TreeNode>();
		leftnode.add(root);
		while (!leftnode.isEmpty() || !rightnode.isEmpty()) {
			while (!leftnode.isEmpty()) {
				TreeNode cur = leftnode.poll();
				list.add(cur.val);
				if (cur.left != null) {
					leftnode.add(cur.left);
				}
				if (cur.right != null) {
					rightnode.add(cur.right);
				}
			}
			TreeNode cur = rightnode.pop();
			list.add(cur.val);
			if (cur.left != null) {
				leftnode.add(cur.left);
			}
			if (cur.right != null) {
				rightnode.add(cur.right);
			}

		}

		return list;
	}

	// Binary Tree inorder Traversal
	// Recursive
	/*
	 * public List<Integer> inorderTraversal(TreeNode root) { if (root == null)
	 * return list;
	 * 
	 * if (root.left != null) { this.inorderTraversal(root.left); }
	 * list.add(root.val); if (root.right != null) {
	 * this.inorderTraversal(root.right); }
	 * 
	 * return list; }
	 */
	// iteratively
	public List<Integer> inorderTraversal(TreeNode root) {

		if (root == null)
			return list;

		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);

		while (!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			if (cur.right != null)
				stack.push(cur.right);
			// only add to the result when left sub tree is null
			if (cur.left == null)
				list.add(cur.val);
			else {
				// if left sub tree is no null, we need to
				// add it back to the stack, but after its left sub tree.
				// then set the left subtree to null.(next time we can
				// add it)
				stack.push(cur);
				stack.push(cur.left);
				cur.left = null;
				cur.right = null;// we also need to cut the right subtree.
			}
		}

		return list;
	}

	// Binary Tree postorder Traversal
	// Recursive

	/*
	 * public List<Integer> postorderTraversal(TreeNode root) { if (root ==
	 * null) return list;
	 * 
	 * if (root.left != null) { this.postorderTraversal(root.left); } if
	 * (root.right != null) { this.postorderTraversal(root.right); }
	 * list.add(root.val); return list; }
	 */

	// iteratively
	public List<Integer> postorderTraversal(TreeNode root) {
		if (root == null)
			return list;
		Stack<TreeNode> sk1 = new Stack<TreeNode>();
		Stack<Integer> sk2 = new Stack<Integer>();

		sk1.push(root);

		while (!sk1.isEmpty()) {
			TreeNode temp = sk1.pop();
			sk2.push(temp.val);
			if (temp.left != null) {
				sk1.push(temp.left);
			}
			if (temp.right != null) {
				sk1.push(temp.right);
			}
		}

		while (!sk2.isEmpty()) {
			list.add(sk2.pop());
		}

		return list;
	}

	// Given a binary tree, return the bottom-up level order traversal of its
	// nodes' values
	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		List<Integer> temp = new LinkedList<Integer>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> depth = new LinkedList<Integer>();
		if (root == null)
			return result;
		queue.add(root);
		depth.add(1);
		int curDep = 0;
		int curlevel = 1;
		while (!queue.isEmpty()) {
			TreeNode top = queue.poll();
			curDep = depth.poll();
			if (curDep > curlevel) {
				curlevel = curDep;
				result.add(0, temp);
				temp = new LinkedList<Integer>();
			}
			temp.add(top.val);
			if (top.left != null) {
				queue.add(top.left);
				depth.add(curDep + 1);
			}
			if (top.right != null) {
				queue.add(top.right);
				depth.add(curDep + 1);
			}
		}

		result.add(0, temp);

		return result;
	}

	// Given a binary tree, return the level order traversal of its nodes'
	// values.
	public static List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		List<Integer> temp = new LinkedList<Integer>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> depth = new LinkedList<Integer>();
		if (root == null)
			return result;
		queue.add(root);
		depth.add(1);
		int curDep = 0;
		int curlevel = 1;
		while (!queue.isEmpty()) {
			TreeNode top = queue.poll();
			curDep = depth.poll();
			if (curDep > curlevel) {
				curlevel = curDep;
				result.add(temp);
				temp = new LinkedList<Integer>();
			}
			temp.add(top.val);
			if (top.left != null) {
				queue.add(top.left);
				depth.add(curDep + 1);
			}
			if (top.right != null) {
				queue.add(top.right);
				depth.add(curDep + 1);
			}
		}

		result.add(temp);

		return result;
	}

	// Maximum Depth of Binary Tree
	public static int maxDepth(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> depth = new LinkedList<Integer>();
		if (root == null)
			return 0;
		queue.add(root);
		depth.add(1);
		int curDep = 0;
		while (!queue.isEmpty()) {
			TreeNode top = queue.poll();
			curDep = depth.poll();
			if (top.left != null) {
				queue.add(top.left);
				depth.add(curDep + 1);
			}
			if (top.right != null) {
				queue.add(top.right);
				depth.add(curDep + 1);
			}
		}

		return curDep;
	}

	// see if it is balanced tree..
	public static boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;

		DepNode start = new DepNode(root);

		if (initialDepNode(start) == -2) {
			return false;
		} else {
			return true;
		}
	}

	public static int initialDepNode(DepNode root) {
		int leftdep, rightdep;
		if (root.node.left != null) {
			root.left = new DepNode(root.node.left);
			leftdep = initialDepNode(root.left) + 1;
		} else {
			leftdep = 1;
		}

		if (root.node.right != null) {
			root.right = new DepNode(root.node.right);
			rightdep = initialDepNode(root.right) + 1;
		} else {
			rightdep = 1;
		}

		if (Math.abs(leftdep - rightdep) > 1 || leftdep < 0 || rightdep < 0) {
			return -2;
		}

		root.depth = Math.max(leftdep, rightdep);

		return root.depth;
	}

	public static class DepNode {
		TreeNode node;
		DepNode left;
		DepNode right;
		int depth = 0;

		DepNode(TreeNode n) {
			node = n;
		}
	}

	// find the mini depth
	public int minDepth(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> depth = new LinkedList<Integer>();
		if (root == null)
			return 0;
		queue.add(root);
		depth.add(1);
		while (!queue.isEmpty()) {
			TreeNode top = queue.poll();
			int curDep = depth.poll();
			if (top.left == null && top.right == null)
				return curDep;
			if (top.left != null) {
				queue.add(top.left);
				depth.add(curDep + 1);
			}
			if (top.right != null) {
				queue.add(top.right);
				depth.add(curDep + 1);
			}
		}
		return -1;
	}

	// Invert Binary Tree
	public TreeNode invertTree(TreeNode root) {
		if (root == null)
			return null;
		TreeNode temp;
		temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;
	}

	// Binary Tree Right Side View .. right side of it.
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();
		List<Integer> temp = new LinkedList<Integer>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> depth = new LinkedList<Integer>();
		if (root == null)
			return result;
		queue.add(root);
		depth.add(1);
		int curDep = 0;
		int curlevel = 1;
		while (!queue.isEmpty()) {
			TreeNode top = queue.poll();
			curDep = depth.poll();
			if (curDep > curlevel) {
				curlevel = curDep;
				result.add(temp.get(temp.size() - 1));
				temp = new LinkedList<Integer>();
			}
			temp.add(top.val);
			if (top.left != null) {
				queue.add(top.left);
				depth.add(curDep + 1);
			}
			if (top.right != null) {
				queue.add(top.right);
				depth.add(curDep + 1);
			}
		}

		result.add(temp.get(temp.size() - 1));

		return result;
	}

	// Flatten Binary Tree to Linked List
	public static void flatten(TreeNode root) {
		if (root == null)
			return;
		flattenWork(root);
	}

	public static TreeNode flattenWork(TreeNode root) {
		if (root.left == null && root.right == null)
			return root;

		TreeNode temp;

		temp = root.right;

		if (root.left != null) {
			root.right = root.left;
			root.left = null;
			if (temp != null) {
				flattenWork(root.right).right = temp;
			}
		}
		if (root.right != null) {
			return flattenWork(root.right);
		}
		return root;
	}

	// find the max path of the tree. start and end at any leaves;
	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		maxPath = root.val;
		DepNode start = new DepNode(root);
		initialPathNode(start);
		return maxPath;
	}

	private int maxPath = 0;

	public int initialPathNode(DepNode root) {
		int leftdep, rightdep;
		if (root.node.left != null) {
			root.left = new DepNode(root.node.left);
			leftdep = initialPathNode(root.left);
		} else {
			leftdep = 0;
		}

		if (root.node.right != null) {
			root.right = new DepNode(root.node.right);
			rightdep = initialPathNode(root.right);
		} else {
			rightdep = 0;
		}

		int tempPath = leftdep + rightdep + root.node.val;
		if (tempPath > maxPath) {
			maxPath = tempPath;
		}

		root.depth = Math.max(leftdep, rightdep) + root.node.val;

		// start and end at any node; We dont need negative one.
		if (root.depth < 0)
			return 0;

		return root.depth;
	}

	// Binary Tree Zigzag Level Order Traversal
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new LinkedList<List<Integer>>();
		List<Integer> temp = new LinkedList<Integer>();
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<Integer> depth = new LinkedList<Integer>();
		if (root == null)
			return result;
		queue.add(root);
		depth.add(1);
		int curDep = 0;
		int curlevel = 1;
		boolean isFront = true;
		while (!queue.isEmpty()) {
			TreeNode top = queue.poll();
			curDep = depth.poll();
			if (curDep > curlevel) {
				curlevel = curDep;
				result.add(temp);
				temp = new LinkedList<Integer>();
				isFront = !isFront;
			}
			if (isFront) {
				temp.add(top.val);
			} else {
				temp.add(0, top.val);
			}

			if (top.left != null) {
				queue.add(top.left);
				depth.add(curDep + 1);
			}
			if (top.right != null) {
				queue.add(top.right);
				depth.add(curDep + 1);
			}
		}

		result.add(temp);

		return result;
	}

	// Construct Binary Tree from Preorder and Inorder Traversal

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder.length == 0) {
			return null;
		}

		TreeNode root = new TreeNode(preorder[0]);

		if (preorder.length == 1) {
			return root;
		}

		for (int i = 0; i < preorder.length; i++) {
			if (preorder[0] == inorder[i]) {
				root.left = buildTree(buildArray(preorder, 1, i),
						buildArray(inorder, 0, i - 1));
				root.right = buildTree(
						buildArray(preorder, i + 1, preorder.length - 1),
						buildArray(inorder, i + 1, preorder.length - 1));
				return root;
			}
		}
		return root;
	}

	public int[] buildArray(int[] order, int start, int end) {
		if (end < start) {
			int[] result = {};
			return result;
		}
		int[] result = new int[end - start + 1];

		for (int i = 0; i <= end - start; i++) {
			result[i] = order[start + i];
		}
		return result;
	}

	// Construct Binary Tree from Inorder and Postorder Traversal

	public TreeNode buildTreePost(int[] inorder, int[] postorder) {
		if (postorder.length == 0) {
			return null;
		}

		TreeNode root = new TreeNode(postorder[postorder.length - 1]);

		if (postorder.length == 1) {
			return root;
		}

		for (int i = 0; i < postorder.length; i++) {
			if (root.val == inorder[i]) {
				root.left = buildTree(buildArray(inorder, 0, i - 1),
						buildArray(postorder, 0, i - 1));
				root.right = buildTree(
						buildArray(inorder, i + 1, postorder.length - 1),
						buildArray(postorder, i, postorder.length - 2));
				return root;
			}
		}
		return root;
	}

}
