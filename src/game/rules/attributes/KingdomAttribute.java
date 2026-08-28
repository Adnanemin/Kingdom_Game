package game.rules.attributes;

import game.Kingdom;

public interface KingdomAttribute {

    // Key used in rule definitions, e.g. "food", "tax"
    String getKey();

    // Current value of this attribute for the given kingdom
    int getValue(Kingdom kingdom);

    // Apply a rule's effect amount to this attribute
    void applyEffect(Kingdom kingdom, int amount);
}