package listClasses;

import java.util.*;

public class BasicLinkedList<T> implements Iterable<T> {

	/* Node definition */
	protected class Node {
		protected T data;
		protected Node next;

		protected Node(T data) {
			this.data = data;
			next = null;
		}
	}

	/* We have both head and tail */
	protected Node head, tail;

	/* size */
	protected int listSize;

	public BasicLinkedList() {
		listSize = 0;
		head = null;
		tail = null;
	}

	/* Keeping track of the size. */
	public int getSize() {
		return listSize;
	}

	/* Adds element to the end of the list. */
	public BasicLinkedList<T> addToEnd(T data) {
		if (data == null) {
			return this;
		}
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		listSize++;
		return this;
	}

	/* Adds element to the front of the list. */
	public BasicLinkedList<T> addToFront(T data) {
		if (data == null) {
			return this;
		}
		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			newNode.next = head;
			head = newNode;
		}
		listSize++;
		return this;
	}

	/*
	 * Returns but doesn't remove the first element from the list otherwise
	 * return null.
	 */
	public T getFirst() {
		if (head == null) {
			return null;
		} else {
			return head.data;
		}
	}

	/*
	 * Returns but doesn't remove the last element from the list otherwise
	 * return null.
	 */
	public T getLast() {
		if (head == null) {
			return null;
		} else {
			return tail.data;
		}
	}

	/*
	 * Removes and returns the first element from the list otherwise return null
	 */
	public T retrieveFirstElement() {
		if (listSize == 0) {
			return null;
		} else {
			T dataInside = head.data;
			head = head.next;
			listSize--;
			return dataInside;
		}
	}

	/*
	 * Removes and returns the last element from the list otherwise return null
	 */
	public T retrieveLastElement() {
		if (listSize == 0) {
			return null;
		} else if (listSize == 1) {
			T dataToReturn = tail.data;
			head = null;
			tail = null;
			listSize--;
			return dataToReturn;
		} else {
			Node curr = head;
			while (curr.next != tail) {
				curr = curr.next;
			}
			T dataToReturn = tail.data;
			curr.next = null;
			tail = curr;
			listSize--;
			return dataToReturn;
		}
	}

	/*
	 * Removes all instance of the target data from the list. Comparing the
	 * comparator to curr.data/target data to see if it equals 0.
	 */
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) { //linked list
		Node prev = null, curr = head;

		while (curr != null) {
			if (comparator.compare(curr.data, targetData) == 0) {
				if (curr == head) {
					head = head.next;
					curr = head;
					listSize--;
				} else if (curr == tail) {
					prev.next = null;
					listSize--;
					break;
				} else {
					prev.next = curr.next;
					curr = prev.next;
					listSize--;
				}
			} else {
				prev = curr;
				curr = curr.next;
			}
		}
		return this;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node curr = head;

			@Override
			public boolean hasNext() {
				return curr != null;
			}

			@Override
			public T next() {
				T toReturn = curr.data;

				curr = curr.next;
				return toReturn;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/*
	 * Returns an arrayList with the element of the linked list in reverse order
	 * using an auxiliary method(getReverseArrayListHelper).
	 */
	public ArrayList<T> getReverseArrayList() {
		ArrayList<T> reverseArrayList = new ArrayList<>();
		getReverseArrayListHelper(head, reverseArrayList);
		return reverseArrayList;
	}

	/* Auxiliary method that will be used in the getReverseArrayList. */
	private void getReverseArrayListHelper(Node headAux, ArrayList<T> answer) {
		if (headAux != null) {
			getReverseArrayListHelper(headAux.next, answer);
			answer.add(headAux.data);
		}
	}

	/*
	 * Returns a new list with the elements of the current list in reverse
	 * order.
	 */
	public BasicLinkedList<T> getReverseList() {
		BasicLinkedList<T> reverse = new BasicLinkedList<>();
		Node curr = head;
		while (curr != null) {
			reverse.addToFront(curr.data);
			curr = curr.next;
		}
		return reverse;
	}
}