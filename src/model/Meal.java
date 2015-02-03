package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 27/01/15.
 */
public class Meal {

    private String name;
    private List<FoodEntry> entries;

    public Meal(String name) throws Exception {
        if (name == null)
            throw new NullPointerException("NULL_MEAL_NAME");

        if (name.isEmpty())
            throw new Exception("MEAL_INVALID_NAME_MESSAGE");

        this.name = name;
        this.entries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<FoodEntry> getEntries() {
        return entries;
    }

    public void add(FoodEntry food) throws Exception {
        entries.add(food);
    }

    public Double getAmountOf(Nutrient nutrient) {
        Double amount = null;
        for(FoodEntry foodEntry : entries){
            if(foodEntry.getAmountOf(nutrient) == null)
                continue;
            else if(amount == null)
                amount = foodEntry.getAmountOf(nutrient);
            else
                amount += foodEntry.getAmountOf(nutrient);
        }
        return amount;
    }
}
