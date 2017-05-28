package com.lalit.ds.java.queue.api;

public class PriorityqueueViaLinkedList<E> implements PriorityQueue<E> {

	private NodeWithPriority nodeWithPriority;

	@Override
	public boolean enqueue(E element, int priority) {
		if (nodeWithPriority == null) {
			nodeWithPriority = new NodeWithPriority(element, null, null,
					priority);
		} else {
			NodeWithPriority tmpNode = nodeWithPriority;
			while (tmpNode != null) {
				if (tmpNode.priority < priority) {
				} else if (tmpNode.priority > priority) {
					if (tmpNode.previousNode == null
							&& tmpNode.nextNode != null) {
						return setNewElementAtFront(element, priority, tmpNode);
					} else {
						return setNewElementInBetweenTheQueue(element,
								priority, tmpNode);
					}
				}
				if (tmpNode.nextNode == null) {
					return setNewElementAtEnd(element, priority, tmpNode);
				}
				tmpNode = tmpNode.nextNode;
			}
		}
		return false;
	}

	private boolean setNewElementAtEnd(E element, int priority,
			NodeWithPriority tmpNode) {
		NodeWithPriority newNode = new NodeWithPriority(element, null, null,
				priority);
		tmpNode.nextNode = newNode;
		newNode.previousNode = tmpNode;
		return true;
	}

	private boolean setNewElementInBetweenTheQueue(E element, int priority,
			NodeWithPriority tmpNode) {
		NodeWithPriority preNode = tmpNode.previousNode;
		NodeWithPriority newNode = new NodeWithPriority(element, null, null,
				priority);
		tmpNode.previousNode = newNode;
		newNode.nextNode = tmpNode;
		newNode.previousNode = preNode;
		preNode.nextNode = newNode;
		return true;
	}

	private boolean setNewElementAtFront(E element, int priority,
			NodeWithPriority tmpNode) {
		NodeWithPriority newNode = new NodeWithPriority(element, null, null,
				priority);
		tmpNode.previousNode = newNode;
		newNode.nextNode = tmpNode;
		nodeWithPriority = newNode;
		return true;
	}

	@Override
	public E dequeue() {
		E val;
		if (nodeWithPriority == null) {
			throw new IndexOutOfBoundsException("empty queue");
		} else {
			val = nodeWithPriority.val;
			nodeWithPriority = nodeWithPriority.nextNode;
			return val;
		}
	}

	private class NodeWithPriority {

		private E val;
		private NodeWithPriority nextNode;
		private NodeWithPriority previousNode;
		private int priority;

		private NodeWithPriority(E val, NodeWithPriority nextNode,
				NodeWithPriority previousNode, int priority) {
			this.val = val;
			this.nextNode = nextNode;
			this.priority = priority;
			this.previousNode = previousNode;
		}
	}
}