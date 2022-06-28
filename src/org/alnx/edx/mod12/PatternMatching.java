package org.alnx.edx.mod12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of the Boyer Moore string searching algorithm.
 */
public class PatternMatching {
    /**
     * Boyer Moore algorithm that relies on a last occurrence table.
     * This algorithm Works better with large alphabets.
     *
     * Make sure to implement the buildLastTable() method, which will be
     * used in this boyerMoore() method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class useful.
     *
     * You may assume that the passed in pattern, text, and comparator will not be null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return           List containing the starting index for each match found.
     */
    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {
        // edge cases
        int m = pattern.length();
        int n = text.length();
        if (m > n) return new ArrayList<>();
        if (m == 0) return new ArrayList<>();

        var table = buildLastTable(pattern);
        var results = new ArrayList<Integer>();  // LL is probably better here but who cares

        int i=0,j=0,shft =0; // i iterates text, j iterates pattern, shft is the shift value
        while (i <= n-m) {
            j=m-1; // start at end of pattern
            while (j>=0 && comparator.compare(text.charAt(i+j),pattern.charAt(j)) == 0) //match case
                j = j-1; // look backwards in the pattern
            if (j==-1) { //we've gone over the beginning of the pattern, success!
                results.add(i);
                i = i + 1; // move onto next
            }
            else { // didn't match
                shft = table.getOrDefault(text.charAt(i+j), -1);
                if (shft < j) {
                    i = i + j - shft;
                }
                else { // case 2:
                    i = i + 1;
                }
            }
        }
        return results;
    }

    /**
     * Builds the last occurrence table that will be used to run the Boyer Moore algorithm.
     *
     * Note that each char x will have an entry at table.get(x).
     * Each entry should be the last index of x where x is a particular
     * character in your pattern.
     * If x is not in the pattern, then the table will not contain the key x,
     * and you will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3
     * table.get(c) = 4
     * table.get(t) = 6
     * table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in
     * Boyer-Moore as -1
     *
     * If the pattern is empty, return an empty map.
     * You may assume that the passed in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return A Map with keys of all of the characters in the pattern mapping
     *         to their last occurrence in the pattern.
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {
        // null case
        if (pattern.length() == 0) { return new HashMap<>();}

        var table = new HashMap<Character, Integer>();
        for(int i=0;i<pattern.length();i++) {
            table.put(pattern.charAt(i), i);
        }
        return table;
    }
}
