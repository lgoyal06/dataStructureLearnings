package com.lalit.ds.java.stack.application;

public class FactorialProblem {

	public int factorial(int f) {

		if (f == 1)
			return 1;
		else {
			f = f * factorial(f - 1);
		}
		return f;
	}

	public static void main(String... s) {
		System.out.println(new FactorialProblem().factorial(4));
	}
}
