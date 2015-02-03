package model;

/**
 * Created by Usuario on 20/01/15.
 */
public class NutritionalInformation {

    private double servingSize;
    private Double[] nutrientsValues;

    public NutritionalInformation(double servingSize, Double[] nutrientsValues) throws Exception {
        if (nutrientsValues == null)
            throw new NullPointerException("NULL_NUTRITIONALINFORMATION_VALUES");


        if (servingSize <= 0)
            throw new Exception("NUTRITIONALINFORMATION_INVALID_SERVING_SIZE_MESSAGE");
        else if (nutrientsValues.length != Nutrient.values().length)
            throw new Exception("NUTRITIONALINFORMATION_INVALID_VALUES_LENGTH_MESSAGE");
        else if (nutrientsValues[Nutrient.PROTEIN.ordinal()] == null)
            throw new Exception("NUTRITIONALINFORMATION_INVALID_PROTEIN_MESSAGE");
        else if (nutrientsValues[Nutrient.CALORIES.ordinal()] == null)
            throw new Exception("NUTRITIONALINFORMATION_INVALID_CALORIES_MESSAGE");
        else if (nutrientsValues[Nutrient.CARBOHYDRATE.ordinal()] == null)
            throw new Exception("NUTRITIONALINFORMATION_INVALID_CARBOHYDRATES_MESSAGE");
        else if (nutrientsValues[Nutrient.TOTAL_FAT.ordinal()] == null)
            throw new Exception("NUTRITIONALINFORMATION_INVALID_FATS_MESSAGE");
        else if (isAnyNegativeValue(nutrientsValues))
            throw new Exception("NUTRITIONALINFORMATION_INVALID_AMOUNT_NUTRIENT_MESSAGE");

        this.servingSize = servingSize;
        this.nutrientsValues = nutrientsValues;
    }

    private boolean isAnyNegativeValue(Double[] nutrientsValues) {
        for (Double nutrientValue : nutrientsValues) {
            if (nutrientValue == null)
                continue;
            else if (nutrientValue < 0)
                return true;
        }
        return false;
    }

    public Double getAmountOf(Nutrient nutrient) {
        return nutrientsValues[nutrient.ordinal()];
    }

    public double getServingSize() {
        return servingSize;
    }

    public Double getValueOf(Nutrient nutrient, double amount) {
        try {
            if (servingSize == amount)
                return getAmountOf(nutrient);
            else
                return amount * getAmountOf(nutrient) / servingSize;
        } catch (Exception e) {
            throw new NullPointerException("UNEXPECTED_EXCEPTION");
        }
    }
}