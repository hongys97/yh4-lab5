import structure5.Assert;
import structure5.DoublyLinkedList;
import structure5.DoublyLinkedNode;
import structure5.DoublyLinkedListIterator;
import java.util.Iterator;

/* THOUGHT QUESTIONS:

1) The 1 parameter constructor assumes that the new node does not have
any nodes that come before or after it- namely, previousElement and
nextElement are null. Therefore, the if-statements become unnecessary
because we do not have to check that they exist.

2) The indexOf method essentially iterates through the list and keeps track
of whether the value passed through the parameter matches any value in
the list. As such, it can also tell us whether the list contains a value
or not. However, because indexOf has to keep track of value i to return the
specfic index at which this value occurs, contains does not suffice
to serve the purpose of indexOf.

3) There isn't any point to having a method that "removes after" as
opposed to one that simply "removes." First, we already have to check
whether the node we want to remove isn't null. If we were to implement
a "removes after" method, we would have to check for the existance of
both the node we want to remove after, and the node we want to remove.
It is simply more efficient to remove a specified node.

4) Case 1: Add to the front of the list:
Case 2: Add to the end of the list:
Case 3: Add at any other index:
By having "add" handle these three cases, we can consolidate the
three methods, addFirst, addLast, and add into one single method.

5) This file is 12KB without the thought questions, as opposed to
the original source file's 13KB!
*/

// Implementation of lists, using doubly linked elements and dummy nodes.
public class LinkedList<E> extends DoublyLinkedList<E>{

    // Number of elements within the list not including the dummy nodes.
    protected int count;
    // Reference to head of the list.
    protected DoublyLinkedNode<E> head;
    // Reference to tail of the list.
    protected DoublyLinkedNode<E> tail;

    /**
    * Constructs an empty list.
    * @post constructs an empty list
    */
    public LinkedList(){
        head = new DoublyLinkedNode<E>(null);
        tail = new DoublyLinkedNode<E>(null, null, head);
    }

    /**
    * Determine the number of elements in the list.
    * @post returns the number of elements in list
    * @return The number of elements found in the list.
    */
    public int size(){
        return count;
    }

    /**
    * Determine if the list is empty.
    * @post returns true iff the list has no elements.
    * @return True iff list has no values.
    */
    public boolean isEmpty(){
        return size() == 0;
    }

    /**
    * Remove all values from list.
    * @post removes all the elements from the list
    */
    public void clear(){
        for (int i = size()-1; i >= 0; i--)
        remove(i);
    }

    /**
    * A private routine to add an element after a node.
    * @param value the value to be added
    * @param previous the node the come before the one holding value
    * @pre previous is non null
    * @post list contains a node following previous that contains value
    */
    protected void insertAfter(E value, DoublyLinkedNode<E> previous){
        Assert.pre(previous != null, "There is a value for us to add E after");
        DoublyLinkedNode<E> insert = new DoublyLinkedNode<E>(value, previous.next(), previous);
        count++;
    }

    /**
    * A private routine to remove a node.
    * @param node the node to be removed
    * @pre node is non null
    * @post node node is removed from the list
    * @return the value of the node removed
    */
    protected E remove(DoublyLinkedNode<E> node){
        Assert.pre(node != null, "There is a node for us to remove.");
        E temp = node.value();
        node.next().setPrevious(node.previous());
        node.previous().setNext(node.next());
        return temp;
    }

    /**
    * Add a value to the head of the list.
    * @pre value is not null
    * @post adds element to head of list
    * @param value The value to be added.
    */
    public void addFirst(E value){
        Assert.pre(value != null, "There is a value for us to add.");
        insertAfter(value, head);
    }

    /**
    * Add a value to the tail of the list.
    * @pre value is not null
    * @post adds new value to tail of list
    * @param value The value to be added.
    */
    public void addLast(E value){
        Assert.pre(value != null, "There is a value for us to add.");
        insertAfter(value, tail.previous());
    }

    /**
    * Remove a value from the head of the list. Value is returned.
    * @pre list is not empty
    * @post removes first value from list
    * @return The value removed from the list.
    */
    public E removeFirst(){
        Assert.pre(!isEmpty(),"List is not empty.");
        return remove(head.next());
    }

    /**
    * Remove a value from the tail of the list.
    * @pre list is not empty
    * @post removes value from tail of list
    * @return The value removed from the list.
    */
    public E removeLast(){
        Assert.pre(!isEmpty(),"List is not empty.");
        return remove(tail.previous());
    }

    /**
    * Get a copy of the first value found in the list.
    * @pre list is not empty
    * @post returns first value in list.
    * @return A reference to first value in list.
    */
    public E getFirst(){
        Assert.pre(!isEmpty(),"List is not empty.");
        return head.next().value();
    }

    /**
    * Get a copy of the last value found in the list.
    * @pre list is not empty
    * @post returns last value in list.
    * @return A reference to the last value in the list.
    */
    public E getLast(){
        Assert.pre(!isEmpty(),"List is not empty.");
        return tail.previous().value();
    }

