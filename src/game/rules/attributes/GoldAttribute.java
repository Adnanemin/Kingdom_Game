package game.rules.attributes;

import game.Kingdom;

public class GoldAttribute implements KingdomAttribute {
    
    @Override
    public String getKey(){
        return "gold";
    }

    @Override
    public int getValue(Kingdom kingdom){
        return kingdom.getGold();
    }

    @Override
    public void applyEffect(Kingdom kingdom, int amount){
        kingdom.changeGold(amount);
    }
}
