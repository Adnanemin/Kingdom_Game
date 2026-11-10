package game.setup;

import game.Kingdom;

import java.util.Scanner;

public class KingdomFactory {

    public static Kingdom createKingdom(Scanner scanner) {
        System.out.println("Name your Kingdom: ");
        String name = scanner.nextLine();

        if (name.isBlank()) {
            name = "My Kingdom";
        }
        return new Kingdom(name);
    }

    public static Kingdom createKingdom(Difficulty difficulty) {
        Kingdom enemy = new Kingdom("Dark Empire");
        difficulty.configureEnemyArmy(enemy);
        return enemy;
    }
}
