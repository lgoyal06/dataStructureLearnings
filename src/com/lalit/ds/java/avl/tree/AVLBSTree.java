package com.lalit.ds.java.avl.tree;

public class AVLBSTree<T extends Object> {

	private BinaryTreeNode<T> bst;

	public boolean insertion(T n) {
		if (bst == null) {
			BinaryTreeNode<T> rootNode = new BinaryTreeNode<>();
			rootNode.value = n;
			rootNode.height = 1;
			this.bst = rootNode;
			return true;
		}
		return recursiveFunctionForInsertion(bst, n);
	}

	private int compareValue(T value1, T value2) {
		if (value1 instanceof Integer && value2 instanceof Integer) {
			if (((Integer) value1).intValue() < ((Integer) value2).intValue()) {
				return -1;
			} else if (((Integer) value1).intValue() > ((Integer) value2).intValue()) {
				return 1;
			}
			return 0;
		}
		return 0;
	}

	public boolean recursiveFunctionForInsertion(BinaryTreeNode<T> currentNode, T value) {

		BinaryTreeNode<T> newNode = new BinaryTreeNode<>();
		if (compareValue(currentNode.value, value) == 1) {
			buildLeftSubTree(currentNode, value, newNode);
		} else if (compareValue(currentNode.value, value) == -1) {
			buildRightSubTree(currentNode, value, newNode);
		} else {
			return false;
		}
		currentNode.height = Math.max((currentNode.nextLeft == null ? 0 : currentNode.nextLeft.height),
				(currentNode.nextRight == null ? 0 : currentNode.nextRight.height)) + 1;
		System.out.println(Math.abs((currentNode.nextLeft == null ? 0 : currentNode.nextLeft.height)
				- (currentNode.nextRight == null ? 0 : currentNode.nextRight.height)) > 1
						? "InBalance in AVL Tree Detected. I am calling Refactoring" : "");
		return true;
	}

	private void buildRightSubTree(BinaryTreeNode<T> currentNode, T value, BinaryTreeNode<T> newNode) {
		if (currentNode.nextRight == null) {
			newNode.value = value;
			newNode.height = 1;
			currentNode.nextRight = newNode;
		} else {
			recursiveFunctionForInsertion(currentNode.nextRight, value);
		}
		currentNode.height = Math.max((currentNode.nextLeft == null ? 0 : currentNode.nextLeft.height),
				(currentNode.nextRight == null ? 0 : currentNode.nextRight.height)) + 1;
		System.out.println(Math.abs((currentNode.nextLeft == null ? 0 : currentNode.nextLeft.height)
				- (currentNode.nextRight == null ? 0 : currentNode.nextRight.height)) > 1
						? "InBalance in AVL Tree Detected. I am calling Refactoring" : "");
	}

	private void buildLeftSubTree(BinaryTreeNode<T> currentNode, T value, BinaryTreeNode<T> newNode) {
		if (currentNode.nextLeft == null) {
			newNode.value = value;
			newNode.height = 1;
			currentNode.nextLeft = newNode;
		} else {
			recursiveFunctionForInsertion(currentNode.nextLeft, value);
		}
		currentNode.height = Math.max((currentNode.nextLeft == null ? 0 : currentNode.nextLeft.height),
				(currentNode.nextRight == null ? 0 : currentNode.nextRight.height)) + 1;
		System.out.println(Math.abs((currentNode.nextLeft == null ? 0 : currentNode.nextLeft.height)
				- (currentNode.nextRight == null ? 0 : currentNode.nextRight.height)) > 1
						? "InBalance in AVL Tree Detected. I am calling Refactoring" : "");
	}

	private class BinaryTreeNode<E> {

		private E value;
		private BinaryTreeNode<E> nextLeft;
		private BinaryTreeNode<E> nextRight;
		private int height;

	}

	public static void main(String... s) {

		AVLBSTree<Integer> tree = new AVLBSTree<Integer>();
		tree.insertion(8);
		tree.insertion(2);
		tree.insertion(4);
		tree.insertion(1);
		tree.insertion(7);
		tree.insertion(40);
		tree.insertion(42);
		System.out.println();
	}
}
