package us.hassu.graphs;

import us.hassu.graphs.graph.Node;
import us.hassu.graphs.graph.string.StringEdge;
import us.hassu.graphs.graph.string.StringGridGraph;
import us.hassu.graphs.graph.trace.BfsFrame;
import us.hassu.graphs.graph.trace.BfsTrace;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        t3();
        t4();
    }

    static void t3 () {
        StringGridGraph.Builder builder = new StringGridGraph.Builder();
        builder.width(3).height(4).createEdges(true).build();

        StringGridGraph gg = builder.height(4).width(3).createEdges(true).build();
        Node<String> start = gg.getGrid().get(0).get(0);
        Node<String> end = gg.getGrid().get(3).get(2);

        gg.print();
        List<Node<String>> path = gg.bfs(start, end);
        log("shortest path from " + start + " to " + end + " is:");
        log(path);
    }

    static void t4 () {
        StringGridGraph.Builder builder = new StringGridGraph.Builder();
        builder.width(3).height(4).createEdges(true).build();

        StringGridGraph gg = builder.height(4).width(3).createEdges(true).build();
        Node<String> start = gg.getGrid().get(0).get(0);
        Node<String> end = gg.getGrid().get(3).get(2);

        gg.print();

        gg.addEdge(new StringEdge(null, null));
        BfsTrace<String> frames = gg.bfsTrace(start, end);
        for (BfsFrame<String> frame: frames.getTrace()) {
            log(frame);
        }
        log("shortest path from " + start + " to " + end + " is:");
        log(frames.getPath());
    }

    static void log(Object o) {
        System.out.println(o);
    }
}