package com.lalit.ds.java.tree.bst;

import java.util.LinkedList;
import java.util.Queue;

public class MaxDepthInBinaryTreeWithLevelOrder {
	// Returns the depth of this binary tree. The depth of a binary tree is the
	// length of the longest path from this node to a leaf. The depth of a
	// binary tree with no descendants (that is, just a leaf) is zero.
	public int maxDepthLevelOrder(BinaryTreeNode root) {
		if (root == null)
			return 0;
		int maxDepth = 1;
		Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
		q.offer(root);
		q.offer(null); // <----------- 'NULL' added Here
		while (!q.isEmpty()) {
			BinaryTreeNode tmp = q.poll();
			if (tmp != null) {
				if (tmp.getNextLeft() != null)
					q.offer(tmp.getNextLeft());
				if (tmp.getNextRight() != null)
					q.offer(tmp.getNextRight());
			} else {
				if (!q.isEmpty()) {
					++maxDepth;
					q.offer(null); // <--------- Here
				}
			}
		}
		return maxDepth;
	}

	public static void main(String... s) {

		BinaryTreeNode nodes = new BinaryTreeNode(10);

	}
}