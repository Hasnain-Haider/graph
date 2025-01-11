package us.hassu.graphs;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.AbstractGraph;
import us.hassu.graphs.graph.Edge;
import us.hassu.graphs.graph.Graph;
import us.hassu.graphs.graph.Node;

import us.hassu.graphs.trace.BfsFrame;
import us.hassu.graphs.trace.BfsTrace;

import java.util.*;

public class GraphUtils<T extends Node> {
    @Setter
    @Getter
    boolean debug;

    public GraphUtils() {
        this(false);
    }

    GraphUtils(boolean debug) {
        this.debug = debug;
    }

    public List<T> breadthFirstSearch(Graph<T> graph, T start, T end) {
        ArrayDeque<T> queue = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        Map<T, T> parents = new HashMap<>();

        queue.add(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            T current = queue.removeFirst();
            if (current.equals(end)) {
                found = true;
                break;
            }

            Set<? extends T> neighbors = graph.getAdjacentNodesSet(current);
            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    parents.put(neighbor, current);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        if (!found) {
            throw new RuntimeException("No path found");
        } else {
            return getPathFromParentMap(parents, end);
        }
    }

    public BfsTrace<T> bfsTrace(Graph<T> graph, T start, T end) {
        BfsTrace<T> trace = new BfsTrace<>();

        ArrayDeque<T> queue = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();
        Map<T, T> parents = new HashMap<>();

        queue.add(start);
        visited.add(start);

        boolean found = false;
        while (!queue.isEmpty()) {
            Set<T> newlyQueued = new HashSet<>();

            T current = queue.removeFirst();
            if (current.equals(end)) {
                found = true;
                break;
            }

            for (T neighbor : graph.getAdjacentNodesSet(current)) {
                if (!visited.contains(neighbor)) {
                    parents.put(neighbor, current);
                    queue.add(neighbor);
                    visited.add(neighbor);
                    newlyQueued.add(neighbor);
                }
            }
            BfsFrame<T> frame = new BfsFrame<T>(current, newlyQueued);
            trace.getTrace().add(frame);
        }

        trace.setFoundPath(found);
        if (found) {
            List<T> path = getPathFromParentMap(parents, end);
            trace.setPath(path);
            return trace;
        }
        return trace;
    }

    public List<T> dfs(Graph<T> graph, T start, T end) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        Map<T, T> parents = new HashMap<>();
        boolean found = false;

        debugLog("Starting DFS from " + start);
        T current = start;
        stack.push(current);

        while (!stack.isEmpty()) {
            current = stack.pop();
            debugLog("DFS Searching " + current);
            if (current == end) {
                found = true;
                debugLog("DFS Found end node " + end);
                break;
            }
            for (Edge<T> neighborEdge : graph.getEdgesFrom(current)) {
                T neighbor = neighborEdge.getTo();
                if (!visited.contains(neighbor)) {
                    debugLog("DFS Discovered " + neighbor);
                    parents.put(neighbor, current);
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        if (!found) {
            throw new RuntimeException("No path found");
        } else {
            return getPathFromParentMap(parents, end);
        }
    }

    public boolean hasCycle(AbstractGraph<T> graph) {
        for (T node : graph.keySet()) {
            Set<T> visited = new HashSet<>();
            Stack<T> stack = new Stack<>();
            visited.add(node);
            stack.push(node);
            if (hasCycle(graph, stack, visited)) {
                return true;
            }
        }
        return false;
    }

    boolean hasCycle(AbstractGraph<T> graph, Stack<T> stack, Set<T> visited) {
        T current = stack.peek();
        // O(n) operation
        Set<T> neighbors = graph.getAdjacentNodesSet(current);

        if (!neighbors.isEmpty()) {
            for (T neighbor : neighbors) {
                if (visited.contains(neighbor)) {
                    this.debugLog("Cycle detected: " + stack + " -> " + neighbor);
                    return true;
                }
                visited.add(neighbor);
                stack.push(neighbor);
                if (hasCycle(graph, stack, visited)) {
                    return true;
                }
            }
        } else {
            stack.pop();
        }
        // all neighbors have been visited
        return false;
    }

    // untested
    boolean hasCycleBFS(AbstractGraph<T> graph) {

        for (T node : graph.keySet()) {
            ArrayDeque<T> queue = new ArrayDeque<>();
            Set<T> visited = new HashSet<>();

            queue.add(node);
            visited.add(node);

            while (!queue.isEmpty()) {
                T curr = queue.poll();
                Set<T> neighbors = graph.getAdjacentNodesSet(curr);
                for (T neighbor : neighbors) {
                    if (visited.contains(neighbor)) {
                        return true;
                    }
                    queue.add(neighbor);
                }
                visited.add(curr);
            }
        }
        return false;
    }

    private List<T> getPathFromParentMap(Map<T, T> parents, T end) {
        List<T> path = new ArrayList<>();
        T current = end;
        while (current != null) {
            path.add(current);
            current = parents.get(current);
        }
        Collections.reverse(path);
        return path;
    }

//    public String drawMaze(Maze maze) {
//        return drawMaze(
//                maze.getGrid(),
//                maze.getHeight(),
//                maze.getWidth(),
//                maze.getEntrance(),
//                maze.getExit(),
//                maze.isTopEntrance()
//        );
//    }
//
//    String drawMaze(List<List<MazeNode>> grid, int height, int width,
//                         MazeNode entrance, MazeNode exit, boolean isTopEntrance) {
//        StringBuilder mazeRepresentation = new StringBuilder();
//
//        // Draw the top boundary
//        for (int col = 0; col < width; col++) {
//            if (isTopEntrance && col == entrance.getCol()) {
//                mazeRepresentation.append("+   ");
//            } else {
//                mazeRepresentation.append("+---");
//            }
//        }
//        mazeRepresentation.append("+\n");
//
//        for (int row = 0; row < height; row++) {
//            StringBuilder topRow = new StringBuilder("|");
//            StringBuilder bottomRow = new StringBuilder("+");
//
//            for (int col = 0; col < width; col++) {
//                MazeNode cell = grid.get(row).get(col);
//                Set<MazeNode.Boundary> boundaries = cell.getBoundaries();
//
//                // Check if this is the entrance or exit
//                if (row == entrance.getRow() && col == entrance.getCol()) {
//                    topRow.append(" E ");
//                } else if (row == exit.getRow() && col == exit.getCol()) {
//                    topRow.append(" X ");
//                } else {
//                    topRow.append("   ");
//                }
//
//                if (boundaries.contains(MazeNode.Boundary.RIGHT)) {
//                    topRow.append("|");
//                } else {
//                    topRow.append(" ");
//                }
//
//                if (boundaries.contains(MazeNode.Boundary.BOTTOM)) {
//                    bottomRow.append("---+");
//                } else {
//                    bottomRow.append("   +");
//                }
//            }
//
//            mazeRepresentation.append(topRow).append("\n");
//            mazeRepresentation.append(bottomRow).append("\n");
//        }
//
//        return mazeRepresentation.toString();
////        System.out.println(mazeRepresentation);
//    }

    private void debugLog(String msg) {
        if (debug) {
            System.out.println(msg);
        }
    }
}
