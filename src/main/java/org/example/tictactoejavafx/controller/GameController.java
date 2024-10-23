package org.example.tictactoejavafx.controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.util.Duration;
import org.example.tictactoejavafx.model.TicTacToeModel;
import org.example.tictactoejavafx.view.TicTacToeView;

public class GameController {

    private TicTacToeModel model;
    private TicTacToeView view;
    private boolean isVsComputer;
    private String player1Name;
    private String player2Name;

    public GameController(TicTacToeModel model, TicTacToeView view, boolean isVsComputer, String player1Name, String player2Name) {
        this.model = model;
        this.view = view;
        this.isVsComputer = isVsComputer;
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        // Set up the game view
        view.showGameBoard(player1Name, player2Name);

        initialize();
    }

    // Method to initialize event handling
    private void initialize() {
        Button[][] cells = view.getCells();

        // Set event handlers for each cell
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int finalRow = row;
                int finalCol = col;

                cells[row][col].setOnAction(event -> handlePlayerMove(finalRow, finalCol));
            }
        }

        // Set event handler for the reset button
        view.getResetButton().setOnAction(event -> {
            model.resetBoard();
            resetView();
        });
    }

    // Method to handle a player's move
    private void handlePlayerMove(int row, int col) {
        // Attempt to place a move
        if (model.placeMove(row, col)) {
            // Update the view with the current player's symbol
            Button currentButton = view.getCells()[row][col];
            char currentSymbol = model.getBoard()[row][col];
            currentButton.setText(String.valueOf(currentSymbol));

            // Add scale animation for visual feedback
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), currentButton);
            scaleTransition.setFromX(0.5);
            scaleTransition.setFromY(0.5);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();

            // Check if there is a winner or draw
            char winner = model.checkWinner();
            if (winner != TicTacToeModel.EMPTY) {
                handleGameOver(winner + " wins!");
                model.updateScore(winner);
                updateScoreboard();
            } else if (model.isDraw()) {
                handleGameOver("It's a draw!");
            } else {
                // If vs Computer and it's the computer's turn, make a move
                if (isVsComputer && model.getCurrentPlayer() == TicTacToeModel.PLAYER_O) {
                    handleComputerMove();
                }
            }
        }
    }

    // Method to handle computer's move
    private void handleComputerMove() {
        // Simple random move for the computer
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (model.getBoard()[row][col] == TicTacToeModel.EMPTY) {
                    handlePlayerMove(row, col);
                    return;
                }
            }
        }
    }

    // Method to show an alert when the game ends
    private void handleGameOver(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        model.resetBoard();
        resetView();
    }

    // Method to reset the view after a game round is over
    private void resetView() {
        Button[][] cells = view.getCells();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setText("");
            }
        }
    }

    // Method to update the scoreboard
    private void updateScoreboard() {
        view.getScoreLabelX().setText(player1Name + ": " + model.getScorePlayer1());
        view.getScoreLabelO().setText(player2Name + ": " + model.getScorePlayer2());
    }
}
