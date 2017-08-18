package com.lalit.ds.java.tree.bounded.depth.tree;

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
				this.rootNode = splitAndBuild(currentNode, nodeIndex);
			}
			return true;
		}
		return false;
	}

	private Node<T> splitAndBuild(Node<T> rootNode, int nodeIndex) {
		while (rootNode.getValues().size() > MAX_KEY_ALLOWED) {
			int middleValueIndex = (MAX_KEY_ALLOWED / 2) + 1;

			Node<T> newLeftChildNode = buildLeftNewChildNodes(rootNode, middleValueIndex);
			Node<T> newRightChildNode = buildRightChildNode(rootNode, middleValueIndex);

			if (rootNode.parentNode == null) {
				Node<T> newParentNode = new Node<>();
				newParentNode.setValues(rootNode.getValueAt(middleValueIndex));
				newLeftChildNode.setParentNode(newParentNode);
				newRightChildNode.setParentNode(newParentNode);
				newParentNode.setChildNode(newLeftChildNode, 0);
				newParentNode.setChildNode(newRightChildNode, 1);
				rootNode = newParentNode;
			} else {
				Node<T> parentNode = rootNode.getParentNode();
				parentNode.setValues(rootNode.getValueAt(middleValueIndex));
				int totalChildren = parentNode.getChildren().size() - 1;
				newLeftChildNode.setParentNode(parentNode);
				newRightChildNode.setParentNode(parentNode);
				/** TODO need to fix next 3 lines **/
				parentNode.getChildren().remove(nodeIndex);
				parentNode.setChildNode(newLeftChildNode, nodeIndex);
				parentNode.setChildNode(newRightChildNode, nodeIndex + 1);
				/****/
				rootNode = parentNode;
			}
			// TODO Wrong code hardcoded to 0 change
			rootNode = splitAndBuild(rootNode, 0);
		}
		// Loop until rootNode points to top most node in hierarcy
		while (rootNode.getParentNode() != null) {
			rootNode = rootNode.getParentNode();
		}

		return rootNode;
	}

	private Node<T> buildRightChildNode(Node<T> rootNode, int middleValueIndex) {
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

	private Node<T> buildLeftNewChildNodes(Node<T> rootNode, int middleValueIndex) {
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

		int totalKeys = 0;
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