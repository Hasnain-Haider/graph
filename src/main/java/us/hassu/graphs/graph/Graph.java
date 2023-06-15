package us.hassu.graphs.graph;

import java.util.*;

public interface Graph {

    Set<? extends Node> getAdjacentNodes(Node node);
    List<? extends Node> getAdjacentNodesList(Node node);

    List<? extends Edge> getEdgesFrom(Node node);

    void print();
}
