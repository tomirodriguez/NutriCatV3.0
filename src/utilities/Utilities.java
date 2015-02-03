package utilities;

import database.FoodDTO;

import java.text.DecimalFormat;

/**
 * Created by Usuario on 03/02/15.
 */
public class Utilities {

    public static String convertDoubleToString(double numberToConvert, DecimalFormat decimalFormat){
        return decimalFormat.format(numberToConvert).replace(".","").replace(",",".");
    }

    public static String removeLasCharacterFromString(String string){
        return string.substring(0, string.length()-1);
    }
}
