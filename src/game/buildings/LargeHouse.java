package game.buildings;

import game.Kingdom;

public class LargeHouse extends House {
    //--------------------------
    // Constructor
    //--------------------------
    public LargeHouse(){
        super("Large House", 40, 15);
        // cost = 40 golds
        // capacity = 15
    }

    // Status indicator
    @Override
    public boolean shouldAppearInStatus(){
        return false;
    }

    //--------------------------
    // Daily Effect
    //--------------------------
    @Override
    public void applyDailyEffect(Kingdom kingdom){
        // Large houses focused on faster population growth but slower happiness
        kingdom.changeFood(+1);
    }
}