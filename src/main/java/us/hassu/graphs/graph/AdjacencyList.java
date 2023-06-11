package us.hassu.graphs.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdjacencyList extends HashMap<Node, List<Edge>> {
    public List<Edge> put(Node node, Edge edge) {
        List<Edge> edges = this.computeIfAbsent(node, k -> new ArrayList<>());
        edges.add(edge);
        return edges;
    }
    public List<Edge> put(Node from, Node to) {
        List<Edge> edges = this.computeIfAbsent(from, k -> new ArrayList<>());
        edges.add(new Edge(from, to));
        return edges;
    }
}
