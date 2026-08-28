package game.rules.attributes;

import game.Kingdom;
import game.exceptions.InvalidTaxRateException;
import game.managers.TaxManager;

public class TaxAttribute implements KingdomAttribute {

    private final TaxManager taxManager;

    public TaxAttribute(TaxManager taxManager) {
        this.taxManager = taxManager;
    }

    @Override
    public String getKey() {
        return "tax";
    }

    @Override
    public int getValue(Kingdom kingdom) {
        // stored as a percentage
        return (int) Math.round(taxManager.getTaxRate() * 100);
    }

    @Override
    public void applyEffect(Kingdom kingdom, int amount) {
        int newPercentage = getValue(kingdom) + amount;
        try {
            taxManager.updateTaxRate(newPercentage);
        } catch (InvalidTaxRateException e) {
            System.out.println("\n[Rule Warning] Tax effect skipped: " + e.getMessage());
        }
    }
}