package com.lalit.ds.java.stack.application;

public class FabonaciSeriesProblem {

	public int fabonaci(int f) {

		if (f == 0)
			return 0;
		else if (f == 1)
			return 1;
		else {
			f = fabonaci(f - 2) + fabonaci(f - 1);
		}
		return f;
	}

	public static void main(String... s) {
		System.out.println(new FabonaciSeriesProblem().fabonaci(4));
	}
}
