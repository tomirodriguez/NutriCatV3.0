package database;

import model.Food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Usuario on 02/02/15.
 */
public abstract class DAO<T> {

    protected DataBaseConnection connection;
    protected String tableName;

    public DAO(DataBaseConnection connection, String tableName){
        if(connection == null)
            throw new NullPointerException("NULL_DAO_CONNECTION");

        this.tableName = tableName;
        this.connection = connection;
    }

    public abstract List<FoodDTO> getObjects() throws Exception;

    public abstract DTO<T> insert(T objectToInsert) throws Exception;

    public abstract void update(DTO<T> objectToUpdate) throws Exception;

    public abstract void delete(DTO<T> foodDTO) throws Exception;

    protected int saveInDatabase(String sql) throws Exception {
        int generatedId = -1;

        PreparedStatement preparedStatement = connection.getPrepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.execute();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        try {
            connection.commit();
            if (generatedKeys.next()) {
                generatedId =  generatedKeys.getInt(1);
            } else {
                throw new Exception("DAO_ERROR_GENERATING_ID_FOR_DATABASE");
            }
        } catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            throw new Exception("DAO_ROLLBACK_INSERT_ERROR_MESSAGE");
        } finally {
            preparedStatement.close();
        }

        return generatedId;
    }

    protected ResultSet executeStatement(String sql) throws Exception {
        Statement statement= connection.getStatement();
        statement.execute(sql);
        try {
            connection.commit();
        } catch (Exception e){
            e.printStackTrace();
            connection.rollback();
            throw new Exception("DAO_ROLLBACK_UPDATE_ERROR_MESSAGE");
        } finally {
            statement.close();
        }

        return statement.getResultSet();
    }
}
