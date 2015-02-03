package model;

import org.junit.Test;
import utilities.TestsModel;

import java.util.List;

/**
 * Created by Usuario on 27/01/15.
 */
public class MealTestsModel extends TestsModel {

    @Test
    public void test00CanNotCreateAMealWithANullName() throws Exception {
        try {
            new Meal(null);
            failTest();
        } catch (NullPointerException exception) {
            assert exception.getMessage().equals("NULL_MEAL_NAME");
        }
    }

    @Test
    public void test01CanNotCreateAMealWithAnEmptyName() throws Exception {
        try {
            new Meal("");
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("MEAL_INVALID_NAME_MESSAGE");
        }
    }

    @Test
    public void test02AMealHasTheCorrectNameWhenCreated() throws Exception {
        Meal meal = new Meal("MEAL");

        assert meal.getName().equals("MEAL");
    }

    @Test
    public void test03AMealHasNoFoodsWhenCreated() throws Exception {
        Meal meal = new Meal("MEAL");

        assert meal.getEntries().isEmpty();
    }

    @Test
    public void test04AFoodEntryCanBeAddedToAMeal() throws Exception {
        Meal meal = new Meal("MEAL");
        Food food = objectFactory.createFood();
        FoodEntry foodEntry = new FoodEntry(food, 100);

        meal.add(foodEntry);

        assert meal.getEntries().size() == 1;
        assert meal.getEntries().contains(foodEntry);
    }

    @Test
    public void test05AMealCanReturnTheTotalAmountOfEachNutrient() throws Exception {
        List<FoodEntry> foodEntryList = objectFactory.createFoodEntries();
        Meal meal = new Meal("MEAL");
        for(FoodEntry foodEntry : foodEntryList)
            meal.add(foodEntry);

        for (Nutrient nutrient : Nutrient.values()) {
            assert meal.getAmountOf(nutrient).equals(getCorrectAmountOfNutrientFromListOfFoodEntry(nutrient, foodEntryList));
        }
    }
}
