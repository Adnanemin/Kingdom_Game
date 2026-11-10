package game.buildings;

import game.Kingdom;

public class Wall extends Building{

    //--------------------------
    // Constructor
    //--------------------------
    public Wall(){
        super("Wall", 70);
        // cost = 70 golds
        // Expensive but powerful
    }

    @Override
    public boolean isUnique() {
        return true;
    }

    //--------------------------
    // Daily Effect
    //--------------------------
    @Override
    public void applyDailyEffect(Kingdom kingdom){
        // Walls do not provide daily resource effects.
        // Their purpose is to protect the kingdom during attacks
        // and give a defense advantage during final battle.
        // So no daily effect is needed here.
    }

    //--------------------------
    // Getter for defensive bonus
    //--------------------------
    public int getDefenceBonus(){
        // Permanent defense bonus for the kingdom
        int defenseBonus = 20;
        return defenseBonus;
    }
}
