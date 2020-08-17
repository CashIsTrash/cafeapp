package main.model;

/**
 * Represents a Drink
 *
 * @author Marcus Cvjeticanin
 */
public class Drink {
    private int id;
    private String name;
    private final String category;
    private double price;

    /**
     * Creates an instance of Drink
     *
     * @param id : int - The ID of a drink.
     * @param name : String - The name of the drink.
     * @param category : String - The category of the drink.
     * @param price : double - The price of the drink.
     */
    public Drink(int id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    /**
     * Gets the ID of the drink.
     *
     * @return id : int - Returns the ID of the Drink.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of the drink.
     *
     * @param newId : int - The new ID of the drink.
     */
    public void setId(int newId) {
        this.id = newId;
    }

    /**
     * Gets the name of the drink.
     *
     * @return name : String - Returns the name of the Drink.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the drink.
     *
     * @param name : String - The new name of the drink.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the category of the drink.
     *
     * @return category : String - Returns the category of the Drink.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Gets the price of the drink.
     *
     * @return price : double - Returns the price of the Drink.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the drink.
     *
     * @param price : double - The new price of the drink.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
