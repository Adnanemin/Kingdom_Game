package game;

import game.buildings.Building;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {

    // -------------------------
    // Immutable properties
    // -------------------------
    private final String name;
    private final boolean isEnemy;

    // -------------------------
    // Mutable state
    // -------------------------
    private int gold;
    private int food;
    private int population;
    private int happiness;

    private final List<Building> buildings;
    private final Army army;

    // -------------------------
    // Constructor
    // -------------------------
    public Kingdom(String name, boolean isEnemy) {
        this.name = name;
        this.isEnemy = isEnemy;

        this.gold = 100;
        this.food = 100;
        this.population = 20;
        this.happiness = 50;

        this.buildings = new ArrayList<>();
        this.army = new Army();
    }

    // -------------------------
    // Getters
    // -------------------------
    public String getName() { return name; }
    public int getGold() { return gold; }
    public int getFood() { return food; }
    public int getPopulation() { return population; }
    public int getHappiness() { return happiness; }
    public List<Building> getBuildings() { return buildings; }
    public Army getArmy() { return army; }

    // -------------------------
    // State change helpers
    // -------------------------
    public void changeGold(int amount) {
        gold += amount;
        if (gold < 0) gold = 0;
    }

    public void changeFood(int amount) {
        food += amount;
        if (food < 0) food = 0;
    }

    public void changePopulation(int amount) {
        population += amount;
        if (population < 0) population = 0;
    }

    public void changeHappiness(int amount) {
        happiness += amount;
        if (happiness < 0) happiness = 0;
        if (happiness > 100) happiness = 100;
    }

    // -------------------------
    // Building helpers
    // -------------------------
    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public boolean hasBuilding(Class<?> type) {
        for (Building b : buildings) {
            if (type.isInstance(b)) {
                return true;
            }
        }
        return false;
    }

    // -------------------------
    // Army power
    // -------------------------
    public int getTotalArmyPower() {
        return army.getTotalPower();
    }

    // -------------------------
    // Daily changes
    // -------------------------
    public void applyDailyChanges(double taxRate) {

        // 1. Apply building effects
        for (Building b : buildings) {
            b.applyDailyEffect(this);
        }

        // 2. Tax income
        int taxIncome = (int)(population * taxRate);
        changeGold(taxIncome);

        int happinessPenalty = (int)(taxRate * 10);
        changeHappiness(-happinessPenalty);

        // 3. Food consumption (civilians)
        int civilianFoodUse = (int)(population * 0.3);

        // 4. Food consumption (army)
        int armyFoodUse = army.getTotalFoodUpkeep();

        changeFood(-(civilianFoodUse + armyFoodUse));

        // 5. Starvation
        if (food <= 0) {
            food = 0;
            changeHappiness(-5);
        }

        // 6. Low happiness penalties
        if (happiness < 50) {
            int migration = (int)(population * 0.02);
            changePopulation(-migration);
        }

        if (happiness < 25) {
            int migration = (int)(population * 0.05);
            changePopulation(-migration);
        }
    }
}