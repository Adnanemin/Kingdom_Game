package game.managers;

import game.Kingdom;
import game.buildings.*;
import game.exceptions.InvalidBuildingException;
import game.exceptions.PrerequisiteNotMetException;

import java.util.Scanner;

public class BuildingManager {

    private final Scanner scanner;
    private final Kingdom kingdom;

    public BuildingManager(Scanner scanner, Kingdom kingdom){
        this.scanner = scanner;
        this.kingdom = kingdom;
    }

    public void buildBuilding(){

        System.out.println("\n====== BUILDING MENU ======");
        System.out.println("Current Gold: " + kingdom.getGold());
        String[] menuItems = {
                "Farm (30)",
                "Small House (Gold/Capacity: 20 / 5)",
                "Large House (Gold/Capacity: 40 / 15)",
                "Barracks (40)",
                "Stable (60)",
                "Bar (70)",
                "Wall (70)",
                "Cancel"
        };

        for (int i = 0; i < menuItems.length; i++){
            System.out.println((i + 1) + ". " + menuItems[i]);
        }

        int choice = InputManager.getInt(scanner, "\nSelect building: ");

        Building newBuilding = null;

        switch (choice) {
            case 1 -> newBuilding = new Farm();
            case 2 -> newBuilding = new SmallHouse();
            case 3 -> newBuilding = new LargeHouse();
            case 4 -> {
                if (kingdom.hasBuilding(Barrack.class)) {
                    throw new InvalidBuildingException("You already have a " + new Barrack().getName() + "!\n");
                }
                newBuilding = new Barrack();
            }
            case 5 -> {
                if (kingdom.hasBuilding(Stable.class)) {
                    throw new InvalidBuildingException("You already have a " + new Stable().getName() + "!\n");
                }
                newBuilding = new Stable();
            }
            case 6 -> newBuilding = new Bar();
            case 7 -> {
                if (kingdom.hasBuilding(Wall.class)) {
                    throw new InvalidBuildingException("You already have a " + new Wall().getName() + "!\n");
                }
                newBuilding = new Wall();
            }
            case 8 -> {
                return;
            }
            default -> {
                throw new InvalidBuildingException("Invalid option!\n");
            }
        }

        // Shared affordability check, defined once on Purchasable
        newBuilding.requireAffordable(kingdom.getGold());

        // Deduct cost and add building
        kingdom.changeGold(-newBuilding.getCost());
        kingdom.addBuilding(newBuilding);

        // Update capacity immediately
        kingdom.recalculateHousingCapacity();

        System.out.println("\n" + newBuilding.getName() + " successfully built!");

        if ((newBuilding instanceof SmallHouse || newBuilding instanceof LargeHouse) && kingdom.getHappiness() < 50){
            throw  new PrerequisiteNotMetException("""
                    \nWARNING!
                    Your happiness is below 50, new house will not give population growth for now.
                    """);
        }
    }
}