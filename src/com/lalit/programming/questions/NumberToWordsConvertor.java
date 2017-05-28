package com.lalit.programming.questions;

import java.util.HashMap;
import java.util.Map;

public class NumberToWordsConvertor {

	private static Map<String, String> map = new HashMap<String, String>();
	private static Map<String, String> specialMap = new HashMap<String, String>();

	static {
		map.put("1", "One");
		map.put("2", "Two");
		map.put("3", "three");
		map.put("4", "Four");
		map.put("5", "five");
		map.put("6", "six");
		map.put("7", "seven");
		map.put("8", "eight");
		map.put("9", "nine");
		map.put("10", "ten");
		map.put("11", "eleven");
		map.put("12", "tweleve");
		map.put("13", "thirteen");
		map.put("14", "forteen");
		map.put("15", "fifteen");
		map.put("16", "sixteen");
		map.put("17", "seventeen");
		map.put("18", "eigteen");
		map.put("19", "ninteen");
		map.put("20", "twenty");
		map.put("30", "thirty");
		map.put("40", "fourty");
		map.put("50", "fifty");
		map.put("60", "sixty");
		map.put("70", "seventy");
		map.put("80", "eighty");
		map.put("90", "ninty");
		map.put("100", "hundred");
		specialMap.put("1000", "thousand");
		specialMap.put("1000000", "milliens");
	}

	public static void main(String... s) {
		int x = 3478856;
		String numInWords = "";
		int counter = 0;
		while (x != 0) {
			String num = String.valueOf(x % 1000);
			numInWords = convertLessThanThousand(num)
					+ String.valueOf((specialMap.get(String.valueOf((int) (Math
							.pow(10, 3 * counter)))))) + " " + numInWords;
			x = x / 1000;
			++counter;
		}
		System.out.println(numInWords.replaceAll("null", ""));
	}

	private static String convertLessThanThousand(String number) {
		Integer num = Integer.valueOf(number), remender;
		String numInWords = "";
		for (int i = 1; i <= number.length(); ++i) {
			remender = (int) (num % Math.pow(10, i));
			if (String.valueOf(remender).length() == 3) {
				numInWords = map.get(String.valueOf((int) (remender / Math.pow(
						10, String.valueOf(remender).length() - 1))))
						+ " "
						+ map.get(String.valueOf((int) (Math.pow(10, String
								.valueOf(remender).length() - 1))))
						+ " "
						+ numInWords;
			} else if (String.valueOf(remender).length() <= 2) {
				numInWords = (map.get(String.valueOf(remender)) == null ? ""
						: map.get(String.valueOf(remender)) + " ") + numInWords;
			}
			num = num - remender;
		}
		return numInWords;
	}
}
