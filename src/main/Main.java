package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("index.fxml"));
        primaryStage.setTitle("Cafe App");
        primaryStage.setScene(new Scene(root, 1024, 768));
        primaryStage.getIcons().add(new Image("resources/icon.png"));
        primaryStage.show();

        PostgreSQL p = new PostgreSQL();
        // p.addTable("Test Table");

        // p.addDrinkToTable(1,2);
        p.addReceipt("Ljiljana Arsenijevic", "2020-07-30", 1);

        // p.addDrink(20.0, "Zajercarsko", "Beer");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
