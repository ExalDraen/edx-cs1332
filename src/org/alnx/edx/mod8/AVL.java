package org.alnx.edx.mod8;

import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        root = rAdd(root, data);
    }

    /**
     * Recursive add using "pointer reinforcement"
     * @param current root of the subtree to which data will be added
     * @param data data to be added to the subtree
     * @return subtree with new data added
     */
    private AVLNode<T> rAdd(AVLNode<T> current, T data) {
        // base case: current node null -> we've found where to add
        if (current == null) {
            size++;
            // don't need to update height/bf (int defaults to 0, which is correct)
            // or balance (leaf node is always balanced)
            return new AVLNode<T>(data);
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
        // update height&bf and balance if needed, then return new subtree root
        // (which may still be `current` if no rotations were done
        return balance(current);
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     *    replace the data, NOT predecessor. As a reminder, rotations can occur
     *    after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data was null");
        }
        AVLNode<T> returnContainer = new AVLNode<>(null);
        root = rRem(root, data, returnContainer);
        return returnContainer.getData();
    }

    private AVLNode<T> rRem(AVLNode<T> current, T data, AVLNode<T> rContainer) {
        final var SUCCESSOR = true;
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
                // no children. Just remove node, no need to rebalance anything
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
                AVLNode<T> tempContainer = new AVLNode<>(null);
                // Successor / predecessor removal , then set data for current to the data
                // we removed
                if (SUCCESSOR == true) {
                    current.setRight(removeSuccessor(current.getRight(), tempContainer));
                } else {
                    current.setLeft(removePredecessor(current.getLeft(), tempContainer));
                }
                current.setData(tempContainer.getData());
            }
        }
        // return node for reinforcement
        // balance() will rebalance the subtree if necessary and do height/bf updates
        return balance(current);
    }

    /**
     * Search until we find current node without right child, then return the left child at that point
     *
     * @param current root of the subtree within which we will search for node without right child
     * @param tempContainer output parameter; used to return the data that was removed
     * @return left child of node without right child
     */
    private AVLNode<T> removePredecessor(AVLNode<T> current, AVLNode<T> tempContainer) {
        if (current.getRight() == null) {
            // found predecessor, update return container and return the node
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
     * @param current root of the subtree within which we will search for node without left child
     * @param tempContainer output parameter; used to return the data that was removed
     * @return right child of node without left child
     */
    private AVLNode<T> removeSuccessor(AVLNode<T> current, AVLNode<T> tempContainer) {
        if (current.getLeft() == null) {
            // found successor, update return container and return the node
            tempContainer.setData(current.getData());
            return current.getRight();
        } else {
            // haven't found it yet. Keep looking left.
            current.setLeft(removeSuccessor(current.getLeft(), tempContainer));
            // may need to balance on the way back up
            //updateHeightAndBF(current);
            return balance(current);
        }
    }
    
    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    private void updateHeightAndBF(AVLNode<T> currentNode) {
        currentNode.setHeight(Math.max(height(currentNode.getLeft()), height(currentNode.getRight())) + 1);
        currentNode.setBalanceFactor(height(currentNode.getLeft()) - height(currentNode.getRight()));
    }

    private int height(AVLNode<T> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        // Conceptual shape
        // A (current)
        //  B
        //   C
        // Store B
        final var tmpRight = currentNode.getRight();

        // Set A's right to B's left
        // right heavy so getRight() aka B should not be null
        currentNode.setRight(currentNode.getRight().getLeft());

        // Set B's left to the current node (A)
        tmpRight.setLeft(currentNode);

        // update, starting at the bottom
        updateHeightAndBF(currentNode);
        updateHeightAndBF(tmpRight);

        // return node B, which took A's place
        return tmpRight;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        // Reverse of rotateLeft
        // Conceptual shape
        //   A (current)
        //  B
        // C
        // Store B
        final var tmpLeft = currentNode.getLeft();

        // Set A's left to B's right
        // left heavy so getLeft() aka B should not be null
        currentNode.setLeft(currentNode.getLeft().getRight());

        // Set B's right to the current node (A)
        tmpLeft.setRight(currentNode);

        // update, starting at the bottom
        updateHeightAndBF(currentNode);
        updateHeightAndBF(tmpLeft);

        // return node B, which took A's place
        return tmpLeft;
    }

    /**
     * Method that balances out the tree starting at the node passed in.
     * This method should be called in your add() and remove() methods to
     * facilitate rebalancing your tree after an operation.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param currentNode The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    private AVLNode<T> balance(AVLNode<T> currentNode) {
        /* First, we update the height and balance factor of the current node. */
        updateHeightAndBF(currentNode);

        if ( /* Condition for a right heavy tree. */ currentNode.getBalanceFactor() < -1 ) {
            //NB: right heavy so getRight() shouldn't be null
            if ( /* Condition for a right-left rotation. */ currentNode.getRight().getBalanceFactor() > 0 ) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if ( /* Condition for a left heavy tree. */ currentNode.getBalanceFactor() > 1) {
            //NB: left heavy so getLeft() shouldn't be null
            if ( currentNode.getLeft().getBalanceFactor() < 0 /* Condition for a left-right rotation. */ ) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }

        return currentNode;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
