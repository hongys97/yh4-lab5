/*
Test for the LinkedList class from CS136 Lab 5
(c) 2016 Bill Jannen
*/

import structure5.*;
import java.util.*;

public class TestLinkedList {

	public static void main(String s[]) {
		LinkedList<Integer> list = new LinkedList<Integer>();

		for (int i = 0; i < 6; i++){
			list.add(i, i);
		}
		compareList("0 1 2 3 4 5", list);

		list.add(2, 10);
		compareList("0 1 10 2 3 4 5", list);

		System.out.println(list.getFirst());
		System.out.println(list.getLast());

		list.clear();


		for (int i = 5; i >= 0; i--){
			list.addFirst(new Integer(i));
		}
		System.out.println("\nAdd First ");
		compareList("0 1 2 3 4 5", list);


		list.clear();

		// This next line breaks in the default code because
		// of the edge conditions that our Lab 5 solves.
		// When you have finished implementing your code,
		// you can uncomment this line and verify that it works.
		System.out.println("\nClear ");
		compareList("", list);


		for (int i = 0; i <= 5; i++){
			list.addLast(new Integer(i));
		}
		System.out.println("\nAdd Last ");
		compareList("0 1 2 3 4 5", list);


		// Add int i at index 2i, 6 times
		for (int i = 0; i <= 5; i++){
			list.add(2*i, new Integer(i));
			//System.out.println("add " + list);
		}
		System.out.println("\nAdd ");
		compareList("0 0 1 1 2 2 3 3 4 4 5 5", list);


		for (int i = 0; i <= 5; i++){
			list.remove(i);
		}
		System.out.println("\nRemove ");
		compareList("0 1 2 3 4 5", list);


		for (int i = 0; i <= 5; i++)
			list.set(i, 0);
		System.out.println("\nSet to 0 ");
		compareList("0 0 0 0 0 0", list);

		list.set(0, 1);
		list.set(2, 3);
		list.set(3, 3);
		list.set(list.size()-1, 1);
		System.out.println("\nset");
		compareList("1 0 3 3 0 1", list);

		System.out.println("\nindexOf(3)");
		System.out.println("Expected: 2");
		System.out.println("Received: " + list.indexOf(3));
		System.out.println("\nlastIndexOf(3)");
		System.out.println("Expected: 3");
		System.out.println("Received: " + list.lastIndexOf(3));
	}

	// a simple helper method to display a list
	// pre: list != null
	public static <E> void compareList(String expected, LinkedList<E> list) {
		System.out.println("Expected: <LinkedList: " + expected + ">");
		System.out.println("Received: " + list.toString());
	}
}
