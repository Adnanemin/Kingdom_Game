package game.buildings;

import game.Kingdom;
public class SmallHouse extends House {
    //--------------------------
    // Constructor
    //--------------------------
    public SmallHouse(){
        super("Small House", 20, 5);
        // cost = 20 golds
        // capacity = 5
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
        // Small houses focused on happiness and modest grown
        kingdom.changeHappiness(+2);
        kingdom.changeFood(-1);
    }
}
