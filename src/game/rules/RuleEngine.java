package game.rules;

import game.Kingdom;
import game.managers.TaxManager;
import game.rules.attributes.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RuleEngine {

    private final List<ParsedRule> rules = new ArrayList<>();
    private final Map<String, KingdomAttribute> attributes = new LinkedHashMap<>();

    public RuleEngine(TaxManager taxManager){
        registerDefaultAttributes(taxManager);
    }

    private void registerDefaultAttributes(TaxManager taxManager) {
        registerAttribute(new FoodAttribute());
        registerAttribute(new GoldAttribute());
        registerAttribute(new HappinessAttribute());
        registerAttribute(new PopulationAttribute());
        registerAttribute(new TaxAttribute(taxManager));
    }

    // Registers a new attribute so it becomes usable in rules.
    // New "mods" (extra tunable attributes) plug in here.
    public void registerAttribute(KingdomAttribute attribute) {
        attributes.put(attribute.getKey(), attribute);
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
            KingdomAttribute conditionAttribute = attributes.get(r.getConditionAttribute());
            if (conditionAttribute == null) {
                System.out.println("\n[Rule Warning] Skipping rule with unknown attribute: " + r.getConditionAttribute());
                continue;
            }

            String op = r.getOperator();
            if (!isKnownOperator(op)) {
                System.out.println("\n[Rule Warning] Skipping rule with unknown operator: " + op);
                continue;
            }

            int currentValue = conditionAttribute.getValue(kingdom);
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

    private boolean isKnownOperator(String op) {
        return switch (op) {
            case "<", "<=", "==", ">=", ">" -> true;
            default -> false;
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

    private void applyEffect(Kingdom kingdom, ParsedRule rule){
        KingdomAttribute effectAttribute = attributes.get(rule.getEffectAttribute());
        if (effectAttribute == null) {
            System.out.println("\n[Rule Warning] Skipping effect with unknown attribute: " + rule.getEffectAttribute());
            return;
        }
        effectAttribute.applyEffect(kingdom, rule.getEffectValue());
    }
}