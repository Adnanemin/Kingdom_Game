package game.managers;

import java.util.Scanner;

public class InputManager {

    public static int getInt(Scanner scanner, String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Try again: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public static String getString(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}