package listClasses;

import java.util.*;

/**
 * Implements a generic sorted list using a provided Comparator. It extends
 * BasicLinkedList class.
 * 
 * @author Dept of Computer Science, UMCP
 * 
 */

public class SortedLinkedList<T> extends BasicLinkedList<T> {
	private Comparator<T> comparator;

	/*
	 * This constructor needs to have super(); because it extends the
	 * BasicLinkedList class then have this.comparator = comparator(parameter).
	 */
	public SortedLinkedList(Comparator<T> comparator) {
		super();
		this.comparator = comparator;

	}

	/*
	 * This methods inserts the specified element at the right position in the
	 * sorted linked list.
	 */
	public SortedLinkedList<T> add(T element) {
		if (element == null) {
			return this;
		}
		Node newNode = new Node(element);

		if (head == null) {
			head = newNode;
			tail = newNode;
		} else if (comparator.compare(head.data, element) > 0) {
			newNode.next = head;
			head = newNode;
		} else if (comparator.compare(tail.data, element) < 0) {
			tail.next = newNode;
			tail = newNode;
		} else {
			Node curr = head;
			while ((curr != null && curr.next != null
					&& comparator.compare(curr.next.data, element) < 0)) {
				newNode.next = curr.next;
				curr.next = newNode;
			}
			newNode.next = curr.next;
			curr.next = newNode;
		}
		listSize++;
		return this;
	}

	/* Calling the super class remove method. */
	public SortedLinkedList<T> remove(T targetData) {
		super.remove(targetData, comparator);
		return this;
	}

	@Override
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException(
				"Invalid operation for sorted list.");
	}

	@Override
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException(
				"Invalid operation for sorted list.");
	}
}