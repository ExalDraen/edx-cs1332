package org.alnx.edx.mod5;

import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The new data should become a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        root = rAdd(root, data);
    }

    /**
     * Recursive add using "pointer reinforcement"
     * @param current
     * @param data
     * @return
     */
    private BSTNode<T> rAdd(BSTNode<T> current, T data) {
        // base case: current node null -> we've found where to add
        if (current == null) {
            size++;
            return new BSTNode<T>(data);
        }
        if (data.compareTo(current.getData()) < 0) {
            // left side
            current.setLeft(rAdd(current.getLeft(), data));
        } else if (data.compareTo(current.getData()) == 0) {
            // equal -- don't need to do anything
        } else if (data.compareTo(current.getData()) > 0) {
            // right side
            current.setRight(rAdd(current.getRight(), data));
        }
        // fall-through after all the recursive calls
        return current;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        BSTNode<T> returnContainer = new BSTNode<>(null);
        root = rRem(root, data, returnContainer);
        return returnContainer.getData();
    }

    private BSTNode<T> rRem(BSTNode<T> current, T data, BSTNode<T> rContainer) {
        if (current == null) {
            // base case, reached null so node not found
            throw new NoSuchElementException(String.format("Data %s not found", data));
        }
        if (data.compareTo(current.getData()) < 0) {
            // recurse left and reinforce left branch
            current.setLeft(rRem(current.getLeft(), data, rContainer));
        } else if (data.compareTo(current.getData()) > 0) {
            // recurse right and reinforce right branch
            current.setRight(rRem(current.getRight(), data, rContainer));
        } else {
            // update return data and size
            rContainer.setData(current.getData());
            size--;
            // need to actually remove it now
            if (current.getLeft() == null && current.getRight() == null) {
                // no children. Just remove node
                return null;
            } else if (current.getLeft() == null && current.getRight() != null) {
                // current -> right
                current = current.getRight();
            } else if (current.getLeft() != null && current.getRight() == null) {
                // current -> left
                current = current.getLeft();
            } else {
                // 2 children
                // temp container to store data removed by successor removal
                BSTNode<T> tempContainer = new BSTNode<>(null);
                // find successor and remove, then set data for current to the data
                // we removed
                current.setRight(removeSuccessor(current.getRight(), tempContainer));
                current.setData(tempContainer.getData());
            }
        }
        // return node for reinforcement
        return current;
    }

    /**
     * Search until we find current node without right child, then return the left child at that point
     *
     * @param current
     * @param tempContainer
     * @return
     */
    private BSTNode<T> removePredecessor(BSTNode<T> current, BSTNode<T> tempContainer) {
        if (current.getRight() == null) {
            // found predecessor update return container and return the node
            tempContainer.setData(current.getData());
            return current.getLeft();
        } else {
            // haven't found it yet. Keep looking right.
            current.setRight(removePredecessor(current.getRight(), tempContainer));
            return current;
        }
    }

    /**
     * Search until we find current node without left child, then return the right child at that point
     *
     * @param current
     * @param tempContainer
     * @return
     */
    private BSTNode<T> removeSuccessor(BSTNode<T> current, BSTNode<T> tempContainer) {
        if (current.getLeft() == null) {
            // found predecessor update return container and return the node
            tempContainer.setData(current.getData());
            return current.getRight();
        } else {
            // haven't found it yet. Keep looking left.
            current.setLeft(removeSuccessor(current.getLeft(), tempContainer));
            return current;
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}