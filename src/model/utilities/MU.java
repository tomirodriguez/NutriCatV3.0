package model.utilities;

import javax.measure.quantity.Energy;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Volume;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

/**
 * Created by ${tomirodriguez} on 01/10/14.
 */
public class MU {

    public static Unit<Energy> CALORIE = Unit.ONE.alternate("Cal");
    public static Unit<Energy> JOULE = SI.JOULE;
    public static Unit<Mass> IU = Unit.ONE.alternate("IU");
    public static Unit<Mass> GRAM = SI.GRAM;
    public static Unit<Mass> MILLIGRAM = SI.MILLI(GRAM);
    public static Unit<Mass> MICROGRAM = SI.MICRO(GRAM);
    public static Unit<Volume> LITER = NonSI.LITER;
    public static Unit<Volume> MILLILITER = SI.MILLI(LITER);
}
