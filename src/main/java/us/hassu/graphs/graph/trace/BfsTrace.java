package us.hassu.graphs.graph.trace;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.Node;

import java.util.ArrayList;
import java.util.List;


// Shows which nodes bfs visited and in which order
@Setter @Getter
public class BfsTrace<T> {
    List<Node<T>> path = new ArrayList<>();

    List<BfsFrame<T>> trace = new ArrayList<>();
}
