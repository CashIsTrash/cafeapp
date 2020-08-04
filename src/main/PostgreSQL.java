package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

            // Creates receipt
            String sql = "INSERT INTO cafe.receipts VALUES (DEFAULT, " +
                    "\'" + server + "\', \'" + date + "\');";
            stmt.executeUpdate(sql);

            // Gets the newly created receipt id
            Statement stmtQuery = c.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            String sql2 = "SELECT * FROM cafe.receipts ORDER BY id DESC LIMIT 1";
            ResultSet rs = stmtQuery.executeQuery(sql2);
            rs.last();
            int receiptId = rs.getInt("id");

            // Creates row in receipts_tables with receipt id and table id
            String sql3 = "INSERT INTO cafe.receipts_tables VALUES (" + receiptId + ", " + tableId + ")";
            stmt.executeUpdate(sql3);

            stmtQuery.close();
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
    public LinkedHashMap<Integer, String> getTables() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(connectionUrl, connectionUser, connectionPwd);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            // Gets the newly created receipt id
            Statement stmtQuery = c.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            LinkedHashMap<Integer, String> allTables = new LinkedHashMap<>();
            String sql = "SELECT * FROM cafe.tables;";
            ResultSet rs = stmtQuery.executeQuery(sql);
            while (rs.next()) {
                allTables.put(rs.getInt("id"), rs.getString("table_name"));
            }

            stmtQuery.close();
            c.commit();
            c.close();

            return allTables;
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Row inserted successfully");

        return null;
    }

    @Override
    public LinkedHashMap<String, String> getDrinks() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(connectionUrl, connectionUser, connectionPwd);
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            // Gets the newly created receipt id
            Statement stmtQuery = c.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            LinkedHashMap<String, String> allDrinks = new LinkedHashMap<>();
            String sql = "SELECT * FROM cafe.drinks;";
            ResultSet rs = stmtQuery.executeQuery(sql);
            while (rs.next()) {
                allDrinks.put(rs.getString("drink_name"), rs.getString("drink_category"));
            }

            stmtQuery.close();
            c.commit();
            c.close();

            return allDrinks;
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Row inserted successfully");

        return null;
    }

    public void createTables() {

    }
}
