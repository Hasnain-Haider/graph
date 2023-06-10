package us.hassu.graphs.graph.maze;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.Node;

import java.util.Set;

public class MazeNode extends Node<String> {
    @Getter @Setter
    Set<Boundary> boundaries;

    public MazeNode(String id) {
        super(id);
    }

    public void removeBoundary(Boundary boundary) {
        boundaries.remove(boundary);
    }

    public void addBoundary(Boundary boundary) {
        boundaries.add(boundary);
    }

    public enum Boundary {
        BOTTOM("b"),
        RIGHT("r");
        final String name;
        Boundary(String name) {
            this.name = name;
        }
    }
}
