package us.hassu.graphs;

import us.hassu.graphs.graph.Node;
import us.hassu.graphs.maze.Maze;
import us.hassu.graphs.trace.BfsFrame;
import us.hassu.graphs.trace.BfsTrace;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        t5();
    }
    static void t5 () {
        Maze.Builder builder = new Maze.Builder();
        Maze gg = builder.width(4).height(5).createEdges(true).build();
//        Node start = gg.getGrid().get(0).get(0);
//        Node end = gg.getGrid().get(3).get(2);

//        gg.printGrid();
        gg.print();
//        List<Node> path = gg.bfs(start, end);
//        log("shortest path from " + start + " to " + end + " is:");
//        log(path);
//
//
//        BfsTrace frames = gg.bfsTrace(start, end);
//        log("start frames");
//        for (BfsFrame frame: frames.getTrace()) {
//            log(frame);
//        }
//        log("end frames");
    }
    static void log(Object o) {
        System.out.println(o);
    }
}