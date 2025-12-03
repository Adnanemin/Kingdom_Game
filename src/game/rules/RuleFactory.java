package game.rules;

import game.exceptions.InvalidRuleException;
import game.setup.InputManager;

import java.util.Scanner;

// Builds ParsedRule objects by asking the player questions.
public class RuleFactory {

    public static ParsedRule createRule(Scanner scanner) {

        // 1) Condition attribute
        System.out.println("""
                \nChoose condition attribute:
                1 -> Food
                2 -> Gold
                3 -> Happiness
                4 -> Population
                5 -> Tax Rate (%)
                """);
        int attrChoice = InputManager.getInt(scanner, "-> ");
        String attribute = toAttribute(attrChoice);

        // 2) Condition operator
        System.out.println("""
                \nChoose condition operator:
                1 -> <
                2 -> <=
                3 -> ==
                4 -> >=
                5 -> >
                """);
        int operatorChoice = InputManager.getInt(scanner, "-> ");
        String operator = toConditionOperator(operatorChoice);

        // 3) Condition value
        int conditionValue = InputManager.getInt(scanner, "\nEnter the value: ");

        // 4) Effect attribute
        System.out.println("\nEffect applies to same attribute: " + attribute);

        // 5) Effect direction (increase / decrease)
        System.out.println("""
                \nChoose effect:
                1 -> Increase
                2 -> Decrease
                """);
        int effectDirection = InputManager.getInt(scanner, "-> ");

        int amount = InputManager.getInt(scanner, "\nEffect amount: ");
        int effectValue = toEffectValue(effectDirection, amount);

        ParsedRule rule = new ParsedRule(attribute, operator, conditionValue, attribute, effectValue);

        System.out.println("""
                \nNOTE:
                    - Food, Gold, Population and Happiness never fall below 0.
                    - Happiness is always between 0 and 100.
                    - Tax Rate is automatically constrained between 0% and 25%.
                    - Therefore, extreme rules (e.g. population -1000) will NOT break the game.
                    - The Kingdom will clamp all values to safe ranges.
                """);
        System.out.println("\nCreated Rule: " + rule);
        return rule;
    }

    // ---------------------
    // Helpers for mapping
    // ---------------------

    private static String toAttribute(int choice) {
        return switch (choice) {
            case 1 -> "food";
            case 2 -> "gold";
            case 3 -> "happiness";
            case 4 -> "population";
            case 5 -> "tax";
            default -> throw new InvalidRuleException("\nInvalid attribute choice!");
        };
    }

    private static String toConditionOperator(int op) {
        return switch (op) {
            case 1 -> "<";
            case 2 -> "<=";
            case 3 -> "==";
            case 4 -> ">=";
            case 5 -> ">";
            default -> throw new InvalidRuleException("\nInvalid operator choice!");
        };
    }

    private static int toEffectValue(int direction, int amount) {
        return switch (direction) {
            case 1 -> amount;   // Increase
            case 2 -> -amount;  // Decrease
            default -> throw new InvalidRuleException("\nInvalid effect choice!");
        };
    }
}