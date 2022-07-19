package org.alnx.edx.mod2;

import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't add null data!");
        }
        size++;
        var newNode = new SinglyLinkedListNode<T>(data);
        // special case: size was 0 before (now 1)
        if (size == 1){
            head = newNode;
            tail = newNode;
            return;
        }
        newNode.setNext(head);
        head = newNode;
        // tail pointer edge case, for adding to a list to take it from size 1 to 2
        if (size == 2) {
            tail = newNode.getNext();
        }
    }

    /**
     * Adds the element to the back of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't add null data!");
        }
        size++;
        var newNode = new SinglyLinkedListNode<T>(data);
        // special case: size was 0 before (now 1)
        if (size == 1){
            head = newNode;
            tail = newNode;
            return;
        }
        tail.setNext(newNode);
        // head pointer edge case, for adding to a list to take it from size 1 to 2
        if (size == 2) {
            head = tail;
        }
        tail = newNode;
    }

    /**
     * Adds the element to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * ASSUMPTIONS:
     * - You may assume that the index will always be valid [0, size]
     * - You may assume that the data will not be null
     *
     * @param index the index to add the new element
     * @param data  the data to add
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Can't add null data!");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Can't add null data!");
        }
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
        // special case: size is 0
        if (size == 0) {
            head = tail = newNode;
            size++;
            return;
        }
        // special case: adding at end
        if (index == size) {
            tail.setNext(newNode);
            tail = newNode;
            size++;
            return;
        }
        // special case: adding at head
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            size++;
            return;
        }

        // normal case: traverse index-1 times
        // then insert new node after that
        var current = head;
        for (int i=0;i<index -1;i++) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (size == 0) {
            throw new NoSuchElementException("List empty!");
        }
        size--;
        var ret = head;
        head = head.getNext();
        // edge cases for tail pointer
        // size now 0: tail should be null
        // size now 1: tail should be == head
        if (size == 0 || size == 1) {
            tail = head;
        }
        return ret.getData();
    }

    /**
     * Removes and returns the last data of the list.
     * <p>
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (size == 0) {
            throw new NoSuchElementException("List empty!");
        }
        size--;
        // edge case going from size 1 to 0:
        // return the one element and set tail / head to null
        if( size == 0) {
            var ret = head;
            head = null;
            tail = null;
            return ret.getData();
        }

        // traverse list until we are at the penultimate element
        var current = head;
        while( current.getNext().getNext() != null) {
            current = current.getNext();
        }
        var ret = current.getNext();
        tail = current;
        current.setNext(null);

        return ret.getData();
    }

    /**
     * Returns the head node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
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
