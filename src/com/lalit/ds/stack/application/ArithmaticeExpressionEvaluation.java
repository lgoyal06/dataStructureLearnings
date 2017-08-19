package com.lalit.ds.stack.application;

import java.util.ArrayList;
import java.util.List;

public class ArithmaticeExpressionEvaluation {

	static List<Character> stack = new ArrayList<>();
	static List<Integer> stackOfInteger = new ArrayList<>();
	static int topIndex = 0;

	public static ArrayList<Character> covertInfixToPostFixExpression(
			ArrayList<Character> charArray) {
		List<Character> postFixExpression = new ArrayList<>();
		for (char c : charArray) {
			if (c == '(')
				push(c);
			else if (c == ')') {
				while (getTopElement() != '(')
					postFixExpression.add(pop());
				pop();
			} else if (c == '*' || c == '+' || c == '/' || c == '-' || c == '^') {
				char topChar = getTopElement();
				if (topChar == '(') {
				} else if (comparePrecedence(topChar, c) == 1
						|| comparePrecedence(topChar, c) == 0) {
					postFixExpression.add(pop());
				}
				push(c);
			} else
				postFixExpression.add(c);
		}
		return (ArrayList<Character>) postFixExpression;
	}

	public static int comparePrecedence(char a, char b) {
		int orderA = PrecedenceOrder.getPrecedenceValue(a);
		int orderB = PrecedenceOrder.getPrecedenceValue(b);
		if (PrecedenceOrder.getPrecedenceValue(a) > PrecedenceOrder
				.getPrecedenceValue(b)) {
			return -1;
		} else if (orderA < orderB) {
			return 1;
		}
		return 0;
	}

	public static int evaluatePostFixExpression(
			ArrayList<Character> postFixExpersion) {
		int result = 0;
		for (char c : postFixExpersion) {
			if (c == '*') {
				result = Integer.valueOf(String.valueOf(pop1()))
						* Integer.valueOf(String.valueOf(pop1()));
				push(result);
			} else if (c == '+') {
				result = Integer.valueOf(String.valueOf(pop1()))
						+ Integer.valueOf(String.valueOf(pop1()));
				push(result);
			} else if (c == '/') {
				result = Integer.valueOf(String.valueOf(pop1()))
						/ Integer.valueOf(String.valueOf(pop1()));
				push(result);
			} else if (c == '-') {
				result = Integer.valueOf(String.valueOf(pop1()))
						- Integer.valueOf(String.valueOf(pop1()));
				push(result);
			} else if (c == '^') {
				result = Integer.valueOf(String.valueOf(pop1()))
						^ Integer.valueOf(String.valueOf(pop1()));
				push(result);
			} else {
				push(Integer.valueOf(String.valueOf(c)));
			}
		}
		return result;
	}

	public static boolean push(char value) {
		stack.add(topIndex, value);
		++topIndex;
		return true;
	}

	public static char pop() {
		--topIndex;
		return stack.remove(topIndex);
	}

	public static boolean push(int value) {
		stackOfInteger.add(topIndex, value);
		++topIndex;
		return true;
	}

	public static int pop1() {
		--topIndex;
		return stackOfInteger.remove(topIndex);
	}

	public static char getTopElement() {

		return stack.get(topIndex - 1);
	}

	public static void main(String... s) {

		String arithmaticExperssionInfix = "6*((7+1)*6)";
		char[] arithmaticExperssionInfixArray = arithmaticExperssionInfix
				.toCharArray();
		ArrayList<Character> infixExpression = new ArrayList<Character>();
		for (char c : arithmaticExperssionInfixArray) {
			infixExpression.add(c);
		}
		infixExpression.add(')');
		infixExpression.add(0, '(');
		System.out
				.println(evaluatePostFixExpression(covertInfixToPostFixExpression(infixExpression)));
	}
}