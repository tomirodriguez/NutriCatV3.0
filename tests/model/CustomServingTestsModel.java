package model;

import org.junit.Test;
import utilities.TestsModel;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Usuario on 26/01/15.
 */
public class CustomServingTestsModel extends TestsModel {

    @Test
    public void test00CanNotCreateACustomServingWithNoName() throws Exception {
        try {
            new CustomServing(null, 0);
            failTest();
        } catch (NullPointerException exception) {
            assert exception.getMessage().equals("NULL_CUSTOMSERVING_NAME");
        }
    }

    @Test
    public void test01CanNotCreateACustomServingWithAZeroOrNegativaAmount() throws Exception {
        try {
            new CustomServing("", 0);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("CUSTOMSERVING_INVALID_AMOUNT_MESSAGE");
        }
    }

    @Test
    public void test02CanNotCreateACustomServingWithAnEmptyName() throws Exception {
        try {
            new CustomServing("", 10);
            failTest();
        } catch (Exception exception) {
            assert exception.getMessage().equals("CUSTOMSERVING_INVALID_NAME_MESSAGE");
        }
    }

    @Test
    public void test03ACustomServingHasTheCorrectValues() throws Exception {
        CustomServing customServing = new CustomServing("Una porcion", 20.0);

        assert customServing.getName().equals("Una porcion");
        assert customServing.getAmount() == 20;
    }

    @Test
    public void test04ACustomServingIsComparable() throws Exception {
        CustomServing thirdCustomServing = new CustomServing("Una porcion 2", 20.0);
        CustomServing fifthCustomServing = new CustomServing("Una porcion", 102.0);
        CustomServing secondCustomServing = new CustomServing("Una porcion 1", 20.0);
        CustomServing forthCustomSercing = new CustomServing("Una porcion", 55);
        CustomServing firstCustomServing = new CustomServing("Una porcion", 19.5);

        TreeSet<CustomServing> customServingTreeSet = new TreeSet<>();
        customServingTreeSet.add(thirdCustomServing);
        customServingTreeSet.add(fifthCustomServing);
        customServingTreeSet.add(secondCustomServing);
        customServingTreeSet.add(forthCustomSercing);
        customServingTreeSet.add(firstCustomServing);

        Iterator<CustomServing> customServingIterator = customServingTreeSet.iterator();

        assert customServingIterator.next().equals(firstCustomServing);
        assert customServingIterator.next().equals(secondCustomServing);
        assert customServingIterator.next().equals(thirdCustomServing);
        assert customServingIterator.next().equals(forthCustomSercing);
        assert customServingIterator.next().equals(fifthCustomServing);
        assert !customServingIterator.hasNext();
    }
}
