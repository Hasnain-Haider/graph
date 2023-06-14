package us.hassu.graphs;

import lombok.Data;
import us.hassu.graphs.graph.AdjacencyList;
import us.hassu.graphs.graph.Graph;
import us.hassu.graphs.graph.Node;

@Data
public class Tree extends Graph {
    Node root;

    public Tree(Node root, AdjacencyList edges) {
        super(edges);
        this.root = root;

        GraphUtils gu = GraphUtils.getInstance();
        if (gu.hasCycle(this)) {
            throw new IllegalArgumentException("Tree cannot have cycles");
        }
    }
}
