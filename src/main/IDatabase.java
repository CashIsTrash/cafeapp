package main;

import main.model.Drink;
import main.model.Receipt;

import java.util.LinkedHashMap;
import java.util.List;

public interface IDatabase {
    // Add data
    void addTable(String tableName);
    void addDrink(Double drinkPrice, String drinkName, String drinkCategory);
    void addDrinkToTable(Drink drink, int tableId);
    void addReceipt(String server, String date, int tableId, double totalSum);

    // Retrieve data
    LinkedHashMap<Integer, String> getTables(); // LinkedHashMap removes duplicates
    LinkedHashMap<String, List<Drink>> getDrinks();
    LinkedHashMap<String, List<Drink>> getTableDrinks(int tableId);
    LinkedHashMap<String, List<Drink>> getAllSoldDrinks();
    LinkedHashMap<String, List<Receipt>> getAllReceipts();
    int getTableId(String tableName);
    int getDrinkId(String drinkName);
    double getTotalSumOfTable(String tableName);
    LinkedHashMap<String, LinkedHashMap<String, Double>> getStatistics();

    // Remove data
    void removeTable(String tableName);
    void removeDrinkFromTable(String tableName, Drink drink);
    void removeAllDrinksFromTable(int tableId);
    void removeReceipt(Receipt receipt);
}
