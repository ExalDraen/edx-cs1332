package org.alnx.edx.mod6;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        MinHeap<Integer> heap = new MinHeap<>();
        test_0();
        test_012();
        test_two_children();
        test_large();
    }

    public static void test_0() {
        MinHeap<Integer> heap = new MinHeap<>();
        System.out.println("-------Test 0 -----");
        heap.add(0);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
        Integer min = heap.remove();
        System.out.println("Got back: " + min);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
    }

    public static void test_012() {
        MinHeap<Integer> heap = new MinHeap<>();
        System.out.println("-------Test 0 -----");
        heap.add(0);
        heap.add(1);
        heap.add(2);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
        Integer min = heap.remove();
        System.out.println("Got back: " + min);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
    }

    public static void test_two_children() {
        MinHeap<Integer> heap = new MinHeap<>();
        System.out.println("-------Test 0 -----");
        heap.add(1);
        heap.add(0);
        heap.add(3);
        heap.add(5);
        heap.add(4);
        heap.add(2);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
        Integer min = heap.remove();
        System.out.println("Got back: " + min);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
    }

    public static void test_large() {
        MinHeap<Integer> heap = new MinHeap<>();
        System.out.println("-------Test lrg -----");
        heap.add(1);
        heap.add(0);
        heap.add(3);
        heap.add(5);
        heap.add(25);
        heap.add(2);
        heap.add(9);
        heap.add(7);
        heap.add(11);
        heap.add(16);
        heap.add(80);
        heap.add(56);
        heap.add(12);
        heap.add(13);
        heap.add(77);
        heap.add(66);
        heap.add(55);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
        Integer min = heap.remove();
        System.out.println("Got back: " + min);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
        System.out.println("Size: " + heap.size());
        min = heap.remove();
        System.out.println("Got back: " + min);
        System.out.println("Contents: " + Arrays.toString(heap.getBackingArray()));
        System.out.println("Size: " + heap.size());
    }
}
