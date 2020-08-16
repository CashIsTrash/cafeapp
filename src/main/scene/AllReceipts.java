package main.scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.PostgreSQL;
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
    private Button backBtn = new Button ("Go Back");
    private final String smBtnFontSize = "-fx-font-size:20";
    private final int maxWidthBtn = 250;
    private final int minWidthBtn = 250;

    public AllReceipts(Stage primaryStage) {
        space = new VBox(25);
        space.setPadding(new Insets(25, 25, 25, 25));

        controlBtns = new VBox(25);

        allReceipts = new VBox(25);
        allReceiptsBtns = new VBox(25);
        receiptTableView = new TableView<>();
        this.setInitialData();
        this.eventListeners(primaryStage);
    }

    public VBox getNode() {
        return this.space;
    }

    public void setInitialData() {
        // Add entries to TableView receiptTableView GUI
        LinkedHashMap<String, List<Receipt>> tableReceipts = p.getAllReceipts();
        for (List<Receipt> l : tableReceipts.values()) {
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

        backBtn.setMinHeight(100);
        backBtn.setMaxHeight(100);
        backBtn.setMinWidth(minWidthBtn);
        backBtn.setMaxWidth(maxWidthBtn);
        backBtn.setStyle(smBtnFontSize);

        controlBtns.getChildren().addAll(removeReceiptBtn, backBtn);
        this.space.getChildren().addAll(allReceipts, controlBtns);
    }

    public void eventListeners(Stage primaryStage) {
        receiptTableView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                Receipt receiptObj = receiptTableView.getSelectionModel().getSelectedItem();

                removeReceiptBtn.setOnAction(clicked -> {
                    p.removeReceipt(receiptObj);
                    receiptTableView.getItems().remove(receiptObj);
                });
            }
        });

        backBtn.setOnAction(click -> {
            Root root = new Root(primaryStage);
            primaryStage.setScene(new Scene(root.getNode(), 1920, 1080));
        });
    }
}
