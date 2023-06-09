package us.hassu.graphs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Node<T> {
    @JsonProperty("id")
    private T data;

    public Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equal(data, node.data);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(data);
    }
}
