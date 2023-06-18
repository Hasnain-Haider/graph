package us.hassu.graphs.maze;

import java.util.ArrayList;
import java.util.List;

public class Grid extends ArrayList<List<MazeNode>> {
    public Grid(int initialCapacity) {
        super(initialCapacity);
    }
}
