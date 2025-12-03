package game.events;

import game.Kingdom;
import game.units.*;
import java.util.List;
import java.util.ArrayList;

public class ReinforcementArrival implements RandomEvent {

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
        List<Unit> reinforcement = new ArrayList<>();
        reinforcement.add(new Soldier());
        reinforcement.add(new Soldier());
        reinforcement.add(new Archer());
        reinforcement.add(new Cavalry());

        // Add via list overload
        kingdom.getArmy().addUnit(reinforcement);

        System.out.println("\nReinforcements arrived to your army: ");
        System.out.println("+2 Soldiers, +1 Archer, +1 Cavalry");
    }
}
