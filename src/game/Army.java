package game;

import game.units.Unit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class Army {

    // -------------------------
    // Fields
    // -------------------------
    private final List<Unit> units;

    // -------------------------
    // Constructor
    // -------------------------
    public Army() {
        this.units = new ArrayList<>();
    }

    // -------------------------
    // Getter
    // -------------------------
    public List<Unit> getUnits() {
        return Collections.unmodifiableList(units);
    }

    // -------------------------
    // Core methods
    // -------------------------

    // Basic: Add one unit
    public void addUnit(Unit unit) {
        //Simple validation
        if(unit == null){
            throw new IllegalArgumentException("Unit can not be null!");
        }
        units.add(unit);
    }

    // Overload: Add multiple units
    public void addUnit(Supplier<Unit> factory, int count){
        for (int i = 0; i < count; i ++){
            addUnit(factory.get());
        }
    }

    // Overload: Add units from a list
    public void addUnit(List<Unit> unitList){
        for(Unit u : unitList){
            addUnit(u);
        }
    }

    // -------------------------
    // Power & Upkeep
    // -------------------------

    public int getTotalPower() {
        int total = 0;
         // Sum BattleValue of every unit in army
        for(Unit u: units){
            total += u.getBattleValue();
        }
        return total;
    }

    public int getTotalFoodUpkeep() {
        int total = 0;
        // Sum TotalFoodUpkeep for every unit
        for (Unit u: units){
            total += u.getFoodUpKeep();
        }
        return total;
    }
}