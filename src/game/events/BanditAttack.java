package game.events;

import game.buildings.Wall;
import game.Kingdom;

public class BanditAttack implements RandomEvent{
    @Override
    public String getName(){
        return "Bandit Attack";
    }

    @Override
    public String getDescription(){
        return "Bandits raids your villages steal the resources!";
    }

    @Override
    public void apply(Kingdom kingdom){
        System.out.println("\n[EVENT] " + getName());
        System.out.println(getDescription());
        // Stolen food and gold and happiness reduces
        int changeGold = -20;
        int changeFood = -10;
        int changeHappiness = -3;
        if(kingdom.hasBuilding(Wall.class)){
            changeGold = changeGold / 2;
            changeFood = changeFood / 2;
        }
        kingdom.changeGold(-changeGold);
        kingdom.changeFood(-changeFood);
        kingdom.changeHappiness(changeHappiness);
        System.out.println("Gold " + changeGold + " Happiness " + changeHappiness + " Food " + changeFood);
    }
}
