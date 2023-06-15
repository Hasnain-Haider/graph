package us.hassu.graphs.graph;

import lombok.Data;

@Data
public class Edge {
    int weight;
    private Node from;
    private Node to;

    public Edge(Node from, Node to) {
        this(from, to, 1);
    }

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
