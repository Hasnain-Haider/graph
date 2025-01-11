package us.hassu.graphs;

//import us.hassu.graphs.maze.Maze;
//import us.hassu.graphs.maze.MazeNode;
//import us.hassu.graphs.maze.Grid;

public class Main {
    public static void main(String[] args) {
//        Maze.Builder builder = new Maze.Builder().debug(true).height(4).width(5);
//        Maze maze = builder.build();
//
//        GraphUtils gu = GraphUtils.getInstance();
//        var s = gu.drawMaze(maze);
//        System.out.println(s);
    }
//
//    static public void drawMazex(Maze maze) {
//        Grid<MazeNode> grid = maze.getGrid();
//        int height = maze.getHeight();
//        int width = maze.getWidth();
//
//        StringBuilder mazeBuilder = new StringBuilder();
//
////        System.out.println("maze has top entrance: " + maze.isTopEntrance() + " maze height: " + height + " maze width: " + width);
//        for (int col = 0; col < width + 1; col++) {
//            if (maze.isTopEntrance() && col == maze.getEntrance().getCol()) continue;
//            mazeBuilder.append("____");
//        }
//        mazeBuilder.append("\n");
//
//        for (int row = 1; row < height + 1; row++) {
//            for (int col = 0; col < width + 1; col++) {
//                if (col == 0) {
//                    mazeBuilder.append("|");
//                    continue;
//                }
//                MazeNode node = grid.get(row - 1).get(col - 1);
//
//                mazeBuilder.append("+");
//                boolean hasRight = node.getBoundaries().contains(MazeNode.Boundary.RIGHT);
//                boolean hasBottom = node.getBoundaries().contains(MazeNode.Boundary.BOTTOM);
//
//                if (hasRight && hasBottom) {
//                    mazeBuilder.append("__|");
//                } else if (hasRight) {
//                    mazeBuilder.append("  |");
//                } else if (hasBottom) {
//                    mazeBuilder.append("___");
//                } else {
//                    mazeBuilder.append("   ");
//                }
//            }
//
//            mazeBuilder.append("\n");
//        }
//        System.out.println(mazeBuilder);
//    }
//
//    static public void drawMaze2(Maze maze) {
//        Grid<MazeNode> grid = maze.getGrid();
//        int height = maze.getHeight();
//        int width = maze.getWidth();
//
//        StringBuilder mazeBuilder = new StringBuilder();
//
//        // Draw the top boundary
//        for (int col = 0; col < width; col++) {
//            if (maze.isTopEntrance() && col == maze.getEntrance().getCol()) {
//                mazeBuilder.append("    "); // Leave an opening for the entrance
//            } else {
//                mazeBuilder.append("+---");
//            }
//        }
//        mazeBuilder.append("+\n");
//
//        // Draw each row of the maze
//        for (int row = 0; row < height; row++) {
//            StringBuilder topRow = new StringBuilder();
//            StringBuilder bottomRow = new StringBuilder();
//
//            for (int col = 0; col < width; col++) {
//                MazeNode node = grid.get(row).get(col);
//
//                // Draw vertical walls and open spaces
//                topRow.append("|");
//                if (node.getBoundaries().contains(MazeNode.Boundary.RIGHT)) {
//                    topRow.append("    ");
//                } else {
//                    topRow.append("    ");
//                }
//
//                // Draw horizontal walls
//                bottomRow.append("+");
//                if (node.getBoundaries().contains(MazeNode.Boundary.BOTTOM)) {
//                    bottomRow.append("---");
//                } else {
//                    bottomRow.append("   ");
//                }
//            }
//
//            // Close the row with a right boundary
//            topRow.append("|\n");
//            mazeBuilder.append(topRow);
//
//            // Append the bottom row
//            mazeBuilder.append(bottomRow).append("+\n");
//        }
//
//        System.out.println(mazeBuilder);
//    }
}
