package com.lalit.ds.java.stack.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuicksortImplementation {

	static ArrayList<List<Integer>> stackOfList = new ArrayList<>();
	static ArrayList<List<Integer>> reductionPerformedList = new ArrayList<>();
	static int top = -1;

	public static Integer reductionStep(List<Integer> list, int splitIndex) {

		// and then split the list into sublist and add them to the stack

		int startIndex, middleIndex, lastIndex;
		startIndex = 0;
		middleIndex = splitIndex;
		lastIndex = list.size();
		if (startIndex + 1 < middleIndex - 1) {
			if (reductionPerformedList.contains(list.subList(startIndex,
					middleIndex - 1))) {
				--top;
			} else {
				reductionPerformedList.add(list.subList(startIndex,
						middleIndex - 1));
				stackOfList.add(list.subList(startIndex, middleIndex - 1));
			}
			++top;
		} else {
			--top;
		}
		if (middleIndex + 1 < lastIndex) {
			reductionPerformedList
					.add(list.subList(middleIndex + 1, lastIndex));
			stackOfList.add(list.subList(middleIndex + 1, lastIndex));
			++top;
		} else {
			--top;
		}

		// Perform the sorting of the single element and send the location and
		// element it self
		sortSubSet(list);
		reductionStep(list, splitIndex);
		return 10;
	}

	// Contains the sorting logic for the list
	// Returns the location of the sorted Element along with the element value
	// in map
	// and from that index split can be performed
	public static Map<Integer, Integer> sortSubSet(List<Integer> list) {

		// int LOWEST = list.get(0), UPPER = list.get(list.size() - 1);
		//
		// for (int i = 0; i < list.size() - 1; ++i) {
		//
		// }
		//
		// System.out.println(LOWEST + "----" + UPPER);
		return null;
	}

	private ArrayList<Integer> leftToRightTraverse(ArrayList<Integer> list) {
		for (int i = 0; i < list.size() - 1; ++i) {

		}

		return null;

	}

	private ArrayList<Integer> RightToLeftTraverse(ArrayList<Integer> list) {
		for (int i = list.size() - 1; i > 0; --i) {
			System.out.println(list);
		}

		return null;

	}

	public static void main(String... s) {
		List<Integer> list = new ArrayList<>();
		list.add(23);
		list.add(13);
		list.add(33);
		list.add(49);
		list.add(1);
		list.add(28);
		list.add(2);
		list.add(93);

		stackOfList.add(list);
		++top;
		// System.out.println(sortSubSet(list));
		// sortSubSet(list);
		while (top != -1) {
			reductionStep(stackOfList.get(top), stackOfList.get(top).size() + 1);
		}
		System.out.println("done ");
	}
}