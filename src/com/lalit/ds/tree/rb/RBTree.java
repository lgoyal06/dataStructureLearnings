package com.lalit.ds.tree.rb;

import java.util.Comparator;

public class RBTree<K, V> {

	private final Comparator<? super K> comparator;
	private static final String RED_COLOR = "R";
	private static final String BLACK_COLOR = "B";
	private Node<K, V> node;

	public RBTree() {
		this.comparator = null;
	}

	public RBTree(Comparator<? super K> comparator) {
		this.comparator = comparator;
	}

	@SuppressWarnings("unchecked")
	private int compareKey(K value1, K value2) {
		if (comparator != null)
			return comparator.compare(value1, value2);
		return ((Comparable<K>) value1).compareTo(value2);
	}

	public boolean add(K key, V value) {
		if (node == null) {
			node = new Node<>(null, null, RED_COLOR, value, key, null);
		} else {
			Node<K, V> currentNode = node;
			while (currentNode != null) {
				int result = compareKey(key, currentNode.key);
				if (result < 0) {
					if (currentNode.nextLeft == null) {
						currentNode.nextLeft = new Node<>(null, null, RED_COLOR, value, key, currentNode);
						if (currentNode.parent != null) {
							reStructureTreeOnInsertion(currentNode.nextLeft);
						}
						return true;
					}
					currentNode = currentNode.nextLeft;
				} else if (result >= 1) {
					if (currentNode.nextRight == null) {
						currentNode.nextRight = new Node<>(null, null, RED_COLOR, value, key, currentNode);
						if (currentNode.parent != null) {
							reStructureTreeOnInsertion(currentNode.nextRight);
						}
						return true;
					}
					currentNode = currentNode.nextRight;
				} else {
					currentNode.value = value;
					return true;
				}
			}
		}
		return true;
	}

	public Node<K, V> getObject(K key) {
		if (node == null) {
			return null;
		}
		Node<K, V> tmpNode = node;
		while (tmpNode != null) {
			int result = compareKey(key, tmpNode.key);
			if (result < 0) {
				tmpNode = tmpNode.nextLeft;
			} else if (result >= 1) {
				tmpNode = tmpNode.nextRight;
			} else {
				return tmpNode;
			}
		}
		return null;
	}

	public V getValue(K key) {
		if (node == null) {
			return null;
		}
		Node<K, V> tmpNode = node;
		while (tmpNode != null) {
			int result = compareKey(key, tmpNode.key);
			if (result < 0) {
				tmpNode = tmpNode.nextLeft;
			} else if (result >= 1) {
				tmpNode = tmpNode.nextRight;
			} else {
				return tmpNode.value;
			}
		}
		return null;
	}

	private void reStructureTreeOnInsertion(Node<K, V> leafNode) {
		if (isParentNodeAlsoRed(leafNode)) {
			if (getUncleColor(leafNode).equals(RED_COLOR)) {
				performRecoloring(leafNode);
				if (isFatherNodePresent(leafNode) && isGrandFatherNodePresent(leafNode)
						&& (compareKey(leafNode.parent.parent.key, node.key) != 0)) {
					reStructureTreeOnInsertion(leafNode.parent.parent);
				}
			} else {
				performRotations(leafNode);
			}
		}
	}

	private boolean isGrandFatherNodePresent(Node<K, V> leafNode) {
		return leafNode.parent.parent != null;
	}

	private boolean isFatherNodePresent(Node<K, V> leafNode) {
		return leafNode.parent != null;
	}

	private void performRecoloring(Node<K, V> leafNode) {
		leafNode.parent.parent.nextRight.color = BLACK_COLOR;
		leafNode.parent.parent.nextLeft.color = BLACK_COLOR;
		if (compareKey(leafNode.parent.parent.key, node.key) == 0)
			leafNode.parent.parent.color = BLACK_COLOR;
		else
			leafNode.parent.parent.color = RED_COLOR;
	}

	private void performRotations(Node<K, V> leafNode) {
		Node<K, V> leaf_parent_parent_parent_node = leafNode.parent.parent.parent;
		Node<K, V> leaf_parent_node;
		if (compareKey(leafNode.parent.key, leafNode.parent.parent.key) < 0) {
			if (compareKey(leafNode.key, leafNode.parent.key) < 0) {
				leaf_parent_node = performLLRotation(leafNode);
			} else {
				leaf_parent_node = performLRRotation(leafNode);
			}
		} else {
			if (compareKey(leafNode.key, leafNode.parent.key) > 0) {
				leaf_parent_node = performRRRotation(leafNode);
			} else {
				leaf_parent_node = performRLRotation(leafNode);
			}
		}
		if (leaf_parent_parent_parent_node != null) {
			if (compareKey(leaf_parent_parent_parent_node.nextLeft.key, leaf_parent_node.key) == 0) {
				leaf_parent_parent_parent_node.nextLeft = leaf_parent_node;
				leaf_parent_node.parent = leaf_parent_parent_parent_node;
			} else {
				leaf_parent_parent_parent_node.nextRight = leaf_parent_node;
				leaf_parent_node.parent = leaf_parent_parent_parent_node;
			}
		} else {
			this.node = leaf_parent_node;
		}
	}

