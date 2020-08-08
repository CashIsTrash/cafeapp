package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.scene.Root;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {
    private final PostgreSQL testP = new PostgreSQL();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cafe App");
        Root root = new Root(primaryStage);

        primaryStage.setScene(new Scene(root.getNode(), 1920, 1080));
        primaryStage.show();

        // p.addTableBtn("Test Table");

        // p.addDrinkToTable(1,2);
        //p.addReceipt("Ljiljana Arsenijevic", "2020-06-30", 1);
        // p.addDrink(20.0, "Zajercarsko", "Beer");

        // Adding tables stored in database
    }

    public static void main(String[] args) {
        launch(args);
    }
}
