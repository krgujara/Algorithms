/* Randomized Queue */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Out;
import java.util.Iterator; 
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
public class RandomizedQueue<Item> implements Iterable<Item>
{
    // implementing randomized queue using arrays   
    private Item[] arr; //array of items
    private int n;
    private int capacity;
    
    // initialize empty stack
    public RandomizedQueue()
    {
     capacity = 2;   
     arr = (Item[]) new Object[capacity];
     n = 0;
    }
    
    
   public boolean isEmpty()                 // is the queue empty?
   {
       return n == 0;
   }
   public int size()                        // return the number of items on the queue
   {
       return n;
   }
   public void enqueue (Item item)           // add the item
   {
       if (item == null) throw new IllegalArgumentException();
       if (n+1 > capacity)
           resizePlus();
       arr[n++] = item;
   }
   private void resizePlus()
   {
       capacity = capacity*2;
       Item[] newarr = (Item[]) new Object[capacity];
       int index = 0;
       for (Item i : arr)
           newarr[index++] = i;
       arr = newarr;
   }
   private void resizeMinus()
   {
       capacity = capacity/2;
       Item[] newarr = (Item[]) new Object[capacity];
       //int index= 0;
       for (int i =0 ;i<n;i++)
           newarr[i] = arr[i];
       arr = newarr;
   }
   public Item dequeue()                    // remove and return a random item
   {
       if (isEmpty()) throw new NoSuchElementException();
       int randomIndex = StdRandom.uniform(n);  //returns random index between o and n-1 (including)
       Item temp = arr[randomIndex];
       
       if (randomIndex == n-1)
           arr[randomIndex] = null;
       else
       {
           arr[randomIndex] = arr[n-1];
           arr[n-1] = null;  
       }
       n--;
       if (n>0 && n ==  capacity/4)
           resizeMinus();
       return temp;
   }
   
   public Item sample()                     // return (but do not remove) a random ite
   {
       if (isEmpty()) throw new NoSuchElementException();
       int randomIndex = StdRandom.uniform(n);
       Item item = arr[randomIndex];
       return item;
   }
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   {
       return new RandomizedQueueIterator();
   }
   private class RandomizedQueueIterator implements Iterator<Item>
   {
       private int current;
       final private int[] shuffledIndexes;
       public RandomizedQueueIterator()
       {
            current = 0;
            shuffledIndexes = new int[n];
             for (int i=0;i<n;i++)
                   shuffledIndexes[i] = i;
               StdRandom.shuffle(shuffledIndexes);
             
       }
       //you cannot decrease the access privilage of the hasNext method 
       public boolean hasNext()
       {
           return current < n;
       }
       public Item next()
       {
           if (current >= n || size() == 0) 
               throw new NoSuchElementException();
          return arr[shuffledIndexes[current++]];
       }
       public void remove()
       {
           throw new UnsupportedOperationException();
       }
   }
   public static void main(String[] args)   // unit testing (optional)
   {
       RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
       Out out = new Out();
       out.println(rq.isEmpty());
       out.println(rq.size());
      
       rq.enqueue(736);
       rq.enqueue(784);
       rq.enqueue(988);
       
       out.println(rq.size());
       rq.dequeue();
       out.println(rq.size());

       rq.dequeue();
       out.println(rq.size());
       rq.dequeue();
       out.println(rq.size());
       //rq.dequeue();
       /*rq.enqueue(1);
       rq.enqueue(2);
       rq.enqueue(3);
       out.println(rq.size());
       Iterator it = rq.iterator();
        while (it.hasNext())
        {
            out.println(it.next());
        }
        rq.dequeue();
        */
       //out.println("Printing Again");
       //Iterator<Integer> it1 = rq.iterator();
       // while (it1.hasNext())
       // {
        //    out.println(it1.next());
       // }
     //   out.println(rq.sample());
       
   }
}