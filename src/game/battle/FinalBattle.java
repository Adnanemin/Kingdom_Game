package game.battle;

import game.Kingdom;
import game.buildings.Wall;
import game.buildings.Building;
import game.util.Outcome;

public class FinalBattle {

    private final Kingdom player;
    private final Kingdom enemy;

    public FinalBattle(Kingdom player, Kingdom enemy){
        this.player = player;
        this.enemy = enemy;
    }

    public Outcome<Integer> startBattle(){

        // Pre-battle intro with narrator
        BattleNarrator.printPreBattleIntro(player, enemy);
        System.out.println("\n===== FINAL BATTLE BEGINS! =====");

        int playerPower = player.getTotalArmyPower();
        int enemyPower = enemy.getTotalArmyPower();

        // Wall bonus
        for (Building b : player.getBuildings()){
            if (b instanceof Wall w){
                System.out.println(
                        "Your Wall reinforces your defenses! (+" + w.getDefenceBonus() + ")"
                );
                playerPower += w.getDefenceBonus();
            }
        }

        // Happiness bonus / penalty
        int happiness = player.getHappiness();
        if (happiness > 70){
            System.out.println("High morale boosts your army! (+6)");
            playerPower += 6;
        }
        else if (happiness < 30){
            System.out.println("Low morale weakens your army... (-6)");
            playerPower -= 6;
        }

        System.out.println("\n===== FINAL POWER =====");
        System.out.println("Your final battle power: " + playerPower);
        System.out.println("Enemy final battle power: " + enemyPower);

        boolean playerWon = playerPower >= enemyPower;

        // Show story-style result
        BattleNarrator.printBattleOutcome(playerWon, player, enemy);

        // Calculate power difference (this becomes Outcome<Integer> detail)
        int powerDifference = playerPower - enemyPower;

        return new Outcome<>(playerWon, powerDifference);
    }
}