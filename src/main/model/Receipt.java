package main.model;

/**
 * Represents a Receipt
 *
 * @author Marcus Cvjeticanin
 */
public class Receipt {
    private int id;
    private String tableName = null;
    private String server = null;
    private String date = null;
    private double price;

    /**
     * Creates an instance of Receipt
     *
     * @param id : int - The ID of a receipt.
     * @param tableName : String - The table name.
     * @param server : String - The server.
     * @param date : String - The date.
     * @param price : double - The price.
     */
    public Receipt(int id, String tableName, String server, String date, double price) {
        this.id = id;
        this.tableName = tableName;
        this.server = server;
        this.date = date;
        this.price = price;
    }

    /**
     * Gets the ID of the receipt.
     *
     * @return id : int - Returns the ID of the receipt.
     */
    public int getId() { return this.id; }

    /**
     * Sets a new ID of the receipt.
     *
     * @param newId : String - New ID.
     */
    public void setId(int newId) { this.id  = newId; }

    /**
     * Gets the table name of the receipt.
     *
     * @return tableName : String - Returns the table name of the receipt.
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Sets a new table name of the receipt.
     *
     * @param tableName : String - New table name.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets the server of the receipt.
     *
     * @return server : String - Returns the server of the receipt.
     */
    public String getServer() {
        return this.server;
    }

    /**
     * Sets a new server of the receipt.
     *
     * @param server : String - New server.
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Gets the date of the receipt.
     *
     * @return date : String - Returns the date of the receipt.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Sets a new date of the receipt.
     *
     * @param date : String - New date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the price of the receipt.
     *
     * @return price : double - Returns the price of the receipt.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets a new price of the receipt.
     *
     * @param price : double - New price.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