    /**
    * Insert the value at location.
    * @pre 0 <= i <= size()
    * @post adds the ith entry of the list to value o
    * @param i the index of this new value
    * @param o the the value to be stored
    */
    public void add(int i, E o){
        Assert.pre((0 <= i) && (i <= size()), "Index in range. (add)");
        DoublyLinkedNode<E> before = head;
        DoublyLinkedNode<E> after = head.next();
        // search for ith position, or end of list
        while (i > 0){
            before = after;
            after = after.next();
            i--;
        }
        // create new value to insert in correct position
        DoublyLinkedNode<E> current = new DoublyLinkedNode<E>(o,after,before);
        count++;
        // make after and before value point to new value
        before.setNext(current);
        after.setPrevious(current);
    }

    /**
    * Remove and return the value at location i.
    * @pre 0 <= i < size()
    * @post removes and returns the object found at that location.
    * @param i the position of the value to be retrieved.
    * @return the value retrieved from location i (returns null if i invalid)
    */
    public E remove(int i){
        Assert.pre((0 <= i) && (i < size()), "Index in range.");
        DoublyLinkedNode<E> previous = head;
        DoublyLinkedNode<E> finger = previous.next();
        // search for the value indexed, keep track of previous
        while (i > 0){
            previous = finger;
            finger = finger.next();
            i--;
        }
        previous.setNext(finger.next());
        finger.next().setPrevious(previous);
        count--;
        return finger.value();
    }

    /**
    * Get the value at location i.
    * @pre 0 <= i < size()
    * @post returns the object found at that location.
    * @param i the position of the value to be retrieved.
    * @return the value retrieved from location i (returns null if i invalid)
    */
    public E get(int i){
        Assert.pre((0 <= i) && (i < size()), "Index in range.");
        DoublyLinkedNode<E> finger = head.next();
        // search for the ith element or end of list
        while (i > 0){
            finger = finger.next();
            i--;
        }
        return finger.value();
    }

    /**
    * Set the value stored at location i to object o, returning the old value.
    * @pre 0 <= i < size()
    * @post sets the ith entry of the list to value o, returns the old value.
    * @param i the location of the entry to be changed.
    * @param o the new value
    * @return the former value of the ith entry of the list.
    */
    public E set(int i, E o){
        Assert.pre((0 <= i) && (i < size()), "Index in range.");
        DoublyLinkedNode<E> finger = head.next();
        //search for the ith element or the end of the list
        while (i > 0){
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
    * @pre value is not null
    * @post returns the (0-origin) index of the value, or -1 if the value is not found
    * @param value The value sought.
    * @return the index (0 is the first element) of the value, or -1
    */
    public int indexOf(E value){
        Assert.pre(value != null, "There is a value for us to search.");
        int i = 0;
        DoublyLinkedNode<E> finger = head.next();
        // search for value or end of list, counting along the way
        while (finger != null && !finger.value().equals(value)){
            finger = finger.next();
            i++;
        }
        if (finger == null){
            return -1;
        } else {
            return i;
        }
    }

    /**
    * Determine the last location of a value in the list.
    * @pre value is not null
    * @post returns the (0-origin) index of the value, or -1 if the value is not found
    * @param value the value sought.
    * @return the index (0 is the first element) of the value, or -1
    */
    public int lastIndexOf(E value){
        Assert.pre(value != null, "There is a value for us to search.");
        int i = size()-1;
        DoublyLinkedNode<E> finger = tail.previous();
        // search for the last matching value, result is desired index
        while (finger != null && !finger.value().equals(value)){
            finger = finger.previous();
            i--;
        }
        if (finger == null){
            return -1;
        } else {
            return i;
        }
    }

    /**
    * Check to see if a value is within the list.
    * @pre value not null
    * @post returns true iff value is in the list
    * @param value A value to be found in the list.
    * @return True if value is in list.
    */
    public boolean contains(E value){
        Assert.pre(value != null, "There is a value for us to search.");
        DoublyLinkedNode<E> finger = head.next();
        while (!finger.value().equals(value)){
            finger = finger.next();
        }
        return finger != null;
    }

    /**
    * Remove a value from the list.  At most one value is removed.
    * Any duplicates remain.  Because comparison is done with "equals,"
    * the actual value removed is returned for inspection.
    * @pre value is not null.  List can be empty.
    * @post first element matching value is removed from list
    * @param value The value to be removed.
    * @return The value actually removed.
    */
    public E remove(E value){
        Assert.pre(value != null, "There is a value for us to search.");
        DoublyLinkedNode<E> finger = head.next();
        while (!finger.value().equals(value)){
            finger = finger.next();
        }
        finger.previous().setNext(finger.next());
        finger.next().setPrevious(finger.previous());
        return finger.value();
    }

    /**
    * Construct a string representation of the list.
    * @post returns a string representing list
    * @return A string representing the elements of the list.
    */
    public String toString(){
        StringBuffer s = new StringBuffer();
        s.append("<LinkedList:");
        Iterator<E> li = iterator();
        while (li.hasNext()){
            s.append(" "+li.next());
        }
        s.append(">");
        return s.toString();
    }

    /**
    * Construct an iterator to traverse the list.
    * @post returns iterator that allows the traversal of list.
    * @return An iterator that traverses the list from head to tail.
    */
    public Iterator<E> iterator(){
        return new DoublyLinkedListIterator<E>(head,tail);
    }
}
