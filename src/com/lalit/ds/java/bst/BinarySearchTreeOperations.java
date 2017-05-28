package com.lalit.ds.java.bst;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeOperations {

	BinaryTreeNode bst;

	public void insertion(BinaryTreeNode n) {

		if (bst == null) {
			bst = n;
			System.out.println(true);
		} else {
			System.out.println(recursiveFunctionForInsertion(bst, n));
		}
	}

	public boolean searching(BinaryTreeNode n) {

		if (bst == null) {
			return false;
		} else {
			return recursiveFunctionForSearch(bst, n);
		}
	}

	public BinaryTreeNode searchingParentBinaryTreeNode(BinaryTreeNode n) {

		if (bst == null) {
			return null;
		} else {
			return recursiveFunctionForSearchingParentNode(bst, n);
		}
	}

	public BinaryTreeNode recursiveFunctionForSearchingParentNode(BinaryTreeNode currentNode,
			BinaryTreeNode searchingNode) {

		if (searchingNode.value < currentNode.value) {
			if (currentNode.nextLeft == null) {
				return null;
			} else if (currentNode.nextLeft.value == searchingNode.value) {
				return currentNode;
			} else {
				return recursiveFunctionForSearchingParentNode(currentNode.nextLeft, searchingNode);
			}
		} else {
			if (currentNode.nextRight == null) {
				return null;
			} else if (currentNode.nextRight.value == searchingNode.value) {
				return currentNode;
			} else {
				return recursiveFunctionForSearchingParentNode(currentNode.nextRight, searchingNode);
			}
		}
	}

	public boolean recursiveFunctionForInsertion(BinaryTreeNode currentNode, BinaryTreeNode nodeToAdd) {

		if (nodeToAdd.value < currentNode.value) {
			if (currentNode.nextLeft == null) {
				currentNode.nextLeft = nodeToAdd;
				return true;
			} else {
				return recursiveFunctionForInsertion(currentNode.nextLeft, nodeToAdd);
			}
		} else {
			if (currentNode.nextRight == null) {
				currentNode.nextRight = nodeToAdd;
				return true;
			} else {
				return recursiveFunctionForInsertion(currentNode.nextRight, nodeToAdd);
			}

		}
	}

	public boolean recursiveFunctionForSearch(BinaryTreeNode currentNode, BinaryTreeNode searchingNode) {

		if (searchingNode.value < currentNode.value) {
			if (currentNode.nextLeft == null) {
				return false;
			} else if (currentNode.nextLeft.value == searchingNode.value) {
				return true;
			} else {
				return recursiveFunctionForSearch(currentNode.nextLeft, searchingNode);
			}
		} else {
			if (currentNode.nextRight == null) {
				return false;
			} else if (currentNode.nextRight.value == searchingNode.value) {
				return true;
			} else {
				return recursiveFunctionForSearch(currentNode.nextRight, searchingNode);
			}
		}
	}

	public BinaryTreeNode maxValue() {
		BinaryTreeNode n = bst;
		while (n.nextRight != null) {
			n = n.nextRight;
		}
		return n;

	}

	public BinaryTreeNode minValue() {
		BinaryTreeNode n = bst;
		while (n.nextLeft != null) {
			n = n.nextLeft;
		}
		return n;

	}

	public boolean delete(BinaryTreeNode nodeToDelete) {

		if (bst == null)
			return false;
		else
			recursiveFunctionForDeletion(bst, nodeToDelete);

		return true;
	}

	public boolean recursiveFunctionForDeletion(BinaryTreeNode currentNode, BinaryTreeNode nodeToDelete) {

		if (nodeToDelete.value < currentNode.value) {
			if (currentNode.nextLeft == null) {
				return false;
			} else if (currentNode.nextLeft.value == nodeToDelete.value && currentNode.nextLeft.nextLeft == null
					&& currentNode.nextLeft.nextRight == null) {
				currentNode.nextLeft = null;
				return true;
			} else if (currentNode.nextLeft.value == nodeToDelete.value && currentNode.nextLeft.nextLeft != null
					&& currentNode.nextLeft.nextRight != null) {

				BinaryTreeNode tmpLeftSubTree = currentNode.nextLeft.nextLeft;
				currentNode.nextLeft = currentNode.nextLeft.nextRight;
				BinaryTreeNode tmp1 = currentNode.nextLeft;
				while (tmp1.nextLeft != null) {
					tmp1 = tmp1.nextLeft;
				}
				tmp1.nextLeft = tmpLeftSubTree;
				return true;
			} else {
				return recursiveFunctionForDeletion(currentNode.nextLeft, nodeToDelete);
			}
		} else {
			if (currentNode.nextRight == null) {
				return false;
			} else if (currentNode.nextRight.value == nodeToDelete.value && currentNode.nextRight.nextLeft == null
					&& currentNode.nextRight.nextRight == null) {
				currentNode.nextRight = null;
				return true;
			} else if (currentNode.nextRight.value == nodeToDelete.value && currentNode.nextRight.nextLeft != null
					&& currentNode.nextRight.nextRight != null) {

				BinaryTreeNode tmpLeftSubTree = currentNode.nextRight.nextLeft;
				currentNode.nextRight = currentNode.nextRight.nextRight;
				BinaryTreeNode tmp1 = currentNode.nextRight;
				while (tmp1.nextLeft != null) {
					tmp1 = tmp1.nextLeft;
				}
				tmp1.nextLeft = tmpLeftSubTree;
				return true;
			} else {
				return recursiveFunctionForDeletion(currentNode.nextRight, nodeToDelete);
			}
		}
	}

	/**
	 * No. 45 - Closest Node in a Binary Search Tree Given a binary search tree
	 * and a value k, please find a node in the binary search tree whose value
	 * is closest to k.
	 * 
	 **/
	public BinaryTreeNode searchingClosestNode(int k) {
		BinaryTreeNode closedNode = null;
		if (bst == null) {
			return null;
		} else {
			return recursiveFunctionForClosestNodeSearch(bst, closedNode, k);
		}
	}

	public BinaryTreeNode recursiveFunctionForClosestNodeSearch(BinaryTreeNode root, BinaryTreeNode closestNode,
			int k) {

		while (root != null) {
			if (k < root.value) {
				closestNode = closedNode(closestNode, root, k);
				return recursiveFunctionForClosestNodeSearch(root.nextLeft, closestNode, k);
			} else if (k > root.value) {
				closestNode = closedNode(closestNode, root, k);
				return recursiveFunctionForClosestNodeSearch(root.nextRight, closestNode, k);
			} else {
				return root;
			}
		}
		return closestNode;
	}

	private BinaryTreeNode closedNode(BinaryTreeNode a, BinaryTreeNode b, int k) {
		if (a != null) {
			int differenceFromNodeA = Math.abs((k - a.value));
			int differenceFromNodeB = Math.abs((k - b.value));
			return differenceFromNodeA < differenceFromNodeB ? a : b;
		} else {
			return b;
		}
	}

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
		BinarySearchTreeOperations bst = new BinarySearchTreeOperations();

		bst.insertion(new BinaryTreeNode(100));
		bst.insertion(new BinaryTreeNode(110));
		bst.insertion(new BinaryTreeNode(125));
		bst.insertion(new BinaryTreeNode(120));
		bst.insertion(new BinaryTreeNode(130));
		bst.insertion(new BinaryTreeNode(115));
		bst.insertion(new BinaryTreeNode(124));
		bst.insertion(new BinaryTreeNode(90));
		bst.insertion(new BinaryTreeNode(95));
		bst.insertion(new BinaryTreeNode(80));
		bst.insertion(new BinaryTreeNode(82));
		bst.insertion(new BinaryTreeNode(75));
		bst.insertion(new BinaryTreeNode(92));
		bst.insertion(new BinaryTreeNode(98));
		bst.insertion(new BinaryTreeNode(118));
		System.out.println(bst.maxDepthLevelOrder(bst.bst));

		// System.out.println(bst.searching(new BinaryTreeNode(18)));
		// System.out.println(bst.maxValue().value);
		// System.out.println(bst.minValue().value);
		// System.out.println(bst.searchingParentBinaryTreeNode(new
		// BinaryTreeNode(13)).value);
		// bst.delete(new BinaryTreeNode(110));
		// System.out.println(bst.searchingClosestNode(9).value);
	}
}