package utilities;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Usuario on 19/01/15.
 */
public class ModelTestsObjectsFactory {

    public Double[] createNutrientsValues() {
        Double[] quantities = new Double[Nutrient.values().length];

        Random randomGenerator = new Random();
        for (Nutrient nutrient : Nutrient.values()) {
            try {
                quantities[nutrient.ordinal()] = randomGenerator.nextDouble() * 100;
            } catch (Exception e) {
                continue;
            }
        }

        return quantities;
    }

    public NutritionalInformation createNutritionalInformation() {
        try {
            return new NutritionalInformation(100, createNutrientsValues());
        } catch (Exception ex) {
            throw new NullPointerException("UNEXPECTED ERROR");
        }
    }

    public Food createFood() {
        return createFood("FOOD");
    }

    private Food createFood(String foodName) {
        try {
            return new Food(foodName, "GROUP", createNutritionalInformation());
        } catch (Exception ex) {
            throw new NullPointerException("UNEXPECTED ERROR");
        }
    }

    public Meal createMeal() {
        try{
            Meal meal = new Meal("MEAL");
            meal.add(new FoodEntry(createFood("FOOD 1"), 50));
            meal.add(new FoodEntry(createFood("FOOD 2"), 150));
            meal.add(new FoodEntry(createFood("FOOD 3"), 10));

            return meal;
        } catch (Exception ex){
            throw new NullPointerException("UNEXPECTED ERROR");
        }
    }

    public List<FoodEntry> createFoodEntries() {
        List<FoodEntry> foodEntries = new ArrayList<>(3);
        try {
            foodEntries.add(new FoodEntry(createFood("FOOD 1"), 50));
            foodEntries.add(new FoodEntry(createFood("FOOD 2"), 20));
            foodEntries.add(new FoodEntry(createFood("FOOD 3"), 25));
        } catch (Exception e) {
            throw new NullPointerException("UNEXPECTED ERROR");
        }
        return foodEntries;
    }
}
