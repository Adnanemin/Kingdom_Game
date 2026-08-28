package game.rules.attributes;

import game.Kingdom;

public class FoodAttribute implements KingdomAttribute{
    
    @Override
    public String getKey(){
        return "food";
    }

    @Override
    public int getValue(Kingdom kingdom){
        return kingdom.getFood();
    }

    @Override
    public void applyEffect(Kingdom kingdom, int amount){
        kingdom.changeFood(amount);
    }

}
