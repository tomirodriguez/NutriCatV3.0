package model;

import org.junit.Test;
import utilities.TestsModel;

/**
 * Created by Usuario on 27/01/15.
 */
public class FoodEntryTestsModel extends TestsModel {

    @Test
    public void test00CanNotCreateAFoodEntryWithNoFood() throws Exception {
        try{
            new FoodEntry(null, 0);
            failTest();
        } catch (NullPointerException exception){
            assert exception.getMessage().equals("NULL_FOODENTRY_MEALELEMENT");
        }
    }

    @Test
    public void test01CanNotCreateAFoodEntryWithAZeroOrLessAmount() throws Exception {
        try{
            new FoodEntry(objectFactory.createFood(), 0);
            failTest();
        } catch (Exception exception){
            assert exception.getMessage().equals("FOODENTRY_INVALID_AMOUNT_MESSAGE");
        }
    }

    @Test
    public void test02AFoodEntryCanBeCreated() throws Exception {
        Food food = objectFactory.createFood();
        FoodEntry foodEntry = new FoodEntry(food, 100);

        assert foodEntry.getMealElement().equals(food);
        assert foodEntry.getAmount() == 100;
    }

    @Test
    public void test03AFoodEntryCanReturnTheValuesOfItsNutrients() throws Exception {
        Food food = objectFactory.createFood();
        FoodEntry foodEntry = new FoodEntry(food, 50);

        for(Nutrient nutrient : Nutrient.values())
            assert foodEntry.getAmountOf(nutrient).equals(food.getNutritionalInformation().getValueOf(nutrient, 50));
    }
}
