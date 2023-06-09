package us.hassu.graphs.graph.string;

import us.hassu.graphs.graph.Edge;
import us.hassu.graphs.graph.Node;

public class StringEdge extends Edge<String> {
    public StringEdge(Node<String> from, Node<String> to) {
        super(from, to);
    }

    public StringEdge(Node<String> from, Node<String> to, int weight) {
        super(from, to, weight);
    }
}
