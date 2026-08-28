package game.util;

import game.exceptions.InsufficientResourceException;

public interface Purchasable {

    String getName();

    int getCost();

    // Shared gold-check logic used by managers before a purchase.
    default void requireAffordable(int availableGold) {
        if (availableGold < getCost()) {
            throw new InsufficientResourceException(
                    "Not enough gold to acquire " + getName()
            );
        }
    }
}