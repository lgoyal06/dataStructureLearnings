package com.lalit.ds.java.queue.api;


public interface Dequeue<E> {

	boolean enqueueAtRear(E e);

	E dequeueFromRear();

	boolean enqueueAtFront(E e);

	E dequeueFromFront();

	// Functionality to be able to iterate over the queue in both directions
	// i.e. from Left to Right and vice versa

	// Make it Circular Queue by adding the next and previous pointers to Node
}