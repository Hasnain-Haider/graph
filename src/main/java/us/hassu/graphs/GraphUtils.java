package us.hassu.graphs;

import lombok.Getter;
import lombok.Setter;
import us.hassu.graphs.graph.AdjacencyList;
import us.hassu.graphs.graph.Edge;
import us.hassu.graphs.graph.Graph;
import us.hassu.graphs.graph.Node;
import us.hassu.graphs.trace.BfsFrame;
import us.hassu.graphs.trace.BfsTrace;

import java.util.*;
import java.util.stream.Collectors;

public class GraphUtils {

    private static GraphUtils INSTANCE;
    @Setter @Getter
    boolean debug;

    private GraphUtils(boolean debug) {
        this.debug = debug;
    }

    public static GraphUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GraphUtils(false);
        }
        return INSTANCE;
    }

    // Returns the path from start to end
    public List<Node> breadFirstSearch(Graph graph, Node start, Node end) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> parents = new HashMap<>();

        queue.add(start);
        visited.add(start);

        boolean found = false;
        while(!queue.isEmpty()) {
            Node current = queue.removeFirst();
            if (current.equals(end)) {
                found = true;
                break;
            }

            List<Edge> neighbors = graph.getAdjacentNodes(current);
            for (Edge neighborEdge : neighbors) {
                Node neighbor = neighborEdge.getTo();
                if (!visited.contains(neighbor)) {
                    parents.put(neighbor, current);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        if(!found) {
            throw new RuntimeException("No path found");
        } else {
            return getPathFromParentMap(parents, end);
        }
    }

    // Returns "BFSTrace", which is a list of every node visited in the order they were visited,
    // and the state of the queue at each step
    public BfsTrace bfsTrace(Graph graph, Node start, Node end) {
        BfsTrace trace = new BfsTrace();

        ArrayDeque<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> parents = new HashMap<>();

        queue.add(start);
        visited.add(start);

        boolean found = false;
        while(!queue.isEmpty()) {
            Set<Node> newlyQueued = new HashSet<>();

            Node current = queue.removeFirst();
            if (current.equals(end)) {
                found = true;
                break;
            }

            List<Edge> neighbors = graph.getAdjacentNodes(current);
            for (Edge neighborEdge : neighbors) {
                Node neighbor = neighborEdge.getTo();
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
        if(found) {
            List<Node> path = getPathFromParentMap(parents, end);
            trace.setPath(path);
            return trace;
        }
        return trace;
    }

    public List<Node> dfs(Graph graph, Node start, Node end) {
        Set<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();
        Map<Node, Node> parents = new HashMap<>();
        boolean found = false;

        debugLog("Starting DFS from " + start);
        Node current = start;
        stack.push(current);

        while(!stack.isEmpty()) {
            current = stack.pop();
            debugLog("DFS Searching " + current);
            if (current == end) {
                found = true;
                debugLog("DFS Found end node " + end);
                break;
            }
            for (Edge neighborEdge : graph.getAdjacentNodes(current)) {
                Node neighbor = neighborEdge.getTo();
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

    boolean hasCycle(Graph graph) {
        HashMap<Node, List<Edge>> adjacencyList1 = graph.getAdjacencyList();
        for (Node node : adjacencyList1.keySet()) {
            Set<Node> visited = new HashSet<>();
            Stack<Node> stack = new Stack<>();
            visited.add(node);
            stack.push(node);
            if (hasCycle(graph, stack, visited)) {
                return true;
            }
        }
        return false;
    }

    boolean hasCycle(Graph graph, Stack<Node> stack, Set<Node> visited) {
        Node current = stack.peek();
        // O(n) operation
        Set<Node> neighbors = getAdjacentNodes(graph, current);

        if (!neighbors.isEmpty()) {
            for (Node neighbor : neighbors) {
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
    boolean hasCycleBFS(Graph graph) {
        AdjacencyList adjacencyList = graph.getAdjacencyList();

        for (Node node : adjacencyList.keySet()) {
            ArrayDeque<Node> queue = new ArrayDeque<>();
            Set<Node> visited = new HashSet<>();

            queue.add(node);
            visited.add(node);

            while (!queue.isEmpty()) {
                Node curr = queue.poll();
                Set<Node> neighbors = getAdjacentNodes(graph, curr);
                for (Node neighbor : neighbors) {
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

    public Set<Node> getAdjacentNodes(Graph graph, Node node) {
        return Optional.ofNullable(graph.getAdjacentNodes(node))
                .orElse(Collections.emptyList())
                .stream()
                .map(Edge::getTo)
                .collect(Collectors.toSet());
    }
    private List<Node> getPathFromParentMap(Map<Node, Node> parents, Node end) {
        List<Node> path = new ArrayList<>();
        Node current = end;
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
