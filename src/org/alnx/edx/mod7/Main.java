package org.alnx.edx.mod7;

import org.alnx.edx.mod6.MinHeap;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        test_0();
        test_1();
        test_1_5();
        test_2();
        test_3();
        test_3_5();
    }

    public static void test_0() {
        System.out.println("-------------T0------------------");
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        printMap(map);
    }
    public static void test_1() {
        System.out.println("--------------T1-----------------");
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        printMap(map);
        map.put(0, 10);
        map.put(1, 11);
        printMap(map);
        map.put(1, 110);
        map.put(0, 100);
        printMap(map);
    }

    public static void test_1_5() {
        System.out.println("--------------T1.5-----------------");
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        printMap(map);
        map.put(0, 10);
        map.put(1, 11);
        printMap(map);
        map.put(15, 110);
        map.put(27, 1100);
        map.put(13, 100);
        printMap(map);
    }

    public static void test_2() {
        System.out.println("--------------T2-----------------");
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        printMap(map);
        map.put(0, 10);
        map.put(15, 11);
        map.put(25, 12);
        map.put(3, 13);
        map.put(4, 14);
        map.put(5, 15);
        map.put(6, 16);
        map.put(70, 17);
        map.put(88, 17);
        map.put(90, 17);
        map.put(100, 17);
        printMap(map);
    }

    public static void test_3() {
        System.out.println("---------------T3----------------");
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        printMap(map);
        map.put(0, 10);
        map.put(1, 11);
        printMap(map);
        System.out.println("Removing 1, got" + map.remove(1));
        map.put(2, 20);
        System.out.println("Removing 2, got" + map.remove(2));
        printMap(map);
    }

    public static void test_3_5() {
        System.out.println("---------------T3----------------");
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        printMap(map);
        map.put(0, 10);
        map.put(1, 11);
        map.put(14, 110);
        map.put(27, 1100);
        map.put(40, 11000);
        map.put(13, 100);
        map.put(26, 1000);
        printMap(map);
        System.out.println("Removing 1, got" + map.remove(1));
        System.out.println("Removing 14, got" + map.remove(14));
        System.out.println("Removing 26, got" + map.remove(26));
        printMap(map);
    }

    public static <K,V> void printMap(ExternalChainingHashMap<K, V> map) {
        String repr = String.format("-- Map of size: %s, capacity: %s:\n%s\n", map.size(), map.getTable().length, printTable(map.getTable()));
        System.out.println(repr);
    }

    public static <K,V> String printTable(ExternalChainingMapEntry<K,V>[] table) {
        return Arrays.stream(table).map(Main::printEntry).toList().toString();
    }

    public static <K, V> String printEntry(ExternalChainingMapEntry<K,V> entry) {
        StringBuilder ret = new StringBuilder("");
        if (entry == null) {
            ret.append("null");
        } else {
            while (entry != null) {
                ret.append(entry.toString());
                if (entry.getNext() != null) {
                    ret.append("--");
                }
                entry = entry.getNext();
            }
        }
        return ret.toString();
    }
}

