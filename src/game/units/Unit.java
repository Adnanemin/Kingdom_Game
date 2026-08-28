package game.units;

import game.util.Purchasable;

public abstract class Unit implements Purchasable {
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
    @Override
    public String getName() {return name; }
    public int getAttack() {return attack; }
    public int getDefense() {return defense; }
    public int getFoodUpKeep() {return foodUpkeep; }
    @Override
    public int getCost() {return cost; }

    //------------------------------
    // Battle calculation(abstract)
    //------------------------------
    public abstract int getBattleValue();
}