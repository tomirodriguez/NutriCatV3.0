package model;


import java.util.List;
import java.util.TreeSet;

/**
 * Created by Usuario on 27/01/15.
 */
public class Recipe extends MealElement {

    private String name;
    private List<FoodEntry> ingredients;
    private String description;

    public Recipe(String name, List<FoodEntry> ingredients, String description) throws Exception {
        super(createNutritionalInformation(ingredients), new TreeSet<CustomServing>());

        if(name == null)
            throw new NullPointerException("NULL_RECIPE_NAME");
        else if(description == null)
            throw new NullPointerException("NULL_RECIPE_DESCRIPTION");

        if(name.isEmpty())
            throw new Exception("RECIPE_INVALID_NAME_MESSAGE");

        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
    }

    private static NutritionalInformation createNutritionalInformation(List<FoodEntry> ingredients) throws Exception {
        if(ingredients == null)
            throw new NullPointerException("NULL_RECIPE_INGREDIENTS");
        if(ingredients.isEmpty())
            throw new Exception("RECIPE_EMPTY_FOODENTRY_MESSAGE");

        double servingSize = 0.0;
        Double[] nutrientsValues = new Double[Nutrient.values().length];

        for(Nutrient nutrient : Nutrient.values()){
            for(FoodEntry ingredient : ingredients){
                if(ingredient.getAmountOf(nutrient) == null)
                    continue;
                else if(nutrientsValues[nutrient.ordinal()] == null)
                    nutrientsValues[nutrient.ordinal()] = ingredient.getAmountOf(nutrient);
                else
                    nutrientsValues[nutrient.ordinal()] += ingredient.getAmountOf(nutrient);
            }
        }

        for(FoodEntry ingredient : ingredients)
            servingSize += ingredient.getAmount();

        return new NutritionalInformation(servingSize, nutrientsValues);
    }

    public String getName() {
        return name;
    }

    public List<FoodEntry> getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }
}
