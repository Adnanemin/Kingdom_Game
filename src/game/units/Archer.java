package game.units;

public class Archer extends Unit {
    //----------------------
    // Constructor
    //----------------------
    public Archer(){
        super("Archer", 7, 1, 2, 10);
        // attack = 7 (high range damage)
        // defense = 1
        // foodUpkeep = 2
        // cost = 10
    }

    //----------------------------
    // Battle Value calculation
    //----------------------------
    @Override
    public int getBattleValue(){
        // Archers rely more on attack
        return (int)(getAttack() * 2.5) + getDefense(); // Just needed the integer value for optimization
    }
}
