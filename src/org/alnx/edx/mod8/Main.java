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
        test_2();
        test_3();
    }

    public static void test_0() {
        //   1
        //  2
        // 3
        System.out.println("-------------T0------------------");
        AVLBasic<Integer> avlBasic = new AVLBasic<>();
        AVLNode<Integer> root = new AVLNode<>(3);
        AVLNode<Integer> left = new AVLNode<>(2);
        AVLNode<Integer> leftleft = new AVLNode<>(1);
        root.setLeft(left);
        left.setLeft(leftleft);
        avlBasic.updateHeightAndBF(leftleft);
        avlBasic.updateHeightAndBF(left);
        avlBasic.updateHeightAndBF(root);
        printAVL(root);
        System.out.println("------balance------");
        final var newRoot = avlBasic.balance(root);
        printAVL(newRoot);
    }

    public static void test_1() {
        System.out.println("--------------T1-----------------");
        AVLBasic<Integer> avlBasic = new AVLBasic<>();
        AVLNode<Integer> root = new AVLNode<>(1);
        AVLNode<Integer> right = new AVLNode<>(3);
        AVLNode<Integer> rightleft = new AVLNode<>(2);
        root.setRight(right);
        right.setLeft(rightleft);
        avlBasic.updateHeightAndBF(rightleft);
        avlBasic.updateHeightAndBF(right);
        avlBasic.updateHeightAndBF(root);
        printAVL(root);
        System.out.println("------balance------");
        final var newRoot = avlBasic.balance(root);
        printAVL(newRoot);
    }

    public static void test_2() {
        System.out.println("--------------T2-----------------");
        final var avl = new AVL<Integer>();
        avl.add(7);
        avl.add(4);
        avl.add(10);
        avl.add(2);
        avl.add(6);
        avl.add(8);
        avl.add(11);
        avl.add(0);
        avl.add(3);
        avl.add(5);
        avl.add(9);
        avl.add(1);
        printAVL(avl.getRoot());
        System.out.println("------remove------");
        avl.remove(4);
        printAVL(avl.getRoot());
    }

    public static void test_3() {
        System.out.println("--------------T3-----------------");
        final var avl = new AVL<Integer>();
        avl.add(7);
        avl.add(4);
        avl.add(15);
        avl.add(1);
        avl.add(6);
        avl.add(10);
        avl.add(18);
        avl.add(0);
        avl.add(3);
        avl.add(5);
        avl.add(8);
        avl.add(13);
        avl.add(16);
        avl.add(19);
        avl.add(2);
        avl.add(9);
        avl.add(11);
        avl.add(14);
        avl.add(17);
        avl.add(12);
        printAVL(avl.getRoot());
        System.out.println("------remove------");
        avl.remove(7);
        printAVL(avl.getRoot());
    }

    private static <T extends Comparable<? super T>> void printAVL(AVLNode<T> root)  {
        AVLBasic<T> avlBasic = new AVLBasic<>();
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
