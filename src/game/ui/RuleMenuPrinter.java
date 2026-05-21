package game.ui;

import game.exceptions.InvalidRuleException;
import game.rules.*;
import game.managers.InputManager;

import java.util.Scanner;

public class RuleMenuPrinter {
    public static void manageCustomRule(Scanner scanner, RuleEngine ruleEngine){
        boolean back = false;
        while (!back){
            System.out.println("\n===== Custom Rules =====");
            String[] menuItems = {
                    "Add new rule",
                    "List active rules",
                    "Delete a specific rule",
                    "Delete ALL rules",
                    "Back to main menu"
            };
            for(int i = 0; i < menuItems.length; i++){
                System.out.println((i + 1) + ". " + menuItems[i]);
            }

            int choice = InputManager.getInt(scanner, "\nYour choice: ");

            switch (choice){
                case 1 -> {
                    try {
                        ParsedRule rule = RuleFactory.createRule(scanner);
                        ruleEngine.addRule(rule);} catch (InvalidRuleException e){
                        System.out.println("\n[Rule Error] " + e.getMessage());
                    }
                }
                case 2 -> ruleEngine.listRules();
                case 3 -> {
                    ruleEngine.listRules();
                    int idx = InputManager.getInt(scanner, "\nEnter the number of the rule to delete: ");
                    boolean ok = ruleEngine.removeRule(idx);
                    if (!ok){
                        System.out.println("\nInvalid rule number!");
                    }
                }
                case 4 -> ruleEngine.clearRules();
                case 5 -> back = true;
                default -> System.out.println("\nInvalid choice! ");
            }
        }
    }
}
