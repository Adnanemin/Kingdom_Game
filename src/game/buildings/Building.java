package game.buildings;

import game.Kingdom;
import game.util.Purchasable;

public abstract class Building implements Purchasable {
    //-----------
    // Fields
    //-----------
    private final String name;
    private final int cost; // gold cost to build

    //--------------------------
    // Constructor
    //--------------------------
    public Building(String name, int cost){
        this.name = name;
        this.cost = cost;
    }
    //--------------------------
    // Getters
    //--------------------------
    @Override
    public String getName() {return name; }
    @Override
    public int getCost() {return cost; }

    // Status indicator
    public boolean shouldAppearInStatus(){
        return true;
    }

    // Can only one of this building exist?
    public boolean isUnique(){
        return false;
    }

    // How many people this building can house (0 for non-housing buildings)
    public int getHouseCapacity(){
        return 0;
    }

    //Bonus this building adds to the final battle (0 for non-defensive buildings)
    public int getDefenceBonus(){
        return 0;
    }
    //--------------------------
    // Daily effect
    //--------------------------
    public abstract void applyDailyEffect(Kingdom kingdom);
}