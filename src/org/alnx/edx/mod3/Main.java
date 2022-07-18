package org.alnx.edx.mod3;

import org.alnx.edx.mod1.ArrayList;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        enqueueSingle();
        enqueueMany();
        enqueueDequeue();
        enqueueDequeueInterleave();
        enqueueDequeueWithResize();
    }

    private static void enqueueDequeueWithResize() {
        var arr = new ArrayQueue<String>();
        arr.enqueue("0a");
        arr.enqueue("1a");
        arr.enqueue("2a");
        arr.enqueue("3a");
        arr.enqueue("4a");
        arr.enqueue("5a");
        arr.enqueue("6a");
        arr.enqueue("7a");
        arr.enqueue("8a");
        printQueue(arr);
        arr.dequeue();
        arr.dequeue();
        arr.enqueue("9a");
        arr.enqueue("10a");
        arr.enqueue("11a");
        printQueue(arr);
    }

    private static void enqueueDequeueInterleave() {
        var arr = new ArrayQueue<String>();
        arr.enqueue("a");
        arr.enqueue("kana");
        arr.enqueue("syllabary");
        arr.enqueue("think");
        arr.enqueue("now");
        arr.enqueue("how");
        arr.enqueue("much");
        arr.enqueue("you");
        arr.enqueue("really");
        arr.enqueue("want");
        printQueue(arr);
        arr.dequeue();
        arr.dequeue();
        arr.enqueue("kanji");
        arr.enqueue("the");
        printQueue(arr);
    }

    private static void enqueueDequeue() {
        var arr = new ArrayQueue<String>();
        arr.enqueue("a");
        arr.enqueue("kana");
        arr.enqueue("syllabary");
        printQueue(arr);
        System.out.printf("Dequeued: %s, %s, %s\n", arr.dequeue(), arr.dequeue(), arr.dequeue());
        printQueue(arr);
    }

    private static void enqueueMany() {
        var arr = new ArrayQueue<String>();
        arr.enqueue("a");
        arr.enqueue("kana");
        arr.enqueue("syllabary");
        arr.enqueue("think");
        arr.enqueue("now");
        arr.enqueue("how");
        arr.enqueue("much");
        arr.enqueue("you");
        arr.enqueue("really");
        printQueue(arr);
        arr.enqueue("want");
        printQueue(arr);
    }

    private static void enqueueSingle() {
        var arr = new ArrayQueue<Integer>();
        arr.enqueue(10);
        printQueue(arr);
    }

    private static <T> void printQueue(ArrayQueue<T> q) {
        System.out.printf("%s (size:%s)\n", Arrays.toString(q.getBackingArray()), q.size());
    }




}
