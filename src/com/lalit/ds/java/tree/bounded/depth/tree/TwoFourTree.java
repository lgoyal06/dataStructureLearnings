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
	private static final int MAX_CHILDREN_ALLOWED = 4;
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
		while (rootNode.getParentNode() != null || (rootNode.getValues().size() > MAX_KEY_ALLOWED)) {
			int middleValueIndex = (MAX_KEY_ALLOWED / 2) + 1;

			Node<T> newChildNode_1 = new Node<>();
			Node<T> newChildNode_2 = new Node<>();

			for (int i = 0; i < middleValueIndex; ++i) {
				newChildNode_1.setValues(rootNode.getValueAt(i));
			}
			for (int i = middleValueIndex + 1; i < rootNode.getValues().size(); ++i) {
				newChildNode_2.setValues(rootNode.getValueAt(i));
			}

			if (rootNode.parentNode == null) {
				Node<T> newParentNode = new Node<>();
				newParentNode.setValues(rootNode.getValueAt(middleValueIndex));
				newChildNode_1.setParentNode(newParentNode);
				newChildNode_2.setParentNode(newParentNode);
				newParentNode.setChildNode(newChildNode_1, 0);
				newParentNode.setChildNode(newChildNode_2, 1);
				rootNode = newParentNode;
			} else {
				Node<T> parentNode = rootNode.getParentNode();
				parentNode.setValues(rootNode.getValueAt(middleValueIndex));
				int totalChildren = parentNode.getChildren().size() - 1;
				newChildNode_1.setParentNode(parentNode);
				newChildNode_2.setParentNode(parentNode);
				/** TODO need to fix next 3 lines **/
				parentNode.getChildren().remove(nodeIndex);
				parentNode.setChildNode(newChildNode_1, nodeIndex);
				parentNode.setChildNode(newChildNode_2, nodeIndex + 1);
				/****/
				rootNode = parentNode;
			}
			// TODO Wrong code hardcoded to 0 change
			rootNode = splitAndBuild(rootNode, 0);
		}
		return rootNode;
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