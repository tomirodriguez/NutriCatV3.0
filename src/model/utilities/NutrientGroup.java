package model.utilities;

import model.Nutrient;

import java.util.List;

/**
 * Created by Usuario on 17/12/14.
 */
public interface NutrientGroup {

    public List<Nutrient> getNutrients();

    public NutrientGroup getNutrientGroup();
}
