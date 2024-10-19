package org.example.tictactoejavafx.model;

public class TicTacToeModel {

    public static final char EMPTY = ' ';
    public static final char PLAYER_X = 'X';  // Human player
    public static final char PLAYER_O = 'O';  // Computer or second player

    private char[][] board;  // 3x3 board
    private char currentPlayer;  // Whose turn it is

    public TicTacToeModel() {
        board = new char[3][3];
        resetBoard();
    }

    // Method to reset the board
    public void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = EMPTY;
            }
        }
        currentPlayer = PLAYER_X;  // Player X starts by default
    }

    // Method to get the current player
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // Method to place a move
    public boolean placeMove(int row, int col) {
        if (board[row][col] == EMPTY) {
            board[row][col] = currentPlayer;
            // Switch players after a successful move
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
            return true;
        }
        return false;  // Move is invalid if cell is already occupied
    }

    // Method to check if a player has won
    public char checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];  // Winner in a row
            }
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];  // Winner in a column
            }
        }

        // Check diagonals
        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];  // Winner in the top-left to bottom-right diagonal
        }
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];  // Winner in the top-right to bottom-left diagonal
        }

        return EMPTY;  // No winner yet
    }

    // Method to check if the game is a draw
    public boolean isDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == EMPTY) {
                    return false;  // At least one empty cell means game is not a draw
                }
            }
        }
        return checkWinner() == EMPTY;  // If no winner and no empty cells, it's a draw
    }

    // Method to get the board state
    public char[][] getBoard() {
        return board;
    }
}
