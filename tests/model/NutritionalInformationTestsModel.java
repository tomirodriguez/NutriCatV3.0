package model;

import org.junit.Test;
import utilities.TestsModel;

/**
 * Created by Usuario on 19/01/15.
 */
public class NutritionalInformationTestsModel extends TestsModel {

    @Test
    public void test00CanNotCreateANutritionalInformationWithNullValues() throws Exception {
        try {
            new NutritionalInformation(100.0, null);
            failTest();
        } catch (Exception exception) {
            assert exception.getClass().equals(NullPointerException.class);
            assert exception.getMessage().equals("NULL_NUTRITIONALINFORMATION_VALUES");
        }
    }

    @Test
    public void test01CanNotCreateANutritionalInformationWithAZeroOrNegativeServingSize() throws Exception {
        try {
            new NutritionalInformation(0, new Double[Nutrient.values().length]);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("NUTRITIONALINFORMATION_INVALID_SERVING_SIZE_MESSAGE");
        }
    }

    @Test
    public void test02CanNotCreateANutritionalInformationWithoutProteins() throws Exception {
        try {
            new NutritionalInformation(100, new Double[Nutrient.values().length]);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("NUTRITIONALINFORMATION_INVALID_PROTEIN_MESSAGE");
        }
    }

    @Test
    public void test03CanNotCreateANutritionalInformationWithoutCalories() throws Exception {
        try {
            Double[] values = new Double[Nutrient.values().length];
            values[Nutrient.PROTEIN.ordinal()] = 10.0;
            new NutritionalInformation(100, values);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("NUTRITIONALINFORMATION_INVALID_CALORIES_MESSAGE");
        }
    }

    @Test
    public void test04CanNotCreateANutritionalInformationWithoutCarbohydrates() throws Exception {
        try {
            Double[] values = new Double[Nutrient.values().length];
            values[Nutrient.PROTEIN.ordinal()] = 10.0;
            values[Nutrient.CALORIES.ordinal()] = 10.0;
            new NutritionalInformation(100, values);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("NUTRITIONALINFORMATION_INVALID_CARBOHYDRATES_MESSAGE");
        }
    }

    @Test
    public void test05CanNotCreateANutritionalInformationWithoutFats() throws Exception {
        try {
            Double[] values = new Double[Nutrient.values().length];
            values[Nutrient.PROTEIN.ordinal()] = 10.0;
            values[Nutrient.CALORIES.ordinal()] = 10.0;
            values[Nutrient.CARBOHYDRATE.ordinal()] = 10.0;
            new NutritionalInformation(100, values);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("NUTRITIONALINFORMATION_INVALID_FATS_MESSAGE");
        }
    }

    @Test
    public void test06ANutritionalInformationHasTheCorrectQuantitiesForEachNutrient() throws Exception {
        Double[] values = objectFactory.createNutrientsValues();
        NutritionalInformation nutritionalInformation = new NutritionalInformation(100.0, values);

        assert nutritionalInformation.getServingSize() == 100.0;
        for (Nutrient nutrient : Nutrient.values())
            assert nutritionalInformation.getAmountOf(nutrient) == values[nutrient.ordinal()];
    }

    @Test
    public void test07ANutritionalInformationCanReturnTheNutrientValueForASpecificAmount() throws Exception {
        Double[] values = objectFactory.createNutrientsValues();
        NutritionalInformation nutritionalInformation = new NutritionalInformation(50, values);

        assert nutritionalInformation.getServingSize() == 50.0;

        for (Nutrient nutrient : Nutrient.values())
            assert nutritionalInformation.getValueOf(nutrient, 20) == values[nutrient.ordinal()] * 20 / 50;
    }

    @Test
    public void test08CanNotCreateANutritionalInformationWithAnArrayOfValuesWithIncorrectLenght() throws Exception {
        try {
            new NutritionalInformation(100, new Double[10]);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("NUTRITIONALINFORMATION_INVALID_VALUES_LENGTH_MESSAGE");
        }
    }

    @Test
    public void test09CanNotCreateANutritionalInformationIfAnyNutrientAmountIsLowerThanZero() throws Exception {
        try {
            Double[] doubles = objectFactory.createNutrientsValues();
            doubles[0] = -1.0;
            new NutritionalInformation(100, doubles);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("NUTRITIONALINFORMATION_INVALID_AMOUNT_NUTRIENT_MESSAGE");
        }
    }
}
