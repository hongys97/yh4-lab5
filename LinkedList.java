// Implementation of lists, using doubly linked elements, and dummy nodes.
// Starter class for List-based lab.

import structure5.Assert;
import structure5.DoublyLinkedList;
import structure5.DoublyLinkedNode;
import structure5.DoublyLinkedListIterator;
import java.util.Iterator;

public class LinkedList<E> extends DoublyLinkedList<E>
{

  // Test method
  public static void main (String args[]){
    LinkedList cool = new LinkedList();
  }


    //Number of elements within the list.
    protected int count;
    // Reference to head of the list.
    protected DoublyLinkedNode<E> head;
    //Reference to tail of the list.
    protected DoublyLinkedNode<E> tail;

    /**
     * Constructs an empty list.
     * @post constructs an empty list
     */
    public LinkedList()
    {
       //super();
       head = new DoublyLinkedNode<E>(null);
       tail = new DoublyLinkedNode<E>(null, null, head);
        }

    /**
     * Determine the number of elements in the list.
     * @post returns the number of elements in list
     * @return The number of elements found in the list.
     */
    public int size()
    {
	     return count;
    }

    /**
     * Determine if the list is empty.
     * @post returns true iff the list has no elements.
     * @return True iff list has no values.
     */
    public boolean isEmpty()
    {
	     return size() == 0;
    }

    /**
     * Remove all values from list.
     pre: count > 0
     * @post removes all the elements from the list
     */
    public void clear()
    {
	     // Students: modify this code
       // We want to clear everything EXCEPT head and tail. Remove all elements from indeces count to 0.
       Assert.pre(count > 0 , "The number of elements is not 0.");
       for (int i = count; i > 0; i--){
         remove(i);
       }
    }

    /**
     * A private routine to add an element after a node.
     * @param value the value to be added
     * @param previous the node the come before the one holding value
     * @pre previous is non null
     * @post list contains a node following previous that contains value
     */
    protected void insertAfter(E value, DoublyLinkedNode<E> previous)
    {
	     // Students: write this code.
       Assert.pre(previous != null && previous != head, "There is a value for us to add E after, and the method cannot be used to insert a value a dummy node.");
       /*int index = indexOf(previous.value());
       // set all elements following previous to their current index + 1
       for (int i = count; i > (count-index); i--){
         E nextVal = get(i);
         set(i+1, nextVal);
       }
       // set value to index after previous
       set(index+1, value);
       count++;*/

       // Add value to the index after previous
       //super.add(indexOf(previous.value())+1, value);

       // or
       DoublyLinkedNode<E> insert = new DoublyLinkedNode<E>(value, previous.next(), previous);
       previous.next().setPrevious(insert);
       previous.setNext(insert);
       count++;
    }

    /**
     * A private routine to remove a node.
     * @param node the node to be removed
     * @pre node is non null
     * @post node node is removed from the list
     * @return the value of the node removed
     */
    protected E remove(DoublyLinkedNode<E> node)
    {
      node.previous().setNext(node.next());
      node.next().setPrevious(node.previous());
      return node.value();
    }


    /**
     * Add a value to the head of the list.
     *
     * @pre value is not null
     * @post adds element to head of list
     *
     * @param value The value to be added.
     */
    public void addFirst(E value)
    {
        insertAfter(value, head);
    }

    /**
     * Add a value to the tail of the list.
     *
     * @pre value is not null
     * @post adds new value to tail of list
     *
     * @param value The value to be added.
     */
    public void addLast(E value)
    {

    }

    /**
     * Remove a value from the head of the list.
     * Value is returned.
     *
     * @pre list is not empty
     * @post removes first value from list
     *
     * @return The value removed from the list.
     */
    public E removeFirst()
    {
	// Students: modify this code.
	Assert.pre(!isEmpty(),"List is not empty.");
	DoublyLinkedNode<E> temp = head;
	head = head.next();
	if (head != null) {
	    head.setPrevious(null);
	} else {
	    tail = null; // remove final value
	}
	temp.setNext(null);// helps clean things up; temp is free
	count--;
	return temp.value();
    }

