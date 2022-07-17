package org.alnx.edx.mod1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        test_0();
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
    }

    private static void test_5() {
        var arr = new ArrayList<String>();
        arr.addToBack("1");
        arr.addToBack("2");
        arr.addToBack("3");
        arr.addToBack("4");
        arr.addToBack("5");
        arr.addToBack("6");
        System.out.printf("Removed: %s\n", arr.removeFromBack());
        System.out.println(Arrays.toString(arr.getBackingArray()));
    }

    private static void test_4() {
        var arr = new ArrayList<String>();
        arr.addToBack("1");
        arr.addToBack("2");
        arr.addToBack("3");
        arr.addToBack("4");
        arr.addToBack("5");
        arr.addToBack("6");
        arr.addToBack("7");
        arr.addToBack("8");
        arr.addToBack("9");
        System.out.println(Arrays.toString(arr.getBackingArray()));
    }

    private static void test_3() {
        var arr = new ArrayList<String>();
        arr.addToFront("Unique");
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.println(Arrays.toString(arr.getBackingArray()));
    }

    private static void test_2() {
        var arr = new ArrayList<String>();
        arr.addToBack("There");
        arr.addToBack("Was");
        arr.addToBack("Once");
        arr.addToBack("A");
        arr.addToBack("Lady");
        arr.addToBack("From");
        arr.addToBack("Riga");
        arr.addToBack(",");
        arr.addToBack("Who");
        arr.addToBack("Liked");
        arr.addToBack("To");
        arr.addToBack("Ride");
        arr.addToBack("Out");
        arr.addToBack("On");
        arr.addToBack("A");
        System.out.println(Arrays.toString(arr.getBackingArray()));
    }

    private static void test_1() {
        var arr = new ArrayList<String>();
        arr.addToBack("My");
        arr.addToBack("Little");
        arr.addToBack("Pony");
        arr.addToBack(",");
        arr.addToBack("Friendship");
        System.out.println(Arrays.toString(arr.getBackingArray()));
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.printf("Removed: %s\n", arr.removeFromFront());
    }

    private static void test_0() {
        var arr = new ArrayList<Integer>();
        arr.addToFront(1);
        arr.addToFront(2);
        arr.addToFront(3);
        arr.addToFront(4);
        System.out.println(Arrays.toString(arr.getBackingArray()));
    }
}
