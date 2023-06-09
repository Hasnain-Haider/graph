package us.hassu.graphs.graph.trace;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.Node;

import java.util.Set;
import java.util.StringJoiner;

@Getter @Setter
public class BfsFrame<T> {
    Node<T> node;
    Set<Node<T>> newlyDiscovered;

    public BfsFrame(Node<T> node, Set<Node<T>> queue) {
        this.node = node;
        this.newlyDiscovered = queue;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BfsFrame.class.getSimpleName() + "[", "]")
                .add("node=" + node)
                .add("newlyDiscovered=" + newlyDiscovered)
                .toString();
    }
}
