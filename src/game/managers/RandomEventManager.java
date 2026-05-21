package game.managers;

import game.events.*;
import game.Kingdom;
import game.setup.Difficulty;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomEventManager {

    private final List<RandomEvent> events = new ArrayList<>();
    private final Random random = new Random();
    private final int triggerChancePercentage;

    public RandomEventManager(Difficulty difficulty) {
        this.triggerChancePercentage = difficulty.getRandomEventTriggerPercent();
        registerDefaultEvents();
    }

    private void registerDefaultEvents() {
        addEvent(new GoodHarvest());
        addEvent(new ImmigrantWave());
        addEvent(new BanditAttack());
        addEvent(new ReinforcementArrival());
        addEvent(new Plague());
        addEvent(new Festival());
    }

    public void addEvent(RandomEvent event) {
        events.add(event);
    }

    public void tryTrigger(Kingdom kingdom) {
        if (events.isEmpty()) {
            return;
        }

        int roll = random.nextInt(100) + 1;

        if (roll > triggerChancePercentage) {
            return;
        }

        RandomEvent event = events.get(random.nextInt(events.size()));
        System.out.println("\n===== RANDOM EVENT! =====");
        event.apply(kingdom);
        System.out.println("==========================\n");
    }
}
