package us.hassu.graphs;

public class Edge<T> {
    int weight;

    private Node<T> from;
    private Node<T> to;

    public Edge(Node<T> from, Node<T> to) {
        this(from, to, 1);
    }

    public Edge(Node<T> from, Node<T> to, int weight) {
        this.from= from;
        this.to = to;
        this.weight = weight;
    }
}
