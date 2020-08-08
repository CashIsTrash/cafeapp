package main.scene;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.PostgreSQL;
import main.model.Drink;

import java.util.*;

public class Table {
    private final PostgreSQL p = new PostgreSQL();
    private HBox space;
    private VBox left;
    private VBox right;
    private GridPane allButtons = null;
    private TableView<Drink> drinksTableView = null;
    private String tableName;

    private final String bigBtnFontSize = "-fx-font-size:40";
    private final String smBtnFontSize = "-fx-font-size:20";
    private final int maxWidthBtn = 250;
    private final int minWidthBtn = 250;
    private int colIndex;
    private int rowIndex;

    public Table(String tn) {
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

        allButtons.setHgap(25);
        allButtons.setVgap(25);

        colIndex = 0;
        rowIndex = 0;

        this.setInitialData();
    }

    public HBox getNode() {
        return this.space;
    }

    public void setInitialData() {
        Button printReceipt = new Button("Print Receipt");
        printReceipt.setMinHeight(100);
        printReceipt.setMaxHeight(100);
        printReceipt.setMinWidth(minWidthBtn);
        printReceipt.setMaxWidth(maxWidthBtn);
        printReceipt.setStyle(smBtnFontSize);

        allButtons.add(printReceipt, colIndex, rowIndex++);

        for (Object drink : p.getDrinks().keySet()) {
            System.out.println(rowIndex);
            if (colIndex == 3) {
                rowIndex++;
                colIndex = 0;
            }

            Button drinkBtn = new Button(drink.toString());
            drinkBtn.setMinHeight(100);
            drinkBtn.setMaxHeight(100);
            drinkBtn.setMinWidth(minWidthBtn);
            drinkBtn.setMaxWidth(maxWidthBtn);
            drinkBtn.setStyle(smBtnFontSize);

            allButtons.add(drinkBtn, colIndex, rowIndex);
            colIndex++;
        }

        left.getChildren().add(allButtons);

        // Add entries to TableView receiptTableView GUI

        int tableId = p.getTableId(tableName);

        LinkedHashMap<String, List<Drink>> tableDrinks = p.getTableDrinks(tableId);
        for (List<Drink> l : tableDrinks.values()) {
            Iterator<Drink> iterator = l.iterator();
            while (iterator.hasNext())
                drinksTableView.getItems().add(iterator.next());
        }


        //drinksTableView.getItems().add(new Drink("Cosmopolitan", "Cocktail", 25));
        // drinksTableView.getItems().add(new Drink("Lone Star Beer", "Beer", 10));

        drinksTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn<Drink, String> receiptCol1 = new TableColumn<>("Name");
        TableColumn<Drink, String> receiptCol2 = new TableColumn<>("Category");
        TableColumn<Drink, String> receiptCol3 = new TableColumn<>("Price");
        receiptCol1.setCellValueFactory(new PropertyValueFactory<>("name"));
        receiptCol2.setCellValueFactory(new PropertyValueFactory<>("category"));
        receiptCol3.setCellValueFactory(new PropertyValueFactory<>("price"));

        drinksTableView.getColumns().addAll(receiptCol1, receiptCol2, receiptCol3);

        right.getChildren().add(drinksTableView);
        space.getChildren().addAll(left, right);
    }
}
