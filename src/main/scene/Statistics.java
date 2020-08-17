package main.scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.PostgreSQL;
import main.model.Drink;

import java.util.LinkedHashMap;
import java.util.List;

public class Statistics {
    private final PostgreSQL p = new PostgreSQL();
    private VBox space = null;
    private VBox allDrinks = null;
    private TableView<Drink> drinksTableView = null;
    private final Button backBtn = new Button ("Go Back");

    public Statistics(Stage primaryStage) {
        space = new VBox(25);
        space.setPadding(new Insets(25, 25, 25, 25));

        allDrinks = new VBox(25);
        drinksTableView = new TableView<>();

        this.setInitialData();
        this.eventListeners(primaryStage);
    }

    public VBox getNode() {
        return this.space;
    }

    public void setInitialData() {
        int minMaxWidthBtn = 250;
        int minMaxHeightBtn = 250;
        String smBtnFontSize = "-fx-font-size:20";

        backBtn.setMinHeight(minMaxHeightBtn);
        backBtn.setMaxHeight(minMaxHeightBtn);
        backBtn.setMinWidth(minMaxWidthBtn);
        backBtn.setMaxWidth(minMaxWidthBtn);
        backBtn.setStyle(smBtnFontSize);

        // Add entries to TableView receiptTableView GUI
        LinkedHashMap<String, List<Drink>> tableDrinks = p.getAllSoldDrinks();
        for (List<Drink> l : tableDrinks.values()) {
            for (Drink drink : l) drinksTableView.getItems().add(drink);
        }

        drinksTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn<Drink, String> drinkCol1 = new TableColumn<>("Drink");
        TableColumn<Drink, String> drinkCol2 = new TableColumn<>("Category");
        TableColumn<Drink, String> drinkCol3 = new TableColumn<>("Price");
        drinkCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        drinkCol2.setCellValueFactory(new PropertyValueFactory<>("category"));
        drinkCol3.setCellValueFactory(new PropertyValueFactory<>("price"));
        drinksTableView.getColumns().addAll(drinkCol1, drinkCol2, drinkCol3);

        allDrinks.getChildren().addAll(drinksTableView);

        // Table Price Texts
        LinkedHashMap<String, LinkedHashMap<String, Double>> stats = p.getStatistics();
        double total_revenue = stats.get("stats").get("total_revenue");
        double drink_p_avg = stats.get("stats").get("drink_price_average");
        int sold_drinks = stats.get("stats").get("sold_drinks").intValue();

        Text revenueText = new Text(25, 25, "Total Revenue: " + total_revenue);
        Text drinkPriceAverageText = new Text(25, 25, "Drink Price Avg: " + drink_p_avg);
        Text soldDrinksText = new Text(25, 25, "Sold Drinks: " + sold_drinks);
        revenueText.setFont(Font.font(null, FontWeight.BOLD, 32));
        drinkPriceAverageText.setFont(Font.font(null, FontWeight.BOLD, 32));
        soldDrinksText.setFont(Font.font(null, FontWeight.BOLD, 32));

        space.getChildren().addAll(
                allDrinks, revenueText, drinkPriceAverageText, soldDrinksText, backBtn
        );
    }

    public void eventListeners(Stage primaryStage) {
        backBtn.setOnAction(click -> {
            Root root = new Root(primaryStage);
            primaryStage.setScene(new Scene(root.getNode(), 1920, 1080));
        });
    }
}
