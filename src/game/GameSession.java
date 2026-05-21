package game;

import game.setup.Difficulty;

public class GameSession {

    public static final int BATTLE_DAY = 30;

    private final Difficulty difficulty;
    private int currentDay = 1;
    private boolean running = true;

    public GameSession(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public boolean isRunning() {
        return running;
    }

    public void advanceDay() {
        currentDay++;
    }

    public boolean isBattleDue() {
        return currentDay >= BATTLE_DAY;
    }

    public void endGame() {
        running = false;
    }
}
