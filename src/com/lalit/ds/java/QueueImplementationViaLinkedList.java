package com.lalit.ds.java;

public class QueueImplementationViaLinkedList {

	Node2 queue;

	public boolean enqueue(Node2 n) {
		if (queue == null) {
			queue = n;
			return true;
		} else {
			Node2 tem = queue;
			while (tem.next != null)
				tem = tem.next;
			tem.next = n;
			return true;
		}
	}

	public boolean deqeue() {
		if (queue == null) {
			return false;
		} else {
			queue = queue.next;
			return true;
		}

	}

	public static void main(String... s) {
		QueueImplementationViaLinkedList queue = new QueueImplementationViaLinkedList();
		queue.enqueue(new Node2(1, null));
		queue.enqueue(new Node2(2, null));
		queue.enqueue(new Node2(3, null));
		queue.enqueue(new Node2(6, null));
		queue.enqueue(new Node2(9, null));
		queue.deqeue();
		queue.deqeue();
		queue.deqeue();
		queue.deqeue();
		System.out.println("hello");
	}
}

class Node2 {
	int x;
	Node2 next;

	public Node2(int x, Node2 n1) {
		this.x = x;
		this.next = n1;
	}
}