package main.model;

public class Receipt {
    private int id;
    private String tableName = null;
    private String server = null;
    private String date = null;
    private double price;

    public Receipt(int id, String tableName, String server, String date, double price) {
        this.id = id;
        this.tableName = tableName;
        this.server = server;
        this.date = date;
        this.price = price;
    }

    public int getId() { return this.id; }

    public void setId(int newId) { this.id  = newId; }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getServer() {
        return this.server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
