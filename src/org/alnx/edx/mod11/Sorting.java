package org.alnx.edx.mod11;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     * <p>
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     * <p>
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     * <p>
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     * <p>
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    @SuppressWarnings("unchecked")
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // Base case, array len 0/1 are sorted by definition
        if (arr.length <= 1) {
            return;
        }

        // Divide into two halves
        // If odd, left should be the smaller array
        var leftSize = Math.floorDiv(arr.length, 2);
        var rightSize = arr.length - leftSize;

        var left = (T[]) new Object[leftSize];
        var right = (T[]) new Object[rightSize];

        // fill halves with data
        //noinspection ManualArrayCopy
        for (int i = 0; i < leftSize; i++) {
            left[i] = arr[i];
        }
        //noinspection ManualArrayCopy
        for (int i = 0; i < rightSize; i++) {
            right[i] = arr[leftSize + i];
        }

        // sort halves
        mergeSort(left, comparator);
        mergeSort(right, comparator);

        // combine them
        merge(left, right, arr, comparator);
    }

    /**
     * Combine two halves of a merge sort
     *
     * @param left       left subarray
     * @param right      right subarray
     * @param arr        array (of length left.length+right.length) into which results will be merged
     * @param comparator the way to compare the elements of the subarray
     * @param <T>        type of the elements in the arrays
     */
    private static <T> void merge(T[] left, T[] right, T[] arr, Comparator<T> comparator) {
        int i = 0;
        int j = 0;

        // merge in appropriate elements
        while (i != left.length && j != right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }
        // one of the two subarrays might still contain elements
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     * <p>
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     * <p>
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     * <p>
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     * <p>
     * Do NOT use anything from the Math class except Math.abs().
     * <p>
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    @SuppressWarnings("unchecked")
    public static void lsdRadixSort(int[] arr) {
        ArrayList<Integer>[] buckets = new ArrayList[19];  // buckets for storing data, 19 to allow -9...0...9
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // determine longest number
        // because Integer.MIN_VALUE has a greater absolute than Integer.MAX_VALUE
        // we find the *smallest absolute value(negated)* to find the longest number
        // This avoids doing Math.abs(Integer.MIN_VALUE) (Overflow!)
        int longest = 0;
        for (int j : arr) {
            if (-Math.abs(j) < -Math.abs(longest)) {
                longest = j;
            }
        }
        final int maxDigits = numDigits(longest);

        // outer loop over all digits
        //int k = 0; //track which digit position we are on
        int dig; // temp variable for storing digit
        // it would be slightly more efficient to separately track the power of 10 for the longest here
        for (int k = 0; k < maxDigits; k++) {
            for (int j : arr) {
                dig = nthDigit(j, k);
                // +9 to deal with negative digits.
                buckets[dig + 9].add(j);
            }

            // unspool values back into array in order of digits
            int idx = 0;
            for (ArrayList<Integer> bucket : buckets) {
                if (!bucket.isEmpty()) {
                    for (int a : bucket) {
                        arr[idx] = a;
                        idx++;
                    }
                    bucket.clear();  // ready for next iteration of outer loop
                }
            }
        }
    }

    /**
     * Return the nth digit of num, in base 10, counting from the
     * lowest order digit (the right-most)
     *
     * So 0th digit of 123 is 3, 1st is 2, 2nd is 1, all others 0
     *
     * @param num number whose digit will be returned
     * @param n digit number to return, 0-indexed
     * @return the nth digit
     */
    private static int nthDigit(int num, int n) {
        // Skip n-1 digits by repeatedly dividing
        for (int i = 0; i < n; i++) {
            num = num / 10;
        }
        return num % 10;
    }

    /**
     * Return the number of digits in the given number, in base 10
     * @param num number whose digits will be counted
     * @return number of digits
     */
    private static int numDigits(int num) {
        int i = 0;
        while(num != 0) {
            num = num / 10;
            i++;
        }
        return i;
    }
}