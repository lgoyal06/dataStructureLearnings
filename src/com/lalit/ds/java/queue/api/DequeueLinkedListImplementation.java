package com.lalit.ds.java.queue.api;

public final class DequeueLinkedListImplementation<E> implements Dequeue<E> {

	private Node nodes;

	private class Node {

		private E val;
		private Node nextNode;

		private Node(E val, Node nextNode) {
			this.val = val;
			this.nextNode = nextNode;
		}
	}

	@Override
	public boolean enqueueAtRear(E val) {
		if (nodes == null) {
			Node newNode = new Node(val, null);
			nodes = newNode;
			return true;
		} else {
			Node newNode = new Node(val, null);
			Node tmpNode = nodes;
			while (tmpNode.nextNode != null) {
				tmpNode = tmpNode.nextNode;
			}
			tmpNode.nextNode = newNode;
			return true;
		}
	}

	@Override
	public E dequeueFromRear() {
		if (nodes == null) {
			throw new IndexOutOfBoundsException("empty queue");
		} else {
			Node tmpNode = nodes;
			Node newNode = null;
			Node tmp = null;
			while (tmpNode.nextNode != null) {
				if (newNode == null) {
					Node n = new Node(tmpNode.val, null);
					tmp = newNode = n;
				} else {
					while (tmp.nextNode != null) {
						tmp = tmp.nextNode;
					}
					tmp.nextNode = new Node(tmpNode.val, null);
				}
				tmpNode = tmpNode.nextNode;
			}
			nodes = newNode;
			return tmpNode.val;
		}
	}

	@Override
	public boolean enqueueAtFront(E val) {
		nodes = new Node(val, nodes);
		return true;
	}

	@Override
	public E dequeueFromFront() {
		E val;
		if (nodes == null) {
			throw new IndexOutOfBoundsException("empty queue");
		} else {
			val = nodes.val;
			nodes = nodes.nextNode;
			return val;
		}
	}
}