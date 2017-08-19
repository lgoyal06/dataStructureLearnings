package com.lalit.ds.queue.api;

import com.lalit.collections.iterator.api.Iterators;

public class QueueClassTester {
	public static void main(String... s) {
		// Dequeue<String> dequeue = new DequeueLinkedListImplementation<>();
		// dequeue.enqueueAtFront("ss");
		// dequeue.enqueueAtFront("sss");
		// dequeue.enqueueAtRear("wewedd");
		// System.out.println(dequeue.dequeueFromRear());
		// System.out.println(dequeue.dequeueFromFront());
		// System.out.println(dequeue.dequeueFromFront());
		//
		// PriorityqueueViaLinkedList<String> priorityQueue = new
		// PriorityqueueViaLinkedList<>();
		// priorityQueue.enqueue("First", 1);
		// priorityQueue.enqueue("Second", 2);
		// priorityQueue.enqueue("Third", 1);
		// priorityQueue.enqueue("Forth", 1);
		// priorityQueue.enqueue("Fifth", 5);
		// priorityQueue.enqueue("sixth", 5);
		// priorityQueue.enqueue("seventh", 4);
		// priorityQueue.enqueue("EIGTH", 0);
		// priorityQueue.enqueue("ninth", 20);
		// try {
		// while (true) {
		// System.out.println(priorityQueue.dequeue());
		// }
		// } catch (Exception e) {
		// }

		DequeueArrayListImplementation<String> test = new DequeueArrayListImplementation<>();
		test.enqueueAtRear("first");
		test.enqueueAtRear("first-second");
		test.enqueueAtRear("first");
		test.enqueueAtRear("first-second");
		test.enqueueAtRear("first");
		test.enqueueAtRear("first-second");
		test.enqueueAtRear("first");
		test.enqueueAtRear("first-second");
		test.enqueueAtRear("SECONDLAST");
		test.enqueueAtRear("last");
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.enqueueAtRear("ssss");
		test.enqueueAtFront("dff3d");
		test.enqueueAtFront("dff3sww");
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();
		test.dequeueFromFront();

		Iterators<String> itr = test.iterateFromFrontToRear();
		while (itr.hasNextElement()) {
			System.out.println(itr.nextElement());
		}

		Iterators<String> itr1 = test.iterateFromRearToFront();
		while (itr1.hasPreviousElement()) {
			System.out.println(itr1.previousElement());
		}

	}
}
