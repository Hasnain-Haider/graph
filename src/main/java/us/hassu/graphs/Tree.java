package us.hassu.graphs;

import lombok.Data;
import us.hassu.graphs.graph.AbstractGraph;
import us.hassu.graphs.graph.Edge;
import us.hassu.graphs.graph.Node;

import java.util.HashMap;
import java.util.List;

@Data
public class Tree extends AbstractGraph {
    Node root;

    public Tree(Node root, HashMap<Node, List<Edge>> edges) {
        super(edges);
        this.root = root;

        GraphUtils gu = GraphUtils.getInstance();
        if (gu.hasCycle(this)) {
            throw new IllegalArgumentException("Tree cannot have cycles");
        }
    }
}
