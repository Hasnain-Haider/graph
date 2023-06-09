package us.hassu.graphs;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.Node;

import java.util.Set;

@Getter @Setter
public class BfsFrame<T> {
    Node<T> node;
    Set<Node<T>> newlyDiscovered;

    public BfsFrame(Node<T> node, Set<Node<T>> queue) {
        this.node = node;
        this.newlyDiscovered = queue;
    }

//    @Override
//    public String toString() {
//        return MoreObjects.toStringHelper(this)
//                .add("node", node)
//                .add("newlyQueued", newlyDiscovered)
//                .toString();
//    }
}
