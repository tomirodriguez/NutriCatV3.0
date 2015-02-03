package database;

import model.CustomServing;
import model.Food;
import model.Nutrient;
import org.junit.Test;
import utilities.TestsModel;
import utilities.Utilities;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Usuario on 02/02/15.
 */
public class FoodDAOTest extends TestsModel {

    @Test
    public void test00CanNotCreateAFoodDAOWithANullConnection() throws Exception {
        try{
            new FoodDAO(null);
            failTest();
        } catch (NullPointerException exception){
            assert true;
        }
    }

    @Test
    public void test01AFoodDAOCanBeCreated() throws Exception {
        DataBaseConnection mock = mock(DataBaseConnection.class);
        new FoodDAO(mock);
        verify(mock).createFoodTable();
    }

    @Test
    public void test02AFoodDAOCanStoreAFoodInTheDataBase() throws Exception {
        DataBaseConnection dataBaseConnection = mock(DataBaseConnection.class);
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);

        FoodDAO foodDAO = new FoodDAO(dataBaseConnection);
        Food food = objectFactory.createFood();
        food.add(new CustomServing("Una porcion", 40));
        food.add(new CustomServing("Dos porciones", 80));

        String correctSql = "INSERT INTO FOOD VALUES (NULL, 'FOOD',100,'Una porcion#40~Dos porciones#80',";
        for(Nutrient nutrient : Nutrient.values())
            correctSql += Utilities.convertDoubleToString(food.getNutritionalInformation().getAmountOf(nutrient), new DecimalFormat("0.0")) + ",";
        correctSql = Utilities.removeLasCharacterFromString(correctSql) + ")";
        when(dataBaseConnection.getPrepareStatement(eq(correctSql), anyInt())).thenReturn(preparedStatement);


        FoodDTO foodDTO = foodDAO.insert(food);
        verify(dataBaseConnection).commit();

        assert foodDTO.getObject().equals(food);
        assert foodDTO.getId() == 1;
    }

    @Test
    public void test03CanNotInsertANullFoodToAFoodDAO() throws Exception {
        try{
            new FoodDAO(mock(DataBaseConnection.class)).insert(null);
            failTest();
        } catch (NullPointerException exception){
            assert exception.getMessage().equals("NULL_FOODDAO_FOOD_INSERT");
        }
    }

    @Test
    public void test04CanNotUpdateANullFoodToAFoodDAO() throws Exception {
        try{
            new FoodDAO(mock(DataBaseConnection.class)).update(null);
            failTest();
        } catch (NullPointerException exception){
            assert exception.getMessage().equals("NULL_FOODDAO_FOOD_UPDATE");
        }
    }

    @Test
    public void test05AFoodDTOCanBeUpdateInTheDatabase() throws Exception {
        DataBaseConnection dataBaseConnection = mock(DataBaseConnection.class);
        Statement statement = mock(Statement.class);
        when(dataBaseConnection.getStatement()).thenReturn(statement);

        FoodDAO foodDAO = new FoodDAO(dataBaseConnection);
        Food food = objectFactory.createFood();
        food.add(new CustomServing("Una porcion", 40));
        food.add(new CustomServing("Dos porciones", 80));
        FoodDTO foodDTO = new FoodDTO(food, 1);

        String correctSql = "UPDATE FOOD SET NAME = 'FOOD', SERVING_SIZE = 100, CUSTOM_SERVING_SIZE = 'Una porcion#40~Dos porciones#80', ";
        for(Nutrient nutrient : Nutrient.values()) {
            correctSql += nutrient.name() + " = ";
            correctSql += Utilities.convertDoubleToString(food.getNutritionalInformation().getAmountOf(nutrient), new DecimalFormat("0.0")) + ",";
        }
        correctSql = Utilities.removeLasCharacterFromString(correctSql) + " WHERE ID = 1";

        foodDAO.update(foodDTO);
        verify(dataBaseConnection).commit();
        verify(statement).execute(correctSql);
    }

    @Test
    public void test06AFoodDTOCanBeDeletedFromTheDatabase() throws Exception {
        DataBaseConnection dataBaseConnection = mock(DataBaseConnection.class);
        Statement statement = mock(Statement.class);
        when(dataBaseConnection.getStatement()).thenReturn(statement);

        FoodDAO foodDAO = new FoodDAO(dataBaseConnection);
        Food food = objectFactory.createFood();
        food.add(new CustomServing("Una porcion", 40));
        food.add(new CustomServing("Dos porciones", 80));
        FoodDTO foodDTO = new FoodDTO(food, 1);

        String correctSql = "DELETE FROM FOOD WHERE ID = 1";

        foodDAO.delete(foodDTO);
        verify(dataBaseConnection).commit();
        verify(statement).execute(correctSql);
    }

    @Test
    public void test07AFoodDAOCanListTheFoodsStoredInTheDatabase() throws Exception {
        DataBaseConnection dataBaseConnection = mock(DataBaseConnection.class);
        FoodDAO foodDAO = new FoodDAO(dataBaseConnection);
        List<FoodDTO> foods = foodDAO.getObjects();

    }
}
