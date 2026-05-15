package game;

import game.buildings.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Kingdom {

    // -------------------------
    // Constants
    // -------------------------
    private static final int INITIAL_GOLD = 150;
    private static final int INITIAL_FOOD = 100;
    private static final int INITIAL_POPULATION = 20;
    private static final int INITIAL_HAPPINESS = 50;

    private static final double CIVILIAN_FOOD_PER_PERSON = 0.3;
    private static final int STARVATION_PENALTY = 5;

    private static final int LOW_HAPPINESS = 49;
    private static final int VERY_LOW_HAPPINESS = 25;

    private static final double MIGRATION_LOW = 0.02;
    private static final double MIGRATION_VERY_LOW = 0.05;

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

    private int housingCapacity;
    private final List<Building> buildings;
    private final Army army; 

    private final Random random = new Random();

    // -------------------------
    // Constructor
    // -------------------------
    public Kingdom(String name, boolean isEnemy) {
        this.name = name;
        this.isEnemy = isEnemy;

        this.gold = INITIAL_GOLD;
        this.food = INITIAL_FOOD;
        this.population = INITIAL_POPULATION;
        this.happiness = INITIAL_HAPPINESS;

        this.buildings = new ArrayList<>();
        this.army = new Army();
    }

    // -------------------------
    // Getters
    // -------------------------
    public String getName() { return name; }
    public boolean isEnemy() { return isEnemy; }

    public int getGold() { return gold; }
    public int getFood() { return food; }
    public int getPopulation() { return population; }
    public int getHappiness() { return happiness; }

    public int getHousingCapacity() { return housingCapacity; }
    public Army getArmy() { return army; }

    public List<Building> getBuildings() {
        return Collections.unmodifiableList(buildings);
    }

    public int getTotalArmyPower() {
        return army.getTotalPower();
    }

    // -------------------------
    // Public daily logic
    // -------------------------
    public void applyDailyChanges(double taxRate) {

        applyBuildingEffects();
        applyTaxStep(taxRate);
        applyFoodStep();
        handleStarvation();
        handleHappinessMigration();
        handleRandomPopulationGrowth();

        recalculateHousingCapacity();
        enforceHousingCapacity();
    }

    // -------------------------
    // Daily steps (private)
    // -------------------------

    private void applyBuildingEffects() {
        for (Building building : buildings) {
            building.applyDailyEffect(this);
        }
    }

    private void applyTaxStep(double taxRate) {
        int income = (int) (population * taxRate);
        changeGold(income);

        int happinessLoss = (int) (taxRate * 10);
        changeHappiness(-happinessLoss);
    }

    private void applyFoodStep() {
        int civilianUse = (int) (population * CIVILIAN_FOOD_PER_PERSON);
        int armyUse = army.getTotalFoodUpkeep();

        changeFood(-(civilianUse + armyUse));
    }

    private void handleStarvation() {
        if (food == 0 && population > 0) {
            changeHappiness(-STARVATION_PENALTY);
        }
    }

    private void handleHappinessMigration() {
        if (happiness <= LOW_HAPPINESS) {
            int migration = (int) (population * MIGRATION_LOW);
            changePopulation(-migration);
        }
        if (happiness < VERY_LOW_HAPPINESS) {
            int migration = (int) (population * MIGRATION_VERY_LOW);
            changePopulation(-migration);
        }
    }

    private void handleRandomPopulationGrowth() {
        if (population <= 0) return;

        if (happiness > 50) {
            int chance = Math.min(happiness - 40, 50);
            if (random.nextInt(100) < chance) {
                changePopulation(1);
                System.out.println("\n Daily population change: (+1)");
                return;
            }
        }
        System.out.println("\nNo population growth today.");
    }

    // -------------------------
    // Housing
    // -------------------------
    public void recalculateHousingCapacity() {
        int capacity = 0;

        for (Building b : buildings) {
            if (b instanceof SmallHouse sh) {
                capacity += sh.getCapacity();
            } else if (b instanceof LargeHouse lh) {
                capacity += lh.getCapacity();
            }
        }

        this.housingCapacity = capacity;
    }

    public void enforceHousingCapacity() {
        if (population > housingCapacity) {
            int lost = population - housingCapacity;
            population = housingCapacity;
            System.out.println("\n" + lost + " people left due to lack of housing.");
        }
    }

    // -------------------------
    // State changes
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
        return buildings.stream().anyMatch(type::isInstance);
    }
}