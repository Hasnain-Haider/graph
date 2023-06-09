package us.hassu.graphs;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        t4();
    }

    static void t3 () {
        GridGraph.GridGraphBuilder builder = new GridGraph.GridGraphBuilder();
        GridGraph gg = builder.width(3).height(4).createEdges(true).build();
        Node<String> start = gg.getGrid().get(0).get(0);
        Node<String> end = gg.getGrid().get(2).get(3);

        gg.print();
        List<Node<String>> path = gg.bfs(start, end);
        log("shortest path from " + start + " to " + end + " is:");
        log(path);
    }

    static void t4 () {
        GridGraph.GridGraphBuilder builder = new GridGraph.GridGraphBuilder();
        GridGraph gg = builder.width(3).height(4).createEdges(true).build();
        Node<String> start = gg.getGrid().get(0).get(0);
        Node<String> end = gg.getGrid().get(2).get(3);

        gg.print();
        BfsTrace<String> frames = gg.bfsTrace(start, end);
        for (BfsFrame<String> frame: frames.getTrace()) {
            log(frame);
        }
        log("shortest path from " + start + " to " + end + " is:");
        log(frames.getPath());
    }

    static void test1() {
        Graph<String> graph = new Graph<>(true);
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");

        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(b, a);
        graph.addEdge(c, c);
        graph.addEdge(c, d);
        graph.addEdge(d, e);

        List<Node<String>> path = graph.bfs(a, e);
        log("test1 path: " + path);
    }

    static void test2() {
        Graph<String> graph = new Graph<>(true);
        Node<String> a = new Node<>("a");
        Node<String> b = new Node<>("b");
        Node<String> c = new Node<>("c");
        Node<String> d = new Node<>("d");
        Node<String> e = new Node<>("e");
        Node<String> f = new Node<>("f");
        Node<String> g = new Node<>("g");
        Node<String> h = new Node<>("h");
        Node<String> i = new Node<>("i");
        Node<String> j = new Node<>("j");
        Node<String> z = new Node<>("z");

        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(a, z);
        graph.addEdge(a, d);
        graph.addEdge(b, c);
        graph.addEdge(b, e);
        graph.addEdge(c, d);
        graph.addEdge(c, e);
        graph.addEdge(d, f);
        graph.addEdge(e, g);
        graph.addEdge(f, g);
        graph.addEdge(f, h);
        graph.addEdge(h, i);
        graph.addEdge(i, j);
        graph.addEdge(g, a);

        log("test2 graph: " + graph);
        List<Node<String>> path = graph.bfs(a, j);
        log("test2 bfs path: " + path);
        path = graph.dfs(a, j);
        log("test2 dfs path: " + path);
    }

    static void log(Object o) {
        System.out.println(o);
    }
}