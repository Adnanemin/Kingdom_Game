package game.managers;

import game.exceptions.InvalidTaxRateException;

public class TaxManager {

    private static final int MAX_TAX = 25;
    private double taxRate = 0.15;

    public double getTaxRate(){
        return taxRate;
    }

    public void updateTaxRate(int newRate){
        if (newRate < 0 || newRate > MAX_TAX) {
            throw new InvalidTaxRateException(
                    "Tax rate must be between 0 and " + MAX_TAX + "!"
            );
        }
        this.taxRate = (double) newRate / 100.0;
        System.out.println("\nTax rate updated to " + newRate + "%");
    }
}
