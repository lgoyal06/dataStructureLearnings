package com.lalit.ds.java.avl.tree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Object> {

	private Node<T> bst;

	public boolean insertion(T value) {
		if (bst == null) {
			Node<T> rootNode = new Node<>();
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

	private boolean recursiveFunctionForInsertion(Node<T> currentNode, T value) {

		Node<T> newNode = new Node<>();
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

	private void buildRightSubTree(Node<T> currentNode, T value, Node<T> newNode) {
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

	private void buildLeftSubTree(Node<T> currentNode, T value, Node<T> newNode) {
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

	List<T> terminal = new ArrayList<>();
	List<T> nonTerminal = new ArrayList<>();

	// TODO build new tree
	// TODO Complete on Dated 22nd at 4:15 am to 5:15 am
	private void restructure(Node<T> subTree) {

		inlineTraversing(subTree);
		Node<T> rootNode = new Node<>();
		rootNode.value = nonTerminal.get(1);

		Node<T> nonTerminal_node_left = new Node<>();
		nonTerminal_node_left.value = nonTerminal.get(0);

		Node<T> nonTerminal_node_right = new Node<>();
		nonTerminal_node_right.value = nonTerminal.get(2);

		rootNode.nextLeft = nonTerminal_node_left;
		rootNode.nextRight = nonTerminal_node_right;

		if (compareValue(terminal.get(0), nonTerminal_node_left.value) < 0) {
			Node<T> terminal_node_1 = new Node<>();
			terminal_node_1.value = terminal.get(0);
			nonTerminal_node_left.nextLeft = terminal_node_1;
		}

		if (compareValue(terminal.get(1), nonTerminal_node_left.value) > 0) {
			if (compareValue(terminal.get(1), rootNode.value) < 0) {
				Node<T> terminal_node_2 = new Node<>();
				terminal_node_2.value = terminal.get(1);
				nonTerminal_node_left.nextRight = terminal_node_2;
			}
		}

		if (compareValue(terminal.get(2), nonTerminal_node_right.value) > 0) {
			Node<T> terminal_node_3 = new Node<>();
			terminal_node_3.value = terminal.get(2);
			nonTerminal_node_right.nextLeft = terminal_node_3;
		}
		System.exit(0);
	}

	private void inlineTraversing(Node<T> tree) {

		if (tree != null) {
			inlineTraversing(tree.nextLeft);
			if (tree.nextLeft == null && tree.nextRight == null) {
				terminal.add(tree.value);
			} else {
				nonTerminal.add(tree.value);
			}
			inlineTraversing(tree.nextRight);
		}
	}

	private class Node<E> {

		private E value;
		private Node<E> nextLeft;
		private Node<E> nextRight;
		private int height;
		@SuppressWarnings("unused")
		private int inOrderTraverse;

	}

	public static void main(String... s) {

		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.insertion(44);
		tree.insertion(17);
		tree.insertion(78);
		tree.insertion(32);
		tree.insertion(50);
		tree.insertion(88);
		tree.insertion(48);
		tree.insertion(62);
		tree.insertion(64);
		tree.insertion(54);
		System.out.println();
	}
}
