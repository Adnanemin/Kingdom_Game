package game.setup;

import game.Kingdom;
import game.units.*;

import java.util.Scanner;
public class KingdomFactory {
    public static Kingdom createKingdom(Scanner scanner){
        System.out.println("Name your Kingdom: ");
        String name = scanner.nextLine();

        if(name.isBlank()){
            name = "My Kingdom";
        }
        return new Kingdom(name, false);
    }

    public static Kingdom createKingdom(int difficulty){
        Kingdom enemy = new Kingdom("Dark Empire", true);
        switch (difficulty){
            case 1 -> {
                enemy.getArmy().addUnit(new Soldier(), 5);
                enemy.getArmy().addUnit(new Archer(), 5);
                enemy.getArmy().addUnit(new Cavalry(), 3);
            }
            case 3 -> {
                enemy.getArmy().addUnit(new Soldier(), 10);
                enemy.getArmy().addUnit(new Archer(), 10);
                enemy.getArmy().addUnit(new Cavalry(), 10);
            }
            default -> {
                enemy.getArmy().addUnit(new Soldier(), 10);
                enemy.getArmy().addUnit(new Archer(), 5);
                enemy.getArmy().addUnit(new Cavalry(), 5);
            }
        }
        return enemy;
    }
}
