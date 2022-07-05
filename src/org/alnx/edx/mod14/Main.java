package org.alnx.edx.mod14;

import java.util.Set;

public class Main {
    private static final Vertex<String> A = new Vertex<>("A");
    private static final Vertex<String> B = new Vertex<>("B");
    private static final Vertex<String> C = new Vertex<>("C");
    private static final Vertex<String> D = new Vertex<>("D");
    private static final Vertex<String> E = new Vertex<>("E");
    private static final Vertex<String> F = new Vertex<>("F");

    public static void main(String[] args) {
        test_0();
        test_1();
        test_2();
        test_3();
        test_4();
        test_5();
        test_6();
    }

    /**
     * Trivial triangular Graph
     *       A
     *     0/  \2
     *   B  -1- C
     */
    public static void test_0() {
        final Graph<String> graph = new Graph<>(
                Set.of(A, B, C),
                Set.of(
                        new Edge<>(A, B, 0), new Edge<>(B, A, 0),
                        new Edge<>(B, C, 1), new Edge<>(C, B, 1),
                        new Edge<>(C, A, 2), new Edge<>(A, C, 2)
                )
        );
        searchPrint(A, graph);
        searchPrint(C, graph);
    }

    /**
     * Trivial LL Graph
     *       A -1- B -2- C -3- D -4- E
     */
    public static void test_1() {
        final Graph<String> graph = new Graph<>(
                Set.of(A, B, C, D, E),
                Set.of(
                        new Edge<>(A, B, 1), new Edge<>(B, A, 1),
                        new Edge<>(B, C, 2), new Edge<>(C, B, 2),
                        new Edge<>(C, D, 3), new Edge<>(D, C, 3),
                        new Edge<>(D, E, 4), new Edge<>(E, D, 4)
                )
        );
        searchPrint(A, graph);
        searchPrint(C, graph);
    }

    /**
     * Edge case 1: single node graph
     *       A
     */
    public static void test_2() {
        final Graph<String> graph = new Graph<>(
                Set.of(A),
                Set.of()
        );
        searchPrint(A, graph);
    }

    /**
     * Edge case 1: single node graph with self-loop
     *       A -0- A
     */
    public static void test_3() {
        final Graph<String> graph = new Graph<>(
                Set.of(A),
                Set.of(new Edge<>(A, A, 0))
        );
        searchPrint(A, graph);
    }

    /**
     * Complex graph
     *       A   D
     *     B   E
     *   C
     *
     */
    public static void test_4() {
        final Graph<String> graph = new Graph<>(
                Set.of(A, B, C, D, E),
                Set.of(
                        new Edge<>(A, B, 0), new Edge<>(B, A, 1),
                        new Edge<>(B, C, 1), new Edge<>(C, B, 1),
                        new Edge<>(A, E, 2), new Edge<>(E, A, 2),
                        new Edge<>(A, D, 3), new Edge<>(D, A, 3),
                        new Edge<>(D, E, 5), new Edge<>(E, D, 5)
                )
        );
        searchPrint(A, graph);
        searchPrint(C, graph);
    }

    /**
     * Different graph
     *       A   D
     *   B   C   E  F
     *
     */
    public static void test_5() {
        final Graph<String> graph = new Graph<>(
                Set.of(A, B, C, D, E, F),
                Set.of(
                        new Edge<>(A, B, 0), new Edge<>(B, A, 1),
                        new Edge<>(B, C, 1), new Edge<>(C, B, 1),
                        new Edge<>(A, C, 2), new Edge<>(C, A, 2),
                        new Edge<>(A, D, 5), new Edge<>(D, A, 5),
                        new Edge<>(C, E, 3), new Edge<>(E, C, 3),
                        new Edge<>(D, E, 2), new Edge<>(E, D, 2),
                        new Edge<>(D, F, 4), new Edge<>(F, D, 4),
                        new Edge<>(E, F, 1), new Edge<>(F, E, 1)
                )
        );
        searchPrint(A, graph);
        searchPrint(C, graph);
    }

    /**
     * Disconnected graph
     *       A   D       E
     *   B   C          F
     *
     */
    public static void test_6() {
        final Graph<String> graph = new Graph<>(
                Set.of(A, B, C, D, E, F),
                Set.of(
                        new Edge<>(A, B, 0), new Edge<>(B, A, 1),
                        new Edge<>(B, C, 1), new Edge<>(C, B, 1),
                        new Edge<>(A, C, 2), new Edge<>(C, A, 2),
                        new Edge<>(A, D, 5), new Edge<>(D, A, 5),
                        new Edge<>(E, F, 1), new Edge<>(F, E, 1)
                )
        );
        searchPrint(A, graph);
        searchPrint(E, graph);
    }

    private static <T> void searchPrint(Vertex<T> start, Graph<T> graph) {
        final var primsRes = GraphAlgorithms.prims(start, graph);

        System.out.println("-----------------------------------");
        System.out.println("Start at: " + start + ", Graph: " + graph.getAdjList());
        System.out.println("MST: " + primsRes);
    }
}
