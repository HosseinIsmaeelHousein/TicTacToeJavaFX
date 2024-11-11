package org.example.tictactoejavafx.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TicTacToeView extends BorderPane {
    private Button[][] gridButtons; // 3x3 buttons for the grid
    private Label scoreLabel;       // Displays scores
    private Label messageLabel;     // Displays round outcome messages

    public TicTacToeView() {
        initializeUI();
    }

    private void initializeUI() {
        // Initialize the grid of buttons
        gridButtons = new Button[3][3];
        GridPane grid = new GridPane();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gridButtons[row][col] = new Button();
                gridButtons[row][col].setPrefSize(100, 100); // Set button size
                grid.add(gridButtons[row][col], col, row);   // Add button to grid
            }
        }
        grid.setAlignment(Pos.CENTER);

        // Initialize the score and message labels
        scoreLabel = new Label("Player: 0 | Computer: 0");
        messageLabel = new Label("Welcome to Tic-Tac-Toe!");

        // Place labels and grid in a vertical layout
        VBox vbox = new VBox(10, scoreLabel, messageLabel, grid);
        vbox.setAlignment(Pos.CENTER);

        // Add VBox to the center of the BorderPane
        setCenter(vbox);
    }

    // Method to access buttons by position
    public Button getButton(int row, int col) {
        return gridButtons[row][col];
    }

    // Method to update a specific button with the player's mark
    public void updateButton(int row, int col, char player) {
        gridButtons[row][col].setText(String.valueOf(player));
    }

    // Method to clear all buttons for a new round
    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                gridButtons[row][col].setText(""); // Clear text on each button
            }
        }
    }

    // Method to update the score label
    public void updateScore(int playerScore, int computerScore) {
        scoreLabel.setText("Player: " + playerScore + " | Computer: " + computerScore);
    }

    // Method to update the message label
    public void updateMessage(String message) {
        messageLabel.setText(message);
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Label getMessageLabel() {
        return messageLabel;
    }

}
