package main.scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.PostgreSQL;
import main.model.Drink;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Table stage
 *
 * @author Marcus Cvjeticanin
 */
public class Table {
    private final PostgreSQL p = new PostgreSQL();
    private final HBox space;
    private final VBox left;
    private final VBox right;
    private GridPane allButtons = null;
    private TableView<Drink> drinksTableView = null;
    private final Button printReceipt = new Button("Print Receipt");
    private final Button removeTable = new Button("Remove Table");
    private final Button removeDrinkBtn = new Button("Remove Drink");
    private final Button backBtn = new Button ("Go Back");
    private final String tableName;

    private int colIndex;
    private int rowIndex;
    private double sum;

    /**
     * Creates an instance of Table
     *
     * @param tn : String - The table name.
     * @param primaryStage : Stage - The Stage object.
     */
    public Table(String tn, Stage primaryStage) {
        tableName = tn;

        space = new HBox();
        left = new VBox();
        right = new VBox();
        allButtons = new GridPane();
        drinksTableView = new TableView<>();

        space.setPadding(new Insets(10, 10, 10, 10));
        right.setPadding(new Insets(10, 10, 10, 10));
        left.setPadding(new Insets(10, 10, 10, 10));

        drinksTableView.setMinWidth(1060);
        drinksTableView.setMaxWidth(1060);
        drinksTableView.setMinHeight(515);
        drinksTableView.getColumns().forEach(column -> column.setMinWidth(100));

        right.setSpacing(25);
        allButtons.setHgap(25);
        allButtons.setVgap(25);

        colIndex = 0;
        rowIndex = 0;
        sum = 0;

        this.setInitialData();
        this.eventListeners(primaryStage);
    }

    /**
     * Gets the node of the space.
     *
     * @return space : VBox - The VBox object.
     */
    public HBox getNode() {
        return this.space;
    }

    /**
     * Sets the initial data to the space.
     */
    public void setInitialData() {
        int minMaxHeightBtn = 100;
        int minMaxWidthBtn = 250;
        String smBtnFontSize = "-fx-font-size:20";

        printReceipt.setMinHeight(minMaxHeightBtn);
        printReceipt.setMaxHeight(minMaxHeightBtn);
        printReceipt.setMinWidth(minMaxWidthBtn);
        printReceipt.setMaxWidth(minMaxWidthBtn);
        printReceipt.setStyle(smBtnFontSize);

        removeTable.setMinHeight(minMaxHeightBtn);
        removeTable.setMaxHeight(minMaxHeightBtn);
        removeTable.setMinWidth(minMaxWidthBtn);
        removeTable.setMaxWidth(minMaxWidthBtn);
        removeTable.setStyle(smBtnFontSize);
        removeTable.setDisable(true);

        removeDrinkBtn.setMinHeight(minMaxHeightBtn);
        removeDrinkBtn.setMaxHeight(minMaxHeightBtn);
        removeDrinkBtn.setMinWidth(minMaxWidthBtn);
        removeDrinkBtn.setMaxWidth(minMaxWidthBtn);
        removeDrinkBtn.setStyle(smBtnFontSize);
        removeDrinkBtn.setDisable(true);

        backBtn.setMinHeight(minMaxHeightBtn);
        backBtn.setMaxHeight(minMaxHeightBtn);
        backBtn.setMinWidth(minMaxWidthBtn);
        backBtn.setMaxWidth(minMaxWidthBtn);
        backBtn.setStyle(smBtnFontSize);

        allButtons.add(printReceipt, colIndex, rowIndex);
        allButtons.add(removeTable, colIndex + 1, rowIndex);
        allButtons.add(removeDrinkBtn, colIndex + 2, rowIndex++);

        for (List<Drink> drinks : p.getDrinks().values()) {
            for (Drink d : drinks) {
                if (colIndex == 3) {
                    rowIndex++;
                    colIndex = 0;
                }

                Button drinkBtn = new Button(d.getName());
                drinkBtn.setMinHeight(100);
                drinkBtn.setMaxHeight(100);
                drinkBtn.setMinWidth(minMaxWidthBtn);
                drinkBtn.setMaxWidth(minMaxWidthBtn);
                drinkBtn.setStyle(smBtnFontSize);

                drinkBtn.setOnAction(event -> {
                    int tableId = p.getTableId(tableName);
                    Drink drink = new Drink(
                            d.getId(),
                            d.getName(),
                            d.getCategory(),
                            d.getPrice());
                    p.addDrinkToTable(drink, tableId);
                    drinksTableView.getItems().add(drink);
                });

                allButtons.add(drinkBtn, colIndex++, rowIndex);
            }
            allButtons.add(backBtn, 0, rowIndex = rowIndex + 2);
        }
        left.getChildren().add(allButtons);

        // Add entries to TableView receiptTableView GUI
        int tableId = p.getTableId(tableName);
        LinkedHashMap<String, List<Drink>> tableDrinks = p.getTableDrinks(tableId);
        for (List<Drink> l : tableDrinks.values()) {
            for (Drink drink : l) drinksTableView.getItems().add(drink);
        }

        drinksTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn<Drink, String> receiptCol1 = new TableColumn<>("Name");
        TableColumn<Drink, String> receiptCol2 = new TableColumn<>("Category");
        TableColumn<Drink, String> receiptCol3 = new TableColumn<>("Price");
        receiptCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        receiptCol2.setCellValueFactory(new PropertyValueFactory<>("category"));
        receiptCol3.setCellValueFactory(new PropertyValueFactory<>("price"));
        drinksTableView.getColumns().addAll(receiptCol1, receiptCol2, receiptCol3);

        sum = p.getTotalSumOfTable(tableName);
        if (sum == 0) {
            printReceipt.setDisable(true);
            removeTable.setDisable(false);
        }

        // Table Price Texts
        Text sumText = new Text(25, 25, "Total Sum: " + sum);
        sumText.setFont(Font.font(null, FontWeight.BOLD, 32));

        right.getChildren().addAll(drinksTableView, sumText);
        space.getChildren().addAll(left, right);
    }

    /**
     * Event Listeners
     *
     * @param primaryStage : Stage - The Stage object.
     */
    public void eventListeners(Stage primaryStage) {
        printReceipt.setOnAction(clicked -> {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            int tableId = p.getTableId(tableName);
            p.addReceipt("Administrator", dateFormat.format(date), tableId, sum);

            // Remove all drinks from Table here
            //p.removeAllDrinksFromTable(tableId);

            // create a new table with the same tableName a
            p.addTable(tableName);

            AllReceipts ar = new AllReceipts(primaryStage);
            primaryStage.setScene(new Scene(ar.getNode(), 1920, 1080));
        });

        removeTable.setOnAction(clicked -> {
            p.removeTable(tableName);
            Root root = new Root(primaryStage);
            primaryStage.setScene(new Scene(root.getNode(), 1920, 1080));
        });

        drinksTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                removeDrinkBtn.setDisable(false);
                Drink drinkObj = drinksTableView.getSelectionModel().getSelectedItem();

                removeDrinkBtn.setOnAction(clicked -> {
                    p.removeDrinkFromTable(tableName, drinkObj);
                    drinksTableView.getItems().remove(drinkObj);
                });
            }
        });

        backBtn.setOnAction(click -> {
            Root root = new Root(primaryStage);
            primaryStage.setScene(new Scene(root.getNode(), 1920, 1080));
        });
    }
}
