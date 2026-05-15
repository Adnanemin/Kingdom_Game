package game.managers;

import game.Kingdom;
import game.buildings.*;
import game.exceptions.InsufficientResourceException;
import game.exceptions.InvalidBuildingException;

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
        System.out.println("Current gold: " + kingdom.getGold());
        System.out.println("1. Farm (30)");
        System.out.println("2. Small House (20)");
        System.out.println("3. Large House (40)");
        System.out.println("4. Barracks (40)");
        System.out.println("5. Stable (60)");
        System.out.println("6. Bar (30)");
        System.out.println("7. Wall (70)");
        System.out.println("8. Cancel");

        int choice = InputManager.getInt(scanner, "\nSelect building: ");

        Building newBuilding = null;

        switch (choice) {
            case 1 -> newBuilding = new Farm();
            case 2 -> newBuilding = new SmallHouse();
            case 3 -> newBuilding = new LargeHouse();
            case 4 -> {
                if (kingdom.hasBuilding(Barrack.class)) {
                    throw new InvalidBuildingException(
                            "You already have a Barracks!"
                    );
                }
                newBuilding = new Barrack();
            }
            case 5 -> {
                if (kingdom.hasBuilding(Stable.class)) {
                 throw new InvalidBuildingException(
                         "You already have a Stable!"
                 );
                }
                newBuilding = new Stable();
            }
            case 6 -> newBuilding = new Bar();
            case 7 -> {
                if (kingdom.hasBuilding(Wall.class)) {
                    throw new InvalidBuildingException(
                            "You already have a Wall!"
                    );
                }
                newBuilding = new Wall();
            }
            case 8 -> {
                return;
            }
            default -> {
                throw new IllegalArgumentException(
                        "Invalid option!"
                );
            }
        }

        if (kingdom.getGold() < newBuilding.getCost()) {
            throw new InsufficientResourceException(
                    "Not enough gold to build " + newBuilding.getName()
            );
        }

        // Deduct cost and add building
        kingdom.changeGold(-newBuilding.getCost());
        kingdom.addBuilding(newBuilding);

        // Update capacity immediately
        kingdom.recalculateHousingCapacity();

        System.out.println("\n" + newBuilding.getName() + " successfully built!");

        if ((newBuilding instanceof SmallHouse || newBuilding instanceof LargeHouse) && kingdom.getHappiness() < 50){
            System.out.println("""
                    \nWARNING!
                    Your happiness is below 50, new house will not give population growth for now.
                    """);
        }
    }
}