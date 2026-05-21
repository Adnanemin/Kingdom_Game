package game.managers;

import game.setup.Difficulty;

import java.util.Scanner;

public class DifficultyManager {

    public static Difficulty askDifficulty(Scanner scanner) {
        System.out.println("\n          DIFFICULTY\n===============================");
        String[] menuItems = {"Easy", "Normal", "Hard"};

        for (int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice < 1 || choice > 3) {
            System.out.println("Invalid input! Starting with Normal difficulty.");
            return Difficulty.NORMAL;
        }
        return Difficulty.fromMenuChoice(choice);
    }
}
