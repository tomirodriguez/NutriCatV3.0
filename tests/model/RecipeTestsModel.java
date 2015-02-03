package model;

import org.junit.Test;
import utilities.TestsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 27/01/15.
 */
public class RecipeTestsModel extends TestsModel {

    @Test
    public void test00CanNotCreateARecipeWithANullName() throws Exception {
        try {
            new Recipe(null, objectFactory.createFoodEntries(), null);
            failTest();
        } catch (NullPointerException exception) {
            assert exception.getMessage().equals("NULL_RECIPE_NAME");
        }
    }

    @Test
    public void test00CanNotCreateARecipeWithANullListOfFoodEntries() throws Exception {
        try {
            new Recipe("RECIPE", null, null);
            failTest();
        } catch (NullPointerException exception) {
            assert exception.getMessage().equals("NULL_RECIPE_INGREDIENTS");
        }
    }

    @Test
    public void test00CanNotCreateARecipeWithANullDescription() throws Exception {
        try {
            new Recipe("", objectFactory.createFoodEntries(), null);
            failTest();
        } catch (NullPointerException exception) {
            assert exception.getMessage().equals("NULL_RECIPE_DESCRIPTION");
        }
    }

    @Test
    public void test01CanNotCreateARecipeWithAnEmptyName() throws Exception {
        try {
            new Recipe("", objectFactory.createFoodEntries(), "");
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("RECIPE_INVALID_NAME_MESSAGE");
        }
    }

    @Test
    public void test02CanNotCreateARecipeWithAnEmptyListOfFIngredients() throws Exception {
        try {
            new Recipe("RECIPE", new ArrayList<FoodEntry>(), "");
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("RECIPE_EMPTY_FOODENTRY_MESSAGE");
        }
    }

    @Test
    public void test03ARecipeCanBeCreated() throws Exception {
        List<FoodEntry> ingredients = objectFactory.createFoodEntries();
        Recipe recipe = new Recipe("RECIPE", ingredients, "");

        assert recipe.getName().equals("RECIPE");
        assert recipe.getIngredients().equals(ingredients);
        assert recipe.getDescription().equals("");
    }

    @Test
    public void test04ARecipeHasNoCustomServingsWhenCreated() throws Exception {
        List<FoodEntry> ingredients = objectFactory.createFoodEntries();
        Recipe recipe = new Recipe("RECIPE", ingredients, "");

        assert recipe.getCustomServings().isEmpty();
    }
    
    @Test
    public void test04ARecipeHasANutritionalInformation() throws Exception {
        List<FoodEntry> ingredients = objectFactory.createFoodEntries();
        Recipe recipe = new Recipe("RECIPE", ingredients, "");
        
        double servingSize = 0.0;
        for(FoodEntry foodEntry : ingredients)
            servingSize += foodEntry.getAmount();

        assert recipe.getNutritionalInformation().getServingSize() == servingSize;
        for(Nutrient nutrient : Nutrient.values()){
            assert recipe.getNutritionalInformation().getAmountOf(nutrient).equals(getCorrectAmountOfNutrientFromListOfFoodEntry(nutrient, ingredients));
        }
    }
}
