package us.hassu.graphs.graph.string;

import us.hassu.graphs.graph.AdjacencyList;
import us.hassu.graphs.graph.Edge;
import us.hassu.graphs.graph.Node;

import java.util.List;

public class StringAdjacencyList extends AdjacencyList<String, StringEdge> {
    @Override
    public List<StringEdge> put(Node<String> node, StringEdge edge) {
        return super.put(node, edge);
    }
}
