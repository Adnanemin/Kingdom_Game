package game.rules.attributes;

import game.Kingdom;

public class PopulationAttribute implements KingdomAttribute {

    @Override
    public String getKey() {
        return "population";
    }

    @Override
    public int getValue(Kingdom kingdom) {
        return kingdom.getPopulation();
    }

    @Override
    public void applyEffect(Kingdom kingdom, int amount) {
        kingdom.changePopulation(amount);
    }
}