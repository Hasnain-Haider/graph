package us.hassu.graphs;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class BfsFrame<T> {
    Node<T> node;
    Set<Node<T>> newlyQueued;

    public BfsFrame(Node<T> node, Set<Node<T>> queue) {
        this.node = node;
        this.newlyQueued = queue;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("node", node)
                .add("newlyQueued", newlyQueued)
                .toString();
    }
}
