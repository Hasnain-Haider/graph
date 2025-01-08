package us.hassu.graphs.graph;

import lombok.Data;
import lombok.NonNull;

@Data
public class Node {
    private String id;

    public Node(Node n) {
        this(n.getId());
    }

    public Node(@NonNull String id) {
        this.id = id;
    }
}
