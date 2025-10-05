## 🤖 Robot Maze Game
#### Author: Anna Tarasidou

A terminal-based maze game implemented in **Java**, where the player must navigate from a starting point to a goal through a randomly generated grid-based maze with obstacles. The maze can be solved manually or using pathfinding algorithms.

---
### Features

#### 🧱 Maze Generation
- User-defined size (N x N)
- Random obstacle placement based on user-defined probability (0.0 - 1.0)

#### 🚦 Start and Goal Setup
- Manual input of start (`S`) and goal (`G`) positions
- Validation prevents placing start or goal on obstacles

#### 🧭 Solving Options
1. **Uniform Cost Search (UCS) Algorithm**
2. **AStar Search Algorithm**
   - Uses a custom heuristic that:
     - Estimates cost from the current cell to the goal
     - Supports 8-directional movement (horizontal, vertical, diagonal)
     - Accounts for teleportation between bottom-left and top-right corners if unobstructed
     - Is admissible (never overestimates the actual path cost)
3. **Both UCS and AStar**
4. **Manual Play Mode**
   - Keyboard controls:
     - Movement: `W`, `A`, `S`, `D`
     - Diagonals: `I`, `O`, `K`, `L`
     - Teleportation with `T` (if allowed)
     - Quit with `q`
   - Visual feedback:
     - Current position represented as `R`
     - Path so far represented as `*`
   - Step counter
   - Instructions appear only once per session
5. **Game Mode**
   - Levels 1-3
   - Tracks points

#### 🔁 Replayability
- Option to restart with a new start/goal in the same maze
- Option to generate a new maze
- Instructions can be reset programmatically (`resetInstructions()`)

#### 📊 Statistics & Feedback
- Solved maze visualized with path
- Total cost of the path
- Number of nodes expanded
- Execution time in milliseconds

### ⚙ Technical Highlights
- Colored messages using ANSI escape codes for feedback
- Obstacle and boundary detection to prevent invalid moves
- Teleportation mechanism between opposite corners
- Automatic detection of goal reached
- Dynamic display system with overloaded `printMaze(...)` and `printMazeWithPath(...)` methods

---
### ▶️ How to Compile & Run
To run the project from terminal:
#### 🔧 Compile

Compile all `.java` files:

```bash
javac src/maze/*.java src/solver/*.java src/core/*.java
```
#### 🚀 Run
Choose one of the following modes:

🧠 Run with Solvers (UCS / A*)
```bash
java -cp src core.RobotMaze
```
🎮 Run the Maze Game (Manual Player Mode with Levels)

```bash
java -cp src core.Game
```
### 📂 Project Structure
```bash
src/
├── core/
│ ├── PlayerMode.java // Manual movement logic
│ └── RobotMaze.java // Main interface & algorithm selection
│
├── maze/
│ ├── Cell.java // Grid cell definition
│ ├── Maze.java // Maze generation and utilities
│ └── Node.java // Helper for search algorithms
│
├── solver/
├── AStar.java // A* algorithm implementation
└── UCS.java // Uniform Cost Search implementation
