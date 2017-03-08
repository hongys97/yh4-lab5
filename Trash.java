import structure5.DoublyLinkedList;
import structure5.DoublyLinkedNode;

public class Trash<E> extends DoublyLinkedList<E>{

  public Trash(){

  }

  protected void insertAfter(E value, DoublyLinkedNode<E> previous)
  {
     // Students: write this code.
     //Assert.pre(previous != null && previous != head, "There is a value for us to add E after, and the method cannot be used to insert a value a dummy node.");
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

  }
}
