package game;

import game.battle.FinalBattle;
import game.exceptions.*;
import game.setup.*;
import game.managers.*;
import game.ui.*;
import game.events.*;
import game.rules.*;

import java.util.Scanner;

public class GameEngine {

    private final Scanner scanner;
    private final Kingdom playerKingdom;
    private final Kingdom enemyKingdom;


    private final RebellionManager rebellionManager;
    private final TaxManager taxManager;
    private final RuleEngine ruleEngine;

    private boolean running = true;

    private int currentDay = 1;
    private static final int battleDay = 30;

    private final BuildingManager buildingManager;
    private final UnitTrainingManager unitTrainingManager;
    private final StatusPrinter statusPrinter;
    private final RandomEventManager randomEventManager;

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
        //Intro printer
        IntroPrinter.printIntro();
        // Ask difficulty
        int difficulty = DifficultyManager.askDifficulty(scanner);

        // Create kingdoms using factory
        enemyKingdom = KingdomFactory.createKingdom(difficulty);
        playerKingdom = KingdomFactory.createKingdom(scanner);

        // Managers
        buildingManager = new BuildingManager(scanner, playerKingdom);
        unitTrainingManager = new UnitTrainingManager(scanner, playerKingdom);
        statusPrinter = new StatusPrinter(playerKingdom);
        rebellionManager = new RebellionManager(playerKingdom, difficulty);
        taxManager = new TaxManager();

        //RuleEngine
        ruleEngine = new RuleEngine(taxManager);

        // Random Events
        randomEventManager = new RandomEventManager(difficulty);
        randomEventManager.addEvent(new GoodHarvest());
        randomEventManager.addEvent(new ImmigrantWave());
        randomEventManager.addEvent(new BanditAttack());
        randomEventManager.addEvent(new ReinforcementArrival());
        randomEventManager.addEvent(new Plague());
        randomEventManager.addEvent(new Festival());
    }

    // -----------------------------------
    // Start Loop
    // -----------------------------------
    public void start() {
        System.out.println("\nWelcome, Ruler of " + playerKingdom.getName() + "!");
        System.out.println("Prepare your Kingdom for the coming war...\n");

        while (running) {
            MenuPrinter.printMainMenu(currentDay);
            int choice = InputManager.getInt(scanner,"\nYour choice: ");
            handleMenuChoice(choice);
        }

        System.out.println("\nThanks for playing!");
    }

    private void handleMenuChoice(int choice) {
        try {
            switch (choice) {
                case 1 -> statusPrinter.printStatus();
                case 2 -> buildingManager.buildBuilding();
                case 3 -> unitTrainingManager.trainUnit();
                case 4 -> setTaxRate();
                case 5 -> nextDay();
                case 6 -> RuleMenuPrinter.manageCustomRule(scanner, ruleEngine);
                case 7 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }catch (InsufficientResourceException e){
            System.out.println("\n[Resource Error] " + e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("\n[Input Error] " + e.getMessage());
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
        // Daily kingdom updates(taxes, food, gold ...)
        playerKingdom.applyDailyChanges(taxManager.getTaxRate());

        //Rebellion Check
        rebellionManager.checkRebellion();

        // Check random events
        randomEventManager.tryTrigger(playerKingdom);

        // Apply custom rules
        ruleEngine.applyRules(playerKingdom);

        // Advance day
        currentDay++;
        System.out.println("\nDay: " + currentDay);

        // Trigger final battle
        if (currentDay >= battleDay) {
            finalBattle();
            running = false;
            return;
        }

        statusPrinter.printStatus();
    }

    // -----------------------------------
    // Final Battle
    // -----------------------------------
    private void finalBattle() {
        FinalBattle fb = new FinalBattle(playerKingdom, enemyKingdom);
        boolean playerWon = fb.startBattle();

        System.out.println("\n===== BATTLE RESULT =====");
        if (playerWon) {
            System.out.println("VICTORY! You defeated the Dark Empire!");
        } else {
            System.out.println("DEFEAT... Your kingdom falls under enemy rule...");
        }
        System.out.println("\n===== GAME OVER =====\n");
    }
}