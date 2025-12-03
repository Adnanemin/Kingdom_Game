package game.ui;

public class MenuPrinter {

    public static void printMainMenu(int currentDay) {
        System.out.println("\n====== MAIN MENU ======");
        System.out.println("Day: " + currentDay);
        System.out.println("1. View Kingdom Status");
        System.out.println("2. Build a Building");
        System.out.println("3. Train Units");
        System.out.println("4. Set Tax Rate");
        System.out.println("5. Next Day");
        System.out.println("6. Custom Rules");
        System.out.println("7. Exit");
    }
}