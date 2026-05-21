package game.managers;

import game.Kingdom;
import game.exceptions.KingdomCollapsedException;
import game.setup.Difficulty;

import java.util.Random;

public class RebellionManager {

    private final Kingdom kingdom;
    private final Difficulty difficulty;
    private final Random random = new Random();

    public RebellionManager(Kingdom kingdom, Difficulty difficulty) {
        this.kingdom = kingdom;
        this.difficulty = difficulty;
    }

    public void checkRebellion() {
        int happiness = kingdom.getHappiness();

        if (happiness <= 0) {
            triggerRebellion(true);
            return;
        }

        if (happiness <= 20) {
            int baseChance = 25 + difficulty.getRebellionChanceModifier();
            int roll = random.nextInt(100);
            if (roll < baseChance) {
                triggerRebellion(false);
            }
        }
    }

    private void triggerRebellion(boolean guaranteed) {
        System.out.println("\n!!! REBELLION IN YOUR KINGDOM !!!\n");

        if (guaranteed) {
            System.out.println("\nThe people have completely lost their faith and rise up in anger!");
        } else {
            System.out.println("\nSome people start protesting and chaos spreads...");
        }

        int changeFood = -15;
        int changeGold = -20;
        int changePopulation = -5;
        int changeHappiness = -10;

        kingdom.changeFood(changeFood);
        kingdom.changeGold(changeGold);
        kingdom.changePopulation(changePopulation);
        kingdom.changeHappiness(changeHappiness);

        if (kingdom.getHappiness() <= 0) {
            throw new KingdomCollapsedException(
                    "Your Kingdom collapses due to unstoppable rebellion..."
            );
        }

        System.out.println("\nRebellion damage: ");
        System.out.println("Food -> " + changeFood + " Current Food: " + kingdom.getFood());
        System.out.println("Gold -> " + changeGold + " Current Gold: " + kingdom.getGold());
        System.out.println("Population -> " + changePopulation + " Current Population: " + kingdom.getPopulation());
        System.out.println("Happiness -> " + changeHappiness + " Current Happiness: " + kingdom.getHappiness() + "\n");
    }
}
