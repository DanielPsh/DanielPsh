package hk.edu.polyu.comp.comp2021.assignment2.compset;

import java.util.ArrayList;
import java.util.List;

class CompSet<T> {

    /** Each CompSet uses at most 1023 buckets.   */
    private static final int NUMBER_OF_BUCKETS = 1023;

    /** An array of buckets as the storage for each set. */
    private final List<List<T>> storage;

    public CompSet() {
        storage = new ArrayList<>(NUMBER_OF_BUCKETS);
        for(int i = 0; i < NUMBER_OF_BUCKETS; i++){
            storage.add(new ArrayList<T>());
        }
    }

    /**
     * Initialize 'this' with the unique elements from 'elements'.
     * Throw IllegalArgumentException if 'elements' is null.
     */
    public CompSet(List<T> elements) {
        // Add missing code here
        this();
        if(elements == null){
            throw new IllegalArgumentException("elements is null");
        }
        for(T element : elements){
            add(element);
        }
    }

    /**
     * Get the total number of elements stored in 'this'.
     */
    public int getCount() {
        // Add missing code here
        int count = 0;
        for(List<T> bucket : storage){
            count += bucket.size();
        }
        return count;
    }

    public boolean isEmpty() {
        // Add missing code here
        return getCount() == 0;
    }

    /**
     * Whether 'element' is contained in 'this'?
     */
    public boolean contains(T element) {
        // Add missing code here
        if(element == null){
            throw new IllegalArgumentException("elements is null");
        }
        int index = getIndex(element);
        return storage.get(index).contains(element);
    }

    /**
     * Get all elements of 'this' as a list.
     */
    public List<T> getElements() {
        // Add missing code here
        List<T> elements = new ArrayList<>();
        for(List<T> bucket : storage){
            elements.addAll(bucket);
        }
        return elements;
    }

    /**
     * Add 'element' to 'this', if it is not contained in 'this' yet.
     * Throw IllegalArgumentException if 'element' is null.
     */
    public void add(T element) {
        // Add missing code here
        if(element == null){
            throw new IllegalArgumentException("elements is null");
        }
        int index = getIndex(element);

        if(!storage.get(index).contains(element)){
            storage.get(index).add(element);
        }

    }

    /**
     * Two CompSets are equivalent is they contain the same elements.
     * The order of the elements inside each CompSet is irrelevant.
     */
    public boolean equals(Object other){
        // Add missing code here
        if(this == other)
            return true;
        if(!(other instanceof CompSet))
            return false;
        CompSet<?> otherSet = (CompSet<?>) other;
        return this.getElements().containsAll(otherSet.getElements()) &&
                otherSet.getElements().containsAll(this.getElements());
    }

    /**
     * Remove 'element' from 'this', if it is contained in 'this'.
     * Throw IllegalArgumentException if 'element' is null.
     */
    public void remove (T element) {
        // Add missing code here
        if(element == null)
            throw new IllegalArgumentException("element is null");
        int index = getIndex(element);

        storage.get(index).remove(element);
    }

    //========================================================================== private methods

    private int getIndex(T element) {
        return element.hashCode() % NUMBER_OF_BUCKETS;
    }

}


