package com.lalit.ds.java.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author lalit goyal
 * 
 *         {@link Class} Node
 * @add
 * @delete
 * @reverse
 * @searching
 * @deleteLooping
 * 
 */
// Simply Linked List
public class Node {

	Integer val;
	Node next;

	public void setVal(int val) {
		this.val = val;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	// add at the end i.e. at tail
	public boolean add(int val) {
		if (this.val == null) {
			this.val = val;
			this.next = null;
			return true;
		} else if (this != null) {
			Node node = new Node();
			node.val = val;
			node.next = null;
			Node n = this;
			while (n.next != null)
				n = n.next;
			n.next = node;
			return true;
		}
		return true;
	}

	public static Node5 createNodeWithLoop() {
		Node5 node1 = new Node5();
		Node5 node2 = new Node5();
		Node5 node3 = new Node5();
		Node5 node4 = new Node5();

		node1.val = 1;
		node1.next = node2;

		node2.val = 2;
		node2.next = node3;

		node3.val = 3;
		node3.next = node4;

		node4.val = 4;
		node4.next = node1;

		return node1;

	}

	// delete any value
	public boolean deletion(Integer val) {
		if (this == null) {
			System.out.println("Empty LinkedList");
			return false;
		} else if (this != null) {
			Node n = this, pre = this;
			boolean isFirstNodeTobeRemoved = true;
			while (n.val != val) {
				isFirstNodeTobeRemoved = false;
				pre = n;
				n = n.next;
			}
			if (n.next == null) {
				pre.next = null;
			} else if (!isFirstNodeTobeRemoved) {
				pre.next = n.next;
			} else if (isFirstNodeTobeRemoved) {
				n.val = n.next.val;
				n.next = n.next.next;
			}
			return true;
		}
		return true;
	}

	public boolean searching(Integer val) {
		Node n = this;
		while (n.next != null) {
			if (n.val == val) {
				return true;
			}
			n = n.next;
		}
		return false;
	}

	public String toString() {
		Node x = this;
		String val = "";
		while (x.next != null) {
			val = val + "," + x.val.toString();
			x = x.next;
		}
		return val + "," + x.val.toString();
	}

	public Node reverse(Node original) {

		Node reversedNode = null;

		while (original != null) {
			if (reversedNode == null) {
				Node newNode = new Node();
				newNode.val = original.val;
				newNode.next = null;
				reversedNode = newNode;
			} else {
				Node newNode = new Node();
				newNode.val = original.val;
				newNode.next = reversedNode;
				reversedNode = newNode;
			}
			original = original.next;
		}

		return reversedNode;

	}

	/**
	 * 
	 * @param linkedList
	 *            O(n)
	 * 
	 * @return
	 */
	public static Node5 findAndRemoveLooping(Node5 linkedList) {

		List<Node5> traversedNodeList = new ArrayList<>();
		boolean isLoopFound = false;

		Node5 currentNode = linkedList;
		if (linkedList == null) {
			return null;
		} else {
			Node5 nextNode = currentNode.next;
			traversedNodeList.add(currentNode);
			while (nextNode.next != null && !isLoopFound) {
				traversedNodeList.add(nextNode);
				currentNode = currentNode.next;
				nextNode = nextNode.next;
				isLoopFound = traversedNodeList.contains(nextNode);
			}
			if (isLoopFound) {
				currentNode.next = null;
			}
		}
		return currentNode;
	}

	/*
	 * K-th Node from End
	 */

	public static Node findKthNodeFromEnd(Node node, int nodeToBeRemoved) {

		int length = 0;
		Node tmp = node;
		Node tmp2 = node;
		if (node == null) {
			return null;
		} else {
			++length;
			while (tmp.next != null) {
				++length;
				tmp = tmp.next;
			}
			for (int i = 0; i < (length - nodeToBeRemoved); ++i) {
				tmp2 = tmp2.next;
			}
		}

		return tmp2;
	}

	/**
	 * 
	 * 54321+876
	 * 
	 * **/
	public static Node addTwoList(Node a, Node b) {
		Node c = new Node();
		boolean isAdditionDone = true;
		int carry = 0, sum = 0;
		while (isAdditionDone) {
			if (a.next == null && b.next == null)
				isAdditionDone = false;
			sum = (a.val + b.val + carry) % 10;
			if (a.val + b.val + carry >= 10)
				carry = (a.val + b.val + carry) / 10;
			else
				carry = 0;
			if (a.next != null)
				a = a.next;
			else if (b.next != null) {
				a.val = 0;
				a.next = null;
			}
			if (b.next != null)
				b = b.next;
			else if (a.next != null) {
				b.val = 0;
				b.next = null;
			}
			c.add(sum);
		}
		return c;

	}

	public static void main(String... s) {
		Node linked = new Node();
		linked.add(5);
		linked.add(6);
		linked.add(3);

		Node linked1 = new Node();
		linked1.add(8);
		linked1.add(4);
		linked1.add(2);

		System.out.println(addTwoList(linked, linked1));
		// System.out.println(findKthNodeFromEnd(linked, 4).val);
		// System.out.println(linked);
		// System.out.println(linked.reverse(linked));
		// linked.deletion(5);
		// System.out.println(linked);
		// System.out.println(linked.searching(35));
		// Node5 node = createNodeWithLoop();
		// findAndRemoveLooping(node);
	}
}

class Node5 {
	Integer val;
	Node5 next;

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public Node5 getNext() {
		return next;
	}

	public void setNext(Node5 next) {
		this.next = next;
	}

}
