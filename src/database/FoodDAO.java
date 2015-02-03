package database;

import model.CustomServing;
import model.Food;
import model.Nutrient;
import model.NutritionalInformation;
import utilities.Utilities;

import javax.measure.unit.SI;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Usuario on 02/02/15.
 */
public class FoodDAO extends DAO<Food> {

    public FoodDAO(DataBaseConnection dataBaseConnection) throws Exception {
        super(dataBaseConnection, dataBaseConnection.foodTableName);

        connection.createFoodTable();
    }

    @Override
    public List<FoodDTO> getObjects() throws Exception {
        List<FoodDTO> foods = new ArrayList<>();
        ResultSet resultSet = executeStatement("SELECT * FROM " + tableName);
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME").replace("''", "'");
                double servingSize = resultSet.getDouble("SERVING_SIZE");

                Double[] values = new Double[Nutrient.values().length];
                for (Nutrient nutrient : Nutrient.values()) {
                    double amount = resultSet.getDouble(nutrient.name());
                    if (!resultSet.wasNull())
                        values[nutrient.ordinal()] = amount;
                }

                Food food = new Food(name, "", new NutritionalInformation(servingSize, values));

                String customServingsString = resultSet.getString("CUSTOM_SERVING_SIZE");
                if (!resultSet.wasNull())
                    addCustomServings(food, customServingsString);

                foods.add(new FoodDTO(food, id));
            }
        return foods;
    }

    @Override
    public FoodDTO insert(Food food) throws Exception {
        if(food == null)
            throw new NullPointerException("NULL_FOODDAO_FOOD_INSERT");

        String sql = "INSERT INTO " + tableName + " VALUES (NULL, ";
        sql += "'" + food.getName().replace("'", "''") + "',";
        String servingSize = Utilities.convertDoubleToString(food.getNutritionalInformation().getServingSize(), new DecimalFormat("0"));
        sql += servingSize + ",";
        sql += customServingsToString(food) + ",";
        for(Nutrient value : Nutrient.values()){
            Double amount = food.getNutritionalInformation().getAmountOf(value);
            if(amount == null)
                sql += "NULL,";
            else
                sql += Utilities.convertDoubleToString(amount, new DecimalFormat("0.0")) + ",";
        }
        sql = Utilities.removeLasCharacterFromString(sql) + ")";

        int id = saveInDatabase(sql);
        return new FoodDTO(food, id);
    }

    @Override
    public void update(DTO<Food> foodDTO) throws Exception {
        if(foodDTO == null)
            throw new NullPointerException("NULL_FOODDAO_FOOD_UPDATE");

        String sql = "UPDATE " + tableName + " SET ";
        sql += "NAME = '" + foodDTO.getObject().getName() + "', ";
        sql += "SERVING_SIZE = " + Utilities.convertDoubleToString(foodDTO.getObject().getNutritionalInformation().getServingSize(), new DecimalFormat("0")) + ", ";
        sql += "CUSTOM_SERVING_SIZE = " + customServingsToString(foodDTO.getObject()) + ", ";

        for(Nutrient nutrient : Nutrient.values()){
            Double amount = foodDTO.getObject().getNutritionalInformation().getAmountOf(nutrient);
            if(amount != null)
                sql += nutrient.name() + " = " + Utilities.convertDoubleToString(amount, new DecimalFormat("0.0")) + ",";
        }
        sql = Utilities.removeLasCharacterFromString(sql);
        sql += " WHERE ID = " + foodDTO.getId();

        executeStatement(sql);
    }

    @Override
    public void delete(DTO<Food> foodDTO) throws Exception {
        executeStatement("DELETE FROM " + tableName + " WHERE ID = " + foodDTO.getId());
    }

    private String customServingsToString(Food food) {
        if(food.getCustomServings().isEmpty())
            return "NULL";

        String customServingsToString = "'";
        for(CustomServing customServing : food.getCustomServings()){
            String amount = Utilities.convertDoubleToString(customServing.getAmount(), new DecimalFormat("0"));
            customServingsToString += customServing.getName() + "#" + amount + "~";
        }
        return Utilities.removeLasCharacterFromString(customServingsToString) + "'";
    }

    private void addCustomServings(Food food, String customServingsString) throws Exception {
        String[] customServingString = customServingsString.split("~");
        for(String customServing : customServingString){
            String[] spplitedCustomServing = customServing.split("#");
            String name = spplitedCustomServing[0].replace("''", "'");
            double amount = Double.valueOf(spplitedCustomServing[1]);
            food.add(new CustomServing(name, amount));
        }
    }
}
