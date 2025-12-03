package game.events;

import game.Kingdom;

public class Plague implements RandomEvent{
    @Override
    public String getName(){
        return "Plague";
    }

    @Override
    public String getDescription(){
        return "A deadly plague spreads through your kingdom.";
    }

    @Override
    public void apply(Kingdom kingdom){
        System.out.println("\n[EVENT]: " + getName());
        System.out.println(getDescription());
        // Reduces the happiness, population and a bit of food
        int hapNum = -3;
        int popNum = -4;
        int foodNum = -10;
        kingdom.changeHappiness(hapNum);
        kingdom.changePopulation(popNum);
        kingdom.changeFood(foodNum);
        System.out.println("Population " + popNum + " Food " + foodNum + " Happiness " + hapNum);
    }
}
