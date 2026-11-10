package game;

public class GameSession {

    public static final int BATTLE_DAY = 30;

    private int currentDay = 1;
    private boolean running = true;

    public GameSession() {
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
