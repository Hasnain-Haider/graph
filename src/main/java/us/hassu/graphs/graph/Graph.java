package us.hassu.graphs.graph;

import us.hassu.graphs.trace.BfsFrame;
import us.hassu.graphs.trace.BfsTrace;

import java.util.*;

public abstract class Graph {

    AdjacencyList edges;
    boolean debug;

    public Graph(AdjacencyList edges) {
        this.edges = edges;
    }

    public Graph() {
        this(false);
    }

    public Graph(boolean debug) {
        this.debug = debug;
    }

    public List<Edge> getAdjacentNodes(Node node) {
        return edges.get(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : edges.keySet()) {
            sb.append("{").append(node).append(" -> ")
                    .append(edges.get(node)).append("}//");
        }
        return sb.toString();
    }

    public void print() {
        System.out.println("Edges:");
        for (Node node : edges.keySet()) {
            System.out.println(node + " -> " + edges.get(node));
        }
    }

    public List<Node> bfs(Node start, Node end) {
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

            List<Edge> neighbors = getAdjacentNodes(current);
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

    public BfsTrace bfsTrace(Node start, Node end) {
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

            List<Edge> neighbors = getAdjacentNodes(current);
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

        if(!found) {
            throw new RuntimeException("No path found");
        } else {
            List<Node> path = getPathFromParentMap(parents, end);
            trace.setPath(path);
            return trace;
        }
    }

    public List<Node> dfs(Node start, Node end) {
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
            for (Edge neighborEdge : getAdjacentNodes(current)){
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
