package game.battle;

import game.Kingdom;
import java.util.Scanner;

public class BattleNarrator {
    public static void printPreBattleIntro(Kingdom player, Kingdom enemy) {
        System.out.println("\n====================================================");
        System.out.println("        THE FINAL BATTLE FOR YOUR KINGDOM");
        System.out.println("====================================================\n");

        System.out.println("For many days, the armies of " + enemy.getName()
                + " have been gathering beyond your borders.");
        System.out.println("Scouts report burned villages and marching banners.\n");

        System.out.println("Now, their full army finally arrives at the gates of "
                + player.getName() + ".");
        System.out.println("Your people look to you for one last stand against the enemy.\n");
    }

    public static void printBattleOutcome(boolean playerWon, Kingdom player, Kingdom enemy) {
        System.out.println("\n----- BATTLE NARRATION -----\n");

        if (playerWon) {
            System.out.println("As the dust settles, the banners of " + enemy.getName()
                    + " lie torn on the battlefield.");
            System.out.println("The soldiers of " + player.getName()
                    + " raise their weapons and cheer in victory.");
            System.out.println("The Dark Empire is defeated, and your people celebrate a new era of hope.");
        } else {
            System.out.println("Your last defensive lines collapse under the pressure of " + enemy.getName() + ".");
            System.out.println("Their banners now fly over the walls of " + player.getName() + ".");
            System.out.println("Your people bow their heads as the kingdom falls under the enemy's rule.");
        }

        System.out.println("-----------------------------\n");
    }
}
