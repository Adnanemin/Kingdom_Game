package game.events;

import game.Kingdom;

public class ImmigrantWave implements RandomEvent {
    @Override
    public String getName(){
        return "Immigrant Wave";
    }

    @Override
    public String getDescription(){
        return "Some people who escaped the cruelty of the Dark Empire have moved to your country. Your own people have become uneasy.";
    }

    @Override
    public void apply(Kingdom kingdom){
        System.out.println("\n[EVENT] " + getName());
        System.out.println(getDescription());
        // Boosts population reduces happiness a bit
        int popBoost = 5;
        int hapChange = -2;
        kingdom.changePopulation(popBoost);
        kingdom.changeHappiness(-2);
        System.out.println("Population + " + popBoost + " Happiness " + hapChange);
    }
}
