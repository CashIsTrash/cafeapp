package main;

import main.model.Drink;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface IDatabase {
    void addTable(String tableName);
    void addDrink(Double drinkPrice, String drinkName, String drinkCategory);
    void addDrinkToTable(int tableId, int drinkId);
    void addReceipt(String server, String date, int tableId);

    LinkedHashMap<Integer, String> getTables(); // LinkedHashMap removes duplicates
    LinkedHashMap<String, String> getDrinks();
    LinkedHashMap<String, List<Drink>> getTableDrinks(int tableId);

    int getTableId(String tableName);

    // void removeAllTables();

    void createTables();
}
