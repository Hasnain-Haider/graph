package us.hassu.graphs;

import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;
import java.util.List;

public class GridGraph extends Graph<String> {
    List<List<Node<String>>> grid;

    int width;
    int height;


    public GridGraph(ArrayListMultimap<Node<String>, Edge<String>> edges, List<List<Node<String>>> grid, int width, int height) {
        super(edges);
        this.grid = grid;
        this.width = width;
        this.height = height;
    }


    public List<List<Node<String>>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Node<String>>> grid) {
        this.grid = grid;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    void print() {
        System.out.println("Grid:");
        for (List<Node<String>> row : grid) {
            System.out.println(row);
        }
        super.print();
    }

    public static class GridGraphBuilder {
        Integer width;

        Integer height;

        boolean createEdges = false;

        private int DEFAULT_WIDTH = 2;

        private int DEFAULT_HEIGHT = 2;

        // builder methods for setting property
        public GridGraphBuilder width(int width) {
            this.width = width;
            return this;
        }

        public GridGraphBuilder height(int height) {
            this.height = height;
            return this;
        }

        public GridGraphBuilder createEdges(boolean createEdges) {
            this.createEdges = createEdges;
            return this;
        }


        // build method to deal with outer class
        // to return outer instance
        public GridGraph build() {
            int width1 = width != null ? width : DEFAULT_WIDTH;
            int height1 = height != null ? height : DEFAULT_HEIGHT;
            List<List<Node<String>>> nodes = createNodes(width1, height1);
            ArrayListMultimap<Node<String>, Edge<String>> edges;
            if (createEdges) {
                edges = createEdges(nodes, width, height);
            } else {
                edges = ArrayListMultimap.create();
            }
            return new GridGraph(edges, nodes, width1, height1);
        }

        private List<List<Node<String>>> createNodes(int width, int height) {
            List<List<Node<String>>> grid = new ArrayList<>(width);
            for (int i = 0; i < width; i++) {
                List<Node<String>> col = new ArrayList<>();
                grid.add(col);
                for (int j = 0; j < height; j++) {
                    col.add(new Node<>(i + "," + j));
                }
            }
            return grid;
        }

        private ArrayListMultimap<Node<String>, Edge<String>> createEdges(List<List<Node<String>>> nodes, int width, int height) {
            ArrayListMultimap<Node<String>, Edge<String>> edges = ArrayListMultimap.create();
            for (int i = 0; i < width; i++) {
                List<Node<String>> col = nodes.get(i);
                for (int j = 0; j < height; j++) {
                    Node<String> node = col.get(j);
                    addEdges(edges, nodes, node, i, j);
                }
            }
            return edges;
        }
        private void addEdges(
                ArrayListMultimap<Node<String>, Edge<String>> edges,
                List<List<Node<String>>> nodes,
                Node<String> node,
                int i,
                int j) {
            //up
            if (i > 0) {
                List<Node<String>> row = nodes.get(i - 1);
                Node<String> up = row.get(j);
                edges.put(node, new Edge<>(node, up));
            }

            //down
            if (i < width - 1) {
                Node<String> down = nodes.get(i + 1).get(j);
                edges.put(node, new Edge<>(node, down));
            }

            //left
            if (j > 0) {
                Node<String> left = nodes.get(i).get(j - 1);
                edges.put(node, new Edge<>(node, left));
            }

            //right
            if (j < height - 1) {
                Node<String> right = nodes.get(i).get(j + 1);
                edges.put(node, new Edge<>(node, right));
            }
        }
    }
}
