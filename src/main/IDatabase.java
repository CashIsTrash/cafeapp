package main;

import main.model.Drink;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface IDatabase {
    void addTable(String tableName);
    void addDrink(Double drinkPrice, String drinkName, String drinkCategory);
    void addDrinkToTable(Drink drink, int tableId);
    void addReceipt(String server, String date, int tableId);

    LinkedHashMap<Integer, String> getTables(); // LinkedHashMap removes duplicates
    LinkedHashMap<String, List<Drink>> getDrinks();
    LinkedHashMap<String, List<Drink>> getTableDrinks(int tableId);

    int getTableId(String tableName);
    int getDrinkId(String drinkName);

    double getTotalSumOfTable(String tableName);
}
