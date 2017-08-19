package com.lalit.ds.stack.application;

public enum PrecedenceOrder {

	Addition('+', 3), Subtraction('-', 3), Division('/', 2), Multiplication(
			'*', 2), Exponential('^', 1), Braket('[', 1);

	private final char name;
	private final int value;

	private PrecedenceOrder(char c, int i) {
		name = c;
		value = i;
	}

	public static int getPrecedenceValue(char name) {
		for (PrecedenceOrder value : PrecedenceOrder.values()) {
			if (value.name == name) {
				return value.value;
			}
		}
		return 1000;
	}

	public static void main(String[] args) {
		for (PrecedenceOrder p : PrecedenceOrder.values())
			System.out.println(p.hashCode());
	}
}