package database;

import model.CustomServing;
import model.Food;
import model.NutritionalInformation;

import java.util.Set;

/**
 * Created by Usuario on 03/02/15.
 */
public class FoodDTO extends DTO<Food> {

    public FoodDTO(Food food, int id) {
        super(food, id);
    }

    public String getName() {
        return getObject().getName();
    }

    public String getGroup() {
        return getObject().getGroup();
    }

    public Set<CustomServing> getCustomServings() {
        return getObject().getCustomServings();
    }

    public boolean add(CustomServing customServing) {
        return getObject().add(customServing);
    }

    public boolean remove(CustomServing customServing) {
        return getObject().remove(customServing);
    }

    public NutritionalInformation getNutritionalInformation() {
        return getObject().getNutritionalInformation();
    }
}
