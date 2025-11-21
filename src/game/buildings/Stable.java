package game.buildings;

import game.Kingdom;

public class Stable extends Building {
    //--------------------------
    // Constructor
    //--------------------------
    public Stable(){
        super("Stable", 60);
        // cost = 60 golds
    }
    //--------------------------
    // Daily Effect
    //--------------------------
    @Override
    public void applyDailyEffect(Kingdom kingdom){
        // Stables do not provide daily production.
        // Their purpose is enabling Cavalry training.
        // So no daily effect is needed here
    }
}
