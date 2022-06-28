package org.alnx.edx.mod12;

public class Main {

    public static void main(String[] args) {
        test_0();
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
    }

    public static void test_0() {
        System.out.println("-------------T0------------------");
        final CharSequence pattern = "";
        final CharSequence text = "aabaabaab";
        final var comp = new CharacterComparator();
        matchPrint(pattern,text, comp);
    }

    public static void test_1() {
        System.out.println("-------------T1------------------");
        final CharSequence pattern = "adsfagerasgafgh";
        final CharSequence text = "aabaabaab";
        final var comp = new CharacterComparator();
        matchPrint(pattern,text, comp);
    }

    public static void test_2() {
        System.out.println("-------------T2------------------");
        final CharSequence pattern = "aab";
        final CharSequence text = "aabaabaab";
        final var comp = new CharacterComparator();
        matchPrint(pattern,text, comp);
    }

    public static void test_3() {
        System.out.println("-------------T3------------------");
        final CharSequence pattern = "aab";
        final CharSequence text = "abaaababa";
        final var comp = new CharacterComparator();
        matchPrint(pattern,text, comp);
    }

    public static void test_4() {
        System.out.println("-------------T4------------------");
        final CharSequence pattern = "cde";
        final CharSequence text = "abaaababa";
        final var comp = new CharacterComparator();
        matchPrint(pattern,text, comp);
    }

    public static void test_5() {
        System.out.println("-------------T4------------------");
        final CharSequence pattern = "aba";
        final CharSequence text = "ababababa";
        final var comp = new CharacterComparator();
        matchPrint(pattern,text, comp);
    }

    private static <T> void matchPrint(CharSequence pattern, CharSequence text, CharacterComparator comp) {
        System.out.println("Input Pattern: " + pattern);
        System.out.println("Input Text: " + text);

        var results = PatternMatching.boyerMoore(pattern,text,comp);
        System.out.println("Output: " + results);
        System.out.println("Table: " + PatternMatching.buildLastTable(pattern));
        System.out.println("Comparisons made: " + comp.getComparisonCount());
    }
}
