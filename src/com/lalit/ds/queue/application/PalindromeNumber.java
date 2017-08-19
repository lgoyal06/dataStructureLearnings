package com.lalit.ds.queue.application;

import com.lalit.ds.queue.api.Dequeue;
import com.lalit.ds.queue.api.DequeueLinkedListImplementation;

public class PalindromeNumber {

	public static boolean isPalindromeNumber(Integer number) {

		Dequeue<Integer> dequeue = new DequeueLinkedListImplementation<>();

		int numberLength = 0;

		while (number > 0) {
			dequeue.enqueueAtFront(number % 10);
			number = number / 10;
			++numberLength;
		}

		for (int i = 0; i < numberLength / 2; ++i) {
			if (dequeue.dequeueFromFront() == dequeue.dequeueFromRear()) {
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean isPalindromeNumber(String value) {

		Dequeue<Character> dequeue = new DequeueLinkedListImplementation<>();

		for (int i = 0; i < value.length(); ++i) {
			dequeue.enqueueAtFront(value.charAt(i));
		}

		for (int i = 0; i < value.length() / 2; ++i) {
			if (dequeue.dequeueFromFront().equals(dequeue.dequeueFromRear())) {
			} else {
				return false;
			}
		}
		return true;
	}
}