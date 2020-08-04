package main;

import java.util.HashMap;
import java.util.LinkedHashMap;

public interface IDatabase {
    void addTable(String tableName);
    void addDrink(Double drinkPrice, String drinkName, String drinkCategory);
    void addDrinkToTable(int tableId, int drinkId);
    void addReceipt(String server, String date, int tableId);

    LinkedHashMap<Integer, String> getTables();
    LinkedHashMap<String, String> getDrinks();
    // HashMap<String, String> getTableDrinks();

    // void removeAllTables();

    void createTables();
}
