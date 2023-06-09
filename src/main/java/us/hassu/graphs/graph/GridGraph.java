//package us.hassu.graphs.graph;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter @Setter
//public class GridGraph extends Graph<String> {
//    Grid<String> grid;
//    int width;
//    int height;
//
//
//    public GridGraph(AdjacencyList<String, Edge<String>> edges, Grid<String> grid, int width, int height) {
//        super(edges);
//        this.grid = grid;
//        this.width = width;
//        this.height = height;
//    }
//
//    @Override
//    public void print() {
//        System.out.println("Grid:");
//        for (List<Node<String>> row : grid) {
//            System.out.println(row);
//        }
//        super.print();
//    }
//
//    public static class GridGraphBuilder {
//        Integer width;
//
//        Integer height;
//
//        boolean createEdges = false;
//
//        private int DEFAULT_WIDTH = 2;
//
//        private int DEFAULT_HEIGHT = 2;
//
//        // builder methods for setting property
//        public GridGraphBuilder width(int width) {
//            this.width = width;
//            return this;
//        }
//
//        public GridGraphBuilder height(int height) {
//            this.height = height;
//            return this;
//        }
//
//        public GridGraphBuilder createEdges(boolean createEdges) {
//            this.createEdges = createEdges;
//            return this;
//        }
//
//
//        // build method to deal with outer class
//        // to return outer instance
//        public GridGraph build() {
//            int width1 = width != null ? width : DEFAULT_WIDTH;
//            int height1 = height != null ? height : DEFAULT_HEIGHT;
//            Grid<String> nodes = createNodesGrid(width1, height1);
//            AdjacencyList<String, Edge<String>> edges;
//            if (createEdges) {
//                edges = createEdges(nodes, width, height);
//            } else {
//                edges = new AdjacencyList<>();
//            }
//            return new GridGraph(edges, nodes, width1, height1);
//        }
//
//        /*
//            || y  - >  +
//            x
//            |
//            v
//            +
//
//            (x,y)     (x, y+1)
//            (x+1,y)  (x+1,y+1)
//
//            i = x = row = limited by height
//            j = y = col = limited by width
//         */
//        private Grid<String> createNodesGrid(int width, int height) {
////            List<List<Node<String>>> grid = new ArrayList<>(width);
//            Grid<String> grid = new Grid<>(width);
//            for (int i = 0; i < height; i++) {
//                List<Node<String>> col = new ArrayList<>();
//                grid.add(col);
//                for (int j = 0; j < width; j++) {
//                    // create row
//                    col.add(new Node<>(i + "," + j));
//                }
//            }
//
//            return grid;
//        }
//
//        private AdjacencyList<String, Edge<String>> createEdges(Grid<String> nodes, int width, int height) {
//            AdjacencyList<String, Edge<String>> adjacencyList = new AdjacencyList<>();
//            for (int i = 0; i < height; i++) {
//                List<Node<String>> col = nodes.get(i);
//                for (int j = 0; j < width; j++) {
//                    Node<String> node = col.get(j);
//                    addEdges(adjacencyList, nodes, node, i, j);
//                }
//            }
//            return adjacencyList;
//        }
//        private void addEdges(
//                AdjacencyList<String, Edge<String>> edges,
//                Grid<String> nodes,
//                Node<String> node,
//                int row, int col) {
//
//            //up
//            if (row > 0) {
//                List<Node<String>> rowx = nodes.get(row - 1);
//                Node<String> up = rowx.get(col);
//                edges.put(node, new Edge<>(node, up));
//            }
//
//            //down
//            if (row < height - 1) {
//                Node<String> down = nodes.get(row + 1).get(col);
//                edges.put(node, new Edge<>(node, down));
//            }
//
//            //left
//            if (col > 0) {
//                Node<String> left = nodes.get(row).get(col - 1);
//                edges.put(node, new Edge<>(node, left));
//            }
//
//            //right
//            if (col < width - 1) {
//                Node<String> right = nodes.get(row).get(col + 1);
//                edges.put(node, new Edge<>(node, right));
//            }
//        }
//    }
//}
