package game;

import game.battle.FinalBattle;
import game.exceptions.GameException;
import game.exceptions.KingdomCollapsedException;
import game.setup.*;
import game.managers.*;
import game.ui.*;
import game.events.*;
import game.rules.*;
import game.util.Outcome;

import java.util.List;
import java.util.Scanner;

public class GameEngine {

    private final Scanner scanner;
    private final Kingdom playerKingdom;
    private final Kingdom enemyKingdom;

    private final RebellionManager rebellionManager;
    private final TaxManager taxManager;
    private final RuleEngine ruleEngine;

    private final BuildingManager buildingManager;
    private final UnitTrainingManager unitTrainingManager;
    private final StatusPrinter statusPrinter;
    private final RandomEventManager randomEventManager;

    private final GameSession session;

    // -----------------------------------
    // Main
    // -----------------------------------
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.start();
    }

    // -----------------------------------
    // Constructor
    // -----------------------------------
    public GameEngine() {
        scanner = new Scanner(System.in);
        IntroPrinter.printIntro();
        Difficulty difficulty = DifficultyManager.askDifficulty(scanner);

        enemyKingdom = KingdomFactory.createKingdom(difficulty);
        playerKingdom = KingdomFactory.createKingdom(scanner);

        session = new GameSession(difficulty);

        buildingManager = new BuildingManager(scanner, playerKingdom);
        unitTrainingManager = new UnitTrainingManager(scanner, playerKingdom);
        statusPrinter = new StatusPrinter(playerKingdom);
        rebellionManager = new RebellionManager(playerKingdom, difficulty);
        taxManager = new TaxManager();
        ruleEngine = new RuleEngine(taxManager);
        randomEventManager = new RandomEventManager(difficulty);
    }

    // -----------------------------------
    // Start Loop
    // -----------------------------------
    public void start() {
        System.out.println("\nWelcome, Ruler of " + playerKingdom.getName() + "!");
        System.out.println("Prepare your Kingdom for the coming war...\n");

        while (session.isRunning()) {
            MenuPrinter.printMainMenu(session.getCurrentDay());
            int choice = InputManager.getInt(scanner, "\nYour choice: ");
            handleMenuChoice(choice);
        }

        System.out.println("\nThanks for playing!");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> statusPrinter.printStatus();
            case 2 -> runBuildingMenu();
            case 3 -> runPlayerAction(unitTrainingManager::trainUnit);
            case 4 -> runPlayerAction(this::setTaxRate);
            case 5 -> nextDay();
            case 6 -> RuleMenuPrinter.manageCustomRule(scanner, ruleEngine);
            case 7 -> session.endGame();
            default -> System.out.println("Invalid choice!");
        }
    }

    private void runBuildingMenu() {
        try {
            buildingManager.buildBuilding();
        } catch (GameException e) {
            System.out.println("\n[Game Error] " + e.getMessage());
        }
    }

    private void runPlayerAction(Runnable action) {
        try {
            action.run();
        } catch (GameException e) {
            System.out.println("\n[Game Error] " + e.getMessage());
        }
    }

    // -----------------------------------
    // Set Tax
    // -----------------------------------
    private void setTaxRate() {
        int newRate = InputManager.getInt(scanner, "\nEnter new tax rate (0 - 25): ");
        taxManager.updateTaxRate(newRate);
    }

    // -----------------------------------
    // Next Day
    // -----------------------------------
    private void nextDay() {
        System.out.println("\nA new day begins...");
        try {
            List<String> dailyMessages = playerKingdom.applyDailyChanges(taxManager.getTaxRate());
            printDailyMessages(dailyMessages);

            rebellionManager.checkRebellion();

            randomEventManager.tryTrigger(playerKingdom);
            ruleEngine.applyRules(playerKingdom);

            session.advanceDay();
            System.out.println("\nDay: " + session.getCurrentDay());

            if (session.isBattleDue()) {
                finalBattle();
                session.endGame();
                return;
            }

            statusPrinter.printStatus();
        } catch (KingdomCollapsedException e) {
            System.out.println("\n" + e.getMessage());
            System.out.println("GAME OVER");
            session.endGame();
        }
    }

    private void printDailyMessages(List<String> messages) {
        for (String message : messages) {
            if (message != null) {
                System.out.println(message);
            }
        }
    }

    // -----------------------------------
    // Final Battle
    // -----------------------------------
    private void finalBattle() {
        FinalBattle fb = new FinalBattle(playerKingdom, enemyKingdom);

        Outcome<Integer> outcome = fb.startBattle();
        boolean playerWon = outcome.isSuccess();
        System.out.println("\n===== BATTLE RESULT =====");
        if (playerWon) {
            System.out.println("VICTORY! You defeated the Dark Empire!");
        } else {
            System.out.println("DEFEAT... Your kingdom falls under enemy rule...");
        }
        System.out.println("\n===== GAME OVER =====\n");
    }
}
