package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreSQL implements IDatabase {
    private Connection c;
    private Statement stmt;
    private final String connectionUrl = "jdbc:postgresql://localhost:5432/2dv513_a3";
    private final String connectionUser = "postgres";
    private final String connectionPwd = "test1234";

    public PostgreSQL() {
        c = null;
        stmt = null;
    }

    @Override
    public void addTable(String tableName) {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(connectionUrl, connectionUser, connectionPwd);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO cafe.tables VALUES (DEFAULT, \'" + tableName + "\');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Row inserted successfully");
    }

    @Override
    public void addDrink(Double drinkPrice, String drinkName, String drinkCategory) {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(connectionUrl, connectionUser, connectionPwd);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO cafe.drinks VALUES (DEFAULT, \'" +
                    drinkPrice + "\', \'" + drinkName + "\', \'" + drinkCategory + "\');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Row inserted successfully");
    }

    @Override
    public void addDrinkToTable(int tableId, int drinkId) {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(connectionUrl, connectionUser, connectionPwd);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO cafe.tables_drinks VALUES (\'" +
                    tableId + "\', \'" + drinkId + "\');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Row inserted successfully");
    }

    @Override
    public void addReceipt(String server, String date, int tableId) {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(connectionUrl, connectionUser, connectionPwd);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO cafe.tables_drinks VALUES (DEFAULT, " +
                    "\'" + server + "\', " + date + "\');";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Row inserted successfully");
    }

    public void createTables() {

    }
}
