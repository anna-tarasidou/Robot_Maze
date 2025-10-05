//ANNA TARASIDOU
package core;
import java.util.*;

import maze.Cell;
import maze.Maze;
import solver.AStar;
import solver.UCS;


public class RobotMaze {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String playNewMaze;
		String playSameMaze;


		do {
			System.out.println("\n===== GAME START =====\n");
			System.out.println("Initializing maze...");

			int N = -1;
			while (N <= 0) {
				System.out.print("Give the size of the maze: ");
				if (scanner.hasNextInt()) {
					N = scanner.nextInt();
					if (N <= 0) {
						System.out.println("The size must be positive!");
					}
				} else {
					System.out.println("Please enter a valid integer!");
					scanner.next(); 
				}
			}

			double p = -1.0;
			while (p < 0.0 || p > 1.0) {
				System.out.print("Give the probability of obstacle (0.0 - 1.0): ");
				if (scanner.hasNextDouble()) {
					p = scanner.nextDouble();
					if (p < 0.0 || p > 1.0) {
						System.out.println("The probability must be between 0.0 and 1.0!");
					}
				} else {
					System.out.println("Please enter a valid number!");
					scanner.next(); 
				}
			}

			Maze maze = new Maze(N, p);
			System.out.println("\nGenerated Maze: \n");
			maze.printMaze(null, null);

			do {
				Cell start_cell = null;
				while (true) {
					System.out.print("\nGive the starting coordinates (x y): ");

					if (scanner.hasNextInt()) {
						int sx = scanner.nextInt();
						if (scanner.hasNextInt()) {
							int sy = scanner.nextInt();

							if (sx >= 0 && sx < N && sy >= 0 && sy < N) {
								if (!maze.getCell(sx, sy).getObstacle()) {
									start_cell = maze.getCell(sx, sy);
									break;
								} else {
									System.out.println("This cell is an obstacle. Try again.\n");
								}
							} else {
								System.out.println("Coordinates out of bounds. Try again.");
							}
						} else {
							System.out.println("You must enter two integers. Try again.");
							scanner.next(); 
						}
					} else {
						System.out.println("You must enter two integers. Try again.");
						scanner.next(); 
					}
				}

				Cell goal_cell = null;
				while (true) {
					System.out.print("Give goal coordinates (x y): ");

					if (scanner.hasNextInt()) {
						int gx = scanner.nextInt();
						if (scanner.hasNextInt()) {
							int gy = scanner.nextInt();

							if (gx >= 0 && gx < N && gy >= 0 && gy < N) {
								if (!maze.getCell(gx, gy).getObstacle()) {
									goal_cell = maze.getCell(gx, gy);
									break;
								} else {
									System.out.println("This cell is an obstacle. Try again.");
								}
							} else {
								System.out.println("Coordinates out of bounds. Try again.");
							}
						} else {
							System.out.println("You must enter two integers. Try again.");
							scanner.next(); 
						}
					} else {
						System.out.println("You must enter two integers. Try again.");
						scanner.next(); 
					}
				}


				System.out.println("\nMaze with start and goal: \n");
				maze.printMaze(start_cell, goal_cell);

				int choice = -1;
				System.out.println("Do you want to solve the maze with: ");
				System.out.println("UCS (1), A* (2), both (3) or do you want to solve it yourself (4)?");

				while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {			
					if (scanner.hasNextInt()) {
						choice = scanner.nextInt();
					} else {
						System.out.println("Choose 1, 2, 3 or 4!");
						scanner.next();
					}
				}

				if (choice == 1 || choice == 3) {
					long startTime = System.nanoTime();
					UCS resultUCS = maze.UniformCostSearch(start_cell, goal_cell);
					long endTime = System.nanoTime();
					double durationMs = (endTime - startTime) / 1_000_000.0;

					printResults("UCS", resultUCS, start_cell, goal_cell, maze);
					System.out.printf("Execution time (UCS): %.3f ms\n", durationMs);
				}

				if (choice == 2 || choice == 3) {
					long startTime = System.nanoTime();
					AStar resultAStar = maze.AlphaStar(start_cell, goal_cell);
					long endTime = System.nanoTime();
					double durationMs = (endTime - startTime) / 1_000_000.0;

					printResults("A*", resultAStar, start_cell, goal_cell, maze);
					System.out.printf("Execution time (A*): %.3f ms\n", durationMs);
				}

				if (choice == 4) {
					PlayerMode play = new PlayerMode(); 
					play.solveManually(maze, start_cell, goal_cell); 
				}

				do {
					System.out.print("\nDo you want to try different start/goal in the same maze? (y/n): ");
					playSameMaze = scanner.next().trim().toLowerCase();
				} while (!playSameMaze.equals("y") && !playSameMaze.equals("n"));

			} while (playSameMaze.equals("y"));

			do {
				System.out.print("\nDo you want to play with a new maze? (y/n): ");
				playNewMaze = scanner.next().trim().toLowerCase();
				scanner.nextLine();
			} while (!playNewMaze.equals("y") && !playNewMaze.equals("n"));

		} while (playNewMaze.equals("y"));

		scanner.close();
		System.out.println("Thank you for playing!");
	}

	private static void printResults(String algoName, Object result, Cell start, Cell goal, Maze maze) {
		if (result == null) {
			System.out.println("\nNo " + algoName + " path found.");
			return;
		}

		List<Cell> path;
		int cost, extensions;

		if (result instanceof UCS) {
			path = ((UCS) result).getPath();
			cost = ((UCS) result).getCost();
			extensions = ((UCS) result).getExtensions();
		} else if (result instanceof AStar) {
			path = ((AStar) result).getPath();
			cost = ((AStar) result).getCost();
			extensions = ((AStar) result).getExtensions();
		} else {
			System.out.println("Unknown result type.");
			return;
		}

		if (path == null) {
			System.out.println("\nNo " + algoName + " path found.");
			return;
		}

		System.out.println("\nMaze with " + algoName + " path:\n");
		maze.printMazeWithPath(start, goal, path);

		System.out.println("\nOptimal " + algoName + " Path: ");
		for (Cell cell : path) {
			System.out.println("(" + cell.getX() + ", " + cell.getY() + ")");
		}
		System.out.println("\nTotal cost of the " + algoName + " path: " + cost);
		System.out.println("\nNumber of extensions: " + extensions);
	}
}