//TARASIDOU ANNA 
package maze;

import java.util.*;
import solver.UCS;
import solver.AStar;

public class Maze{
	private int N;
	private double p; 
	private Cell[][] maze;
	
	public static final String YELLOW = "\u001B[33m";
	public static final String RED = "\u001B[31m";
	public static final String BLUE = "\u001B[34m";
	public static final String RESET = "\u001B[0m";

	public Maze(int N, double p){
		this.N = N;
		this.p = p;	
		this.maze = new Cell[N][N];
		generateMaze();
	}

	// Initializing the maze  
	public void generateMaze(){
		Random rand = new Random();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean isObstacle = rand.nextDouble() < p;
				maze[i][j] = new Cell(i, j, isObstacle);
			}
		}
		maze[N-1][0].setObstacle(false);
		maze[0][N-1].setObstacle(false); 
	}
	
	// Print the maze for the two algorithms
	public void printMaze(Cell start, Cell goal) {
		System.out.print("   ");
		for (int j = 0; j < N; j++) {
			System.out.print(String.format("%3d", j));
		}
		System.out.println("\n");

		for (int i = 0; i < N; i++) {
			System.out.print(String.format("%2d ", i));

			for (int j = 0; j < N; j++) {
				String symbol;
				if (start != null && start.getX() == i && start.getY() == j) {
					symbol = BLUE + "  S" + RESET;
				} else if (goal != null && goal.getX() == i && goal.getY() == j) {
					symbol = BLUE + "  G" + RESET;
				} else if (maze[i][j].getObstacle()) {
					symbol = "1";
				} else {
					symbol = "0";
				}
				System.out.print(String.format("%3s", symbol));
			}
			System.out.println();
		}
	}
	
	// Print the maze for the Player-Mode
	public void printMaze(Cell start, Cell goal, Cell current) {
		System.out.print("   ");
		for (int j = 0; j < N; j++) {
			System.out.print(String.format("%3d", j));
		}
		System.out.println("\n");

		for (int i = 0; i < N; i++) {
			System.out.print(String.format("%2d ", i));

			for (int j = 0; j < N; j++) {
				String symbol;

				if (current != null && current.getX() == i && current.getY() == j) {
					symbol = YELLOW + "  R" + RESET;
				} else if (start != null && start.getX() == i && start.getY() == j) {
					symbol = BLUE + "  S" + RESET;
				} else if (goal != null && goal.getX() == i && goal.getY() == j) {
					symbol = BLUE + "  G" + RESET;
				} else if (maze[i][j].getObstacle()) {
					symbol = "1";
				} else {
					symbol = "0";
				}

				System.out.print(String.format("%3s", symbol));
			}
			System.out.println();
		}
	}	

	// Print maze with path for the two algorithms
	public void printMazeWithPath(Cell start, Cell goal, List<Cell> path) {
		Set<String> pathSet = new HashSet<>();
		if (path != null) {
			for (Cell cell : path) {
				pathSet.add(cell.getX() + "," + cell.getY());
			}
		}

		System.out.print("   ");
		for (int j = 0; j < N; j++) {
			System.out.print(String.format("%2d ", j));
		}
		System.out.println("\n");

		for (int i = 0; i < N; i++) {
			System.out.print(String.format("%2d ", i)); 

			for (int j = 0; j < N; j++) {
				String symbol;
				if (start != null && start.getX() == i && start.getY() == j) {
					symbol = BLUE + " S" + RESET;
				} else if (goal != null && goal.getX() == i && goal.getY() == j) {
					symbol = BLUE + " G" + RESET;
				} else if (pathSet.contains(i + "," + j)) {
					symbol = RED + " *" + RESET;
				} else if (maze[i][j].getObstacle()) {
					symbol = "1";
				} else {
					symbol = "0";
				}
				System.out.print(String.format("%2s ", symbol));
			}
			System.out.println();
		}
	}
	
	// Print maze with path for the Player-Mode
	public void printMazeWithPath(Cell start, Cell goal, Cell current, List<Cell> path) {
		Set<String> pathSet = new HashSet<>();
		if (path != null) {
			for (Cell c : path) {
				pathSet.add(c.getX() + "," + c.getY());
			}
		}

		// Print column indices
		System.out.print("   ");
		for (int j = 0; j < N; j++) {
			System.out.print(String.format("%2d ", j));
		}
		System.out.println("\n");

		// Print each row
		for (int i = 0; i < N; i++) {
			System.out.print(String.format("%2d ", i));

			for (int j = 0; j < N; j++) {
				String symbol;
				if (current != null && current.getX() == i && current.getY() == j) {
					symbol = YELLOW + " R" + RESET;
				} else if (start != null && start.getX() == i && start.getY() == j) {
					symbol = BLUE + " S" + RESET;
				} else if (goal != null && goal.getX() == i && goal.getY() == j) {
					symbol = BLUE + " G" + RESET;
				} else if (pathSet.contains(i + "," + j)) {
					symbol = RED + " *" + RESET;
				} else if (maze[i][j].getObstacle()) {
					symbol = "1";
				} else {
					symbol = "0";
				}
				System.out.print(String.format("%2s ", symbol));
			}
			System.out.println();
		}
	}


	public int heuristic(Cell current, Cell goal) {
		int dx = current.getX() - goal.getX();
		if (dx < 0) 
			dx = -dx;
		
		int dy = current.getY() - goal.getY();
		if (dy < 0) 
			dy = -dy;

		if (dx == 0 && dy == 0) {
			return 0;
		} else if (dx == 0 || dy == 0 || dx == dy) {
			return 1;
		} else if (dx == dy + 1 || dy == dx + 1) {
			return 2;
		} else {
			return 4;
		}
	}
	
	public UCS UniformCostSearch(Cell start, Cell goal){
		boolean[][] visited = new boolean[N][N];
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(new Node(start, null, 0, 0));

		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
		
		int extensions = 0;

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			int x = current.getCell().getX();
			int y = current.getCell().getY();


			if (visited[x][y]) 
				continue;
			visited[x][y] = true;
	
			extensions ++;
			
			if (current.getCell().getX() == goal.getX() && current.getCell().getY() == goal.getY()) {
				List<Cell> path = new ArrayList<>();
				int totalCost = current.getGCost();
				while (current != null) {
					path.add(0, current.getCell());
					current = current.getParent();
				}
				return new UCS(path, totalCost, extensions);
			}

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (!maze[nx][ny].getObstacle() && !visited[nx][ny]) {
						queue.add(new Node(maze[nx][ny], current, current.getGCost() + 1, 0));
					}
				}
			}

			int ax = N - 1;
			int ay = 0;
			int bx = 0;
			int by = N - 1;

			if ((x == ax && y == ay) && !visited[bx][by] && !maze[ax][ay].getObstacle() && !maze[bx][by].getObstacle()) {
				queue.add(new Node(maze[bx][by], current, current.getGCost() + 2, 0));
			}

			if ((x == bx && y == by) && !visited[ax][ay] && !maze[bx][by].getObstacle() && !maze[ax][ay].getObstacle()) {
				queue.add(new Node(maze[ax][ay], current, current.getGCost() + 2, 0));
			}
		}
		return null; 
	}
	
	public AStar AlphaStar(Cell start, Cell goal) {
		boolean[][] visited = new boolean[N][N];
		int[][] minCost = new int[N][N];
		for (int[] row : minCost)
			Arrays.fill(row, Integer.MAX_VALUE);
		minCost[start.getX()][start.getY()] = 0;

		PriorityQueue<Node> queue = new PriorityQueue<>();
		int hStart = heuristic(start, goal);
		queue.add(new Node(start, null, 0, hStart));

		int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

		int extensions = 0;

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			int x = current.getCell().getX();
			int y = current.getCell().getY();

			if (visited[x][y])
				continue;
			visited[x][y] = true;
			
			extensions++;

			if (current.getCell().getX() == goal.getX() && current.getCell().getY() == goal.getY()) {
				List<Cell> path = new ArrayList<>();
				int totalCost = current.getGCost();
				while (current != null) {
					path.add(0, current.getCell());
					current = current.getParent();
				}
				return new AStar(path, totalCost, extensions);
			}

			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (!maze[nx][ny].getObstacle()) {
						int gCost = current.getGCost() + 1;
						if (gCost < minCost[nx][ny]) {
							minCost[nx][ny] = gCost;
							int hCost = heuristic(maze[nx][ny], goal);
							queue.add(new Node(maze[nx][ny], current, gCost, hCost));
						}
					}
				}
			}

			int ax = N - 1, ay = 0;
			int bx = 0, by = N - 1;

			if (x == ax && y == ay && !maze[ax][ay].getObstacle() && !maze[bx][by].getObstacle()) {
				int gCost = current.getGCost() + 2;
				if (gCost < minCost[bx][by]) {
					minCost[bx][by] = gCost;
					int hCost = heuristic(maze[bx][by], goal);
					queue.add(new Node(maze[bx][by], current, gCost, hCost));
				}
			}

			if (x == bx && y == by && !maze[bx][by].getObstacle() && !maze[ax][ay].getObstacle()) {
				int gCost = current.getGCost() + 2;
				if (gCost < minCost[ax][ay]) {
					minCost[ax][ay] = gCost;
					int hCost = heuristic(maze[ax][ay], goal);
					queue.add(new Node(maze[ax][ay], current, gCost, hCost));
				}
			}
		}
		return null;
	}
	
	public Cell getCell(int x, int y) {
		return maze[x][y];
	}

	public int getSize() {
		return N;
	}

}