package us.hassu.graphs.graph;

import java.util.List;
import java.util.Set;

public interface Graph {

    Set<? extends Node> getAdjacentNodesSet(Node node);

    List<? extends Node> getAdjacentNodes(Node node);

    List<? extends Edge> getEdgesFrom(Node node);

    void print();
}
