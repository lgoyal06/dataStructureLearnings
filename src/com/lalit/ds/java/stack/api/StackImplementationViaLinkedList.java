package com.lalit.ds.java.stack.api;

public class StackImplementationViaLinkedList {

	Node2 top;

	public boolean push(Node2 n) {
		if (top == null)
			top = n;
		else {
			n.next = top;
			top = n;
		}
		return true;

	}

	public boolean pop() {
		if (top == null) {
			return false;
		} else {
			top = top.next;
			return false;
		}

	}

	public static void main(String... s) {
		StackImplementationViaLinkedList stack = new StackImplementationViaLinkedList();
		stack.push(new Node2(1, null));
		stack.push(new Node2(2, null));
		stack.push(new Node2(3, null));
		stack.push(new Node2(6, null));
		stack.push(new Node2(9, null));
		stack.pop();
		stack.pop();
		stack.pop();
		stack.pop();
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
