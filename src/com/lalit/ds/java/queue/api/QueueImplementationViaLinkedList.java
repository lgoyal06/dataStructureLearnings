package com.lalit.ds.java.queue.api;

public class QueueImplementationViaLinkedList {

	Node3 queue;

	public boolean enqueue(Node3 n) {
		if (queue == null) {
			queue = n;
			return true;
		} else {
			Node3 tem = queue;
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
		queue.enqueue(new Node3(1, null));
		queue.enqueue(new Node3(2, null));
		queue.enqueue(new Node3(3, null));
		queue.enqueue(new Node3(6, null));
		queue.enqueue(new Node3(9, null));
		queue.deqeue();
		queue.deqeue();
		queue.deqeue();
		queue.deqeue();
		System.out.println("hello");
	}
}

class Node3 {
	int x;
	Node3 next;

	public Node3(int x, Node3 n1) {
		this.x = x;
		this.next = n1;
	}
}