package us.hassu.graphs;

import com.google.common.collect.ArrayListMultimap;

import java.util.*;

class Graph <T>{

//    ArrayListMultimap<Node<T>, Node<T>> edges;
    ArrayListMultimap<Node<T>, Edge<T>> edges;
    boolean debug;

    public Graph(ArrayListMultimap<Node<T>, Edge<T>> edges) {
        this.edges = edges;
    }

    public Graph() {
        this(false);
    }

    public Graph(boolean debug) {
        this.edges = ArrayListMultimap.create();
        this.debug = debug;
    }


    public void addEdge(Edge<T> edge) {
        edges.put(edge.getFrom(), edge);
    }

    public void addEdge(Node<T> from, Node<T> to) {
        edges.put(from, new Edge<>(from, to));
    }

    public List<Edge<T>> getAdjacentNodes(Node<T> node) {
        return edges.get(node);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> node : edges.keySet()) {
            sb.append("{").append(node).append(" -> ")
                    .append(edges.get(node)).append("}//");
        }
        return sb.toString();
    }

    void print() {
        System.out.println("Edges:");
        for (Node<T> node : edges.keySet()) {
            System.out.println(node + " -> " + edges.get(node));
        }
    }

    public List<Node<T>> bfs(Node<T> start, Node<T> end) {
        ArrayDeque<Node<T>> queue = new ArrayDeque<>();
        Set<Node<T>> visited = new HashSet<>();
        Map<Node<T>, Node<T>> parents = new HashMap<>();

        queue.add(start);
        visited.add(start);

        boolean found = false;
        while(!queue.isEmpty()) {
            Node<T> current = queue.removeFirst();
            if (current.equals(end)) {
                found = true;
                break;
            }

            List<Edge<T>> neighbors = getAdjacentNodes(current);
            for (Edge<T> neighborEdge : neighbors) {
                Node<T> neighbor = neighborEdge.getTo();
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
    public BfsTrace<T> bfsTrace(Node<T> start, Node<T> end) {
        BfsTrace<T> trace = new BfsTrace<>();

        ArrayDeque<Node<T>> queue = new ArrayDeque<>();
        Set<Node<T>> visited = new HashSet<>();
        Map<Node<T>, Node<T>> parents = new HashMap<>();

        queue.add(start);
        visited.add(start);

        boolean found = false;
        while(!queue.isEmpty()) {
            Set<Node<T>> newlyQueued = new HashSet<>();

            Node<T> current = queue.removeFirst();
            if (current.equals(end)) {
                found = true;
                break;
            }

            List<Edge<T>> neighbors = getAdjacentNodes(current);
            for (Edge<T> neighborEdge : neighbors) {
                Node<T> neighbor = neighborEdge.getTo();
                if (!visited.contains(neighbor)) {
                    parents.put(neighbor, current);
                    queue.add(neighbor);
                    visited.add(neighbor);
                    newlyQueued.add(neighbor);
                }
            }
            BfsFrame<T> frame = new BfsFrame<>(current, newlyQueued);
            trace.getTrace().add(frame);
        }

        if(!found) {
            throw new RuntimeException("No path found");
        } else {
            List<Node<T>> path = getPathFromParentMap(parents, end);
            trace.setPath(path);
            return trace;
        }
    }

    public List<Node<T>> dfs(Node<T> start, Node<T> end) {
        Set<Node<T>> visited = new HashSet<>();
        Stack<Node<T>> stack = new Stack<>();
        Map<Node<T>, Node<T>> parents = new HashMap<>();
        boolean found = false;

        debugLog("Starting DFS from " + start);
        Node<T> current = start;
        stack.push(current);

        while(!stack.isEmpty()) {
            current = stack.pop();
            debugLog("DFS Searching " + current);
            if (current == end) {
                found = true;
                debugLog("DFS Found end node " + end);
                break;
            }
            for (Edge<T> neighborEdge : getAdjacentNodes(current)){
                Node<T> neighbor = neighborEdge.getTo();
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

    private List<Node<T>> getPathFromParentMap(Map<Node<T>, Node<T>> parents, Node<T> end) {
        List<Node<T>> path = new ArrayList<>();
        Node<T> current = end;
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
