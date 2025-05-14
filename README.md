# 🤖 Robot Maze Game

A terminal-based maze game implemented in **Java**, where the player must navigate from a starting point to a goal through a randomly generated grid-based maze filled with obstacles. The maze can be solved either manually or using classic pathfinding algorithms.

---

## 🎮 Features

- 🧱 **Maze Generation**  
  - User-defined size (N x N)  
  - Random obstacle placement (`1`) based on probability (0.0 - 1.0) also defined by the user

- 🚦 **Start and Goal Setup**  
  - Define start (`S`) and goal (`G`) positions manually  
  - Input validation ensures you don’t land on obstacles

- 🧭 **Solving Options**
  1. 📦 **Uniform Cost Search (UCS) Algorithm**
  2. 🧠 **A\* Search Algorithm**
  3. 🔁 **Both UCS and A\***
  4. 👤 **Manual Play Mode**
     - Move with:
       - Arrows: `W`, `A`, `S`, `D`
       - Diagonals: `I`, `O`, `K`, `L`
     - Current position shown as `R`
     - Path traced with `*`
     - Steps counted 🧮
     - Quit anytime with `q`

- 🔁 **Replayability**  
  - Restart with new start/goal in the **same maze**  
  - Or generate a **completely new maze**

- 📊 **Statistics & Feedback**
  - Shows:
    - 🗺️ Solved maze with path
    - 💰 Total cost
    - 📦 Number of nodes expanded
    - ⏱️ Execution time (in ms)

- ☕ **Built in Java**

---

## ⚙️ Technical Features & Design Overview

### 🧠 Algorithmic & Functional Highlights

- **🔄 Method Overloading**  
  The `Maze` class uses multiple overloaded `printMaze(...)` methods to support different display modes:
  - Maze only  
  - Maze with start/goal  
  - Maze with current player position  
  - Maze with path (for algorithms or manual play)

- **🎯 Search Algorithms**  
  - **Uniform Cost Search (UCS)** – cost-based optimal pathfinding  
  - **A\*** – heuristic-based search 

- **⏱️ Performance Metrics**  
  - Execution time in milliseconds (ms) for both UCS and A*  
  - Total path cost  
  - Number of node expansions

- **📥 User Interaction & Input Validation**  
  - Repeated input prompts until valid values are given  
  - Validation for:
    - Maze size  
    - Obstacle probability  
    - Start/goal coordinates  

- **🕹️ Player-Controlled Mode**  
  - Free-form movement via keyboard:
    - Arrows: `W`, `A`, `S`, `D`  
    - Diagonal: `I`, `O`, `K`, `L`  
  - Obstacles and bounds are handled gracefully  
  - Player path is visualized with `*`, and current position with `R`  
  - Tracks total number of steps

---

### 🧩 Code Structure & Design

- **Modular Design**  
  - `RobotMaze` handles game logic and user interface  
  - `PlayerMode` manages manual player control  
  - `Maze` handles grid and data logic

- **Encapsulation**  
  - Cell properties are accessed through getters

---

### 💬 User Experience

- Clear menus and instructions  
- Friendly error handling and prompts  
- Axis labeling to assist movement  
- Option to quit (`q`) or restart (`y/n`) at key stages
- Includes optical effects for better user experience

---

### 📦 Summary Table

| Category           | Features |
|--------------------|----------|
| 🔍 Algorithms       | UCS, A\*, with cost and time tracking |
| 🎮 Gameplay         | Manual movement mode with feedback |
| 🔁 Game Flow        | Play again with same or new maze |
| 🧱 Structure        | Method overloading, modular code |
| 📊 Output           | Path display, stats, step tracking |
| 🛠️ Technology       | Pure Java, interactive CLI |

---

## ▶️ How to Run

```bash
javac *.java
java RobotMaze
