package us.hassu.graphs.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public abstract class Graph {

    @Setter
    @Getter
    AdjacencyList adjacencyList;

    public Graph() {
    }

    public Graph(AdjacencyList adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public List<Edge> getAdjacentNodes(Node node) {
        return adjacencyList.get(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : adjacencyList.keySet()) {
            sb.append("{").append(node).append(" -> ")
                    .append(adjacencyList.get(node)).append("}//");
        }
        return sb.toString();
    }

    public void print() {
        System.out.println("Edges:");
        for (Node node : adjacencyList.keySet()) {
            System.out.println(node + " -> " + adjacencyList.get(node));
        }
    }
}
