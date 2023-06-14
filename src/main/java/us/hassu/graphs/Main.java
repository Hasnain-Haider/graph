package us.hassu.graphs;

import us.hassu.graphs.graph.AdjacencyList;
import us.hassu.graphs.graph.Node;
import us.hassu.graphs.maze.Maze;

public class Main {
    public static void main(String[] args) {
        Maze.Builder builder = new Maze.Builder().height(5).width(5);
        Maze maze = builder.build();
        maze.print();


        AdjacencyList adjacencyList = new AdjacencyList();
        Node n = new Node("n");
        Node m = new Node("m");
        adjacencyList.put(n, m);
        Tree tree = new Tree(n, adjacencyList);
        tree.print();

    }
}