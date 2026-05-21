package game.ui;

import game.Kingdom;
import game.buildings.Building;

public class StatusPrinter {

    private final Kingdom kingdom;

    public StatusPrinter(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public void printStatus() {
        String[] statusItems = {
                "Kingdom: " + kingdom.getName(),
                "Gold: " + kingdom.getGold(),
                "Food: " + kingdom.getFood(),
                "Population/Capacity " + kingdom.getPopulation() +  " / " + kingdom.getHousingCapacity(),
                "Happiness: " + kingdom.getHappiness(),
                "Army Power: " + kingdom.getArmy().getTotalPower(),
        };

        for(int i = 0; i < statusItems.length; i++){
            System.out.println((i + 1) + ". " + statusItems[i]);
        }

        System.out.println("\nBuildings:");
        if (kingdom.getBuildings().isEmpty()) {
            System.out.println("\nNone\n");
        } else {
            for (Building b : kingdom.getBuildings()) {
                if(!b.shouldAppearInStatus()){
                    continue;
                }
                System.out.println("- " + b.getName());
            }
            System.out.println();
        }
    }
}