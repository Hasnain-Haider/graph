package us.hassu.graphs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter @Getter
public class BfsTrace<T> {
    List<Node<T>> path = new ArrayList<>();

    List<BfsFrame<T>> trace = new ArrayList<>();
}
