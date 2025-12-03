package game.buildings;

import game.Kingdom;

public class Farm extends Building {
    //--------------------------
    // Constructor
    //--------------------------
    public Farm(){
        super("Farm", 30);
        // cost = 30 golds
    }

    //--------------------------
    // Daily Effect
    //--------------------------
    @Override
    public void applyDailyEffect(Kingdom kingdom){
        // Farms produce food every day and pay taxes
        kingdom.changeFood(+20);
        kingdom.changeGold(+2);
    }
}
