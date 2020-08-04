package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private int tableIndex = 1;
    private final GridPane startGP = new GridPane();
    private final GridPane tableGP = new GridPane();
    private final PostgreSQL p = new PostgreSQL();
    private ListView tableListView = new ListView();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cafe App");

        Button addTable = new Button("Add Table");
        startGP.add(addTable, 0, 0);

        addTable.setOnAction(event -> {
            String tableName = "Table " + tableIndex;
            Button tableBtn = new Button(tableName);
            p.addTable(tableName);
            startGP.add(tableBtn, tableIndex, 2);
            tableIndex++;
        });

        for (Object value : p.getTables().values()) {
            Button tableBtn = new Button(value.toString());
            startGP.add(tableBtn, tableIndex,2);
            tableBtn.setOnAction(event -> {
                primaryStage.setTitle("Cafe App - " + tableBtn.getText());

                this.showAvailableDrinksToAdd();

                primaryStage.setScene(new Scene(tableGP, 1024, 768));
                primaryStage.show();
            });
            tableIndex++;
        }

        primaryStage.setScene(new Scene(startGP, 1024, 768));
        // primaryStage.setMaximized(true);
        primaryStage.show();


        // p.addTable("Test Table");

        // p.addDrinkToTable(1,2);
        //p.addReceipt("Ljiljana Arsenijevic", "2020-06-30", 1);
        // p.addDrink(20.0, "Zajercarsko", "Beer");

        // Adding tables stored in database


    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showAvailableDrinksToAdd() {
        int index = 1;
        for (Object drink : p.getDrinks().keySet()) {
            Button drinkBtn = new Button(drink.toString());
            tableGP.add(drinkBtn, index, 2);
            index++;
        }
    }
}
