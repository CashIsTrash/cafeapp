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

public class Table {
    private final PostgreSQL p = new PostgreSQL();
    private Stage primaryStage;
    private HBox space;
    private VBox left;
    private VBox right;
    private GridPane allButtons = null;
    private TableView<Drink> drinksTableView = null;
    private Button printReceipt = new Button("Print Receipt");
    private Button removeTable = new Button("Remove Table");
    private Button removeDrinkBtn = new Button("Remove Drink");
    private String tableName;

    private final String bigBtnFontSize = "-fx-font-size:40";
    private final String smBtnFontSize = "-fx-font-size:20";
    private final int maxWidthBtn = 250;
    private final int minWidthBtn = 250;
    private int colIndex;
    private int rowIndex;
    private double sum;

    public Table(String tn, Stage ps) {
        primaryStage = ps;
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

    public HBox getNode() {
        return this.space;
    }

    public void setInitialData() {
        printReceipt.setMinHeight(100);
        printReceipt.setMaxHeight(100);
        printReceipt.setMinWidth(minWidthBtn);
        printReceipt.setMaxWidth(maxWidthBtn);
        printReceipt.setStyle(smBtnFontSize);
        printReceipt.setDisable(true);

        removeTable.setMinHeight(100);
        removeTable.setMaxHeight(100);
        removeTable.setMinWidth(minWidthBtn);
        removeTable.setMaxWidth(maxWidthBtn);
        removeTable.setStyle(smBtnFontSize);
        removeTable.setDisable(true);

        removeDrinkBtn.setMinHeight(100);
        removeDrinkBtn.setMaxHeight(100);
        removeDrinkBtn.setMinWidth(minWidthBtn);
        removeDrinkBtn.setMaxWidth(maxWidthBtn);
        removeDrinkBtn.setStyle(smBtnFontSize);
        removeDrinkBtn.setDisable(true);

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
                drinkBtn.setMinWidth(minWidthBtn);
                drinkBtn.setMaxWidth(maxWidthBtn);
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
        }
        left.getChildren().add(allButtons);

        // Add entries to TableView receiptTableView GUI
        int tableId = p.getTableId(tableName);

        LinkedHashMap<String, List<Drink>> tableDrinks = p.getTableDrinks(tableId);
        for (List<Drink> l : tableDrinks.values()) {
            for (Drink drink : l) drinksTableView.getItems().add(drink);
        }

        printReceipt.setDisable(false);

        drinksTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn<Drink, String> receiptCol1 = new TableColumn<>("Name");
        TableColumn<Drink, String> receiptCol2 = new TableColumn<>("Category");
        TableColumn<Drink, String> receiptCol3 = new TableColumn<>("Price");
        receiptCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        receiptCol2.setCellValueFactory(new PropertyValueFactory<>("category"));
        receiptCol3.setCellValueFactory(new PropertyValueFactory<>("price"));

        drinksTableView.getColumns().addAll(receiptCol1, receiptCol2, receiptCol3);

        // Table Price Texts
        sum = p.getTotalSumOfTable(tableName);

        if (sum == 0) {
            printReceipt.setDisable(true);
        }

        Text sumText = new Text(25, 25, "Total Sum: " + sum);
        sumText.setFont(Font.font(null, FontWeight.BOLD, 32));

        right.getChildren().addAll(drinksTableView, sumText);
        space.getChildren().addAll(left, right);
    }

    public void eventListeners(Stage primaryStage) {
        printReceipt.setOnAction(clicked -> {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            int tableId = p.getTableId(tableName);
            p.addReceipt("Administrator", dateFormat.format(date), tableId, sum);

            // Remove all drinks from Table here
            p.removeAllDrinksFromTable(tableId);

            AllReceipts ar = new AllReceipts();
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
    }
}
