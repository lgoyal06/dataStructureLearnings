package com.lalit.ds.java.stack.application;

import java.util.ArrayList;

/**
 * @author lalit goyal Quick sort implementation
 * 
 */
public class QuickSortSortingLogic {

	static ArrayList<Integer[]> stack = new ArrayList<>();
	static int stackTop = 0;
	static Integer[] finalSortedList, tmpCurrentList;
	static Integer begIndex = null, endIndex = null;

	public int sortAnElementAndGetFinalPosition(int begIndex, int endIndex,
			Integer[] list) {
		return rightToLeftArrayTraverse(begIndex, endIndex, list, begIndex);
	}

	private int rightToLeftArrayTraverse(int begIndex, int endIndex,
			Integer[] list, int loc) {
		int i = 0;
		for (i = endIndex; i > begIndex; --i) {
			if (list[loc] <= list[i]) {
			} else
				break;
		}
		if (list[i] == list[loc])
			return loc;
		else {
			swap(list, loc, i);
			return leftToRightArrayTraverse(loc, i, list, i);
		}
	}

	private int leftToRightArrayTraverse(int begIndex, int endIndex,
			Integer[] list, int loc) {
		int i = 0;
		for (i = begIndex; i < endIndex; ++i) {
			if (list[loc] >= list[i]) {
			} else
				break;
		}
		if (list[i] == list[loc])
			return loc;
		else {
			swap(list, loc, i);
			return rightToLeftArrayTraverse(i, loc, list, i);
		}
	}

	private void swap(Integer[] list, int loc, int i) {
		int temp = list[loc];
		list[loc] = list[i];
		list[i] = temp;
	}

	public static void main(String... s) {

		Integer[] list = { 434343434, 0, 11, 55, 77, 90, 40, 1, 99, 22, 88, 66,
				4, 2, 45455, 22, 220, 9, 434343434, 0, 11, 55, 77, 90, 40, 1,
				99, 22, 88, 66, 4, 2, 45455, 22, 220, 92323, 434343434, 0, 11,
				55, 77, 90, 40, 1, 99, 22, 88, 66, 4, 2, 45455, 22, 220, 9,
				434343434, 0, 11, 55, 77, 90, 40, 1, 99, 22, 88, 66, 4, 2,
				45455, 22, 220, 92323, 434343434, 0, 11, 55, 77, 90, 40, 1, 99,
				22, 88, 66, 4, 2, 45455, 22, 220, 9, 434343434, 0, 11, 55, 77,
				90, 40, 1, 99, 22, 88, 66, 4, 2, 45455, 22, 220, 92323,
				434343434, 0, 11, 55, 77, 90, 40, 1, 99, 22, 88, 66, 4, 2,
				45455, 22, 220, 9, 434343434, 0, 11, 55, 77, 90, 40, 1, 99, 22,
				88, 66, 4, 2, 45455, 22, 220, 92323, 4, 4, 5, 7, 8, 34, 7, 2 };

		tmpCurrentList = new Integer[list.length];
		finalSortedList = new Integer[list.length];
		stack.add(list);
		int loc = 0;
		while (stack.size() > 0) {
			tmpCurrentList = stack.remove(stackTop);
			--stackTop;

			for (int e = 0; e < tmpCurrentList.length; ++e) {
				if (tmpCurrentList[e] != null) {
					begIndex = e;
					break;
				}
			}
			for (int e = tmpCurrentList.length - 1; e > -1; --e) {
				if (tmpCurrentList[e] != null) {
					endIndex = e;
					break;
				}
			}
			if (begIndex != null || endIndex != null) {
				loc = new QuickSortSortingLogic()
						.sortAnElementAndGetFinalPosition(begIndex, endIndex,
								tmpCurrentList);
				finalSortedList[loc] = tmpCurrentList[loc];
				stackTop = reduceToSortSubListAndAddToSatck(stack, loc,
						stackTop, begIndex, endIndex, tmpCurrentList);
			}
			begIndex = endIndex = null;
		}
		System.out.println("FinalList:::"
				+ arrayToStringFormat(finalSortedList));
	}

	private static int reduceToSortSubListAndAddToSatck(
			ArrayList<Integer[]> stack, int loc, int stackTop,
			Integer begIndex, Integer endIndex, Integer[] tmpCurrentList) {
		Integer[] sublistA = new Integer[tmpCurrentList.length];
		Integer[] sublistB = new Integer[tmpCurrentList.length];
		if (begIndex != loc) {
			for (int u = begIndex; u < loc; ++u)
				sublistA[u] = tmpCurrentList[u];
			++stackTop;
			stack.add(stackTop, sublistA);
		}
		if (endIndex + 1 != loc + 1) {
			for (int u = loc + 1; u < endIndex + 1; ++u)
				sublistB[u] = tmpCurrentList[u];
			++stackTop;
			stack.add(stackTop, sublistB);
		}
		return stackTop;
	}

	private static String arrayToStringFormat(Integer[] array) {
		String arr = "[";
		for (int i = 0; i < array.length; ++i)
			arr = arr + array[i] + ",";
		return arr + "]";
	}
}