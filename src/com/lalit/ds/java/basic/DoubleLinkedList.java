package com.lalit.ds.java.basic;

public class DoubleLinkedList {

	DequeNode nodeList;

	public boolean add(int n) {
		if (nodeList == null) {
			DequeNode q = new DequeNode(n, null, null);
			nodeList = q;
			return true;
		} else {
			DequeNode tem = nodeList;
			while (tem.next != null) {
				tem = tem.next;
			}
			DequeNode q = new DequeNode(n, null, tem);
			tem.next = q;
			return true;
		}
	}

	public static void main(String... s) {
		DoubleLinkedList test = new DoubleLinkedList();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5);
		System.out.println(test);
	}
}

class DequeNode {

	int value;
	DequeNode next;
	DequeNode previous;

	public DequeNode(int value, DequeNode next, DequeNode previous) {
		this.value = value;
		this.next = next;
		this.previous = previous;
	}
}
