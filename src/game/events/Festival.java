package game.events;

import game.Kingdom;

public class Festival implements RandomEvent{
    @Override
    public String getName(){
        return "Festival";
    }

    @Override
    public String getDescription(){
        return "A lively festival boosts morale across your kingdom!";
    }

    @Override
    public void apply(Kingdom kingdom){
        System.out.println("\n[EVENT]: " + getName());
        System.out.println(getDescription());
        // Boosts happiness and reduces food
        int foodNum = -5;
        int hapNum = 10;
        kingdom.changeHappiness(hapNum);
        kingdom.changeFood(foodNum);
        System.out.println("Happiness + " + hapNum + " Food  " + foodNum);
    }
}
