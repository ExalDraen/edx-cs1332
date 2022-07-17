package org.alnx.edx.mod1;


import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

    /*
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayList.
     * <p>
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    private void growIfNeeded() {
        if (size <= backingArray.length) {
            return;
        }
        T[] newBacking = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            newBacking[i] = backingArray[i];
        }
        backingArray = newBacking;
    }

    /**
     * Adds the data to the front of the list.
     * <p>
     * This add may require elements to be shifted.
     * <p>
     * Method should run in O(n) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't add null data");
        }
        size++;
        growIfNeeded();
        // Copy all elements forward
        for (int j = size - 1; j > 0; j--) {
            backingArray[j] = backingArray[j - 1];
        }
        backingArray[0] = data;
    }

    /**
     * Adds the data to the back of the list.
     * <p>
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't add null data");
        }
        size++;
        growIfNeeded();
        backingArray[size - 1] = data;
    }

    /**
     * Removes and returns the first data of the list.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * This remove may require elements to be shifted.
     * <p>
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        var removed = backingArray[0];
        // Copy all elements backwards
        for (int j = 0; j < size - 1; j++) {
            backingArray[j] = backingArray[j + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return removed;
    }

    /**
     * Removes and returns the last data of the list.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        var removed = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return removed;
    }

    /**
     * Returns the backing array of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}