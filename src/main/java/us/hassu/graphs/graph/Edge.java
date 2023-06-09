package us.hassu.graphs.graph;


import lombok.Getter;

import java.util.Objects;
import java.util.StringJoiner;

@Getter
public class Edge<T> {
    int weight;
//    @JsonIgnore
    private Node<T> from;
    private Node<T> to;

    public Edge(Node<T> from, Node<T> to) {
        this(from, to, 1);
    }

    public Edge(Node<T> from, Node<T> to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

//    @Override
//    public String toString() {
//        return MoreObjects.toStringHelper(this)
//                .add("weight", weight)
//                .add("to", to)
//                .toString();
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Edge<?> edge = (Edge<?>) o;
//        return weight == edge.weight && Objects.equal(from, edge.from) && Objects.equal(to, edge.to);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(weight, from, to);
//    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Edge.class.getSimpleName() + "[", "]")
                .add("weight=" + weight)
                .add("to=" + to)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return weight == edge.weight && Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, from, to);
    }
}
