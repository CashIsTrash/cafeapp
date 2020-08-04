package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Controller {
    int index = 1;

    @FXML
    GridPane grid;

    @FXML
    public void addTable(ActionEvent actionEvent) {
        System.out.println("Button clicked");
        String tableName = "Table " + index;

        Button tableBtn = new Button(tableName);
        grid.add(tableBtn, index,1);

        PostgreSQL p = new PostgreSQL();
        p.addTable(tableName);
        index++;
    }
}
