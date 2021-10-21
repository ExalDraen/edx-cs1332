package org.alnx.edx.mod7;

import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and Value must be non-nulL!");
        }

        // resize if overfull
        final float load = (float) (size + 1) / table.length;
        if (load > MAX_LOAD_FACTOR) {
            resizeBackingTable(2*table.length + 1);
        }
        // find slot for key - hash and compress
        final int slot = getSlot(key, table.length);

        if (table[slot] == null) {
            // basic case: key not in map already
            table[slot] = new ExternalChainingMapEntry<>(key, value);
            size++;
            return null;
        } else {
            // collision case: key may or may not be in map
            ExternalChainingMapEntry<K, V> next = table[slot];
            while(next != null) {
                if (next.getKey().equals(key)) {
                    // dupe, return early
                    final V old = next.getValue();
                    next.setValue(value);
                    return old;
                }
                next = next.getNext();
            }
            // no dupe, insert at head
            table[slot] = new ExternalChainingMapEntry<>(key, value, table[slot]);
        }
        size++;
        return null;
    }

    /**
     * Compute the slot / index of the given key in a table of the given length.
     *
     * Do this by hashing then compressing the hash.
     *
     * @param key key whose index will be returned
     * @return index into the backing array corresponding to this key
     */
    private static <K> int getSlot(K key, int length) {
        return Math.abs(key.hashCode() % length);
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key must be non-nulL!");
        }
        final int slot = getSlot(key, table.length);
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ExternalChainingMapEntry<K,V> entry = table[slot];
        if (entry == null) {
            // slot empty, definitely not in map
            throw new NoSuchElementException(String.format("Key %s not found", key));
        }

        // is it the head of the chain?
        if (entry.getKey().equals(key)) {
            final V old = entry.getValue();
            table[slot] = entry.getNext();
            size--;
            return old;
        }
        ExternalChainingMapEntry<K,V> next = entry.getNext();
        while(next != null) {
            if (next.getKey().equals(key)) {
                // found the element, remove and store value
                final V old = next.getValue();
                entry.setNext(next.getNext());
                size--;
                return old;
            }
            entry = next;
            next = next.getNext();
        }
        // if we fall through to here, the key is not in the map
        throw new NoSuchElementException(String.format("Key %s not found", key));
    }

    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        int slot;
        // grow
        ExternalChainingMapEntry<K,V>[] newTable = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];

        // iterate existing
        for(ExternalChainingMapEntry<K,V> e : table) {
            // base case: nothing there, move on
            if (e == null) {
                continue;
            }

            // something there; insert head into new table
            slot = getSlot(e.getKey(), newTable.length);
            // insert current key/val into new table by inserting a new
            // element as the HEAD. Base case (null) is covered by this as well
            newTable[slot] = new ExternalChainingMapEntry<>(e.getKey(), e.getValue(), newTable[slot]);

            // iterate over any other externally chained kv pairs at this entry
            ExternalChainingMapEntry<K, V> next = e.getNext();
            while(next != null) {
                slot = getSlot(next.getKey(), newTable.length);
                // insert current key/val into new table by inserting a new
                // element as the HEAD. Base case (null) is covered by this as well
                newTable[slot] = new ExternalChainingMapEntry<>(next.getKey(), next.getValue(), newTable[slot]);
                next = next.getNext();
            }
        }
        table = newTable;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}