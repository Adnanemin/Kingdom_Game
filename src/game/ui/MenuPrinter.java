package game.ui;

public class MenuPrinter {

    public static void printMainMenu(int currentDay) {
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