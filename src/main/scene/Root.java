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
    private HBox adminBtns = null;
    private final GridPane allTablesGP = new GridPane();
    private final Button addTableBtn = new Button("Add Table");
    private final Button allReceiptsBtn = new Button("All Receipts");
    private final Button statsBtn = new Button("Statistics");
    private final String bigBtnFontSize = "-fx-font-size:40";
    private final String smBtnFontSize = "-fx-font-size:20";
    private final int maxWidthBtn = 250;
    private final int minWidthBtn = 250;
    private int tableIndex;
    private int colIndex;
    private int rowIndex;

    public Root(Stage primaryStage) {
        this.primaryStage = primaryStage;
        root = new VBox(25);
        root.setPadding(new Insets(20, 20, 20, 20));

        adminBtns = new HBox(25);

        tableIndex = 1;
        colIndex = 0;
        rowIndex = 0;

        this.setInitialData();
        this.buttonEventListners(primaryStage);

        adminBtns.getChildren().addAll(addTableBtn, allReceiptsBtn, statsBtn);
        this.root.getChildren().addAll(adminBtns, allTablesGP);
    }

    public VBox getNode() {
        return this.root;
    }

    public void setInitialData() {
        // Button configuration
        addTableBtn.setMinHeight(100);
        addTableBtn.setMaxHeight(100);
        addTableBtn.setMinWidth(minWidthBtn);
        addTableBtn.setMaxWidth(maxWidthBtn);
        addTableBtn.setStyle(smBtnFontSize);

        allReceiptsBtn.setMinHeight(100);
        allReceiptsBtn.setMaxHeight(100);
        allReceiptsBtn.setMinWidth(minWidthBtn);
        allReceiptsBtn.setMaxWidth(maxWidthBtn);
        allReceiptsBtn.setStyle(smBtnFontSize);

        statsBtn.setMinHeight(100);
        statsBtn.setMaxHeight(100);
        statsBtn.setMinWidth(minWidthBtn);
        statsBtn.setMaxWidth(maxWidthBtn);
        statsBtn.setStyle(smBtnFontSize);

        allTablesGP.setVgap(25);
        allTablesGP.setHgap(25);

        // Creating the initial table buttons from database
        for (Object value : p.getTables().values()) {
            Button tableBtn = new Button(value.toString());
            tableBtn.setMinHeight(100);
            tableBtn.setMaxHeight(100);
            tableBtn.setMaxWidth(maxWidthBtn);
            tableBtn.setMinWidth(minWidthBtn);
            tableBtn.setStyle(smBtnFontSize);

            if (colIndex == 3) {
                rowIndex++;
                colIndex = 0;
            }

            allTablesGP.add(tableBtn, colIndex , rowIndex);

            tableBtn.setOnAction(event -> {
                Table t = new Table(tableBtn.getText());
                primaryStage.setTitle("Cafe App - " + tableBtn.getText());
                primaryStage.setScene(new Scene(t.getNode(), 1920, 1080));
            });

            tableIndex++;
            colIndex++;
        }
    }

    public void buttonEventListners(Stage primaryStage) {
        addTableBtn.setOnAction(event -> {
            String tableName = "Table " + tableIndex;
            Button tableBtn = new Button(tableName);
            tableBtn.setMinHeight(100);
            tableBtn.setMaxHeight(100);
            tableBtn.setMaxWidth(maxWidthBtn);
            tableBtn.setMinWidth(minWidthBtn);
            tableBtn.setStyle(smBtnFontSize);

            if (colIndex == 3) {
                rowIndex++;
                colIndex = 0;
            }

            p.addTable(tableName);
            allTablesGP.add(tableBtn, colIndex++, rowIndex);
            tableIndex++;
        });

        allReceiptsBtn.setOnAction(event -> {
            AllReceipts ar = new AllReceipts();
            primaryStage.setTitle("Cafe App - " + allReceiptsBtn.getText());
            primaryStage.setScene(new Scene(ar.getNode(), 1920, 1080));
        });

        statsBtn.setOnAction(event -> {
            System.out.println("Here we should have the list of all drinks that are sold" +
                    "and statistics of how many, how much etc etc.");
        });
    }
}
