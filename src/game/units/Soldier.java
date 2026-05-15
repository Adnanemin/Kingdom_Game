package game.units;

public class Soldier extends Unit {
    //----------------------
    // Constructor
    //----------------------
    public Soldier(){
        super("Soldier", 4, 6, 2, 10);
        // attack = 4
        // defense = 6
        // foodUpkeep = 2
        // cost = 10
    }

    //----------------------------
    // Battle Value calculation
    //----------------------------
    @Override
    public int getBattleValue(){
        // Soldiers rely more on defense
        return getAttack() + (int)(getDefense() * 2.5); // Just needed the integer value for optimization
    }
}
