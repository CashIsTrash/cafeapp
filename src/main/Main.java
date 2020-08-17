package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.scene.Root;

/**
 * Starting point of the application
 *
 * @author Marcus Cvjeticanin
 */
public class Main extends Application {

    /**
     * Start method
     *
     * @param primaryStage : Stage - The stage object
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cafe App");
        Root root = new Root(primaryStage);

        primaryStage.setScene(new Scene(root.getNode(), 1920, 1080));
        primaryStage.show();
    }

    /**
     * Main method
     *
     * @param args : String[]
     */
    public static void main(String[] args) {
        launch(args);
    }
}