	private Node<K, V> performRLRotation(Node<K, V> leafNode) {
		Node<K, V> node_0 = leafNode;
		Node<K, V> node_1 = leafNode.parent;
		Node<K, V> node_2 = leafNode.parent.parent;
		node_0.parent = node_2.parent;
		node_0.color = BLACK_COLOR;
		Node<K, V> node_0_nextLeft = node_0.nextLeft;
		Node<K, V> node_0_nextRight = node_0.nextRight;
		node_0.nextLeft = node_2;
		node_0.nextRight = node_1;
		node_1.parent = node_0;
		node_2.parent = node_0;
		node_2.nextRight = node_0_nextLeft;
		if (node_0_nextLeft != null)
			node_0_nextLeft.parent = node_2;
		node_1.nextLeft = node_0_nextRight;
		if (node_0_nextRight != null)
			node_0_nextRight.parent = node_1;
		node_1.color = RED_COLOR;
		node_2.color = RED_COLOR;
		return node_0;

	}

	private Node<K, V> performRRRotation(Node<K, V> leafNode) {
		Node<K, V> node_0 = leafNode.parent;
		Node<K, V> node_1 = leafNode.parent.parent;
		node_0.parent = node_1.parent;
		node_0.color = BLACK_COLOR;
		Node<K, V> node_1_nextLeft = node_0.nextLeft;
		node_0.nextLeft = node_1;
		node_1.parent = node_0;
		node_1.nextRight = node_1_nextLeft;
		if (node_1_nextLeft != null)
			node_1_nextLeft.parent = node_1;
		node_1.color = RED_COLOR;
		leafNode.color = RED_COLOR;
		return node_0;

	}

	private Node<K, V> performLRRotation(Node<K, V> leafNode) {
		Node<K, V> node_0 = leafNode;
		Node<K, V> node_1 = leafNode.parent;
		Node<K, V> node_2 = leafNode.parent.parent;
		node_0.parent = node_2.parent;
		node_0.color = BLACK_COLOR;
		Node<K, V> node_0_nextLeft = node_0.nextLeft;
		Node<K, V> node_0_nextRight = node_0.nextRight;
		node_0.nextLeft = node_1;
		node_0.nextRight = node_2;
		node_1.parent = node_0;
		node_2.parent = node_0;
		node_1.nextRight = node_0_nextLeft;
		if (node_0_nextLeft != null)
			node_0_nextLeft.parent = node_1;
		node_2.nextLeft = node_0_nextRight;
		if (node_0_nextRight != null)
			node_0_nextRight.parent = node_2;
		node_1.color = RED_COLOR;
		node_2.color = RED_COLOR;
		return node_0;
	}

	private Node<K, V> performLLRotation(Node<K, V> leafNode) {
		Node<K, V> node_0 = leafNode.parent;
		Node<K, V> node_1 = leafNode.parent.parent;
		node_0.parent = node_1.parent;
		node_0.color = "B";
		Node<K, V> node_1_nextLeft = node_0.nextRight;
		node_0.nextRight = node_1;
		node_1.parent = node_0;
		node_1.nextLeft = node_1_nextLeft;
		if (node_1_nextLeft != null)
			node_1_nextLeft.parent = node_1;
		node_1.color = RED_COLOR;
		leafNode.color = RED_COLOR;
		return node_0;
	}

	private String getUncleColor(Node<K, V> newlyAddedNode) {
		// Uncle is null leaf node Black
		if (newlyAddedNode.parent.parent.nextLeft == null || newlyAddedNode.parent.parent.nextRight == null) {
			return BLACK_COLOR;
		} else if (!newlyAddedNode.parent.color.equals(newlyAddedNode.parent.parent.nextLeft.color)) {
			return newlyAddedNode.parent.parent.nextLeft.color;
		} else if (!newlyAddedNode.parent.color.equals(newlyAddedNode.parent.parent.nextRight.color)) {
			return newlyAddedNode.parent.parent.nextRight.color;
		}
		return newlyAddedNode.parent.color;
	}

	private boolean isParentNodeAlsoRed(Node<K, V> newlyAddedNode) {
		return (newlyAddedNode.color.equals(RED_COLOR)) && newlyAddedNode.parent.color.equals(RED_COLOR);
	}

	public boolean deleteKey(K value) {
		// TODO Auto-generated method stub
		return false;
	}

	private class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> parent;
		private Node<K, V> nextLeft;
		private Node<K, V> nextRight;
		private String color;

		private Node(Node<K, V> nextLeft, Node<K, V> nextRight, String color, V value, K key, Node<K, V> parent) {
			this.nextLeft = nextLeft;
			this.nextRight = nextRight;
			this.color = color;
			this.value = value;
			this.key = key;
			this.parent = parent;
		}

	}
}