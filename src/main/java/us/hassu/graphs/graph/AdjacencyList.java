package us.hassu.graphs.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjacencyList<T, N extends Node<T>, E extends Edge<T>> extends HashMap<N, List<E>> {
    public List<E> put(N node, E edge) {
        List<E> edges = this.computeIfAbsent(node, k -> new ArrayList<>());
        edges.add(edge);
        return edges;
    }
}
