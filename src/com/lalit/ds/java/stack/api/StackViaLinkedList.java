package com.lalit.ds.java.stack.api;

public class StackViaLinkedList<T> {

	private Node<T> top;
	private int size = 0;

	class Node<N> {

		Node<N> next;
		N value;

		public Node<N> getNext() {
			return next;
		}

		public void setNext(Node<N> next) {
			this.next = next;
		}

		public N getValue() {
			return value;
		}

		public void setValue(N value) {
			this.value = value;
		}

	}

	public boolean push(T value) {
		Node<T> node = new Node<T>();
		node.value = value;
		if (top == null) {
			top = node;
		} else {
			node.next = top;
			top = node;
		}
		++size;
		return true;
	}

	public boolean pop() {
		if (top == null) {
			return false;
		}
		Node<T> tmp = top.next;
		top = tmp;
		--size;
		return true;
	}

	public T peak() {
		if (top == null) {
			return null;
		}
		return top.value;
	}

	public int size() {
		return size;
	}
}
