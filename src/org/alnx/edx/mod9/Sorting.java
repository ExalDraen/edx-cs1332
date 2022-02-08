package org.alnx.edx.mod9;

import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        var stopIdx = arr.length - 1;
        int lastSwapIdx;

        while(stopIdx != 0) {
            lastSwapIdx = 0;
            for (int i = 0; i < stopIdx; i++) {
                if (comparator.compare(arr[i], arr[i+1]) > 0) {
                    swap(i, i+1, arr);
                    lastSwapIdx = i;
                }
            }
            stopIdx = lastSwapIdx;
        }
    }

    /**
     * Swap elements at index i and j inside the given array arr. Assumes i,j >0 and < arr.length
     */
    private static <T> void swap(int i, int j, T[] arr) {
        final var tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        int max;
        // Outer loop tracks subarray that isn't yet sorted
        for (int i = arr.length - 1; i > 0; i--) {
            max=i;
            // inner loop finds maximum index
            for (int j = 0; j < i; j++) {
                if (comparator.compare(arr[j], arr[max]) > 0) {
                    max = j;
                }
            }
            // swap to last idx of unsorted portion
            swap(i,max,arr);
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        int j;
        // outer loop iterates over all elements until the end
        // can start at 1 because 1-element subarray is already sorted.
        for (int i = 1; i < arr.length; i++) {
            j = i;
            // inner loop iterates backwards over the first portion of the array
            // and sorts it relatively
            while(j != 0 && (comparator.compare(arr[j], arr[j-1]) < 0)) {
                swap(j,j-1,arr);
                j--;
            }
        }
    }
}
