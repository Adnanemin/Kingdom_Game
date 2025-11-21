package game;

import game.buildings.*;
import game.units.*;

import java.util.Scanner;

public class GameEngine {

    private final Scanner scanner;
    private final Kingdom playerKingdom;
    private final Kingdom enemyKingdom;
    private double taxRate = 0.15;
    private boolean running = true;
    private int currentDay = 1;
    private final int battleDay = 30;
    //-------------------------
    // Main
    //-------------------------
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.start();
    }

    //-------------------------
    // Constructor
    //-------------------------
    public GameEngine() {
        // Player Kingdom
        scanner = new Scanner(System.in);

        System.out.println("Name your Kingdom: ");
        String name = scanner.nextLine();

        if (name.isBlank()) {
            name = "Player Kingdom";
        }

        playerKingdom = new Kingdom(name, false);

        // --------- Enemy kingdom as final boss ---------
        enemyKingdom = new Kingdom("Dark Empire", true);

        // Simple but strong enemy army (can tweak numbers later)
        for (int i = 0; i < 10; i++) enemyKingdom.getArmy().addUnit(new Soldier());
        for (int i = 0; i < 5; i++) enemyKingdom.getArmy().addUnit(new Archer());
        for (int i = 0; i < 5; i++) enemyKingdom.getArmy().addUnit(new Cavalry());
    }

    //-------------------------
    // Start Loop
    //-------------------------
    public void start() {
        System.out.println("\nWelcome, Ruler of " + playerKingdom.getName() + "!");
        System.out.println("Prepare your Kingdom for the coming war...\n");

        while (running) {
            printMenu();
            int choice = getIntInput("Your choice: ");
            handleMenuChoice(choice);
        }

        System.out.println("Thanks for playing!");
    }

    //-------------------------
    // Menu
    //-------------------------
    private void printMenu() {
        System.out.println("\n====== MAIN MENU ======");
        System.out.println("1. View Kingdom Status");
        System.out.println("2. Build a Building");
        System.out.println("3. Train Units");
        System.out.println("4. Set Tax Rate");
        System.out.println("5. Next Day");
        System.out.println("6. Exit");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> showStatus();
            case 2 -> buildBuilding();
            case 3 -> trainUnit();
            case 4 -> setTaxRate();
            case 5 -> nextDay();
            case 6 -> running = false;
            default -> System.out.println("Invalid choice!");
        }
    }

    //-------------------------
    // Status
    //-------------------------
    private void showStatus() {
        System.out.println("\n====== KINGDOM STATUS ======");
        System.out.println("Kingdom: " + playerKingdom.getName());
        System.out.println("Gold: " + playerKingdom.getGold());
        System.out.println("Food: " + playerKingdom.getFood());
        System.out.println("Population: " + playerKingdom.getPopulation());
        System.out.println("Happiness: " + playerKingdom.getHappiness());
        System.out.println("Army Power: " + playerKingdom.getArmy().getTotalPower());
        System.out.println("Tax Rate: " + (int)(taxRate * 100) + "%");

        System.out.println("\nBuildings:");
        if (playerKingdom.getBuildings().isEmpty()) {
            System.out.println("None");
        } else {
            for (Building b : playerKingdom.getBuildings()) {
                System.out.println("- " + b.getName());
            }
        }
    }

    //-------------------------
    // Build a Building
    //-------------------------
    private void buildBuilding() {
        System.out.println("\n====== BUILDING MENU ======");
        System.out.println("Current gold: " + playerKingdom.getGold());
        System.out.println("1. Farm (30)");
        System.out.println("2. Small House (20)");
        System.out.println("3. Large House (40)");
        System.out.println("4. Barracks (40)");
        System.out.println("5. Stable (60)");
        System.out.println("6. Bar (30)");
        System.out.println("7. Wall (70)");
        System.out.println("8. Cancel");

        int choice = getIntInput("Select building: ");
        Building newBuilding = null;

        switch (choice) {
            case 1 -> newBuilding = new Farm();
            case 2 -> newBuilding = new SmallHouse();
            case 3 -> newBuilding = new LargeHouse();
            case 4 -> {
                if (playerKingdom.hasBuilding(Barracks.class)) {
                    System.out.println("You already have a Barracks!");
                    return;
                }
                newBuilding = new Barracks();
            }
            case 5 -> {
                if (playerKingdom.hasBuilding(Stable.class)) {
                    System.out.println("You already have a Stable!");
                    return;
                }
                newBuilding = new Stable();
            }
            case 6 -> newBuilding = new Bar();
            case 7 -> {
                if (playerKingdom.hasBuilding(Wall.class)) {
                    System.out.println("You already have a Wall! You cannot build a second one.");
                    return;
                }
                newBuilding = new Wall();
            }
            case 8 -> { return; }
            default -> {
                System.out.println("Invalid option!");
                return;
            }
        }

        if (playerKingdom.getGold() < newBuilding.getCost()) {
            System.out.println("Not enough gold!");
            return;
        }

        playerKingdom.changeGold(-newBuilding.getCost());
        playerKingdom.addBuilding(newBuilding);

        System.out.println(newBuilding.getName() + " successfully built!");
    }

    //-------------------------
    // Train Units
    //-------------------------
    private void trainUnit() {
        System.out.println("\n====== TRAIN UNITS ======");
        System.out.println("Current gold: " + playerKingdom.getGold());
        System.out.println("Population available: " + playerKingdom.getPopulation());
        System.out.println("1. Soldier (10 gold, 1 pop)");
        System.out.println("2. Archer (10 gold, 1 pop)");
        System.out.println("3. Cavalry (20 gold, 1 pop)");
        System.out.println("4. Cancel");

        int choice = getIntInput("Select unit: ");
        Unit unit = null;
        int cost = 0;

        switch (choice) {
            case 1 -> {
                if (!playerKingdom.hasBuilding(Barracks.class)) {
                    System.out.println("You need a Barracks to train Soldiers!");
                    return;
                }
                unit = new Soldier();
                cost = unit.getCost();
            }
            case 2 -> {
                if (!playerKingdom.hasBuilding(Barracks.class)) {
                    System.out.println("You need a Barracks to train Archers!");
                    return;
                }
                unit = new Archer();
                cost = unit.getCost();
            }
            case 3 -> {
                if (!playerKingdom.hasBuilding(Stable.class)) {
                    System.out.println("You need a Stable to train Cavalry!");
                    return;
                }
                unit = new Cavalry();
                cost = unit.getCost();
            }
            case 4 -> { return; }
            default -> {
                System.out.println("Invalid choice!");
                return;
            }
        }

        if (playerKingdom.getGold() < cost) {
            System.out.println("Not enough gold!");
            return;
        }

        if (playerKingdom.getPopulation() < 1) {
            System.out.println("Not enough population!");
            return;
        }

        playerKingdom.changeGold(-cost);
        playerKingdom.changePopulation(-1);
        playerKingdom.getArmy().addUnit(unit);

        System.out.println(unit.getName() + " trained!");
    }

    //-------------------------
    // Set Tax Rate
    //-------------------------
    private void setTaxRate() {
        int newRate = getIntInput("Enter new tax rate (0–25): ");

        if (newRate < 0 || newRate > 25) {
            System.out.println("Tax rate must be between 0 and 25!");
            return;
        }

        taxRate = newRate / 100.0;
        System.out.println("Tax rate updated to " + newRate + "%");
    }

    //-------------------------
    // Final Battle
    //-------------------------
    public void finalBattle(){
        System.out.println("\n===== FINAL BATTLE BEGINS! =====");
        System.out.println("The Dark Empire begins its invasion...\n");

        int playerPower = playerKingdom.getTotalArmyPower();
        int enemyPower = enemyKingdom.getTotalArmyPower();

        System.out.println("Your initial power: " + playerPower);
        System.out.println("Enemy initial power: " + enemyPower);

        // Wall bonus
        int wallBonus = 0;
        for(Building b : playerKingdom.getBuildings()){
            if(b instanceof Wall){
                Wall w = (Wall) b;
                wallBonus = w.getDefenceBonus();
                break;
            }
        }
        if (wallBonus > 0){
            System.out.println("\n🛡 Your Wall reinforces your defenses! (+" + wallBonus + " power)");
            playerPower += wallBonus;
        }
        int happiness = playerKingdom.getHappiness();
        if (happiness > 70){
            System.out.println("High morale inspires your troops! (+6 attack damage)");
            playerPower += 6;
        }else if(happiness < 30) {
            System.out.println("Low morale weakens your army... (-6 attack damage)");
            playerPower -= 6;
        }
        //-------------------------------
        // 4) Final power summary
        //-------------------------------
        System.out.println("\n===== FINAL POWER =====");
        System.out.println("Your final battle power: " + playerPower);
        System.out.println("Enemy final battle power: " + enemyPower);

        System.out.println("\n===== BATTLE RESULT =====");
        if(playerPower >= enemyPower){
            System.out.println("VICTORY! You defeated the Dark Empire!");
            System.out.println("Your kingdom survives and prospers!");
        }else{
            System.out.println("DEFEAT... The Dark Empire overwhelmed your forces.");
            System.out.println("Your kingdom falls under enemy rule...");
        }
        System.out.println("\n===== GAME OVER =====\n");
    }

    //-------------------------
    // Next Day
    //-------------------------
    private void nextDay() {
        System.out.println("\nA new day begins...");
        playerKingdom.applyDailyChanges(taxRate);
        currentDay++;
        System.out.println("Day: " + currentDay);
        if (currentDay >= battleDay){
            finalBattle();
            running = false;
            return;
        }
        showStatus();
    }

    //-------------------------
    // Input Helper
    //-------------------------
    private int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Try again: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}