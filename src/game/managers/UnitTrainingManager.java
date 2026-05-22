package game.managers;

import game.Kingdom;
import game.units.*;
import game.buildings.Barrack;
import game.buildings.Stable;
import game.exceptions.InsufficientPopulationException;
import game.exceptions.InsufficientResourceException;
import game.exceptions.InvalidBuildingException;
import game.exceptions.PrerequisiteNotMetException;

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

        String[] menuItems = {
                "Soldier (10 gold, 1 pop)",
                "Archer (10 gold, 1 pop)",
                "Cavalry (20 gold, 1 pop)",
                "Cancel"
        };
        for(int i = 0; i < menuItems.length; i++){
            System.out.println((i + 1) + ". " + menuItems[i]);
        }

        int choice = InputManager.getInt(scanner, "\nSelect unit: ");
        Unit unit = null;

        switch (choice) {
            case 1 -> {
                if (!kingdom.hasBuilding(Barrack.class)) {
                    throw new PrerequisiteNotMetException("You need a " + new Barrack().getName() + " to train " + new Soldier().getName() + " !");
                }
                unit = new Soldier();
            }
            case 2 -> {
                if (!kingdom.hasBuilding(Barrack.class)) {
                    throw new PrerequisiteNotMetException("You need a " + new Barrack().getName() + " to train " + new Archer().getName() + " !");
                }
                unit = new Archer();
            }
            case 3 -> {
                if (!kingdom.hasBuilding(Stable.class)) {
                    throw new PrerequisiteNotMetException("You need a " + new Stable().getName() + " to train " + new Cavalry().getName() + " !");
                }
                unit = new Cavalry();
            }
            case 4 -> { return; }
            default -> throw new InvalidBuildingException("Invalid choice!");
        }

        int cost = unit.getCost();

        if (kingdom.getGold() < cost) {
            throw new InsufficientResourceException("Not enough gold!");
        }

        if (kingdom.getPopulation() < 1) {
            throw new InsufficientPopulationException("Not enough population!");
        }

        kingdom.changeGold(-cost);
        kingdom.changePopulation(-1);
        kingdom.getArmy().addUnit(unit);

        System.out.println(unit.getName() + " trained!");
    }
}
