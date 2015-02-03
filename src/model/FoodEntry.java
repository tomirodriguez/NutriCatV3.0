package model;

/**
 * Created by Usuario on 27/01/15.
 */
public class FoodEntry {

    private MealElement mealElement;
    private double amount;

    public FoodEntry(MealElement mealElement, double amount) throws Exception {
        if (mealElement == null)
            throw new NullPointerException("NULL_FOODENTRY_MEALELEMENT");

        if (amount <= 0)
            throw new Exception("FOODENTRY_INVALID_AMOUNT_MESSAGE");

        this.mealElement = mealElement;
        this.amount = amount;
    }

    public MealElement getMealElement() {
        return mealElement;
    }

    public double getAmount() {
        return amount;
    }

    public Double getAmountOf(Nutrient nutrient) {
        return mealElement.getNutritionalInformation().getValueOf(nutrient, amount);
    }
}
