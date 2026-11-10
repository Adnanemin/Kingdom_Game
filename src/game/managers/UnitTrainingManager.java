package game.managers;

import game.Kingdom;
import game.buildings.Building;
import game.exceptions.PrerequisiteNotMetException;
import game.units.*;
import game.exceptions.InsufficientPopulationException;
import game.exceptions.InvalidUnitException;

import java.util.Scanner;

public class UnitTrainingManager {

    private static final int POPULATION_COST = 1;

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
                "Soldier (" + new Soldier().getCost() + " gold, " + POPULATION_COST +" pop)",
                "Archer (" + new Archer().getCost() + " gold, " + POPULATION_COST +" pop)",
                "Cavalry (" + new Cavalry().getCost() + " gold, " + POPULATION_COST +" pop)",
                "Cancel"
        };
        for(int i = 0; i < menuItems.length; i++){
            System.out.println((i + 1) + ". " + menuItems[i]);
        }

        int choice = InputManager.getInt(scanner, "\nSelect unit: ");
        if (choice == 4){ return;} // Cancel

        Unit unit = switch(choice){
            case 1 -> new Soldier();
            case 2 -> new Archer();
            case 3 -> new Cavalry();
            default -> throw new InvalidUnitException("Invalid choice");
        };

        Building required = unit.getRequiredBuilding();
        if(!kingdom.hasBuilding(required.getClass())){
            throw new PrerequisiteNotMetException(
                    "You need a " + required.getName() + " to train " + unit.getName() + "!"
            );
        }

        // Shared affordability check, defined once on Purchasable
        unit.requireAffordable(kingdom.getGold());

        if (kingdom.getPopulation() < POPULATION_COST) {
            throw new InsufficientPopulationException("Not enough population!");
        }

        kingdom.changeGold(-unit.getCost());
        kingdom.changePopulation(-POPULATION_COST);
        kingdom.getArmy().addUnit(unit);

        System.out.println(unit.getName() + " trained!");
    }
}