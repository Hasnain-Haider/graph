package us.hassu.graphs.graph;

import lombok.Data;
import lombok.NonNull;

@Data
public class Node {
    private String id;

    private Node() {
    }

    public Node(Node n) {
        this.id = n.id;
    }

    public Node(@NonNull() String id) {
        this.id = id;
    }
}
