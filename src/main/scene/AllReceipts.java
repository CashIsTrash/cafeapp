package main.scene;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import main.Receipt;

public class AllReceipts {
    private VBox ar = null;
    private VBox allReceipts = null;
    private VBox allReceiptsBtns = null;
    private TableView<Receipt> receiptTableView = null;

    public AllReceipts() {
        this.ar = new VBox();
        this.allReceipts = new VBox(25);
        this.allReceiptsBtns = new VBox(25);
        this.receiptTableView = new TableView<>();
        this.setInitialData();
    }

    public VBox getNode() {
        return this.ar;
    }

    public void setInitialData() {
        // Add entries to TableView receiptTableView GUI
        receiptTableView.getItems().addAll(new Receipt("Table 1", "Marcus Cvjeticanin", "2020-07-31", 100));

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
        this.ar.getChildren().add(allReceipts);
    }
}
