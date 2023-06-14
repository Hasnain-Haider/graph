package us.hassu.graphs.graph;

import us.hassu.graphs.maze.MazeNode;

import java.util.ArrayList;
import java.util.List;

public class Grid extends ArrayList<List<MazeNode>> {
    public Grid(int initialCapacity) {
        super(initialCapacity);
    }
}
