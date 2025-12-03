package game.managers;

import game.events.*;
import game.Kingdom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEventManager {
    private final List<RandomEvent> events = new ArrayList<>();
    private final Random random = new Random();

    // Percentage of the chosen event that depends on the difficulty
    private final int triggerChancePercentage;

    public RandomEventManager(int difficulty){
        this.triggerChancePercentage = switch (difficulty){
            case 1 -> 20;
            case 2 -> 30;
            case 3 -> 40;
            default -> 20;
        };
    }
    // Adding the events in events list
    public void addEvent(RandomEvent event){
        events.add(event);
    }
    public void tryTrigger(Kingdom kingdom){

        // Check if the list empty
        if(events.isEmpty()) return;

        int roll = random.nextInt(100) + 1;

        // No event came up
        if(roll > triggerChancePercentage){
            return;
        }

        // Chooses an event
        RandomEvent event = events.get(random.nextInt(events.size()));
        System.out.println("\n===== RANDOM EVENT! =====");
        event.apply(kingdom);
        System.out.println("==========================\n");
    }
}
