package us.hassu.graphs.graph;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
public abstract class AbstractGraph extends HashMap<Node, List<Edge>> implements Graph {
    public AbstractGraph(@NonNull HashMap<Node, List<Edge>> adjacencyList) {
        super(adjacencyList);
    }

    AbstractGraph() {
    }

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

    @Override
    public List<? extends Node> getAdjacentNodes(Node node) {
        return Optional.ofNullable(this.get(node)).orElse(Collections.emptyList())
                .stream().map(Edge::getTo).collect(Collectors.toList());
    }

    @Override
    public Set<? extends Node> getAdjacentNodesSet(Node node) {
        return Optional.ofNullable(this.get(node))
                .orElse(Collections.emptyList())
                .stream()
                .map(Edge::getTo)
                .collect(Collectors.toSet());
    }

    @Override
    public List<Edge> getEdgesFrom(Node node) {
        return this.get(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : this.keySet()) {
            sb.append("{").append(node).append(" -> ")
                    .append(this.get(node)).append("}//");
        }
        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println("Edges:");
        for (Node node : this.keySet()) {
            System.out.println(node + " -> " + this.get(node));
        }
    }
}
