package com.lalit.ds.java.avl.tree;

public class AVLBSTree<T extends Object> {

	private BinaryTreeNode<T> bst;

	public boolean insertion(T value) {
		if (bst == null) {
			BinaryTreeNode<T> rootNode = new BinaryTreeNode<>();
			rootNode.value = value;
			rootNode.height = 1;
			this.bst = rootNode;
			return true;
		}
		return recursiveFunctionForInsertion(bst, value);
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

	private boolean recursiveFunctionForInsertion(BinaryTreeNode<T> currentNode, T value) {

		BinaryTreeNode<T> newNode = new BinaryTreeNode<>();
		if (compareValue(currentNode.value, value) == 1) {
			buildLeftSubTree(currentNode, value, newNode);
		} else if (compareValue(currentNode.value, value) == -1) {
			buildRightSubTree(currentNode, value, newNode);
		} else {
			return false;
		}
		currentNode.height = Math.max((currentNode.nextLeft == null ? 1 : currentNode.nextLeft.height),
				(currentNode.nextRight == null ? 1 : currentNode.nextRight.height)) + 1;
		if (Math.abs((currentNode.nextLeft == null ? 1 : currentNode.nextLeft.height)
				- (currentNode.nextRight == null ? 1 : currentNode.nextRight.height)) > 1) {
			restructure(currentNode);
		}
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
		currentNode.height = Math.max((currentNode.nextLeft == null ? 1 : currentNode.nextLeft.height),
				(currentNode.nextRight == null ? 1 : currentNode.nextRight.height)) + 1;
		if (Math.abs((currentNode.nextLeft == null ? 1 : currentNode.nextLeft.height)
				- (currentNode.nextRight == null ? 1 : currentNode.nextRight.height)) > 1) {
			restructure(currentNode);
		}
	}

	private void restructure(BinaryTreeNode<T> subTree) {
		inlineTraversing(subTree);
	}

	private void buildLeftSubTree(BinaryTreeNode<T> currentNode, T value, BinaryTreeNode<T> newNode) {
		if (currentNode.nextLeft == null) {
			newNode.value = value;
			newNode.height = 1;
			currentNode.nextLeft = newNode;
		} else {
			recursiveFunctionForInsertion(currentNode.nextLeft, value);
		}
		currentNode.height = Math.max((currentNode.nextLeft == null ? 1 : currentNode.nextLeft.height),
				(currentNode.nextRight == null ? 1 : currentNode.nextRight.height)) + 1;
		if (Math.abs((currentNode.nextLeft == null ? 1 : currentNode.nextLeft.height)
				- (currentNode.nextRight == null ? 1 : currentNode.nextRight.height)) > 1) {
			restructure(currentNode);
		}
	}

	private void inlineTraversing(BinaryTreeNode<T> n) {

		if (n != null) {
			inlineTraversing(n.nextLeft);
			System.out.println(n.value);
			inlineTraversing(n.nextRight);
		}
	}

	private class BinaryTreeNode<E> {

		private E value;
		private BinaryTreeNode<E> nextLeft;
		private BinaryTreeNode<E> nextRight;
		private int height;
		@SuppressWarnings("unused")
		private int inOrderTraverse;

	}

	public static void main(String... s) {

		AVLBSTree<Integer> tree = new AVLBSTree<Integer>();
		tree.insertion(44);
		tree.insertion(17);
		tree.insertion(78);
		tree.insertion(32);
		tree.insertion(50);
		tree.insertion(88);
		tree.insertion(48);
		tree.insertion(62);
		tree.insertion(54);
		System.out.println();
	}
}
