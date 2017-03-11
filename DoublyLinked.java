// Implementation of lists, using doubly linked elements.
// (c) 1998, 2001 duane a. bailey
package structure5;
import java.util.Iterator;
/**
 * An implementation of lists using doubly linked elements, similar to that of {@link java.util.LinkedList java.util.LinkedList}.
 * <p>
 * This class is a basic implementation of the {@link List} interface.
 * Operations accessing or modifying either the head or the tail of
 * the list execute in constant time.
 * Doubly linked lists are less space-efficient than singly linked lists,
 * but tail-related operations are less costly.
 * <p>
 * Example usage:
 *
 * To place a copy of every unique parameter passed to a program into a
 * DoublyLinkedList,  we would use the following:
 * <pre>
 * public static void main({@link java.lang.String String[]} arguments)
 * {
 *     {@link DoublyLinkedList} argList = new {@link #DoublyLinkedList()};
 *     for (int i = 0; i < arguments.length; i++){
 *         if (!argList.{@link #contains(Object) contains(arguments[i])}){
 *             argList.{@link #add(Object) add(arguments[i])};
 *         }
 *    }
 *    System.out.println(argList);
 * }
 * </pre>
 * @version $Id: DoublyLinkedList.java 31 2007-08-06 17:19:56Z bailey $
 * @author, 2001 duane a. bailey
 * @see SinglyLinkedList
 * @see CircularList
 */
public class DoublyLinked<E> extends AbstractList<E>
{
    protected int count;
    protected DoublyLinkedNode<E> head;
    protected DoublyLinkedNode<E> tail;

    public DoublyLinked()
    {
        head = null;
        tail = null;
        count = 0;
    }


    public void add(E value)
    {
        addFirst(value);
    }

    public void addFirst(E value)
    {
        // construct a new element, making it head
        head = new DoublyLinkedNode<E>(value, head, null);
        // fix tail, if necessary
        if (tail == null) tail = head;
        count++;
    }

    public E removeFirst()
    {
        Assert.pre(!isEmpty(),"List is not empty.");
        DoublyLinkedNode<E> temp = head; // temp value to store first value
        head = head.next(); // make the 2nd node the 1st
        if (head != null) { // If there is a 2nd node, now there's nothing in front of it, making it the 1st
            head.setPrevious(null);
        } else { // If there is no 2nd node, there is nothing at the end of the list (remove first = remove first/last/only element in the list)
            tail = null; // remove final value
        }
        temp.setNext(null);// helps clean things up; temp is free
        count--;
        return temp.value();
    }

    public void addLast(E value)
    {
        // construct new element
        tail = new DoublyLinkedNode<E>(value, null, tail);
        // fix up head
        if (head == null) head = tail;
        count++;
    }

    public E removeLast()
    {
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
    /*
    public void addLast(E value)
    {
        // construct new element
        tail = new DoublyLinkedNode<E>(value, null, tail);
        count++;
    }

    public E removeLast()
    {
        Assert.pre(!isEmpty(),"List is not empty.");
        DoublyLinkedNode<E> temp = tail;
        tail = tail.previous();
        tail.setNext(null);
        count--;
        return temp.value();
    }
    */

    /**
     * Get a copy of first value found in list.
     *
     * @pre list is not empty
     * @post returns first value in list
     *
     * @return A reference to first value in list.
     */
    public E getFirst()
    {
        return head.value();
    }

    /**
     * Get a copy of last value found in list.
     *
     * @pre list is not empty
     * @post returns last value in list
     *
     * @return A reference to last value in list.
     */
    public E getLast()
    {
        return tail.value();
    }

    public boolean contains(E value)
    {
        DoublyLinkedNode<E> finger = head;
        while ((finger != null) && (!finger.value().equals(value)))
        {
            finger = finger.next();
        }
        return finger != null;
    }

    public E remove(E value)
    {
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
            count--;            // fewer elements
            return finger.value();
        }
        return null;
    }


    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public void clear()
    {
        head = tail = null;
        count = 0;
    }

    public E get(int i)
    {
        if (i >= size()) return null;
        DoublyLinkedNode<E> finger = head;
        // search for ith element or end of list
        while (i > 0)
        {
            finger = finger.next();
            i--;
        }
        // not end of list, return value found
        return finger.value();
    }

    public E set(int i, E o)
    {
        if (i >= size()) return null;
        DoublyLinkedNode<E> finger = head;
        // search for ith element or end of list
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

    public void add(int i, E o)
    {
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

    public E remove(int i)
    {
        Assert.pre((0 <= i) &&
                   (i < size()), "Index in range.");
        if (i == 0) return removeFirst();
        else if (i == size()-1) return removeLast();
        DoublyLinkedNode<E> previous = null;
        DoublyLinkedNode<E> finger = head;
        // search for value indexed, keep track of previous
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

    public int indexOf(E value)
    {
        int i = 0;
        DoublyLinkedNode<E> finger = head;
        // search for value or end of list, counting along way
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

    public int lastIndexOf(E value)
    {
        int i = size()-1;
        DoublyLinkedNode<E> finger = tail;
        // search for last matching value, result is desired index
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

    public Iterator<E> iterator()
    {
        return new DoublyLinkedListIterator<E>(head);
    }


    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("<DoublyLinkedList:");
        Iterator li = iterator();
        while (li.hasNext())
        {
            s.append(" "+li.next());
        }
        s.append(">");
        return s.toString();
    }
}
