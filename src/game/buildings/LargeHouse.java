package game.buildings;

import game.Kingdom;

public class LargeHouse extends Building {
    //--------------------------
    // Constructor
    //--------------------------
    public LargeHouse(){
        super("Large House", 40);
        // cost = 40 golds
    }

    //--------------------------
    // Daily Effect
    //--------------------------
    @Override
    public void applyDailyEffect(Kingdom kingdom){
        // Large houses focused on faster population growth but slower happiness
        kingdom.changePopulation(+3);
        kingdom.changeFood(-2);
    }
}