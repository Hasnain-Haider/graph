package us.hassu.graphs.graph;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.*;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
public abstract class AbstractGraph<T extends Node> extends HashMap<T, List<Edge<T>>> implements Graph<T> {
    public AbstractGraph(@NonNull HashMap<T, List<Edge<T>>> adjacencyList) {
        super(adjacencyList);
    }

    AbstractGraph() {
    }

    public List<Edge<T>> put(T node, Edge<T> edge) {
        List<Edge<T>> edges = this.computeIfAbsent(node, k -> new ArrayList<>());
        edges.add(edge);
        return edges;
    }

    public List<Edge<T>> put(T from, T to) {
        List<Edge<T>> edges = this.computeIfAbsent(from, k -> new ArrayList<>());
        edges.add(new Edge<T>(from, to));
        return edges;
    }

    @Override
    public List<T> getAdjacentNodes(T node) {
        return Optional.ofNullable(this.get(node)).orElse(Collections.emptyList())
                .stream().map(edge -> (T) edge.getTo()).collect(Collectors.toList());
    }

    @Override
    public Set<T> getAdjacentNodesSet(T node) {
        return Optional.ofNullable(this.get(node))
                .orElse(Collections.emptyList())
                .stream()
                .map(edge -> (T) edge.getTo())
                .collect(Collectors.toSet());
    }

    @Override
    public List<Edge<T>> getEdgesFrom(T node) {
        return this.get(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T node : this.keySet()) {
            sb.append("{").append(node).append(" -> ")
                    .append(this.get(node)).append("}//");
        }
        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println("Edges:");
        for (T node : this.keySet()) {
            System.out.println(node + " -> " + this.get(node));
        }
    }
}
