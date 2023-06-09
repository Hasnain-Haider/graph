package us.hassu.graphs.graph.string;

import us.hassu.graphs.graph.Edge;
import us.hassu.graphs.graph.Graph;

public class StringGraph extends Graph<String, StringEdge, StringAdjacencyList> {
    public StringGraph(StringAdjacencyList edges) {
        super(edges);
    }
}
