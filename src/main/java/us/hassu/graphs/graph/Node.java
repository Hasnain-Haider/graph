package us.hassu.graphs.graph;

import lombok.Data;

import java.util.Objects;

@Data
public class Node {
    private String id;

    Node() {
    }

    public Node(String id) {
        this.id = Objects.requireNonNull(id, "Node id cannot be null");
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
