package us.hassu.graphs.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter
public class Node<T> {
    private T id;

    public Node(T id) {
        this.id = Objects.requireNonNull(id, "Node id cannot be null");
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return id.equals(node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
