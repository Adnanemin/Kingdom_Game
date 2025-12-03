package game.rules;

// Represents a parsed, ready-to-use rule.
// Example:
//  attribute       = "happiness"
//  conditionOp     = "<"
//  conditionValue  = 30
//  effectAttribute = "food"
//  effectAmount    = -5

public class ParsedRule {

    // Condition if <attr> <op> <value>
    private final String conditionAttribute;
    private final String operator;
    private final int conditionValue;

    // Effect <attr> <value>
    private final String effectAttribute;
    private final int effectValue;

    public ParsedRule(String conditionAttribute, String operator, int conditionValue,
                      String effectAttribute, int effectValue){
        this.conditionAttribute = conditionAttribute;
        this.operator = operator;
        this.conditionValue = conditionValue;
        this.effectAttribute = effectAttribute;
        this.effectValue = effectValue;
    }

    // ---------
    // Getters
    // ---------

    public String getConditionAttribute() {
        return conditionAttribute;
    }

    public String getOperator(){
        return operator;
    }

    public int getConditionValue(){
        return conditionValue;
    }

    public String getEffectAttribute(){
        return effectAttribute;
    }

    public int getEffectValue(){
        return effectValue;
    }

    @Override
    public String toString(){
        return ("IF " + conditionAttribute + " " + operator + " " + conditionValue +
                " THEN " +  effectAttribute + " " + effectValue);
    }

}
