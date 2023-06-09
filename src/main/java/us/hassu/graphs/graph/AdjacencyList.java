package us.hassu.graphs.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjacencyList<T, E extends Edge<T>> extends HashMap<Node<T>, List<E>> {

    public List<E> put(Node<T> node, E edge) {
        List<E> edges = this.computeIfAbsent(node, k -> new ArrayList<>());
        edges.add(edge);
        return edges;
    }
}
