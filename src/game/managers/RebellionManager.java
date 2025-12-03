package game.managers;

import game.Kingdom;

import java.util.Random;

public class RebellionManager {

    // Difficulty affect Rebellion chance
    // Easy -> -10 %
    // Normal -> 0 %
    //Hard -> +10 %

    private final Kingdom kingdom;
    private final int difficulty;
    private final Random random = new Random();

    public RebellionManager(Kingdom kingdom, int difficulty){
        this.kingdom = kingdom;
        this.difficulty = difficulty;
    }

    public void checkRebellion(){
        int happiness = kingdom.getHappiness();

        // Guaranteed rebellion
        if (happiness <= 0){
            triggerRebellion(true);
            return;
        }

        // Possible Rebellion
        if (happiness <= 20){
            int baseChance = 25; // 25% default

            if (difficulty == 1){
                baseChance -= 10; //Easy
            } else if (difficulty == 3) {
                baseChance += 10; // Hard
            }
            int roll = random.nextInt(100); // 0 - 99
            if (roll < baseChance){
                triggerRebellion(false);
            }
        }
    }

    private void triggerRebellion(boolean guaranteed){
        System.out.println("\n!!! REBELLION IN YOUR KINGDOM !!!\n");

        if (guaranteed){
            System.out.println("\nThe people have completely lost their faith and rise up in anger!");
        }else {
            System.out.println("\nSome people start protesting and chaos spreads...");
        }

        // Effects of rebellion
        int changeFood = -15;
        int changeGold = -20;
        int changePopulation = -5;
        int changeHappiness = -10;

        kingdom.changeFood(changeFood);
        kingdom.changeGold(changeGold);
        kingdom.changePopulation(changePopulation);
        kingdom.changeHappiness(changeHappiness);

        if (kingdom.getHappiness() <= 0){ // after the changes if the final happiness is zero, GAME OVER
            System.out.println("\nYour Kingdom collapses due to unstoppable rebellion...");
            System.out.println("GAME OVER");
            System.exit(0);
        }

        System.out.println("\nRebellion damage: ");
        System.out.println("Food -> " + changeFood + " " + " Current Food: " + kingdom.getFood());
        System.out.println("Gold -> " + changeGold + " " + " Current Gold: " + kingdom.getGold());
        System.out.println("Population -> " + changePopulation + " " + " Current Population: " + kingdom.getPopulation());
        System.out.println("Happiness -> " + changeHappiness + " " + " Current Happiness: " + kingdom.getHappiness() + "\n");
    }
}
