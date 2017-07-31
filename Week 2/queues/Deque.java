import edu.princeton.cs.algs4.Out;
import java.util.Iterator;
import java.util.NoSuchElementException;
//java.lang by default included
//import java.lang.UnsupportedOperationException;
/*
 * Implementation of Deque 
 * Inserting and deleting of nodes from front and back possible.
 */

public class Deque<Item> implements Iterable<Item> {
  
    private Node<Item> first;
    private Node<Item> last;
    private int n;
    
    /*
        *private inner node class has next and prev pointers to implement constant 
        * worst case time operations
        */
    private class Node<Item>
    {
        final private Item item;
        private Node<Item> prev;
        private Node<Item> next;
        
        Node(Item item)
        {
            this.item = item;
            this.next = null;
            this.prev = null;
               
        }
    }
    
   /*
    * Dequeue iterator is private inner class which implements hasNext().
    * remove(). next();
    */
    
   private class DequeueIterator implements Iterator<Item>
   {
       private Node<Item> current;
       public DequeueIterator()
       {
           this.current = first;
       }
       public boolean hasNext()
       {
           return current != null;
       }
       public Item next()
       {
           if (!this.hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
       public void remove()
       {
           throw new UnsupportedOperationException();
       }
   }
    public Deque()                           // construct an empty deque
   {
       this.first = null;
       this.last = null;
       this.n = 0;
   }
   
   public boolean isEmpty()                 // is the deque empty?
   {
       return this.size() == 0;
   }
   public int size()                        // return the number of items on the deque
   {
    return this.n;   
   }
   
   /*
    * Adds an item to the front of the deque        
    * @param item Item to be inserted
    */
   public void addFirst(Item item)          
   {
       if (item == null) 
       {
           throw new IllegalArgumentException();
       }
       
       if (this.isEmpty())
       {
           this.first = new Node<Item>(item);
           this.last = first;
       } else {
           Node<Item> node = new Node<Item>(item);
           node.next = this.first;
           this.first.prev = node;
           this.first = node;
       }
       this.n++;
   }
   public void addLast(Item item)           // add the item to the end
   {
       if (item == null) 
       {
           throw new IllegalArgumentException();
       }

       if (this.isEmpty()){
           this.last = new Node<Item>(item);
           this.first = last;
       } else {
           Node<Item> node = new Node<Item>(item);
           node.prev = last;
           this.last.next = node;
           this.last = node;
       }
       this.n++;
   }
   
   
   public Item removeFirst()                // remove and return the item from the front
   {
       if (this.isEmpty())
       {
           throw new NoSuchElementException();
       }
       Node<Item> node = this.first;
       if (this.size() == 1)
       {
           this.first = null;
           this.last = null;
       } else
       {
           this.first.next.prev = null;
           this.first = this.first.next;
       }
       n--;
       node.next = null;
       return node.item;
   }
   
   public Item removeLast()                 // remove and return the item from the end
   {
       if (this.isEmpty()) { throw new NoSuchElementException();}
       Node<Item> node = this.last;
       if (this.n == 1)
       {
           this.first = null;
           this.last = null;
       } else
       {
           this.last.prev.next = null;
           this.last = this.last.prev;
       }
       n--;
       node.next = null;
       return node.item;
   }
   
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
       return new DequeueIterator();
   }
       
    public static void main(String[] args)   // unit testing (optional)
    {
        Deque<Integer> deq = new Deque<Integer>();
        Out out = new Out();
        out.println("deq : "+deq.toString());
        out.println("size : "+deq.size());
        /*
        deq.addLast(0);
        deq.addLast(1);
        deq.addLast(2);
        deq.addFirst(3);
        deq.addLast(4);
        deq.addLast(5);
         deq.addLast(6);
         deq.addFirst(7);
         deq.addFirst(8);
         deq.size(); */
        deq.removeFirst();
        deq.removeLast();
        Iterator<Integer> it = deq.iterator();
        while (it.hasNext())
        {
            out.println(it.next());
        }
    }
}