package model;

import org.junit.Test;
import utilities.TestsModel;

/**
 * Created by Usuario on 27/01/15.
 */
public class DayTestsModel extends TestsModel {

    @Test
    public void test00CanNotCreateADayModelWithNoName() throws Exception {
        try{
            new DayModel(null);
            failTest();
        } catch (NullPointerException exception){
            assert exception.getMessage().equals("NULL_DAYMODEL_NAME");
        }
    }

    @Test
    public void test01CanNotCreateADayModelWithAnEmptyName() throws Exception {
        try{
            new DayModel("");
            failTest();
        } catch (Exception exception){
            assert exception.getMessage().equals("DAYMODEL_INVALID_NAME_MESSAGE");
        }
    }

    @Test
    public void test02ADayModelHasTheCorrectNameWhenCreated() throws Exception {
        DayModel dayModel = new DayModel("DAYMODEL");

        assert dayModel.getName().equals("DAYMODEL");
    }

    @Test
    public void test03ADayModelHasNoMealsWhenCreated() throws Exception {
        DayModel dayModel = new DayModel("DAYMODEL");

        assert dayModel.getMeals().isEmpty();
    }

    @Test
    public void test04AMealCanBeAddedToADayModel() throws Exception {
        DayModel dayModel = new DayModel("DAYMODEL");
        Meal meal = objectFactory.createMeal();

        assert dayModel.add(meal);
        assert dayModel.getMeals().size() == 1;
        assert dayModel.getMeals().contains(meal);
    }

    @Test
    public void test05AMealCanBeRemovedFromADayModel() throws Exception {
        DayModel dayModel = new DayModel("DAYMODEL");
        Meal meal = objectFactory.createMeal();
        dayModel.add(meal);

        assert dayModel.remove(meal);
        assert dayModel.getMeals().isEmpty();
    }
}
