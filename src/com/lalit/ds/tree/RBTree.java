package com.lalit.ds.tree;

import com.lalit.ds.tree.bounded.depth.BasicTreeOperations;

public class RBTree<T> implements BasicTreeOperations<T> {
	@Override
	public boolean add(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteKey(T value) {
		// TODO Auto-generated method stub
		return false;
	}

	private class Node<E> {
		private E value;
		private Node<E> nextLeft;
		private Node<E> nextRight;
		private int height = 1;
		@SuppressWarnings("unused")
		private int inOrderTraverse;

	}
}
