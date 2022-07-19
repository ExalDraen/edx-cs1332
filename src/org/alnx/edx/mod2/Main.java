package org.alnx.edx.mod2;

public class Main {

    public static void main(String[] args) {
        test_0();
        test_0_1();
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
        test_add_at_index();
    }

    private static void test_add_at_index() {
        System.out.println("-----------------------------------");
        var arr = new SinglyLinkedList<String>();
        arr.addToBack("1");
        arr.addToBack("2");
        arr.addAtIndex(2, "30");
        printLinkedList(arr);
    }

    private static void test_5() {
        System.out.println("-----------------------------------");
        var arr = new SinglyLinkedList<String>();
        arr.addToBack("1");
        arr.addToBack("2");
        arr.addToBack("3");
        arr.addToBack("4");
        arr.addToBack("5");
        arr.addToBack("6");
        System.out.printf("Removed: %s\n", arr.removeFromBack());
        printLinkedList(arr);
    }

    private static void test_4() {
        System.out.println("-----------------------------------");
        var arr = new SinglyLinkedList<String>();
        arr.addToBack("1");
        arr.addToBack("2");
        arr.addToBack("3");
        arr.addToBack("4");
        arr.addToBack("5");
        arr.addToBack("6");
        arr.addToBack("7");
        arr.addToBack("8");
        arr.addToBack("9");
        printLinkedList(arr);    }

    private static void test_3() {
        System.out.println("-----------------------------------");
        var arr = new SinglyLinkedList<String>();
        arr.addToFront("Unique");
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        printLinkedList(arr);    }

    private static void test_2() {
        System.out.println("-----------------------------------");
        var arr = new SinglyLinkedList<String>();
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
        printLinkedList(arr);    }

    private static void test_1() {
        System.out.println("-----------------------------------");
        var arr = new SinglyLinkedList<String>();
        arr.addToBack("My");
        arr.addToBack("Little");
        arr.addToBack("Pony");
        arr.addToBack(",");
        arr.addToBack("Friendship");
        printLinkedList(arr);
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        System.out.printf("Removed: %s\n", arr.removeFromFront());
        printLinkedList(arr);
    }

    private static void test_0_1() {
        System.out.println("-----------------------------------");
        var arr = new SinglyLinkedList<Integer>();
        arr.addToFront(1);
        printLinkedList(arr);
    }

    private static void test_0() {
        System.out.println("-----------------------------------");
        var arr = new SinglyLinkedList<Integer>();
        arr.addToFront(1);
        arr.addToFront(2);
        arr.addToFront(3);
        arr.addToFront(4);
        printLinkedList(arr);
    }

    private static <T> void printLinkedList(SinglyLinkedList<T> list) {
        var curr = list.getHead();

        var out = new StringBuilder();
        out.append("[ ");
        while (curr != null) {
            out.append(curr.getData().toString());
            out.append(", ");
            curr = curr.getNext();
        }
        out.append(" ]");
        out.append(" (size: ").append(list.size()).append(")");
        out.append(" (head: ").append(list.getHead()).append(")");
        out.append(" (tail: ").append(list.getTail()).append(")");
        System.out.println(out);
    }
}
