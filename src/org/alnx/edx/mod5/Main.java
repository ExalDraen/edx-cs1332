package org.alnx.edx.mod5;

public class Main {

    public static void main(String[] args) {
        BST<Integer> myBST = new BST<>();
        myBST.add(30);
        myBST.add(50);
        myBST.add(20);
        myBST.add(25);
        myBST.add(15);
        myBST.add(70);
        myBST.add(80);
        myBST.add(85);
        myBST.add(75);
        myBST.add(10);

        Traversals<Integer> trav = new Traversals<>();
        System.out.println("-------------");
        System.out.println("Size: " + (myBST.size()));

        System.out.println("Levelorder: " + trav.levelorder(myBST.getRoot()));
        System.out.println("-------------");
        myBST.remove(20);
        myBST.remove(50);
        myBST.remove(75);
        System.out.println("-------------");
        System.out.println("Size: " + (myBST.size()));
        System.out.println("Levelorder: " + trav.levelorder(myBST.getRoot()));
        System.out.println("-------------");

        test_0();
        test_012();
        test_two_children();
        test_two_children2();
    }

    public static void test_0() {
        BST<Integer> bst = new BST<>();
        Traversals<Integer> trav = new Traversals<>();
        System.out.println("-------Test 0 -----");
        bst.add(0);
        System.out.println("Levelorder: " + trav.levelorder(bst.getRoot()));
        bst.remove(0);
        System.out.println("Levelorder: " + trav.levelorder(bst.getRoot()));
        CheckContainsAndGet(bst, 0);
    }

    private static void CheckContainsAndGet(BST<Integer> bst, Integer data) {
        System.out.println("Contains " + data + " : " + bst.contains(data));
        if (bst.contains(data)) {
            System.out.println("Get gives: " + bst.get(data));
        }
    }

    public static void test_012() {
        BST<Integer> bst = new BST<>();
        Traversals<Integer> trav = new Traversals<>();
        System.out.println("-------Test 012 -----");
        bst.add(1);
        bst.add(0);
        bst.add(2);
        System.out.println("Levelorder: " + trav.levelorder(bst.getRoot()));
        bst.remove(1);
        System.out.println("Levelorder: " + trav.levelorder(bst.getRoot()));
        CheckContainsAndGet(bst, 2);
        CheckContainsAndGet(bst, 1);
    }

    public static void test_two_children() {
        BST<Integer> bst = new BST<>();
        Traversals<Integer> trav = new Traversals<>();
        System.out.println("-------Test two children -----");
        bst.add(1);
        bst.add(0);
        bst.add(5);
        bst.add(4);
        bst.add(2);
        bst.add(3);
        CheckContainsAndGet(bst, 4);
        System.out.println("Levelorder: " + trav.levelorder(bst.getRoot()));
        bst.remove(1);
        System.out.println("Levelorder: " + trav.levelorder(bst.getRoot()));
    }

    public static void test_two_children2() {
        BST<Integer> bst = new BST<>();
        Traversals<Integer> trav = new Traversals<>();
        System.out.println("-------Test two children2 -----");
        bst.add(1);
        bst.add(0);
        bst.add(4);
        bst.add(3);
        bst.add(2);
        CheckContainsAndGet(bst, 2);
        System.out.println("Levelorder: " + trav.levelorder(bst.getRoot()));
        bst.remove(1);
        System.out.println("Levelorder: " + trav.levelorder(bst.getRoot()));
    }
}
