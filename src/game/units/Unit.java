package game.units;

public abstract class Unit {
    private final String name;
    private final int attack;
    private final int defense;
    private final int foodUpkeep;
    private final int cost;

    //---------------
    // Constructor
    //---------------
    public Unit(String name, int attack, int defense, int foodUpkeep, int cost){
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.foodUpkeep = foodUpkeep;
        this.cost = cost;
    }

    //---------------
    // Getters
    //---------------
    public String getName() {return name; }
    public int getAttack() {return attack; }
    public int getDefense() {return defense; }
    public int getFoodUpKeep() {return foodUpkeep; }
    public int getCost() {return cost; }

    //------------------------------
    // Battle calculation(abstract)
    //------------------------------
    public abstract int getBattleValue();
}
