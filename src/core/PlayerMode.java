//TARASIDOU ANNA 
package core;

import java.util.*;
import maze.Cell;
import maze.Maze;

public class PlayerMode {

	public static final String CYAN = "\u001B[36m";
	public static final String RED = "\u001B[31m";
	public static final String RESET = "\u001B[0m";

	private static boolean instructionsShown = false; 
	private int steps;
    
	public boolean solveManually(Maze maze, Cell start, Cell goal) {
		Scanner scanner = new Scanner(System.in);
		Cell current = start;
		List<Cell> path = new ArrayList<>();
		path.add(current);

		while (true) {
			maze.printMaze(start, goal, current);

			if (!instructionsShown) {
				System.out.println(CYAN);
				System.out.println("\nMove using:");
				System.out.println("W(up), S(down), A(left), D(right)");
				System.out.println("I(up-left), O(up-right), K(down-left), L(down-right)\n");
				System.out.println("T (teleport) only when posible - [down-left or up-right corners]");
				System.out.println("Type q to quit.");
				System.out.println(RESET);
				instructionsShown = true;
			}

			System.out.print("Your move: ");
			String input = scanner.nextLine().toLowerCase().trim();

			if (input.equals("q")) {
				System.out.println("You quit the game.");
				return false;
			}

			int dx = 0, dy = 0;
			switch (input) {
				case "w": dx = -1; dy = 0; break;
				case "s": dx = 1; dy = 0; break;
				case "a": dx = 0; dy = -1; break;
				case "d": dx = 0; dy = 1; break;
				case "i": dx = -1; dy = -1; break;
				case "o": dx = -1; dy = 1; break;
				case "k": dx = 1; dy = -1; break;
				case "l": dx = 1; dy = 1; break;
				case "t":
					boolean teleported = false;

					// bottom-left to top-right
					if (current.getX() == maze.getSize() - 1 && current.getY() == 0) {
						Cell target = maze.getCell(0, maze.getSize() - 1);
						if (!target.getObstacle()) {
							current = target;
							path.add(current);
							steps++;
							System.out.println(CYAN + "--- Teleported to top-right corner! ---\n" + RESET);
							teleported = true;
						}
					}

					// top-right to bottom-left
					else if (current.getX() == 0 && current.getY() == maze.getSize() - 1) {
						Cell target = maze.getCell(maze.getSize() - 1, 0);
						if (!target.getObstacle()) {
							current = target;
							path.add(current);
							steps++;
							System.out.println(CYAN + "--- Teleported to bottom-left corner! ---\n" + RESET);
							teleported = true;
						}
					}

					if (!teleported) {
						System.out.println(RED + "--- Teleport not allowed from here! ---\n" + RESET);
					}
					continue;
				default:
					System.out.println(RED);
					System.out.println("Invalid input.");
					System.out.println(RESET);
					continue;
			}

			int newX = current.getX() + dx;
			int newY = current.getY() + dy;

			if (newX >= 0 && newX < maze.getSize() && newY >= 0 && newY < maze.getSize()) {
				Cell nextCell = maze.getCell(newX, newY);
				if (nextCell.equals(goal)) {
			        current = nextCell;
			        path.add(current);
			        steps++;
			    }else if (!nextCell.getObstacle()) {
			        current = nextCell;
			        path.add(current);
			        steps++;
			    }else {
			            System.out.println(RED);
			            System.out.println("--- Obstacle! Cannot move there. ---\n");
			            System.out.println(RESET);
			    }
			} else {
				System.out.println(RED);
				System.out.println("--- Out of bounds! Try a different direction. ---\n");
				System.out.println(RESET);

			}

			if (current.equals(goal)) {
				System.out.println("\nHere is the path you followed!");
				maze.printMazeWithPath(start, goal, current, path);
				System.out.println("\nYou reached the goal in " + steps + " steps!");
				return true;
			}
		}
	}
	
	public int getSteps() {
		return steps;
	}
	
	public void resetInstructions() {
		instructionsShown = false;
	}

}
