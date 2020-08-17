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

/**
 * AllReceipts stage
 *
 * @author Marcus Cvjeticanin
 */
public class AllReceipts {
    private final PostgreSQL p = new PostgreSQL();
    private VBox space = null;
    private VBox controlBtns = null;
    private VBox allReceipts = null;
    private TableView<Receipt> receiptTableView = null;
    private final Button removeReceiptBtn = new Button("Remove Receipt");
    private final Button backBtn = new Button ("Go Back");

    /**
     * Creates an instance of AllReceipts
     *
     * @param primaryStage : Stage - The Stage object.
     */
    public AllReceipts(Stage primaryStage) {
        space = new VBox(25);
        controlBtns = new VBox(25);
        allReceipts = new VBox(25);
        VBox allReceiptsBtns = new VBox(25);
        receiptTableView = new TableView<>();

        space.setPadding(new Insets(25, 25, 25, 25));

        this.setInitialData();
        this.eventListeners(primaryStage);
    }

    /**
     * Gets the node of the space.
     *
     * @return space : VBox - The VBox object.
     */
    public VBox getNode() {
        return this.space;
    }

    /**
     * Sets the initial data to the space.
     */
    public void setInitialData() {
        int minMaxWidthBtn = 250;
        int minMaxHeightBtn = 100;
        String smBtnFontSize = "-fx-font-size:20";

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

        removeReceiptBtn.setMinHeight(minMaxHeightBtn);
        removeReceiptBtn.setMaxHeight(minMaxHeightBtn);
        removeReceiptBtn.setMinWidth(minMaxWidthBtn);
        removeReceiptBtn.setMaxWidth(minMaxWidthBtn);
        removeReceiptBtn.setStyle(smBtnFontSize);

        backBtn.setMinHeight(minMaxHeightBtn);
        backBtn.setMaxHeight(minMaxHeightBtn);
        backBtn.setMinWidth(minMaxWidthBtn);
        backBtn.setMaxWidth(minMaxWidthBtn);
        backBtn.setStyle(smBtnFontSize);

        controlBtns.getChildren().addAll(removeReceiptBtn, backBtn);
        space.getChildren().addAll(allReceipts, controlBtns);
    }

    /**
     * Event Listeners
     *
     * @param primaryStage : Stage - The Stage object.
     */
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
