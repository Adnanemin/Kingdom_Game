package game.units;

import game.buildings.Building;
import game.buildings.Stable;

public class Cavalry extends Unit{
    //----------------------
    // Constructor
    //----------------------
    public Cavalry(){
        super("Cavalry", 7, 4, 4, 20);
        // attack = 7 -faster and stronger
        // defense = 4
        // foodUpkeep = 4 -the horse included
        // cost = 20
    }

    //---------------------------
    // Battle value calculation
    //---------------------------
    @Override
    public int getBattleValue(){
        return (getAttack() * 3) + getDefense(); // Elite level units
    }

    @Override
    public Building getRequiredBuilding() {
        return new Stable();
    }
}
