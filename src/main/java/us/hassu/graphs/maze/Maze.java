package us.hassu.graphs.maze;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.*;

import java.util.*;

import static us.hassu.graphs.maze.MazeNode.Boundary.BOTTOM;
import static us.hassu.graphs.maze.MazeNode.Boundary.RIGHT;


@Setter
@Getter
public class Maze extends AbstractGraph {
    // grid is technically not needed, but it helps with mental model
    Grid grid;
    int width;
    int height;
    MazeNode start;
    MazeNode end;

    public Maze(HashMap<Node, List<Edge>> edges, Grid grid, int width, int height, MazeNode start, MazeNode end) {
        super(edges);
        this.grid = grid;
        this.width = width;
        this.height = height;
        this.start = start;
        this.end = end;
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
        private final int DEFAULT_WIDTH = 2;
        private final int DEFAULT_HEIGHT = 2;
        Integer width;
        Integer height;
        boolean createEdges;
        boolean debug;

        // builder methods for setting property
        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder createEdges(boolean createEdges) {
            this.createEdges = createEdges;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public Maze build() {
            int mazeWidth = Optional.ofNullable(width).orElse(DEFAULT_WIDTH) + 1;
            int mazeHeight = Optional.ofNullable(height).orElse(DEFAULT_HEIGHT) + 1;
            if (mazeHeight < 2 || mazeWidth < 2) {
                throw new IllegalArgumentException("Maze must be at least 2x2");
            }
            Grid nodes = createNodesGrid(mazeWidth, mazeHeight);
            HashMap<Node, List<Edge>> mazeAdjacencyList = new HashMap<>();
            Pair<MazeNode, MazeNode> ingressEgress = generateMaze(nodes, mazeAdjacencyList, mazeWidth, mazeHeight);
            MazeNode entrance = ingressEgress.getFirst();
            MazeNode exit = ingressEgress.getSecond();
            return new Maze(mazeAdjacencyList, nodes, mazeWidth, mazeHeight, entrance, exit);
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

        // depth first maze generation algorithm

        /**
         * Carve out a maze from the grid
         * @param grid
         * @param width
         * @param height
         * @param realAdjacencyList easier to make new adjacency list than to remove nodesInitialAdjacencyList from existing one
         * @return ingress and egress nodes
         */
        private Pair<MazeNode, MazeNode> generateMaze(Grid grid, HashMap<Node, List<Edge>> realAdjacencyList, int width, int height) {
            List<MazeNode> row1 = grid.get(0);
            Set<MazeNode> visited = new HashSet<>();

            for (MazeNode node : row1) {
                node.removeBoundary(RIGHT);
                visited.add(node);
            }
            for (int i = 0; i < height; i++) {
                grid.get(i).get(0).removeBoundary(BOTTOM);
                visited.add(grid.get(i).get(0));
            }

            Random random = new Random();
            Stack<MazeNode> stack = new Stack<>();

            //starting position
            List<MazeNode> firstRow = grid.get(0);
            MazeNode start = firstRow.get(random.nextInt(firstRow.size() - 1) + 1);

            stack.push(start);
            while (!stack.isEmpty()) {
                MazeNode current = stack.peek();
                visited.add(current);
                this.debugLog("current = " + current);

//                List<Edge> neighbors = nodesInitialAdjacencyList.getOrDefault(current, new ArrayList<>());
                List<MazeNode> neighbors = getAdjacentMazeNodes(grid, current, width, height);
                List<MazeNode> unvisitedNeighbors = new ArrayList<>();

                for (MazeNode neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        unvisitedNeighbors.add(neighbor);
                    }
                }

                this.debugLog("unvisitedNeighbors = " + unvisitedNeighbors);

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
                            this.debugLog("move right");
                            this.debugLog("removing right boundary for current");
                            current.removeBoundary(RIGHT);
                        } else if (diffCol == -1) { // diffCol == -1
                            //neighbor is to the left
                            this.debugLog("move left");
                            this.debugLog("removing right boundary for neighbor");
                            neighbor.removeBoundary(RIGHT);
                        } else {
                            throw new IllegalStateException("diffRow = " + diffRow + " diffCol = " + diffCol);
                        }
                    } else if (diffRow == 1) {
                        //diffCol must be 0
                        //neighbor is below
                        this.debugLog("move down");
                        this.debugLog("removing bottom boundary for current");
                        current.removeBoundary(BOTTOM);
                    } else if (diffRow == -1) {
                        //diffCol must be 0
                        //same col
                        //neighbor is above
                        neighbor.removeBoundary(BOTTOM);
                    } else {
                        throw new IllegalStateException("diffRow = " + diffRow + " diffCol = " + diffCol);
                    }
                    addEdge(realAdjacencyList, current, neighbor);
                    addEdge(realAdjacencyList, neighbor, current);
                }
            }

            //create exit
            List<MazeNode> lastRow = grid.get(height - 1);
            MazeNode exit = lastRow.get(random.nextInt(lastRow.size() - 1) + 1);
            exit.removeBoundary(BOTTOM);
            return Pair.of(start, exit);
        }

        List<MazeNode> getAdjacentMazeNodes(Grid grid, MazeNode node, int width, int height) {
            int row = node.getRow();
            int col = node.getCol();

            List<MazeNode> adjacentNodes = new ArrayList<>();
            //up
            if (row > 0) {
                MazeNode up = grid.get(row - 1).get(col);
                adjacentNodes.add(up);
            }

            //down
            if (row < height - 1) {
                MazeNode down = grid.get(row + 1).get(col);
                adjacentNodes.add(down);
            }

            //left
            if (col > 0) {
                MazeNode left = grid.get(row).get(col - 1);
                adjacentNodes.add(left);
            }

            //right
            if (col < width - 1) {
                MazeNode right = grid.get(row).get(col + 1);
                adjacentNodes.add(right);
            }
            return adjacentNodes;
        }

        void addEdge(HashMap<Node, List<Edge>> edges, Node from, Node to) {
            edges.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(from, to));
        }

        void debugLog(String s) {
            if (debug) {
                System.out.println(s);
            }
        }
    }
}
