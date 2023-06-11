package us.hassu.graphs.maze;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static us.hassu.graphs.maze.MazeNode.Boundary.BOTTOM;
import static us.hassu.graphs.maze.MazeNode.Boundary.RIGHT;


@Setter @Getter
public class Maze extends Graph {
    Grid grid;
    int width;
    int height;


    public Maze(AdjacencyList edges, Grid grid, int width, int height) {
        super(edges);
        this.grid = grid;
        this.width = width;
        this.height = height;
    }

    @Override
    public void print() {
        System.out.println("Grid:");
        for (List<MazeNode> row : grid) {
            System.out.println(row);
        }
        super.print();
    }

    public static class Builder {
        Integer width;

        Integer height;

        boolean createEdges = false;

        private int DEFAULT_WIDTH = 2;

        private int DEFAULT_HEIGHT = 2;

        // builder methods for setting property
        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder createEdges(boolean createEdges) {
            this.createEdges = createEdges;
            return this;
        }


        // build method to deal with outer class
        // to return outer instance
        public Maze build() {
            int width1 = width != null ? width : DEFAULT_WIDTH;
            int height1 = height != null ? height : DEFAULT_HEIGHT;
            if (height1 < 2 || width1 < 2) {
                throw new IllegalArgumentException("Maze must be at least 2x2");
            }
            Grid nodes = createNodesGrid(width1, height1);
            AdjacencyList edges;
            if (createEdges) {
                edges = createEdges(nodes, width, height);
            } else {
                edges = new AdjacencyList();
            }
            carve(nodes, edges, width1, height1);
            return new Maze(edges, nodes, width1, height1);
        }

        /*
            || y  - >  +
            x
            |
            v
            +

            (x,y)     (x, y+1)
            (x+1,y)  (x+1,y+1)

            i = x = row = limited by height
            j = y = col = limited by width
         */
        private Grid createNodesGrid(int width, int height) {
            Grid grid = new Grid(width);
            for (int i = 0; i < height; i++) {
                List<MazeNode> col = new ArrayList<>();
                grid.add(col);
                for (int j = 0; j < width; j++) {
                    // create row
                    col.add(new MazeNode(i + "," + j));
                }
            }

            return grid;
        }

        private AdjacencyList createEdges(Grid nodes, int width, int height) {
            AdjacencyList adjacencyList = new AdjacencyList();
            for (int i = 0; i < height; i++) {
                List<MazeNode> col = nodes.get(i);
                for (int j = 0; j < width; j++) {
                    Node node = col.get(j);
                    addEdges(adjacencyList, nodes, node, i, j);
                }
            }
            return adjacencyList;
        }

        private void carve(Grid grid, AdjacencyList edges, int width, int height) {
            List<MazeNode> row1 = grid.get(0);
            for (MazeNode node: row1) {
                node.removeBoundary(RIGHT);
            }
            for (int i = 0; i < height; i++) {
                grid.get(i).get(0).removeBoundary(BOTTOM);
            }
            if (width > 1 && height > 1) {
                grid.get(0).get(0).removeBoundary(BOTTOM);
                grid.get(0).get(0).removeBoundary(RIGHT);
            }
            Random random = new Random();
//            Stack<Node> stack = new Stack<>();
        }
        private void addEdges(
                AdjacencyList edges,
                Grid nodes,
                Node node,
                int row, int col) {

            //up
            if (row > 0) {
                Node up = nodes.get(row - 1).get(col);
                edges.put(node, new Edge(node, up));
            }

            //down
            if (row < height - 1) {
                Node down = nodes.get(row + 1).get(col);
                edges.put(node, new Edge(node, down));
            }

            //left
            if (col > 0) {
                Node left = nodes.get(row).get(col - 1);
                edges.put(node, new Edge(node, left));
            }

            //right
            if (col < width - 1) {
                Node right = nodes.get(row).get(col + 1);
                edges.put(node, new Edge(node, right));
            }
        }
    }
}
