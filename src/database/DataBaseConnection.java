package database;

import model.Nutrient;

import java.sql.*;

/**
 * Created by Tomi Rodriguez on 02/11/2014.
 */
public class DataBaseConnection {

    private Connection connection;

    public final String foodTableName = "FOOD";

    public void openConnection() throws Exception {
        String localPath = System.getProperty("user.dir");
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(
                "jdbc:h2:" + localPath + "/food_data_base",
                "food", "foodpass");
        connection.setAutoCommit(false);
        System.out.println("Conexion establecida");
    }

    public boolean createPatchesTable(String tableName) throws Exception {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, tableName, null);
        if (!rs.next()) {
            Statement stat = connection.createStatement();
            String code = "create table " + tableName + " (" +
                    "ID INT NOT NULL, " +
                    "VERSION NUMBER NOT NULL, " +
                    "PRIMARY KEY (ID) )";
            stat.execute(code);
            stat.close();
            connection.commit();
            System.out.println("Tabla " + tableName + " creada");
            return true;
        }
        return false;
    }

    public void createFoodTable() throws Exception {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, foodTableName, null);
        if (!rs.next()) {
            Statement stat = connection.createStatement();
            String code = "create table " + foodTableName + " (" +
                    "ID INT NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL, " +
                    "SERVING_SIZE NUMBER NOT NULL, " +
                    "CUSTOM_SERVING_SIZE VARCHAR(1000) default NULL, ";
            for (Nutrient value : Nutrient.values()) {
                code += value.name() + " NUMBER default NULL,";
            }
            code += "PRIMARY KEY (ID) )";
            stat.execute(code);
            stat.close();
            connection.commit();
            System.out.println("Tabla " + foodTableName + " creada");
        }
    }

    public void createMealTable(String tableName) throws Exception {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, tableName, null);
        if (!rs.next()) {
            Statement stat = connection.createStatement();
            String code = "create table "+ tableName + " (" +
                    "ID int NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL, " +
                    "FOOD_ENTRIES VARCHAR(2000) default NULL, " +
                    "PRIMARY KEY (ID) )";
            stat.execute(code);
            stat.close();
            connection.commit();
            System.out.println("Tabla " + tableName + " creada");
        }
    }

    public void createRecipesTable(String tableName) throws Exception {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, tableName, null);
        if (!rs.next()) {
            Statement stat = connection.createStatement();
            String code = "create table "+ tableName + " (" +
                    "ID int NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL, " +
                    "CUSTOM_SERVING VARCHAR(30) NOT NULL, " +
                    "FOOD_ENTRIES VARCHAR(2000) default NULL, " +
                    "PRIMARY KEY (ID) )";
            stat.execute(code);
            stat.close();
            connection.commit();
            System.out.println("Tabla " + tableName + " creada");
        }
    }

    public void createDayModelTable(String tableName) throws Exception {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, tableName, null);
        if (!rs.next()) {
            Statement stat = connection.createStatement();
            String code = "create table "+ tableName + " (" +
                    "ID int NOT NULL AUTO_INCREMENT, " +
                    "NAME VARCHAR(255) NOT NULL, " +
                    "MEALS VARCHAR(30) default NULL, " +
                    "PRIMARY KEY (ID) )";
            stat.execute(code);
            stat.close();
            connection.commit();
            System.out.println("Tabla " + tableName + " creada");
        }
    }

    public void closeConnection() throws Exception {
        connection.close();
        System.out.println("Conexion cerrada");
    }

    public void commit() throws SQLException {
        connection.commit();
    }

    public void rollback() throws SQLException {
        connection.rollback();
    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement getPrepareStatement(String sql, int returnGeneratedKeys) throws SQLException {
        return connection.prepareStatement(sql, returnGeneratedKeys);
    }

    public boolean isClosed() throws SQLException {
        return connection.isClosed();
    }
}