    /**
     * Remove a value from the tail of the list.
     *
     * @pre list is not empty
     * @post removes value from tail of list
     *
     * @return The value removed from the list.
     */
    public E removeLast()
    {
	// Students: modify this code.
	Assert.pre(!isEmpty(),"List is not empty.");
	DoublyLinkedNode<E> temp = tail;
	tail = tail.previous();
	if (tail == null) {
	    head = null;
	} else {
	    tail.setNext(null);
	}
	count--;
	return temp.value();
    }

    /**
     * Get a copy of the first value found in the list.
     *
     * @pre list is not empty
     * @post returns first value in list.
     *
     * @return A reference to first value in list.
     */
    public E getFirst()
    {
	// Students: modify this code.
  E first = get(1);
	return first;
    }

    /**
     * Get a copy of the last value found in the list.
     *
     * @pre list is not empty
     * @post returns last value in list.
     *
     * @return A reference to the last value in the list.
     */
    public E getLast()
    {
	// Students: modify this code.
	E last = get(count);
  return last;
    }

    /**
     * Insert the value at location.
     *
     * @pre 0 <= i <= size()
     * @post adds the ith entry of the list to value o
     * @param i the index of this new value
     * @param o the the value to be stored
     */
    public void add(int i, E o)
    {

	// Students: modify this code.
	Assert.pre((0 <= i) &&
		   (i <= size()), "Index in range.");
	if (i == 0) addFirst(o);
	else if (i == size()) addLast(o);
	else {
	    DoublyLinkedNode<E> before = null;
	    DoublyLinkedNode<E> after = head;
	    // search for ith position, or end of list
	    while (i > 0)
	    {
		before = after;
		after = after.next();
		i--;
	    }
	    // create new value to insert in correct position
	    DoublyLinkedNode<E> current =
		new DoublyLinkedNode<E>(o,after,before);
	    count++;
	    // make after and before value point to new value
	    before.setNext(current);
	    after.setPrevious(current);
	}
    }

    /**
     * Remove and return the value at location i.
     *
     * @pre 0 <= i < size()
     * @post removes and returns the object found at that location.
     *
     * @param i the position of the value to be retrieved.
     * @return the value retrieved from location i (returns null if i invalid)
     */
    public E remove(int i)
    {

	// Students: modify this code.
	Assert.pre((0 <= i) &&
		   (i < size()), "Index in range.");
	if (i == 0) return removeFirst();
	else if (i == size()-1) return removeLast();
	DoublyLinkedNode<E> previous = null;
	DoublyLinkedNode<E> finger = head;
	// search for the value indexed, keep track of previous
	while (i > 0)
	{
	    previous = finger;
	    finger = finger.next();
	    i--;
	}
	previous.setNext(finger.next());
	finger.next().setPrevious(previous);
	count--;
	// finger's value is old value, return it
	return finger.value();
    }

    /**
     * Get the value at location i.
     *
     * @pre 0 <= i < size()
     * @post returns the object found at that location.
     *
     * @param i the position of the value to be retrieved.
     * @return the value retrieved from location i (returns null if i invalid)
     */
    public E get(int i)
    {

	// Students: modify this code.
	if (i >= size()) return null;
	DoublyLinkedNode<E> finger = head;
	// search for the ith element or end of list
	while (i > 0)
	{
	    finger = finger.next();
	    i--;
	}
	// not end of list, return the value found
	return finger.value();
    }

