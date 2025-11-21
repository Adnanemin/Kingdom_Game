package game.units;

public class Cavalry extends Unit{
    //----------------------
    // Constructor
    //----------------------
    public Cavalry(){
        super("Cavalry", 7, 4, 4, 20);
        // attack = 7 -faster and stronger
        // defence = 4
        // foodUpkeep = 4 -the horse included
    }

    //---------------------------
    // Battle value calculation
    //---------------------------
    @Override
    public int getBattleValue(){
        return (attack * 3) + defence; // Elite level units
    }
}
