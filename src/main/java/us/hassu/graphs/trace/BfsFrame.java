package us.hassu.graphs.trace;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import us.hassu.graphs.graph.Node;

import java.util.Set;

@Getter
@Setter
@ToString
public class BfsFrame <T extends Node> {
    T node;
    Set<T> newlyDiscovered;

    public BfsFrame(T node, Set<T> queue) {
        this.node = node;
        this.newlyDiscovered = queue;
    }
}
