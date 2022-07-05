package org.alnx.edx.mod14;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Your implementation of Prim's algorithm.
 */
public class GraphAlgorithms {

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * You should NOT allow self-loops or parallel edges in the MST.
     *
     * You may import/use java.util.PriorityQueue, java.util.Set, and any
     * class that implements the aforementioned interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * The only instance of java.util.Map that you may use is the adjacency
     * list from graph. DO NOT create new instances of Map for this method
     * (storing the adjacency list in a variable is fine).
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {
        final Set<Vertex<T>> visited = new HashSet<>();
        final Set<Edge<T>> mst = new HashSet<>();
        final PriorityQueue<Edge<T>> pq = new PriorityQueue<>();

        // initialize: add start vertex and its edges
        HashSet<Edge<T>> edges = getEdges(start, graph);
        visited.add(start);
        pq.addAll(edges);

        Edge<T> curEdge;
        Vertex<T> curTarget;
        while( !pq.isEmpty() && (visited.size() != graph.getVertices().size())) {
            // dequeue edge and the vertex it points to
            curEdge = pq.poll();
            curTarget = curEdge.getV();
            // if we've already seen the target vertex, nothing to do
            if (!visited.contains(curTarget)) {
                // add both forward and reverse edge so that mst forms and *un*directed graph
                mst.add(curEdge);
                mst.add(new Edge<T>(curEdge.getV(), curEdge.getU(), curEdge.getWeight()));
                visited.add(curTarget);
                // otherwise add all edges that are interesting
                // i.e. ones that point to vertices we haven't seen
                for(Edge<T> w: getEdges(curTarget, graph)) {
                    if(!visited.contains(w.getV())) {
                        pq.add(w);
                    }
                }
            }
        }
        // check if we built a full MST or not (i.e. if graph connected)
        // if not then the 'mst' we built is not an actual MST, so return null
        if (visited.size() != graph.getVertices().size()){
            return null;
        }
        return mst;
    }

    /**
     * Return the set of edges incident on vertex start in the given graph
     *
     * @param start vertex to which edges are incident
     * @param graph graph containing the vertex
     * @param <T> data type stored in the graph vertices
     * @return set of all incident edges
     */
    private static <T> HashSet<Edge<T>> getEdges(Vertex<T> start, Graph<T> graph) {
        var neighbours = graph.getAdjList().get(start);
        var edges = new HashSet<Edge<T>>();
        for(var n: neighbours) {
            edges.add(new Edge<T>(start, n.getVertex(), n.getDistance()));
        }
        return edges;
    }
}
