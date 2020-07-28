package main;

public interface IDatabase {
    void addTable(String tableName);
    void addDrink(Double drinkPrice, String drinkName, String drinkCategory);
    void addDrinkToTable(int tableId, int drinkId);
    void addReceipt(String server, String date, int tableId);
    void createTables();
}
