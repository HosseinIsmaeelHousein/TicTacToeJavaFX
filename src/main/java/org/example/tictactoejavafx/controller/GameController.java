package org.example.tictactoejavafx.controller;

import javafx.scene.control.Alert;
import org.example.tictactoejavafx.model.TicTacToeModel;
import org.example.tictactoejavafx.view.TicTacToeView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class GameController {
    private TicTacToeModel model;
    private TicTacToeView view;

    public GameController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
        setupEventHandlers();
        startNewRound();
    }

    private void setupEventHandlers() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int finalRow = row;
                int finalCol = col;
                view.getButton(row, col).setOnAction(event -> {
                    if (model.isValidMove(finalRow, finalCol)) {
                        playerMove(finalRow, finalCol);
                    }
                });
            }
        }
    }

    private void playerMove(int row, int col) {
        model.makeMove(row, col);
        view.updateButton(row, col, model.getCurrentPlayer());

        if (checkGameEnd()) {
            return;
        }

        model.switchPlayer(); // Switch to computer
        computerMove();
    }

    private void computerMove() {
        // Create a 2-second pause
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> {
            boolean moveMade = false;
            while (!moveMade) {
                int row = (int) (Math.random() * 3);
                int col = (int) (Math.random() * 3);
                if (model.isValidMove(row, col)) {
                    model.makeMove(row, col);
                    view.updateButton(row, col, model.getCurrentPlayer());
                    moveMade = true;
                }
            }

            if (!checkGameEnd()) {
                model.switchPlayer(); // Switch back to player
            }
        });

        // Start the pause
        pause.play();
    }


    private boolean checkGameEnd() {
        if (model.checkWin()) {
            char winner = model.getCurrentPlayer();
            model.updateScore(winner);
            view.updateMessage("Winner: " + winner);
            view.updateScore(model.getPlayerScore(), model.getComputerScore());
            startNewRound();
            return true;
        } else if (model.isBoardFull()) {
            view.updateMessage("It's a draw!");
            startNewRound();
            return true;
        }
        return false;
    }

    private void startNewRound() {
        model.resetGame();
        view.resetBoard();
        view.updateMessage("New Round! Player's turn.");
    }
}
