package main.scene;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import main.PostgreSQL;

public class Table {
    private final PostgreSQL p = new PostgreSQL();
    private GridPane table = null;
    private final String bigBtnFontSize = "-fx-font-size:40";
    private final String smBtnFontSize = "-fx-font-size:20";
    private final int maxWidthBtn = 250;
    private final int minWidthBtn = 250;
    private int colIndex;
    private int rowIndex;

    public Table() {
        table = new GridPane();
        table.setPadding(new Insets(10, 10, 10, 10));
        table.setVgap(25);
        table.setHgap(25);
        colIndex = 0;
        rowIndex = 0;

        this.setInitialData();
    }

    public GridPane getNode() {
        return this.table;
    }

    public void setInitialData() {
        Button printReceipt = new Button("Print Receipt");
        printReceipt.setMinHeight(100);
        printReceipt.setMaxHeight(100);
        printReceipt.setMinWidth(minWidthBtn);
        printReceipt.setMaxWidth(maxWidthBtn);
        printReceipt.setStyle(smBtnFontSize);

        table.add(printReceipt, colIndex, rowIndex++);

        for (Object drink : p.getDrinks().keySet()) {
            System.out.println(rowIndex);
            if (colIndex == 3) {
                rowIndex++;
                colIndex = 0;
            }

            Button drinkBtn = new Button(drink.toString());
            drinkBtn.setMinHeight(100);
            drinkBtn.setMaxHeight(100);
            drinkBtn.setMinWidth(minWidthBtn);
            drinkBtn.setMaxWidth(maxWidthBtn);
            drinkBtn.setStyle(smBtnFontSize);

            table.add(drinkBtn, colIndex, rowIndex);
            colIndex++;
        }
    }
}
