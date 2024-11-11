package org.example.tictactoejavafx.controller;

import org.example.tictactoejavafx.TicTacToeModel;
import org.example.tictactoejavafx.view.TicTacToeView;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class GameController {
    private TicTacToeModel model;
    private TicTacToeView view;
    public boolean isPlayerTurn; // Flag to control turn

    public GameController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
        this.isPlayerTurn = true; // Player starts the game
        setupEventHandlers();
        startNewRound();
    }

    private void setupEventHandlers() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int finalRow = row;
                int finalCol = col;
                view.getButton(row, col).setOnAction(event -> {
                    if (isPlayerTurn && model.isValidMove(finalRow, finalCol)) {
                        playerMove(finalRow, finalCol);
                    }
                });
            }
        }
    }

    public void playerMove(int row, int col) {
        model.makeMove(row, col);
        view.updateButton(row, col, model.getCurrentPlayer());

        if (checkGameEnd()) {
            return;
        }

        isPlayerTurn = false; // Set to false as it's now the computer's turn
        model.switchPlayer();
        computerMove();
    }

    public void computerMove() {
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
                model.switchPlayer();
                isPlayerTurn = true; // Set back to true for player's turn
            }
        });
        pause.play();
    }

    public boolean checkGameEnd() {
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

    public void startNewRound() {
        model.resetGame();
        view.resetBoard();
        view.updateMessage("New Round! Player's turn.");
        isPlayerTurn = true; // Set to true as player starts each round
    }
}
