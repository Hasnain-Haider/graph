package us.hassu.graphs.graph;

import us.hassu.graphs.graph.Node;

import java.util.ArrayList;
import java.util.List;

public class Grid <T> extends ArrayList<List<Node<T>>>{
    public Grid(int initialCapacity) {
        super(initialCapacity);
    }
}
