package game.buildings;

import game.Kingdom;

public abstract class Building {
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
    public String getName() {return name; }
    public int getCost() {return cost; }

    // Status indicator
    public boolean shouldAppearInStatus(){
        return true;
    }
    //--------------------------
    // Daily effect
    //--------------------------
    public abstract void applyDailyEffect(Kingdom kingdom);
}
