package us.hassu.graphs;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        t3();
    }

    static void t3 () {
        GridGraph.GridGraphBuilder builder = new GridGraph.GridGraphBuilder();
        GridGraph gg = builder.width(200).height(300).createEdges(true).build();
        Node<String> start = gg.getGrid().get(0).get(0);
        Node<String> end = gg.getGrid().get(199).get(299);

        List<Node<String>> path = gg.bfs(start, end);
        System.out.println("shortest path from " + start + " to " + end + " is:");
        System.out.println(path);
//        GridGraphGenerator gg = new GridGraphGenerator(2,3);
//        Graph<String> graph = gg.generate();
//        gg.print();
//        graph.print();
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
        System.out.println("test1 path: " + path);
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

        System.out.println("test2 graph: " + graph);
        List<Node<String>> path = graph.bfs(a, j);
        System.out.println("test2 bfs path: " + path);
        path = graph.dfs(a, j);
        System.out.println("test2 dfs path: " + path);
    }
}