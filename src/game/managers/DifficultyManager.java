package game.managers;

import java.util.Scanner;
public class DifficultyManager {
    public static int askDifficulty(Scanner scanner){
        System.out.println("Select a difficulty:" +
                "\n1-Easy" +
                "\n2-Normal" +
                "\n3-Hard");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice < 1 || choice > 3){
            System.out.println("Invalid input! Starting with Normal difficulty.");
            return 2;
        }
        return choice;
    }
}
