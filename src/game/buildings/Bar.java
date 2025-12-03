package game.buildings;

import game.Kingdom;

public class Bar extends Building {
    //--------------------------
    // Constructor
    //--------------------------
    public Bar(){
        super("Bar", 30);
        // cost = 30 gold
    }
    //--------------------------
    // Daily Effect
    //--------------------------
    @Override
    public void applyDailyEffect(Kingdom kingdom){
        // Bars increase the happiness but consume more food and pay taxes
        kingdom.changeFood(-2);
        kingdom.changeHappiness(+3);
        kingdom.changeGold(+5);
    }
}
