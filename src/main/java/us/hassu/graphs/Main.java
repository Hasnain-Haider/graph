package us.hassu.graphs;

import us.hassu.graphs.graph.Edge;
import us.hassu.graphs.graph.Node;
import us.hassu.graphs.maze.Maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Maze.Builder builder = new Maze.Builder().height(3).width(3);
        Maze maze = builder.build();
        maze.printGrid();
    }
}