    /**
     * Set the value stored at location i to object o, returning the old value.
     *
     * @pre 0 <= i < size()
     * @post sets the ith entry of the list to value o, returns the old value.
     * @param i the location of the entry to be changed.
     * @param o the new value
     * @return the former value of the ith entry of the list.
     */
    public E set(int i, E o)
    {

	// Students: modify this code.
	if (i >= size()) return null;
	DoublyLinkedNode<E> finger = head;
	// search for the ith element or the end of the list
	while (i > 0)
	{
	    finger = finger.next();
	    i--;
	}
	// get old value, update new value
	E result = finger.value();
	finger.setValue(o);
	return result;
    }

    /**
     * Determine the first location of a value in the list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of the value,
     *   or -1 if the value is not found
     *
     * @param value The value sought.
     * @return the index (0 is the first element) of the value, or -1
     */
    public int indexOf(E value)
    {

	// Students: modify this code.
	int i = 0;
	DoublyLinkedNode<E> finger = head;
	// search for value or end of list, counting along the way
	while (finger != null && !finger.value().equals(value))
	{
	    finger = finger.next();
	    i++;
	}
	// finger points to value, i is index
	if (finger == null)
	{   // value not found, return indicator
	    return -1;
	} else {
	    // value found, return index
	    return i;
	}
    }

    /**
     * Determine the last location of a value in the list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of the value,
     *   or -1 if the value is not found
     *
     * @param value the value sought.
     * @return the index (0 is the first element) of the value, or -1
     */
    public int lastIndexOf(E value)
    {

	// Students: modify this code.
	int i = size()-1;
	DoublyLinkedNode<E> finger = tail;
	// search for the last matching value, result is desired index
	while (finger != null && !finger.value().equals(value))
	{
	    finger = finger.previous();
	    i--;
	}
	if (finger == null)
	{   // value not found, return indicator
	    return -1;
	} else {
	    // value found, return index
	    return i;
	}
    }

    /**
     * Check to see if a value is within the list.
     *
     * @pre value not null
     * @post returns true iff value is in the list
     *
     * @param value A value to be found in the list.
     * @return True if value is in list.
     */
    public boolean contains(E value)
    {
	// Students: modify this code.
	DoublyLinkedNode<E> finger = head;
	while ((finger != null) && (!finger.value().equals(value)))
	{
	    finger = finger.next();
	}
	return finger != null;
    }

    /**
     * Remove a value from the list.  At most one value is removed.
     * Any duplicates remain.  Because comparison is done with "equals,"
     * the actual value removed is returned for inspection.
     *
     * @pre value is not null.  List can be empty.
     * @post first element matching value is removed from list
     *
     * @param value The value to be removed.
     * @return The value actually removed.
     */
    public E remove(E value)
    {
	     // Students: modify this code.
      	DoublyLinkedNode<E> finger = head;
      	while (finger != null &&
      	       !finger.value().equals(value))
      	{
      	    finger = finger.next();
      	}
      	if (finger != null)
	{
	    // fix next field of element above
	    if (finger.previous() != null)
	    {
		finger.previous().setNext(finger.next());
	    } else {
		head = finger.next();
	    }
	    // fix previous field of element below
	    if (finger.next() != null)
	    {
		finger.next().setPrevious(finger.previous());
	    } else {
		tail = finger.previous();
	    }
	    count--;		// fewer elements
	    return finger.value();
	}
	return null;
    }










    /**
     * Construct a string representation of the list.
     *
     * @post returns a string representing list
     *
     * @return A string representing the elements of the list.
     */
    public String toString()
    {
      	StringBuffer s = new StringBuffer();
      	s.append("<LinkedList:");
      	Iterator<E> li = iterator();
      	while (li.hasNext())
      	{
      	    s.append(" "+li.next());
      	}
      	s.append(">");
      	return s.toString();
    }


    /**
     * Construct an iterator to traverse the list.
     *
     * @post returns iterator that allows the traversal of list.
     *
     * @return An iterator that traverses the list from head to tail.
     */
    public Iterator<E> iterator()
    {// Students: This code should not be modified
       return new DoublyLinkedListIterator<E>(head,tail);
    }
}
