package org.example.tictactoejavafx;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.tictactoejavafx.view.TicTacToeView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeViewTest {
    private TicTacToeView view;

    @BeforeAll
    static void initToolkit() {
        Platform.startup(() -> {}); // Initializes the JavaFX toolkit
    }

    @BeforeEach
    void setUp() {
        view = new TicTacToeView();
    }

    @Test
    void testInitializeUI() {
        // Verify that the 3x3 grid of buttons is initialized
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = view.getButton(row, col);
                assertNotNull(button, "Button should be initialized at position (" + row + ", " + col + ")");
                assertEquals("", button.getText(), "Button text should initially be empty");
                assertEquals(100, button.getPrefWidth(), "Button width should be set to 100");
                assertEquals(100, button.getPrefHeight(), "Button height should be set to 100");
            }
        }

        // Verify that labels are initialized with correct default text
        Label scoreLabel = view.getScoreLabel();
        Label messageLabel = view.getMessageLabel();
        assertEquals("Player: 0 | Computer: 0", scoreLabel.getText(), "Score label should display initial scores");
        assertEquals("Welcome to Tic-Tac-Toe!", messageLabel.getText(), "Message label should display initial message");
    }

    @Test
    void testUpdateButton() {
        // Update a button at (0, 0) with player 'X' and check its text
        view.updateButton(0, 0, 'X');
        assertEquals("X", view.getButton(0, 0).getText(), "Button text should be updated to 'X'");
    }

    @Test
    void testResetBoard() {
        // Update buttons with marks
        view.updateButton(0, 0, 'X');
        view.updateButton(1, 1, 'O');

        // Reset the board and verify all buttons are cleared
        view.resetBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertEquals("", view.getButton(row, col).getText(), "Button text should be empty after reset");
            }
        }
    }

    @Test
    void testUpdateScore() {
        // Update score and verify the score label's text
        view.updateScore(2, 1);
        assertEquals("Player: 2 | Computer: 1", view.getScoreLabel().getText(), "Score label should update with new scores");
    }

    @Test
    void testUpdateMessage() {
        // Update the message label and verify its text
        view.updateMessage("Player X wins!");
        assertEquals("Player X wins!", view.getMessageLabel().getText(), "Message label should display the updated message");
    }
}
