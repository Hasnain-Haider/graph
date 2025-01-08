package us.hassu.graphs.graph;

import lombok.Data;

@Data
public class Edge <T extends Node> {
    int weight;
    private T from;
    private T to;

    public Edge(T from, T to) {
        this(from, to, 1);
    }

    public Edge(T from, T to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
