package us.hassu.graphs.maze;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.*;


import java.util.*;

import static us.hassu.graphs.maze.MazeNode.Boundary.BOTTOM;
import static us.hassu.graphs.maze.MazeNode.Boundary.RIGHT;


@Setter @Getter
public class Maze extends Graph {

    // grid is technically not needed, but it helps with mental model
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
        printGrid();
        super.print();
    }

    public void printGrid() {
        System.out.println("Grid:");
        for (List<MazeNode> row : grid) {
            System.out.println(row);
        }
    }

    public static class Builder {
        Integer width;

        Integer height;

        boolean createEdges;

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
            AdjacencyList edges = createEdges(nodes, width, height);
            AdjacencyList mazeAdjacencyList = new AdjacencyList();
            carve(nodes, edges, width1, height1, mazeAdjacencyList);
            return new Maze(mazeAdjacencyList, nodes, width1, height1);
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
                    col.add(new MazeNode(i + "," + j, i, j));
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

        // depth first maze generation algorithm
        private void carve(Grid grid, AdjacencyList edges, int width, int height, AdjacencyList realAdjacencyList) {
            List<MazeNode> row1 = grid.get(0);
            Set<MazeNode> visited = new HashSet<>();

            for (MazeNode node: row1) {
                node.removeBoundary(RIGHT);
                visited.add(node);
            }
            for (int i = 0; i < height; i++) {
                grid.get(i).get(0).removeBoundary(BOTTOM);
                visited.add(grid.get(i).get(0));
            }

            Random random = new Random(0L);
            Stack<MazeNode> stack = new Stack<>();

            //starting position
            List<MazeNode> firstRow = grid.get(0);
            MazeNode start = firstRow.get(random.nextInt(firstRow.size()));
//            System.out.println("start = " + start);

            stack.push(start);
            while (!stack.isEmpty()) {
                MazeNode current = stack.peek();
                visited.add(current);
                System.out.println("current = " + current);
                List<Edge> neighbors = edges.get(current);
                List<MazeNode> unvisitedNeighbors = new ArrayList<>();

                for (Edge edge: neighbors) {
                    MazeNode neighbor = (MazeNode) edge.getTo();
                    if (!visited.contains(neighbor)) {
                        unvisitedNeighbors.add(neighbor);
                    }
                }

                System.out.println("unvisitedNeighbors = " + unvisitedNeighbors);

                if (unvisitedNeighbors.isEmpty()) {
                    stack.pop();
                } else {
                    // choose random neighbor
                    MazeNode neighbor = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));
                    stack.push(neighbor);

                    // which neighbor is it?
                    int diffRow = neighbor.getRow() - current.getRow();
                    int diffCol = neighbor.getCol() - current.getCol();
                    if (diffRow == 0) {
                        // same row
                        if (diffCol == 1) {
                            //neighbor is to the right
                            System.out.println("move right");
                            System.out.println("removing right boundary for current");
                            current.removeBoundary(RIGHT);
                        } else { // diffCol == -1
                            //neighbor is to the left
                            System.out.println("move left");
                            System.out.println("removing right boundary for neighbor");
                            neighbor.removeBoundary(RIGHT);
                        }
                    } else if (diffRow == 1) {
                        //diffCol must be 0
                        //neighbor is below
                        System.out.println("move down");
                        System.out.println("removing bottom boundary for current");
                        current.removeBoundary(BOTTOM);
                    } else if (diffRow == -1) {
                        //diffCol must be 0
                        //same col
                        //neighbor is above
                        neighbor.removeBoundary(BOTTOM);
                    } else {
                        throw new IllegalStateException("diffRow = " + diffRow + " diffCol = " + diffCol);
                    }
                    realAdjacencyList.put(current, neighbor);
                    realAdjacencyList.put(neighbor, current);
                }
            }
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
