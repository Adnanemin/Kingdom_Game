package game.setup;

import game.Kingdom;
import game.managers.InputManager;

import java.util.Scanner;

public class KingdomFactory {

    public static Kingdom createKingdom(Scanner scanner) {
        String name = InputManager.getString(scanner, "\nName your Kingdom: ");

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
