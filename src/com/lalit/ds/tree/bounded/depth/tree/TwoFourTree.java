package com.lalit.ds.tree.bounded.depth.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lalit goyal
 * 
 * 
 */
public class TwoFourTree<T extends Comparable<T>> {

	private Node<T> rootNode;
	private static final int MAX_KEY_ALLOWED = 3;
	@SuppressWarnings("unchecked")
	private BinaryApproximationSearch<T> binaryApproximationSearch = BinaryApproximationSearch.getInstance();

	public boolean add(T value) {
		if (rootNode == null) {
			rootNode = new Node<T>();
			rootNode.setValues(value);
			return true;
		} else {
			return insertion(value, rootNode, 0);
		}
	}

	private boolean insertion(T value, Node<T> currentNode, int nodeIndex) {
		if (currentNode.getChildren().size() > 0) {
			int indexChild = binaryApproximationSearch.getChildNodeIndex(currentNode.getValues(), value);
			insertion(value, currentNode.getChildNode(indexChild), indexChild);
		} else {
			currentNode.setValues(value);
			if (currentNode.getTotalKeys() > MAX_KEY_ALLOWED) {
				this.rootNode = splitAndRebuildTree(currentNode, nodeIndex);
			}
			return true;
		}
		return false;
	}

	private Node<T> splitAndRebuildTree(Node<T> currentNode, int index) {
		while (currentNode.getValues().size() > MAX_KEY_ALLOWED) {
			int middleKeyIndex = (MAX_KEY_ALLOWED / 2) + 1;

			Node<T> newLeftChildNode = buildNewLeftChildNodes(currentNode, middleKeyIndex);
			Node<T> newRightChildNode = buildNewRightChildNode(currentNode, middleKeyIndex);
			Node<T> updatedParentNode = rebuildParentNode(currentNode, index, middleKeyIndex, newLeftChildNode,
					newRightChildNode);

			currentNode = updatedParentNode;
			if (currentNode.getParentNode() != null)
				index = binaryApproximationSearch.getChildNodeIndex(currentNode.getParentNode().getValues(),
						currentNode.getValueAt(0));
			else
				index = 0;
			currentNode = splitAndRebuildTree(currentNode, index);
		}

		while (currentNode.getParentNode() != null) {
			currentNode = currentNode.getParentNode();
		}

		return currentNode;
	}

	private Node<T> rebuildParentNode(Node<T> rootNode, int nodeIndex, int middleValueIndex, Node<T> newLeftChildNode,
			Node<T> newRightChildNode) {
		Node<T> parentNode = rootNode.parentNode == null ? new Node<>() : rootNode.getParentNode();
		parentNode.setValues(rootNode.getValueAt(middleValueIndex));

		newLeftChildNode.setParentNode(parentNode);
		newRightChildNode.setParentNode(parentNode);

		if (parentNode.getChildren().size() > 0) {
			parentNode.getChildren().remove(nodeIndex);
		}
		parentNode.setChildNode(newLeftChildNode, nodeIndex);
		parentNode.setChildNode(newRightChildNode, nodeIndex + 1);
		return parentNode;
	}

	private Node<T> buildNewRightChildNode(Node<T> rootNode, int middleValueIndex) {
		Node<T> newChildNode_2 = new Node<>();
		for (int i = middleValueIndex + 1; i < rootNode.getValues().size(); ++i) {
			newChildNode_2.setValues(rootNode.getValueAt(i));
		}
		if (rootNode.getChildren().size() > 1) {
			for (int i = middleValueIndex + 1; i <= rootNode.getValues().size(); ++i) {
				Node<T> childNode = rootNode.getChildNode(i);
				childNode.setParentNode(newChildNode_2);
				newChildNode_2.setChildNode(childNode);
			}
		}
		return newChildNode_2;
	}

	private Node<T> buildNewLeftChildNodes(Node<T> rootNode, int middleValueIndex) {
		Node<T> newChildNode_1 = new Node<>();

		for (int i = 0; i < middleValueIndex; ++i) {
			newChildNode_1.setValues(rootNode.getValueAt(i));
		}

		if (rootNode.getChildren().size() > 1) {
			for (int i = 0; i <= middleValueIndex; ++i) {
				Node<T> childNode = rootNode.getChildNode(i);
				childNode.setParentNode(newChildNode_1);
				newChildNode_1.setChildNode(childNode);
			}
		}
		return newChildNode_1;
	}

	private class Node<T extends Comparable<T>> {

		private int totalKeys = 0;
		private Node<T> parentNode;
		private ArrayList<Node<T>> children = new ArrayList<Node<T>>();
		private ArrayList<T> values = new ArrayList<T>();

		public void setParentNode(Node<T> parent) {
			this.parentNode = parent;
		}

		public Node<T> getParentNode() {
			return parentNode;
		}

		public ArrayList<Node<T>> getChildren() {
			return children;
		}

		public void setChildNode(Node<T> child, int index) {
			children.add(index, child);
		}

		public void setChildNode(Node<T> child) {
			children.add(child);
		}

		public Node<T> getChildNode(int index) {
			return children.get(index);
		}

		public ArrayList<T> getValues() {
			return values;
		}

		public T getValueAt(int index) {
			return values.get(index);
		}

		public void setValues(T value) {
			values.add(totalKeys, value);
			Collections.sort((List<T>) values);
			++totalKeys;
		}

		public int getTotalKeys() {
			return totalKeys;
		}
	}
}