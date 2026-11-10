package game.managers;

import game.Kingdom;
import game.buildings.*;
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
        System.out.println("Current Gold: " + kingdom.getGold());
        String[] menuItems = {
                "Farm (" + new Farm().getCost() + ")",
                "Small House (Gold/Capacity: " + new SmallHouse().getCost() + " / " + new SmallHouse().getHouseCapacity() + ")",
                "Large House (Gold/Capacity: " + new LargeHouse().getCost() + " / " + new LargeHouse().getHouseCapacity() + ")",
                "Barracks (" + new Barrack().getCost() + ")",
                "Stable (" + new Stable().getCost() + ")",
                "Bar (" + new Bar().getCost() + ")",
                "Wall (" + new Wall().getCost() + ")",
                "Cancel"
        };

        for (int i = 0; i < menuItems.length; i++){
            System.out.println((i + 1) + ". " + menuItems[i]);
        }

        int choice = InputManager.getInt(scanner, "\nSelect building: ");
        if (choice == menuItems.length){return;}

        Building newBuilding = switch (choice){
            case 1 -> new Farm();
            case 2 -> new SmallHouse();
            case 3 -> new LargeHouse();
            case 4 -> new Barrack();
            case 5 -> new Stable();
            case 6 -> new Bar();
            case 7 -> new Wall();
            default -> throw new InvalidBuildingException("Invalid choice\n");
        };

        if (newBuilding.isUnique() && kingdom.hasBuilding(newBuilding.getClass())){
            throw new InvalidBuildingException("You already have a " + newBuilding.getName() + "!\n");
        }

        // Shared affordability check, defined once on Purchasable
        newBuilding.requireAffordable(kingdom.getGold());

        // Deduct cost and add building
        kingdom.changeGold(-newBuilding.getCost());
        kingdom.addBuilding(newBuilding);

        // Update capacity immediately
        kingdom.recalculateHousingCapacity();

        System.out.println("\n" + newBuilding.getName() + " successfully built!");

        if (newBuilding instanceof House && kingdom.getHappiness() < Kingdom.LOW_HAPPINESS){
            System.out.printf("""
                    
                    WARNING!
                    Your happiness is below %d, new house will not give population growth for now.
                    %n""", Kingdom.LOW_HAPPINESS);
        }
    }
}