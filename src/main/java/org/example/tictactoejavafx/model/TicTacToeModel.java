package org.example.tictactoejavafx.model;

public class TicTacToeModel {

    // Constants for the players and empty cell
    public static final char EMPTY = ' ';
    public static final char PLAYER_X = 'X';  // Player 1
    public static final char PLAYER_O = 'O';  // Player 2 or Computer

    // Game board and current player tracking
    private char[][] board;
    private char currentPlayer;

    // Track player names and scores
    private String player1Name;
    private String player2Name;
    private int scorePlayer1;
    private int scorePlayer2;

    // Game mode flag
    private boolean vsComputer;

    // Constructor to initialize the board and game mode
    public TicTacToeModel(String player1Name, String player2Name, boolean vsComputer) {
        this.board = new char[3][3];
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.vsComputer = vsComputer;
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

    // Method to get the board state
    public char[][] getBoard() {
        return board;
    }

    // Method to place a move
    public boolean placeMove(int row, int col) {
        if (board[row][col] == EMPTY) {
            board[row][col] = currentPlayer;
            switchPlayer();
            return true;
        }
        return false;  // Move is invalid if the cell is already occupied
    }

    // Method to switch the current player
    private void switchPlayer() {
        if (vsComputer && currentPlayer == PLAYER_X) {
            currentPlayer = PLAYER_O;  // Computer takes the next turn
        } else {
            currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        }
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
                    return false;  // At least one empty cell means the game is not a draw
                }
            }
        }
        return checkWinner() == EMPTY;  // If no winner and no empty cells, it's a draw
    }

    // Method to update the score of the winning player
    public void updateScore(char winner) {
        if (winner == PLAYER_X) {
            scorePlayer1++;
        } else if (winner == PLAYER_O) {
            scorePlayer2++;
        }
    }

    // Getters for player names and scores
    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public int getScorePlayer1() {
        return scorePlayer1;
    }

    public int getScorePlayer2() {
        return scorePlayer2;
    }
}
