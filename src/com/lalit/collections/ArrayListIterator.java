package com.lalit.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListIterator {

	public static void main(String... s) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("A");
		list.add("A1");
		list.add("A2");
		list.add("A3");
		list.add("A4");
		list.add("A5");

		Iterator<String> itr = list.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
			itr.remove();
		}
		Iterator<String> itr1 = list.iterator();
		while (itr1.hasNext()) {
			System.out.println("Iterator List 1 ::::" + itr1.next());
			itr.remove();
		}
	}
}
