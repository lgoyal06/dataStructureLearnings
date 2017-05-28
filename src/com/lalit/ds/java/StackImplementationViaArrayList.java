package com.lalit.ds.java;

import java.util.ArrayList;

public class StackImplementationViaArrayList {

	ArrayList<String> list = new ArrayList<String>();
	Integer topIndex = null;

	public Object peek() {
		if (topIndex == null) {
			System.out.println("Empty Stack. Nothing to pop");
			return null;
		} else {
			String val = list.get(topIndex);
			return val;
		}

	}

	public String pop() {
		if (topIndex == null) {
			System.out.println("Empty Stack. Nothing to pop");
			return null;
		} else {
			String val = list.get(topIndex);
			list.remove((int) topIndex);
			--topIndex;
			return val;
		}

	}

	public String push(String val) {
		if (topIndex == null) {
			topIndex = 0;
			list.add(topIndex, val);
		} else {
			++topIndex;
			list.add(topIndex, val);
		}
		return null;
	}

	public Object search() {
		return null;
	}

	public String toString() {
		String valFinal = "";
		for (String val : list) {
			valFinal = valFinal + (val + ",");
		}
		return valFinal;
	}

	public static void main(String... s) {
		StackImplementationViaArrayList stack = new StackImplementationViaArrayList();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		System.out.println(stack);
		System.out.println(stack.topIndex);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack);
		stack.push("A1");
		stack.push("B2");
		stack.push("C3");
		System.out.println(stack);
	}

}
