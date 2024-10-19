package org.example.tictactoejavafx.controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.example.tictactoejavafx.model.TicTacToeModel;
import org.example.tictactoejavafx.view.TicTacToeView;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

public class GameController {

    private TicTacToeModel model;
    private TicTacToeView view;
    private int scoreX = 0;
    private int scoreO = 0;

    private Clip buttonClickSound;
    private Clip winSound;

    public GameController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;
        initialize();

        // Load sound effects
        try {
            URL buttonClickUrl = getClass().getResource("/sounds/fast-click.wav");
            if (buttonClickUrl != null) {
                AudioInputStream buttonClickStream = AudioSystem.getAudioInputStream(buttonClickUrl);
                buttonClickSound = AudioSystem.getClip();
                buttonClickSound.open(buttonClickStream);
            }

            URL winSoundUrl = getClass().getResource("/sounds/win_sound.wav");
            if (winSoundUrl != null) {
                AudioInputStream winSoundStream = AudioSystem.getAudioInputStream(winSoundUrl);
                winSound = AudioSystem.getClip();
                winSound.open(winSoundStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Handle reset button action
        view.getResetButton().setOnAction(event -> {
            model.resetBoard();
            resetView();
        });
    }

    // Method to initialize the controller and set up event handling
    private void initialize() {
        Button[][] cells = view.getCells();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int finalRow = row;
                int finalCol = col;

                // Add click event handler for each cell button
                cells[row][col].setOnAction(event -> handlePlayerMove(finalRow, finalCol));
            }
        }
    }

    // Method to handle a player's move
    private void handlePlayerMove(int row, int col) {
        // Attempt to place a move
        if (model.placeMove(row, col)) {
            Button currentButton = view.getCells()[row][col];
            char currentSymbol = model.getBoard()[row][col];

            // Play button click sound
            if (buttonClickSound != null) {
                buttonClickSound.setFramePosition(0); // Rewind to the beginning
                buttonClickSound.start();
            }

            // Set button text based on the current player
            currentButton.setText(String.valueOf(currentSymbol));

            // Add scale animation
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), currentButton);
            scaleTransition.setFromX(0.5);
            scaleTransition.setFromY(0.5);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();

            // Check if there is a winner
            char winner = model.checkWinner();
            if (winner != TicTacToeModel.EMPTY) {
                if (winner == TicTacToeModel.PLAYER_X) {
                    scoreX++;
                } else {
                    scoreO++;
                }
                updateScoreboard();

                // Play win sound
                if (winSound != null) {
                    winSound.setFramePosition(0); // Rewind to the beginning
                    winSound.start();
                }

                showGameOverAlert(winner + " wins!");
                model.resetBoard();
                resetView();
            } else if (model.isDraw()) {
                showGameOverAlert("It's a draw!");
                model.resetBoard();
                resetView();
            }
        }
    }

    // Method to show a game-over alert
    private void showGameOverAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Method to reset the view after a round is over
    private void resetView() {
        Button[][] cells = view.getCells();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setText("");
                cells[row][col].setStyle(""); // Reset the style
            }
        }
    }

    // Method to update the scoreboard
    private void updateScoreboard() {
        Label scoreLabelX = view.getScoreLabelX();
        Label scoreLabelO = view.getScoreLabelO();
        scoreLabelX.setText("Player X: " + scoreX);
        scoreLabelO.setText("Player O: " + scoreO);
    }

    public void onHelloButtonClick(ActionEvent actionEvent) {
    }
}
