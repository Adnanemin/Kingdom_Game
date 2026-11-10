package game.events;

import game.Kingdom;
import game.units.*;

public class ReinforcementArrival implements RandomEvent {

    private static final int SOLDIER_COUNT = 2;
    private static final int ARCHER_COUNT = 1;
    private static final int CAVALRY_COUNT = 1;
    @Override
    public String getName(){
        return "Reinforcement Arrival";
    }

    @Override
    public String getDescription(){
        return "A group of trained soldiers arrived to support your army!";
    }

    @Override
    public void apply(Kingdom kingdom){
        System.out.println("\n[EVENT] " + getName());
        System.out.println(getDescription());

        // List of reinforcements
        kingdom.getArmy().addUnit(Soldier::new, SOLDIER_COUNT);
        kingdom.getArmy().addUnit(Archer::new, ARCHER_COUNT);
        kingdom.getArmy().addUnit(Cavalry::new, CAVALRY_COUNT);

        System.out.println("\nReinforcements arrived to your army: ");
        System.out.println("+" + SOLDIER_COUNT + " Soldiers, +" + ARCHER_COUNT + " Archer, +" + CAVALRY_COUNT + " Cavalry");
    }

}
