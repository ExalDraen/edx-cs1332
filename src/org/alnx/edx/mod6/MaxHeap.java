package org.alnx.edx.mod6;

import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MaxHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MaxHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * <p>
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("May not add nulld ata");
        }
        // check backing array and double, manually
        if (size == backingArray.length - 1) {
            doubleBacking();
        }

        // 1. add
        backingArray[size + 1] = data;
        size++;
        // 2. up-heap
        upHeap(size);
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }

    private void doubleBacking() {
        T[] newBacking = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i < backingArray.length; ++i) {
            newBacking[i] = backingArray[i];
        }
        backingArray = newBacking;
    }

    private void upHeap(int idx) {
        // base case: we're at the top
        if (idx <= 1) {
            return;
        }
        // find parent
        int parentIdx = idx / 2;
        // optional swap if parent *smaller*
        if (backingArray[parentIdx].compareTo(backingArray[idx]) < 0) {
            swapByIdx(idx, parentIdx);
        }
        // recurse
        upHeap(parentIdx);
    }

    private void swapByIdx(int idx, int parent) {
        T temp = backingArray[parent];
        backingArray[parent] = backingArray[idx];
        backingArray[idx] = temp;
    }

    /**
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * You may assume that the heap is not empty.
     *
     * @return The data that was removed.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException("Heap empty!");
        }
        // pop max
        final T max = backingArray[1];
        swapByIdx(1, size);
        backingArray[size] = null;
        size--;
        // fix order
        downHeap(1);
        // finally return the max
        return max;
    }

    private void downHeap(int idx) {
        // base case: we're at a leaf node
        if (idx > size / 2) {
            return;
        }
        // find children
        int lChildIdx = idx * 2;
        int rChildIdx = idx * 2 + 1;

        // 1-child case
        // note: there should always be a left child, because we are not looking at leaf nodes
        if (backingArray[rChildIdx] == null) {
            if (backingArray[lChildIdx].compareTo(backingArray[idx]) > 0) {
                swapByIdx(idx, lChildIdx);
            }
            downHeap(lChildIdx);
            return;
        }

        // 2 child case
        // swap with highest priority child
        if (backingArray[lChildIdx].compareTo(backingArray[rChildIdx]) > 0) {
            if (backingArray[lChildIdx].compareTo(backingArray[idx]) > 0) {
                swapByIdx(idx, lChildIdx);
            }
            downHeap(lChildIdx);
        } else {
            if (backingArray[rChildIdx].compareTo(backingArray[idx]) > 0) {
                swapByIdx(idx, rChildIdx);
            }
            downHeap(rChildIdx);
        }
    }

    /**
     * Returns the backing array of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

}
