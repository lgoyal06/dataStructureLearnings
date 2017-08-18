package com.lalit.ds.java.tree.bst;

import com.lalit.ds.java.basic.DoubleLinkedList;

public class BinarySearchTreeTraversingAlgorithm {

	DoubleLinkedList dll = new DoubleLinkedList();

	public void breathFirstSearch(BinaryTreeNode n) {
		Queue queue = new Queue();
		while (n != null) {
			System.out.println(n.value);
			if (n.nextLeft != null)
				queue.enqueue(n.nextLeft);
			if (n.nextRight != null)
				queue.enqueue(n.nextRight);
			if (!queue.isEmpty()) {
				n = queue.deqeue().value;
			} else
				n = null;
		}
	}

	/**
	 * Given Sorted Binary Search Tree i.e. Left SubTree values < root value and
	 * right subtree values > root value
	 * 
	 * Step 1: Apply inline Traverse and it will return the nodes in the Sorted
	 * Order
	 * 
	 * Step 2: Push it into the Double Linked List Data Structure Class
	 * 
	 * @return Sorted Double Linked List
	 **/
	public DoubleLinkedList binarySearchTreeToSortedDoubleLinkedList(BinaryTreeNode n) {

		if (n != null) {
			inlineTraversing(n.nextLeft);
			dll.add(n.value);
			inlineTraversing(n.nextRight);
		} else {

		}
		return dll;
	}

	// Node visit order is left,root,right
	public void inlineTraversing(BinaryTreeNode n) {

		if (n != null) {
			inlineTraversing(n.nextLeft);
			System.out.println(n.value);
			System.out.println(n.value);
			inlineTraversing(n.nextRight);
		}
	}

	// Node visit order is root,left,right
	public void preTraversing(BinaryTreeNode n) {

		if (n != null) {
			System.out.println(n.value);
			preTraversing(n.nextLeft);
			preTraversing(n.nextRight);
		}
	}

	// Node visit order is left,right,root,
	public void postTraversing(BinaryTreeNode n) {

		if (n != null) {
			postTraversing(n.nextLeft);
			postTraversing(n.nextRight);
			System.out.println(n.value);
		}
	}

	public static void main(String... s) {
		BinarySearchTreeOperations bst = new BinarySearchTreeOperations();
		bst.insertion(new BinaryTreeNode(100));
		bst.insertion(new BinaryTreeNode(90));
		bst.insertion(new BinaryTreeNode(110));
		bst.insertion(new BinaryTreeNode(80));
		bst.insertion(new BinaryTreeNode(70));
		bst.insertion(new BinaryTreeNode(95));
		bst.insertion(new BinaryTreeNode(92));
		bst.insertion(new BinaryTreeNode(97));
		bst.insertion(new BinaryTreeNode(105));
		bst.insertion(new BinaryTreeNode(115));
		bst.insertion(new BinaryTreeNode(120));
		bst.insertion(new BinaryTreeNode(112));
		// bst.insertion(new BinaryTreeNode(23));
		// bst.insertion(new BinaryTreeNode(14));
		// bst.insertion(new BinaryTreeNode(31));
		// bst.insertion(new BinaryTreeNode(7));
		// bst.insertion(new BinaryTreeNode(17));
		// bst.insertion(new BinaryTreeNode(9));
		// new BinarySearchTreeTraversingAlgo().breathFirstSearch(bst.bst);
		// new BinarySearchTreeTraversingAlgo().preTraversing(bst.bst);
		// new BinarySearchTreeTraversingAlgo().postTraversing(bst.bst);
		BinarySearchTreeTraversingAlgorithm obj = new BinarySearchTreeTraversingAlgorithm();
		DoubleLinkedList dll = obj.binarySearchTreeToSortedDoubleLinkedList(bst.bst);
		System.out.println(dll);
	}
}

class Queue {

	QueueNode queue;

	public boolean isEmpty() {
		if (queue != null)
			return queue.value == null;
		return true;
	}

	public boolean enqueue(BinaryTreeNode n) {
		if (queue == null) {
			QueueNode q = new QueueNode(n, null);
			queue = q;
			return true;
		} else {
			QueueNode tem = queue;
			while (tem.next != null) {
				tem = tem.next;
			}
			QueueNode q = new QueueNode(n, null);
			tem.next = q;
			return true;
		}
	}

	public QueueNode deqeue() {
		if (queue == null) {
			return null;
		} else {
			QueueNode tmp = queue;
			if (queue.next != null) {
				queue = queue.next;
				return tmp;
			} else {
				queue = null;
				return tmp;
			}
		}
	}
}

class QueueNode {

	BinaryTreeNode value;
	QueueNode next;

	public QueueNode(BinaryTreeNode value, QueueNode next) {
		this.value = value;
		this.next = next;
	}

	public BinaryTreeNode getValue() {
		return value;
	}

	public void setValue(BinaryTreeNode value) {
		this.value = value;
	}

	public QueueNode getNext() {
		return next;
	}

	public void setNext(QueueNode next) {
		this.next = next;
	}
}