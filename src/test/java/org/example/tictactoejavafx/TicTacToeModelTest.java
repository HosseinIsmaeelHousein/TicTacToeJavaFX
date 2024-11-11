package org.example.tictactoejavafx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeModelTest {
    private TicTacToeModel model;

    @BeforeEach
    void setUp() {
        model = new TicTacToeModel();
    }

    @Test
    void testResetGame() {
        model.makeMove(0, 0); // Make a move
        model.resetGame();    // Reset the board

        // Check that all cells are empty
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                assertTrue(model.isValidMove(row, col));
            }
        }

        // Check that the current player is set to 'X'
        assertEquals('X', model.getCurrentPlayer());
    }

    @Test
    void testValidMove() {
        assertTrue(model.isValidMove(0, 0)); // Initially valid
        model.makeMove(0, 0);                // Place move at (0, 0)
        assertFalse(model.isValidMove(0, 0)); // Now should be invalid
    }

    @Test
    void testMakeMove() {
        assertTrue(model.makeMove(1, 1)); // Valid move
        assertEquals('X', model.getCurrentPlayer()); // Verify current player
        assertFalse(model.makeMove(1, 1)); // Invalid move to same cell
    }


    @Test
    void testCheckWinRow() {
        model.makeMove(0, 0); // X
        model.makeMove(1, 0); // O
        model.makeMove(0, 1); // X
        model.makeMove(1, 1); // O
        model.makeMove(0, 2); // X - Winning move

        assertTrue(model.checkWin());
    }

    @Test
    void testCheckWinColumn() {
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(1, 0); // X
        model.makeMove(1, 1); // O
        model.makeMove(2, 0); // X - Winning move

        assertTrue(model.checkWin());
    }

    @Test
    void testCheckWinDiagonal() {
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.makeMove(1, 1); // X
        model.makeMove(1, 0); // O
        model.makeMove(2, 2); // X - Winning diagonal

        assertTrue(model.checkWin());
    }

    @Test
    void testCheckWinAntiDiagonal() {
        model.makeMove(0, 2); // X
        model.makeMove(0, 1); // O
        model.makeMove(1, 1); // X
        model.makeMove(1, 0); // O
        model.makeMove(2, 0); // X - Winning anti-diagonal

        assertTrue(model.checkWin());
    }

    @Test
    void testDrawCondition() {
        model.makeMove(0, 0); // X
        model.switchPlayer();
        model.makeMove(0, 1); // O
        model.switchPlayer();
        model.makeMove(0, 2); // X
        model.switchPlayer();
        model.makeMove(1, 0); // O
        model.switchPlayer();
        model.makeMove(1, 2); // X
        model.switchPlayer();
        model.makeMove(1, 1); // O
        model.switchPlayer();
        model.makeMove(2, 0); // X
        model.switchPlayer();
        model.makeMove(2, 2); // O
        model.switchPlayer();
        model.makeMove(2, 1); // X

        assertTrue(model.isBoardFull());
        assertFalse(model.checkWin()); // Ensure no win condition
    }






    @Test
    void testScoreUpdate() {
        model.updateScore('X');
        model.updateScore('O');
        model.updateScore('X');

        assertEquals(2, model.getPlayerScore());
        assertEquals(1, model.getComputerScore());
    }

    @Test
    void testSwitchPlayer() {
        assertEquals('X', model.getCurrentPlayer()); // Starts with 'X'
        model.switchPlayer();
        assertEquals('O', model.getCurrentPlayer()); // Should switch to 'O'
        model.switchPlayer();
        assertEquals('X', model.getCurrentPlayer()); // Should switch back to 'X'
    }
}
