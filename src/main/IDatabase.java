package main;

import main.model.Drink;
import main.model.Receipt;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * IDatabase interface.
 *
 * @author Marcus Cvjeticanin
 */
public interface IDatabase {

    /**
     * Add a table
     *
     * @param tableName : String - Takes the table name
     */
    void addTable(String tableName);

    /**
     * Add a drink
     *
     * @param drinkPrice : double - Drink Price as double precision.
     * @param drinkName : String - The name of the drink.
     * @param drinkCategory : String - The category of the drink.
     */
    void addDrink(Double drinkPrice, String drinkName, String drinkCategory);

    /**
     * Add a drink to a specific table
     *
     * @param drink : Drink - a Drink object.
     * @param tableId : int - The ID of a table.
     */
    void addDrinkToTable(Drink drink, int tableId);

    /**
     * Add a receipt.
     *
     * @param server : String - The name of the server.
     * @param date : String - The date in string format.
     * @param tableId : int - The ID of a table.
     * @param totalSum : double - The total sum in double precision.
     */
    void addReceipt(String server, String date, int tableId, double totalSum);

    /**
     * Get all tables.
     *
     * @return allTables : LinkedHashMap<Integer, String> - Returns a LinkedHashMap with the ID and the table name.
     */
    LinkedHashMap<Integer, String> getTables();

    /**
     * Get all drinks.
     *
     * @return allDrinks : LinkedHashMap<String, List<Drink>> - Returns a LinkedHashMap with a list of all drinks as Drink objects.
     */
    LinkedHashMap<String, List<Drink>> getDrinks();

    /**
     * Get all drinks of a specific table.
     *
     * @param tableId : int - The ID of a table.
     * @return allDrinks : LinkedHashMap<String, List<Drink>> - Returns a LinkedHashMap with a list of all drinks as Drink objects.
     */
    LinkedHashMap<String, List<Drink>> getTableDrinks(int tableId);

    /**
     * Get all sold drinks of all tables.
     *
     * @return allDrinks : LinkedHashMap<String, List<Drink>> - Returns a LinkedHashMap with a list of all drinks as Drink objects.
     */
    LinkedHashMap<String, List<Drink>> getAllSoldDrinks();

    /**
     * Get all receipts.
     *
     * @return allReceipts : LinkedHashMap<String, List<Receipt>> - Returns a LinkedHashMap with a list of all receipts as Receipt objects.
     */
    LinkedHashMap<String, List<Receipt>> getAllReceipts();

    /**
     * Get ID of a specific table.
     *
     * @param tableName : String - The ID of a table.
     * @return allReceipts : int - Returns the ID of a specific table name.
     */
    int getTableId(String tableName);

    /**
     * Get the ID of a specific drink name.
     *
     * @param drinkName : String - The name of the drink.
     * @return tableId : int - Returns the ID of a specific drink name.
     */
    int getDrinkId(String drinkName);

    /**
     * Get the total sum of a specific table.
     *
     * @param tableName : String - The name of the table.
     * @return sum : double - Returns the total sum of a table.
     */
    double getTotalSumOfTable(String tableName);

    /**
     * Get all statistics.
     *
     * @return stats : LinkedHashMap<String, LinkedHashMap<String, Double>> - Returns all statistics.
     */
    LinkedHashMap<String, LinkedHashMap<String, Double>> getStatistics();

    /**
     * Remove a specific table.
     *
     * @param tableName : String - The table name.
     */
    void removeTable(String tableName);

    /**
     * Remove a drink from a specific table.
     *
     * @param tableName : String - The table name.
     * @param drink : Drink - a Drink object.
     */
    void removeDrinkFromTable(String tableName, Drink drink);

    /**
     * Remove all drinks from a specific table.
     *
     * @param tableId : int - The ID of a table.
     */
    void removeAllDrinksFromTable(int tableId);

    /**
     * Remove a receipt.
     *
     * @param receipt : Receipt - A Receipt object.
     */
    void removeReceipt(Receipt receipt);
}
