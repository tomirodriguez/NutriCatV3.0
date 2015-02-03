package model;

import org.junit.Test;
import utilities.TestsModel;

import java.util.TreeSet;

/**
 * Created by Usuario on 26/01/15.
 */
public class FoodTestsModel extends TestsModel {

    @Test
    public void test00CanNotCreateAFoodWithNoName() throws Exception {
        try {
            new Food(null, null, objectFactory.createNutritionalInformation());
            failTest();
        } catch (NullPointerException exception) {
            assert exception.getMessage().equals("NULL_FOOD_NAME");
        }
    }

    @Test
    public void test00CanNotCreateAFoodWithNullCustomServings() throws Exception {
        try {
            new Food("Food", "", objectFactory.createNutritionalInformation(), null);
            failTest();
        } catch (NullPointerException exception) {
            assert exception.getMessage().equals("NULL_MEALELEMENT_CUSTOMSERVINGS");
        }
    }

    @Test
    public void test00CanNotCreateAFoodWithNoGroup() throws Exception {
        try {
            new Food("FOOD", null, objectFactory.createNutritionalInformation());
            failTest();
        } catch (Exception exception) {
            assert exception.getClass().equals(NullPointerException.class);
            assert exception.getMessage().equals("NULL_FOOD_GROUP");
        }
    }

    @Test
    public void test00CanNotCreateAFoodWithNoNutritionalInformation() throws Exception {
        try {
            new Food("FOOD", "GROUP", null);
            failTest();
        } catch (Exception exception) {
            assert exception.getClass().equals(NullPointerException.class);
            assert exception.getMessage().equals("NULL_FOOD_NUTRIENTS");
        }
    }

    @Test
    public void test01CanNotCreateAFoodWithAnEmptyName() throws Exception {
        try {
            new Food("", "GROUP", objectFactory.createNutritionalInformation());
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("FOOD_INVALID_NAME_MESSAGE");
        }
    }

    @Test
    public void test02AFoodContainsTheCorrectValues() throws Exception {
        NutritionalInformation nutritionalInformation = objectFactory.createNutritionalInformation();
        Food food = new Food("FOOD", "GROUP", nutritionalInformation);

        assert food.getName().equals("FOOD");
        assert food.getGroup().equals("GROUP");
        assert food.getNutritionalInformation().equals(nutritionalInformation);
    }

    @Test
    public void test03AFoodHasNoCustomServingsWhenCreated() throws Exception {
        NutritionalInformation nutritionalInformation = objectFactory.createNutritionalInformation();
        Food food = new Food("FOOD", "GROUP", nutritionalInformation);

        assert food.getCustomServings().isEmpty();
    }

    @Test
    public void test04AFoodCanBeCreatedWithCustomServings() throws Exception {
        NutritionalInformation nutritionalInformation = objectFactory.createNutritionalInformation();
        TreeSet<CustomServing> customServings = new TreeSet<>();
        customServings.add(new CustomServing("Una porcion", 66));

        Food food = new Food("FOOD", "GROUP", nutritionalInformation, customServings);

        assert food.getCustomServings().equals(customServings);
    }

    @Test
    public void test05ACustomServingCanBeAddedToTheFood() throws Exception {
        Food food = new Food("FOOD", "GROUP", objectFactory.createNutritionalInformation());
        CustomServing customServing = new CustomServing("PORICON", 55);

        assert food.add(customServing);
        assert food.getCustomServings().size() == 1;
        assert food.getCustomServings().contains(customServing);
    }

    @Test
    public void test06CanNotAddADuplicatedCustomServingToAFood() throws Exception {
        Food food = new Food("FOOD", "GROUP", objectFactory.createNutritionalInformation());
        CustomServing customServing = new CustomServing("PORCION", 55);
        food.add(customServing);

        assert !food.add(new CustomServing("PORCION", 55));
        assert food.getCustomServings().size() == 1;
        assert food.getCustomServings().contains(customServing);
    }

    @Test
    public void test07ACustomServingCanBeRemovedFromAFood() throws Exception {
        Food food = new Food("FOOD", "GROUP", objectFactory.createNutritionalInformation());
        CustomServing customServing = new CustomServing("PORCION", 55);
        food.add(customServing);

        assert food.remove(customServing);
        assert food.getCustomServings().isEmpty();
    }

    @Test
    public void test08CanNotRemoveACustomServingThatIsNotAddedInTheFood() throws Exception {
        Food food = new Food("FOOD", "GROUP", objectFactory.createNutritionalInformation());
        CustomServing customServing = new CustomServing("PORCION", 55);

        assert !food.remove(customServing);
        assert food.getCustomServings().isEmpty();
    }

}
