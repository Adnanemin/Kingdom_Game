package game.events;

import game.Kingdom;

public interface RandomEvent {
    // Short name of the event
    String getName();

    // Text shown to the player
    String getDescription();

    // Apply the changes to the kingdom
    void apply(Kingdom kingdom);
}
