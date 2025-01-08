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

    private static GraphUtils INSTANCE;

    @Setter
    @Getter
    boolean debug;

    private GraphUtils(boolean debug) {
        this.debug = debug;
    }

    public static <T extends Node> GraphUtils<T> getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphUtils<>(false);
        }
        return INSTANCE;
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

    public BfsTrace bfsTrace(Graph<T> graph, T start, T end) {
        BfsTrace trace = new BfsTrace();

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
            BfsFrame frame = new BfsFrame(current, newlyQueued);
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

    boolean hasCycle(AbstractGraph<T> graph) {
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

    private void debugLog(String msg) {
        if (debug) {
            System.out.println(msg);
        }
    }
}
