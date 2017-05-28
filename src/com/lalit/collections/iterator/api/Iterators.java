package com.lalit.collections.iterator.api;

public interface Iterators<E> {

	boolean hasNextElement();

	boolean hasPreviousElement();

	Object nextElement();

	Object previousElement();

}