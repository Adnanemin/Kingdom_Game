package game.ui;

import game.Kingdom;
import game.buildings.Building;

public class StatusPrinter {

    private final Kingdom kingdom;

    public StatusPrinter(Kingdom kingdom) {
        this.kingdom = kingdom;
    }

    public void printStatus() {
        System.out.println("\n====== KINGDOM STATUS ======");
        System.out.println("Kingdom: " + kingdom.getName());
        System.out.println("Gold: " + kingdom.getGold());
        System.out.println("Food: " + kingdom.getFood());
        System.out.println("Population/Capacity " + kingdom.getPopulation() + " / " + kingdom.getHousingCapacity());
        System.out.println("Happiness: " + kingdom.getHappiness());
        System.out.println("Army Power: " + kingdom.getArmy().getTotalPower());

        System.out.println("\nBuildings:");
        if (kingdom.getBuildings().isEmpty()) {
            System.out.println("\nNone");
        } else {
            for (Building b : kingdom.getBuildings()) {
                System.out.println("- " + b.getName());
            }
        }
    }
}