package game.units;

public abstract class Unit {
    protected String name;
    protected int attack;
    protected int defence;
    protected int foodUpkeep;
    protected int cost;

    //---------------
    // Constructor
    //---------------
    public Unit(String name, int attack, int defence, int foodUpkeep, int cost){
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.foodUpkeep = foodUpkeep;
        this.cost = cost;
    }

    //---------------
    // Getters
    //---------------
    public String getName() {return name; }
    public int getAttack() {return attack; }
    public int getDefence() {return defence; }
    public int getFoodUpKeep() {return foodUpkeep; }
    public int getCost() {return cost; }

    //------------------------------
    // Battle calculation(abstract)
    //------------------------------
    public abstract int getBattleValue();
}
