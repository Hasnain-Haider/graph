package us.hassu.graphs.maze;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.Node;

import java.util.EnumSet;
import java.util.Set;
import java.util.StringJoiner;

public class MazeNode extends Node {
    @Getter @Setter
    Set<Boundary> boundaries;

    public MazeNode(String id) {
        super(id);
        this.boundaries = EnumSet.allOf(Boundary.class);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MazeNode.class.getSimpleName() + "[", "]")
                .add("boundaries=" + boundaries)
                .add("id='" + this.getId() + "'")
                .toString();
    }

    public void removeBoundary(Boundary boundary) {
        boundaries.remove(boundary);
    }

    public void addBoundary(Boundary boundary) {
        boundaries.add(boundary);
    }

    public enum Boundary { BOTTOM, RIGHT }
}
