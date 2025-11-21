package game.buildings;

import game.Kingdom;
public class SmallHouse extends Building {
    //--------------------------
    // Constructor
    //--------------------------
    public SmallHouse(){
        super("Small House", 20);
        // cost = 20 golds
    }
    //--------------------------
    // Daily Effect
    //--------------------------
    @Override
    public void applyDailyEffect(Kingdom kingdom){
        // Small houses focused on happiness and modest grown
        kingdom.changeHappiness(+2);
        kingdom.changePopulation(+1);
        kingdom.changeFood(-1);
    }
}
