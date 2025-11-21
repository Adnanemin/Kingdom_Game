package game.units;

public class Archer extends Unit {
    //----------------------
    // Constructor
    //----------------------
    public Archer(){
        super("Archer", 7, 1, 2, 10);
        // attack = 7 (high range damage)
        // defence = 1
        // foodUpkeep = 2
    }

    //----------------------------
    // Battle Value calculation
    //----------------------------
    @Override
    public int getBattleValue(){
        // Archers rely more on attack
        return (int)(attack * 2.5) + defence; // Just needed the integer value for optimization
    }
}
