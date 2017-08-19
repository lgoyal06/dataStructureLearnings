package com.lalit.ds.queue.api;

public interface PriorityQueue<E> {

	boolean enqueue(E element, int priority);

	E dequeue();

}
