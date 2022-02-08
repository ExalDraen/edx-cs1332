package org.alnx.edx.mod9;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        test_0();
        test_0_5();
        test_1();
        test_1_5();
        test_2();
        test_3();
        test_4();
        test_5();
        test_6();
    }

    public static void test_0() {
        System.out.println("-------------T0------------------");
        final Integer[] arr = {4,5,3,2,1};
        final var comp = new CountingComparator<Integer>(Comparator.naturalOrder());
        sortPrint(arr, comp);
    }
    public static void test_0_5() {
        System.out.println("-------------T0.5------------------");
        final Integer[] arr = {4,5,3,2,1};
        final var comp = new CountingComparator<Integer>(Comparator.reverseOrder());
        sortPrint(arr, comp);
    }

    public static void test_1() {
        System.out.println("-------------T1------------------");
        final String[] arr = {"aaa","aa","aaaa", "aaaaa"};
        final var comp = new CountingComparator<String>(Comparator.naturalOrder());
        sortPrint(arr, comp);
    }

    public static void test_1_5() {
        System.out.println("-------------T1.5------------------");
        final String[] arr = {"aaa","bbb", "aa", "ccc", "aaaa", "aaaaa"};
        final var comp = new CountingComparator<>(Comparator.comparing(String::length));
        sortPrint(arr, comp);
    }

    public static void test_2() {
        System.out.println("-------------T2------------------");
        final Integer[] arr = {5,4,3,2,1};
        final var comp = new CountingComparator<Integer>(Comparator.naturalOrder());
        sortPrint(arr, comp);
    }

    public static void test_3() {
        System.out.println("-------------T3------------------");
        final Integer[] arr = {1,2,3,4,5};
        final var comp = new CountingComparator<Integer>(Comparator.naturalOrder());
        sortPrint(arr, comp);
    }

    public static void test_4() {
        System.out.println("-------------T4------------------");
        final Integer[] arr = {1};
        final var comp = new CountingComparator<Integer>(Comparator.naturalOrder());
        sortPrint(arr, comp);
    }

    public static void test_5() {
        System.out.println("-------------T5------------------");
        final Integer[] arr = {};
        final var comp = new CountingComparator<Integer>(Comparator.naturalOrder());
        sortPrint(arr, comp);
    }

    public static void test_6() {
        System.out.println("-------------T6------------------");
        final Integer[] arr = {4,3,1,5,2,6,7};
        final var comp = new CountingComparator<Integer>(Comparator.naturalOrder());
        sortPrint(arr, comp);
    }


    private static <T> void sortPrint(T[] arr, CountingComparator<T> comp) {
        System.out.println("Input:  " + Arrays.toString(arr));
        T[] workArr;

        comp.clearCount();
        workArr = arr.clone();
        System.out.println("=== Bubble === ");
        Sorting.bubbleSort(workArr, comp);
        System.out.println("Output: " + Arrays.toString(workArr));
        System.out.println("Comparisons made: " + comp.count());

        comp.clearCount();
        workArr = arr.clone();
        System.out.println("=== Insertion === ");
        Sorting.insertionSort(workArr, comp);
        System.out.println("Output: " + Arrays.toString(workArr));
        System.out.println("Comparisons made: " + comp.count());

        comp.clearCount();
        workArr = arr.clone();
        System.out.println("=== Selection === ");
        Sorting.selectionSort(workArr, comp);
        System.out.println("Output: " + Arrays.toString(workArr));
        System.out.println("Comparisons made: " + comp.count());
    }
}
