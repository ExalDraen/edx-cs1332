package org.alnx.edx.mod5;

import java.util.*;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(BSTNode<T> root) {
        if (root == null) {
            return new LinkedList<>();
        }
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        final LinkedList<T> result = new LinkedList<>();
        result.add(root.getData());
        result.addAll(preorder(root.getLeft()));
        result.addAll(preorder(root.getRight()));
        return result;
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(BSTNode<T> root) {
        if (root == null) {
            return new LinkedList<>();
        }
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        final LinkedList<T> result = new LinkedList<>();
        result.addAll(inorder(root.getLeft()));
        result.add(root.getData());
        result.addAll(inorder(root.getRight()));
        return result;
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(BSTNode<T> root) {
        if (root == null) {
            return new LinkedList<>();
        }
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        final LinkedList<T> result = new LinkedList<>();
        result.addAll(postorder(root.getLeft()));
        result.addAll(postorder(root.getRight()));
        result.add(root.getData());
        return result;
    }

    List<T> levelorder(BSTNode<T> root) {
        if (root == null) {
            return new LinkedList<>();
        }
        Deque<BSTNode<T>> q = new ArrayDeque<>();
        List<T> ret = new LinkedList<T>();
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode<T> curr = q.pop();
            ret.add(curr.getData()); // for return
            if (curr.getLeft() != null) q.add(curr.getLeft());
            if (curr.getRight() != null) q.add(curr.getRight());
        }
        return ret;
    }
}