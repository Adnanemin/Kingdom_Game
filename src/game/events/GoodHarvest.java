package game.events;

import game.Kingdom;

public class GoodHarvest implements RandomEvent {
    @Override
    public String getName(){
        return "Good Harvest";
    }

    @Override
    public String getDescription(){
        return "The fields are fertile this season. Your people gather a rich harvest!";
    }

    @Override
    public void apply(Kingdom kingdom){
        System.out.println("\n[EVENT] " + getName());
        System.out.println(getDescription());
        // Boosts the food and a bit of happiness
        int foodNum = 20;
        int hapNum = 3;
        kingdom.changeFood(foodNum);
        kingdom.changeHappiness(hapNum);

        System.out.println("Food + " + foodNum + " Happiness + " + hapNum);
    }
}
