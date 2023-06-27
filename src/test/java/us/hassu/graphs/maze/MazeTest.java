package us.hassu.graphs.maze;

import org.testng.annotations.Test;

public class MazeTest {

    @org.testng.annotations.BeforeMethod
    public void setUp() {
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
    }
    @Test
    public void testGenerateMaze() {
//        HashMap<Node, Integer> startIndexOccurence = new HashMap<>();
//        PriorityQueue<Map.Entry<Node, Integer>> pq = new PriorityQueue<>(30, Comparator.comparingInt(Map.Entry::getValue));

        for (int h = 2; h < 30; h++) {
            for (int w = 2; w < 30; w++) {
                Maze.Builder builder = new Maze.Builder();
                Maze maze = builder.height(h).width(w).build();
//                startIndexOccurence.put(new Node(maze.start), startIndexOccurence.getOrDefault(new Node(maze.start), 0) + 1);
            }
        }
//        pq.addAll(startIndexOccurence.entrySet());
//        while (!pq.isEmpty()) {
//            Map.Entry<Node, Integer> entry = pq.poll();
//            System.out.println(entry.getKey() + " count="+entry.getValue());
//        }
    }
}