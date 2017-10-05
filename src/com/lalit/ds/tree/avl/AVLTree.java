package com.lalit.ds.tree.avl;

import java.util.ArrayList;
import java.util.List;

import com.lalit.ds.tree.bounded.depth.BasicTreeOperations;

public class AVLTree<T extends Object> implements BasicTreeOperations<T> {

	private Node<T> bst;

	public boolean add(T value) {
		if (bst == null) {
			Node<T> rootNode = new Node<>();
			rootNode.value = value;
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

	private void restructure(Node<T> subTree) {

		List<T> terminal = new ArrayList<>();
		List<T> nonTerminal = new ArrayList<>();

		inOrderTraversing(subTree, terminal, nonTerminal);

		Node<T> nonTerminal_node_left = new Node<>();
		Node<T> nonTerminal_node_right = new Node<>();

		subTree.value = nonTerminal.get(1);
		nonTerminal_node_left.value = nonTerminal.get(0);
		nonTerminal_node_right.value = nonTerminal.get(2);
		subTree.nextLeft = nonTerminal_node_left;
		subTree.nextRight = nonTerminal_node_right;

		int indexTraversedTerminal = 0;

		while (terminal.size() > indexTraversedTerminal) {
			Node<T> current_terminal_node = new Node<>();
			current_terminal_node.value = terminal.get(indexTraversedTerminal);
			if (compareValue(current_terminal_node.value, subTree.value) < 0) {
				if (compareValue(current_terminal_node.value, nonTerminal_node_left.value) < 0) {
					nonTerminal_node_left.nextLeft = current_terminal_node;
				} else {
					nonTerminal_node_left.nextRight = current_terminal_node;
				}
			} else {
				if (compareValue(current_terminal_node.value, nonTerminal_node_right.value) < 0) {
					nonTerminal_node_right.nextLeft = current_terminal_node;
				} else {
					nonTerminal_node_right.nextRight = current_terminal_node;
				}
			}
			++indexTraversedTerminal;
		}
		nonTerminal_node_right.height = 2;
		nonTerminal_node_left.height = 2;
		subTree.height = 3;
	}

	private void inOrderTraversing(Node<T> tree, List<T> terminalNode, List<T> nonTerminalNode) {
		if (tree != null) {
			inOrderTraversing(tree.nextLeft, terminalNode, nonTerminalNode);
			if (tree.nextLeft == null && tree.nextRight == null) {
				terminalNode.add(tree.value);
			} else {
				nonTerminalNode.add(tree.value);
			}
			inOrderTraversing(tree.nextRight, terminalNode, nonTerminalNode);
		}
	}

	@Override
	public boolean deleteKey(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	private class Node<E> {

		private E value;
		private Node<E> nextLeft;
		private Node<E> nextRight;
		private int height = 1;
		@SuppressWarnings("unused")
		private int inOrderTraverse;

	}

}
