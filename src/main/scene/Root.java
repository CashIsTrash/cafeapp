package main.scene;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.PostgreSQL;

public class Root {
    private final PostgreSQL p = new PostgreSQL();
    private Stage primaryStage = null;
    private VBox root = null;
    private final GridPane allTablesGP = new GridPane();
    private final Button addTableBtn = new Button("Add Table");
    private final Button allReceiptsBtn = new Button("All Receipts");
    private final Button statsBtn = new Button("Statistics");
    private final String btnFontSize = "-fx-font-size:20";
    private final int minMaxWidthBtn = 250;
    private final int minMaxHeightBtn = 250;
    private int tableIndex;
    private int colIndex;
    private int rowIndex;

    public Root(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new VBox(25);
        root.setPadding(new Insets(20, 20, 20, 20));

        HBox adminBtns = new HBox(25);

        tableIndex = 1;
        colIndex = 0;
        rowIndex = 0;

        this.setInitialData();
        this.eventListeners(primaryStage);

        adminBtns.getChildren().addAll(addTableBtn, allReceiptsBtn, statsBtn);
        this.root.getChildren().addAll(adminBtns, allTablesGP);
    }

    public VBox getNode() {
        return this.root;
    }

    public void setInitialData() {
        addTableBtn.setMinHeight(minMaxHeightBtn);
        addTableBtn.setMaxHeight(minMaxHeightBtn);
        addTableBtn.setMinWidth(minMaxWidthBtn);
        addTableBtn.setMaxWidth(minMaxWidthBtn);
        addTableBtn.setStyle(btnFontSize);

        allReceiptsBtn.setMinHeight(minMaxHeightBtn);
        allReceiptsBtn.setMaxHeight(minMaxHeightBtn);
        allReceiptsBtn.setMinWidth(minMaxWidthBtn);
        allReceiptsBtn.setMaxWidth(minMaxWidthBtn);
        allReceiptsBtn.setStyle(btnFontSize);

        statsBtn.setMinHeight(minMaxHeightBtn);
        statsBtn.setMaxHeight(minMaxHeightBtn);
        statsBtn.setMinWidth(minMaxWidthBtn);
        statsBtn.setMaxWidth(minMaxWidthBtn);
        statsBtn.setStyle(btnFontSize);

        allTablesGP.setVgap(25);
        allTablesGP.setHgap(25);

        // Creating the initial table buttons from database
        for (Object value : p.getTables().values()) {
            Button tableBtn = new Button(value.toString());
            tableBtn.setMinHeight(minMaxHeightBtn);
            tableBtn.setMaxHeight(minMaxHeightBtn);
            tableBtn.setMaxWidth(minMaxWidthBtn);
            tableBtn.setMinWidth(minMaxWidthBtn);
            tableBtn.setStyle(btnFontSize);

            if (colIndex == 3) {
                rowIndex++;
                colIndex = 0;
            }

            allTablesGP.add(tableBtn, colIndex , rowIndex);

            tableBtn.setOnAction(event -> {
                Table t = new Table(tableBtn.getText(), primaryStage);
                primaryStage.setTitle("Cafe App - " + tableBtn.getText());
                primaryStage.setScene(new Scene(t.getNode(), 1920, 1080));
            });

            tableIndex++;
            colIndex++;
        }
    }

    public void eventListeners(Stage primaryStage) {
        addTableBtn.setOnAction(event -> {
            String tableName = "Table " + tableIndex;
            Button tableBtn = new Button(tableName);
            tableBtn.setMinHeight(minMaxHeightBtn);
            tableBtn.setMaxHeight(minMaxHeightBtn);
            tableBtn.setMaxWidth(minMaxWidthBtn);
            tableBtn.setMinWidth(minMaxWidthBtn);
            tableBtn.setStyle(btnFontSize);

            if (colIndex == 3) {
                rowIndex++;
                colIndex = 0;
            }

            p.addTable(tableName);
            allTablesGP.add(tableBtn, colIndex++, rowIndex);
            tableIndex++;
        });

        allReceiptsBtn.setOnAction(event -> {
            AllReceipts ar = new AllReceipts(primaryStage);
            primaryStage.setTitle("Cafe App - " + allReceiptsBtn.getText());
            primaryStage.setScene(new Scene(ar.getNode(), 1920, 1080));
        });

        statsBtn.setOnAction(event -> {
            Statistics stats = new Statistics(primaryStage);
            primaryStage.setTitle("Cafe App - " + statsBtn.getText());
            primaryStage.setScene(new Scene(stats.getNode(), 1920, 1080));
        });
    }
}
