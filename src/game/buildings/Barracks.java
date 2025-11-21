package game.buildings;

import game.Kingdom;

public class Barracks extends Building {
    //--------------------------
    // Constructor
    //--------------------------
    public Barracks(){
        super("Barracks", 40);
        // cost = 40 gold
    }
    //--------------------------
    // Daily Effect
    //--------------------------
    @Override
    public void applyDailyEffect(Kingdom kingdom){
        // Barracks does not produce resources daily
        // Its function is enabling soldier training
        // Therefore no daily effect
    }
}
