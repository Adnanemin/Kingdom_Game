package game.rules;

import game.Kingdom;
import game.exceptions.InvalidTaxRateException;
import game.managers.TaxManager;

import java.util.List;
import java.util.ArrayList;

// Stores custom rules and applies them to the Kingdom each day.

public class RuleEngine {

    private final List<ParsedRule> rules = new ArrayList<>();
    private final TaxManager taxManager;

    public RuleEngine(TaxManager taxManager){
        this.taxManager = taxManager;
    }

    public void addRule(ParsedRule rule){
        rules.add(rule);
    }

    public void listRules(){
        if (rules.isEmpty()){
            System.out.println("\nNo custom rules defined.");
            return;
        }
        System.out.println("\n---- Active Rules ---");
        int i = 1;
        for (ParsedRule r : rules){
            System.out.println(i++ + ") " + r);
        }
    }

    // Delete a specific rule that has been already made.
    public boolean removeRule(int realNumber){ // the number shown on the screen
        if(realNumber < 1 || realNumber > rules.size()){
            return false;
        }
        ParsedRule removed = rules.remove(realNumber - 1);
        System.out.println("\nRemoved rule: " + removed);
        return true;
    }

    // Delete all rules that has been already made.
    public void clearRules(){
        rules.clear();
        System.out.println("\nAll rules has been deleted.");
    }

    // Apply all rules to the current Kingdom state.
    public void applyRules(Kingdom kingdom){
        if (rules.isEmpty()){
            return;
        }

        System.out.println("\n[Custom Rule Evaluation]");

        for (ParsedRule r : rules){
            String attr = r.getConditionAttribute();
            if (!isKnownAttribute(attr)) {
                System.out.println("\n[Rule Warning] Skipping rule with unknown attribute: " + attr);
                continue;
            }

            String op = r.getOperator();
            if (!isKnownOperator(op)) {
                System.out.println("\n[Rule Warning] Skipping rule with unknown operator: " + op);
                continue;
            }

            int currentValue = getAttributeValue(kingdom, attr);
            boolean condition = evaluate(currentValue, op, r.getConditionValue());

            if (condition){
                System.out.println("\nRule Triggered: " + r);
                applyEffect(kingdom, r);
            }
        }
    }

    // ----------
    // Helpers
    // ----------

    private boolean isKnownAttribute(String attr) {
        return switch (attr) {
            case "food", "gold", "happiness", "population", "tax" -> true;
            default -> false;
        };
    }

    private boolean isKnownOperator(String op) {
        return switch (op) {
            case "<", "<=", "==", ">=", ">" -> true;
            default -> false;
        };
    }

    private int getAttributeValue(Kingdom kingdom, String attr){
        return switch (attr){
            case "food"       -> kingdom.getFood();
            case "gold"       -> kingdom.getGold();
            case "happiness"  -> kingdom.getHappiness();
            case "population" -> kingdom.getPopulation();
            case "tax"        -> (int) Math.round(taxManager.getTaxRate() * 100); //store as %
            default -> 0;
        };
    }

    private boolean evaluate(int current, String op, int target){
        return switch (op){
            case "<"  -> current < target;
            case "<=" -> current <= target;
            case "==" -> current == target;
            case ">=" -> current >= target;
            case ">"  -> current > target;
            default   -> false;
        };
    }

    // ---------------------------
    // Overloaded applyEffect(...)
    // ---------------------------

    // 1) ParsedRule
    private void applyEffect(Kingdom kingdom, ParsedRule rule){
        String attr = rule.getEffectAttribute();
        if (!isKnownAttribute(attr)) {
            System.out.println("\n[Rule Warning] Skipping effect with unknown attribute: " + attr);
            return;
        }
        applyEffect(kingdom, attr, rule.getEffectValue());
    }

    // 2) Old version: attribute + amount
    private void applyEffect(Kingdom kingdom, String attr, int amount){
        switch (attr){
            case "food" -> kingdom.changeFood(amount);
            case "gold" -> kingdom.changeGold(amount);
            case "happiness" -> kingdom.changeHappiness(amount);
            case "population" -> kingdom.changePopulation(amount);
            case "tax" -> {
                int currentPercentage = (int) Math.round(taxManager.getTaxRate() * 100);
                int newPercentage = currentPercentage + amount;
                try {
                    taxManager.updateTaxRate(newPercentage);
                } catch (InvalidTaxRateException e) {
                    System.out.println("\n[Rule Warning] Tax effect skipped: " + e.getMessage());
                }
            }
            default -> System.out.println("\n[Rule Warning] Skipping effect with unknown attribute: " + attr);
        }
    }
}
