package game.rules.attributes;

import game.Kingdom;

public class HappinessAttribute implements KingdomAttribute {

    @Override
    public String getKey() {
        return "happiness";
    }

    @Override
    public int getValue(Kingdom kingdom) {
        return kingdom.getHappiness();
    }

    @Override
    public void applyEffect(Kingdom kingdom, int amount) {
        kingdom.changeHappiness(amount);
    }
}