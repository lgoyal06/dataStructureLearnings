package com.lalit.ds.tree.bst;

public class BinaryTreeNode {

	public int value;
	public BinaryTreeNode nextLeft;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public BinaryTreeNode getNextLeft() {
		return nextLeft;
	}

	public void setNextLeft(BinaryTreeNode nextLeft) {
		this.nextLeft = nextLeft;
	}

	public BinaryTreeNode getNextRight() {
		return nextRight;
	}

	public void setNextRight(BinaryTreeNode nextRight) {
		this.nextRight = nextRight;
	}

	public BinaryTreeNode nextRight;

	public BinaryTreeNode(int value) {
		this.value = value;
	}
}