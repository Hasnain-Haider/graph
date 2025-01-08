package us.hassu.graphs.graph;

import java.util.List;
import java.util.Set;

public interface Graph<T extends Node> {
    Set<? extends T> getAdjacentNodesSet(T node);

    List<? extends T> getAdjacentNodes(T node);

    List<? extends Edge> getEdgesFrom(T node);

    void print();
}
