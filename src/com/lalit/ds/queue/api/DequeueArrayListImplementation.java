package com.lalit.ds.queue.api;

import com.lalit.collections.iterator.api.Iterators;

/**
 * @author lalit goyal
 * 
 * @param <E>
 */
public final class DequeueArrayListImplementation<E> implements Dequeue<E> {

	private Object[] elementArray = new Object[10];

	private int FRONT = -1;
	private int REAR = -1;
	private int size = 0;

	@Override
	public boolean enqueueAtRear(E obj) {
		if (size == elementArray.length) {
			throw new ArrayIndexOutOfBoundsException("Array Full");
		} else {
			REAR = (REAR + 1) % 10;
			elementArray[REAR] = obj;
			++size;
			return true;
		}
	}

	@Override
	public E dequeueFromFront() {
		if (size == 0) {
			throw new NullPointerException("No Element friend");
		}
		FRONT = (FRONT + 1) % 10;
		elementArray[FRONT] = null;
		--size;
		return null;
	}

	@Override
	public boolean enqueueAtFront(E obj) {
		if (size == elementArray.length) {
			throw new ArrayIndexOutOfBoundsException("Array Full");
		} else {
			if (FRONT == -1) {
				FRONT = elementArray.length - 2;
				elementArray[elementArray.length - 1] = obj;
			} else {
				elementArray[FRONT] = obj;
				if (FRONT == 0)
					FRONT = elementArray.length - 1;
				else
					FRONT = (FRONT - 1) % 10;
			}
			++size;
			return true;
		}
	}

	@Override
	public E dequeueFromRear() {
		if (size == 0) {
			throw new NullPointerException("No more Element friend");
		}
		if (REAR == -1) {
			REAR = elementArray.length - 2;
			elementArray[elementArray.length - 1] = null;
			return null;
		} else {
			elementArray[REAR] = null;
			if (REAR == 0)
				REAR = elementArray.length - 1;
			else
				REAR = (REAR - 1) % 10;
		}
		--size;
		return null;
	}

	public ItrsFromFrontToRear iterateFromFrontToRear() {

		return new ItrsFromFrontToRear(FRONT);

	}

	public ItrsFromRearToFront iterateFromRearToFront() {

		return new ItrsFromRearToFront(REAR);

	}

	private class ItrsFromFrontToRear implements Iterators<E> {

		int pointerToFront;
		int counter;

		public ItrsFromFrontToRear(int pointerToFront) {
			this.pointerToFront = pointerToFront;
		}

		@Override
		public boolean hasNextElement() {
			if (size == elementArray.length && counter < elementArray.length) {
				++counter;
				return true;
			} else {
				if (pointerToFront <= 0 && elementArray[0] != null
						&& counter < elementArray.length) {
					++counter;
					return true;
				} else if (pointerToFront > 0
						&& elementArray[(pointerToFront + 1) % 10] != null
						&& counter < elementArray.length) {
					++counter;
					return true;
				}
				return false;
			}
		}

		@Override
		public Object nextElement() {
			if (size == elementArray.length) {
				if (pointerToFront == -1) {
					pointerToFront = 0;
				}
				return elementArray[pointerToFront++ % 10];
			}
			return elementArray[++pointerToFront % 10];
		}

		@Override
		public Object previousElement() {
			return false;
		}

		@Override
		public boolean hasPreviousElement() {
			return false;
		}

	}

	private class ItrsFromRearToFront implements Iterators<E> {

		int pointerToRear;
		int counter;

		public ItrsFromRearToFront(int pointerToRear) {
			this.pointerToRear = pointerToRear;
		}

		@Override
		public boolean hasPreviousElement() {
			if (size == elementArray.length && counter < elementArray.length) {
				++counter;
				return true;
			} else {
				if (pointerToRear < 0
						&& elementArray[elementArray.length - 1] != null
						&& counter < elementArray.length) {
					++counter;
					return true;
				} else if (pointerToRear == 0
						&& elementArray[pointerToRear] != null
						&& counter < elementArray.length) {
					++counter;
					return true;
				}

				else if (pointerToRear > 0
						&& elementArray[(pointerToRear) % 10] != null
						&& counter < elementArray.length) {
					++counter;
					return true;
				}
				return false;
			}
		}

		@Override
		public Object previousElement() {
			if (pointerToRear < 0)
				pointerToRear = elementArray.length - 1;
			return elementArray[pointerToRear-- % 10];

		}

		@Override
		public boolean hasNextElement() {
			return false;
		}

		@Override
		public E nextElement() {
			return null;
		}
	}
}