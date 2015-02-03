package utilities;

import model.FoodEntry;
import model.Nutrient;

import java.util.List;

/**
 * Created by Usuario on 19/01/15.
 */
public abstract class TestsModel {

    protected ModelTestsObjectsFactory objectFactory = new ModelTestsObjectsFactory();

    protected void failTest() {
        assert false;
    }

    protected Double getCorrectAmountOfNutrientFromListOfFoodEntry(Nutrient nutrient, List<FoodEntry> ingredients) {
        Double value = null;
        for(FoodEntry ingredient : ingredients){
            if(ingredient.getAmountOf(nutrient) == null)
                continue;
            else if(value == null)
                value = ingredient.getAmountOf(nutrient);
            else
                value += ingredient.getAmountOf(nutrient);
        }
        return value;
    }
}
