package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 27/01/15.
 */
public class DayModel {

    private List<Meal> meals;
    private String name;

    public DayModel(String name) throws Exception {
        if(name == null)
            throw new NullPointerException("NULL_DAYMODEL_NAME");

        if(name.isEmpty())
            throw new Exception("DAYMODEL_INVALID_NAME_MESSAGE");

        this.name = name;
        this.meals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public boolean add(Meal meal) {
        return meals.add(meal);
    }

    public boolean remove(Meal meal) {
        return meals.remove(meal);
    }
}
