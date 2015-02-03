package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Usuario on 02/02/15.
 */
public abstract class MealElement {

    private Set<CustomServing> customServings;
    private NutritionalInformation nutritionalInformation;

    public MealElement(NutritionalInformation nutritionalInformation){
        this(nutritionalInformation, new TreeSet<CustomServing>());
    }

    public MealElement(NutritionalInformation nutritionalInformation, Set<CustomServing> customServings){
        if(customServings == null)
            throw new NullPointerException("NULL_MEALELEMENT_CUSTOMSERVINGS");
        else if (nutritionalInformation == null)
            throw new NullPointerException("NULL_FOOD_NUTRIENTS");

        this.customServings = customServings;
        this.nutritionalInformation = nutritionalInformation;
    }

    public Set<CustomServing> getCustomServings() {
        return customServings;
    }

    public boolean add(CustomServing customServing) {
        return customServings.add(customServing);
    }

    public boolean remove(CustomServing customServing) {
        return customServings.remove(customServing);
    }

    public NutritionalInformation getNutritionalInformation() {
        return nutritionalInformation;
    }
}
