package model;

/**
 * Created by Usuario on 26/01/15.
 */
public class CustomServing implements Comparable<CustomServing> {

    private String name;
    private double amount;

    public CustomServing(String name, double amount) throws Exception {
        if (name == null)
            throw new NullPointerException("NULL_CUSTOMSERVING_NAME");

        if (amount <= 0)
            throw new Exception("CUSTOMSERVING_INVALID_AMOUNT_MESSAGE");
        else if (name.isEmpty())
            throw new Exception("CUSTOMSERVING_INVALID_NAME_MESSAGE");

        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(CustomServing o) {
        int comparation = Double.compare(amount, o.amount);
        if (comparation != 0)
            return comparation;
        else return name.compareTo(o.name);
    }
}
