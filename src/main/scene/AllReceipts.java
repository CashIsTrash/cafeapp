package main.scene;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import main.PostgreSQL;
import main.model.Drink;
import main.model.Receipt;

import java.util.LinkedHashMap;
import java.util.List;

public class AllReceipts {
    private PostgreSQL p = new PostgreSQL();
    private VBox space = null;
    private VBox controlBtns = null;
    private VBox allReceipts = null;
    private VBox allReceiptsBtns = null;
    private TableView<Receipt> receiptTableView = null;
    private Button removeReceiptBtn = new Button("Remove Receipt");
    private final String smBtnFontSize = "-fx-font-size:20";
    private final int maxWidthBtn = 250;
    private final int minWidthBtn = 250;

    public AllReceipts() {
        space = new VBox(25);
        space.setPadding(new Insets(25, 25, 25, 25));

        controlBtns = new VBox(25);

        allReceipts = new VBox(25);
        allReceiptsBtns = new VBox(25);
        receiptTableView = new TableView<>();
        this.setInitialData();
        this.buttonEventListners();
    }

    public VBox getNode() {
        return this.space;
    }

    public void setInitialData() {
        // Add entries to TableView receiptTableView GUI
        LinkedHashMap<String, List<Receipt>> tableDrinks = p.getAllReceipts();
        System.out.println(tableDrinks);
        for (List<Receipt> l : tableDrinks.values()) {
            for (Receipt receipt : l) receiptTableView.getItems().add(receipt);
        }

        receiptTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        TableColumn<Receipt, String> receiptCol1 = new TableColumn<>("Table Name");
        TableColumn<Receipt, String> receiptCol2 = new TableColumn<>("Server");
        TableColumn<Receipt, String> receiptCol3 = new TableColumn<>("Date");
        TableColumn<Receipt, String> receiptCol4 = new TableColumn<>("Total Price");
        receiptCol1.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        receiptCol2.setCellValueFactory(new PropertyValueFactory<>("server"));
        receiptCol3.setCellValueFactory(new PropertyValueFactory<>("date"));
        receiptCol4.setCellValueFactory(new PropertyValueFactory<>("price"));

        receiptTableView.getColumns().addAll(receiptCol1,receiptCol2, receiptCol3, receiptCol4);
        allReceipts.getChildren().addAll(receiptTableView);

        removeReceiptBtn.setMinHeight(100);
        removeReceiptBtn.setMaxHeight(100);
        removeReceiptBtn.setMinWidth(minWidthBtn);
        removeReceiptBtn.setMaxWidth(maxWidthBtn);
        removeReceiptBtn.setStyle(smBtnFontSize);

        controlBtns.getChildren().addAll(removeReceiptBtn);
        this.space.getChildren().addAll(allReceipts, controlBtns);
    }

    public void buttonEventListners() {
        removeReceiptBtn.setOnAction(event -> {
            System.out.println("Hej");
        });
    }
}
