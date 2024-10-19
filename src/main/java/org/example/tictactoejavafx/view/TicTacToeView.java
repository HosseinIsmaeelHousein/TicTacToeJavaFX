package org.example.tictactoejavafx.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToeView {

    private GridPane board;
    private Button[][] cells;
    private Button resetButton;
    private Label scoreLabelX;
    private Label scoreLabelO;

    public TicTacToeView(Stage stage) {
        // Initialize the GridPane
        board = new GridPane();
        board.setPadding(new Insets(10, 10, 10, 10));
        board.setHgap(5);
        board.setVgap(5);

        // Create the 3x3 grid buttons
        cells = new Button[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button cell = new Button();
                cell.setPrefSize(100, 100);  // Set preferred size for each cell
                cell.getStyleClass().add("button-cell"); // Add CSS class to style the cell
                cells[row][col] = cell;
                board.add(cell, col, row);  // Add button to the grid
            }
        }

        // Create reset button
        resetButton = new Button("Reset Game");
        resetButton.getStyleClass().add("reset-button"); // Add CSS class to style the reset button

        // Create score labels
        scoreLabelX = new Label("Player X: 0");
        scoreLabelX.getStyleClass().add("score-label"); // Add CSS class to style the score label
        scoreLabelO = new Label("Player O: 0");
        scoreLabelO.getStyleClass().add("score-label"); // Add CSS class to style the score label

        // Layout for the game UI
        VBox vbox = new VBox(10, board, scoreLabelX, scoreLabelO, resetButton);
        vbox.setPadding(new Insets(10));

        // Create scene and set the stylesheet
        Scene scene = new Scene(vbox, 400, 500);
        scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

        // Set up the stage
        stage.setScene(scene);
        stage.setTitle("Tic-Tac-Toe Game");
        stage.show();
    }

    public GridPane getBoard() {
        return board;
    }

    public Button[][] getCells() {
        return cells;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Label getScoreLabelX() {
        return scoreLabelX;
    }

    public Label getScoreLabelO() {
        return scoreLabelO;
    }
}
