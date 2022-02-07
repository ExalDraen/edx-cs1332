package org.alnx.edx.mod8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        test_0();
        test_1();

    }

    public static void test_0() {
        //   1
        //  2
        // 3
        System.out.println("-------------T0------------------");
        AVL<Integer> avl = new AVL<>();
        AVLNode<Integer> root = new AVLNode<>(3);
        AVLNode<Integer> left = new AVLNode<>(2);
        AVLNode<Integer> leftleft = new AVLNode<>(1);
        root.setLeft(left);
        left.setLeft(leftleft);
        avl.updateHeightAndBF(leftleft);
        avl.updateHeightAndBF(left);
        avl.updateHeightAndBF(root);
        printAVL(root);
        System.out.println("------balance------");
        final var newRoot = avl.balance(root);
        printAVL(newRoot);
    }

    public static void test_1() {
        System.out.println("--------------T1-----------------");
        AVL<Integer> avl = new AVL<>();
        AVLNode<Integer> root = new AVLNode<>(1);
        AVLNode<Integer> right = new AVLNode<>(3);
        AVLNode<Integer> rightleft = new AVLNode<>(2);
        root.setRight(right);
        right.setLeft(rightleft);
        avl.updateHeightAndBF(rightleft);
        avl.updateHeightAndBF(right);
        avl.updateHeightAndBF(root);
        printAVL(root);
        System.out.println("------balance------");
        final var newRoot = avl.balance(root);
        printAVL(newRoot);
    }

    private static <T extends Comparable<? super T>> void printAVL(AVLNode<T> root)  {
        AVL<T> avl = new AVL<>();
        final var elems = levelorder(root);
        final var output = elems
                .stream()
                .map((e) -> e.getData().toString() + "[" + e.getHeight() + "," + e.getBalanceFactor() + "]")
                .collect(Collectors.joining(","));
        System.out.println(output);
    }

    /**
     * Added for testing by me
     * @param root root of the AVL to get levelorder from
     * @return in order list of elements from AVL
     */
    static <T extends Comparable<? super T>> List<AVLNode<T>> levelorder(AVLNode<T> root) {
        if (root == null) {
            return new LinkedList<>();
        }
        Deque<AVLNode<T>> q = new ArrayDeque<>();
        var ret = new LinkedList<AVLNode<T>>();
        q.add(root);
        while (!q.isEmpty()) {
            AVLNode<T> curr = q.pop();
//            ret.add(curr.getData()); // for return
            ret.add(curr);
            if (curr.getLeft() != null) q.add(curr.getLeft());
            if (curr.getRight() != null) q.add(curr.getRight());
        }
        return ret;
    }
}
