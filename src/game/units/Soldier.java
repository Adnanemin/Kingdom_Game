package game.units;

public class Soldier extends Unit {
    //----------------------
    // Constructor
    //----------------------
    public Soldier(){
        super("Soldier", 4, 6, 2, 10);
        // attack = 4
        // defence = 6
        // foodUpkeep = 2
    }

    //----------------------------
    // Battle Value calculation
    //----------------------------
    @Override
    public int getBattleValue(){
        // Soldiers rely more on defence
        return attack + (int)(defence * 2.5); // Just needed the integer value for optimization
    }
}
