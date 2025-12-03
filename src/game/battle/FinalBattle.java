package game.battle;

import game.Kingdom;
import game.buildings.Wall;
import game.buildings.Building;

public class FinalBattle {

    private final Kingdom player;
    private final Kingdom enemy;

    public FinalBattle(Kingdom player, Kingdom enemy){
        this.player = player;
        this.enemy = enemy;
    }

    public boolean startBattle(){

        // Intro
        BattleNarrator.printPreBattleIntro(player, enemy);
        System.out.println("\n===== FINAL BATTLE BEGINS! =====");

        int playerPower = player.getTotalArmyPower();
        int enemyPower = enemy.getTotalArmyPower();

        // Wall bonus
        for (Building b : player.getBuildings()){
            if(b instanceof Wall w){
                System.out.println("Your wall reinforces your defenses! (+" + w.getDefenceBonus() + ")");
                playerPower += w.getDefenceBonus();
            }
        }

        // Happiness bonus
        int happiness = player.getHappiness();
        if(happiness > 70){
            System.out.println("High morale boosts your army! (+6)");
            playerPower += 6;
        } else if (happiness < 30){
            System.out.println("Low morale weakens your army... (-6)");
            playerPower -= 6;
        }

        System.out.println("\n===== FINAL POWER =====");
        System.out.println("Your final battle power: " + playerPower);
        System.out.println("Enemy final battle power: " + enemyPower);

        boolean playerWon = playerPower >= enemyPower;

        // Story about the outcome
        BattleNarrator.printBattleOutcome(playerWon, player, enemy);

        return playerWon;
    }
}