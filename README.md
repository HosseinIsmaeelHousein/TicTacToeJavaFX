
# Tic-Tac-Toe Game

## Overview
This project is a simple yet interactive Tic-Tac-Toe game built with Java, implementing the Model-View-Controller (MVC) design pattern. The game provides a graphical user interface (GUI) using JavaFX, allowing a player to compete against the computer on a 3x3 grid.

The objective is to align three symbols in a row (either vertically, horizontally, or diagonally) to win. If all squares are filled without a winner, the game ends in a draw. Scores are tracked for both the player and the computer across multiple rounds.

## Project Structure

This project uses an MVC architecture to separate concerns and make the application modular and easy to understand. Here is an overview of each component:

1. **Model (`TicTacToeModel.java`)** - Handles the game logic, including player and computer turns, checking for a winner, and tracking the game state and scores.
2. **View (`TicTacToeView.java`)** - Manages the graphical user interface using JavaFX, displaying the game board and updating it based on actions.
3. **Controller (`GameController.java`)** - Acts as the intermediary between the model and the view. It receives inputs, updates the model, and refreshes the view accordingly.
4. **Main Application (`MainApp.java`)** - Launches the JavaFX application and initializes the model, view, and controller.

## Features

- **Graphical User Interface (GUI)**: The game has a GUI created with JavaFX, making it user-friendly and interactive.
- **Player vs. Computer Mode**: The game supports a single-player mode where the user plays against the computer.
- **Computer Move Delay**: The computer waits for 2 seconds before making its move, enhancing the player experience.
- **Win Detection and Draw Handling**: Automatically detects winning conditions and declares the winner. If all cells are filled without a winner, the game ends in a draw.
- **Score Tracking**: Tracks the score of both the player and the computer across multiple rounds.

## Requirements

- Java JDK 8 or above
- JavaFX Library

## How to Run the Game

1. Clone the Repository:
   ```bash
   git clone <repository_url>
   ```

2. Navigate to the Project Directory:
   ```bash
   cd tic-tac-toe-game
   ```

3. Compile the Project:
   Make sure JavaFX is included in your classpath if it’s not automatically configured.
   ```bash
   javac -classpath .;path/to/javafx-sdk/lib/*.jar MainApp.java
   ```

4. Run the Game:
   ```bash
   java -classpath .;path/to/javafx-sdk/lib/*.jar MainApp
   ```
   Replace `path/to/javafx-sdk/lib` with the actual path to the JavaFX SDK library on your machine.

## How to Play

1. Launch the game following the steps above.
2. The game will display a 3x3 grid representing the Tic-Tac-Toe board.
3. The player starts first and uses the "X" symbol.
4. Players take turns clicking on empty cells to mark their symbol.
5. The game checks for a win or a draw after each turn.
   - If a player or the computer aligns three symbols in a row, they win, and a message displays the result.
   - If the board is full and no player has won, the game declares a draw.
6. The game will automatically reset for the next round, keeping a running score of wins for both the player and the computer.

## Project Files and Classes

- **MainApp.java**: Initializes and starts the JavaFX application.
- **TicTacToeModel.java**: Defines the game’s core logic, handling players' moves, win conditions, board status, and score tracking.
- **TicTacToeView.java**: Manages the game’s visual components using JavaFX, displaying the game board, scores, and messages.
- **GameController.java**: Coordinates actions between the model and view, interpreting player moves, adding a delay for the computer's turn, and updating the view.

## Future Enhancements

Potential enhancements to the project could include:
- **AI Difficulty Levels**: Adding different levels for the computer AI to increase the challenge.
- **Sound Effects**: Adding sound effects for moves and win/draw outcomes to enhance gameplay.
- **Enhanced User Interface**: Improving the visual design for a more engaging experience.

## Conclusion

This Tic-Tac-Toe game demonstrates key programming concepts, including GUI development with JavaFX, implementing the MVC architectural pattern, and adding basic AI functionality. It serves as an excellent foundational project for expanding into more complex games or applications with enhanced interactivity.
