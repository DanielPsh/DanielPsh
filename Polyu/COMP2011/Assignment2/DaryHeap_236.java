package comp2011.a2; // Don't change this line!
/**
 *
 * You are forbidden to use {@code import} or {@code java.} in this file.
 *
 * Do not change the signature of any given method, but feel free to introduce new ones, which must be {@code private}.
 */

/**
 * VERY IMPORTANT.
 * 
 * I've sought help from the following GenAI:
 * 1.poe
 * 2.
 * 3.
 * ...
 * 
 * I've discussed this question with the following students (secret numbers, not names!):
 * 1.
 * 2.
 * 3.
 * ...
 * 
 * I've sought help from the following Internet resources and books:
 * 1.geeksforgeeks
 * 2.
 * 3.
 * ...
 * 
 * My secret number is 326, I'm implementing a 4-ary min (min/max) heap.
 */
public class DaryHeap_236<T extends Comparable<T>> { // Please replace 000 with your secret number!

    private T[] heap;
    private int capacity;
    private int size;
    private final int D;

    @SuppressWarnings("unchecked")
    public DaryHeap_236(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = (T[]) new Comparable[capacity];
        this.D = 4;
    }

    public void insert(T x) {
        if(size >= capacity){
            throw new IllegalStateException("Full");
        }
        heap[size] = x;
        size++;
        up(size - 1);
    }

    // Running time: O(log(n)).
    public T removeRoot() {
        if(size == 0){
            throw new IllegalStateException("Empty");
        }
        T root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        down(0);
        return root;
    }

    // Running time: O(log(n)).
    private void up(int c) {
        while (c > 0) {
            int p = (c - 1) / D;
            if (heap[c].compareTo(heap[p]) < 0) {
                swap(c, p);
                c = p;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    // Running time: O(log(n)).
    private void down(int ind) {
        T value = heap[ind];
        while (true) {
            int childIndex = D * ind + 1;
            if (childIndex >= size) {
                break;
            }
            int minIndex = childIndex;
            for (int i = 1; i < D; i++) {
                int nextChildIndex = childIndex + i;
                if (nextChildIndex < size && heap[nextChildIndex].compareTo(heap[minIndex]) < 0) {
                    minIndex = nextChildIndex;
                }
            }
            if (value.compareTo(heap[minIndex]) <= 0) {
                break;
            }
            heap[ind] = heap[minIndex];
            ind = minIndex;
        }
        heap[ind] = value;
    }

    /**
     * Merge the given {@code heap} with {@code this}.
     * The result will be stored in {@code this}.
     *
     * VERY IMPORTANT.
     *
     * I've sought help from the following GenAI:
     * 1.poe
     * 2.
     * 3.
     * ...
     * 
     * I've discussed this question with the following students (secret numbers, not names!):
     * 1.
     * 2.
     * 3.
     * ...
     * 
     * I've sought help from the following Internet resources and books:
     * 1.geeksforgeeks
     * 2.
     * 3.
     * ...
     *
     * Running time: O(n^2).
     */
    public void merge(DaryHeap_236<T> heap) {
        for(int i = 0; i < heap.size; i++){
            insert(heap.heap[i]);
        }
    }
}
