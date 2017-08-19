package com.lalit.ds.tree.bst;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.lalit.ds.basic.Node1;

public class BinaryTreeValidator {

	boolean isDataValueInRange(int value) {
		if (value >= 0 && value <= 10000) {
			return true;
		}
		return false;
	}

	boolean checkBST(Node1 root) {
		Queue<Node1> queue = new LinkedList<Node1>();
		Set<Integer> setOfValues = new HashSet<Integer>();
		while (root != null) {
			// Duplication Value and Data range check
			if (!(setOfValues.add(root.data) && isDataValueInRange(root.data))) {
				return false;
			}

			if (root.left != null) {
				if (root.left.data >= root.data) {
					return false;
				}
				queue.add(root.left);
			}

			if (root.right != null) {
				if (root.right.data <= root.data) {
					return false;
				}
				queue.add(root.right);
			}

			root = null;
			if (!queue.isEmpty()) {
				root = queue.remove();
			}
		}

		// Check all nodes of left subtree have value < root
		// Check all nodes of right subtree have value > root
		return isValidLeftSubTree(root) && isValidRightSubTree(root);
	}

	boolean isValidLeftSubTree(Node1 root) {
		Stack<Node1> stack = new Stack<Node1>();
		Set<Integer> setOfValuesTraversed = new HashSet<Integer>();
		stack.push(root);
		while (!stack.empty()) {
			root = stack.peek();
			if (stack.size() == 2 && setOfValuesTraversed.contains(stack.get(0).left.data)) {
				if (stack.get(0).data < stack.get(0).left.data) {
					return false;
				}
				return true;
			}
			// Push if Right Child exist but not traversed yet
			if (root.right != null) {
				if (!setOfValuesTraversed.contains(root.right.data)) {
					stack.push(root.right);
				} else if (root.data < root.right.data) {
					root.data = root.right.data;
					root.right = null;
				} else {
					return false;
				}
			}
			// Push if Left Child exists but not traversed yet
			if (root.left != null) {
				if (!setOfValuesTraversed.contains(root.left.data)) {
					stack.push(root.left);
				} else if (root.data > root.left.data) {
					root.left = null;
				} else {
					return false;
				}
			}

			// Current Node1 is Leaf Node1
			if (root.right == null && root.left == null) {
				setOfValuesTraversed.add(stack.pop().data);
			}
		}
		return true;
	}

	boolean isValidRightSubTree(Node1 tmpRoot1) {
		Stack<Node1> stack = new Stack<Node1>();
		Set<Integer> setOfValuesTraversed = new HashSet<Integer>();
		stack.push(tmpRoot1);
		while (!stack.empty()) {
			tmpRoot1 = stack.peek();
			if (stack.size() == 2 && setOfValuesTraversed.contains(stack.get(0).right.data)) {
				if (stack.get(0).data < stack.get(0).right.data) {
					return false;
				}
				return true;
			}
			// Push if Right Child exist but not traversed yet
			if (tmpRoot1.right != null) {
				if (!setOfValuesTraversed.contains(tmpRoot1.right.data)) {
					stack.push(tmpRoot1.right);
				} else if (tmpRoot1.data < tmpRoot1.right.data) {
					tmpRoot1.right = null;
				} else {
					return false;
				}
			}
			// Push if Left Child exists but not traversed yet
			if (tmpRoot1.left != null) {
				if (!setOfValuesTraversed.contains(tmpRoot1.left.data)) {
					stack.push(tmpRoot1.left);
				} else if (tmpRoot1.data > tmpRoot1.left.data) {
					tmpRoot1.data = tmpRoot1.left.data;
					tmpRoot1.left = null;
				} else {
					return false;
				}
			}

			// Current Node1 is Leaf Node1
			if (tmpRoot1.right == null && tmpRoot1.left == null) {
				setOfValuesTraversed.add(stack.pop().data);
			}
		}
		return true;
	}
}