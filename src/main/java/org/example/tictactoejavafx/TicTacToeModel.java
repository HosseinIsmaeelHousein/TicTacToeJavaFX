package org.example.tictactoejavafx;

public class TicTacToeModel {
    private char[][] board;
    private int playerScore;
    private int computerScore;
    private char currentPlayer;

    public TicTacToeModel() {
        resetGame();
        playerScore = 0;
        computerScore = 0;
    }

    public void resetGame() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X'; // Player starts the round
    }

    public boolean isValidMove(int row, int col) {
        return board[row][col] == ' ';
    }

    public boolean makeMove(int row, int col) {
        if (isValidMove(row, col)) {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }



    public boolean checkWin() {
        // Check rows for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }
        // Check columns for a win
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        // Check diagonals for a win
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }

        return false; // No win found
    }



    private boolean isLineMatch(char a, char b, char c) {
        return a != ' ' && a == b && b == c; // Only returns true if all are the same non-empty symbol
    }


    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void updateScore(char winner) {
        if (winner == 'X') {
            playerScore++;
        } else if (winner == 'O') {
            computerScore++;
        }
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isBoardEmpty() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}
