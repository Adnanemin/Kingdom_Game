package game.setup;

import game.Kingdom;
import game.units.*;

public enum Difficulty {
    EASY(1, -10, 20) {
        @Override
        public void configureEnemyArmy(Kingdom enemy) {
            enemy.getArmy().addUnit(Soldier::new, 5);
            enemy.getArmy().addUnit(Archer::new, 5);
            enemy.getArmy().addUnit(Cavalry::new, 3);
        }
    },
    NORMAL(2, 0, 30) {
        @Override
        public void configureEnemyArmy(Kingdom enemy) {
            enemy.getArmy().addUnit(Soldier::new, 10);
            enemy.getArmy().addUnit(Archer::new, 5);
            enemy.getArmy().addUnit(Cavalry::new, 5);
        }
    },
    HARD(3, 10, 40) {
        @Override
        public void configureEnemyArmy(Kingdom enemy) {
            enemy.getArmy().addUnit(Soldier::new, 10);
            enemy.getArmy().addUnit(Archer::new, 10);
            enemy.getArmy().addUnit(Cavalry::new, 10);
        }
    };

    private final int menuChoice;
    private final int rebellionChanceModifier;
    private final int randomEventTriggerPercent;

    Difficulty(int menuChoice, int rebellionChanceModifier, int randomEventTriggerPercent) {
        this.menuChoice = menuChoice;
        this.rebellionChanceModifier = rebellionChanceModifier;
        this.randomEventTriggerPercent = randomEventTriggerPercent;
    }

    public int getRebellionChanceModifier() {
        return rebellionChanceModifier;
    }

    public int getRandomEventTriggerPercent() {
        return randomEventTriggerPercent;
    }

    public static Difficulty fromMenuChoice(int choice) {
        for (Difficulty difficulty : values()) {
            if (difficulty.menuChoice == choice) {
                return difficulty;
            }
        }
        return NORMAL;
    }

    public abstract void configureEnemyArmy(Kingdom enemy);
}