package us.hassu.graphs.graph.string;

import us.hassu.graphs.graph.Graph;
import us.hassu.graphs.graph.Node;

public class StringGraph extends Graph<String, Node<String>, StringEdge, StringAdjacencyList> {
    public StringGraph(StringAdjacencyList edges) {
        super(edges);
    }
}
