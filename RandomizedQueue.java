import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add null item.");
        }
        if (size == q.length) {
            resize(2 * q.length);
        }
        q[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        int index = StdRandom.uniform(size); // Replace with updated method if available
        Item item = q[index];
        q[index] = q[size - 1]; // Swap with the last item
        q[size - 1] = null; // Avoid loitering
        size--;
        if (size > 0 && size == q.length / 4) {
            resize(q.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        int index = StdRandom.uniform(size); // Replace with updated method if available
        return q[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> 
    {
        private final Item[] shuffledArray;

        private int i = 0;

        public RandomizedQueueIterator() {
            shuffledArray = (Item[]) new Object[size];
            System.arraycopy(q, 0, shuffledArray, 0, size);
            StdRandom.shuffle(shuffledArray);
        }

        public boolean hasNext() {
            return i < size;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return shuffledArray[i++];
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

    // resize the underlying array to the given capacity
    private void resize(int newCapacity) {
        Item[] newArray = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = q[i];
        }
        q = newArray;
    }

    //make use of all functions created above like enqueue, dequeue, sample, iterator, resize, isEmpty, size
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
    
        rq.enqueue("Hello");
        rq.enqueue("World");
        rq.enqueue("!");
    
        // Check if empty
        System.out.println("Is empty: " + rq.isEmpty());
    
        // Check size
        System.out.println("Size: " + rq.size());
    
        // Sample an item
        System.out.println("Sample: " + rq.sample());
    
        // Iterate and print
        for (String item : rq) {
            System.out.print(item + " ");
        }
        System.out.println();
    
        // Dequeue and print
        while (!rq.isEmpty()) {
            System.out.print(rq.dequeue() + " ");
        }
        System.out.println();
    
        // Check if empty again
        System.out.println("Is empty: " + rq.isEmpty());
    }

}