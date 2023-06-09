package us.hassu.graphs;

import com.google.common.collect.ArrayListMultimap;

import java.util.*;

class Graph <T>{

    ArrayListMultimap<Node<T>, Node<T>> edges;
    boolean debug;

    public Graph(ArrayListMultimap<Node<T>, Node<T>> edges) {
        this.edges = edges;
    }

    public Graph() {
        this(false);
    }

    public Graph(boolean debug) {
        this.edges = ArrayListMultimap.create();
        this.debug = debug;
    }


    public void addEdge(Node<T> from, Node<T> to) {
        edges.put(from, to);
    }

    public List<Node<T>> getAdjacentNodes(Node<T> node) {
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
            if(current.equals(end)) {
                found = true;
                break;
            }

            List<Node<T>> neighbors = getAdjacentNodes(current);
            for (Node<T> neighbor : neighbors) {
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
            for (Node<T> neighbor : getAdjacentNodes(current)){
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
