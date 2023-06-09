//package us.hassu.graphs.controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import us.hassu.graphs.BfsFrame;
//import us.hassu.graphs.BfsTrace;
//import us.hassu.graphs.graph.Node;
//import us.hassu.graphs.graph.string.StringGridGraph;
//
//@RestController
//public class Controller {
//
//    // TODO implement a REST API that returns a BfsTrace
//    @GetMapping("/test")
//    public BfsTrace<String> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//
//        StringGridGraph.Builder builder = new StringGridGraph.Builder();
//        builder.width(3).height(4).createEdges(true).build();
//
//        StringGridGraph gg = builder.width(3).height(4).createEdges(true).build();
//        Node<String> start = gg.getGrid().get(0).get(0);
//        Node<String> end = gg.getGrid().get(2).get(3);
//
////        gg.print();
//        BfsTrace<String> frames = gg.bfsTrace(start, end);
//        for (BfsFrame<String> frame: frames.getTrace()) {
//            System.out.println(frame);
//        }
//        System.out.println("shortest path from " + start + " to " + end + " is:");
//        return frames;
//    }
//}
