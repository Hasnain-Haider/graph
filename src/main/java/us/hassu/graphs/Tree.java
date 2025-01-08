package us.hassu.graphs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import us.hassu.graphs.graph.AbstractGraph;
import us.hassu.graphs.graph.Edge;
import us.hassu.graphs.graph.Node;

import java.util.HashMap;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Tree <T extends Node> extends AbstractGraph<T> {
    T root;

    public Tree(T root, HashMap<T, List<Edge<T>>> edges) {
        super(edges);
        this.root = root;

        GraphUtils<T> gu = GraphUtils.getInstance();
        if (gu.hasCycle(this)) {
            throw new IllegalArgumentException("Tree cannot have cycles");
        }
    }
}
