package com.lalit.ds.java.basic;

/**
 * No. 06 - Post-order Traversal Sequences of Binary Search Trees Determine
 * whether an input array is a post-order traversal sequence of a binary tree or
 * not. If it is, return true; otherwise return false. Assume all numbers in an
 * input array are unique.
 * 
 * 
 * Scenarios: 1.Only Left Subtree present
 * 
 * 2.Only Right Subtree present
 * 
 * Things in hand Factor Fix :::
 * 
 * 1) Last element will always be root
 * 
 * 2) If 1st element < last element, it means left tree present. Then
 * 
 * 3) If 1st element > last element it means right subtree Then no value in
 * future can be < last element
 * 
 * 4) Divide array into left tree and right subtree
 * 
 * 5) Right subtree elements should be > last element
 * 
 * 6) single element no tree
 * 
 * **/
public class VerifyPostOrderTraversalSequence {

	public static boolean isPostOrderTraverse(Integer[] n) {
		int rootElement = n[n.length - 1];

		// Only one element
		if (n.length == 0) {
			System.out.println("Binary Tree not valid");
			return false;
		}

		// Left SubTree
		int i = 0;
		while (n[i] < rootElement) {
			++i;
		}

		// Right Sub Tree
		while (i < n.length - 1) {
			// if right tree <= root element
			if (n[i] <= rootElement) {
				System.out.println("Right Subtree not valid");
				return false;
			}
			++i;
		}

		return true;
	}

	public static void main(String... s) {
		System.out.println(isPostOrderTraverse(new Integer[] { 3, 4, 8, 15, 67,
				78, 34, 45, 19 }));
	}

}
