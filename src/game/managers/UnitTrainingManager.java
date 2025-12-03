package game.managers;

import game.Kingdom;
import game.units.*;
import game.buildings.Barrack;
import game.buildings.Stable;

import java.util.Scanner;

public class UnitTrainingManager {

    private final Scanner scanner;
    private final Kingdom kingdom;

    public UnitTrainingManager(Scanner scanner, Kingdom kingdom){
        this.scanner = scanner;
        this.kingdom = kingdom;
    }

    public void trainUnit() {
        System.out.println("\n====== TRAIN UNITS ======");
        System.out.println("Current gold: " + kingdom.getGold());
        System.out.println("Population available: " + kingdom.getPopulation());
        System.out.println("1. Soldier (10 gold, 1 pop)");
        System.out.println("2. Archer (10 gold, 1 pop)");
        System.out.println("3. Cavalry (20 gold, 1 pop)");
        System.out.println("4. Cancel");

        int choice = getIntInput("\nSelect unit: ");
        Unit unit = null;

        switch (choice) {
            case 1 -> {
                if (!kingdom.hasBuilding(Barrack.class)) {
                    System.out.println("\nYou need a Barrack to train Soldiers!");
                    return;
                }
                unit = new Soldier();
            }
            case 2 -> {
                if (!kingdom.hasBuilding(Barrack.class)) {
                    System.out.println("\nYou need a Barrack to train Archers!");
                    return;
                }
                unit = new Archer();
            }
            case 3 -> {
                if (!kingdom.hasBuilding(Stable.class)) {
                    System.out.println("\nYou need a Stable to train Cavalry!");
                    return;
                }
                unit = new Cavalry();
            }
            case 4 -> { return; }
            default -> {
                System.out.println("\nInvalid choice!");
                return;
            }
        }

        int cost = unit.getCost();

        if (kingdom.getGold() < cost) {
            System.out.println("\nNot enough gold!");
            return;
        }

        if (kingdom.getPopulation() < 1) {
            System.out.println("\nNot enough population!");
            return;
        }

        kingdom.changeGold(-cost);
        kingdom.changePopulation(-1);
        kingdom.getArmy().addUnit(unit);

        System.out.println(unit.getName() + " trained!");
    }

    private int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("\nInvalid input. Try again: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}