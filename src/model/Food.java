package model;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Usuario on 26/01/15.
 */
public class Food extends MealElement {

    private String name;
    private String group;

    public Food(String name, String group, NutritionalInformation nutritionalInformation) throws Exception {
        this(name, group, nutritionalInformation, new TreeSet<CustomServing>());
    }

    public Food(String name, String group, NutritionalInformation nutritionalInformation, Set<CustomServing> customServings) throws Exception {
        super(nutritionalInformation, customServings);

        if (name == null)
            throw new NullPointerException("NULL_FOOD_NAME");
        else if (group == null)
            throw new NullPointerException("NULL_FOOD_GROUP");

        if (name.isEmpty())
            throw new Exception("FOOD_INVALID_NAME_MESSAGE");

        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }
}
