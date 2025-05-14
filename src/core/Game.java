//TARASIDOU ANNA 
package core;

import maze.Maze;
import maze.Cell;

public class Game {

    public static void main(String[] args) {
        System.out.println("Welcome to Maze Game!");

        int totalPoints = 0; // Αρχικοποιούμε το σκορ του παίκτη

        for (int level = 1; level <= 3; level++) {
            System.out.println("\n--- Level " + level + " ---");

            int size = 5 + level * 2;
            double p = 0.2;
            Maze maze = new Maze(size, p);
            maze.generateMaze();

            Cell start = maze.getCell(0, 0);
            Cell goal = maze.getCell(size - 1, size - 1);

            PlayerMode playerMode = new PlayerMode();
            playerMode.resetInstructions();

            boolean levelComplete = playerMode.solveManually(maze, start, goal);
            if (!levelComplete) {
                System.out.println("\nGame Over. You exited the game.");
                break;
            }

            // Υπολογίζουμε πόντους ανάλογα με τα βήματα
            int points = playerMode.getSteps(); // Κάθε βήμα κοστίζει -1 πόντο, με μέγιστο 100 πόντους
            totalPoints += points; // Προσθέτουμε πόντους στο συνολικό σκορ

            System.out.println("\nLevel " + level + " complete! You earned " + points + " points.");
        }

        System.out.println("\n🎉 Thank you for playing!");
        System.out.println("Total points: " + totalPoints);
    }
}
