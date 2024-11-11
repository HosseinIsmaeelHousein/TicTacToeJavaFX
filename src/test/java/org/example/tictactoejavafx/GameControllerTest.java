package org.example.tictactoejavafx;

import javafx.application.Platform;
import org.example.tictactoejavafx.controller.GameController;
import org.example.tictactoejavafx.view.TicTacToeView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private TicTacToeModel model;
    private TicTacToeView view;
    private GameController controller;

    @BeforeAll
    static void initToolkit() {
        Platform.startup(() -> {});
    }

    @BeforeEach
    void setUp() {
        model = mock(TicTacToeModel.class);
        view = mock(TicTacToeView.class);

        // Initialize a 3x3 grid of buttons for the view mock
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                when(view.getButton(row, col)).thenReturn(new javafx.scene.control.Button());
            }
        }

        controller = new GameController(model, view);
    }

    @Test
    void testPlayerMoveValid() {
        when(model.isValidMove(0, 0)).thenReturn(true);
        when(model.getCurrentPlayer()).thenReturn('X');

        controller.playerMove(0, 0);

        verify(model).makeMove(0, 0);
        verify(view).updateButton(0, 0, 'X');
        assertFalse(controller.isPlayerTurn, "Turn should switch to computer after player move");
    }

    @Test
    void testComputerMove() {
        when(model.isValidMove(anyInt(), anyInt())).thenReturn(true);
        when(model.getCurrentPlayer()).thenReturn('O');

        Platform.runLater(controller::computerMove);
        Platform.runLater(() -> {
            verify(model, atLeastOnce()).makeMove(anyInt(), anyInt());
            verify(view, atLeastOnce()).updateButton(anyInt(), anyInt(), eq('O'));
            assertTrue(controller.isPlayerTurn, "Turn should switch back to player after computer move");
        });

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void testCheckGameEndWinCondition() {
        when(model.checkWin()).thenReturn(true);
        when(model.getCurrentPlayer()).thenReturn('X');
        when(model.getPlayerScore()).thenReturn(1);
        when(model.getComputerScore()).thenReturn(0);

        boolean gameEnded = controller.checkGameEnd();

        assertTrue(gameEnded, "checkGameEnd should return true when a player wins");
        verify(view).updateMessage("Winner: X");
        verify(view).updateScore(1, 0);
    }

    @Test
    void testCheckGameEndDrawCondition() {
        when(model.checkWin()).thenReturn(false);
        when(model.isBoardFull()).thenReturn(true);

        boolean gameEnded = controller.checkGameEnd();

        assertTrue(gameEnded, "checkGameEnd should return true when the game is a draw");
        verify(view).updateMessage("It's a draw!");
    }

    @Test
    void testStartNewRound() {
        reset(model);
        reset(view);

        controller.startNewRound();

        verify(model, times(1)).resetGame();
        verify(view, times(1)).resetBoard();
        verify(view, times(1)).updateMessage("New Round! Player's turn.");
        assertTrue(controller.isPlayerTurn, "New round should start with player's turn");
    }
}
