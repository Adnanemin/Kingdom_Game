package game.ui;

import game.GameSession;

public class MenuPrinter {

    public static void printMainMenu(int currentDay) {
        System.out.println("\n===== DAY " + currentDay + " / " + GameSession.BATTLE_DAY + " =====");

        String[] menuItems = {
                "View Kingdom Status",
                "Build a Building",
                "Train Units",
                "Set Tax Rate",
                "Next Day",
                "Custom Rules",
                "Exit"
        };
        for(int i = 0; i < menuItems.length; i++) {
            System.out.println((i + 1) + ". " + menuItems[i]);
        }
    }
}