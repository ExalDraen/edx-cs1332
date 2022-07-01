package org.alnx.edx.mod13;

import java.util.Set;

public class Main {

    private static final Vertex<String> A = new Vertex<>("A");
    private static final Vertex<String> B = new Vertex<>("B");
    private static final Vertex<String> C = new Vertex<>("C");
    private static final Vertex<String> D = new Vertex<>("D");
    private static final Vertex<String> E = new Vertex<>("E");


    public static void main(String[] args) {
        test_0();
        test_1();
        test_2();
        test_3();
        test_4();
//        test_5();
    }

    /**
     * Trivial triangular Graph
     *       A
     *     0/  \1
     *   B  -2- C
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
                        new Edge<>(A, B, 0), new Edge<>(B, A, 1),
                        new Edge<>(B, C, 0), new Edge<>(C, B, 1),
                        new Edge<>(C, D, 0), new Edge<>(D, C, 1),
                        new Edge<>(D, E, 1), new Edge<>(E, D, 1)
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
                        new Edge<>(B, C, 0), new Edge<>(C, B, 1),
                        new Edge<>(A, E, 0), new Edge<>(E, A, 1),
                        new Edge<>(D, E, 1), new Edge<>(E, D, 1)
                )
        );
        searchPrint(A, graph);
        searchPrint(C, graph);
    }

    private static <T> void searchPrint(Vertex<T> start, Graph<T> graph) {
        final var bfsRes = GraphAlgorithms.bfs(start, graph);
        final var dfsRes = GraphAlgorithms.dfs(start,graph);

        System.out.println("-----------------------------------");
        System.out.println("Start at: " + start + ", Graph: " + graph.toString());
        System.out.println("BFS: " + bfsRes);
        System.out.println("DFS: " + dfsRes);
    }
}
