package com.lalit.ds.tree.bounded.depth;

import java.util.ArrayList;
import java.util.Iterator;

public class BinaryApproximationSearch<T extends Comparable<T>> {

	private static final BinaryApproximationSearch binaryApproximationSearchInstance = new BinaryApproximationSearch();

	private BinaryApproximationSearch() {

	}

	public static BinaryApproximationSearch getInstance() {
		return binaryApproximationSearchInstance;
	}

	public int getChildNodeIndex(ArrayList<T> values, T value) {
		Iterator<T> it = values.iterator();
		int index = 0;
		while (it.hasNext()) {
			if (value.compareTo(it.next()) == -1) {
				return index;
			}
			++index;
		}
		return index;
	}

}
