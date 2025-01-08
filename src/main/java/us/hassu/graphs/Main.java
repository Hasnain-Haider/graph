package us.hassu.graphs;

import us.hassu.graphs.maze.Grid;
import us.hassu.graphs.maze.Maze;
import us.hassu.graphs.maze.MazeNode;



public class Main {
    public static void main(String[] args) {
        Maze.Builder builder = new Maze.Builder().debug(true).height(4).width(4);
        Maze maze = builder.build();
        maze.printGrid();

        System.out.println("entrance: " + maze.getEntrance() + " exit: " + maze.getExit());

        drawMaze(maze);

//        printAsciiMaze(maze.getGrid(), maze.getWidth(), maze.getHeight());
    }

    static public void drawMaze(Maze maze) {
        Grid<MazeNode> grid = maze.getGrid();
        int height = maze.getHeight();
        int width = maze.getWidth();

        StringBuilder mazeBuilder = new StringBuilder();

        System.out.println("maze has top entrance: " + maze.isTopEntrance() + " maze height: " + height + " maze width: " + width);
        for (int col = 0; col < width + 1; col++) {
            System.out.println("col: " + col + " entrance col: " + maze.getEntrance().getCol());
            if (maze.isTopEntrance() && col == maze.getEntrance().getCol()) continue;
            mazeBuilder.append("____");
        }
        mazeBuilder.append("\n");

        for (int row = 1; row < height + 1; row++) {
            for (int col = 0; col < width + 1; col++) {
                if (col == 0) {
                    mazeBuilder.append("|");
                    continue;
                }
                MazeNode node = grid.get(row - 1).get(col - 1);

                mazeBuilder.append("+");
                boolean hasRight = node.getBoundaries().contains(MazeNode.Boundary.RIGHT);
                boolean hasBottom = node.getBoundaries().contains(MazeNode.Boundary.BOTTOM);

                if (hasRight && hasBottom) {
                    mazeBuilder.append("__|");
                } else if (hasRight) {
                    mazeBuilder.append("  |");
                } else if (hasBottom) {
                    mazeBuilder.append("___");
                } else {
                    mazeBuilder.append("   ");
                }
            }

            mazeBuilder.append("\n");
        }
        System.out.println(mazeBuilder.toString());
    }
}

            // Side walls of each cell
//            for (int col = 0; col < width; col++) {
//                MazeNode node = grid.get(row).get(col);
//                if (node.getBoundaries().contains(MazeNode.Boundary.RIGHT)) {
//                    mazeBuilder.append("|   ");
//                } else {
//                    mazeBuilder.append("    ");
//                }
//            }
//            mazeBuilder.append("|\n");
//        }

        // Bottom walls of the last row
//        for (int col = 0; col < width; col++) {
//            mazeBuilder.append("+___");
//        }
//        mazeBuilder.append("+\n");
//
//        System.out.println(mazeBuilder.toString());
//    }
//
//    public static void printAsciiMaze(Grid<MazeNode> grid, int width, int height) {
//        StringBuilder sb = new StringBuilder();
//
//        for (int row = 0; row < height; row++) {
//            StringBuilder middleRow = new StringBuilder();
//            StringBuilder bottomBoundary = new StringBuilder();
//
//            for (int col = 0; col < width; col++) {
//                MazeNode node = grid.get(row).get(col);
//
//                // Middle row
//                middleRow.append(" "); // Cell space
//                if (node.getBoundaries().contains(MazeNode.Boundary.RIGHT)) {
//                    middleRow.append(" |"); // Right wall
//                } else {
//                    middleRow.append("  "); // No right wall
//                }
//
//                // Bottom boundary
//                bottomBoundary.append("+");
//                if (node.getBoundaries().contains(MazeNode.Boundary.BOTTOM)) {
//                    bottomBoundary.append("---"); // Bottom wall
//                } else {
//                    bottomBoundary.append("   "); // No bottom wall
//                }
//            }
//
//            // Add the closing '+' for the bottom boundary
//            bottomBoundary.append("+");
//
//            // Append middle row and bottom boundary to the final output
//            sb.append(middleRow).append("\n");
//            sb.append(bottomBoundary).append("\n");
//        }
//        System.out.println(sb.toString());
//    }
//
//}