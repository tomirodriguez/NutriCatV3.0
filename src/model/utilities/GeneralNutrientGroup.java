package model.utilities;

import model.Nutrient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 17/12/14.
 */
public enum GeneralNutrientGroup implements NutrientGroup {

    ENERGY_INFORMATION,
    CARBOHYDRATES,
    FAT_AND_FATTY_ACIDS,
    PROTEIN_AND_AMINO_ACIDS,
    VITAMINS,
    MINERALS,
    STEROLS,
    OTHERS;

    @Override
    public List<Nutrient> getNutrients() {
        List<Nutrient> nutrientList = new ArrayList<>();
        for (Nutrient nutrient : Nutrient.values())
            if (nutrient.getNutrientGroup().equals(this))
                nutrientList.add(nutrient);

        return nutrientList;
    }

    @Override
    public NutrientGroup getNutrientGroup() {
        return null;
    }
}
